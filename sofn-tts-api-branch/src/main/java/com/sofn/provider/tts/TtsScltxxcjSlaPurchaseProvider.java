package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaPurchase;
import com.sofn.model.generator.TtsScltxxcjSlaSale;

import java.util.Map;

/**
 * 屠宰产品采购记录模型对象
 * @author moon.l
 *
 */

public interface TtsScltxxcjSlaPurchaseProvider extends BaseProvider<TtsScltxxcjSlaPurchase> {

	PageInfo<TtsScltxxcjSlaPurchase> getPurchasePageInfo(Map<String, Object> map);

}