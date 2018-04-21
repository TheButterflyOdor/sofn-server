package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsModelSampleLink;

import java.util.Map;

/**
 * 模型_抽样环节模型对象
 * @author moon.l
 *
 */
public interface AdsModelSampleLinkProvider extends BaseProvider<AdsModelSampleLink> {

	public PageInfo<AdsModelSampleLink> getPageInfo(Map<String, Object> map);

	public PageInfo<AdsModelSampleLink> getPageInfoAll(Map<String, Object> map);

	public int insert(AdsModelSampleLink adsModelSampleLink);

	public int updateByPrimaryKey(AdsModelSampleLink adsModelSampleLink);

	public int deleteByID(AdsModelSampleLink adsModelSampleLink);

	public int deleteAll(AdsModelSampleLink adsModelSampleLink);

}