package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesTaskSample;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AlesTaskSampleExpandMapper extends BaseExpandMapper {

    List<Map<String,Object>> getPagesList(Map<String, Object> map);

    Long getPageCount(Map<String, Object> params);

    List<AlesTaskSample> getSampleListByjcxxId(AlesTaskSample ale);

    AlesTaskSample getSampleBySampleCode(AlesTaskSample ale);

    AlesTaskSample getLocalSampleBySampleCode(AlesTaskSample ale);

    List<Map<String,Object>> getSampleListByTaskIdPagesList(Map<String, Object> map);

    Long getSampleListByTaskIdPageCount(Map<String, Object> params);
    AlesTaskSample selectByPrimaryKey(String id);
}