package com.sofn.core.authorization.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.authorization.manager.TokenManager;
import com.sofn.core.config.Global;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.JwtHelper;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import static jxl.biff.FormatRecord.logger;

/**
 * 自定义拦截器，对请求进行身份验证
 *
 * @author sofn.core
 * @date 2015/7/30.
 * @see Authorization
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ResponseHolder responseHolder;

    /**
     * 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    //管理身份验证操作的对象
    private TokenManager manager;

    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权信息的无用前缀，默认为空
    private String httpHeaderPrefix = "";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    public void setManager(TokenManager manager) {
        this.manager = manager;
    }

    public void setHttpHeaderName(String httpHeaderName) {
        this.httpHeaderName = httpHeaderName;
    }

    public void setHttpHeaderPrefix(String httpHeaderPrefix) {
        this.httpHeaderPrefix = httpHeaderPrefix;
    }

    public void setUnauthorizedErrorMessage(String unauthorizedErrorMessage) {
        this.unauthorizedErrorMessage = unauthorizedErrorMessage;
    }

    public void setUnauthorizedErrorCode(int unauthorizedErrorCode) {
        this.unauthorizedErrorCode = unauthorizedErrorCode;
    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //查看方法上是否有注解
        if (method.getAnnotation(Authorization.class) == null) {
            return true;
        }

        //从header中得到token
        String token = null;
        token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        String type = request.getParameter("type");

        if (type != null && type.length() > 0) {
            type = type.toUpperCase();
        }

        if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0 && !StringUtils.isNullBlank(token)) {
            token = token.substring(httpHeaderPrefix.length());

            Claims claims = JwtHelper.parseJWT(token, Global.getConfig("sofn.api.base64Secret"));
            String redisTokenKey = "";
            // token未过期claims不为null, 否则为null, token指定的过期时间包含在token的内容里面
            if (claims != null) {
                String userId = (String) claims.get("userId");
                String account = (String) claims.get("account");
                // 解析之后的token,组装为redis中的key 帐号_pc-token  或者 帐号_app-token
                redisTokenKey = WebUtil.generateRedisTokenKey(account, userId, ApiConstants.PCTOKEN);

                // 获取登录时保存在redis中的token字符串
                String redistoken = (String) RedisUtil.get(redisTokenKey, ApiConstants.PC_IDCODE_TIMEOUT);
                // 判断redis中保存的token是否为null, 不为null即当前的token有效，否则说明token已失效，需要前端重新登录
                if (redistoken != null) {
                    // 刷新当前用户缓存失效时间
                    RedisUtil.get(WebUtil.generateRedisCurrentUserKey((String) claims.get("userId")), ApiConstants.PC_IDCODE_TIMEOUT);
                    request.setAttribute(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
                    request.setAttribute(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
                    request.setAttribute(REQUEST_CURRENT_KEY, redisTokenKey);
                    return true;
                }
            }
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        //查看方法上是否有注解
        if (method.getAnnotation(Authorization.class) != null) {    //查看方法所在的Controller是否有注解
            response.setStatus(unauthorizedErrorCode);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));

            ModelMap map = new ModelMap();
            map.put("httpCode", HttpCode.UNAUTHORIZED.value());
            map.put("msg", HttpCode.UNAUTHORIZED.msg());
            map.put("timestamp", System.currentTimeMillis());

            writer.write(JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect));
            writer.close();
            return false;
        }
        //为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
        request.setAttribute(ApiConstants.MSG, ApiMsgConstants.TOKEN_ILLEGAL);
        request.setAttribute(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
        if (responseHolder == null) {
            logger.error("system error", exception);
            responseHolder.setErrorMsg(HttpCode.FORBIDDEN);
        }

        responseHolder.clean();
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("textml; charset=utf-8");
    }


}
