package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsOrganTask;
import com.sofn.provider.ads.AdsOrganTaskProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构任务 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsOrganTaskService extends BaseService<AdsOrganTaskProvider, AdsOrganTask> {

    @DubboReference
    public void setAdsOrganTaskProvider(AdsOrganTaskProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsOrganTask bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    /* public List<AdsOrganTask> findList(){
          return provider.findList();
     }*/


    /**
     * 获取监测任务的机构任务列表
     * @param bean
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getPageInfoByMonitorTask(AdsOrganTask bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId",bean.getMonitorTaskId());
        queryParams.put("sampleOrgan",bean.getSampleOrgan());
        queryParams.put("detectionOrgan",bean.getDetectionOrgan());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoByMonitorTask(queryParams);
    }

    /**
     * 创建机构任务
     * @param adsOrganTask
     * @return
     */
    public int addOrganTask(AdsOrganTask adsOrganTask){
        return provider.add(adsOrganTask);
    }

    /**
     * 批量删除机构任务
     * @param adsOrganTask
     * @return
     */
    public int batchDelete(AdsOrganTask adsOrganTask){
        return provider.batchDelete(adsOrganTask);
    }

    public PageInfo getPageInfoByMonitorTaskID(AdsOrganTask bean, int pageNum, int pageSize, String monitorClass, String year, String taskName,String publishStatus) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("publishStatus",publishStatus); //监测任务发布状态
        queryParams.put("sampleOrgan",bean.getSampleOrgan());
        queryParams.put("detectionOrgan",bean.getDetectionOrgan());
        queryParams.put("monitorClass",monitorClass);
        queryParams.put("year",year);
        queryParams.put("monitorTaskId",bean.getMonitorTaskId());
        queryParams.put("taskName",taskName);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("orgId", bean.getDeparmentId());
        return provider.getPageInfoByMonitorTaskID(queryParams);
    }
    public List<AdsOrganTask> findSampleOrgan(String monitorTaskId) {
        return  provider.findSampleOrgan(monitorTaskId);
    }

    public void updateCheckInfo(String organTaskId,int num,int status) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId", organTaskId);
        queryParams.put("num", num);
        queryParams.put("status", status);
        provider.updateCheckInfo(queryParams);
    }

    public void updateById(AdsOrganTask adsOrganTask) {
        provider.updateById(adsOrganTask);
    }

    /**
     * 根据监测任务id查询机构任务列表
     * @param taskId
     * @return
     */
    public List<AdsOrganTask> getOrganListByTaskId(String taskId){
        return provider.getOrganListByTaskId(taskId);
    }

}