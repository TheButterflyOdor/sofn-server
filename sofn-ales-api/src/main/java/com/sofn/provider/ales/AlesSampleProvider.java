package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesSample;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/10/18.
 */
public interface AlesSampleProvider extends BaseProvider<AlesSample> {
    //添加抽样信息
    public int addAlesSample(AlesSample alesSample);
    //根据相关条件查询机构变更历史列表
    public PageInfo getAlesSampleList(Map<String, Object> map);

    //获取委托检测任务-某单位某一年度某一行业的最大样品编码
    List<Map<String,Object>> getWtjcMaxSampleCode(Map<String, Object> map);
    //获取监督抽查任务-某单位某一年度某一行业的最大样品编码
    List<Map<String,Object>> getJdccMaxSampleCode(Map<String, Object> map);
    //根据抽样单id获取检测信息
    String getCheckInfoIdBySampleId(String sampleId);
}
