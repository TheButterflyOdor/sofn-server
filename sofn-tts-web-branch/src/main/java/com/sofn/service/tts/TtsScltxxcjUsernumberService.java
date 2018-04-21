package com.sofn.service.tts;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjUsernumber;
import com.sofn.provider.tts.TtsScltxxcjUsernumberProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/18.
 */
@Service
public class TtsScltxxcjUsernumberService extends BaseService<TtsScltxxcjUsernumberProvider,TtsScltxxcjUsernumber> {
    @DubboReference
    public void setTtsScltxxcjUsernumberProvider(TtsScltxxcjUsernumberProvider provider){
        this.provider = provider;
    }

    public Integer getUsernumByEntityCode(String entityCode){
        return provider.getUsernumByEntityCode(entityCode);
    }

    public void updateUsernumByEntityCode(Map<String, Object> map){
        provider.updateUsernumByEntityCode(map);
    }
}
