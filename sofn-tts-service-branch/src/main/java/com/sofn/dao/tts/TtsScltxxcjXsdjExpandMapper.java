package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndUserInfo;
import com.sofn.model.tts.SamplingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 销售登记 ExpandMapper
 *
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjXsdjExpandMapper extends BaseExpandMapper {


    /**
     * 获取销售登记列表
     */
    List<Map<String, Object>> getPageInfo(Map<String, Object> map);


    /**
     * 获取销售登记数据条数
     */
    long getCount(Map<String, Object> map);

    TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id);


    /**
     * 针对采购，专门写的分页查询，数据对象增加了对应的销售企业名称，和客户的信息
     *
     * @param map
     * @return
     */
    List<TtsScltxxcjXsdjAndCustomer> queryPageForCg(Map<String, Object> map);

    long queryCountForCg(Map<String, Object> map);


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

    TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForUsual(String code);

    TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForGov(String code);

    List<Map<String, Object>> getInfoByRsCode(Map<String, Object> map);

    void updateComfigState(Map<String, Object> map);

    TtsScltxxcjXsdjAndCustomer getCgqrAndCustomerById(Map<String, Object> map);

    long existsTrace(Map<String, Object> map);

    /**
     * 根据入市追溯凭证获取抽样信息
     *
     * @param code
     * @return
     */
    SamplingInfo getSamplingInfoByTraceProof(@Param("code") String code);

    List<Map<String,Object>> querySource(@Param("id") String id);

    int getBaseBusinessStatusByEntityIdCode(String entityIdCode);

    int getDownBusinessStatusByEntityIdCode(String entityIdCode);
}
