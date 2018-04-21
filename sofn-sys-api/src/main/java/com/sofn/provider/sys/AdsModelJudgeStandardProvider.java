package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsModelJudgeStandard;

import java.util.Map;

/**
 * 模型_判定标准模型对象
 * @author moon.l
 *
 */
public interface AdsModelJudgeStandardProvider extends BaseProvider<AdsModelJudgeStandard> {

	public PageInfo<AdsModelJudgeStandard> getPageInfo(Map<String, Object> map);

	public int batchDelete(AdsModelJudgeStandard adsModelJudgeStandard);
}