package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsModelType;

import java.util.Map;

/**
 * 模型_种类模型对象
 * @author moon.l
 *
 */
public interface AdsModelTypeProvider extends BaseProvider<AdsModelType> {

	public PageInfo<AdsModelType> getPageInfo(Map<String, Object> map);

	public PageInfo<AdsModelType> getPageInfoAll(Map<String, Object> map);

	public int insert(AdsModelType adsModelType);

	public int updateByPrimaryKey(AdsModelType adsModelType);

	public int deleteByID(AdsModelType adsModelType);

	public int deleteAll(AdsModelType adsModelType);
}