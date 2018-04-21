package com.sofn.web.tts;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.base.RedisService;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.MathUtil;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.SecurityUtil;
import com.sofn.core.util.ValidateCodeUtil;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.TtsScltxxcjRegiterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@RequestMapping(value = "/ttsLogin")
public class LoginController extends BaseController {
    /**
     * The Sso login service.
     */
    @Autowired
    private SSOLoginService ssoLoginService;

    ////-----
    /**
     * The Redis service.
     */
    @Autowired
    private RedisService redisService;

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

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
    @SystemControllerLog(description = "登录验证码",operationType="获取验证码")
    public Object generatorCode(ModelMap modelMap, HttpServletResponse response,
                                @ApiParam(required = false, value = "前一次的uuid")
                                @RequestParam(name = "prev", required = false) String prevUUID) {
        // 解决垮域请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        String uuid = ValidateCodeUtil.getUUID(prevUUID, ApiConstants.VALIDATE_CODE_LEN);
        modelMap.addAttribute("uuid", uuid);
        String code = (String) RedisUtil.get(uuid);
        modelMap.addAttribute("code", code);
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
    @SystemControllerLog(description = "登录验证码",operationType="刷新验证码")
    public void generatorImage(HttpServletRequest request, HttpServletResponse response,
                               @ApiParam(required = true, value = "刷新后UUID")
                               @RequestParam(name = "curr", required = false) String currUUID) throws Exception {
        Assert.isNotBlank(currUUID, "UUID");
        response.setHeader("Access-Control-Allow-Origin", "*");
        ValidateCodeUtil.getRandcode(request, response, currUUID);
    }

    /**
     * Login sso object.
     *
     * @param modelMap the model map
     * @param account  the account
     * @param password the password
     * @param uuid     the uuid
     * @param code     the code
     * @return the object
     */
    // todo 将 pc 或 app 参数存入 token 中 用于区分用户用什么登录系统的
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @SystemControllerLog(description = "追溯登录",operationType="用户登录")
    public Object login(ModelMap modelMap,
                        @ApiParam(required = true, value = "登录帐号")
                        @RequestParam(value = "account", required = false) String account,
                        @ApiParam(required = true, value = "登录密码")
                        @RequestParam(value = "password", required = false) String password,
                        @ApiParam(required = true, value = "刷新后UUID")
                        @RequestParam(name = "uuid", required = false) String uuid,
                        @ApiParam(required = true, value = "验证码")
                        @RequestParam(name = "code", required = false) String code) {
        // 参数检查
        Assert.isNotBlank(account, "ACCOUNT");
        Assert.isNotBlank(password, "PASSWORD");
        Assert.isNotBlank(uuid, "UUID");
        Assert.isNotBlank(code, "CODE");
        if (ValidateCodeUtil.checkCode(uuid, code)) {
            //登录逻辑
            Map<String, Object> map = ssoLoginService.login(account, password);
            if (map == null || map.size() < 1) {
                return setModelMap(new ModelMap(), HttpCode.PASSWORD_ERROR);
            }
            Map<String, Object> paramsMap = null;
            TtsScltxxcjRegiter ttsScltxxcjRegiter = null;
            try {
                paramsMap = new HashMap<String, Object>();
                paramsMap.put("account", account);
                ttsScltxxcjRegiter = ttsScltxxcjRegiterService.selectByAccount(paramsMap);
                if(null == ttsScltxxcjRegiter){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
                //缓存用户主体信息
                RedisUtil.set(ttsScltxxcjRegiter.getId().trim(), ttsScltxxcjRegiter, 3600);
            } catch (Exception e) {
                e.printStackTrace();
                return setModelMap(new ModelMap(), HttpCode.REQUEST_TIMEOUT);
            }
            modelMap.put("token",map.get("token"));
            //如果监管用户有，主体信息用户没有的话
            return setSuccessModelMap(modelMap, ttsScltxxcjRegiter);
        }
        return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }

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
    @SystemControllerLog(description = "追溯登出",operationType="注销登录")
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
     *
     * @param modelMap the model map
     * @return the object
     */
    @ApiOperation(value = "没有登录")
    @GetMapping("/unauthorized")
    @SystemControllerLog(description = "登录",operationType="验证有没有登录")
    public Object unauthorized(ModelMap modelMap) {
        SecurityUtils.getSubject().logout();
        return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
    }

    /**
     * Forbidden object.
     * 没有权限
     *
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
     *
     * @param image    the image
     * @param username the username
     * @param password the password
     * @return the object
     */
    @PostMapping("/addUserInfo")
    @SystemControllerLog(description = "测试添加用户",operationType="添加用户信息")
    public Object addUserInfo(
            @RequestParam("file") CommonsMultipartFile image,
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password) {

        return null;
    }


    /**
     * Upload object.
     * 上传文件
     *
     * @param file the file
     * @return the object
     * @throws IOException the io exception
     * @throws MyException the my exception
     */
    @PostMapping("/upload")
    @SystemControllerLog(description = "文件上传",operationType="添加")
    public Object upload(MultipartFile file) throws IOException, MyException {
        System.out.println(file.getSize());
        //return null;
        ClientGlobal.init("/Users/codeai/Downloads/SpringBoot/SpringBoot-Learning/client.conf");
        System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
        System.out.println("charset=" + ClientGlobal.g_charset);
        TrackerGroup tg = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress("172.16.7.157", 22122)});
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
        String item = "c:/windows/system32/notepad.exe";
        String fileid = sc1.upload_file1(file.getBytes(), "jpg", meta_list);
        System.out.println("Upload local file " + item + " ok, fileid=" + fileid);
        return null;
    }

    /**
     * Get sys user object.
     *
     * @param modelMap the model map
     * @return the object
     */
    @PostMapping("/cache_test")
    @SystemControllerLog(description = "缓存",operationType="缓存测试")
    public Object getSysUser(ModelMap modelMap) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("getSysUser", ssoLoginService.getSysUser("1"));
        CurrentUser sysUser = new CurrentUser();
        double randomDub = MathUtil.getRandom(1, 4).doubleValue();
        String randomStr = com.xiaoleilu.hutool.util.MathUtil.roundStr(randomDub, 3);
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
    public Object register(CurrentUser userModel) {
        userModel.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(userModel.getPassword())));
        ssoLoginService.register(userModel);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "得到服务器企业用户实体对象")
    @GetMapping("/getEntityByRedis")
    @SystemControllerLog(description = "获取企业实体对象",operationType="查询")
    public Object getEntityByRedis(ModelMap modelMap, @RequestBody String id) {
        TtsScltxxcjRegiter ttsScltxxcjRegiter = ssoLoginService.getEntityByRedis(id);
        Map<String, Object> resultMap = new HashMap<>();
        if (null != ttsScltxxcjRegiter) {
            resultMap.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            resultMap.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            resultMap.put("entityObj", ttsScltxxcjRegiter);
        } else {
            resultMap.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            resultMap.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
        }
        return setSuccessModelMap(modelMap, resultMap);
    }
}
