package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;

import java.util.List;
import java.util.Map;

/**
 * Created by czx on 2017/3/21.
 */
public interface ZsmQueryProvider extends BaseProvider {
    /*采购管理*/

    /**
     * 根据追溯码，查询采购管理记录
     *
     * @param map
     * @return
     */
    public TtsScltxxcjCgglAndCustomer getCgglForZsm(Map<String, Object> map);

    /**
     * 通过追溯码和主体身份码，查询是否有对应的采购记录
     *
     * @param map
     * @return
     */
    public long zsmIsCgOrXs(Map<String, Object> map);
    /*产品批次合成*/

    /**
     * 通过pc号，查询产品数据来源
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> getInfoForNewPc(Map<String, Object> map);
    /*生产管理*/

    /**
     * 通过批次码查询信息
     *
     * @param map
     * @return
     */
    Map<String, Object> getBaseForPc(Map<String, Object> map);
    /*销售记录*/

    /**
     * 通过销售明细数据
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> pageForZsm(Map<String, Object> map);

    /**
     * 追溯查询，销售是本级
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> getXsbaseZsm(Map<String, Object> map);

    /**
     * 通过产品批次码查询对应的销售明细信息
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> getPcToUpInfo(Map<String, Object> map);

    /**
     * 通过产品批次码查询对应的销售明细信息
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> getInfoforpc(Map<String, Object> map);

    /**
     * 产品合成后流向
     *
     * @param pc
     * @return
     */
    public Map<String, Object> getHclx(Map<String, Object> pc);
    /*销售登记*/

    /**
     * 通过追溯码查询 销售登记，产品从哪里来，到哪里去
     *
     * @param map
     * @return
     */
    public List<TtsScltxxcjXsdjAndCustomer> getXsdjForZsm(Map<String, Object> map);

    /**
     * 销售是本级，查询上级销售信息
     *
     * @param map
     * @return
     */
    public PageInfo<Map<String, Object>> getXsUpZsm(Map<String, Object> map);

    long existsTrace(Map<String, Object> map);


    /************追溯查询************/

    /**
     * 通过追溯码获取采购主体信息
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjCgglAndCustomer getCgSubjectInfoByTraceCode(String traceCode);

    /**
     * 通过追溯码获取销售主体信息
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjXsdjAndCustomer getXsSubjectInfoByTraceCode(String traceCode);

    /**
     * 通过追溯码获取生产管理记录
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjScgl getProductionProductInfo(String traceCode);

    /**
     * 通过追溯码获取合成后产品登记信息
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjScgl getSynthesisProductInfo(String traceCode);

    /**
     * 通过批次码获取采购对象(流通)
     *
     * @param batchCode 批次码
     * @return
     */
    public List<Map<String, Object>> getCgSubjectInfoByBatchCode(String batchCode);

    /**
     * 通过批次码获取入市对象
     *
     * @param batchCode 批次码
     * @return
     */
    public List<Map<String, Object>> getRsSubjectInfoByBatchCode(String batchCode);

    /**
     * 通过客户id查询客户信息（入市对象）
     *
     * @param customerIds 客户ids
     * @return
     */
    public List<Map<String, Object>> getRsSubjectInfoByIds(List<String> customerIds);

    /**
     * 通过主体身份码查询主体信息（流通对象）
     *
     * @param entityIdCode 主体码
     * @return
     */
    public Map<String, Object> getSubjectInfoByEntityIdCode(String entityIdCode);

    /**
     * 通过销售登记id获取生产管理信息
     *
     * @param saleRegistrationId 销售登记ID
     * @return
     */
    public List<TtsScltxxcjScgl> getScglInfos(String saleRegistrationId);

    /**
     * 通过生产Id获取本批次合成前产品
     *
     * @param productionId 生产管理ID
     * @return
     */
    public List<TtsScltxxcjScgl> getHcScglInfos(String productionId);

    /**
     * 通过追溯码查询销售关系
     *
     * @param traceCode 追溯码
     * @return ForceData {@link com.sofn.model.qry.ForceData}
     * @throws IllegalParameterException
     * @throws DataParseException
     */
    public ForceData findSaleRelationByTraceCode(String traceCode) throws IllegalParameterException, DataParseException;

    /**
     * 通过批次码查询销售关系
     *
     * @param batchCode 批次码
     * @return ForceData {@link com.sofn.model.qry.ForceData}
     * @throws IllegalParameterException
     * @throws DataParseException
     */
    public ForceData findSaleRelationByBatchCode(String batchCode) throws IllegalParameterException, DataParseException;

    /**
     * 通过追溯码查询销售关系
     *
     * @param traceCode
     * @return
     */
    public Map<String, SaleInfoData> getSubjectSaleInfoByTraceCode(String traceCode);
}
