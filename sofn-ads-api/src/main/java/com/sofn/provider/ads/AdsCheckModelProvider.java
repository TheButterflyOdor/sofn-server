package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsModelAttributeMapping;

import java.util.List;
import java.util.Map;

/**
 * 检测模型模型对象
 * @author moon.l
 *
 */
public interface AdsCheckModelProvider extends BaseProvider<AdsCheckModel> {

	public PageInfo<AdsCheckModel> getPageInfo(Map<String, Object> map);

	public List<Map<String, Object>> selectAll(Map<String, Object> map);

	public PageInfo<AdsCheckModel> getPageInfoAll(Map<String, Object> map);

	public int insert(AdsCheckModel adsCheckModel);

	public int updateByPrimaryKey(AdsCheckModel adsCheckModel);

	public int deleteByID(AdsCheckModel adsCheckModel);

	public int deleteAll(AdsCheckModel adsCheckModel);

	/**
	 * 获取检测模型含有的子表数据列表
	 * @return
	 */
	public PageInfo<AdsModelAttributeMapping> getPageInfoChecked(Map<String, Object> map);

	/**
	 * 获取检测模型未含有的子表数据列表
	 * @return
	 */
	public PageInfo<AdsModelAttributeMapping> getPageInfoUnChecked(Map<String, Object> map);

	/**
	 * 批量插入子表数据
	 * @return
	 */
	int insertIntoMapping(AdsModelAttributeMapping adsModelAttributeMapping);

	/**
	 * 根据ID删除单条子表中的数据
	 * @return
	 */
	public int deleteMappingByID(AdsModelAttributeMapping adsModelAttributeMapping);

	/**
	 * 批量删除模子表中的数据
	 * @return
	 */
	public int deleteMappingAll(AdsModelAttributeMapping adsModelAttributeMapping);

	/**
	 * 确认启用检测模型
	 * @return
	 */
	int enableAdsCheckModel(Map<String, Object> map);

	/**
	 * 获取检测模型相关的判定标准数据
	 * @return
	 */
	 PageInfo<AdsModelAttributeMapping> getPageInfoCheckedAndJudgeStandard(Map<String, Object> map);
}