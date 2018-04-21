package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.generator.AsmsInspectionAssess;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.model.qry.EnforceLawPersonnelDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 11:17
 */
public interface SupervisionInspectionProvider extends BaseProvider{

    PageInfo getBaseInspectionAllList(Map<String,Object> map);

    PageInfo<List<Map<String, Object>>> getInspectionTaskList(Map<String, Object> params);

    PageInfo<List<Map<String, Object>>> getInspectionManAssessList(Map<String, Object> params);

    AsmsBaseInspection findBaseInspectionById(String id);

    PageInfo getEnforceLawPersonList(Map<String,Object> map);

    EnforceLawPersonnelDto findEnforceLawPersonById(String id);

    PageInfo getKhTaskList(Map<String,Object> map);

    AsmsInspectionTask findKhTaskById(String id);

    List<AsmsInspectionAssess> getKhPersonByTaskId(String id);

    PageInfo getAssessList(Map<String,Object> map);

    Long getKhRealCount(Map<String,Object> map);;
}
