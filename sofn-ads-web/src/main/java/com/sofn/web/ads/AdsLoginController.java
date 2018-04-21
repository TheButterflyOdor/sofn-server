package com.sofn.web.ads;

import com.alibaba.fastjson.JSONObject;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.base.RedisService;
import com.sofn.core.config.Resources;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.LoginException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.*;
import com.sofn.core.util.encrypt.PublicKeyMap;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.service.ads.SSOLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: dong4j
 * Date: 2016-09-18
 * Time: 10:12
 * Description:
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class AdsLoginController extends BaseController {
    /**
     * The Sso login service.
     */
    @Autowired
    private SSOLoginService ssoLoginService;
    /*@Autowired
    private SysOrganizationService sysOrgService;*/
    /**
     * The Redis service.
     */
    @Autowired
    private RedisService    redisService;

    /**
     * Gets uuid.
     * 前端刷新登录页面或者点击验证码图片时调用此接口,用于生成uuid
     *
     * @param modelMap the model map
     * @param response the response
     * @param prevUUID the prev uuid 如果没有则不传
     * @return the uuid
     */
    @ApiOperation(value = "加载login.html时向后台获取UUID")
    @RequestMapping(value = "/getUUID", method = RequestMethod.GET)
    @SystemControllerLog(description = "查询验证码uuid",operationType="查询")
    public Object generatorCode(ModelMap modelMap, HttpServletResponse response,
                                @ApiParam(required = false, value = "前一次的uuid")
                                @RequestParam(name = "prev", required = false) String prevUUID) {
        // 解决垮域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        String uuid = ValidateCodeUtil.getUUID(prevUUID, ApiConstants.VALIDATE_CODE_LEN);
        modelMap.addAttribute("uuid", uuid);
        String code = (String) RedisUtil.get(uuid);
        modelMap.addAttribute("code",code);
        return setSuccessModelMap(modelMap);
    }

    /**
     * Generator image.
     * 前端先调用 generatorCode 获取uuid,然后将uuid传入到此接口获取验证码图片反给前端
     *
     * @param request  the request
     * @param response the response
     * @param currUUID the curr uuid
     * @throws Exception the exception
     */
    @ApiOperation(value = "生成验证码")
    @RequestMapping(value = "/generatorImage", method = RequestMethod.GET)
    @SystemControllerLog(description = "生成验证码",operationType="查询")
    public void generatorImage(HttpServletRequest request, HttpServletResponse response,
                               @ApiParam(required = true, value = "刷新后UUID")
                               @RequestParam(name = "curr", required = false) String currUUID) throws Exception {
        Assert.isNotBlank(currUUID, "UUID");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ValidateCodeUtil.getRandcode(request, response, currUUID);
    }

    /**
     * Login object.
     *
     * @param modelMap the model map
     * @param account  the account
     * @param password the password
     * @return the object
     */
    //<editor-fold desc="不再使用">
//    @ApiOperation(value = "用户登录")
//    @PostMapping("/login_unable")   /*等效于 @RequestMapping(value = "/login",method = RequestMethod.POST) */
//    public Object login_unable(ModelMap modelMap,
//			/*@ApiParam为swagger使用的参数注解*/
//                        @ApiParam(required = true, value = "登录帐号")
//                        @RequestParam(value = "account", required = false) String account,
//                        @ApiParam(required = true, value = "登录密码")
//                        @RequestParam(value = "password", required = false) String password) {
//        // 参数检查
//
//        //登录逻辑
//        String encryptPassword = sysUserService.encryptPassword(password);
//        //<editor-fold desc="Todo app 登录和 pc 登录的 token 不一样">
//        //		Map<String, Object> result = LoginHelper.login(account,aa,"returnMap");
////		if((Boolean)result.get("isAuthenticated")){
////		if (LoginHelper.login(account,aa)) {
//        // 2016年08月09日  下午 4:59:30 测试数据code
//        // 如果登陆成功 且是 pc 登陆 生成 token
////			String token = "";
////			// 如果是 app 登陆
////			if(Objects.equals("app", ApiConstants.TYPE_APP)){
////				// 根据用户 id 生成 token
////				token = StringUtils.produceAppToken(account);
////				// 存入 redis
////				token = redisService.putAccessToken(account, token);
////			}else { // 网页端接口
////				token = StringUtils.producePcToken(account);
////				token = redisService.putBuyerToken(account, token);
////			}
//        //</editor-fold>
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("countSql", 0);
//        params.put("enable", 1);
//        params.put("account", account);
//        PageInfo<SysUser> pageInfo          = sysUserService.query(params);
//        AccessToken       accessTokenEntity = new AccessToken();
//        String            accessToken       = "";
//        if (pageInfo.getSize() == 1) {
//            SysUser user = pageInfo.getList().get(0);
//            if (user.getPassword().equals(encryptPassword)) {
//                WebUtil.saveCurrentUser(user.getId());
//                //拼装accessToken
//                accessToken = JwtHelper.createJWT(account,
//                        user.getId(),
//                        "admin",
//                        Global.getConfig("sofn.api.clientId"),
//                        Global.getConfig("sofn.api.name"),
//                        Long.parseLong(Global.getConfig("sofn.api.expiresSecond")) * 1000,
//                        Global.getConfig("sofn.api.base64Secret"));
//                // 存入 redis
//                modelMap.addAttribute("token", redisService.putPcToken(account + user.getId(), accessToken));
//                return setSuccessModelMap(modelMap);
//            } else {
//                throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
//            }
//        } else {
//            throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
//        }
//    }

    //</editor-fold>

    /**
     * 登录逻辑
     * @param modelMap
     * @param ciphertext
     * @param desKey
     * @return
     */
//////////////////////////////////////////////////////////////////////////
    // todo 将 pc 或 app 参数存入 token 中 用于区分用户用什么登录系统的
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @SystemControllerLog(description = "登录验证",operationType="查询")
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
            JSONObject json= RSAUtils.decrypt(ciphertext,RSAUtils.Module.ADS,desKey);
            //将解密后的json值赋值给变量
            if(json.get("account")!=null)account=json.get("account").toString();
            if(json.get("password")!=null)password=json.get("password").toString();
            if(json.get("uuid")!=null)uuid=json.get("uuid").toString();
            if(json.get("code")!=null)code=json.get("code").toString();
        }catch (Exception ex){
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }
        //end 解密-----------------------------------------------------------------------------
        // 参数检查
        Assert.isNotBlank(account, "ACCOUNT");
        Assert.isNotBlank(password, "PASSWORD");
        Assert.isNotBlank(uuid, "UUID");

        if (ValidateCodeUtil.checkCode(uuid, code)) {
            // if(true){
            //登录逻辑
            Map<String, Object> map = ssoLoginService.login(account, password);
            if (map == null) {
                throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
            }
            CurrentUser userModel = (CurrentUser) map.get("user");
            //查询机构信息并缓存
            CurrentUser user = (CurrentUser) map.get("user");
            ////如果是超级管理员等无机构的则不查询
            //if(userModel.getOrganizationId()!=null&&!userModel.getOrganizationId().equals("")) {
            //    SysOrganization org = sysOrgService.queryById(userModel.getOrganizationId());
            //    user.setOrganization(org);
            //}
            //根据业务需要将返回数据加密后返回
            try {
                String token=RSAUtils.encode(map.get("token").toString(),RSAUtils.Module.ADS,desKey);
                //添加返回加密后的token
                modelMap.addAttribute("token", token);
                modelMap.addAttribute("userType", user.getUserType());
            }catch (UnsupportedEncodingException ex){
                return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
            }
            //modelMap.addAttribute("user-记得删除", userModel);
            return setSuccessModelMap(modelMap);

        }
        throw new LoginException(Resources.getMessage("CODE_ERROR"));
        /*if (ValidateCodeUtil.checkCode(uuid, code)) {
       // if(true){
            //登录逻辑
            Map<String, Object> map = ssoLoginService.login(account, password);
            if (map == null) {
                throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
            }
            CurrentUser userModel = (CurrentUser)map.get("user");
            //查询机构信息并缓存
            CurrentUser user= (CurrentUser) map.get("user");
            //如果是超级管理员等无机构的则不查询
            if(userModel.getOrganizationId()!=null&&!userModel.getOrganizationId().equals("")) {
                *//*SysOrganization org = sysOrgService.queryById(userModel.getOrganizationId());
                user.setOrganization(org);*//*
            }
            WebUtil.saveCurrentUser(user);
            modelMap.addAttribute("token",map.get("token"));
            //存入redis
            RedisUtil.set(map.get("token").toString(),user);
            //modelMap.addAttribute("user-记得删除",userModel);
            return setSuccessModelMap(modelMap);

        }
        throw new LoginException(Resources.getMessage("CODE_ERROR"));*/
    }
    /////////////////////////////////////////////////////////////////////////

    /**
     * Logout object.
     *
     * @param modelMap the model map
     * @param token    the token
     * @param type     the type
     * @return the object
     */
    @ApiOperation(value = "用户登出")
    @PostMapping("/logout")
    @SystemControllerLog(description = "用户登出",operationType="查询")
    public Object logout(ModelMap modelMap,
                         @ApiParam(required = true, value = "token")
                         @RequestHeader(value = "token", defaultValue = "") String token,
                         @ApiParam(required = true, value = "pc or app")
                         @RequestHeader(value = "type", defaultValue = "") String type) {
        ssoLoginService.logout(token, type);
        return setSuccessModelMap(modelMap);
    }

    /**
     * Unauthorized object.
     * 没有登录
     * @param modelMap the model map
     * @return the object
     */
    @ApiOperation(value = "没有登录")
    @GetMapping("/unauthorized")
    public Object unauthorized(ModelMap modelMap) {
        SecurityUtils.getSubject().logout();
        return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
    }

    /**
     * Forbidden object.
     * 没有权限
     * @param modelMap the model map
     * @return the object
     */
    @ApiOperation(value = "没有权限")
    @GetMapping("/forbidden")
    public Object forbidden(ModelMap modelMap) {
        return setModelMap(modelMap, HttpCode.FORBIDDEN);
    }


    /**
     * Add user info object.
     * todo 添加用户信息测试
     * @param image    the image
     * @param username the username
     * @param password the password
     * @return the object
     */
    @PostMapping("/addUserInfo")
    public Object addUserInfo(
            @RequestParam("file") CommonsMultipartFile image,
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password) {

        return null;
    }


    /**
     * Upload object.
     * 上传文件
     * @param file the file
     * @return the object
     * @throws IOException the io exception
     * @throws MyException the my exception
     */
    @PostMapping("/upload")
    public Object upload(MultipartFile file) throws IOException, MyException {
        System.out.println(file.getSize());
        //return null;
        ClientGlobal.init("/Users/codeai/Downloads/SpringBoot/SpringBoot-Learning/client.conf");
        System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        System.out.println("charset=" + ClientGlobal.g_charset);
        TrackerGroup  tg = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress("172.16.7.157", 22122)});
        TrackerClient tc = new TrackerClient(tg);
        TrackerServer ts = tc.getConnection();
        if (ts == null) {
            System.out.println("getConnection return null");
            return null;
        }
        StorageServer ss = tc.getStoreStorage(ts);
        if (ss == null) {
            System.out.println("getStoreStorage return null");
        }
        StorageClient1 sc1 = new StorageClient1(ts, ss);
        NameValuePair[] meta_list = null;  //new NameValuePair[0];
        String          item      = "c:/windows/system32/notepad.exe";
        String          fileid    = sc1.upload_file1(file.getBytes(), "jpg", meta_list);
        System.out.println("Upload local file " + item + " ok, fileid=" + fileid);
        return null;
    }

    /**
     * Get sys user object.
     *  todo 缓存测试
     * @param modelMap the model map
     * @return the object
     */
    @PostMapping("/cache_test")
    public Object getSysUser(ModelMap modelMap) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getSysUser", ssoLoginService.getSysUser("1"));
        CurrentUser sysUser   = new CurrentUser();
        double    randomDub = MathUtil.getRandom(1, 4).doubleValue();
        String    randomStr = com.xiaoleilu.hutool.util.MathUtil.roundStr(randomDub, 3);
        logger.info("randomStr {}", randomStr.length());
        sysUser.setUserName("test_" + randomStr);
        sysUser.setAccount("test_" + randomStr);
        sysUser.setPassword("1234");
        sysUser.setPhone("18628362906");
        sysUser.setCreateBy("admin");
        sysUser.setUpdateTime(new Date());
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateBy("admin");
        resultMap.put("insertSelective", ssoLoginService.insertSelective(sysUser));
        resultMap.put("delete", ssoLoginService.deleteByPrimaryKey("201609211150483422", "test"));
        return setSuccessModelMap(modelMap, resultMap);
    }

    @PostMapping("/register")
    public Object register(CurrentUser userModel){
        userModel.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(userModel.getPassword())));
        ssoLoginService.register(userModel);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "获取公钥")
    @PostMapping("/getPublicKey")
    @SystemControllerLog(description = "获取公钥",operationType="查询")
    public Object getPublicKey(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) {
        try{
            PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap(RSAUtils.Module.ADS);
            modelMap.put("publicKey",publicKeyMap);
            return setSuccessModelMap(modelMap);
        }catch (Exception e){
            e.printStackTrace();
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }

    }

}
