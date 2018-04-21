package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsFile;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.model.generator.AsmsCheckTaskReport;
import com.sofn.provider.ads.AdsFileProvider;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.asms.AsmsCheckTaskReportProvider;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsSetFileInfoService extends BaseService<AsmsCheckTaskReportProvider, AsmsCheckTaskReport> {
    private  AdsMonitorTaskProvider adsMonitorTaskProvider;

    @DubboReference
    public void setAdsSetFileInfoProvider(AsmsCheckTaskReportProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setAdsMonitorTaskProvider(AdsMonitorTaskProvider provider) {
        this.adsMonitorTaskProvider = provider;
    }

    public boolean setFileInfo(AdsFile adsFile) {
        AsmsCheckTaskReport n = new AsmsCheckTaskReport();
        n.setId(adsFile.getId());
        n.setFileName(adsFile.getFileName());
        n.setMonitorType(adsFile.getMonitorType());
        n.setFileAddress(adsFile.getFileAddress());
        n.setMonitorTaskId(adsFile.getMonitorTaskId());
        n.setMonitorTask(adsFile.getMonitorTask());
        n.setUploadDepartment(adsFile.getUploadDepartment());
        n.setYears(adsFile.getYears());
        n.setSource(adsFile.getSource());
        n.setFolder(adsFile.getFolder());
        n.setField(adsFile.getField());
        n.setOrganTaskId(adsFile.getOrganTaskId());
        return provider.addReport(n);
    }

    public boolean setFileInfoForBase(AdsFile adsFile) {

        String monitorTask = adsFile.getMonitorTaskId();

        Map<String, Object> map = new HashedMap();
        map.put("monitorTaskId",monitorTask);

        List<AdsMonitorTask> adsMonitorTasks = adsMonitorTaskProvider.findAdsMonitorTaskByid(map);

        AsmsCheckTaskReport n = new AsmsCheckTaskReport();
        n.setId(adsFile.getId());
        n.setFileName(adsFile.getFileName());
        n.setMonitorType(adsFile.getMonitorType());
        n.setFileAddress(adsFile.getFileAddress());
        n.setMonitorTaskId(adsMonitorTasks.get(0).getSupId());
        n.setMonitorTask(adsFile.getMonitorTask());
        n.setUploadDepartment(adsFile.getUploadDepartment());
        n.setYears(adsFile.getYears());
        n.setSource(adsFile.getSource());
        n.setFolder(adsFile.getFolder());
        n.setField(adsFile.getField());
        n.setOrganTaskId(adsFile.getOrganTaskId());
        return provider.addReport(n);
    }

}