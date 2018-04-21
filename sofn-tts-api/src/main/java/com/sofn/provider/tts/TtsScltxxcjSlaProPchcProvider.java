package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjSlaProPchc;

import java.util.Map;

/**
 * 屠宰后产品批次合成模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjSlaProPchcProvider extends BaseProvider<TtsScltxxcjSlaProPchc> {

	public PageInfo<TtsScltxxcjSlaProPchc> getPageInfo(Map<String, Object> map);


    int insertBySelect(Map<String, Object> map);
}