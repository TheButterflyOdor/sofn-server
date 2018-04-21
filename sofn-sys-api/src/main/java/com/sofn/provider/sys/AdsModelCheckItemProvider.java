package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelObjectItemMapping;

import java.util.List;
import java.util.Map;

/**
 * 模型_检测项目模型对象
 * @author moon.l
 *
 */
public interface AdsModelCheckItemProvider extends BaseProvider<AdsModelCheckItem> {

	public PageInfo<AdsModelCheckItem> getPageInfo(Map<String, Object> map);

	/**
	 * 新增检测项目
	 * @param adsModelCheckItem
	 * @return
     */
	int insert(AdsModelCheckItem adsModelCheckItem);

	/**
	 * 批量删除检测项目
	 * @param adsModelCheckItem
	 * @return
     */
	int batchDelete(AdsModelCheckItem adsModelCheckItem);

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

	/**
	 * 查询检测模型下的所有检测项目
	 * @param model_id
	 * @return
     */
	public List<AdsModelCheckItem> getCheckItemListByModelId(String model_id);

	/**
	 * 根据检测对象ID获取模型_检测对象集合
	 * @return
	 */
	public List<AdsModelCheckItem> queryByObjId(Map<String, Object> map);

}