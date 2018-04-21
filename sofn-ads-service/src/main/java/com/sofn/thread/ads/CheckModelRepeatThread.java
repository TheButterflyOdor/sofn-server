package com.sofn.thread.ads;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.util.RedisUtil;
import com.sofn.dao.ads.AdsNewModelExpandMapper;
import com.sofn.model.generator.AdsCheckModelMapping;

import java.util.Map;

/**
 * describe:
 *
 * @author xuquan
 * @date 2018/04/10
 */
public class CheckModelRepeatThread implements Runnable {

    private Map<String, Object> queryParams;
    private AdsCheckModelMapping modelMapping;
    private AdsNewModelExpandMapper adsNewModelExpandMapper;

    public CheckModelRepeatThread(Map<String, Object> queryParams, AdsCheckModelMapping modelMapping, AdsNewModelExpandMapper adsNewModelExpandMapper) {
        this.queryParams = queryParams;
        this.modelMapping = modelMapping;
        this.adsNewModelExpandMapper = adsNewModelExpandMapper;
    }

    @Override
    public void run() {
        long count = adsNewModelExpandMapper.getConflict(queryParams);
        if (count == 0) {
            adsNewModelExpandMapper.saveModelConfig(modelMapping);
        } else {
            JSONObject jObject = (JSONObject) RedisUtil.get(modelMapping.getModelId());
            int result = jObject.getInteger("result");
            jObject.put("result", ++result);

            RedisUtil.set(modelMapping.getModelId(), jObject);

            modelMapping = null;
        }
    }
}
