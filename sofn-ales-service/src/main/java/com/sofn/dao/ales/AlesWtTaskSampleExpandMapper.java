package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AlesWtTaskSample;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AlesWtTaskSampleExpandMapper extends BaseExpandMapper {
    //得到集合
    List<Map<String,Object>> getList(Map<String, Object> map);
    //查询集合条数
    Long getCount(Map<String, Object> params);
    //查询集合
    List<AlesWtTaskSample> getSampleListByWtTaskId(AlesWtTaskSample ale);

    boolean syncIsTrue(AlesWtTaskSample ale);
    //查询单个对象
    AlesWtTaskSample getSampleBySampleCode(AlesWtTaskSample ale);
    //查询单个对象
    AlesWtTaskSample getLocalSampleBySampleCode(AlesWtTaskSample ale);
}