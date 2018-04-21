package com.sofn.service.sys;


import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysRole;
import com.sofn.model.generator.SysRoleMenu;
import com.sofn.model.sys.SysRoleBean;
import com.sofn.provider.sys.SysRoleMenuProvider;
import com.sofn.provider.sys.SysRoleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qmy
 * @version 2016年10月14日 下午 3:05
 */

@Service
public class SysRoleService extends BaseService<SysRoleProvider,SysRole> {

    private SysRoleMenuProvider sysRoleMenuProvider;
    @DubboReference
    public void setSysRoleMenuProvider(SysRoleMenuProvider sysRoleMenuProvider){this.sysRoleMenuProvider=sysRoleMenuProvider;}
    public SysRoleMenuProvider getSysRoleMenuProvider(){return this.sysRoleMenuProvider;}
    /**
     * dubbo注入角色Provider
     * @param provider
     */
    @DubboReference
    public void setSysRoleProvider(SysRoleProvider provider){
        this.provider = provider;
    }

    /**
     * 新增或修改角色信息，修改该角色权限菜单
     * @param sysRole 角色对象
     * @return 新增或修改后的角色对象
     */
    @Transactional
    public SysRole saveSysRole(SysRole sysRole){
        //新增或修改角色
        SysRole sysRoleNew=provider.update(sysRole);
        return sysRoleNew;
    }
    /**
     * 新增或修改该角色权限菜单
     * @param sysRole 角色对象
     * @param menus 权限菜单集合
     * @return 新增或修改后的角色对象
     */
    @Transactional
    public SysRole saveSysRoleMenus(SysRole sysRole, List<SysRoleMenu> menus) {
        //读取原有权限菜单
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleId", sysRole.getId());
        List<SysRoleMenu> mOldAry = sysRoleMenuProvider.queryByParam(param);
        //过滤并新增的集合
        Map<SysRoleMenu, Boolean> addMapData = new HashMap<SysRoleMenu, Boolean>();
        //原权限里没有数据
        if (mOldAry == null || mOldAry.size() < 1) {
            for (SysRoleMenu m : menus) {
                if (m.getMenuId() == null || m.getMenuId().trim().equals(""))
                    continue;
                m.setRoleId(sysRole.getId());
                m.setCreateBy(sysRole.getUpdateBy());
                m.setUpdateBy(sysRole.getUpdateBy());
                sysRoleMenuProvider.update(m);
            }
        }
        //过滤并删除
        for (SysRoleMenu m : mOldAry) {
//        for(int i=0;i<mOldAry.size();i++){
//            SysRoleMenu m=mOldAry.get(i);
            boolean con = true;//遍历循环是否有找到，没找到则为删除项
            for (SysRoleMenu mNew : menus) {
                boolean notIsNull = !StringUtils.isNullEmpty(mNew.getMenuId());
                String mStrTrim = mNew.getMenuId().trim();
                //上传的菜单字段不为空，并且找到与原有权限相匹配的数据
                if (notIsNull && m.getMenuId().equals(mStrTrim)) {
                    //设置非添加项
                    addMapData.put(mNew, false);
                    //设置不删除
                    con = false;
                    //设置修改项m.getAuthority()!=mNew.getAuthority()
                    if ("Y".equals(m.getDelFlag())||(m.getAuthority()!=null&&!m.getAuthority().equals(mNew.getAuthority()))||(mNew.getAuthority()!=null&&!mNew.getAuthority().equals(m.getAuthority()))) {
                        m.setAuthority(mNew.getAuthority());
                        m.setDelFlag("N");
                        m.setUpdateBy(sysRole.getUpdateBy());
                        sysRoleMenuProvider.update(m);
                    }
                }
                //设置为添加项
                else if (notIsNull && !addMapData.containsKey(mNew)) {
                    addMapData.put(mNew, true);
                }
            }
            //遍历提交的菜单未找到，则执行删除
            if (con) {
                m.setDelFlag("Y");
                m.setUpdateBy(sysRole.getUpdateBy());
                sysRoleMenuProvider.update(m);
            }
        }
        //过滤并新增添加项
        for (Map.Entry<SysRoleMenu, Boolean> entry : addMapData.entrySet()) {
            if (entry.getValue()) {
                SysRoleMenu m = entry.getKey();
                m.setRoleId(sysRole.getId());
                m.setCreateBy(sysRole.getUpdateBy());
                sysRoleMenuProvider.update(m);
            }
        }

        return sysRole;
    }


    /**
     * 删除角色信息
     * @param idStr 角色对象
     * @return 删除的个数
     */
    @Transactional
    public String deleteSysRole(String idStr,String delUId){
        StringBuffer roleIds = new StringBuffer();
        //过滤id字符串为空
        String[] ids=idStr.split(",");
        String result=provider.getUsedRoleNameByRoleIds(ids);
        if(StringUtils.isNotEmpty(result))
            return result ;
        if(!StringUtils.isNullEmpty(idStr)){
            for (String id:ids){
                //根据ID查询角色信息
                SysRole role=provider.queryById(id.trim());
                //伪删除(修改)角色信息
                if(role==null) {
                    logger.error("com.sofn.service.sys.SysRoleService.saveSysRole(String idStr):No user role found for ID " + id);
                }else{
                    role.setDelFlag("Y");
                    role.setUpdateBy(delUId);
                    provider.update(role);
                }
            }
        }
        return  result;
    }

    /**
     * 根据ID查看角色信息
     * @param id
     * @return
     */
    public SysRole findSysRole(String id){
        return provider.queryById(id);
    }
    /**
     * 根据ID查看角色权限菜单信息
     * @param id
     * @return
     */
    public List<SysRoleMenu> findSysRoleMenu(String id){
        //读取原有权限菜单
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("roleId",id.trim());
        List<SysRoleMenu> mOldAry=sysRoleMenuProvider.queryByParam(param);
        return mOldAry;
    }
    /**
     * 根据ID查看角色权限菜单信息
     * @param id
     * @return
     */
    public List<SysRoleMenu> findSysRoleMenu(String id,String delFlag){
        //读取原有权限菜单
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("roleId",id.trim());
        param.put("delFlag",delFlag.trim());
        List<SysRoleMenu> mOldAry=sysRoleMenuProvider.queryByParam(param);
        return mOldAry;
    }
    public List<SysRole> queryByParam(Map param){

        return provider.queryByParam(param);

    }

    /**
     * 分页查询角色列表，封装扩展字段
     * @param page
     * @param keyword
     * @return
     */
    public List<SysRoleBean> getPageList(Page page, String keyword,String organizationId,String userId,String status,String notRoleId){
        return provider.getSysRoleList(page, keyword,organizationId,userId,status, notRoleId);
    }

    /**
     * 获取条件查询总数
     * @param keyword
     * @return
     */
    public int getRecordsTotal(String keyword,String organizationId,String userId, String status,String notRoleId){
        return provider.getRecordsTotal(keyword,organizationId,userId,status, notRoleId);
    }

    public SysRole getNormalRoleByAccount(String account){
        return provider.getNormalRoleByAccount(account);
    }

    public List<SysRole> getASMSRoles(){
        return provider.getASMSRoles();
    }

    public List<SysRole> getSysRoleByRoleCodes(List<String> roleCodes){
        return provider.getSysRoleByRoleCodes(roleCodes);
    }
    public List<SysRole> getIndustryormalRolesByUserId(String userId,String orgFlag){
        return provider.getIndustryormalRolesByUserId(userId,orgFlag);
    }
}
