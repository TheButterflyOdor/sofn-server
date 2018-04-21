package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjSlaStorExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaStor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*	屠宰库存 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjSlaStorProvider.class)
public class TtsScltxxcjSlaStorProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaStor> implements TtsScltxxcjSlaStorProvider {

    @Autowired
    private TtsScltxxcjSlaStorExpandMapper ttsScltxxcjSlaStorExpandMapper;

    public PageInfo<TtsScltxxcjSlaStor> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaStorExpandMapper.getPageInfo(map);
        long count = ttsScltxxcjSlaStorExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    @Override
    public List<TtsScltxxcjSlaStor> getSphcByIds(Map<String, Object> map) {
        List<TtsScltxxcjSlaStor> list = ttsScltxxcjSlaStorExpandMapper.getSphcByIds(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCheckProduct(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = ttsScltxxcjSlaStorExpandMapper.getCheckProduct(map);
        return list;
    }
    public PageInfo<TtsScltxxcjSlaStor> getStorPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaStorExpandMapper.getStorPageInfo(map);
        long count = ttsScltxxcjSlaStorExpandMapper.getStorCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    public PageInfo<TtsScltxxcjSlaStor> getStor(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaStorExpandMapper.getStor(map);
        long count = ttsScltxxcjSlaStorExpandMapper.getStorC(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public void updateProductStoreCount(Map<String, Object> map) {
        ttsScltxxcjSlaStorExpandMapper.updateProductStoreCount(map);
    }

    @Override
    public void updateProductStoreFreezeCount(Map<String, Object> map) {
        ttsScltxxcjSlaStorExpandMapper.updateProductStoreFreezeCount(map);
    }

    @Override
    public void updateStoreCountForPurchase(String id) {
        ttsScltxxcjSlaStorExpandMapper.updateStoreCountForPurchase(id);
    }
}
