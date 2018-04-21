package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 */
@MyBatisDao
public interface AlesSampleExpandMapper extends BaseExpandMapper {
    //获取条件数据
    List<Map<String,Object>> getAlesSampleAllList(Map<String, Object> map);
    //获取满足条件的记录数
    long getAlesSampleAllCount(Map<String, Object> map);
    //获取委托检测任务-某单位某一年度某一行业的最大样品编码
    List<Map<String,Object>> getWtjcMaxSampleCode(Map<String, Object> map);
    //获取监督抽查任务-某单位某一年度某一行业的最大样品编码
    List<Map<String,Object>> getJdccMaxSampleCode(Map<String, Object> map);
    //更具抽样单id获取信息
    String getCheckInfoIdBySampleId(String sampleId);
}
