package com.sofn.provider.tts;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjCgglExpandMapper;
import com.sofn.dao.tts.TtsScltxxcjRegiterExpandMapper;
import com.sofn.dao.tts.TtsScltxxcjScglExpandMapper;
import com.sofn.dao.tts.TtsScltxxcjXsdjExpandMapper;
import com.sofn.model.generator.TempDemo;
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


    @Override
    public List<Map<String, Object>> queryInfoByCode(String userType, String code) {
        //判断传入的是什么类型的码，MainBodyIdentityCode：主体身份码，MainBodyUserCode：主体用户码，
        // ProductBatchCode：产品批次码，ProductTraceabilityCode：产品追溯码，IntoCityTraceability：入市追溯凭证
        String codeType = IdGenerator.getEncodingPropertie(code).toString().trim();
        //将参数封装入map集合中
        Map<String, Object> map= new HashMap<>();
        map.put("userType",userType.trim());
        map.put("code",code.trim());

        List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
        if(codeType.equals("MainBodyIdentityCode")){
            resultMap = ttsScltxxcjRegiterExpandMapper.getBodyInfoByCode(map);
            return resultMap;
        }else if(codeType.equals("MainBodyUserCode")){
            resultMap = ttsScltxxcjRegiterExpandMapper.getBodyInfoByUserCode(map);
            return resultMap;
        }else if(codeType.equals("ProductBatchCode")){
            resultMap = ttsScltxxcjScglExpandMapper.getInfoByPcCode(map);
            return resultMap;
        }else if(codeType.equals("ProductTraceabilityCode") && ttsScltxxcjCgglExpandMapper.isExists(code) > 0){
            resultMap = ttsScltxxcjCgglExpandMapper.getInfoByTraceCode(map);
            return resultMap;
        }else if(codeType.equals("IntoCityTraceability")){
            resultMap = ttsScltxxcjXsdjExpandMapper.getInfoByRsCode(map);
            return resultMap;
        }else{
            return resultMap;
        }
    }
}
