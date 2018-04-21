package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjProductType;

import java.util.List;
import java.util.Map;

/**
 * 产品品种管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjProductTypeProvider extends BaseProvider<TtsScltxxcjProductType> {

	public PageInfo<TtsScltxxcjProductType> getPageInfo(Map<String, Object> map);


    int updateStatus(TtsScltxxcjProductType ttsScltxxcjProductType);

    List<TtsScltxxcjProductType> getTypeList();

    TtsScltxxcjProductType getProTypeByCode(Map<String,Object> queryParams);

}