package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjSlaProPchc;
import com.sofn.provider.tts.TtsScltxxcjSlaProPchcProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 屠宰后产品批次合成 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjSlaProPchcService extends BaseService<TtsScltxxcjSlaProPchcProvider, TtsScltxxcjSlaProPchc> {

    @DubboReference
    public void setTtsScltxxcjSlaProPchcProvider(TtsScltxxcjSlaProPchcProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjSlaProPchc bean, int pageNum, int pageSize, String productType, String productName) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("productName",productName);
        queryParams.put("productType",productType);
        return provider.getPageInfo(queryParams);
    }


    public int insertBySelect(Map<String, Object> map) {
        return provider.insertBySelect(map);
    }
}