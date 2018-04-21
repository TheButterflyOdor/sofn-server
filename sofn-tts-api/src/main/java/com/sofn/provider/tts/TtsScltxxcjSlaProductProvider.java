package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaProduct;

import java.util.List;
import java.util.Map;

/**
 * 屠宰后产品模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjSlaProductProvider extends BaseProvider<TtsScltxxcjSlaProduct> {

	public PageInfo<TtsScltxxcjSlaProduct> getPageInfo(Map<String, Object> map);


    List<TtsScltxxcjSlaProduct> getProductList();
}