package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesWtTaskSample;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
public interface AlesWtTaskSampleProvider extends BaseProvider<AlesWtTaskSample> {
    /**
     * 获取样品信息列表
     *
     * @param params
     * @return
     */
    PageInfo<AlesWtTaskSample> list(Map<String, Object> params);

    /**
     * 根据任务ID 添加样品信息
     *
     * @param alesWtTaskSample
     * @return
     */
    @Transactional
    int addAlesWtTaskSample(AlesWtTaskSample alesWtTaskSample);

    /**
     * 根据委托检测任务id得到抽样单列表
     *
     * @param jcxxId
     * @return
     */
    List<AlesWtTaskSample> getSampleListByWtTaskId(String jcxxId);

    /**
     * 更改抽样单信息状态值 0：未同步 1：已同步
     *
     * @param jcxxId
     * @param state
     * @return
     */
    @Transactional
    boolean syncIsTrue(String jcxxId, String state);

    /**
     * 根据样品唯一编码获取抽样单信息(仅针对已提交至检测系统数据)
     *
     * @param sampleCode
     * @return
     */
    AlesWtTaskSample getSampleBySampleCode(String sampleCode);

    /**
     * 根据样品编码查询本地抽样单
     */
    AlesWtTaskSample getLocalSampleBySampleCode(String sampleCode);
}
