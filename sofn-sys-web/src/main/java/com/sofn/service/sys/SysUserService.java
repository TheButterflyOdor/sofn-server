package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.persistence.Pager;
import com.sofn.core.support.Assert;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.*;
import com.sofn.model.generator.SysOrganization;
import com.sofn.model.generator.SysUser;
import com.sofn.model.generator.SysUserRole;
import com.sofn.model.sys.SysUserBean;
import com.sofn.model.sys.SysUserDto;
import com.sofn.provider.sys.SysOrganizationProvider;
import com.sofn.provider.sys.SysRoleProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.sys.SysUserRoleProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-17.
 * Time: 22:51.
 * Description:
 */
@Service
@Transactional
public class SysUserService extends BaseService<SysUserProvider,SysUser> {
    private SysRoleProvider sysRoleProvider;
    private SysUserRoleProvider sysUserRoleProvider;
    private SysOrganizationProvider sysOrganizationProvider;
    @DubboReference
    public void setSysRoleProvider(SysRoleProvider sysRoleProvider){this.sysRoleProvider=sysRoleProvider;}
    @DubboReference
    public void setSysUserRoleProvider(SysUserRoleProvider sysUserRoleProvider){this.sysUserRoleProvider=sysUserRoleProvider;}
    public SysUserRoleProvider getSysUserRoleProvider(){return this.sysUserRoleProvider;}
    @DubboReference
    public void setSysUserProvider(SysUserProvider provider){
        this.provider = provider;
    }
    @DubboReference
    public void setSysOrganizationProvider(SysOrganizationProvider sysOrganizationProvider){
        this.sysOrganizationProvider = sysOrganizationProvider;
    }

    /**
     * 新增或修改用户信息，修改该用户权限角色
     * @param sysUser 用户对象
     * @param roleStr 角色ID串
     * @return 新增或修改后的角色对象
     */
    @Transactional
    public SysUser saveSysUser(SysUser sysUser, String roleStr){
        //如果是新增，则生成账号和密码
        if(sysUser.getId()==null||sysUser.getId().trim().equals("")) {
            //查询登录名是否重复，是则随机4位数
            String account = StringUtils.getPingYin(sysUser.getUserName());
            //判断是否有所属机构，如果有，则取机构别名和区域代码为账号前缀
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("account", account);
            boolean isExist = provider.checkUserByAccount(param) == 0?false:true ;
            //生成用户名，判断是否重复 重复则重新生成
            String accountRandom = "";
            while(isExist){
                accountRandom = String.valueOf((int) (Math.random() * 10000));
                String accountTemp = account+accountRandom ;
                param.put("account", accountTemp);
                isExist = provider.checkUserByAccount(param) == 0?false:true ;
            }
            //自动设置登录用户名和密码
            sysUser.setAccount(account+accountRandom);
            if(StringUtils.isEmpty(sysUser.getReservedField2()))
                sysUser.setReservedField2(account+accountRandom);
            sysUser.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(account+accountRandom)));
            //设置用户类型 管理员统一由生成机构时创建
            sysUser.setUserType(UserEnum.NORMAL.name());
        }
        //如果是修改用户，则查询数据库进行替换
        else{
            SysUser oldUser=provider.queryById(sysUser.getId());
            sysUser.setAccount(oldUser.getAccount());
            sysUser.setPassword(oldUser.getPassword());
            sysUser.setCreateBy(oldUser.getCreateBy());
            sysUser.setCreateTime(oldUser.getCreateTime());
            sysUser.setReservedField1(oldUser.getReservedField1());
            sysUser.setReservedField2(oldUser.getReservedField2());
            sysUser.setUserType(oldUser.getUserType());
        }
        //获取提交的角色集合
        String [] roles= StringUtils.isNullEmpty(roleStr)?new String[0]:roleStr.split(",");
        //设置用户默认角色
        if(roles.length>0){
            sysUser.setRoleId(roles[0]);
        }
       //新增或修改用户
        SysUser sysUserNew=provider.update(sysUser);
        //考虑服务器运行效率，新增和修改处理分开，可避免一次queryList
        //新增角色
        if(StringUtils.isNullEmpty(sysUser.getId())){
            //新增权限角色
            if(roles.length>0){
                for(String rStr:roles){
                    //如果提交的某个权限角色为空，则忽略
                    if(StringUtils.isNullEmpty(rStr))
                        continue;
                    SysUserRole r=new SysUserRole();
                    r.setRoleId(rStr);
                    r.setUserId(sysUserNew.getId());
                    sysUserRoleProvider.update(r);
                }
            }
        }
        //修改
        else{
            //读取原有权限角色
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("userId",sysUser.getId());
            List<SysUserRole> rOldAry=sysUserRoleProvider.queryByParam(param);
            if(rOldAry==null||rOldAry.size()==0){
                //新增权限角色
                if(roles.length>0){
                    for(String rStr:roles){
                        //如果提交的某个权限角色为空，则忽略
                        if(StringUtils.isNullEmpty(rStr))
                            continue;
                        SysUserRole r=new SysUserRole();
                        r.setRoleId(rStr);
                        r.setUserId(sysUserNew.getId());
                        sysUserRoleProvider.update(r);
                    }
                }
            }else {
                //过滤并新增的集合
                Map<String, Boolean> addMapData = new HashMap<String, Boolean>();
                //过滤并删除
                for (SysUserRole r : rOldAry) {
                    boolean con = true;//遍历循环是否有找到，没找到则为删除项
                    for (String rStr : roles) {
                        String rStrTrim = rStr.trim();
                        boolean notIsNull = !StringUtils.isNullEmpty(rStrTrim);
                        //上传的角色字段不为空，并且找到与原有权限相匹配的数据
                        if (notIsNull && r.getRoleId().equals(rStrTrim)) {
                            //设置非添加项
                            addMapData.put(rStrTrim, false);
                            //设置不删除
                            con = false;
                            //设置修改项
                            if (r.getDelFlag() != null && r.getDelFlag().equals("Y")) {
                                r.setDelFlag("N");
                                sysUserRoleProvider.update(r);
                            }
                        }
                        //设置为添加项
                        else if (notIsNull && !addMapData.containsKey(rStrTrim)) {
                            addMapData.put(rStrTrim, true);
                        }
                    }
                    //遍历提交的角色未找到，则执行删除
                    if (con) {
                        r.setDelFlag("Y");
                        sysUserRoleProvider.update(r);
                    }
                }
                //过滤并新增添加项
                for (Map.Entry<String, Boolean> entry : addMapData.entrySet()) {
                    if (entry.getValue()) {
                        SysUserRole m = new SysUserRole();
                        m.setRoleId(entry.getKey());
                        m.setUserId(sysUserNew.getId());
                        sysUserRoleProvider.update(m);
                    }
                }
            }
        }
        return sysUserNew;
    }

    /**
     * 重置密碼
     * @return 新增或修改后的角色对象
     */
    @Transactional
    public boolean resetPassword(String account,String token){
        return provider.resetPwd( account, token);
    }
   /* *//**
     * 新增机构并新增admin用户信息、新增机构角色、新增机构角色权限
     * @param oranization 机构信息
     * @return 新增或修改后的角色对象
     *//*
    @Transactional
    public SysUser saveSysOranizationUser(SysOrganization oranization){
        //定义角色对象
        SysRole role;
        //定义用户对象
        SysUser sysUser;
        //查询是否存在机构信息
        //由于缺少接口 假设不存在
        SysOrganization org=sysOrganizationProvider.selectByName(oranization.getOrgName());
        //不存在则添加机构信息
        if(org==null) {
            oranization = sysOrganizationProvider.update(oranization);
        }else{
            oranization=org;
        }
        //查询角色信息
        Map<String ,Object> param=new HashMap<String,Object>();
//        param.put("roleName",oranization.getOrgName()+"_admin");
        param.put("roleName","系统管理员");
        param.put("role_code","SYSADMIN");
        List<SysRole> roles=sysRoleProvider.queryByParam(param);
        //-- 不存在则添加角色信息并添加权限菜单
        //指定固定code的角色
        role=roles.get(0);
        //查询用户信息
        param=new HashMap<String,Object>();
        param.put("userName",oranization.getOrgName()+"_admin");
        List<SysUser> users=provider.queryByParam(param);
        if(users==null||users.size()<1){
            sysUser=new SysUser();
            sysUser.setDelFlag("N");
            sysUser.setStatus("1");
            sysUser.setUserName(oranization.getOrgName()+"_admin");
            sysUser.setOrganizationId(oranization.getId());
            sysUser.setAccount(oranization.getOrgName()+"_admin");//待修改
            sysUser.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA("00000000")));
            sysUser.setRemark("添加“"+oranization.getOrgName()+"”机构时生成的机构管理员角色账号");
            sysUser.setRoleId(role.getId());
            sysUser.setCreateBy(oranization.getCreateBy());
            sysUser.setUserType(UserEnum.SYSADMIN.name());
            sysUser=provider.update(sysUser);
            //配置默认角色关联
            SysUserRole ur=new SysUserRole();
            ur.setDelFlag("N");
            ur.setRoleId(role.getId());
            ur.setUserId(sysUser.getId());
            ur.setCreateBy(oranization.getCreateBy());
            ur.setUpdateBy(oranization.getCreateBy());
            ur.setRemark("添加“"+oranization.getOrgName()+"”机构时生成的用户角色关联");
            sysUserRoleProvider.update(ur);
        }else{
            sysUser=users.get(0);
        }
        //返回管理员用户对象
        return sysUser;
    }*/
    /**
     * 删除用户信息
     * @param idStr 用户对象
     * @return 删除的个数
     */
    @Transactional
    public int deleteSysUser(String idStr,String updateUId){
        int count=0;
        //过滤id字符串为空
        if(!StringUtils.isNullEmpty(idStr)){
            String[]ids=idStr.split(",");
            for (String id:ids){
                //根据ID查询用户信息
                SysUser user=provider.queryById(id.trim());
                //伪删除(修改)用户信息
                if(user==null) {
                    logger.error("com.sofn.service.sys.SysUserService.saveSysUser(String idStr):No user role found for ID " + id);
                }else{
                    user.setDelFlag("Y");
                    user.setUpdateBy(updateUId);
                    provider.update(user);
                    count++;
                }
            }
        }
        return  count;
    }

    /**
     * 根据ID查看用户的角色权限信息
     * @param id
     * @return
     */
    public List<SysUserRole> findSysUserRole(String id){
        //读取原有权限菜单
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("userId",id.trim());
        List<SysUserRole> mOldAry=sysUserRoleProvider.queryByParam(param);
        return mOldAry;
    }
    /**
     * 根据条件查询用户集合
     * @param param 参数
     * @return
     */
    public List<SysUser> queryByParam(Map param){
        //读取原有权限菜单
        List<SysUser> mOldAry=provider.queryByParam(param);
        return mOldAry;
    }

    /**
     * 按条件分页查询用户信息
     * @param param 查询条件
     * @param pageNum 页号
     * @param pageSize 每页的记录数
     * @return
     */
    public PageInfo<SysUser> queryByParamWithPaging(Map<String, Object> param, int pageNum, int pageSize) {
        return provider.queryByParamWithPaging(param, pageNum, pageSize);
    }
    /** 根据Id查询 */
    @SuppressWarnings("unchecked")
    public SysUserBean queryUserBeanById(String id) {
        Assert.isNotBlank(id, "ID");
        SysUserBean bean=provider.queryUserBeanById(id);
        return bean;
    }
    /**
     * 根据ID查看用户的角色权限信息
     * @param id
     * @return
     */
    public List<SysUserRole> findSysUserRole(String id,String delFlag){
        //读取原有权限菜单
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("userId",id.trim());
        param.put("delFlag",delFlag.trim());
        List<SysUserRole> mOldAry=sysUserRoleProvider.queryByParam(param);
        return mOldAry;
    }

    public int getRecordsTotal(String keyword,String status ,String userId, String userType){
        return provider.getRecordsTotalNew(keyword,status,userId,userType);
    }
    public List<SysUserBean> getPageList(Page page, String keyword, String status,String userId,String userType){
        return provider.getSysUserListNew(page, keyword,status,userId,userType);
    }


    /**************************************以下是分页样例*****************************************/

    public List<SysUserDto> getList(Pager pager, String keyword){
        return provider.getSysUserList(pager, keyword);
    }


    @Transactional
    public void saveUser(){
        //定义角色对象
        /*SysRole role=new SysRole();
        role.setDelFlag("N");
        role.setOrganizationId(null);
        role.setDescribe("添加机构时生成的机构管理员角色");
        role.setRoleName("_admin");
        role.setStatus("09674468d567471a88b1bf9307272883");//状态：正常
        role.setCreateBy(null);
        role=sysRoleProvider.update(role);*/
        //定义用户对象
        SysUser sysUser=new SysUser();
//        sysUser.setId(StringUtils.getUUIDString());
        sysUser.setDelFlag("N");
        sysUser.setStatus("1");
        sysUser.setUserName("_admin");
        sysUser.setOrganizationId(StringUtils.getUUIDString());
        sysUser.setAccount(Math.random() + "_admin");//待修改
        sysUser.setPassword("admin123456");
        sysUser.setRemark("添加机构时生成的机构管理员角色账号");
        sysUser.setRoleId(null);
        sysUser.setCreateBy(null);
        sysUser=provider.update(sysUser);

        SysUser sysUser1=new SysUser();
        sysUser1.setId(null);
        sysUser1.setDelFlag("N");
        sysUser1.setStatus("1");
        sysUser1.setUserName("_admin");
        sysUser1.setOrganizationId(StringUtils.getUUIDString());
        sysUser1.setAccount(Math.random() + "_admin");//待修改
        sysUser1.setPassword("admin123456");
        sysUser1.setRemark("添加机构时生成的机构管理员角色账号");
        sysUser1.setRoleId(null);
        sysUser1.setCreateBy(null);
        sysUser1=provider.update(sysUser1);
    }

    /**
     * 预创建用户
     * @param userAry
     * @param account
     * @return
     */
    public String createUser(List<SysUser> userAry,String account){
        String accountRandom = String.valueOf((int) (Math.random() * 10000));
        for (int i = 0; i < userAry.size(); i++) {
            if (userAry.get(i).getAccount().equals(account + accountRandom)) {
                //生成随机数 并初始化循环
                accountRandom = String.valueOf((int) (Math.random() * 10000));
                i = -1;
            }
        }
        return account+accountRandom ;
    }
    public List<Map<String,Object>> getRoleByUserId(String userId){
        return sysUserRoleProvider.getRoleByUserId(userId);
    }
    public Result addSubjUser(String token, UserEnum userEnum, SysUser user, List<String> ites, boolean choiceAbattoir, OrgFormEnum orgFormEnum){
        return provider.addSubjUser(token,userEnum, user, ites, choiceAbattoir,orgFormEnum);
    }
    public SysUser saveSysOranizationUser(SysOrganization oranization, String token){
        return provider.saveSysOranizationUser(oranization,token,null);
    }
}
