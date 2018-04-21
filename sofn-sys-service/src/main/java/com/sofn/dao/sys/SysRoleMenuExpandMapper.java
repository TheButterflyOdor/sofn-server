package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysRoleMenuExpandMapper extends BaseExpandMapper {
    /** 条件查询所有 */
    public List<SysRoleMenu> queryByParam(Map<String, Object> params);

}