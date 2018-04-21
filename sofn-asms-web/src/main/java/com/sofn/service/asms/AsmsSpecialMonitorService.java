package com.sofn.service.asms;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsSpecialLeadUnit;
import com.sofn.model.generator.AsmsSpecialMonitor;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.asms.AsmsSpecialMonitorProvider;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.web.asms.AsmsEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsSpecialMonitorService extends BaseService<AsmsSpecialMonitorProvider, AsmsSpecialMonitor> {

    private SysUserProvider sysUserProvider;

    private AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    private AdsMonitorTaskProvider adsMonitorTaskProvider;

    @DubboReference
    public void setProvider(AsmsSpecialMonitorProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider asmsSubjDetectionProvider) {
        this.asmsSubjDetectionProvider = asmsSubjDetectionProvider;
    }

    @DubboReference
    public void setProvider(AdsMonitorTaskProvider adsMonitorTaskProvider) {
        this.adsMonitorTaskProvider = adsMonitorTaskProvider;
    }

    public void addTask(AsmsSpecialMonitor asmsSpecialMonitor, String orgs, String token, String userId) {
        Organization organization = this.getOrganizationByToken(token);
        asmsSpecialMonitor.setCreateOrgRegionId(organization.getRegionId());
        //机构级别—任务级别,系统管理是1234，而数据字典是0123，所以要处理一下
        asmsSpecialMonitor.setLevelEnum(String.valueOf(Integer.valueOf(organization.getOrgLevel())-1));
        asmsSpecialMonitor.setCreateOrgName(organization.getOrgName());
        asmsSpecialMonitor.setCreateOrgId(organization.getOrgId());
        asmsSpecialMonitor.setSmState(AsmsEnum.UN_PUBLISHED.getCode());
        asmsSpecialMonitor.setId(StringUtils.getUUIDString());
        asmsSpecialMonitor.setSmState(AsmsEnum.UN_PUBLISHED.getCode());//待发布
        asmsSpecialMonitor.setDelFlag("N");
        asmsSpecialMonitor.setCreateBy(userId);
        asmsSpecialMonitor.setCreateTime(new Date());
        asmsSpecialMonitor.setUpdateBy(userId);
        asmsSpecialMonitor.setUpdateTime(new Date());
        String id = provider.addAsmsSpecialMonitor(asmsSpecialMonitor).getId();//主表信息
        if (!"[]".equals(orgs)) {
            List<AsmsSpecialLeadUnit> asmsSpecialLeadUnits = JSONObject.parseArray(orgs, AsmsSpecialLeadUnit.class);
            for (AsmsSpecialLeadUnit asmsSpecialLeadUnit : asmsSpecialLeadUnits) {
                if (asmsSpecialLeadUnit != null) {
                    asmsSpecialLeadUnit.setSpecialMonitorId(id);
                    provider.addGlInfo(asmsSpecialLeadUnit);
                }
            }
        }
    }

    public void updateTask(AsmsSpecialMonitor asmsSpecialMonitor, String qtIds, String userId) {
        AsmsSpecialMonitor specialMonitor = provider.queryById(asmsSpecialMonitor.getId());
        specialMonitor.setSmName(asmsSpecialMonitor.getSmName());
        specialMonitor.setSmType(asmsSpecialMonitor.getSmType());
        specialMonitor.setSmModelId(asmsSpecialMonitor.getSmModelId());
        specialMonitor.setSmYear(asmsSpecialMonitor.getSmYear());
        specialMonitor.setSmBatch(asmsSpecialMonitor.getSmBatch());
        specialMonitor.setSmDateBegin(asmsSpecialMonitor.getSmDateBegin());
        specialMonitor.setSmDateEnd(asmsSpecialMonitor.getSmDateEnd());
        specialMonitor.setSmReleaseUnit(asmsSpecialMonitor.getSmReleaseUnit());
        specialMonitor.setSmFile(asmsSpecialMonitor.getSmFile());
        specialMonitor.setSmFileName(asmsSpecialMonitor.getSmFileName());
        specialMonitor.setSmFileNum(asmsSpecialMonitor.getSmFileNum());
        specialMonitor.setSmRemark(asmsSpecialMonitor.getSmRemark());
        specialMonitor.setUpdateBy(userId);
        specialMonitor.setUpdateTime(new Date());
        provider.updateAsmsSpecialMonitor(specialMonitor);//主表信息
        //关联表信息
        provider.delGlInfoByTaskId(asmsSpecialMonitor.getId());//删除原数据
        if (!"[]".equals(qtIds)) {
            List<AsmsSpecialLeadUnit> lis = JSONObject.parseArray(qtIds, AsmsSpecialLeadUnit.class);
            for (AsmsSpecialLeadUnit asmsSpecialLeadUnit : lis) {
                if (asmsSpecialLeadUnit != null) {
                    asmsSpecialLeadUnit.setSpecialMonitorId(asmsSpecialMonitor.getId());
                    provider.addGlInfo(asmsSpecialLeadUnit);
                }
            }
        }
    }

    public PageInfo<List<Map<String, Object>>> list(String token, AsmsSpecialMonitor asmsSpecialMonitor, QueryParameter queryParameter) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.listFilter(org.getOrgId()));//创建机构
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("dateBegin", StringUtil.isNotBlank(queryParameter.getDateBegin()) ? queryParameter.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(queryParameter.getDateEnd()) ? queryParameter.getDateEnd() : null);
        params.put("smName", StringUtil.isNotBlank(asmsSpecialMonitor.getSmName()) ? "%" + asmsSpecialMonitor.getSmName() + "%" : null);
        params.put("smState", StringUtil.isNotBlank(asmsSpecialMonitor.getSmState()) ? asmsSpecialMonitor.getSmState() : null);
        params.put("smYear", StringUtil.isNotBlank(asmsSpecialMonitor.getSmYear()) ? asmsSpecialMonitor.getSmYear() : null);
        return provider.list(params);
    }


    public PageInfo<List<AsmsSpecialMonitor>> listByOrg(String token, AsmsSpecialMonitor asmsSpecialMonitor, QueryParameter queryParameter) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.put("createOrgId", org.getOrgId());
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("dateBegin", StringUtil.isNotBlank(queryParameter.getDateBegin()) ? queryParameter.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(queryParameter.getDateEnd()) ? queryParameter.getDateEnd() : null);
        params.put("smName", StringUtil.isNotBlank(asmsSpecialMonitor.getSmName()) ? "%" + asmsSpecialMonitor.getSmName() + "%" : null);
        params.put("smState", StringUtil.isNotBlank(asmsSpecialMonitor.getSmState()) ? asmsSpecialMonitor.getSmState() : null);
        params.put("smYear", StringUtil.isNotBlank(asmsSpecialMonitor.getSmYear()) ? asmsSpecialMonitor.getSmYear() : null);
        return provider.listByOrg(params);
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsSpecialLeadUnit> getUnitByTaskId(String taskId) {
        return provider.getUnitByTaskId(taskId);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }


    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map){
        adsMonitorTaskProvider.updateAdsMonitorTaskPublishStatus(map);
    }

}
