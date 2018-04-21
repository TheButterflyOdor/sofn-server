package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjCggl;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjCgglAndUserInfo;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;

import java.util.List;
import java.util.Map;

/**
 * 采购管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjCgglProvider extends BaseProvider<TtsScltxxcjCggl> {

	public PageInfo<TtsScltxxcjCggl> getPageInfo(Map<String, Object> map);


	public List<TtsScltxxcjCgglAndCustomer> getCgglForZsm(Map<String, Object> map);

	public long zsmIsCgOrXs(Map<String, Object> map);

    long isExists(String code);

	TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCg(String code);

	TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCgforUsual(String code);

	List<TtsScltxxcjXsdjAndCustomer> allPurchase(Map<String, Object> map);
}