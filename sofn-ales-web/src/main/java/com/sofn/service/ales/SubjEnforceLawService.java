package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AsmsSubjElCancel;
import com.sofn.model.generator.AsmsSubjElChange;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.provider.asms.AsmsSubjEnforceLawProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.util.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/10/9.
 */
@Service
public class SubjEnforceLawService extends BaseService<AsmsSubjEnforceLawProvider,AsmsSubjEnforceLaw> {
    @DubboReference
    public void setProvider(AsmsSubjEnforceLawProvider provider){
        this.provider = provider;
    }

    private SysUserProvider sysUserProvider;
    @DubboReference
    public void setSysUserProvider(SysUserProvider sysUserProvider){
        this.sysUserProvider = sysUserProvider;
    }

    //获取机构变更历史列表
    public PageInfo getChangeListByElId(AsmsSubjElChange AlesSubjElChange,Page page,String token){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("applyElId",sysUserProvider.findSysUserOrganization(token).getOrgId());
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        return provider.getChangeListByElId(queryParams);
    }

    //添加机构变更申请
    public int addSubjElChange(String token,AsmsSubjElChange subjElChange){
        subjElChange.setApplyElId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        AsmsSubjEnforceLaw subjEnforceLaw = this.findSubjEnforceLawById(subjElChange.getApplyElId());
        //查询是否有执法机构（机构名称、机构代码、所属区域和机构级别其中的一个相同）--有的话返回一个标识
        Map<String,Object> map = new HashMap();
        map.put("areaId",subjElChange.getElAreaId());
        map.put("name",subjElChange.getElName());
        map.put("code",subjElChange.getElCode());
        map.put("elLevelId",subjElChange.getElLevelId());
        List<AsmsSubjEnforceLaw> list = provider.getSubjEnforceLawByCondition(map);
        if(list!=null&&!list.isEmpty()){
            for(AsmsSubjEnforceLaw ase:list){//如果是当前执法机构，则允许修改
                if(ase.getId().equals(subjEnforceLaw.getId())){
                    continue;
                }else {
                    return 0;
                }
        }
    }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"before\":{").append("elName:").append("\"").append(subjEnforceLaw.getElName()).append("\",");
        sb.append("elCode:").append("\"").append(subjEnforceLaw.getElCode()).append("\",");
        sb.append("elType:").append("\"").append(subjEnforceLaw.getElType()).append("\",");
        sb.append("elTypeId:").append("\"").append(subjEnforceLaw.getElTypeId()).append("\",");
        sb.append("elLevel:").append("\"").append(subjEnforceLaw.getElLevel()).append("\",");
        sb.append("elLevelId:").append("\"").append(subjEnforceLaw.getElLevelId()).append("\",");
        sb.append("elArea:").append("\"").append(subjEnforceLaw.getElArea()).append("\",");
        sb.append("elAreaId:").append("\"").append(subjEnforceLaw.getElAreaId()).append("\",");
        sb.append("elAddress:").append("\"").append(subjEnforceLaw.getElAddress()).append("\",");
        sb.append("elLeader:").append("\"").append(subjEnforceLaw.getElLeader()).append("\",");
        sb.append("elLeaderPhone:").append("\"").append(subjEnforceLaw.getElLeaderPhone()).append("\",");
        sb.append("elContact:").append("\"").append(subjEnforceLaw.getElContact()).append("\",");
        sb.append("elContactPhone:").append("\"").append(subjEnforceLaw.getElContactPhone()).append("\",");
        sb.append("elContactQQ:").append("\"").append(subjEnforceLaw.getElContactQQ()).append("\",");
        sb.append("elContactEmail:").append("\"").append(subjEnforceLaw.getElContactEmail()).append("\",");
        sb.append("elPostcode:").append("\"").append(subjEnforceLaw.getElPostcode()).append("\",");
        sb.append("elUnitNature:").append("\"").append(subjEnforceLaw.getElUnitNature()).append("\",");
        sb.append("elWorkBody:").append("\"").append(subjEnforceLaw.getElWorkBody()).append("\",");
        sb.append("elPeopleNum:").append("\"").append(subjEnforceLaw.getElPeopleNum()).append("\"}}");
        StringBuilder content = new StringBuilder();
        if(this.equalsStrNoEqual(subjEnforceLaw.getElName(),subjElChange.getElName())){
            content.append("机构名称变更为：").append(subjElChange.getElName()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElCode(),subjElChange.getElCode())){
            content.append("机构代码变更为：").append(subjElChange.getElCode()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElType(),subjElChange.getElType())){
            content.append("机构类型变更为：").append(subjElChange.getElType()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElLevel(),subjElChange.getElLevel())){
            content.append("机构等级变更为：").append(subjElChange.getElLevel()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElArea(),subjElChange.getElArea())){
            content.append("所属区域变更为：").append(subjElChange.getElArea()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElAddress(),subjElChange.getElAddress())){
            content.append("通讯地址变更为：").append(subjElChange.getElAddress()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElLeader(),subjElChange.getElLeader())){
            content.append("负责人变更为：").append(subjElChange.getElLeader()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElLeaderPhone(),subjElChange.getElLeaderPhone())){
            content.append("负责人电话变更为：").append(subjElChange.getElLeaderPhone()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElContact(),subjElChange.getElContact())){
            content.append("联系人变更为：").append(subjElChange.getElContact()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElContactPhone(),subjElChange.getElContactPhone())){
            content.append("联系人电话变更为：").append(subjElChange.getElContactPhone()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElContactQQ(),subjElChange.getElContactQQ())){
            content.append("联系人QQ变更为：").append(subjElChange.getElContactQQ()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElContactEmail(),subjElChange.getElContactEmail())){
            content.append("联系人电子邮箱变更为：").append(subjElChange.getElContactEmail()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElPostcode(),subjElChange.getElPostcode())){
            content.append("邮编变更为：").append(subjElChange.getElPostcode()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElUnitNature(),subjElChange.getElUnitNature())){
            content.append("单位性质变更为：").append(subjElChange.getElUnitNature()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElWorkBody(),subjElChange.getElWorkBody())){
            content.append("具体办事机构变更为：").append(subjElChange.getElWorkBody()).append("。");
        }
        if(this.equalsStrNoEqual(subjEnforceLaw.getElPeopleNum(),subjElChange.getElPeopleNum())){
            content.append("执法人数变更为：").append(subjElChange.getElPeopleNum()).append("。");
        }
        subjElChange.setId(StringUtils.getUUIDString());
        subjElChange.setChangeBeforeField(sb.toString().replace("null",""));
        subjElChange.setChangeContent(content.toString());
        subjElChange.setApplyUserId(WebUtil.getCurrentUser(token).getId());
        subjElChange.setApplyTime(new Date());
        subjElChange.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjElChange.setDelFlag("N");
        return provider.addSubjElChange(subjElChange);
    }

    public boolean equalsStrNoEqual(String before,String after){
        if((StringUtils.isBlank(before)&&StringUtils.isNotBlank(after))||(StringUtils.isNotBlank(before)&&!before.equals(after))){
            return true;
        }else{
            return false;
        }
    }

    //注销机构申请
    public int addSubjElCancel(String token,AsmsSubjElCancel subjElCancel){
        subjElCancel.setId(StringUtils.getUUIDString());
        subjElCancel.setElId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjElCancel.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjElCancel.setApplyTime(new Date());
        subjElCancel.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjElCancel.setDelFlag("N");
        sysUserProvider.cancelSysOranization(sysUserProvider.findSysUserOrganization(token).getOrgId(),token,2);//注销申请中
        return provider.addSubjElCancel(subjElCancel);
    }

    //根据ID查找执法机构备案信息
    public AsmsSubjEnforceLaw findSubjEnforceLawById(String id){
        return  provider.findSubjEnforceLawById(id);
    }

    //通过token获取组织机构
    public Organization findSysOrganizationByToken(String token){
        return sysUserProvider.findSysUserOrganization(token);
    }

    //根据ID查找变更历史
    public AsmsSubjElChange findSubjElChangeById(String id){
        return provider.findSubjElChangeById(id);
    }

    /**
     * 修改密码
     * @param account
     * @param oldPwd
     * @param newPwd
     * @param token
     * @return
     */
    public boolean changePwd(String account, String oldPwd, String newPwd,String token){
        return sysUserProvider.changePwd(account,oldPwd,newPwd,token);
    }

}
