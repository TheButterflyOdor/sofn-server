package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.qry.SaleInfoData;

import java.util.List;

/**
 * 追溯查询
 */
@MyBatisDao
public interface TraceQueryExpandMapper extends BaseExpandMapper {
    /**
     * 通过追溯码获取该企业信息
     *
     * @param traceCode
     * @return
     */
    SaleInfoData findByTraceCode(String traceCode);

    /**
     * 通过批次码获取该企业信息
     *
     * @param batchCode
     * @return
     */
    SaleInfoData findByBatchCode(String batchCode);

    /**
     * 通过批次码获取合成后企业信息
     *
     * @param batchCode
     * @return
     */
    SaleInfoData findHcByBatchCode(String batchCode);

    /**
     * 通过批次码获取入市企业信息
     *
     * @param batchCode
     * @return
     */
    List<SaleInfoData> findIntoCityByBatchCode(String batchCode);

    /**
     * 通过批次码获取流通企业信息
     *
     * @param batchCode
     * @return
     */
    List<SaleInfoData> findCirculationByBatchCode(String batchCode);

    /**
     * 通过批次码获取合成前企业信息
     *
     * @param batchCode
     * @return
     */
    List<SaleInfoData> findBeforeHcByBatchCode(String batchCode);

    /**
     * 通过批次码获取上游企业信息
     *
     * @param batchCode
     * @return
     */
    List<SaleInfoData> findUpByBatchCode(String batchCode);
}
