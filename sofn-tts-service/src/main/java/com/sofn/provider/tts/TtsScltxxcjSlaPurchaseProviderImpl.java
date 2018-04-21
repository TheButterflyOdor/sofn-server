package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjSlaPurchaseMapper;
import com.sofn.dao.tts.TtsScltxxcjSlaPurchaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaPurchase;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	屠宰产品采购记录 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjSlaPurchaseProvider.class)
public class TtsScltxxcjSlaPurchaseProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaPurchase> implements TtsScltxxcjSlaPurchaseProvider {

    @Autowired
    private TtsScltxxcjSlaPurchaseExpandMapper ttsScltxxcjSlaPurchaseExpandMapper;

    public PageInfo<TtsScltxxcjSlaPurchase> getPurchasePageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaPurchaseExpandMapper.getPageInfo(map);
        long count = ttsScltxxcjSlaPurchaseExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}
