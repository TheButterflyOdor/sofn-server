package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjSlaSaleMapper;
import com.sofn.dao.tts.TtsScltxxcjSlaSaleExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaCustomer;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	屠宰产品销售 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjSlaSaleProvider.class)
public class TtsScltxxcjSlaSaleProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaSale> implements TtsScltxxcjSlaSaleProvider {

    @Autowired
    private TtsScltxxcjSlaSaleExpandMapper ttsScltxxcjSlaSaleExpandMapper;

    public PageInfo<TtsScltxxcjSlaSale> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaSaleExpandMapper.getPageInfo(map);
        long count = ttsScltxxcjSlaSaleExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public TtsScltxxcjSlaCustomer getSaleAndCustomerById(String id) {
        return ttsScltxxcjSlaSaleExpandMapper.getSaleAndCustomerById(id);
    }

    @Override
    public PageInfo<TtsScltxxcjSlaCustomer> queryPageForPurchase(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaSaleExpandMapper.getPurchasePageInfo(map);
        long count = ttsScltxxcjSlaSaleExpandMapper.getPurchaseCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
