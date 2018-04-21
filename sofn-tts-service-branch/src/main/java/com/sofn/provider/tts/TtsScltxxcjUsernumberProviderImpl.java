package com.sofn.provider.tts;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.TtsScltxxcjUsernumberMapper;
import com.sofn.dao.tts.TtsScltxxcjUsernumberExpandMapper;
import com.sofn.model.generator.TtsScltxxcjUsernumber;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/18.
 */
@DubboService(interfaceClass = TtsScltxxcjUsernumberProvider.class)
public class TtsScltxxcjUsernumberProviderImpl extends BaseProviderImpl<TtsScltxxcjUsernumber> implements TtsScltxxcjUsernumberProvider{

    @Autowired
    private TtsScltxxcjUsernumberMapper ttsScltxxcjUsernumberMapper;
    @Autowired
    private TtsScltxxcjUsernumberExpandMapper ttsScltxxcjUsernumberExpandMapper;

    @Override
    public Integer getUsernumByEntityCode(String entityCode) {
        return ttsScltxxcjUsernumberExpandMapper.getUsernumByEntityCode(entityCode);
    }

    @Override
    public void updateUsernumByEntityCode(Map<String, Object> map) {
        map.put("id", StringUtils.getUUIDString());
//        map.put("entityCode","");
//        map.put("createBy","");
//        map.put("updateBy","");
//        Integer ss = ttsScltxxcjUsernumberExpandMapper.getUsernumByEntityCode(map.get("entityCode").toString());
        ttsScltxxcjUsernumberExpandMapper.updateUsernumByEntityCode(map);
    }
}
