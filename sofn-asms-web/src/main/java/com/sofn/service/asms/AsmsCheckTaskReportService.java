package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.*;
import com.sofn.provider.ads.AdsCheckInfoProvider;
import com.sofn.provider.ads.AdsInfoProjectProvider;
import com.sofn.provider.ads.AdsProducttempoRrarycodeProvider;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import com.sofn.provider.asms.AsmsCheckTaskReportProvider;
import com.sofn.web.asms.AsmsEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsCheckTaskReportService extends BaseService<AsmsCheckTaskReportProvider, AsmsCheckTaskReport> {

    private AsmsCheckTaskProvider asmsCheckTaskProvider;//监督检查任务

    private AdsCheckInfoProvider adsCheckInfoProvider;//检测系统-检测信息

    private AdsInfoProjectProvider adsInfoProjectProvider;//检测系统-

    private AdsProducttempoRrarycodeProvider adsProducttempoRrarycodeProvider;//检测系统-主体备案

    @DubboReference
    public void setProvider(AdsProducttempoRrarycodeProvider adsProducttempoRrarycodeProvider) {
        this.adsProducttempoRrarycodeProvider = adsProducttempoRrarycodeProvider;
    }


    @DubboReference
    public void setProvider(AsmsCheckTaskReportProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(AdsCheckInfoProvider adsCheckInfoProvider) {
        this.adsCheckInfoProvider = adsCheckInfoProvider;
    }

    @DubboReference
    public void setProvider(AdsInfoProjectProvider adsInfoProjectProvider) {
        this.adsInfoProjectProvider = adsInfoProjectProvider;
    }

    @DubboReference
    public void setProvider(AsmsCheckTaskProvider asmsCheckTaskProvider) {
        this.asmsCheckTaskProvider = asmsCheckTaskProvider;
    }


    /**
     * 报告列表
     * 部级任务：需要通过承担单位id关联该部级任务子任务获取报告
     * 非部级任务：直接通过任务id获取报告
     */
    public PageInfo<List<Map<String, Object>>> list(AsmsCheckTaskReport asmsCheckTaskReport, int start, int length, String CdUnitId, String taskLevel) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(start, length));
        if (AsmsEnum.MINISTERIAL.getCode().equals(taskLevel)) {
            List<AsmsCheckTask> list = asmsCheckTaskProvider.getTaskByCreateOrgIdAndParentTaskId(asmsCheckTaskReport.getMonitorTaskId(), CdUnitId);
            if (list != null && !list.isEmpty()) {
                if (list.get(0) != null) {
                    params.put("taskId", list.get(0).getId());
                } else {
                    params.put("taskId", "-1");
                }
            } else {
                params.put("taskId", "-1");
            }
        } else {
            params.put("taskId", StringUtil.isNotBlank(asmsCheckTaskReport.getMonitorTaskId()) ? asmsCheckTaskReport.getMonitorTaskId() : "-1");//所属任务id[必要条件]
        }
        params.put("monitorTask", StringUtil.isNotBlank(asmsCheckTaskReport.getMonitorTask()) ? "%" + asmsCheckTaskReport.getMonitorTask() + "%" : null);//所属任务名称
        params.put("years", StringUtil.isNotBlank(asmsCheckTaskReport.getYears()) ? asmsCheckTaskReport.getYears() : null);//年份
        return provider.list(params);
    }

    /**
     * 根据报告获取监测信息列表
     */
    public PageInfo<AdsCheckInfo> getJclistByReport(AsmsCheckTaskReport asmsCheckTaskReport, int start, int length) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(start, length));
        params.put("organTaskId", asmsCheckTaskReport.getOrganTaskId());//机构任务id（对应监测对象id）
        return adsCheckInfoProvider.getPageInfoWithOrgTaskId(params);
    }

    /**
     * 根据监测信息id获取检测详情列表
     */
    public List<AdsInfoProject> getReportByJcInfo(AdsInfoProject adsInfoProject) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkInfoId", adsInfoProject.getCheckInfoId());//检测信息id
        return adsInfoProjectProvider.getPageInfoWithCheckInfoId(params);
    }


    /**
     * 产品备案信息列表
     */
    public PageInfo<AdsProducttempoRrarycode> getPageInfo(AdsProducttempoRrarycode adsProducttempoRrarycode, int start, int length) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(start, length));
//        params.put("start",start);
//        params.put("length",length)
//        params.put("organTaskId", asmsCheckTaskReport.getOrganTaskId());//机构任务id（对应监测对象id）
        return adsProducttempoRrarycodeProvider.getPageInfo(params);
    }

    /**
     * 产品备案详情
     */
    public AdsProducttempoRrarycode findById(String id ) {

        return adsProducttempoRrarycodeProvider.findById(id);
    }
}
