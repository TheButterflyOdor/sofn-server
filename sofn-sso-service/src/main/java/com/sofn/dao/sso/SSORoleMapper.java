package com.sofn.dao.sso;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.sso.SysMenu;
import com.sofn.model.sso.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/13.
 */
@MyBatisDao
public interface SSORoleMapper extends BaseMapper<SysRole> {

    /** 条件查询所有 */
    List<SysRole> queryByParam(@Param("param")Map<String, Object> param);

}
