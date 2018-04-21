package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsInfoProject;

import java.util.List;
import java.util.Map;

/**
 * 检测信息_检测项目模型对象
 * @author moon.l
 *
 */
public interface AdsInfoProjectProvider extends BaseProvider<AdsInfoProject> {

	public PageInfo<AdsInfoProject> getPageInfo(Map<String, Object> map);

	public List<AdsInfoProject> getPageInfoWithCheckInfoId(Map<String, Object> map);

	public void updateResult(Map<String, Object> map);

	public AdsInfoProject queryByProjectAndInfoId(Map<String, Object> map);

	public List<AdsInfoProject> queryByInfoId(Map<String, Object> map);

	public List<AdsInfoProject> queryListByInfoId(Map<String, Object> map);

	public List<AdsInfoProject> getInfoForId(Map<String, Object> map);

	public void updateMax(Map<String, Object> map);



}