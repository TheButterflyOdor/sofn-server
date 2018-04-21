package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjScglAndUserInfo;
import jxl.write.Boolean;

import java.util.List;
import java.util.Map;

/**
 * 生产管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjScglProvider extends BaseProvider<TtsScltxxcjScgl> {

	public PageInfo<TtsScltxxcjScgl> getPageInfo(Map<String, Object> map);

	List<TtsScltxxcjScgl> getSphcByIds(Map<String, Object> map);

	List<Map<String,Object>> getCheckProduct(Map<String, Object> map);

	PageInfo<TtsScltxxcjScgl> getslaughterProduct(Map<String, Object> map);

	void updateProductAmount(String id,String amount);

	void updateProductStoreCount(Map<String,Object> map);

	void updateProductStoreFreezeCount(Map<String, Object> map);

	long updateStoreCountForCgqr(String id);

    List<TtsScltxxcjScgl> getTypeList(String entityIdcode);

	void saveSearchData(Map<String, Object> map);

	List<Map<String,Object>> getBaseForPc(Map<String, Object> map);

	//通过批次码为消费者查询信息
    TtsScltxxcjScglAndUserInfo getInfoByPcCodeForUsual(String code);

	//通过批次码为政府查询信息
	TtsScltxxcjScglAndUserInfo getInfoByPcCodeForGov(String code);

	TtsScltxxcjScgl getproductInfo(String id);

	void updateState(String id);

    void deleteByIds(String id);

    List<TtsScltxxcjScgl> getSaleByProId(String[] ids);

    List<Map<String,Object>> getPrintForFrom(Map<String, Object> queryParams);

	List<Map<String,Object>> getPrintForPc(Map<String, Object> queryParams);

    List<TtsScltxxcjScgl> getProType(Map<String, Object> queryParams);

	List<TtsScltxxcjScgl> getProName(Map<String, Object> queryParams);

	long isOneType(String id);

	String getProductId(String id);

	List<Map<String,Object>> queryBySourceEntity(String sourceEntity);
}