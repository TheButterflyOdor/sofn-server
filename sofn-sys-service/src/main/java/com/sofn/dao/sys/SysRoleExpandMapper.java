package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysRole;
import com.sofn.model.sys.SysRoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface SysRoleExpandMapper extends BaseExpandMapper {
    int getRecordsTotal(@Param("keyword") String keyword,@Param("organizationId") String organizationId,@Param("userId") String userId,@Param("status") String status,@Param("notRoleId") String notRoleId);
    /** 条件查询所有 */
    List<SysRole> queryByParam(@Param("param")Map<String, Object> param);
    //分页查询
    List<SysRoleBean> getSysRoleListByPage(@Param("page") Page page, @Param("keyword") String keyword,@Param("organizationId") String organizationId,@Param("userId") String userId,@Param("status") String status,@Param("notRoleId") String notRoleId);

    //根据机构id删除角色信息
    void deleteByOrganization(@Param("organizationId") String organizationId,@Param("delFlag") String delFlag);

    public String getUsedRoleNameByRoleIds(String[] roleId);

    public List<SysRole> getSysRoleByRoleCodes(List<String> roleCodes);

    public SysRole getNormalRoleByAccount(@Param("account") String account);

    /*
    获取监管系统可见的检测和执法系统角色
     */
    public List<SysRole> getASMSRoles();

    List<SysRole> getOrgRoleByUserId(@Param("status") String status,@Param("userId") String userId);

    public List<SysRole> getSysRolesByUserId(@Param("userId") String userId);

    public List<SysRole> getIndustryormalRolesByUserId(@Param("userId") String userId,@Param("orgFlag") String orgFlag);
}