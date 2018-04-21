package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjXsdj;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndUserInfo;
import com.sofn.model.tts.ResInfo;

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

	public List<Map<String,Object>> getXsUpZsm(Map<String, Object> map);

    TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForUsual(String code);

	TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForGov(String code);

	void updateComfigState(Map<String, Object> map);

	TtsScltxxcjXsdjAndCustomer getCgqrAndCustomerById(Map<String, Object> map);

    long existsTrace(Map<String, Object> map);

    List<Map<String,Object>> querySource(String id);

	/**
	 * 根据主体身份码获取交易状态
	 * @param entityIdCode 主体身份码
	 * @return ResInfo：status(0：可申请注销；1：本级未确认上游；2：下游未确认)
	 * message(提示信息)
     */
	ResInfo getBusinessStatusByEntityIdCode(String entityIdCode);
}