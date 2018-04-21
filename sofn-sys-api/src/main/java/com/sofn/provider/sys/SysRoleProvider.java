package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysRole;
import com.sofn.model.sys.SysRoleBean;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/20.
 */
public interface SysRoleProvider extends BaseProvider<SysRole> {
    int getRecordsTotal(String keyword,String organizationId,String userId,String status,String notRoleId);
    List<SysRoleBean> getSysRoleList(Page page, String keyword,String organizationId,String userId,String status,String notRoleId);
    List<SysRole> queryByParam(Map<String,Object> param);

    //以下为业务提供的接口-------------------------------------------------

    /**
     * 查询用户同部门下的角色列表
     * @param userId
     * @return
     */
    List<SysRole> queryByUserId(String userId);
    /**
     *获取被使用过的角色名
     * @param roleId 角色ID
     * @return 被使用过的角色名
     */
    String getUsedRoleNameByRoleIds(String[] roleId);

    /**
     * 通过当前用户账号获取对应系统的普通用户基础角色
     * @param account
     * @return
     */
    public SysRole getNormalRoleByAccount(String account);

    /**
     * 获取监管系统能够访问的检测和执法系统角色
     * @return
     */
    public List<SysRole> getASMSRoles();

    public List<SysRole> getSysRoleByRoleCodes(List<String> roleCodes);

    /**
     * 获取用户所在机构的所有角色
     * @param userId
     * @param status
     * @return
     */
    public List<SysRole> getOrgRoleByUserId(String status,String userId);

    /**
     * 通过用户Id获取其所有可用角色
     * @param userId
     * @return
     */
    public List<SysRole> getSysRolesByUserId(String userId);

    /**
     *通过用户ID获取特定机构用户的行业角色信息
     * @param userId 用户ID
     * @param orgFlag 系统标识 ，监管机构：ASMS
     * @return
     */
    public List<SysRole> getIndustryormalRolesByUserId(String userId,String orgFlag);
}
