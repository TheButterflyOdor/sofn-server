package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelCheckObject;
import com.sofn.model.generator.AdsModelObjectItemMapping;

import java.util.Map;

/**
 * 模型_检测对象模型对象
 * @author moon.l
 *
 */
public interface AdsModelCheckObjectProvider extends BaseProvider<AdsModelCheckObject> {

	public PageInfo<AdsModelCheckObject> getPageInfo(Map<String, Object> map);

	public PageInfo<AdsModelCheckObject> getPageInfoAll(Map<String, Object> map);

	/**
	 * 根据监测模型id分页查询检测对象列表
	 * @param map
	 * @return
     */
	public PageInfo<AdsModelCheckObject> getPageInfoByModelId(Map<String, Object> map);

	public int insert(AdsModelCheckObject adsModelCheckObject);

	public int updateByPrimaryKey(AdsModelCheckObject adsModelCheckObject);

	public int deleteByID(AdsModelCheckObject adsModelCheckObject);

	public int deleteAll(AdsModelCheckObject adsModelCheckObject);

	public AdsModelCheckObject queryByModelIdWithName(Map<String, Object> map);

	/**
	 * 获取模型_检测对象含有的模型_检测项目列表
	 * @return
	 */
	public PageInfo<AdsModelCheckItem> getPageInfoChecked(Map<String, Object> map);

	/**
	 * 获取模型_检测对象未含有的模型_检测项目列表
	 * @return
	 */
	public PageInfo<AdsModelCheckItem> getPageInfoUnChecked(Map<String, Object> map);

	/**
	 * 批量插入对象数据
	 * @return
	 */
	int insertIntoMapping(AdsModelObjectItemMapping adsModelObjectItemMapping);

	/**
	 * 根据ID删除单条模型_检测项目中的检测对象
	 * @return
	 */
	public int deleteMappingByID(AdsModelObjectItemMapping adsModelObjectItemMapping);

	/**
	 * 批量删除模型_检测项目中的检测对象
	 * @return
	 */
	public int deleteMappingAll(AdsModelObjectItemMapping adsModelObjectItemMapping);

	public AdsModelCheckObject queryByName(Map<String, Object> map);
}