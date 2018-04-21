/**
 *
 */
package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AlesWtObjectCriterion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AlesTaskSampleProvider extends BaseProvider<AlesTaskSample> {

    PageInfo<AlesTaskSample> list(Map<String, Object> params);

    /**
     * 根据监测信息id获取下面所有抽样单对象
     */
    List<AlesTaskSample> getSampleListByjcxxId(String jcxxId);

    /**
     * 根据监测信息id,样品码获取下面抽样单对象
     */
    List<AlesTaskSample> getSampleListByjcxxIds(String jcxxId,String code);
    /**
     * 更改抽样单信息状态值 0：未同步 1：已同步
     */
    @Transactional
    boolean syncIsTrue(String jcxxId, String state);

    /**
     * 根据id和样品编码更新指定抽样单状态值 0：未同步 1：已同步
     * 2018年3月19日11:37:37
     * @param jcxxId
     * @param state
     * @param code
     * @return
     */
    @Transactional
    boolean sampleUpdate(String jcxxId, String state,String code,String id);

    @Transactional
    long judgeisSync(String organTaskId);
    /**
     * 根据样品唯一编码获取抽样单信息(仅针对已提交至检测系统数据)
     */
    AlesTaskSample getSampleBySampleCode(String sampleCode);

    /**
     * 根据样品编码查询本地抽样单
     */
    AlesTaskSample getLocalSampleBySampleCode(String sampleCode);

    AlesTaskSample getLocaloleSampleBySampleCode(String sampleCode,String id);
    /**
     * 根据监测对象ids获取所有抽样单
     */
    PageInfo<List<Map<String, Object>>> getSampleListByObjIds(Map<String, Object> params);

    /**
     * 检测结果提交
     */
    @Transactional
    boolean sampleResult(String id, String result);

    /**
     * 根据样品编码获取检测项
     * @param sampleCode
     * @return
     */
    List<AlesWtObjectCriterion> getCriBySampleCode(String sampleCode);

    /**
     * 根据追溯码统计抽样单任务信息
     * @param params
     * @return
     */
    Long countSubjectinfoTraceabilityCode(Map<String, Object> params);
}
