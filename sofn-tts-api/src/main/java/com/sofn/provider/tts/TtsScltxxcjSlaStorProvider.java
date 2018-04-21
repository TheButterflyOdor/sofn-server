package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaStor;

import java.util.List;
import java.util.Map;

/**
 * 屠宰库存模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjSlaStorProvider extends BaseProvider<TtsScltxxcjSlaStor> {

	PageInfo<TtsScltxxcjSlaStor> getPageInfo(Map<String, Object> map);


    List<TtsScltxxcjSlaStor> getSphcByIds(Map<String, Object> map);

    List<Map<String,Object>> getCheckProduct(Map<String, Object> map);

    PageInfo<TtsScltxxcjSlaStor> getStorPageInfo(Map<String, Object> map);

    PageInfo<TtsScltxxcjSlaStor> getStor(Map<String, Object> map);

    void updateProductStoreCount(Map<String, Object> map);

    void updateProductStoreFreezeCount(Map<String, Object> map);

    void updateStoreCountForPurchase(String id);
}