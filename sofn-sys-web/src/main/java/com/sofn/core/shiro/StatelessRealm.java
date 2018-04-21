package com.sofn.core.shiro;

import com.sofn.core.base.RedisService;
import com.sofn.core.config.Global;
import com.sofn.core.config.Resources;
import com.sofn.core.exception.LoginException;
import com.sofn.core.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Code.Ai on 16/8/10.
 * Description:
 */
public class StatelessRealm extends AuthorizingRealm {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisService redisService;

    @Override
    public boolean supports(AuthenticationToken token) {
        // 仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //<editor-fold desc="Description">
        //        String uniqueToken = (String) principals.getPrimaryPrincipal();
//        logger.error("uniqueToken = {}", uniqueToken);
//        // 解析 token
//        Claims claims = JwtHelper.parseJWT(uniqueToken, Global.getConfig("sofn.api.base64Secret"));
//        // 从token获取userId
//        String userId = (String) claims.get("userId");
//        //通过customerId查询角色
////        List<?>                 roles             = sysRoleService.getRoleByUserId(userId);
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        //将所有角色放入权限验证器中
////        if (roles != null && roles.size() != 0) {
////            for (Object role : roles) {
////                authorizationInfo.addRole(((SysRoleBean) role).getRoleName());
////            }
////        }
//        authorizationInfo.addRole("admin");
//        return authorizationInfo;
        //</editor-fold>

        //<editor-fold desc="todo sso系统将用户权限存入redis,此处从redis中获取权限,并且托管给shiro管理">
        SimpleAuthorizationInfo info    = new SimpleAuthorizationInfo();
        String                  userId  = "1";
        //SysUser                 sysUser = sysUserService.queryById(userId);
        //if (sysUser.getUserType() != 1) {
        //    userId = null;
        //}
        //todo 根据用户 id 查询权限,托管给 shiro 管理 (一到 sso 模块中)
        //List<String> list = sysAuthorizeService.queryPermissionByUserId(userId);
        //for (String permission : list) {
        //    if (StringUtils.isNotBlank(permission)) {
        //        // 添加基于Permission的权限信息
        //        info.addStringPermission(permission);
        //    }
        //}
        // 添加用户权限
        info.addStringPermission("user");
        return info;
        //</editor-fold>
    }

    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        if (uniqueToken.equals("app")) {
//            return new SimpleAuthenticationInfo(uniqueToken, "app_token", getName());
//        } else {
//            return new SimpleAuthenticationInfo(uniqueToken, "pc_token", getName());
//        }
        StatelessToken statelessToken = (StatelessToken) token;
        String         uniqueToken    = statelessToken.getUniqueToken();
        //logger.error("token = " + uniqueToken);
        if(uniqueToken == null){
            throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
        }
        // 解析 token
        Claims claims = JwtHelper.parseJWT(uniqueToken, Global.getConfig("sofn.api.base64Secret"));
        if (claims != null) {
            //logger.error("userId = {}", claims.get("userId"));
            //logger.error("userName = {}", claims.get("userName"));
            //logger.error("role = {}", claims.get("role"));
            String account = (String) claims.get("account");
            String userId = (String) claims.get("userId");
            String role    = (String) claims.get("role");
            String userName = (String) token.getPrincipal();
            //logger.error("token.getPrincipal() = {}", token.getPrincipal());
            String password = (String) token.getCredentials();
            //logger.error("token.getCredentials() = {}", token.getCredentials());
            // 如果 redis 没有此 token 登录失败
            return new SimpleAuthenticationInfo(uniqueToken,
                    redisService.getAccessToken(account + userId), getName());
        }
        throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
    }
}
