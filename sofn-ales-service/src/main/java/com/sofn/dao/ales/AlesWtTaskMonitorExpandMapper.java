package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesWtTaskMonitor;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@MyBatisDao
public interface AlesWtTaskMonitorExpandMapper extends BaseExpandMapper {
    //查询集合
    List<AlesWtTaskMonitor> getPagesList(Map<String, Object> map);
    //查询集合条数
    Long getPageCount(Map<String, Object> params);
    //根据id删除
    boolean delByTaskId(AlesWtTaskMonitor alesWtTaskMonitor);
    //根据条件跟新
    boolean updateIsSample(AlesWtTaskMonitor alesWtTaskMonitor);
    //查询根据id
    AlesWtTaskMonitor getOneByTaskId(String id);
    //查询集合
    List<AlesWtTaskMonitor> getListByTaskId(AlesWtTaskMonitor alesWtTaskMonitor);
    /*
    新增 获取判断报告状态
    2018年2月10日10:13:12
     */
    List<Map<String, Object>> getReportStatus(Map<String, Object> map);
}