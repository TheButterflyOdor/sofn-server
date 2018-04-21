package com.sofn.provider.sso;


import com.sofn.core.base.BaseProvider;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.exception.RegisterException;

import java.util.Map;

/**
 * Created by dong4j on 2016年09月13日  上午 10:48:34
 * Description: SSO帮助类接口,用于所有子系统的登录服务,各个子系统的退出服务由子系统本生负责
 */
public interface SSOLoginProvider extends BaseProvider<CurrentUser> {
    /**
     * Login map.
     *
     * @param account  the account
     * @param password the password
     * @return the map
     */
    Map<String, Object> login(String account, String password);

    /**
     * LoginApp
     * @param account
     * @param password
     * @return
     */
    Map<String, Object> loginApp(String account, String password);


    /**
     * Login out.
     *
     * @param token the token
     * @param type  the type
     */
    void logout(String token, String type);


    /**
     * Register user model.
     * 用户注册
     *
     * @param userModel the user model
     * @return the user model CurrentUser 必须有的参数 id (64位) account 登陆账号 password 密码(分加密后的密码) userName 姓名 deptId 部门编号 roleId 默认角色编号 postId
     * 职务编号 phone 电话 email 邮箱 status 状态 如果账号重复 则抛出异常
     * @throws RegisterException the register exception
     */
    Integer register(CurrentUser userModel) throws RegisterException;


    /**
     * Repeat check boolean.
     * 账号重复检查
     * 如果重复 返回 false
     *
     * @param user the user
     * @return the boolean
     */
    Boolean repeatCheck(CurrentUser user);

    /**
     * Update password boolean.
     * 修改密码
     * @param token       the token
     * @param oldPassword the old password
     * @param newPassword the new password
     * @return the boolean
     */
    void updatePassword(String token, String oldPassword, String newPassword);

    Integer delUser(String account);
}
