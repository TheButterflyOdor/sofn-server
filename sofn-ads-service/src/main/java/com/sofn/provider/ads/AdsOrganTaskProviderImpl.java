package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.AdsMonitorTaskMapper;
import com.sofn.dao.generator.AdsOrganTaskMapper;
import com.sofn.dao.ads.AdsOrganTaskExpandMapper;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.model.generator.AdsOrganTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
*	机构任务 providerImpl 实现
 * Created by yangran
 */
@DubboService(interfaceClass = AdsOrganTaskProvider.class)
public class AdsOrganTaskProviderImpl extends BaseProviderImpl<AdsOrganTask> implements AdsOrganTaskProvider {

    @Autowired
    private AdsMonitorTaskMapper adsMonitorTaskMapper;
    @Autowired
    private AdsOrganTaskExpandMapper AdsOrganTaskExpandMapper;
    @Autowired
    private AdsOrganTaskMapper adsOrganTaskMapper;

    public PageInfo<AdsOrganTask> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsOrganTaskExpandMapper.getPageInfo(map);
        long count = AdsOrganTaskExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /*@Override
    public List<AdsOrganTask> findList() {
        List<Object> list= AdsOrganTaskExpandMapper.findAll();
        List<AdsOrganTask> listAdsOrganTask= new ArrayList<>();
        for (Object o:list
             ) {
            list.add((AdsOrganTask)o);
        }
        return listAdsOrganTask;
    }*/

    @Override
    public PageInfo<AdsOrganTask> getPageInfoByMonitorTask(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsOrganTaskExpandMapper.getPageInfoByMonitorTask(map);
        long count = AdsOrganTaskExpandMapper.getCountByMonitorTask(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int add(AdsOrganTask adsOrganTask) {
        if(adsOrganTask!=null){
            AdsMonitorTask task = adsMonitorTaskMapper.selectByPrimaryKey(adsOrganTask.getMonitorTaskId());
            if(task!=null){
                //判断监测任务是否已发布
                if(task.getPublishStatus().compareTo(BigDecimal.ONE) == 0){
                    return -2;  //监测任务已发布
                }
                //判断抽检机构是否已经被下发任务
                Map<String, Object> queryParams = new HashMap<>();
                queryParams.put("monitorTaskId",adsOrganTask.getMonitorTaskId());
                queryParams.put("sampleOrganId",adsOrganTask.getSampleOrganId());
                queryParams.put("detectionOrganId",adsOrganTask.getDetectionOrganId());
                int count = AdsOrganTaskExpandMapper.getCountByAdsOrganCondition(queryParams);
                if(count > 0){
                    return -3;  //抽检机构已经被下发了任务
                }
                //判断上报时间，需要晚于抽样截止时间，早于任务结束时间
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if(StringUtils.isNullBlank(task.getDeadline()) && StringUtils.isNullBlank(task.getEndTime()) && StringUtils.isNullBlank(adsOrganTask.getUploadTime())){
                        Date deadline = format.parse(task.getDeadline());
                        Date endTime = format.parse(task.getEndTime());
                        Date upTime = format.parse(adsOrganTask.getUploadTime());
                        if(upTime.getTime() < deadline.getTime() || upTime.getTime() > endTime.getTime()){
                            return -4;  //上报时间不符合条件，需要晚于抽样截止时间，早于任务结束时间
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return -4;
                }
                //创建机构任务
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                BigDecimal b = BigDecimal.ZERO;
                adsOrganTask.setId(uuid);
                adsOrganTask.setReportTime(adsOrganTask.getUploadTime());
                adsOrganTask.setReportStatus("N");
                adsOrganTask.setTasksStatus("N");
                adsOrganTask.setSampleFinishNum(b);
                adsOrganTask.setCheckFinishNum(b);
                adsOrganTask.setSampleFinishStatus(b);
                adsOrganTask.setCheckFinishStatus(b);
                adsOrganTask.setSampleReportStatus(b);
                adsOrganTask.setCheckReportStatus(b);
                adsOrganTaskMapper.insert(adsOrganTask);
                return 1;
            }else{
                return -1;    //监测任务不存在
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public int batchDelete(AdsOrganTask adsOrganTask) {
        int result = 0;
        if(adsOrganTask!=null){
            String ids = adsOrganTask.getIds();
            AdsMonitorTask task = adsMonitorTaskMapper.selectByPrimaryKey(adsOrganTask.getMonitorTaskId());
            if(task!=null){
                if(task.getPublishStatus().compareTo(BigDecimal.ONE) == 0){
                    result = -2;    //监测任务已发布
                }else{
                    if(ids!=null && ids.length() > 0){
                        String[] arr = ids.split(",");
                        if(arr!=null && arr.length > 0){
                            for (String id : arr){
                                AdsOrganTask organTask = new AdsOrganTask();
                                organTask.setId(id);
                                organTask.setUpdateBy(adsOrganTask.getUpdateBy());
                                adsOrganTaskMapper.deleteOrganTaskByLogic(organTask);
                            }
                            result = 1;
                        }
                    }
                }
            }else{
                result = -1;    //监测任务不存在
            }
        }
        return result;
    }

    @Override
    public PageInfo<AdsOrganTask> getPageInfoByMonitorTaskID(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsOrganTaskExpandMapper.getPageInfoByMonitorTaskID(map);
        long count = AdsOrganTaskExpandMapper.getCountByMonitorTaskID(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<AdsOrganTask> findSampleOrgan(String monitorTaskId) {
        return  AdsOrganTaskExpandMapper.findSampleOrgan(monitorTaskId);
    }

    public void updateCheckInfo(Map<String, Object> map) {
        AdsOrganTaskExpandMapper.updateCheckInfo(map);
    }

    @Override
    public void updateById(AdsOrganTask adsOrganTask) {AdsOrganTaskExpandMapper.updateById(adsOrganTask);
    }

    @Override
    public List<AdsOrganTask> getOrganListByTaskId(String taskId) {
        return AdsOrganTaskExpandMapper.getOrganListByTaskId(taskId);
    }
}
