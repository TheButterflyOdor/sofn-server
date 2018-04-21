package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysNoticerelease;

import java.util.List;
import java.util.Map;

/**
 * 公告管理模型对象
 * @author moon.l
 *
 */
public interface SysNoticereleaseProvider extends BaseProvider<SysNoticerelease> {

	List<SysNoticerelease> getPageInfo(Map<String, Object> map);

	long getRecordsTotal(String noticeType, String title, String releaseTime,String releasePerson);

	int addNotice(SysNoticerelease sysNoticerelease);

	int updateNotice(SysNoticerelease sysNoticerelease);

	int deleteNotice(String id);
}