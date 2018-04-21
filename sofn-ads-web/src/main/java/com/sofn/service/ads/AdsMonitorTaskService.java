package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.provider.ads.AdsMonitorTaskProvider;
import com.sofn.provider.ales.AlesWtTaskMonitorProvider;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import com.sofn.provider.asms.AsmsMonitorObjectProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监测任务 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsMonitorTaskService extends BaseService<AdsMonitorTaskProvider, AdsMonitorTask> {

    @DubboReference
    public void setAdsMonitorTaskProvider(AdsMonitorTaskProvider provider) {
        this.provider = provider;
    }

    private AsmsMonitorObjectProvider asmsMonitorObjectProvider;

    private AlesWtTaskMonitorProvider alesWtTaskMonitorProvider;

    private AsmsCheckTaskProvider asmsCheckTaskProvider;

    @DubboReference
    public void setAsmsMonitorObjectProvider(AsmsMonitorObjectProvider asmsMonitorObjectProvider){
        this.asmsMonitorObjectProvider = asmsMonitorObjectProvider;
    }

    @DubboReference
    public void setAlesWtTaskMonitorProvider(AlesWtTaskMonitorProvider alesWtTaskMonitorProvider){
        this.alesWtTaskMonitorProvider = alesWtTaskMonitorProvider;
    }

    @DubboReference
    public void setAsmsCheckTaskProvider(AsmsCheckTaskProvider asmsCheckTaskProvider){
        this.asmsCheckTaskProvider = asmsCheckTaskProvider;
    }

    public static final String routine="1";//例行
    public static final String special="2";//专项
    public static final String supervise="3";//监督
    public PageInfo getPageInfo(AdsMonitorTask bean, int pageNum, int pageSize,String queryStartTiem,String queryEndTiem,String orgId,AdsFile adsFile) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("queryStartTiem",queryStartTiem);
        queryParams.put("queryEndTiem",queryEndTiem);
        queryParams.put("industry",bean.getIndustry());
        queryParams.put("year",bean.getYear());
        queryParams.put("monitorClass",bean.getMonitorClass());
        queryParams.put("year",bean.getYear());
        queryParams.put("taskSource", bean.getTaskSource());
        queryParams.put("publishStatus", bean.getPublishStatus());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("monitorType", bean.getMonitorType());
        queryParams.put("organId",orgId);
        queryParams.put("source",adsFile.getSource());
        queryParams.put("is_history",bean.getIs_history());
        return provider.getPageInfo(queryParams);
    }

    /**
     * 根据条件查询监测任务列表
     * @param bean
     * @return
     */
    public List<AdsMonitorTask> getAdsTaskListByCondition(AdsMonitorTaskExtend bean, String orgId){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("flag", bean.getFlag());
        if (StringUtils.isNotEmpty(bean.getTaskName())){
            queryParams.put("taskName",bean.getTaskName());
        }
        queryParams.put("monitorClass",bean.getMonitorClass());
        queryParams.put("year",bean.getYear());
        queryParams.put("organId",orgId);
        return provider.getAdsTaskListByCondition(queryParams);
    }

    /**
     * 根据复选框批量导出
     * @param bean
     * @return
     */
    public List<AdsMonitorTask> getAdsTaskListByIds(AdsMonitorTaskExtend bean){
        String ids=  bean.getIds();
        String[] idsArr = ids.split(",");
        return provider.getAdsTaskListByIds(idsArr);
    }

    /**
     * 修改执行状态
     * @param bean
     * @return
     */
    public void updatePublishStatus(AdsMonitorTask bean){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("publishStatus", bean.getPublishStatus());
        queryParams.put("id", bean.getId());
         provider.updatePublishStatus(queryParams);
    }

    /**
     * 发布监测任务
     * @param adsMonitorTask
     * @return
     */
    public int publishAdsMonitorTask(AdsMonitorTask adsMonitorTask){
        return provider.publishAdsMonitorTask(adsMonitorTask);
    }

    public List<Map<String,Object>> getHangye(AdsMonitorTask bean) {
        return provider.getHangye();
    }

    public AdsMonitorTask insert(AdsMonitorTask adsMonitorTask) {
        return provider.insert(adsMonitorTask);
    }

    public List<Map<String,Object>> getYear() {
        return provider.getYear();
    }

    public List<Map<String,Object>> getLevel() {
        return provider.getLevel();
    }

    public PageInfo getPageInfoWithParam(AdsMonitorTask bean, int pageNum, int pageSize, String industry, String year, String status,String type,String orgId,String level) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("taskSource", bean.getTaskSource());
        queryParams.put("publishStatus", bean.getPublishStatus());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("industry", industry);
        queryParams.put("year", year);
        queryParams.put("status", status);
        queryParams.put("type", type);
        queryParams.put("orgId", orgId);
        queryParams.put("level", level);
        queryParams.put("startTime",bean.getStartTime());
        queryParams.put("endTime",bean.getEndTime());
        PageInfo pageInfo = new PageInfo();
        if("监督抽查".equals(type)){
            pageInfo = provider.getPageInfoWithParamAudit(queryParams);
        }else {
            pageInfo = provider.getPageInfoWithParam(queryParams);
        }
        return pageInfo;
    }

    public PageInfo getPageInfoWithType(AdsMonitorTask bean, int pageNum, int pageSize, String status,String type,String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("status", status);
        queryParams.put("type", type);
        queryParams.put("orgId", orgId);
        return provider.getPageInfoWithType(queryParams);
    }

    public PageInfo getPageAdsMonitorTask(String id, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageAdsMonitorTask(queryParams);
    }
    public int updateByPrimaryKey(AdsMonitorTask adsMonitorTask){
        return provider.updateByPrimaryKey(adsMonitorTask);
    }

    /**
     * 根据监测任务id查询监测任务基本信息
     * @param id
     * @return
     */
    public AdsMonitorTask selectByPrimaryKey(String id){
        return provider.selectByPrimaryKey(id);
    }
    /**
     * 根据监测任务id查询监测任务基本信息以及机构任务列表
     * @param monitorTaskId
     * @return
     */
    public List<AdsMonitorTask> findAdsMonitorTaskByid(String monitorTaskId,String sampleOrganId,String monitorClass) {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId",monitorTaskId);
        queryParams.put("sampleOrganId",sampleOrganId);
        queryParams.put("detectionOrganId",sampleOrganId);
        if(monitorClass.equals(routine)){
            queryParams.put("monitorClass","例行监测");
        }else if(monitorClass.equals(special)){
            queryParams.put("monitorClass","专项监测");
        }else if(monitorClass.equals(supervise)){
            queryParams.put("monitorClass","监督抽查");
        }
        return provider.findAdsMonitorTaskByid(queryParams);
    }
    public AdsMonitorTask selectMonitorTaskAndOrganTaskByPrimaryKey(String id){
        return provider.selectMonitorTaskAndOrganTaskByPrimaryKey(id);
    }

    public AdsMonitorTask findAdsMonitorTask(String id) {
        return provider.findAdsMonitorTask(id);
    }

    public List<String> selectTaskName() {
        return provider.selectTaskName();
    }

    public AdsMonitorTask queryByMyId(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.queryByMyId(queryParams);
    }

    public List<Map<String, Object>> getNameByParam(AdsMonitorTask adsMonitorTask){
        return provider.getNameByParam(adsMonitorTask);
    }

    public Map<String, Object> getTaskCountDataByModel(String model_id){
        return provider.getTaskCountDataByModel(model_id);
    }

    public PageInfo getPageInfoTaskName(AdsMonitorTask bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("industry", bean.getIndustry());
        queryParams.put("industryId", bean.getIndustryId());
        queryParams.put("monitorClass", bean.getMonitorClass());
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organId", bean.getOrganId());
        return provider.getPageInfoTaskName(queryParams);
    }

    public AdsMonitorTask findAuditAdsMonitorTaskById(String id, String sampleOrganId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id",id);
        queryParams.put("sampleOrganId", sampleOrganId);
        queryParams.put("detectionOrganId", sampleOrganId);
        return provider.findAuditAdsMonitorTaskById(queryParams);
    }
    public AdsMonitorTask insertSup(AdsMonitorTask adsMonitorTask) {
        return provider.insertSup(adsMonitorTask);
    }

    /**
     * 查询承担单位接收新任务列表
     * @param sampleOrganId
     * @param monitorClass
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo queryExecUnitTaskPageInfo(String sampleOrganId,String monitorClass, int pageNum, int pageSize){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("sampleOrganId",sampleOrganId);
        queryParams.put("detectionOrganId",sampleOrganId);
        queryParams.put("monitorClass",monitorClass);
        return provider.queryExecUnitTaskPageInfo(queryParams);
    }

    //查询监测任务返回的条数
    public int getAdsMonitorTaskNum(AdsMonitorTask adsMonitorTask,int status){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("supId",adsMonitorTask.getSupId());
        queryParams.put("organId",adsMonitorTask.getOrganId());
        queryParams.put("publishStatus",status);
        return provider.getAdsMonitorTaskNum(queryParams);
    }
    public AdsMonitorTask findAdsMonitorTaskInfo(AdsMonitorTask adsMonitorTask){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("supId",adsMonitorTask.getSupId());
        queryParams.put("batch",adsMonitorTask.getBatch());
        return provider.findAdsMonitorTaskInfo(queryParams);
    }

    /**
     * 查询过期的监测任务
     * @return 过期监测任务的集合
     */
    public List<AdsMonitorTask> findExpiredTask(){
        return provider.findExpiredTask();
    }

    public PageInfo getPageInfoForAndroid(AdsMonitorTask bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("taskName",bean.getTaskName());
        queryParams.put("monitorClass",bean.getMonitorClass());
        queryParams.put("publishStatus", bean.getPublishStatus());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organId",bean.getOrganId());
        return provider.getPageInfoForAndroid(queryParams);
    }

    /**
     * 监督抽查任务-监测对象列表
     */
    public PageInfo<List<Map<String, Object>>> objList(String superviseCheckTaskId, QueryParameter queryParameter) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> paramsPage = new HashMap<>();
        paramsPage.put("pageNum", (queryParameter.getStart() / queryParameter.getLength() + 1));
        paramsPage.put("pageSize", queryParameter.getLength());
        params.putAll(paramsPage);
        params.put("taskId", StringUtil.isNotBlank(superviseCheckTaskId) ? superviseCheckTaskId : "-1");
        PageInfo<List<Map<String, Object>>> pageInfo = asmsMonitorObjectProvider.objList(params);
        List<List<Map<String, Object>>> list = pageInfo.getList();
        for(int i=0;i<list.size();i++){
            AsmsMonitorObject monitorObject = (AsmsMonitorObject)list.get(i);
            String id = monitorObject.getId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("taskId",id);
            List<Map<String, Object>> list1 = provider.getReportStatus(map);
            int k = 0;
            if(list1!=null&&list1.size()>0){
                for(Map<String, Object> m : list1){
                    String status = m.get("CHECK_REPORT").toString();
                    if("1".equals(status)){
                        k++;
                    }
                }
                if(k==list1.size()){
                    AsmsMonitorObject b = (AsmsMonitorObject) list.get(i);
                    b.setFlag("1");
                }
            }
        }
        return pageInfo;
    }

    /**
     * 受托检测-监测对象列表
     */
    public PageInfo<AlesWtTaskMonitor> pages(String superviseCheckTaskId, String detectionUnitId, QueryParameter queryParameter) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> paramsPage = new HashMap<>();
        paramsPage.put("pageNum", (queryParameter.getStart() / queryParameter.getLength() + 1));
        paramsPage.put("pageSize", queryParameter.getLength());
        params.putAll(paramsPage);
        params.put("taskId", StringUtil.isNotBlank(superviseCheckTaskId) ? superviseCheckTaskId : "-1");
        PageInfo<AlesWtTaskMonitor> pageInfo = alesWtTaskMonitorProvider.pages(params);
        List<AlesWtTaskMonitor> list = pageInfo.getList();
        Map map = new HashMap();
        map.put("taskId",detectionUnitId);
        List<Map<String, Object>> list1 = provider.getReportStatusByDelegate(map);
        int k = 0;
        boolean flag = false;
        if(list1!=null&&list1.size()>0){
            for(Map<String, Object> m : list1){
                String status = m.get("CHECK_REPORT").toString();
                if("1".equals(status)){
                    k++;
                }
            }
            if(k==list1.size()){
                  flag = true;
            }
        }
        for(AlesWtTaskMonitor monitor : list){
            if(flag){
                monitor.setStatus("1");
            }
        }
        return pageInfo;
    }


  public void  updateAsmsTaskStateByIdAndType(String id, String type, String state){
      asmsCheckTaskProvider.updateAsmsTaskStateByIdAndType(id, type, state);
  }
}