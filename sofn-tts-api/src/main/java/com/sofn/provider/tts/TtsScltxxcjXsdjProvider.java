package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjXsdj;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndUserInfo;

import java.util.List;
import java.util.Map;

/**
 * 销售登记模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjXsdjProvider extends BaseProvider<TtsScltxxcjXsdj> {

	public PageInfo<TtsScltxxcjXsdj> getPageInfo(Map<String, Object> map);

	public TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id);

	public PageInfo<TtsScltxxcjXsdjAndCustomer> queryPageForCg(Map<String, Object> map);

	public List<TtsScltxxcjXsdjAndCustomer> getXsdjForZsm(Map<String, Object> map);

	public PageInfo<Map<String,Object>> getXsUpZsm(Map<String, Object> map);

    TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForUsual(String code);

	TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForGov(String code);

	void updateComfigState(String id);

	TtsScltxxcjXsdjAndCustomer getCgqrAndCustomerById(Map<String, Object> map);

    long existsTrace(Map<String, Object> map);
}