package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjCggl;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjCgglAndUserInfo;

import java.util.Map;

/**
 * 采购管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjCgglProvider extends BaseProvider<TtsScltxxcjCggl> {

	public PageInfo<TtsScltxxcjCggl> getPageInfo(Map<String, Object> map);


	public TtsScltxxcjCgglAndCustomer getCgglForZsm(Map<String, Object> map);

	public long zsmIsCgOrXs(Map<String, Object> map);

    long isExists(String code);

	TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCg(String code);

	TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCgforUsual(String code);
}