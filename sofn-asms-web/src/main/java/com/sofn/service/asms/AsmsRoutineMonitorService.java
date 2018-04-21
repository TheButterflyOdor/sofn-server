package com.sofn.service.asms;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AdsFile;
import com.sofn.model.generator.AsmsRoutineLeadUnit;
import com.sofn.model.generator.AsmsRoutineMonitor;
import com.sofn.provider.ads.AdsFileProvider;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.asms.AsmsRoutineMonitorProvider;
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
public class AsmsRoutineMonitorService extends BaseService<AsmsRoutineMonitorProvider, AsmsRoutineMonitor> {

    private SysUserProvider sysUserProvider;

    private AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    private AdsFileProvider adsFileProvider;

    private AdsMonitorTaskProvider adsMonitorTaskProvider;

    @DubboReference
    public void setProvider(AsmsRoutineMonitorProvider provider) {
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

    @DubboReference
    public void setProvider(AdsFileProvider adsFileProvider) {
        this.adsFileProvider = adsFileProvider;
    }

    /**
     * 添加任务
     */
    public void addTask(AsmsRoutineMonitor asmsRoutineMonitor, String orgs, String token, String userId) {
        //任务信息
        Organization organization = this.getOrganizationByToken(token);
        asmsRoutineMonitor.setCreateOrgRegionId(organization.getRegionId());//任务所属创建机构行政区划
        //机构级别—任务级别,系统管理是1234，而数据字典是0123，所以要处理一下
        asmsRoutineMonitor.setLevelEnum(String.valueOf(Integer.valueOf(organization.getOrgLevel())-1));
        asmsRoutineMonitor.setCreateOrgName(organization.getOrgName());
        asmsRoutineMonitor.setCreateOrgId(organization.getOrgId());
        asmsRoutineMonitor.setId(StringUtils.getUUIDString());
        asmsRoutineMonitor.setRmState(AsmsEnum.UN_PUBLISHED.getCode());//待发布
        asmsRoutineMonitor.setDelFlag("N");
        asmsRoutineMonitor.setCreateBy(userId);
        asmsRoutineMonitor.setCreateTime(new Date());
        asmsRoutineMonitor.setUpdateBy(userId);
        asmsRoutineMonitor.setUpdateTime(new Date());
        String id = provider.addAsmsRoutineMonitor(asmsRoutineMonitor).getId();//主表信息
        if (!"[]".equals(orgs)) {
            List<AsmsRoutineLeadUnit> asmsRoutineLeadUnits = JSONObject.parseArray(orgs, AsmsRoutineLeadUnit.class);
            for (AsmsRoutineLeadUnit asmsRoutineLeadUnit : asmsRoutineLeadUnits) {
                if (asmsRoutineLeadUnit != null) {
                    asmsRoutineLeadUnit.setRoutineMonitorId(id);
                    provider.addGlInfo(asmsRoutineLeadUnit);
                }
            }
        }
    }

    public void updateTask(AsmsRoutineMonitor asmsRoutineMonitor, String qtIds, String userId) {
        AsmsRoutineMonitor routineMonitor = provider.queryById(asmsRoutineMonitor.getId());
        routineMonitor.setRmName(asmsRoutineMonitor.getRmName());
        routineMonitor.setRmType(asmsRoutineMonitor.getRmType());
        routineMonitor.setRmModelId(asmsRoutineMonitor.getRmModelId());
        routineMonitor.setRmYear(asmsRoutineMonitor.getRmYear());
        routineMonitor.setRmBatch(asmsRoutineMonitor.getRmBatch());
        routineMonitor.setRmDateBegin(asmsRoutineMonitor.getRmDateBegin());
        routineMonitor.setRmDateEnd(asmsRoutineMonitor.getRmDateEnd());
        routineMonitor.setRmReleaseUnit(asmsRoutineMonitor.getRmReleaseUnit());
        routineMonitor.setRmFile(asmsRoutineMonitor.getRmFile());
        routineMonitor.setRmFileName(asmsRoutineMonitor.getRmFileName());
        routineMonitor.setRmFileNum(asmsRoutineMonitor.getRmFileNum());
        routineMonitor.setRmRemark(asmsRoutineMonitor.getRmRemark());
        routineMonitor.setUpdateBy(userId);
        routineMonitor.setUpdateTime(new Date());
        provider.updateAsmsRoutineMonitor(routineMonitor);//主表信息
        //关联表信息
        provider.delGlInfoByTaskId(asmsRoutineMonitor.getId());//删除原数据
        if (!"[]".equals(qtIds)) {
            List<AsmsRoutineLeadUnit> asmsRoutineLeadUnits = JSONObject.parseArray(qtIds, AsmsRoutineLeadUnit.class);
            for (AsmsRoutineLeadUnit asmsRoutineLeadUnit : asmsRoutineLeadUnits) {
                if (asmsRoutineLeadUnit != null) {
                    asmsRoutineLeadUnit.setRoutineMonitorId(asmsRoutineMonitor.getId());
                    provider.addGlInfo(asmsRoutineLeadUnit);
                }
            }
        }
    }

    public PageInfo<List<Map<String, Object>>> list(String token, AsmsRoutineMonitor r, QueryParameter p) {
        Organization organization = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(p.getStart(), p.getLength()));
        params.putAll(AsmsServiceBoth.listFilter(organization.getOrgId()));
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        params.put("rmName", StringUtil.isNotBlank(r.getRmName()) ? "%" + r.getRmName() + "%" : null);
        params.put("rmState", StringUtil.isNotBlank(r.getRmState()) ? r.getRmState() : null);
        params.put("rmYear", StringUtil.isNotBlank(r.getRmYear()) ? r.getRmYear() : null);
        return provider.list(params);
    }

    public PageInfo<List<AsmsRoutineMonitor>> listByCeateOrgId(String token,AsmsRoutineMonitor asmsRoutineMonitor, QueryParameter queryParameter) {
        Organization org = this.getOrganizationByToken(token);
        Map<String, Object> params = new HashMap<>();
        params.put("createOrgId", org.getOrgId());
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("dateBegin", StringUtil.isNotBlank(queryParameter.getDateBegin()) ? queryParameter.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(queryParameter.getDateEnd()) ? queryParameter.getDateEnd() : null);
        params.put("rmName", StringUtil.isNotBlank(asmsRoutineMonitor.getRmName()) ? "%" + asmsRoutineMonitor.getRmName() + "%" : null);
        params.put("rmState", StringUtil.isNotBlank(asmsRoutineMonitor.getRmState()) ? asmsRoutineMonitor.getRmState() : null);
        params.put("rmYear", StringUtil.isNotBlank(asmsRoutineMonitor.getRmYear()) ? asmsRoutineMonitor.getRmYear() : null);
        return provider.listByCeateOrgId(params);
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsRoutineLeadUnit> getUnitByTaskId(String taskId) {
        return provider.getUnitByTaskId(taskId);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    /**
     * 根据监测系统提供的接口获取牵头单位例行监测报告列表
     *
     * @param adsFile
     * @param taskId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getPageInfoAdsFileBySupId(AdsFile adsFile, String taskId, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("supId", taskId);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        return adsFileProvider.getPageInfoAdsFileBySupId(params);
    }


    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map){
        adsMonitorTaskProvider.updateAdsMonitorTaskPublishStatus(map);
    }
}
