package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.SupervisionInspectionExpandMapper;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.generator.AsmsInspectionAssess;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.model.qry.EnforceLawPersonnelDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:19
 */
@DubboService(interfaceClass = SupervisionInspectionProvider.class)
public class SupervisionInspectionProviderImpl extends BaseProviderImpl implements SupervisionInspectionProvider {

    @Autowired
    private SupervisionInspectionExpandMapper mapper;

    @Override
    public PageInfo getBaseInspectionAllList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = mapper.getBaseInspectionAllList(map);
        long count = mapper.getBaseInspectionAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getInspectionTaskList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = mapper.getInspectionTaskList(params);
        long count = mapper.getInspectionTaskCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getInspectionManAssessList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = mapper.getInspectionManAssessList(params);
        //获取实际巡查次数
        if (list.size()>0){
            for (Map<String,Object> mp : list){
                String userId = (String)mp.get("USERID");
                String taskType = (String)mp.get("TASKTYPE");
                String taskDateType = (String)mp.get("TASKDATETYPE");
                String taskDateYear = (String)mp.get("TASKDATEYEAR");
                String taskDateMonths = (String)mp.get("TASKDATEMONTHS");
                String taskDateQuarter = (String)mp.get("TASKDATEQUARTER");
                StringBuffer beginTime = new StringBuffer();
                StringBuffer endTime = new StringBuffer();
                if ("月度".equals(taskDateType.trim())){
                    beginTime.append(taskDateYear+"-"+taskDateMonths+"-01 00:00:00") ;
                    int year = Integer.parseInt(taskDateYear);
                    if ("02".equals(taskDateMonths.trim())){
                        if (year%4==0 && year%100!=0 || year%400==0){
                            //润年
                            endTime.append(taskDateYear+"-"+taskDateMonths+"-29 00:00:00") ;
                        }else {
                            //平年
                            endTime.append(taskDateYear+"-"+taskDateMonths+"-28 00:00:00") ;
                        }
                    }else {
                        if ("01".equals(taskDateMonths.trim()) || "03".equals(taskDateMonths.trim())|| "05".equals(taskDateMonths.trim())|| "07".equals(taskDateMonths.trim())|| "08".equals(taskDateMonths.trim())|| "10".equals(taskDateMonths.trim())||"12".equals(taskDateMonths.trim())){
                            endTime.append(taskDateYear+"-"+taskDateMonths+"-31 00:00:00") ;
                        }else {
                            endTime.append(taskDateYear+"-"+taskDateMonths+"-30 00:00:00") ;
                        }
                    }

                }else if("季度".equals(taskDateType.trim())){
                    if ("1".equals(taskDateQuarter.trim())){
                        beginTime.append(taskDateYear+"-01-01 00:00:00") ;
                        endTime.append(taskDateYear+"-03-31 00:00:00") ;
                    }else if ("2".equals(taskDateQuarter.trim())){
                        beginTime.append(taskDateYear+"-04-01 00:00:00") ;
                        endTime.append(taskDateYear+"-06-30 00:00:00") ;
                    }else if ("3".equals(taskDateQuarter.trim())){
                        beginTime.append(taskDateYear+"-07-01 00:00:00") ;
                        endTime.append(taskDateYear+"-09-30 00:00:00") ;
                    }else if ("4".equals(taskDateQuarter.trim())){
                        beginTime.append(taskDateYear+"-10-01 00:00:00") ;
                        endTime.append(taskDateYear+"-12-30 00:00:00") ;
                    }
                }else if("年度".equals(taskDateType.trim())){
                    beginTime.append(taskDateYear+"-01-01 00:00:00") ;
                    endTime.append(taskDateYear+"-12-30 00:00:00") ;
                }
                //任务类型
                int inspectionType = 0;
                if ("基地".equals(taskType.trim())){
                    inspectionType = 1;
                }else if ("主体".equals(taskType.trim())){
                    inspectionType = 2;
                }else if ("农资".equals(taskType.trim())){
                    inspectionType = 3;
                }else if ("其他".equals(taskType.trim())){
                    inspectionType = 0;
                }
                //次数统计
                Map<String, Object> pars = new HashMap<>();
                pars.put("userId",userId);
                pars.put("beginTime",beginTime);
                pars.put("endTime",endTime);
                pars.put("inspectionType",inspectionType);
                Long c = mapper.getInspectionManRealCount(pars);
                mp.put("INSPECTIONREALCOUNT",c.toString());
                Long i =0l;
                if (mp.get("INSPECTIONCOUNT")!=null) {
                    i = Long.parseLong(mp.get("INSPECTIONCOUNT").toString());
                }else {
                    mp.put("INSPECTIONCOUNT",i);
                }
                if (c<i&&i!=0){
                    mp.put("TASKSTATUS","未完成");
                }else {
                    mp.put("TASKSTATUS","已完成");
                }
            }
        }
        long count = mapper.getInspectionManAssessCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AsmsBaseInspection findBaseInspectionById(String id) {
        return mapper.findBaseInspectionById(id);
    }

    @Override
    public PageInfo getEnforceLawPersonList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<EnforceLawPersonnelDto> list = mapper.getEnforceLawPersonList(map);
        long count = mapper.getEnforceLawPersonCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public EnforceLawPersonnelDto findEnforceLawPersonById(String id) {
        return mapper.findEnforceLawPersonById(id);
    }

    @Override
    public PageInfo getKhTaskList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>>  list = mapper.getKhTaskList(map);
        long count = mapper.getKhTaskCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AsmsInspectionTask findKhTaskById(String id) {
        return mapper.findKhTaskById(id);
    }

    @Override
    public List<AsmsInspectionAssess> getKhPersonByTaskId(String id) {
        return mapper.getKhPersonByTaskId(id);
    }

    @Override
    public PageInfo getAssessList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>>  list = mapper.getAssessList(map);
        long count = mapper.getAssessCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public Long getKhRealCount(Map<String, Object> map) {
        return mapper.getKhRealCount(map);
    }
}
