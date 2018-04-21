/**
 *
 */
package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesTaskSample;

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
     * */
    List<AlesTaskSample> getSampleListByjcxxId(String jcxxId);


    /**
     * 根据样品唯一编码获取抽样单信息(仅针对已提交至检测系统数据)
     * */
    AlesTaskSample getSampleBySampleCode(String sampleCode);

    /**
     * 根据样品编码查询本地抽样单
     * */
    AlesTaskSample getLocalSampleBySampleCode(String sampleCode);

    /**
     * 根据监测对象ids获取所有抽样单
     * */
    PageInfo<List<Map<String, Object>>> getSampleListByObjIds(Map<String, Object> params);

    /**
     * 根据id获取抽样单信息
     */
    AlesTaskSample getSampleById(String id);
}
