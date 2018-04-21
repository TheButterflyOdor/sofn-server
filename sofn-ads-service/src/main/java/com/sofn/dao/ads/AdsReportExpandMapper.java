package com.sofn.dao.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsMonitorTask;

import java.util.List;
import java.util.Map;

/**
 * 报表接口
 * @author yangran
 */
@MyBatisDao
public interface AdsReportExpandMapper extends BaseExpandMapper {

    /**
     * 查询抽样环节报表数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getSamplingLinkReportList(Map<String, Object> map);

    /**
     * 查询检测环节报表数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getCheckLinkReportList(Map<String, Object> map);

    /**
     * 查询抽样地区报表数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getSamplingAreaReportList(Map<String, Object> map);

    /**
     *  查询抽样地区报表数据list条数
     *  @author TC
     */
    long getCountSamplingAreaReport(Map<String, Object> map);

    /**
     * 查询检测对象统计count
     * @param map
     * @return
     */
    int getCheckObjectReportCount(Map<String, Object> map);

    /**
     * 查询检测对象统计list
     * @param map
     * @return
     */
    List<Map<String,Object>> getCheckObjectReportList(Map<String, Object> map);

    /**
     * 查询检测地区、对象统计count
     * @param map
     * @return
     */
    int getCheckAreaObjCount(Map<String, Object> map);

    /**
     * 查询检测地区、对象统计list
     * @param map
     * @return
     */
    List<Map<String,Object>> getCheckAreaObjList(Map<String, Object> map);

    /**
     * 查询检测地区报表数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getCheckAreaReportList(Map<String, Object> map);

    /**
     *  查询检测地区报表数据list条数
     *  @author TC
     */
    long getCountCheckAreaReport(Map<String, Object> map);

    /**
     * 查询抽样机构报表数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getSamplingOrganReportList(Map<String, Object> map);

    /**
     *  查询抽样机构报表数据list条数
     *  @author TC
     */
    long getCountSamplingOrganReport(Map<String, Object> map);

    /**
     * 查询检测机构报表数据list
     * @param map
     * @return
     */
    List<Map<String,Object>> getCheckOrganReportList(Map<String, Object> map);

    /**
     *  查询检测机构报表数据list条数
     *  @author TC
     */
    long getCountCheckOrganReport(Map<String, Object> map);

    /**
     *  导出抽样地区或机构表
     *  @author TC
     */
    List<Map<String, Object>> getSamplingTypeListByCondition(Map<String, Object> map);

    /**
     *  导出检测地区或机构表
     *  @author TC
     */
    List<Map<String, Object>> getCheckTypeListByCondition(Map<String, Object> map);

    /**
     *  导出检测对象表
     *  @author TC
     */
    List<Map<String, Object>> getCheckObjectListByCondition(Map<String, Object> map);

    /**
     *  导出检测地区,对象表
     *  @author TC
     */
    List<Map<String, Object>> getCheckAreaObjListByCondition(Map<String, Object> map);

}
