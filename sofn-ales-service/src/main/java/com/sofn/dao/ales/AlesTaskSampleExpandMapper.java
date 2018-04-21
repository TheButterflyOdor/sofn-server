package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AlesTaskSample;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AlesTaskSampleExpandMapper extends BaseExpandMapper {
    //查询集合
    List<Map<String, Object>> getPagesList(Map<String, Object> map);
    //查询集合条数
    Long getPageCount(Map<String, Object> params);
    //查询抽样单集
    List<AlesTaskSample> getSampleListByjcxxId(AlesTaskSample ale);
    //查询抽样单
    List<AlesTaskSample> getSampleListByjcxxIds(AlesTaskSample ale);

    boolean syncIsTrue(AlesTaskSample ale);

    boolean sampleUpdate(AlesTaskSample ale);

    Long  judgeisSync(AlesTaskSample ale);
    Long  judgeisSyncs(AlesTaskSample ale);
    //根据样品编码获取
    AlesTaskSample getSampleBySampleCode(AlesTaskSample ale);

    AlesTaskSample getLocalSoleSampleBySampleCode(AlesTaskSample ale);

    AlesTaskSample getLocalSampleBySampleCode(AlesTaskSample ale);

    List<Map<String, Object>> getSampleListByTaskIdPagesList(Map<String, Object> map);

    Long getSampleListByTaskIdPageCount(Map<String, Object> params);

    boolean sampleResult(Map<String, Object> params);

    Long countSubjectinfoTraceabilityCode(Map<String, Object> params);
}