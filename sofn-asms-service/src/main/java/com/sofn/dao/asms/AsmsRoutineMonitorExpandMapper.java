package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsRoutineMonitor;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsRoutineMonitorExpandMapper extends BaseExpandMapper {

    List<Map<String, Object>> getPagesList(Map<String, Object> map);

    Long getPageCount(Map<String, Object> params);

    List<AsmsRoutineMonitor> getPagesListByCeateOrgId(Map<String, Object> map);

    Long getPageCountByCeateOrgId(Map<String, Object> params);

    List<Map<String, Object>> getTaskListByADS(Map<String, Object> map);

    Long getTaskCountByADS(Map<String, Object> params);
}