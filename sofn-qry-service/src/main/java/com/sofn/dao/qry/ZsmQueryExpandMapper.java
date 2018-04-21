package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjjlAndCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by czx on 2017/3/21.
 */
@MyBatisDao
public interface ZsmQueryExpandMapper extends BaseExpandMapper {
    /*采购管理*/

    /**
     * 根据追溯码查询采购管理信息
     *
     * @param map
     */
    TtsScltxxcjCgglAndCustomer getCgglForZsm(Map<String, Object> map);

    /**
     * 当前登录用户在采购管理中是否能查询到对应的追溯数据
     *
     * @param map
     * @return
     */
    long zsmIsCgOrXs(Map<String, Object> map);
    /*产品批次合成*/

    /**
     * 通过批次码查询合成前信息
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getInfoForNewPc(Map<String, Object> map);

    long getCountForNewPc(Map<String, Object> map);

    /*生产管理*/
    Map<String, Object> getBaseForPc(Map<String, Object> map);
    /*销售记录*/

    /**
     * 追溯信息查询分页
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> pageForZsm(Map<String, Object> map);

    long pageForCountZsm(Map<String, Object> map);

    /**
     * 追溯查询，本级是销售
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getXsbaseZsm(Map<String, Object> map);

    long getXsbaseZsmCount(Map<String, Object> map);

    /**
     * 批次查询得到zsm查询销售来源
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getPcToUpInfo(Map<String, Object> map);

    long getPcToUpCount(Map<String, Object> map);

    /**
     * 通过PC码查询销售记录
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getInfoforpc(Map<String, Object> map);

    long getCountforpc(Map<String, Object> map);

    /**
     * 产品合成后流向
     *
     * @param pc
     * @return
     */
    Map<String, Object> getHclx(Map<String, Object> pc);
    /*销售登记*/

    /**
     * 通过追溯码查询 销售登记，产品从哪里来，到哪里去
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjXsdjAndCustomer> getXsdjForZsm(Map<String, Object> map);

    /**
     * 当销售是本级的时候，使用查询上级追溯信息
     *
     * @param map
     * @return
     */
    List<Map<String, Object>> getXsUpZsm(Map<String, Object> map);

    long getXsUpZsmCount(Map<String, Object> map);

    long existsTrace(Map<String, Object> map);


    /************追溯查询************/
    /**
     * 是否存在追溯码
     *
     * @param traceCode 追溯码
     * @return
     */
    public long existTraceCode(@Param("traceCode") String traceCode);

    /**
     * （采购）本级
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjCgglAndCustomer getCgglByTraceCode(@Param("traceCode") String traceCode);

    /**
     * （采购）上游
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjXsdjAndCustomer getXsdjByTraceCode(@Param("traceCode") String traceCode);

    /**
     * （采购）下游
     *
     * @param traceCode 追溯码
     * @return
     */
    public List<TtsScltxxcjXsdjAndCustomer> getXsdjsByTraceCode(@Param("traceCode") String traceCode);

    /**
     * （销售）本级
     *
     * @param traceCode 追溯码
     * @return
     */
    public List<TtsScltxxcjXsdjjlAndCustomer> getXsdjjlsByTraceCodex(@Param("traceCode") String traceCode);

    /**
     * （销售）上游
     *
     * @param traceCode 追溯码
     * @return
     */
    public List<TtsScltxxcjXsdjAndCustomer> getXsdjByTraceCodex(@Param("traceCode") String traceCode);

    /**
     * 通过追溯码获取采购主体信息
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjCgglAndCustomer getCgSubjectInfoByTraceCode(@Param("traceCode") String traceCode);

    /**
     * 通过追溯码获取销售主体信息
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjXsdjAndCustomer getXsSubjectInfoByTraceCode(@Param("traceCode") String traceCode);

    /**
     * 通过追溯码获取生产管理记录
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjScgl getProductionProductInfo(@Param("traceCode") String traceCode);

    /**
     * 通过追溯码获取合成后产品登记信息
     *
     * @param traceCode 追溯码
     * @return
     */
    public TtsScltxxcjScgl getSynthesisProductInfo(@Param("traceCode") String traceCode);

    /**
     * 通过批次码获取采购对象(流通)
     *
     * @param batchCode 批次码
     * @return
     */
    public List<Map<String, Object>> getCgSubjectInfoByBatchCode(@Param("batchCode") String batchCode);

    /**
     * 通过批次码获取入市对象
     *
     * @param batchCode 批次码
     * @return
     */
    public List<Map<String, Object>> getRsSubjectInfoByBatchCode(@Param("batchCode") String batchCode);

    /**
     * 通过客户id查询客户信息（入市对象）
     *
     * @param customerIds 客户ids
     * @return
     */
    public List<Map<String, Object>> getRsSubjectInfoByIds(@Param("customerIds") List<String> customerIds);

    /**
     * 通过主体身份码查询主体信息（流通对象）
     *
     * @param entityIdCode 主体码
     * @return
     */
    public Map<String, Object> getSubjectInfoByEntityIdCode(@Param("entityIdCode") String entityIdCode);

    /**
     * 通过销售登记id获取生产管理信息
     *
     * @param saleRegistrationId 销售登记ID
     * @return
     */
    public List<TtsScltxxcjScgl> getScglInfos(@Param("saleRegistrationId") String saleRegistrationId);

    /**
     * 通过生产Id获取本批次合成前产品
     *
     * @param productionId 生产管理ID
     * @return
     */
    public List<TtsScltxxcjScgl> getHcScglInfos(@Param("productionId") String productionId);

    /**
     * 是否存在批次码
     *
     * @param batchCode 批次码
     * @return
     */
    public long existBatchCode(@Param("batchCode") String batchCode);
}
