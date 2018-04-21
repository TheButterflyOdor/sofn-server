package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.model.generator.TtsScltxxcjScgl;

import java.util.List;
import java.util.Map;

/**
 * 产品管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjProductProvider extends BaseProvider<TtsScltxxcjProduct> {

	public PageInfo<TtsScltxxcjProduct> getPageInfo(Map<String, Object> map);


    int changeStatus(TtsScltxxcjProduct ttsScltxxcjProduct);

    PageInfo<TtsScltxxcjProduct> findProList(Map<String, Object> queryParams);

    PageInfo<TtsScltxxcjProduct> queryProByEntityCode(Map<String, Object> queryParams);

    List<TtsScltxxcjScgl> getProType(String entityId);

    PageInfo<TtsScltxxcjProduct> getProductByTerm(Map<String, Object> queryParams);

    List<TtsScltxxcjScgl> getProName(Map<String, Object> queryParams);

    boolean isExistedPro(TtsScltxxcjProduct ttsScltxxcjProduct);

    public PageInfo<TtsScltxxcjProduct> getProductforEntityInfo( Map<String, Object> queryParams);

}