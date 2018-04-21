package com.sofn.provider.tts;


import com.sofn.core.base.BaseProvider;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.model.generator.TempDemo;
import com.sofn.model.tts.SamplingInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public interface QueryInfoProvider extends BaseProvider<TempDemo> {
    /**
     * 根据用户类型和传入的码查询相关信息
     *
     * @param userType
     * @param code
     * @return List集合
     */
    List<Map<String, Object>> queryInfoByCode(String userType, String code);

    /**
     * 根据追溯码获取抽样信息
     *
     * @param code
     * @return
     */
    SamplingInfo getSamplingInfoByTraceCode(String code);

    /**
     * 根据批次码获取抽样信息
     *
     * @param code
     * @return
     */
    SamplingInfo getSamplingInfoByBatchCode(String code);

    /**
     * 根据入市追溯凭证获取抽样信息
     *
     * @param code
     * @return
     */
    SamplingInfo getSamplingInfoByTraceProof(String code);

    /**
     * 根据追溯码获取信息
     *
     * @param code 追溯、批次、入市凭证
     * @return
     */
    Map<String, Object> getInfoByTraceCode(String code) throws IllegalParameterException, DataParseException;
}
