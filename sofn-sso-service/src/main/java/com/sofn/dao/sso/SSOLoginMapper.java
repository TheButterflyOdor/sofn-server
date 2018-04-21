package com.sofn.dao.sso;

import com.github.pagehelper.Page;
import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.core.constant.CurrentUser;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-09-27.
 * Time: 09:46.
 * Description:
 */
@MyBatisDao
public interface SSOLoginMapper extends BaseMapper<CurrentUser> {
    /** 条件分页查询 */
    Page<String> query(Map<String, Object> params);
    Integer queryByUserModel(CurrentUser userModel);
    Integer register(CurrentUser userModel);
    Integer updatePassword(CurrentUser currentUser);
    CurrentUser login(@Param("account")String account,@Param("password") String password);
    Integer delUser(CurrentUser userModel);
}
