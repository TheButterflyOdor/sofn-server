package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsTaskProject;

import java.util.List;
import java.util.Map;

/**
 * 监测任务_检测项目模型对象
 * @author moon.l
 *
 */
public interface AdsTaskProjectProvider extends BaseProvider<AdsTaskProject> {

	public PageInfo<AdsTaskProject> getPageInfo(Map<String, Object> map);

	public List<AdsTaskProject> getEntityWithTaskId(Map<String, Object> map);


}