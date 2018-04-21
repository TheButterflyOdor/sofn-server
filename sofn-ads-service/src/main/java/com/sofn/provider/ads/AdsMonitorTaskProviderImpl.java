package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.*;
import com.sofn.dao.generator.AdsMonitorTaskMapper;
import com.sofn.dao.generator.AdsOrganTaskMapper;
import com.sofn.model.generator.*;
import com.sofn.util.adsConstant;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 监测任务 providerImpl 实现
 * Created by moon.l
 */
@DubboService(interfaceClass = AdsMonitorTaskProvider.class)
public class AdsMonitorTaskProviderImpl extends BaseProviderImpl<AdsMonitorTask> implements AdsMonitorTaskProvider {

    @Autowired
    private AdsMonitorTaskExpandMapper AdsMonitorTaskExpandMapper;
    @Autowired
    private AdsMonitorTaskMapper adsMonitorTaskMapper;
    @Autowired
    private AdsModelCheckItemExpandMapper adsModelCheckItemExpandMapper;
    @Autowired
    private AdsModelCheckObjectExpandMapper adsModelCheckObjectExpandMapper;
    @Autowired
    private AdsCheckModelExpandMapper adsCheckModelExpandMapper;
    @Autowired
    private AdsModelSampleLinkExpandMapper adsModelSampleLinkExpandMapper;
    @Autowired
    private AdsOrganTaskExpandMapper AdsOrganTaskExpandMapper;
    @Autowired
    private AdsOrganTaskMapper adsOrganTaskMapper;
    @Autowired
    private AdsModelJudgeStandardExpandMapper adsModelJudgeStandardExpandMapper;
    @Autowired
    private AdsMonitorSampleExpandMapper AdsMonitorSampleExpandMapper;
    @Autowired
    private AdsNewModelExpandMapper adsNewModelExpandMapper;



    public PageInfo<AdsMonitorTask> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageInfo(map);
        long count = AdsMonitorTaskExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<Map<String, Object>> getHangye() {
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getHangye();
        return list;
    }

    @Transactional
    public int publishAdsMonitorTask(AdsMonitorTask adsMonitorTask) {
        int result = 0;
        if (adsMonitorTask != null) {
            String task_ids = adsMonitorTask.getTask_ids();
            if (task_ids != null && task_ids.length() > 0) {
                String[] ids = task_ids.split(",");
                if (ids != null && ids.length > 0) {
                    //判定监测任务是否已创建机构任务，如果没有，发布失败
                    for (String id : ids) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("monitorTaskId", id);
                        long count = AdsOrganTaskExpandMapper.getCountByMonitorTaskID(params);
                        if (count == 0) {
                            return -1;
                        }
                    }
                    //判定是否选择检测模型,如果没有,发布失败
                    for (String id : ids) {
                        AdsMonitorTask adsMonitorTaskCheckModel = adsMonitorTaskMapper.selectByPrimaryKey(id);
                        if(adsMonitorTaskCheckModel.getCheckModel() == "" || adsMonitorTaskCheckModel.getCheckModel() == null){
                            return -2;
                        }
                    }
                    for (String id : ids) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("id", id);
                        params.put("publishStatus", adsConstant.YES);
                        params.put("updateBy", adsMonitorTask.getUpdateBy());
                        AdsMonitorTaskExpandMapper.updateAdsMonitorTaskStatus(params);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    public AdsMonitorTask insert(AdsMonitorTask adsMonitorTask) {
        if (null != adsMonitorTask) {
            if (null != adsMonitorTask.getStartTime()) {
                if (null != adsMonitorTask.getEndTime()) {
                    if (null != adsMonitorTask.getDeadline()) {
                        if (adsMonitorTask.getStartTime().compareTo(adsMonitorTask.getEndTime()) < 0
                                && adsMonitorTask.getEndTime().compareTo(adsMonitorTask.getDeadline()) > 0
                                && adsMonitorTask.getStartTime().compareTo(adsMonitorTask.getDeadline()) < 0) {
                            //获取检测项目
                            List<AdsModelCheckItem> checkProjectList = adsModelCheckItemExpandMapper.getCheckItemListByModelId(adsMonitorTask.getCheckModel());
                            List<String> checkProjectNameList = new ArrayList();
                            for (AdsModelCheckItem adsModelCheckItem : checkProjectList) {
                                checkProjectNameList.add(adsModelCheckItem.getName());
                            }
                            String checkProject = listToString(checkProjectNameList);
                            //获取检测对象
                            List<String> checkObjectNameList = adsModelCheckObjectExpandMapper.getCheckObjectNameListByModelId(adsMonitorTask.getCheckModel());
                            String checkObject = listToString(checkObjectNameList);
                            //获取抽样环节
                            List<String> modelSampleLinkNameList = adsModelSampleLinkExpandMapper.getModelSampleLinkNameListByModelId(adsMonitorTask.getCheckModel());
                            String sampleLink = listToString(modelSampleLinkNameList);

                            String uuid = UUID.randomUUID().toString();
                            uuid = uuid.replace("-", "");
                            adsMonitorTask.setId(uuid);
                            adsMonitorTask.setCheckProject(checkProject);
                            adsMonitorTask.setCheckObject(checkObject);
                            adsMonitorTask.setSampleLink(sampleLink);
                            adsMonitorTask.setIndustry(adsCheckModelExpandMapper.getIndustryByModelId(adsMonitorTask.getCheckModel()));
                            AdsMonitorTaskExpandMapper.insert(adsMonitorTask);
                        }
                    }
                }
            }
        }
        return adsMonitorTask;
    }

    //将列表转换为字符串,以"、"隔开
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append("、");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    public List<Map<String, Object>> getYear() {
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getYear();
        return list;
    }

    public List<Map<String, Object>> getLevel() {
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getLevel();
        return list;
    }

    @Override
    public void updatePublishStatus(Map<String, Object> map) {
        AdsMonitorTaskExpandMapper.updatePublishStatus(map);
    }

    public PageInfo<AdsMonitorTask> getPageInfoWithParam(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageInfoWithParam(map);
        long count = AdsMonitorTaskExpandMapper.getCountWithParam(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsMonitorTask> getPageInfoWithParamAudit(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageInfoWithParamAudit(map);
        long count = AdsMonitorTaskExpandMapper.getCountWithParamAudit(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsMonitorTask> getPageInfoWithType(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageInfoWithType(map);
        long count = AdsMonitorTaskExpandMapper.getCountWithType(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<Map<String, Object>> getPageAdsMonitorTask(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageAdsMonitorTask(map);
        long count = AdsMonitorTaskExpandMapper.getPageCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public int updateByPrimaryKey(AdsMonitorTask adsMonitorTask) {
        int result = 0;
        if (null != adsMonitorTask) {
            if (null != adsMonitorTask.getStartTime()) {
                if (null != adsMonitorTask.getEndTime()) {
                    if (null != adsMonitorTask.getDeadline()) {
                        if (adsMonitorTask.getStartTime().compareTo(adsMonitorTask.getEndTime()) <= 0) {
                            //获取检测对象
                            List<String> checkProjectNameList = adsNewModelExpandMapper.getCheckProductNameListByModelId(adsMonitorTask.getCheckModel());
                            String checkObject = listToString(checkProjectNameList);
                            //获取检测项目
                            List<String> checkObjectNameList = adsNewModelExpandMapper.getCheckObjectNameListByModelId(adsMonitorTask.getCheckModel());
                            String checkProject= listToString(checkObjectNameList);

                            adsMonitorTask.setCheckProject(checkProject);
                            adsMonitorTask.setCheckObject(checkObject);
                            adsMonitorTask.setIndustry(adsNewModelExpandMapper.getIndustryByModelId(adsMonitorTask.getCheckModel()));

                            adsMonitorTaskMapper.update(adsMonitorTask);
                            result = 1;
                        }
                    }
                }
            }
        }
        return result;
    }

    public AdsMonitorTask selectByPrimaryKey(String id) {
        return adsMonitorTaskMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据监测任务id查询监测任务基本信息以及机构任务列表
     *
     * @param id
     * @return
     */
    @Override
    public AdsMonitorTask selectMonitorTaskAndOrganTaskByPrimaryKey(String id) {
        AdsMonitorTask task = adsMonitorTaskMapper.selectByPrimaryKey(id);
        if (task != null) {
            //查询机构任务
            List<AdsOrganTask> list = AdsOrganTaskExpandMapper.getOrganListByTaskId(task.getId());
            task.setAdsOrganTaskList(list);
        }
        return task;
    }

    @Override
    public List<AdsMonitorTask> findAdsMonitorTaskByid(Map<String, Object> map) {
        List<AdsMonitorTask> adsMonitorTaskById = AdsMonitorTaskExpandMapper.findAdsMonitorTaskById(map);
        return adsMonitorTaskById;
    }

    @Override
    public AdsMonitorTask findAdsMonitorTask(String id) {
        return AdsMonitorTaskExpandMapper.findAdsMonitorTask(id);
    }


    @Override
    public List<String> selectTaskName() {
        return AdsMonitorTaskExpandMapper.selectTaskName();
    }

    public AdsMonitorTask queryByMyId(Map<String, Object> map) {
        AdsMonitorTask adsMonitorTask = AdsMonitorTaskExpandMapper.queryByMyId(map);
        return adsMonitorTask;
    }


    @Override
    public List<Map<String, Object>> getNameByParam(AdsMonitorTask adsMonitorTask) {
        return AdsMonitorTaskExpandMapper.getNameByParam(adsMonitorTask);
    }

    @Transactional
    public boolean getTaskInfo(AsmsCheckTask asmsCheckTask, List<AsmsMonitorObject> list) {
        try {
            SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            AdsMonitorTask adsMonitorTask = new AdsMonitorTask();
            adsMonitorTask.setTaskName(asmsCheckTask.getTaskName());
            adsMonitorTask.setMonitorClass("监督抽查");
            adsMonitorTask.setId(asmsCheckTask.getId());
//            adsMonitorTask.setMonitorClass(asmsCheckTask.getTaskType());//监测类型
            adsMonitorTask.setReleaseUnit(asmsCheckTask.getTaskReleaseUnit());//发布单位
            adsMonitorTask.setYear(asmsCheckTask.getTaskYear());//年度
            adsMonitorTask.setSeparation(new BigDecimal(asmsCheckTask.getTaskIsSeparate()));//抽检分离
            adsMonitorTask.setStartTime(dd.format(asmsCheckTask.getTaskBeginTime()));//任务开始时间
            adsMonitorTask.setEndTime(dd.format(asmsCheckTask.getTaskEndTime()));//任务结束时间
            adsMonitorTask.setDeadline(dd.format(asmsCheckTask.getTaskSampleDeadline()));//抽样信息上报截止时间
            adsMonitorTask.setAttachment(asmsCheckTask.getAttachmentName());//附件
            adsMonitorTask.setAttachmentAddress(asmsCheckTask.getAttachmentAddress());//附件地址
            adsMonitorTask.setAttachmentcode(asmsCheckTask.getFileNumber());//附件编号
            adsMonitorTask.setComment(asmsCheckTask.getRemark());//备注
            adsMonitorTask.setPublishStatus(new BigDecimal(adsConstant.YES));//发布状态   默认执行中
            adsMonitorTask.setIndustryId(asmsCheckTask.getTaskIndustry());//行业
            adsMonitorTask.setAbolish(asmsCheckTask.getState());//废止状态
            adsMonitorTask.setTaskSource(adsConstant.SUPTASK);//任务来源
            adsMonitorTask.setCreateBy(asmsCheckTask.getCreateBy());
            adsMonitorTask.setCreateTime(asmsCheckTask.getCreateTime());
            adsMonitorTask.setLevelEnum(asmsCheckTask.getLevelEnum());//任务等级

            adsMonitorTask.setSupId(asmsCheckTask.getId());//Sup_id 赋值

            AdsMonitorTaskExpandMapper.insert(adsMonitorTask);

            for (AsmsMonitorObject asmsMonitorObject : list) {
                AdsOrganTask adsOrganTask = new AdsOrganTask();

                adsOrganTask.setId(asmsMonitorObject.getId());
                adsOrganTask.setMonitorTaskId(asmsMonitorObject.getSuperviseCheckTaskId());//监测任务KEY
//                adsOrganTask.setSampleOrganId(asmsMonitorObject.getSampleUnitId());//抽样机构KEY
//                adsOrganTask.setDetectionOrganId(asmsMonitorObject.getDetectionUnitId());//检测机构KEY
                adsOrganTask.setSampleOrganId(asmsCheckTask.getCyUnitId());
                adsOrganTask.setSampleOrgan(asmsCheckTask.getCyUnitName());
                adsOrganTask.setDetectionOrganId(asmsCheckTask.getJcUnitId());
                adsOrganTask.setDetectionOrgan(asmsCheckTask.getJcUnitName());
                adsOrganTask.setCreateBy(asmsMonitorObject.getCreateBy());
                adsOrganTask.setCreateTime(asmsMonitorObject.getCreateTime());
                String num = asmsMonitorObject.getMonitorNum();
                if (null != num) {
                    adsOrganTask.setNumbers(new BigDecimal(num));
                } else {
                    adsOrganTask.setNumbers(new BigDecimal("0"));
                }
                //设置默认值
                BigDecimal b = BigDecimal.ZERO;
                adsOrganTask.setReportStatus("N");
                adsOrganTask.setTasksStatus("N");
                adsOrganTask.setSampleFinishNum(b);
                adsOrganTask.setCheckFinishNum(b);
                adsOrganTask.setSampleFinishStatus(b);
                adsOrganTask.setCheckFinishStatus(b);
                adsOrganTask.setSampleReportStatus(b);
                adsOrganTask.setCheckReportStatus(b);
                adsOrganTaskMapper.insert(adsOrganTask);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public boolean getTaskEntrustAndSample(AlesEntrustDetection alesEntrustDetection, List<AdsMonitorSample> list) {
        try {
            SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            AdsMonitorTask adsMonitorTask = new AdsMonitorTask();
            adsMonitorTask.setTaskName(alesEntrustDetection.getTaskName());
            adsMonitorTask.setMonitorClass("受托检测");
            adsMonitorTask.setId(alesEntrustDetection.getId());
//            adsMonitorTask.setMonitorClass(alesEntrustDetection.getTaskType());//监测类型
            adsMonitorTask.setReleaseUnit(alesEntrustDetection.getCreateOrgName());//发布单位
            adsMonitorTask.setYear(alesEntrustDetection.getTaskyear());//年度
            adsMonitorTask.setSeparation(new BigDecimal(1));//抽检分离
            adsMonitorTask.setStartTime(dd.format(alesEntrustDetection.getTaskBeginTime()));//任务开始时间
            adsMonitorTask.setEndTime(dd.format(alesEntrustDetection.getTaskEndTime()));//任务结束时间
//            adsMonitorTask.setDeadline(dd.format(alesEntrustDetection.getTaskSampleDeadline()));//抽样信息上报截止时间
            adsMonitorTask.setAttachmentAddress(alesEntrustDetection.getAttachments());//附件
            adsMonitorTask.setAttachment(alesEntrustDetection.getAttachmentNames());//附件
            adsMonitorTask.setAttachmentcode(alesEntrustDetection.getFileCode());//附件编号
            adsMonitorTask.setComment(alesEntrustDetection.getRemark());//备注
            adsMonitorTask.setPublishStatus(new BigDecimal(adsConstant.YES));//发布状态   默认执行中
//            adsMonitorTask.setIndustry(alesEntrustDetection.getTaskIndustry());//行业
            adsMonitorTask.setAbolish(alesEntrustDetection.getState());//废止状态
            adsMonitorTask.setTaskSource(adsConstant.ENTTASK);//任务来源
            adsMonitorTask.setCreateBy(alesEntrustDetection.getCreateBy());
            adsMonitorTask.setCreateTime(alesEntrustDetection.getCreateTime());
            adsMonitorTask.setSampleLink(alesEntrustDetection.getCreateOrgName());//暂时存发布单位名称（检测单位）
            adsMonitorTask.setLeve(alesEntrustDetection.getStUnitName());//暂时存受托单位名称
            AdsMonitorTaskExpandMapper.insert(adsMonitorTask);

            //强行生成一个机构任务
            AdsOrganTask adsOrganTask = new AdsOrganTask();

            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsOrganTask.setId(uuid);
            adsOrganTask.setMonitorTaskId(alesEntrustDetection.getId());//监测任务KEY
            adsOrganTask.setSampleOrganId(alesEntrustDetection.getCreateOrgId());
            adsOrganTask.setSampleOrgan(alesEntrustDetection.getCreateOrgName());
            adsOrganTask.setDetectionOrganId(alesEntrustDetection.getStUnitId());
            adsOrganTask.setDetectionOrgan(alesEntrustDetection.getStUnitName());
            adsOrganTask.setCreateBy(alesEntrustDetection.getCreateBy());
            adsOrganTask.setCreateTime(alesEntrustDetection.getCreateTime());
            adsOrganTask.setNumbers(new BigDecimal("0"));
            //设置默认值
            BigDecimal b = BigDecimal.ZERO;
            adsOrganTask.setReportStatus("N");
            adsOrganTask.setTasksStatus("N");
            adsOrganTask.setSampleFinishNum(b);
            adsOrganTask.setCheckFinishNum(b);
            adsOrganTask.setSampleFinishStatus(b);
            adsOrganTask.setCheckFinishStatus(b);
            adsOrganTask.setSampleReportStatus(b);
            adsOrganTask.setCheckReportStatus(b);
            adsOrganTaskMapper.insert(adsOrganTask);

            for (AdsMonitorSample adsMonitorSample : list) {

                adsMonitorSample.setOrganTaskId(uuid);
                adsMonitorSample.setSampleReport("2");
                AdsMonitorSampleExpandMapper.insertAdsMonitor(adsMonitorSample);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean setAbolish(String taskId) {
        try {
            AdsMonitorTaskExpandMapper.updateAbolish(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PageInfo<List<Map<String, Object>>> getSamplesWithSupId(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsMonitorSampleExpandMapper.getPageInfoWithParams(params);
        long count = AdsMonitorSampleExpandMapper.getPageInfoWithParamsCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Transactional
    public boolean getTaskInfoForReback(AsmsRecheckTask asmsRecheckTask,List<AsmsRecheckObject> asmsRecheckObjects) {
        try {
            SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            String supId = asmsRecheckTask.getInitTaskId();
            String batch = asmsRecheckTask.getBatch();

            String mySupId = "";

            AdsMonitorTask adsMonitorTask = new AdsMonitorTask();
            if(null != batch){
                Map<String, Object> param = new HashedMap();
                param.put("supId", supId);
                param.put("batch", batch);

                //获取任务列表
                List<AdsMonitorTask> monitorTasks = AdsMonitorTaskExpandMapper.getTasksBySupIdAndBatch(param);
                if(null != monitorTasks){
                    adsMonitorTask.setCheckProject(monitorTasks.get(0).getTaskName());//暂存初检任务的名称
                    adsMonitorTask.setSupId(monitorTasks.get(0).getId());//暂存初检任务的ID
                    adsMonitorTask.setIndustryId(monitorTasks.get(0).getMonitorClass());//暂存初检任务类型
                    adsMonitorTask.setCheckModel(monitorTasks.get(0).getCheckModel());
                    mySupId = monitorTasks.get(0).getId();
                }
            }else{
                Map<String, Object> param = new HashedMap();
                param.put("id", supId);
                AdsMonitorTask adsMonitorTask2 = AdsMonitorTaskExpandMapper.queryByMyId(param);
                adsMonitorTask.setCheckProject(adsMonitorTask2.getTaskName());//暂存初检任务的名称
                adsMonitorTask.setSupId(supId);//暂存初检任务的ID
                adsMonitorTask.setIndustryId(adsMonitorTask2.getMonitorClass());//暂存初检任务类型
                adsMonitorTask.setCheckModel(adsMonitorTask2.getCheckModel());
                mySupId = supId;
            }
            adsMonitorTask.setTaskName(asmsRecheckTask.getRecheckTaskName());
            adsMonitorTask.setMonitorClass("复检任务");
            adsMonitorTask.setId(asmsRecheckTask.getId());
//            adsMonitorTask.setMonitorClass(alesEntrustDetection.getTaskType());//监测类型
//            adsMonitorTask.setReleaseUnit(alesEntrustDetection.getCreateOrgName());//发布单位
            adsMonitorTask.setYear(asmsRecheckTask.getRecheckTaskYear());//年度
            adsMonitorTask.setSeparation(new BigDecimal(1));//抽检分离
            adsMonitorTask.setStartTime(dd.format(asmsRecheckTask.getRecheckTaskBegin()));//任务开始时间
            adsMonitorTask.setEndTime(dd.format(asmsRecheckTask.getRecheckTaskEnd()));//任务结束时间
//            adsMonitorTask.setDeadline(dd.format(alesEntrustDetection.getTaskSampleDeadline()));//抽样信息上报截止时间
//            adsMonitorTask.setAttachment(alesEntrustDetection.getFiles());//附件
//            adsMonitorTask.setAttachmentcode(alesEntrustDetection.getFileCode());//附件编号
//            adsMonitorTask.setComment(alesEntrustDetection.getRemark());//备注
            adsMonitorTask.setPublishStatus(new BigDecimal(adsConstant.YES));//发布状态   默认执行中
//            adsMonitorTask.setIndustry(alesEntrustDetection.getTaskIndustry());//行业
//            adsMonitorTask.setAbolish(alesEntrustDetection.getState());//废止状态
            adsMonitorTask.setTaskSource(adsConstant.REBTASK);//任务来源
            adsMonitorTask.setCreateBy(asmsRecheckTask.getCreateBy());
            adsMonitorTask.setCreateTime(asmsRecheckTask.getCreateTime());
            adsMonitorTask.setSampleLink(asmsRecheckTask.getCreateOrgName());//暂时存发布单位名称（检测单位）
            AdsMonitorTaskExpandMapper.insert(adsMonitorTask);

            //强行生成一个机构任务
            AdsOrganTask adsOrganTask = new AdsOrganTask();

            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsOrganTask.setId(uuid);
            adsOrganTask.setMonitorTaskId(asmsRecheckTask.getId());//监测任务KEY
//            adsOrganTask.setSampleOrganId(asmsRecheckTask.getCreateOrgId());
//            adsOrganTask.setSampleOrgan(asmsRecheckTask.getCreateOrgName());
            adsOrganTask.setDetectionOrganId(asmsRecheckTask.getRecheckUnitId());
            adsOrganTask.setDetectionOrgan(asmsRecheckTask.getRecheckUnitName());
            adsOrganTask.setCreateBy(asmsRecheckTask.getCreateBy());
            adsOrganTask.setCreateTime(asmsRecheckTask.getCreateTime());
            adsOrganTask.setNumbers(new BigDecimal("0"));
            //设置默认值
            BigDecimal b = BigDecimal.ZERO;
            adsOrganTask.setReportStatus("N");
            adsOrganTask.setTasksStatus("N");
            adsOrganTask.setSampleFinishNum(b);
            adsOrganTask.setCheckFinishNum(b);
            adsOrganTask.setSampleFinishStatus(b);
            adsOrganTask.setCheckFinishStatus(b);
            adsOrganTask.setSampleReportStatus(b);
            adsOrganTask.setCheckReportStatus(b);
            adsOrganTaskMapper.insert(adsOrganTask);

            //通过样品编码查询抽样单
            for(AsmsRecheckObject asmsRecheckObject : asmsRecheckObjects){
                AdsMonitorSample sampleCodeEntity = new AdsMonitorSample();
                sampleCodeEntity.setSampleCode(asmsRecheckObject.getRecheckSampleCode());
                String sampleCode = asmsRecheckObject.getRecheckSampleCode();
                Map<String, Object> param = new HashedMap();
                param.put("sampleCode", sampleCode);
                param.put("supId", mySupId);
                //获取抽样单
                AdsMonitorSample adsMonitorSample = AdsMonitorSampleExpandMapper.findAdsMonitorSampleWithCode(param);

                String uuid2 = UUID.randomUUID().toString();
                uuid2 = uuid2.replace("-", "");
                adsMonitorSample.setId(uuid2);
                adsMonitorSample.setSampleReport("2");
                adsMonitorSample.setOrganTaskId(uuid);
                adsMonitorSample.setDelFlag("N");
                adsMonitorSample.setCreateBy(asmsRecheckTask.getCreateBy());
                adsMonitorSample.setCreateTime(asmsRecheckTask.getCreateTime());
                AdsMonitorSampleExpandMapper.insertAdsMonitor(adsMonitorSample);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getTaskCountDataByModel(String model_id) {
        Map<String, Object> param = new HashedMap();
        param.put("model_id", model_id);
        Map<String, Object> map = new HashedMap();
        int checkObjectCount = adsModelCheckObjectExpandMapper.getCountByModelId(param);    //检测对象数
        int checkItemCount = adsModelCheckItemExpandMapper.getCheckItemCountByModelId(model_id); //检测项目数
        //int standardCount = adsModelJudgeStandardExpandMapper.getJudgeCountByModel(model_id);  //判定标准数
        int standardCount = checkObjectCount * checkItemCount;
        map.put("checkObjectCount", checkObjectCount);
        map.put("checkItemCount", checkItemCount);
        map.put("standardCount", standardCount);
        return map;
    }

    @Override
    public List<AdsMonitorTask> getAdsTaskListByCondition(Map<String, Object> map) {
        return AdsMonitorTaskExpandMapper.getAdsTaskListByCondition(map);
    }
    @Override
    public List<AdsMonitorTask> getAdsTaskListByIds( String[] idsArr) {
        return AdsMonitorTaskExpandMapper.getAdsTaskListByIds(idsArr);
    }


    @Override
    public PageInfo<AdsMonitorTask> getPageInfoTaskName(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageInfoTaskName(map);
        long count = AdsMonitorTaskExpandMapper.getCountTaskName(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AdsMonitorTask findAuditAdsMonitorTaskById(Map<String, Object> map) {
        AdsMonitorTask adsMonitorTask = AdsMonitorTaskExpandMapper.findAuditAdsMonitorTaskById(map);
        return adsMonitorTask;
    }

    public AdsMonitorTask insertSup(AdsMonitorTask adsMonitorTask) {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        adsMonitorTask.setId(uuid);
        adsMonitorTask.setIndustry(adsCheckModelExpandMapper.getIndustryByModelId(adsMonitorTask.getCheckModel()));
        AdsMonitorTaskExpandMapper.insert(adsMonitorTask);
        return adsMonitorTask;
    }

    /**
     * 查询
     *
     * @param map
     * @return
     */
    @Override
    public PageInfo<AdsMonitorTask> queryExecUnitTaskPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AdsMonitorTask> list = AdsMonitorTaskExpandMapper.queryExecUnitTaskList(map);
        int count = AdsMonitorTaskExpandMapper.queryExecUnitTaskCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 查询监测项目的条数
     *
     */
    public  int getAdsMonitorTaskNum(Map<String,Object> map){
        int count = AdsMonitorTaskExpandMapper.getAdsMonitorTaskNum(map);
        return  count;
    }

    public  AdsMonitorTask findAdsMonitorTaskInfo(Map<String,Object> map){
        AdsMonitorTask adsMonitorTask =AdsMonitorTaskExpandMapper.findAdsMonitorTaskInfo(map);
        return  adsMonitorTask;
    }

    public List<AdsMonitorTask> findExpiredTask(){
        return AdsMonitorTaskExpandMapper.findExpiredTask();
    }
   /* @Override
    public void changePublishStatus() {
        AdsMonitorTaskExpandMapper.changePublishStatusByProcedure();
    }*/

    public PageInfo<AdsMonitorTask> getPageInfoForAndroid(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsMonitorTaskExpandMapper.getPageInfoForAndroid(map);
        long count = AdsMonitorTaskExpandMapper.getCountForAndroid(map);
        pageInfo.setPageNum((Integer) map.get("pageNum"));
        pageInfo.setPageSize((Integer) map.get("pageSize"));
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    public void updateAdsMonitorTaskPublishStatus(Map<String, Object> map) {
        AdsMonitorTaskExpandMapper.updateAdsMonitorTaskPublishStatus(map);
    }

    @Override
    public List<Map<String, Object>> getReportStatus(Map<String, Object> map) {
        return  AdsMonitorTaskExpandMapper.getReportStatus(map);
    }

    @Override
    public List<Map<String, Object>> getReportStatusByDelegate(Map<String, Object> map) {
        return AdsMonitorTaskExpandMapper.getReportStatusByDelegate(map);
    }
}
