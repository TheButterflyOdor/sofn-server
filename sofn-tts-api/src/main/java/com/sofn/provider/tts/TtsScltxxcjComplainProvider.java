package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjComplain;

import java.util.Map;

/**
 * 投诉举报模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjComplainProvider extends BaseProvider<TtsScltxxcjComplain> {

	public PageInfo<TtsScltxxcjComplain> getPageInfo(Map<String, Object> map);

	PageInfo<TtsScltxxcjComplain> getInterPageInfo(Map<String, Object> map);

	public PageInfo<TtsScltxxcjComplain> getEntityPageInfo(Map<String, Object> map);

}