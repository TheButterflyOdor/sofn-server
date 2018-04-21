package com.sofn.core.util;

import com.sofn.core.Constants;
import com.sofn.core.config.Global;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Web层辅助类
 *
 * @author dong4j
 * @version 2016年11月02日  上午 12:21:19 dong4j
 */
public final class WebUtil {
    /**
     * Instantiates a new Web util.
     */
    private WebUtil() {
    }

    /**
     * 保存当前用户
     *
     * @param user the user
     */
    public static void saveCurrentUser(CurrentUser user) {
        String key = user.getId() + Constants.CURRENT_USER;
        RedisUtil.set(key,user);
    }

    /**
     * 获取当前用户名Id todo 后期删除
     * @return the current user id
     */
    public static String getCurrentUserId() {
        HttpServletRequest  request  = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if("".equals(token))
            return ApiConstants.DEFAULT_USER;
        String userId = WebUtil.getCurrentUserId(token);
        if("".equals(userId))
            return ApiConstants.DEFAULT_USER;
        else
            return userId ;
    }

    /**
     * Gets current user id.
     * 根据 token 获取当前用户 id
     * @param token the token
     * @return the current user id
     */
    public static String getCurrentUserId(String token) {
        CurrentUser currentUser = getCurrentUser(token);
        if(currentUser != null){
            return currentUser.getId();
        }
        return ApiConstants.DEFAULT_USER;
    }

    /**
     * Gets current user.
     * 获取当前用户对象 后期删除
     * @return the current user
     */
    public static CurrentUser getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                return (CurrentUser)session.getAttribute(Constants.CURRENT_USER);
            }
        }
        return null;
    }

    /**
     * 获取当前用户名称
     * @param token
     * @return
     */
    public static String getCurrentUserName(String token){
        CurrentUser currentUser = getCurrentUser(token);
        if(currentUser != null){
            return currentUser.getAccount();
        }
        return ApiConstants.DEFAULT_USER;
    }

    /**
     * Gets current user.
     * 根据 token 获取当前用户
     * @param token the token
     * @return the current user
     */
    public static CurrentUser getCurrentUser(String token) {
        // 解析 token
        Claims claims = JwtHelper.parseJWT(token, Global.getConfig("sofn.api.base64Secret"));
        System.out.println("claims:"+claims);
        if (claims != null) {
            String userId  = (String) claims.get("userId");
            String currentUserKey = generateRedisCurrentUserKey(userId);
            // 从 redis 获取
            System.out.println("currentUser:"+RedisUtil.get(currentUserKey));
            return (CurrentUser)RedisUtil.get(currentUserKey);
        }
        return null;
    }

    /**
     * 移除当前用户
     * @param token the token
     */
    public static void removeCurrentUser(String token) {
        RedisUtil.del(getCurrentUserId(token)+Constants.CURRENT_USER);
    }

    /**
     * 获得国际化信息
     * @param key     键
     * @param request the request
     * @return application resource
     */
    public static String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    /**
     * 获得参数Map
     * @param request the request
     * @return parameter map
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, null);
    }

    /**
     * 获取客户端IP
     * @param request the request
     * @return the host
     */
    public static String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip)) {
            InetAddress inet = null;
            try { // 根据网卡取本机配置的IP
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ip = inet != null ? inet.getHostAddress() : "";
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    private static final String HEADER_X_FORWARDED_FOR =
            "X-FORWARDED-FOR";

    public static String remoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String x;
        if ((x = request.getHeader(HEADER_X_FORWARDED_FOR)) != null) {
            remoteAddr = x;
            int idx = remoteAddr.indexOf(',');
            if (idx > -1) {
                remoteAddr = remoteAddr.substring(0, idx);
            }
        }
        return remoteAddr;
    }

    /**
     * 生成在redis中存放token的key键
     *
     * @param account 账号
     * @param userId  用户ID
     * @param suffix  token后缀
     * @return 存放token的key
     */
    public static String generateRedisTokenKey(String account, String userId, String suffix) {
        return String.format("%s%s_%s", account, userId, suffix);
    }

    /**
     * 生成在redis中存放当前登录用户信息的key键
     *
     * @param userId 当前登录用户ID
     * @return 当前登录用户对应的key
     */
    public static String generateRedisCurrentUserKey(String userId) {
        return String.format("%s%s", userId, Constants.CURRENT_USER);
    }

    /**
     * 判断是否是微软他家的浏览器
     * @param request http请求
     * @return 判断结果
     */
    public static boolean isMSBrowser(HttpServletRequest request) {
        final String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};
        String userAgent = request.getHeader("User-Agent");
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)){
                return true;
            }
        }
        return false;
    }
}
