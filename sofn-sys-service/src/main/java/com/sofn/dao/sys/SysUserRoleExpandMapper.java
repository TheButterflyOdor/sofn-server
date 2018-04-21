package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysUserRoleExpandMapper extends BaseExpandMapper {
    /** 条件查询所有 */
    public List<SysUserRole> queryByParam(Map<String, Object> params);

    /**
     * 通过用户ID获取角色信息
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getRoleByUserId(@Param("userId") String userId);

}