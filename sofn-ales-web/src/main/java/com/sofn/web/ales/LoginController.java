package com.sofn.web.ales;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseController;
import com.sofn.core.config.Resources;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.LoginException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.ValidateCodeUtil;
import com.sofn.core.util.encrypt.PublicKeyMap;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.service.ales.SSOLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by dong4j on 16/9/13.
 * Description: 调用单点系统进行验证登录
 */
@RestController
@Api(value = "单点登录接口", description = "单点登录接口")
@RequestMapping(value = "/login")
public class LoginController extends BaseController  {
    /**
     * The Sso login service.
     */
    @Autowired
    private SSOLoginService ssoLoginService;

    /**
     * Login sso object.
     *
     * @param modelMap the model map
     * @param ciphertext  the account
     * @param desKey the password
     * @return the object
     */
//////////////////////////////////////////////////////////////////////////
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Object login(ModelMap modelMap,@ApiParam(required = true, value = "密文")
    @RequestParam(value = "ciphertext", required = false) String ciphertext,
                        @ApiParam(required = true, value = "RSA加密后的key")
                            @RequestParam(value = "desKey", required = false) String desKey) {
        //将原有参数定义为变量
        String account=null;
        String password=null;
        String uuid=null;
        String code=null;
        //解密-----------------------------------------------------------------------------
        try {
            //将字符串转换为JSON对象
            JSONObject json= RSAUtils.decrypt(ciphertext,RSAUtils.Module.ALES,desKey);
            //将解密后的json值赋值给变量
            if(json.get("account")!=null)account=json.get("account").toString();
            if(json.get("password")!=null)password=json.get("password").toString();
            if(json.get("uuid")!=null)uuid=json.get("uuid").toString();
            if(json.get("code")!=null)code=json.get("code").toString();
        }catch (Exception ex){
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        // 参数检查
        Assert.isNotBlank(account, "ACCOUNT");
        Assert.isNotBlank(password, "PASSWORD");
        Assert.isNotBlank(uuid, "UUID");
        Assert.isNotBlank(code, "CODE");
        if (ValidateCodeUtil.checkCode(uuid, code)) {
            //登录逻辑
            Map<String, Object> map = ssoLoginService.login(account, password);
            if (map == null) {
                throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
            }
            //WebUtil.saveCurrentUser(map.get("user"));
            CurrentUser userModel = (CurrentUser)map.get("user");
            try {
                String token=RSAUtils.encode(map.get("token").toString(),RSAUtils.Module.ALES,desKey);
                //添加返回加密后的token
                modelMap.addAttribute("token", token);
            }catch (UnsupportedEncodingException ex){
                return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
            }
            modelMap.addAttribute("user",userModel);
            modelMap.addAttribute("userType", userModel.getUserType());
//            modelMap.addAttribute("currentUser",WebUtil.getCurrentUser());
            return setSuccessModelMap(modelMap);
        }
        throw new LoginException(Resources.getMessage("CODE_ERROR"));
    }
    /////////////////////////////////////////////////////////////////////////

    /**
     * Gets uuid.
     * 前端刷新登录页面或者点击验证码图片时调用此接口,用于生成uuid
     * @param modelMap the model map
     * @param prevUUID the prev uuid 如果没有则不传
     * @return the uuid
     */
    @ApiOperation(value = "加载login.html时向后台获取UUID")
    @RequestMapping(value = "/getUUID",method = RequestMethod.GET)
    public Object generatorCode(ModelMap modelMap,HttpServletResponse response,
                                @ApiParam(required = false, value = "前一次的uuid")
                                @RequestParam(name = "prev", required = false) String prevUUID) {
        // 解决垮域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        modelMap.addAttribute("uuid",ValidateCodeUtil.getUUID(prevUUID, ApiConstants.VALIDATE_CODE_LEN));
        return setSuccessModelMap(modelMap);
    }

    /**
     * Generator image.
     * 前端先调用 generatorCode 获取uuid,然后将uuid传入到此接口获取验证码图片反给前端
     * @param request  the request
     * @param response the response
     * @param currUUID the curr uuid
     * @throws Exception the exception
     */
    @ApiOperation(value = "生成验证码")
    @RequestMapping(value="/generatorImage",method = RequestMethod.GET)
    public void generatorImage(HttpServletRequest request, HttpServletResponse response,
                               @ApiParam(required = true, value = "刷新后UUID")
                               @RequestParam(name = "curr", required = false) String currUUID) throws Exception {
        Assert.isNotBlank(currUUID, "CURRUUID");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ValidateCodeUtil.getRandcode(request, response, currUUID);
    }

    /**
     * Logout object.
     * 用户登出
     * @param modelMap the model map
     * @param token    the token
     * @return the object
     */
    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    public Object logout(ModelMap modelMap,
                         @ApiParam(required = true, value = "token")
                         @RequestHeader(value = "token", defaultValue = "") String  token,
                         @ApiParam(required = true, value = "pc or app")
                         @RequestHeader(value = "type", defaultValue = "") String  type) {
        Assert.isNotBlank(token,"TOKEN");
        Assert.isNotBlank(type,"TYPE");
        ssoLoginService.logout(token, type);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取公钥")
    @PostMapping("/getPublicKey")
    public Object getPublicKey(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) {
        try{
            PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap(RSAUtils.Module.ALES);
            modelMap.put("publicKey",publicKeyMap);
            return setSuccessModelMap(modelMap);
        }catch (Exception e){
            e.printStackTrace();
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }

    }
}
