package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjXsth;
import com.sofn.model.generator.TtsScltxxcjXsthAndCustomer;

import java.util.Map;

/**
 * 销售退回模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjXsthProvider extends BaseProvider<TtsScltxxcjXsth> {

	public PageInfo<TtsScltxxcjXsth> getPageInfo(Map<String, Object> map);


	TtsScltxxcjXsthAndCustomer queryByXsjlId(String id);

    TtsScltxxcjXsthAndCustomer querySaleInfoById(String id);
}