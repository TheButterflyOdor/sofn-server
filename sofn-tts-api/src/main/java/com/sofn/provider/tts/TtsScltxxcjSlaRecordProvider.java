package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaRecord;

import java.util.Map;

/**
 * 屠宰记录模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjSlaRecordProvider extends BaseProvider<TtsScltxxcjSlaRecord> {

	PageInfo<TtsScltxxcjSlaRecord> getPageInfo(Map<String, Object> map);

	PageInfo findById(Map<String,Object> params);
}