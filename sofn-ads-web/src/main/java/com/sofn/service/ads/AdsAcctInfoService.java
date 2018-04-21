package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renjinghua on 2016/11/9.
 */
@Service
public class AdsAcctInfoService extends BaseService<AsmsSubjDetectionProvider,AsmsSubjDetection> {
    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider provider){
        this.provider = provider;
    }

    public AsmsSubjDetection findSubjDetectionById(AsmsSubjDetection subjDetection) {
        return provider.findSubjDetectionById(subjDetection.getId());
    }

    public PageInfo getChangeListBySvId(Page page,String svId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("dtId", svId);
        queryMap.put("pageOffset", page.getStart()+1);
        queryMap.put("pageTail", page.getStart()+page.getLength());
        return provider.getChangeListByDtId(queryMap);
    }

    public void addSubjDtChange(AsmsSubjDtChange subjDtChange,CurrentUser sysUser){
        subjDtChange.setApplyDtId(sysUser.getOrgId());

        AsmsSubjDetection asmsSubjDetection = provider.findSubjDetectionById(subjDtChange.getApplyDtId());
        StringBuilder sb = new StringBuilder();
        sb.append("{\"before\":{").append("dtName:").append("\"").append(asmsSubjDetection.getDtName()).append("\",");
        sb.append("dtCode:").append("\"").append(asmsSubjDetection.getDtCode()).append("\",");
        sb.append("dtType:").append("\"").append(asmsSubjDetection.getDtType()).append("\",");
        sb.append("dtLevel:").append("\"").append(asmsSubjDetection.getDtLevel()).append("\",");
        sb.append("dtAreaId:").append("\"").append(asmsSubjDetection.getDtAreaId()).append("\",");
        sb.append("dtAddress:").append("\"").append(asmsSubjDetection.getDtAddress()).append("\",");
        sb.append("dtLeader:").append("\"").append(asmsSubjDetection.getDtLeader()).append("\",");
        sb.append("dtLeaderPhone:").append("\"").append(asmsSubjDetection.getDtLeaderPhone()).append("\",");
        sb.append("dtContact:").append("\"").append(asmsSubjDetection.getDtContact()).append("\",");
        sb.append("dtContactPhone:").append("\"").append(asmsSubjDetection.getDtContactPhone()).append("\",");
        sb.append("dtContactQQ:").append("\"").append(asmsSubjDetection.getDtContactQQ()).append("\",");
        sb.append("dtContactEmail:").append("\"").append(asmsSubjDetection.getDtContactEmail()).append("\",");
        sb.append("dtPostcode:").append("\"").append(asmsSubjDetection.getDtPostcode()).append("\"}}");
        StringBuilder content = new StringBuilder();
        if(equalsStrNoEqual(asmsSubjDetection.getDtName(),subjDtChange.getDtName())){
            content.append("机构名称变更为：").append(subjDtChange.getDtName()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtCode(),subjDtChange.getDtCode())){
            content.append("机构代码变更为：").append(subjDtChange.getDtCode()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtType(),subjDtChange.getDtType())){
            content.append("机构类型变更为：").append(subjDtChange.getDtType()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtLevel(),subjDtChange.getDtLevel())){
            content.append("机构等级变更为：").append(subjDtChange.getDtLevel()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtAreaId(),subjDtChange.getDtAreaId())){
            content.append("所属区域变更为：").append(subjDtChange.getDtAreaId()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtAddress(),subjDtChange.getDtAddress())){
            content.append("通讯地址变更为：").append(subjDtChange.getDtAddress()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtLeader(),subjDtChange.getDtLeader())){
            content.append("负责人变更为：").append(subjDtChange.getDtLeader()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtLeaderPhone(),subjDtChange.getDtLeaderPhone())){
            content.append("负责人电话变更为：").append(subjDtChange.getDtLeaderPhone()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtContact(),subjDtChange.getDtContact())){
            content.append("联系人变更为：").append(subjDtChange.getDtContact()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtContactPhone(),subjDtChange.getDtContactPhone())){
            content.append("联系人电话变更为：").append(subjDtChange.getDtContactPhone()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtContactQQ(),subjDtChange.getDtContactQQ())){
            content.append("联系人QQ变更为：").append(subjDtChange.getDtContactQQ()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtContactEmail(),subjDtChange.getDtContactEmail())){
            content.append("联系人电子邮箱变更为：").append(subjDtChange.getDtContactEmail()).append("。");
        }
        if(equalsStrNoEqual(asmsSubjDetection.getDtPostcode(),subjDtChange.getDtPostcode())){
            content.append("邮编变更为：").append(subjDtChange.getDtPostcode()).append("。");
        }
        subjDtChange.setId(StringUtils.getUUIDString());
        subjDtChange.setChangeBeforeField(sb.toString());
        subjDtChange.setChangeContent(content.toString());
        subjDtChange.setApplyUserId(sysUser.getId());
        subjDtChange.setApplyTime(new Date());
        subjDtChange.setDelFlag("N");
        asmsSubjDetection.setStatus("1");
        provider.addSubjDtChange(subjDtChange);
    }

    public boolean equalsStrNoEqual(String before,String after){
        if((StringUtils.isBlank(before)&&StringUtils.isNotBlank(after))||(StringUtils.isNotBlank(before)&& !before.equals(after))){
            return true;
        }else{
            return false;
        }
    }

    public void addSubjDtCancel(AsmsSubjDtCancel subjDtCancel){
        provider.addSubjDtCancel(subjDtCancel);
    }

    public AsmsSubjDtChange findSubjDtChangeById(String id){
        return provider.findSubjDtChangeById(id);
    }


}
