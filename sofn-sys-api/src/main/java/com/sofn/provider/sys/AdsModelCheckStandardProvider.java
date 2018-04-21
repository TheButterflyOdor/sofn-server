package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsModelCheckStandard;

import java.util.Map;

/**
 * 模型_检测标准模型对象
 * @author moon.l
 *
 */
public interface AdsModelCheckStandardProvider extends BaseProvider<AdsModelCheckStandard> {

	public PageInfo<AdsModelCheckStandard> getPageInfo(Map<String, Object> map);

	public PageInfo<AdsModelCheckStandard> getPageInfoAll(Map<String, Object> map);

	public int insert(AdsModelCheckStandard adsModelCheckStandard);

	public int updateByPrimaryKey(AdsModelCheckStandard adsModelCheckStandard);

	public int deleteByID(AdsModelCheckStandard adsModelCheckStandard);

	public int deleteAll(AdsModelCheckStandard adsModelCheckStandard);

	public AdsModelCheckStandard queryByItemId(Map<String, Object> map);

}