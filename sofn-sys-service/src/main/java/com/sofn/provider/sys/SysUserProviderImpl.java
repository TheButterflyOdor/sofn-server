package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.persistence.Page;
import com.sofn.core.persistence.Pager;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.*;
import com.sofn.dao.generator.SysOrganizationMapper;
import com.sofn.dao.generator.SysRoleMapper;
import com.sofn.dao.generator.SysUserMapper;
import com.sofn.dao.generator.SysUserRoleMapper;
import com.sofn.dao.sys.SysOrganizationExpandMapper;
import com.sofn.dao.sys.SysRoleExpandMapper;
import com.sofn.dao.sys.SysUserExpandMapper;
import com.sofn.model.generator.SysOrganization;
import com.sofn.model.generator.SysRole;
import com.sofn.model.generator.SysUser;
import com.sofn.model.generator.SysUserRole;
import com.sofn.model.sys.SysUserBean;
import com.sofn.model.sys.SysUserDto;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by: dong4j.
 * Date: 2016-10-17.
 * Time: 23:02.
 * Description:
 */
@DubboService(interfaceClass = SysUserProvider.class)
public class SysUserProviderImpl extends BaseProviderImpl<SysUser> implements SysUserProvider {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserExpandMapper sysUserExpandMapper;
    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;
    @Autowired
    private SysOrganizationExpandMapper sysOrganizationExpandMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleExpandMapper sysRoleExpandMapper;


    @Override
    public int getRecordsTotal(String keyword,String status,String organizationId  ) {
        return sysUserExpandMapper.getRecordsTotal(keyword,status,organizationId);
    }
    @Override
    public int getRecordsTotalNew(String keyword,String status,String userId,String userType) {
        return sysUserExpandMapper.getRecordsTotalNew(keyword,status,userId,userType);
    }

    @Override
    public List<SysUserDto> getSysUserList(Pager pager, String keyword) {
        return sysUserExpandMapper.getSysUserList(pager, keyword);
    }

    @Override
    public List<SysUserBean> getSysUserList(Page page, String keyword, String status,String organizationId ) {
        return sysUserExpandMapper.getSysUserListByPage(page, keyword,status,organizationId);
    }
    @Override
    public List<SysUserBean> getSysUserListNew(Page page, String keyword, String status,String userId,String userType) {
        System.out.println("查询分页--------------------------------------------------");
        return sysUserExpandMapper.getSysUserListByPageNew(page, keyword,status,userId,userType);
    }
    @Override
    public List<SysUserBean> getOrgUserRecordsByUserId(Page page, String status,String userId) {
        return sysUserExpandMapper.getOrgUserRecordsByUserId(page, status,userId);
    }
    @Override
    public int getOrgUserRecordsCountByUserId(String status,String userId) {
        return sysUserExpandMapper.getOrgUserRecordsCountByUserId(status,userId);
    }

    @Override
    public boolean editPasswordByAccount(String account, String password) {
        String newPwd=SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password));
        int flag = sysUserExpandMapper.editPasswordByAccount(account,newPwd);
        if(flag==1)
            return true ;
        else
            return false ;
    }

    @Override
    public List<SysUser> queryByParam(Map<String, Object> param) {
        return sysUserExpandMapper.queryByParam(param);
    }

    @Override
    public PageInfo<SysUser> queryByParamWithPaging(Map<String, Object> param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> userList = sysUserExpandMapper.queryByParam(param);
        return new PageInfo<>(userList);
    }

    public SysUserBean queryUserBeanById(String id){
        return sysUserExpandMapper.queryUserBeanById(id);
    }
    //以下对业务平台提供接口---------------------------------------------------------
    @Override
    public List<SysUser> getDeptUser(String token) {
        CurrentUser u= WebUtil.getCurrentUser(token);
        if(u==null||u.getDeptId()==null)
            return  null;
        Map<String,Object> param=new HashMap();
        param.put("deptId",u.getDeptId());
        List<SysUser> list=this.queryByParam(param);
        return sysUserExpandMapper.queryByParam(param);
    }
    @Override
    public List<SysUser> getRoleUser(String roleId) {
        if(roleId==null||roleId.trim().equals(""))
            return  null;
        List<SysUser> list=sysUserExpandMapper.queryByRoleId(roleId);
        return list;
    }
    @Override
    public boolean resetPwd(String account,String token) {
        if (account == null || account.trim().equals(""))
            return false;
        CurrentUser u= WebUtil.getCurrentUser(token);
        if(u==null||u.getAccount()==null)
            return  false;
        Map<String,Object> param=new HashMap();
        SysUser user = sysUserExpandMapper.queryUserByAccount(account);
        if(user==null)
            return false;
        if(user.getReservedField2()==null||user.getReservedField2().trim().equals(""))
            user.setReservedField2("00000000");
        user.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(user.getReservedField2())));
        user.setUpdateBy(u.getId());
        sysUserMapper.updateByPrimaryKey(user);
        //如果是重置自己的密码 则清空登陆缓存
        if(u.getId().equals(user.getId())) {
            RedisUtil.del(WebUtil.generateRedisTokenKey(account, u.getId(), ApiConstants.PCTOKEN));
        }
        return true;
    }


    @Override
    public boolean changePwd(String account,String oldPwd,String newPwd,String token) {
        if (account == null || account.trim().equals(""))
            return false;
        //密码加密
        oldPwd=SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(oldPwd));
        newPwd=SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(newPwd));
        CurrentUser u= WebUtil.getCurrentUser(token);
        if(u==null||u.getAccount()==null)
            return  false;
        if(!u.getPassword().equals(oldPwd)){
            return false;
        }
        SysUser user=super.queryById(u.getId());
        user.setPassword(newPwd);
        user.setUpdateBy(u.getId());
        RedisUtil.del(WebUtil.generateRedisTokenKey(account, u.getId(), ApiConstants.PCTOKEN));
        sysUserMapper.updateByPrimaryKey(user);
        return true;
    }

    @Override
    public boolean changePwdByAccount(String account,String newPwd) {
        if (account == null || account.trim().equals(""))
            return false;
        //密码加密
        newPwd=SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(newPwd));
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("account",account);
        map.put("organizationId","");
        List<SysUser> l=queryByParam(map);
        if(l==null||l.size()==0)
            return false;
        SysUser user=l.get(0);
        user.setPassword(newPwd);
        user.setUpdateBy(user.getId());
        RedisUtil.del(WebUtil.generateRedisTokenKey(account, user.getId(), ApiConstants.PCTOKEN));
        sysUserMapper.updateByPrimaryKey(user);
        return true;
    }
    @Override
    public SysUser addUser(SysUserBean user,String token,UserEnum userEnum){
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if(u==null)
            return null;
        user.setOrganizationId(u.getOrganizationId());
        String roleStr=user.getRoleStr();
        //查询登录名是否重复，是则随机4位数
        String account= StringUtils.getPingYin(user.getUserName());
        String accountRandom="";
        Map<String ,Object> param=new HashMap<String,Object>();
        param.put("account",account);
        List<SysUser> userAry=this.queryByParam(param);

        //生成用户名，判断是否重复 重复则重新生成
        for(int i=0;i<userAry.size();i++){
            if(userAry.get(i).getAccount().equals(account+accountRandom)){
                //生成随机数 并初始化循环
                accountRandom=String.valueOf((int)(Math.random()*10000));
                i=-1;
            }
        }
        //自动设置登录用户名和密码
        user.setAccount(account+accountRandom);
        user.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(user.getAccount())));
        user.setStatus("1");
        //配置修改时间、修改人
        user.setCreateBy(u.getId());
        user.setUpdateBy(u.getId());
        //设置用户类型
        user.setUserType(userEnum.name());
        //生成密码SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password))
        SysUser result;
        if(userEnum.equals(UserEnum.SUBJADMIN)||userEnum.equals(UserEnum.SUBJNORMAL)){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("roleCode","SUBJ");
            List<SysRole> roles=sysRoleExpandMapper.queryByParam(map);
            //强制要求数据库必须有企业角色
            if(roles==null||roles.size()==0){
                return null;
            }
            user.setRoleId(roles.get(0).getId());
            //保存用户
            result=super.update(user);
            //配置用户角色
            SysUserRole sur=new SysUserRole();
            sur.setRoleId(roles.get(0).getId());
            sur.setUserId(user.getId());
            sur.setUpdateTime(new Date());
            sur.setId(StringUtils.getUUIDString());
            sur.setCreateTime(new Date());
            sur.setDelFlag("N");
            sysUserRoleMapper.insert(sur);
        }else{
            result=super.update(user);
        }

//        SUBJADMIN,SUBJNORMAL,
        return  result;
    }

    @Override
    public SysUser saveSysOranizationUser(SysOrganization oranization, String token,String industrys){
        long startTime = new Date().getTime();
        if(oranization==null||token==null)
            return null;
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if(u==null)
            return null;
        //设置机构信息创建人和修改人
        oranization.setCreateBy(u.getId());
        oranization.setCreateTime(new Date());
        oranization.setUpdateBy(u.getId());
        oranization.setUpdateTime(new Date());
        //根据机构类型封装默认账号
        String orgGjz="";
        if(StringUtils.isBlank(oranization.getOrgType()))
            return null ;
        switch (oranization.getOrgType()) {
            case "ASMS":
                orgGjz = "JG";
                break;
            case "ALES":
                orgGjz = "ZF";
                break;
            case "ADS":
                orgGjz = "JC";
                break;
        }
        String orgLevel = oranization.getOrgLevel();
        if(StringUtils.isBlank(orgLevel))
            return null ;

        //角色编码的命名规则：系统简称+"-0"+组织机构等级+"-ADMIN" ，此处代码待角色授权后可打开
        //获取用户管理员应该拥有的行业类型角色 01-JG-INDUSTRY-ADMIN-ROLE、02-JG-INDUSTRY-ADMIN-ROLE、03-JG-INDUSTRY-ADMIN-ROLE
        String roleCode =  orgGjz+"-0"+orgLevel+"-ADMIN";

        List<String> industryTypeSqlIn = new ArrayList<String>();
        industryTypeSqlIn.add(roleCode);
        if(StringUtils.isNotEmpty(industrys)){
            String[] industryArr = industrys.split(",");
            for(String industry : industryArr ){
                String is = industry+"-JG-INDUSTRY-ADMIN-ROLE" ;
                industryTypeSqlIn.add(is);
            }
        }
        List<SysRole> roles = sysRoleExpandMapper.getSysRoleByRoleCodes(industryTypeSqlIn);
        //指定固定code的角色
        if(roles == null || (roles.size() < 2 && orgGjz.equals("JG")) || (roles.size() == 0 && !orgGjz.equals("JG"))){
            return null ;
        }
        //定义角色对象
        SysRole role = null;
        for(SysRole r : roles){
            if(roleCode.equals(r.getRoleCode())){
                role = r ;
                break;
            }
        }
        if (role == null)
            return null ;
        //定义用户对象
        SysUser sysUser = null;
        oranization.setId(StringUtils.getUUIDString());
        oranization.setDelFlag("N");
        sysOrganizationMapper.insert(oranization);
        //查询用户信息
        sysUser=new SysUser();
        sysUser.setDelFlag("N");
        sysUser.setStatus("1");
        sysUser.setUserName(oranization.getOrgName()+"_admin");
        sysUser.setOrganizationId(oranization.getId());
        sysUser.setAccount(orgGjz+"-"+oranization.getRegionId()+"-");
        sysUser.setRemark("添加“"+oranization.getOrgName()+"”机构时生成的机构管理员角色账号");
        sysUser.setRoleId(role.getId());
        sysUser.setCreateBy(oranization.getCreateBy());
        sysUser.setUserType(UserEnum.SYSADMIN.name());
        //用户名去重
        Map<String, Object> paramC = new HashMap<String, Object>();
        paramC.put("accountLike", sysUser.getAccount());
        String maxAreaByAccount = this.getMaxAreaByAccount(paramC);
        String accountRandom = getAccountRandom(maxAreaByAccount);
        //自动设置登录用户名和密码
        if(!"".equals(accountRandom))
            sysUser.setAccount(sysUser.getAccount() + accountRandom);
        sysUser.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(sysUser.getAccount())));
        sysUser.setReservedField2(sysUser.getAccount());
        //end 用户名去重
        //保存用户
        sysUser=super.update(sysUser);
        for(SysRole s : roles){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(StringUtils.getUUIDString());
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(s.getId());
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUpdateTime(new Date());
            sysUserRole.setDelFlag("N");
            sysUserRole.setRemark("添加“"+oranization.getOrgName()+"”机构时生成的用户角色关联");
            sysUserRoleMapper.insert(sysUserRole);
            //为用户新增角色
        }
        //返回管理员用户对象
        long endTime = new Date().getTime();
        logger.info(oranization.getOrgName()+"消耗时间:"+(endTime-startTime)+"毫秒");
        return sysUser;
    }

    /**
     * 设置组织机构用户的状态
     * @param orgId 组织机构ID
     * @param token
     * @param status 1：正常状态、2：注销申请中、3：已注销、4：撤销申请中、5：已撤销
     * @return 是否处理成功
     */
    @Override
    public boolean cancelSysOranization(String orgId,String token,int status){
        if(orgId==null||token==null)
            return false;
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if(u==null)
            return false;
        //查询机构信息
        List<SysOrganization> org=sysOrganizationExpandMapper.getOrgByBusiness(orgId);
        if(org==null||org.size()==0)
            return false;
        //修改保存机构信息
        SysOrganization o=org.get(0);
        String del_flag = "Y";
        if(1==status || 2 == status || 4==status){
            del_flag = "N";
        }
        o.setDelFlag(del_flag);
        sysOrganizationMapper.updateByPrimaryKey(o);
        //修改本机构的用户信息
        sysUserExpandMapper.deleteByOrganization(o.getId(),null,status+"");
        //修改本机构的角色信息
        sysRoleExpandMapper.deleteByOrganization(o.getId(),del_flag);
        return true;
    }
    @Override
    public Organization findSysUserOrganization(String token){
//从登录缓存中获取机构信息
        CurrentUser u= WebUtil.getCurrentUser(token);
        if(u==null||u.getOrganizationId()==null)
            return null;
        return u.getOrganization();
    }

    @Override
    public int getRecordsTotal(String userName){
        return sysUserExpandMapper.getRecordsTotalExternal(userName);
    }
    @Override
    public List<SysUser> getSysUserList(int start,int length, String userName){
        Page page=new Page();
        page.setStart((long) start);
        page.setLength((long) length);
//        page.doPage();
        page.setPageTail(start+length);
        page.setPageOffset(start);
        return sysUserExpandMapper.getRecordsExternal(page,userName);
    }
    @Override
    public List<SysUser> getSysUserListByUDept(String userId){
        SysUser u=super.queryById(userId);
        if(u==null||u.getDeptId()==null)
            return null;
        Map param=new HashMap<String,Object>();
        param.put("deptId",u.getDeptId());
        List<SysUser> list=sysUserExpandMapper.queryByParam(param);
        return list;
    }

    @Override
    public Result addSubjUser(String token,UserEnum userEnum, SysUser sysUser, List<String> industryTypes, boolean choiceAbattoir,OrgFormEnum orgFormEnum) {
        //1.判断用户是否重复
        //2.获取角色
        //3.保存用户
        //4.保存用户与角色关系
        //5.返回标识
        String account = "";
        String password = "";
        SysUser user = new SysUser();
        if(UserEnum.SUBJADMIN.name().equals(userEnum.name())){
            if(StringUtils.isBlank(sysUser.getAccount()))
                return Result.setFailMsg("账号不能为空");
            if(StringUtils.isBlank(sysUser.getPassword()))
                return Result.setFailMsg("密码不能为空");
            account = sysUser.getAccount();
            password = sysUser.getPassword();
            Map<String,Object> map = new HashMap<>();
            map.put("account",account);
            if(this.checkUserByAccount(map)!= 0)
                return Result.setFailMsg("账号重复");
        }else if(UserEnum.SUBJNORMAL.name().equals(userEnum.name())){
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u == null || StringUtils.isBlank(token))
                return Result.setFailMsg("token认证失败");
            if(StringUtils.isBlank(sysUser.getUserName()))
                return Result.setFailMsg("姓名不能为空");
            account= StringUtils.getPingYin(sysUser.getUserName());
            String accountRandom="";
            Map<String ,Object> param=new HashMap<String,Object>();
            param.put("account",account);
            int isExist = this.checkUserByAccount(param);
            while(isExist == 1){
                accountRandom=String.valueOf((int)(Math.random()*10000));
                param.put("account",account+accountRandom);
                isExist = this.checkUserByAccount(param) ;
            }
            //自动设置登录用户名和密码
            account = account+accountRandom;
            password = account;
        }else{
            return Result.setFailMsg("用户类型错误");
        }
        user.setAccount(account);
        user.setUserName(sysUser.getUserName());
        user.setUserType(userEnum.name());
        user.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password)));
        user.setReservedField2(password);
        user.setStatus("1");
        user.setDelFlag("N");
        //配置时间、操作人
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        List<String> industryTypeSqlIn = new ArrayList<String>();
        //获取用户管理员应该拥有的行业类型角色 01-SUBJ-ROLE、02-SUBJ-ROLE、03-SUBJ-ROLE
        //普通用户屠宰场角色:ABATTOIR-ROLE
        //管理员和普通用户角色 SUBJADMIN-ROLE、SUBJNORMAL-ROLE
        //管理员组织机构管理员角色：SUBJ-ENTERPRISE-ADMIN-ROLE、SUBJ-COOPERATIVE-ADMIN-ROLE、SUBJ-FARM-ADMIN-ROLE、SUBJ-PERSONAL-ADMIN-ROLE
        //管理员组织机构子账号角色：SUBJ-ENTERPRISE-NORMAL-ROLE、SUBJ-COOPERATIVE-NORMAL-ROLE、SUBJ-FARM-NORMAL-ROLE、SUBJ-PERSONAL-NORMAL-ROLE
        for(String i : industryTypes){
            industryTypeSqlIn.add(i+"-SUBJ-ROLE");
        }
        if(choiceAbattoir)
            industryTypeSqlIn.add("ABATTOIR-ROLE");
        if(UserEnum.SUBJADMIN.name().equals(userEnum.name())){
            industryTypeSqlIn.add("SUBJADMIN-ROLE");
            industryTypeSqlIn.add("SUBJ-"+orgFormEnum.name()+"-ADMIN"+"-ROLE");
        }else if (UserEnum.SUBJNORMAL.name().equals(userEnum.name())){
            industryTypeSqlIn.add("SUBJNORMAL-ROLE");
            industryTypeSqlIn.add("SUBJ-"+orgFormEnum.name()+"-NORMAL"+"-ROLE");
        }

        List<SysRole> roles = sysRoleExpandMapper.getSysRoleByRoleCodes(industryTypeSqlIn);
        if((roles == null) || (roles != null && choiceAbattoir && roles.size() <= 2) || (roles != null && !choiceAbattoir && roles.size() <= 1))
            return Result.setFailMsg("无企业类型对于的角色");
        user.setRoleId(roles.get(0).getId());
        SysUser uResult = super.update(user);
        user.setId(uResult.getId());
        for(SysRole s : roles){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(StringUtils.getUUIDString());
            sysUserRole.setUserId(uResult.getId());
            sysUserRole.setRoleId(s.getId());
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUpdateTime(new Date());
            sysUserRoleMapper.insert(sysUserRole);
            //为用户新增角色
        }
        return Result.setSuccessData(user);
    }

    @Override
    public String getMaxAreaByAccount(Map<String, Object> param) {
        return sysUserExpandMapper.getMaxAreaByAccount(param);
    }

    @Override
    public int checkUserByAccount(Map<String, Object> param) {
        return sysUserExpandMapper.checkUserByAccount(param);
    }

    @Override
    public SysUser getSysUserByAccountPwd(String account, String password) {
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
            return null ;
        }
        Map<String,Object> param = new HashedMap();
        param.put("account",account);
        param.put("password",SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password)));
        List<SysUser> ss = this.queryByParam(param);
        if(ss != null && ss.size()>0)
            return ss.get(0);
        return null;
    }

    /**
     * 获取账号的三位数的数值后缀
     * @param maxAreaAccount
     * @return
     */
    public String getAccountRandom(String maxAreaAccount) {
        String accountRandom = "";
        if(StringUtils.isEmpty(maxAreaAccount))
            return "001";
        String[] account = maxAreaAccount.split("-");
        if(account.length != 3){
            return "001";
        }
        int maxArea = Integer.parseInt(account[2])+1;
        if( maxArea<=9){
            accountRandom = "00"+ maxArea;
        }else if(maxArea<100){
            accountRandom = "0"+ maxArea;
        }else{
            accountRandom = maxArea + "";
        }
        return accountRandom ;
    }
}
