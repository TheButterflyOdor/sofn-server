package com.sofn.dao.sso;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.sso.SysRole;
import com.sofn.model.sso.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/13.
 */
@MyBatisDao
public interface SSOUserRoleMapper extends BaseMapper<SysUserRole> {


}
