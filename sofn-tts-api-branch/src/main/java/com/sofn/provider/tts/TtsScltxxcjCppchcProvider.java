package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjCppchc;


import java.util.List;
import java.util.Map;

/**
 * 产品批次合成模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjCppchcProvider extends BaseProvider<TtsScltxxcjCppchc> {

	public PageInfo<TtsScltxxcjCppchc> getPageInfo(Map<String, Object> map);

	public int insertBySelect(Map<String, Object> map);

	public List<Map<String,Object>> getInfoForNewPc(Map<String, Object> map);
}