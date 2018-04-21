package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjXsthMapper;
import com.sofn.dao.tts.TtsScltxxcjXsthExpandMapper;
import com.sofn.model.generator.TtsScltxxcjXsth;
import com.sofn.model.generator.TtsScltxxcjXsthAndCustomer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	销售退回 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjXsthProvider.class)
public class TtsScltxxcjXsthProviderImpl extends BaseProviderImpl<TtsScltxxcjXsth> implements TtsScltxxcjXsthProvider {

    @Autowired
    private TtsScltxxcjXsthExpandMapper ttsScltxxcjXsthExpandMapper;

    public PageInfo<TtsScltxxcjXsth> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjXsthExpandMapper.getPageInfo(map);
        long count = ttsScltxxcjXsthExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public TtsScltxxcjXsthAndCustomer queryByXsjlId(String id) {
        return ttsScltxxcjXsthExpandMapper.queryByXsjlId(id);
    }

    @Override
    public TtsScltxxcjXsthAndCustomer querySaleInfoById(String id) {
        return ttsScltxxcjXsthExpandMapper.querySaleInfoById(id);
    }

}
