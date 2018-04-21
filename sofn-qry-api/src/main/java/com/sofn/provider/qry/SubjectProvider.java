package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.*;
import com.sofn.model.qry.TtsScltxxcjRegiterDto2;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月17日 下午 2:41
 */
public interface SubjectProvider extends BaseProvider{

    PageInfo getSubjSuperviseList(Map<String,Object> map);

    PageInfo getSubjEnforceLawList(Map<String, Object> map);

    PageInfo getSubjDetectionList(Map<String, Object> map);

    /**
     * 监管系统主体管理里生产经营主体信息专用
     * 条件跟上面的getEntityPageInfoByCondition一样，多一个不良记录badRecord
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiterDto2> getSubjEntList(Map<String, Object> map);

    /**
     * getSubjEntList列表总数
     * @param map
     * @return
     */
    long getSubjEntTotal(Map<String, Object> map);

    PageInfo<TtsScltxxcjUserChangeRecord> getPageInfo(Map<String, Object> map);

    PageInfo getAsmsSubjEntBadrecordByIdList(Map<String,Object> map);

    AsmsSubjSupervise findSubjSuperviseById(String id);

    AsmsSubjEnforceLaw findSubjEnforceLawById(String id);

    AsmsSubjDetection findSubjDetectionById(String id);

    TtsScltxxcjRegiterDto2 findSubjEntById(String id);

    TtsScltxxcjRegiterDto2 findSubjEntByEntityIdCode(Map<String,Object> map);

    AsmsSubjEntTemp findSubjEntTempById(String id);

    PageInfo getChangeListBySvId(Map<String,Object> map);

    PageInfo getChangeListByElId(Map<String, Object> map);

    PageInfo getChangeListByDtId(Map<String,Object> map);

    AsmsSubjSvChange findSubjSvChangeById(String id);

    AsmsSubjElChange findSubjElChangeById(String id);

    AsmsSubjDtChange findSubjDtChangeById(String id);

    /**
     * 根据主体码获取产品列表
     * @param map
     * @return
     */
    PageInfo getProductListByEntityIdcode(Map<String,Object> map);

    /**
     * 根据主体码获取基地列表
     * @param map
     * @return
     */
    PageInfo getSubjEntBaseByEntityIdcode(Map<String,Object> map);
    /**
     * 根据主体码获取批次列表
     */
    PageInfo getBathByEntityIdcode(Map<String,Object> map);
    /**
     * 通过id查询合成批次列表
     */
    PageInfo getHcBathById(Map<String,Object> map);
    /**
     * 根据主体码获取销售历史列表
     */
    PageInfo getSaleByEntityIdcode(Map<String,Object> map);
    /**
     * 根据ID获取单条销售登记数据及对应的客户信息信息（销售历史）
     */
    TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id);

    TtsScltxxcjXsthAndCustomer queryByXsjlId(String id);

    /**
     * 根据主体码查询采购列表
     * @param map
     * @return
     */
    PageInfo<TtsScltxxcjXsdjAndCustomer>  getSubjCgglListByEntityIdcode(Map<String, Object> map);
    /**
     * 根据主体码查询销售打印列表
     * @param map
     * @return
     */
    PageInfo  getSalePrintListByEntityIdcode(Map<String, Object> map);
    /**
     * 根据主体码查询库存打印列表
     * @param map
     * @return
     */
    PageInfo  getStockPrintListByEntityIdcode(Map<String, Object> map);
    /**
     * 根据主体码查询屠宰信息列表
     * @param map
     * @return
     */
    PageInfo  getSlaughterListByEntityIdcode(Map<String, Object> map);

    /**
     * 根据条件查询主体和批次总数
     */
    PageInfo getSubjEntAndBathList(Map<String, Object> map);

    /**
     * 根据条件查询主体和交易信息
     */
    PageInfo getSubjEntAndTransactionInfoList(Map<String, Object> map);

    /**
     *根据主体码查询主体交易记录
     */
    PageInfo getTransactionInfoListByEntityIdcode(Map<String, Object> map);

    /**
     * 根据条件查询主体和打印信息
     * @param map
     * @return
     */
    PageInfo getSubjEntAndPrintInfoList(Map<String, Object> map);

}
