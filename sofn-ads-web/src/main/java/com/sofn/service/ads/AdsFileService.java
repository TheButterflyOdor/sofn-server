package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsFile;
import com.sofn.provider.ads.AdsFileProvider;
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
public class AdsFileService extends BaseService<AdsFileProvider, AdsFile> {

    @DubboReference
    public void setAdsFileProvider(AdsFileProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsFile bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("folder", bean.getFolder());
        queryParams.put("field", bean.getField());
        queryParams.put("monitorTask", bean.getMonitorTask());
        queryParams.put("fileName", bean.getFileName());
        queryParams.put("monitorType", bean.getMonitorType());
        queryParams.put("monitorTaskId", bean.getMonitorTaskId());
        queryParams.put("years", bean.getYears());
        queryParams.put("source", bean.getSource());
        queryParams.put("createBy", bean.getCreateBy());
        return provider.getPageInfo(queryParams);
    }

    public PageInfo getDlReportPage(AdsFile bean, String organId, int pageNum, int pageSize){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("folder", bean.getFolder());
        queryParams.put("field", bean.getField());
        queryParams.put("monitorTask", bean.getMonitorTask());
        queryParams.put("fileName", bean.getFileName());
        queryParams.put("monitorType", bean.getMonitorType());
        queryParams.put("monitorTaskId", bean.getMonitorTaskId());
        queryParams.put("organId", organId);
        queryParams.put("years", bean.getYears());
        queryParams.put("source", bean.getSource());
        queryParams.put("createBy", bean.getCreateBy());
        return provider.getDlReportPage(queryParams);
    }

    public int batchDownload(AdsFile adsFile){
        //return provider.batchDownload(adsFile);
    return 1;
    }
    public List<Map<String,Object>> getField() {
        return provider.getField();
    }
    public List<Map<String,Object>> getType() {
        return provider.getType();
    }
    public List<Map<String,Object>> getYear() {
        return provider.getYear();
    }

    public List<Map<String,Object>> getTask(AdsFile  bean) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("folder",bean.getFolder());
        queryParams.put("monitorType",bean.getMonitorType());
        queryParams.put("source",bean.getSource());
        queryParams.put("organId",bean.getUploadDepartment());
        return provider.getTask(queryParams);
    }

    public List<AdsFile> getIdByOrganTask(String  organTaskId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId", organTaskId);
        return provider.getIdByOrganTask(queryParams);
    }

    public PageInfo getPageInfoProblemFile(AdsFile bean, int pageNum, int pageSize){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("years", bean.getYears());
        queryParams.put("field", bean.getField());
        queryParams.put("monitorType", bean.getMonitorType());
        queryParams.put("folder", bean.getFolder());
        queryParams.put("monitorTask", bean.getMonitorTask());
        queryParams.put("uploadDepartment", bean.getUploadDepartment());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoProblemFile(queryParams);
    }

    public int updateForFile(AdsFile bean){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("fileName", bean.getFileName());
        queryParams.put("fileAddress", bean.getFileAddress());
        queryParams.put("id", bean.getId());

        return provider.updateForFile(queryParams);
    }

    /**
     * 根据文件对象的monitorTaskId和source属性进行查询
     * @param bean 上传文件对象
     * @return
     */
    public List<AdsFile> getAdsFileByCondition(AdsFile bean){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId",bean.getMonitorTaskId());
        queryParams.put("source",bean.getSource());
        return provider.getAdsFileByCondition(queryParams);
    }

}