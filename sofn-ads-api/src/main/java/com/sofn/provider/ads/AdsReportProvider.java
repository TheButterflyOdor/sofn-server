package com.sofn.provider.ads;

import com.sofn.model.generator.AdsCheckInfo;
import com.sofn.model.generator.AdsMonitorTask;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 报表接口
 * @author yangran
 */
public interface AdsReportProvider {

    /**
     * 查询抽样统计环节报表数据
     * @param map
     * @return
     */
    List<Map<String,Object>> getSamplingLinkReportList(Map<String,Object> map);

    /**
     * 查询检测统计环节报表数据
     * @param map
     * @return
     */

    List<Map<String,Object>> getCheckLinkReportList(Map<String, Object> map);

    /**
     * 查询检测统计对象报表数据
     * @param map
     * @return
     */
    PageInfo<Map<String,Object>> getCheckObjectReportPageInfo(Map<String, Object> map);

    /**
     * 查询抽样统计地区报表分页数据
     * @param map
     * @return
     */
   PageInfo<Map<String,Object>> getSamplingAreaReportPageInfo(Map<String, Object> map);

    /**
     * 查询检测地区、对象报表分页数据
     * @param map
     * @return
     */
    PageInfo<Map<String,Object>> getCheckAreaObjReportPageInfo(Map<String, Object> map);

    /**
     * 查询检测统计地区报表分页数据
     * @param map
     * @return
     */
    PageInfo<Map<String,Object>> getCheckAreaReportPageInfo(Map<String, Object> map);

    /**
     * 查询抽样统计机构报表分页数据
     * @param map
     * @return
     */
    PageInfo<Map<String,Object>> getSamplingOrganReportPageInfo(Map<String, Object> map);

    /**
     * 查询检测统计机构报表分页数据
     * @param map
     * @return
     */
    PageInfo<Map<String,Object>> getCheckOrganReportPageInfo(Map<String, Object> map);

    /**
     * 根据条件查询抽样地区或机构报表数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<Map<String, Object>> getSamplingTypeListByCondition(Map<String, Object> map);

    /**
     * 根据条件查询检测地区或机构报表数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCheckTypeListByCondition(Map<String, Object> map);

    /**
     * 根据条件查询检测对象报表数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCheckObjectListByCondition(Map<String, Object> map);

    /**
     * 根据条件查询检测地区,对象报表数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCheckAreaObjListByCondition(Map<String, Object> map);


}
