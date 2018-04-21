package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsCheckProject;

import java.util.Map;

/**
 * 检测项目模型对象
 * @author moon.l
 *
 */
public interface AdsCheckProjectProvider extends BaseProvider<AdsCheckProject> {

	public PageInfo<AdsCheckProject> getPageInfo(Map<String, Object> map);


}