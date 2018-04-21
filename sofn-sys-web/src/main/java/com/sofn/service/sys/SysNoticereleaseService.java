package com.sofn.service.sys;

import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysNoticerelease;
import com.sofn.provider.sys.SysNoticereleaseProvider;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 公告管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class SysNoticereleaseService extends BaseService<SysNoticereleaseProvider, SysNoticerelease> {

    @DubboReference
    public void setSysNoticereleaseProvider(SysNoticereleaseProvider provider) {
        this.provider = provider;
    }

    public List<SysNoticerelease> getPageInfo(Page pager, String noticeType, String title, String releaseTime, String releasePerson) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pager", pager);
        queryParams.put("noticeType",noticeType);
        queryParams.put("title",title);
        queryParams.put("releaseTime",releaseTime);
        queryParams.put("releasePerson",releasePerson);
        return provider.getPageInfo(queryParams);
    }

    public long getRecordsTotal(String noticeType, String title, String releaseTime, String releasePerson){
        return provider.getRecordsTotal(noticeType,title,releaseTime,releasePerson);
    }

    public int addNotice(SysNoticerelease sysNoticerelease){
        sysNoticerelease.setId(UUID.randomUUID().toString());
        sysNoticerelease.setDelFlag("N");
        sysNoticerelease.setCreateBy(WebUtil.getCurrentUserId());
        sysNoticerelease.setCreateTime(new Date());
        sysNoticerelease.setUpdateBy(WebUtil.getCurrentUserId());
        sysNoticerelease.setUpdateTime(new Date());
        return provider.addNotice(sysNoticerelease);
    }

    public int updateNotice(SysNoticerelease sysNoticerelease){
        sysNoticerelease.setUpdateTime(new Date());
        sysNoticerelease.setUpdateBy(WebUtil.getCurrentUserId());
        return provider.updateNotice(sysNoticerelease);
    }

    public int deleteNotice(String id){
        return provider.deleteNotice(id);
    }
}