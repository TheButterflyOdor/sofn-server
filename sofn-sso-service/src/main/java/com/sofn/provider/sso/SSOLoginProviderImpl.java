package com.sofn.provider.sso;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.config.Global;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.exception.RegisterException;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.*;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.sso.SSOLoginMapper;
import com.sofn.dao.sso.SSORoleMapper;
import com.sofn.dao.sso.SSOUserRoleMapper;
import com.sofn.model.sso.SysRole;
import com.sofn.model.sso.SysUserRole;
import io.jsonwebtoken.Claims;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.*;

/**
 * Created by dong4j on 16/9/13.
 * Description: 单点登录逻辑,验证用户合法性,生成 token 存入 redis, 并返回 token
 */
@DubboService(interfaceClass = SSOLoginProvider.class)
@CacheConfig(cacheNames = "SSOLoginProvider")
public class SSOLoginProviderImpl extends BaseProviderImpl<CurrentUser> implements SSOLoginProvider {
    private final Logger logger = LoggerFactory.getLogger(SSOLoginProviderImpl.class);
    private static final String SSO_LOGIN_URL = Global.getConfig("primeton.ssologin.url");
    @Autowired
    private SSOLoginMapper ssoLoginMapper;
    @Autowired
    private SSORoleMapper ssoRoleMapper;
    @Autowired
    private SSOUserRoleMapper ssoUserRoleMapper;
    @Override
    public Map<String, Object> login(String account, String password) {
        boolean simulateLoginResult = false; // 模拟登录普元单点登录服务器结果
        String accessToken = "";
        Map<String, Object> resultMap = new HashMap<>();
        CurrentUser user = null;

        try {
            simulateLoginResult = simulateLogin(account, password); // 模拟登录普元单点登录服务器
        } catch (Exception e) {
            logger.debug("模拟登录普元单点服务器发生异常！", e);
        }

        // 模拟登录普元单点登录服务器成功后,获取登录用户的相关信息
        if (simulateLoginResult) {
            user = ssoLoginMapper.login(account, SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password)));
        }

        if (user != null) {
            String currentUserKey = WebUtil.generateRedisCurrentUserKey(user.getId());
            String tokenKey = WebUtil.generateRedisTokenKey(account, user.getId(), ApiConstants.PCTOKEN);
            String status = user.getStatus();

            if (!"1".equals(status)) {
                resultMap.put("status", status);
                return resultMap;
            }
            // 创建accessToken
            accessToken = JwtHelper.createJWT(account,
                    user.getId(),
                    "admin",
                    Global.getConfig("sofn.api.clientId"),
                    Global.getConfig("sofn.api.name"),
                    Long.parseLong(Global.getConfig("sofn.api.expiresSecond")) * 1000,
                    Global.getConfig("sofn.api.base64Secret"));
            if (RedisUtil.get(tokenKey) != null) {
                RedisUtil.del(tokenKey);
                RedisUtil.del(currentUserKey);
            }
            // 存入 redis todo 重新设计 key 分为 pc 和 app 端
            RedisUtil.set(tokenKey, accessToken, ApiConstants.PC_IDCODE_TIMEOUT);
            // 缓存当前用户
            RedisUtil.set(currentUserKey, user, ApiConstants.PC_IDCODE_TIMEOUT);
            resultMap.put("token", accessToken);
            resultMap.put("权限列表", "未实现");
            resultMap.put("user", user);
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> loginApp(String account, String password) {

        String accessToken = "";

        CurrentUser user = ssoLoginMapper.login(account,SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password)));
        if(user != null){
            // 创建accessToken
            accessToken = JwtHelper.createJWT(account,
                    user.getId(),
                    "admin",
                    Global.getConfig("sofn.api.clientId"),
                    Global.getConfig("sofn.api.name"),
                    Long.parseLong(Global.getConfig("sofn.api.expiresSecond")) * 1000,
                    Global.getConfig("sofn.api.base64Secret"));
            // 存入 redis todo 重新设计 key 分为 pc 和 app 端
            RedisUtil.set(WebUtil.generateRedisTokenKey(account, user.getId(), ApiConstants.APPTOKEN), accessToken, ApiConstants.APP_TOKEN_TIMEOUT);
            System.out.println(account + user.getId() + "_" + ApiConstants.APPTOKEN);
//            RedisUtil.set("token123", accessToken, -1);
            // 缓存当前用户
            String currentUserKey = WebUtil.generateRedisCurrentUserKey(user.getId());
            RedisUtil.set(currentUserKey, user);
            // 用来存贮token和权限列表
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("token",accessToken);
//            resultMap.put("权限列表","未实现");
            resultMap.put("user",user);
            return resultMap;
        }

        return null;
    }

    /**
     * 通过token删除redis中的数据,标识此用户退出系统
     * @param token the token
     */
    @Override
    public void logout(String token,String type) {
        if(StringUtils.isNotBlank((String)RedisUtil.get(token == null ? "" : ""))){
            RedisUtil.del(token);
        }
        // 解析 token
        Claims claims = JwtHelper.parseJWT(token, Global.getConfig("sofn.api.base64Secret"));
        if(claims != null){
            // 解析之后的token,组装为redis中的key 帐号_pc-token  或者 帐号_app-token
            RedisUtil.del(claims.get("account") + "_" + (type.equals("pc") ? ApiConstants.PCTOKEN : ApiConstants.APPTOKEN));
        }
    }


    @Override
    public Integer register(CurrentUser userModel) {
        if(repeatCheck(userModel)){
            throw  new RegisterException(ApiConstants.REGISTER_FAIL);
        }

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("roleCode","SUBJ");
        List<SysRole> roles=ssoRoleMapper.queryByParam(map);
        //强制要求数据库必须有企业角色
        if(roles==null||roles.size()==0){
            return 0;
        }




        
//        CurrentUser newUser = new CurrentUser();
        userModel.setCreateBy("admin");
        userModel.setCreateTime(new Date());
        userModel.setUpdateBy("admin");
        userModel.setUpdateTime(new Date());
        userModel.setId(StringUtils.getUUIDString());
        userModel.setRoleId(roles.get(0).getId());
//        newUser.setPassword(userModel.getPassword()).setAccount(userModel.getAccount()).setUserName(userModel.getUserName());
//        newUser.setEmail(userModel.getEmail());
//        newUser.setPostId(userModel.getPostId()).setRoleId(userModel.getRoleId()).setStatus(userModel.getStatus()).setDeptId(userModel.getDeptId());
        int result=ssoLoginMapper.register(userModel);
        //配置用户角色
        SysUserRole sur=new SysUserRole();
        sur.setRoleId(roles.get(0).getId());
        sur.setUserId(userModel.getId());
        sur.setUpdateTime(new Date());
        String key = sur.getClass().getSimpleName();
        sur.setId(createId(key));
        sur.setCreateTime(new Date());
        sur.setDelFlag("N");
        ssoUserRoleMapper.insert(sur);
        return result;
    }

    @Override
    public Boolean repeatCheck(CurrentUser user) {
        Integer alreadyExist = ssoLoginMapper.queryByUserModel(user);
        return (alreadyExist != null && alreadyExist != 0);
    }

    @Override
    public void updatePassword(String token, String oldPassword, String newPassword) {
        // 解析 token
        Claims claims = JwtHelper.parseJWT(token, Global.getConfig("sofn.api.base64Secret"));
        if(claims != null){
            String userId = (String) claims.get("userId");
            CurrentUser currentUser = ssoLoginMapper.selectByPrimaryKey(userId);
            if(currentUser == null){
                throw new IllegalParameterException("参数错误");
            }
            if(currentUser.getPassword().equals(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(oldPassword)))){
                throw new IllegalParameterException("密码错误");
            }
            currentUser.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(newPassword)));
            if(ssoLoginMapper.updatePassword(currentUser) != 1){
                throw new DataParseException("修改失败");
            }
        }
    }

    @Override
    public Integer delUser(String account) {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setAccount(account);
        if(!repeatCheck(currentUser)){
            throw  new RegisterException(ApiConstants.DEL_USER_FAIL);
        }
        return ssoLoginMapper.delUser(currentUser);
    }


    @Override
    public PageInfo<CurrentUser> query(Map<String, Object> params) {
        // 设置查询条件
        this.startPage(params);
        // 查询满足条件的 id
        Page<String> page = ssoLoginMapper.query(params);
        return getPage(page);
    }

    /**
     * HttpClient模拟登录普元单点登录服务器
     *
     * @param account 账号
     * @param password 密码
     * @return 登录结果
     * @throws Exception
     */
    private boolean simulateLogin(String account, String password) throws Exception {
        boolean loginPassed = false; // 登录成功标识
        String lt = ""; // 登录表单隐藏域元素的value值

        // 当普元单点登录服务器的登录地址不存在时忽略模拟登录操作
        if (org.springframework.util.StringUtils.isEmpty(SSO_LOGIN_URL) || SSO_LOGIN_URL.equals("${primeton.ssologin.url}")) {
            return true;
        }

        final CloseableHttpClient httpclient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet(SSO_LOGIN_URL);
        CloseableHttpResponse response = httpclient.execute(httpGet); // get请求单点登录服务器登录页面
        int status = response.getStatusLine().getStatusCode();

        if (status >= 200 && status < 300) {
            String responseText = EntityUtils.toString(response.getEntity(), Consts.UTF_8); // 把响应消息体转成文本内容
            // 解析html内容，获取指定表单隐藏域元素
            Element hiddenInput = Jsoup.parse(responseText, SSO_LOGIN_URL).selectFirst("input[name=\"lt\"]");
            // 获取隐藏域元素的value值，该值之后会作为表单登录的参数
            lt = hiddenInput.attr("value");

            /**
             * 以下为表单登录逻辑
             */

            HttpPost httpPost = new HttpPost(SSO_LOGIN_URL);
            List<NameValuePair> formParams = new ArrayList<>();
            // 添加表单参数
            formParams.add(new BasicNameValuePair("username", account));
            formParams.add(new BasicNameValuePair("password", password));
            formParams.add(new BasicNameValuePair("_eventId", "submit"));
            formParams.add(new BasicNameValuePair("lt", lt));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8); // 构造包含已参数转码的请求体
            httpPost.setEntity(entity); // 将请求体添加到post请求中

            CloseableHttpResponse response2 = httpclient.execute(httpPost); // post提交表单内容
            status = response2.getStatusLine().getStatusCode();

            if (status >= 200 && status < 300) {
                Header[] cookieHeader = response2.getHeaders("Set-Cookie"); // 获取包含cookie信息的响应头
                for (Header header : cookieHeader) {
                    String cookie = header.getValue();
                    // 如果存在名称为CASTGC的cookie，表示登录单点登录服务器成功
                    if (cookie.contains("CASTGC")) {
                        loginPassed = true;
                    }
                }

                EntityUtils.consume(response2.getEntity());
            } else {
                logger.debug("post提交登录表单返回了非正常的状态码：{}", status);
            }

        } else {
            logger.debug("get请求登录页面返回了非正常的状态码：{}", status);
        }

        httpclient.close();

        if (loginPassed) {
            logger.debug("登录普元单点登录服务器成功！");
        } else {
            logger.debug("登录普元单点登录服务器失败！");
        }
        return loginPassed;
    }
}
