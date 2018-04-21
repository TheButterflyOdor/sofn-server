package com.sofn.service.ads;

import com.ctc.wstx.util.StringUtil;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.SysUser;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.sys.SysUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @author yangran
 * @version 2016年11月18日
 */
@Service
public class AdsSysUserService extends BaseService<SysUserProvider, SysUser> {
    @DubboReference
    public void setProvider(SysUserProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    @DubboReference
    public  SysUserProvider sysUserProvider;

    /**
     * 修改密码
     * @param account
     * @param oldPwd
     * @param newPwd
     * @param token
     * @return
     */
    public boolean changePwd(String account, String oldPwd, String newPwd,String token){
        return this.provider.changePwd(account,oldPwd,newPwd,token);
    }

    /**
     * 变更用户信息
     * @param sysUser
     * @return
     */
    public SysUser changeInfo(SysUser sysUser){
        SysUser su = this.provider.queryById(sysUser.getId());
        if(su!=null){
            su.setPostId(sysUser.getPostId());
            su.setPhone(sysUser.getPhone());
            su.setEmail(sysUser.getEmail());
            su.setUpdateBy(sysUser.getId());
            su.setUpdateTime(new Date());
            return this.provider.update(su);
        }
        return null;
    }

    /**
     * 用户备案注销
     * @param subjDtCancel
     * @param token
     * @return
     */
    @Transactional
    public int cancelInfo(String token,AsmsSubjDtCancel subjDtCancel){
        subjDtCancel.setId(StringUtils.getUUIDString());
        subjDtCancel.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjDtCancel.setDtId(this.provider.findSysUserOrganization(token).getOrgId());
        subjDtCancel.setApplyTime(new Date());
        subjDtCancel.setAuditState("0");//0-未审核, 1-审核通过, 2-审核不通过
        subjDtCancel.setDelFlag("N");//N-未删除, Y-逻辑删除
        //return asmsSubjDetectionProvider.addSubjDtCancel(subjDtCancel);
        sysUserProvider.cancelSysOranization(subjDtCancel.getDtId(),token,2);
        return asmsSubjDetectionProvider.addSubjDtCancel(subjDtCancel);//调监管系统的接口

    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public SysUser queryById(String id){
        return this.provider.queryById(id);
    }

}
