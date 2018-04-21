package com.sofn.web.tts;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.config.Global;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.JwtHelper;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUser;
import com.sofn.service.tts.TtsScltxxcjRegiterService;
import com.sofn.service.tts.TtsScltxxcjUserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

@RestController
@Api(value = "根据token获取用户信息以及主体信息", description = "根据token获取用户信息以及主体信息")
@RequestMapping(value = "/ttsScltxxcjAllUser",method = RequestMethod.POST)
public class TtsScltxxcjGetUserAllInfoController extends BaseController{

    @Autowired
    public TtsScltxxcjUserService ttsScltxxcjUserService;
    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

    @ApiOperation(value = "根据token获取子账号&主账号信息")
    @RequestMapping(value = "/getTtsScltxxcjUserByToken",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据token获取子账号&主账号信息",operationType = "查询")
    public Map<String,Object> getUserByToken(HttpServletRequest request){
        Map<String,Object> info = new HashMap<String,Object>();
        try {
            String token = request.getParameter("token");
            Claims claims = JwtHelper.parseJWT(token, Global.getConfig("sofn.api.base64Secret"));
            String account = "";
            //账号为空则直接返回
            if (StringUtils.isNullOrEmpty(claims)){
                info.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
                info.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
                return info;
            }else{
                account = (String) claims.get("account");
            }
            //根据账号查询用户信息
            TtsScltxxcjUser userInfo = ttsScltxxcjUserService.getUserByTokenAccount(account);
            //根据用户身份码查询主账号信息
            TtsScltxxcjRegiter mainInfo = ttsScltxxcjRegiterService.getInfoByUserInfo(userInfo.getEntityIdcode(),userInfo.getUserIdcode());
            //如果为空直接返回
            if (StringUtils.isNullOrEmpty(userInfo) || StringUtils.isNullOrEmpty(mainInfo)){
                info.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
                info.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
                return info;
            }
            info.put("userInfo",userInfo);
            info.put("mainInfo",mainInfo);
            //返回用户信息
            info.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            info.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            return info;
        }catch (Exception e){
            info.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            info.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return info;
        }
    }
}
