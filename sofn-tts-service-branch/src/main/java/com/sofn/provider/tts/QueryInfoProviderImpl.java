package com.sofn.provider.tts;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.*;
import com.sofn.model.generator.TempDemo;
import com.sofn.model.tts.SamplingInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
@DubboService(interfaceClass = QueryInfoProvider.class)
public class QueryInfoProviderImpl extends BaseProviderImpl<TempDemo> implements QueryInfoProvider {

    @Autowired
    private TtsScltxxcjRegiterExpandMapper ttsScltxxcjRegiterExpandMapper;

    @Autowired
    private TtsScltxxcjScglExpandMapper ttsScltxxcjScglExpandMapper;

    @Autowired
    private TtsScltxxcjXsdjExpandMapper ttsScltxxcjXsdjExpandMapper;

    @Autowired
    private TtsScltxxcjCgglExpandMapper ttsScltxxcjCgglExpandMapper;
    @Autowired
    private QueryInfoExpandMapper queryInfoExpandMapper;


    @Override
    public List<Map<String, Object>> queryInfoByCode(String userType, String code) {
        //判断传入的是什么类型的码，MainBodyIdentityCode：主体身份码，MainBodyUserCode：主体用户码，
        // ProductBatchCode：产品批次码，ProductTraceabilityCode：产品追溯码，IntoCityTraceability：入市追溯凭证
        String codeType = IdGenerator.getEncodingPropertie(code).toString().trim();
        //将参数封装入map集合中
        Map<String, Object> map = new HashMap<>();
        map.put("userType", userType.trim());
        map.put("code", code.trim());

        List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
        if (codeType.equals("MainBodyIdentityCode")) {
            resultMap = ttsScltxxcjRegiterExpandMapper.getBodyInfoByCode(map);
            return resultMap;
        } else if (codeType.equals("MainBodyUserCode")) {
            resultMap = ttsScltxxcjRegiterExpandMapper.getBodyInfoByUserCode(map);
            return resultMap;
        } else if (codeType.equals("ProductBatchCode")) {
            resultMap = ttsScltxxcjScglExpandMapper.getInfoByPcCode(map);
            return resultMap;
        } else if (codeType.equals("ProductTraceabilityCode") && ttsScltxxcjCgglExpandMapper.isExists(code) > 0) {
            resultMap = ttsScltxxcjCgglExpandMapper.getInfoByTraceCode(map);
            return resultMap;
        } else if (codeType.equals("IntoCityTraceability")) {
            resultMap = ttsScltxxcjXsdjExpandMapper.getInfoByRsCode(map);
            return resultMap;
        } else {
            return resultMap;
        }
    }

    /**
     * 根据追溯码获取抽样信息
     *
     * @param code
     * @return
     */
    @Override
    public SamplingInfo getSamplingInfoByTraceCode(String code) {
        SamplingInfo samplingInfo = ttsScltxxcjCgglExpandMapper.getSamplingInfoByTraceCode(code);
        if (samplingInfo == null) {
            return null;
        }
        getProEntity(samplingInfo, samplingInfo.getProductPc());
        return samplingInfo;
    }

    /**
     * 根据批次码获取抽样信息
     *
     * @param code
     * @return
     */
    @Override
    public SamplingInfo getSamplingInfoByBatchCode(String code) {
        SamplingInfo samplingInfo = ttsScltxxcjScglExpandMapper.getSamplingInfoByBatchCode(code);
        if (samplingInfo == null) {
            return null;
        }
        getProEntity(samplingInfo, samplingInfo.getProductPc());
        return samplingInfo;
    }

    /**
     * 根据入市追溯凭证获取抽样信息
     *
     * @param code
     * @return
     */
    @Override
    public SamplingInfo getSamplingInfoByTraceProof(String code) {
        SamplingInfo samplingInfo = ttsScltxxcjXsdjExpandMapper.getSamplingInfoByTraceProof(code);
        if (samplingInfo == null) {
            return null;
        }
        getProEntity(samplingInfo, samplingInfo.getProductPc());
        return samplingInfo;
    }

    /**
     * 根据追溯码获取信息
     *
     * @param code 追溯、批次、入市凭证
     * @return
     */
    @Override
    public Map<String, Object> getInfoByTraceCode(String code) throws IllegalParameterException, DataParseException {
        if (StringUtils.isBlank(code)) {
            throw new IllegalParameterException("参数不能为空.");
        }
        IdGenerator.TracingCodeType tracingCodeType = IdGenerator.getTracingCodeType(code);
        if (tracingCodeType == null) {
            throw new IllegalParameterException("参数非法.");
        }

        //批次码
        String batchCode = null;
        if (IdGenerator.TracingCodeType.circulate.equals(tracingCodeType)) {//追溯码
            batchCode = queryInfoExpandMapper.selectBatchCodeByTraceCode(code.trim());
        } else if (IdGenerator.TracingCodeType.production.equals(tracingCodeType)) {//批次
            batchCode = code.trim();
        } else {//入市凭证
            throw new IllegalParameterException("参数非法.");
        }

        if (StringUtils.isBlank(batchCode)) {
            return null;//无数据
        }

        //获取源头信息
        try {
            return findProductSouce(batchCode);
        } catch (Exception e) {
            throw new DataParseException(e);
        }
    }

    private void getProEntity(SamplingInfo samplingInfo, String code) {
        SamplingInfo obj1 = getProSubject(samplingInfo, code);
        samplingInfo.setProEnterpriseName(obj1.getProEnterpriseName());
        samplingInfo.setProAddress(obj1.getProAddress());
        samplingInfo.setProContactName(obj1.getProContactName());
        samplingInfo.setProContactPhone(obj1.getProContactPhone());
        samplingInfo.setProFaxNumber(obj1.getProFaxNumber());
        samplingInfo.setProZipcode(obj1.getProZipcode());
    }

    /**
     * 通过批次码获取生产主体
     * 如果进行过合成那么合成后的企业为生产主体
     *
     * @param code
     * @return
     */
    private SamplingInfo getProSubject(SamplingInfo samplingInfo, String code) {
        //找到合成产品
        List<SamplingInfo> obj = ttsScltxxcjScglExpandMapper.findHcByBatchCode(code);
        if (obj == null || obj.size() == 0) {//不是合成产品
            //获取上游
            List<SamplingInfo> obj1 = ttsScltxxcjScglExpandMapper.findUpByBatchCode(code);
            if (obj1 != null && obj1.size() != 0) {
                if (obj1.size() > 1) {
                    return obj1.get(0);//返回上一家
                } else {
                    return getProSubject(obj1.get(0), obj1.get(0).getProductPc());
                }
            } else {
                return samplingInfo;//返回自己
            }
        } else {
            return obj.get(0);//返回自己
        }
    }

    /**
     * 通过批次码找到生产源头信息
     * 如果进行过合成那么合成后的企业为生产主体
     *
     * @param code
     * @return
     */
    private Map<String, Object> findProductSouce(String code) throws Exception {
        int num = queryInfoExpandMapper.countHcNumByBatchCode(code);
        if (num > 0) {//合成
            return queryInfoExpandMapper.selectInfoByBatchCode(code);
        } else {
            List<String> batchCodes = queryInfoExpandMapper.selectParentBatchCodeByBatchCode(code);
            if (batchCodes != null && batchCodes.size() != 0) {//存在上家
                if (batchCodes.size() > 1) {//上家销售的是多批次同类产品
                    return queryInfoExpandMapper.selectInfoByBatchCode(batchCodes.get(0));
                } else {
                    return findProductSouce(batchCodes.get(0));
                }
            } else {
                return queryInfoExpandMapper.selectInfoByBatchCode(code);
            }
        }
    }
}
