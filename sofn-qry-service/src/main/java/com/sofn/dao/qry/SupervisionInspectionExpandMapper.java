package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.generator.AsmsInspectionAssess;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.model.qry.EnforceLawPersonnelDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:21
 */
@MyBatisDao
public interface SupervisionInspectionExpandMapper extends BaseExpandMapper{

    List<Map<String,Object>> getBaseInspectionAllList(Map<String, Object> map);

    long getBaseInspectionAllCount(Map<String, Object> map);

    List<Map<String,Object>> getInspectionTaskList(Map<String, Object> map);

    Long getInspectionTaskCount(Map<String, Object> params);

    List<Map<String,Object>> getInspectionManAssessList(Map<String, Object> map);

    Long getInspectionManAssessCount(Map<String, Object> params);

    long getInspectionManRealCount(Map<String, Object> params);

    AsmsBaseInspection findBaseInspectionById(String id);

    List<EnforceLawPersonnelDto> getEnforceLawPersonList(Map<String, Object> map);

    long getEnforceLawPersonCount(Map<String, Object> map);

    EnforceLawPersonnelDto findEnforceLawPersonById(@Param("id") String id);

    List<Map<String,Object>> getKhTaskList(Map<String, Object> map);

    long getKhTaskCount(Map<String, Object> map);

    AsmsInspectionTask findKhTaskById(@Param("id") String id);

    List<AsmsInspectionAssess> getKhPersonByTaskId(@Param("id") String id);

    List<Map<String,Object>> getAssessList(Map<String, Object> map);

    long getAssessCount(Map<String, Object> map);

    long getKhRealCount(Map<String, Object> map);

}
