package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaCustomer;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;

import java.util.Map;

/**
 * 屠宰产品销售模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjSlaSaleProvider extends BaseProvider<TtsScltxxcjSlaSale> {

	PageInfo<TtsScltxxcjSlaSale> getPageInfo(Map<String, Object> map);


    TtsScltxxcjSlaCustomer getSaleAndCustomerById(String id);

    PageInfo<TtsScltxxcjSlaCustomer> queryPageForPurchase(Map<String, Object> queryParams);
}