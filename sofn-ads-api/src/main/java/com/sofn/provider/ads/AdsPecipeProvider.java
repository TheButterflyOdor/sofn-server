package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsPecipe;

import java.util.Map;

/**
 * 回执单模型对象
 * @author moon.l
 *
 */
public interface AdsPecipeProvider extends BaseProvider<AdsPecipe> {

	public PageInfo<AdsPecipe> getPageInfo(Map<String, Object> map);
	public PageInfo<AdsPecipe> getAdsPecipePageInfo(Map<String, Object> map);


	public int insert(AdsPecipe adsPecipe);
}