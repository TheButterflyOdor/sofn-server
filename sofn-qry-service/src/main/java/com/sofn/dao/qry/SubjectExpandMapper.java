package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.*;
import com.sofn.model.qry.TtsScltxxcjRegiterDto2;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月17日 下午 2:45
 */
@MyBatisDao
public interface SubjectExpandMapper extends BaseExpandMapper{

    List<AsmsSubjSupervise> getSubjSuperviseList(Map<String,Object> map);

    long getSubjSuperviseCount(Map<String,Object> map);

    List<AsmsSubjEnforceLaw> getSubjectEnforceLawList(Map<String,Object> map);

    long getSubjectEnforceLawCount(Map<String,Object> map);

    List<AsmsSubjDetection> getSubjDetectionList(Map<String, Object> map);

    long getSubjDetectionCount(Map<String, Object> map);

    /**
     * 监管系统主体管理里生产经营主体信息专用
     * 条件跟上面的getEntityPageInfoByCondition一样，多一个不良记录badRecord
     * @param map
     * @return
     */
    List<TtsScltxxcjRegiterDto2> getSubjEntListNew(Map<String, Object> map);
    /**
     * getSubjEntList列表总数
     * @param map
     * @return
     */
    long getSubjEntTotal(Map<String, Object> map);

    /**
     *  获取注册主体信息表变更记录表列表
     */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);

    /**
     *  获取注册主体信息表变更记录表数据条数
     */
    long getCount(Map<String, Object> map);

    List<Map<String,Object>> findSubjEntBadrecodeById(Map<String,Object> map);

    long getSubjEntBadrecordByIdCount(Map<String,Object> map);

    AsmsSubjSupervise findSubjSuperviseById(String id);

    AsmsSubjEnforceLaw findSubjEnforceLawById(String id);

    AsmsSubjDetection findSubjDetectionById(String id);

    AsmsSubjEntTemp findSubjEntTempById(String id);

    TtsScltxxcjRegiterDto2 findSubjEntById(String id);

    TtsScltxxcjRegiterDto2 findSubjEntByEntityIdCode(Map<String, Object> map);

    List<Map<String,Object>> getChangeListBySvId(Map<String,Object> map);

    long getChangeCountBySvId(Map<String,Object> map);

    List<Map<String,Object>> getChangeListByElId(Map<String, Object> map);

    long getChangeCountByElId(Map<String, Object> map);

    List<Map<String,Object>> getChangeListByDtId(Map<String, Object> map);

    long getChangeCountByDtId(Map<String, Object> map);

    AsmsSubjSvChange findSubjSvChangeById(String id);

    AsmsSubjElChange findSubjElChangeById(String id);

    AsmsSubjDtChange findSubjDtSvChangeById(String id);

    List<Map<String,Object>> getProductListByEntityIdcode(Map<String, Object> map);

    Long getProductCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getSubjEntBaseByEntityIdcode(Map<String, Object> map);

    Long getSubjEntBaseCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getBathByEntityIdcode(Map<String, Object> map);

    Long getBathCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getHcBathById(Map<String, Object> map);

    Long getHcCountBathById(Map<String, Object> map);

    List<Map<String,Object>> getSaleByEntityIdcode(Map<String, Object> map);

    Long getSaleCountByEntityIdcode(Map<String, Object> map);

    TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id);

    TtsScltxxcjXsthAndCustomer queryByXsjlId(String id);

    List<TtsScltxxcjXsdjAndCustomer> getSubjCgglListByEntityIdcode(Map<String, Object> map);

    Long getSubjCgglCountListByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getSalePrintListByEntityIdcode(Map<String, Object> map);

    Long getSalePrintCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getStockPrintListByEntityIdcode(Map<String, Object> map);

    Long getStockPrintCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getSlaughterListByEntityIdcode(Map<String, Object> map);

    Long getSlaughterCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getSubjEntAndBathList(Map<String, Object> map);

    Long getSubjEntAndBathCount(Map<String, Object> map);

    List<Map<String,Object>> getSubjEntAndTransactionInfoList(Map<String, Object> map);

    Long getSubjEntAndTransactionInfoCount(Map<String, Object> map);

    List<Map<String,Object>> getTransactionInfoListByEntityIdcode(Map<String, Object> map);

    Long getTransactionInfoCountByEntityIdcode(Map<String, Object> map);

    List<Map<String,Object>> getSubjEntAndPrintInfoList(Map<String, Object> map);

    Long getSubjEntAndPrintInfoCount(Map<String, Object> map);


}
