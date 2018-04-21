package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.TtsScltxxcjNotification;

import java.util.Map;

/**
 * 投诉建议信息管理模型对象
 * @author moon.l
 *
 */
public interface TtsScltxxcjNotificationProvider extends BaseProvider<TtsScltxxcjNotification> {

	public PageInfo<TtsScltxxcjNotification> getPageInfo(Map<String, Object> map);

	/** 更新阅读 */
	public void updateIsRead(String id) ;
}