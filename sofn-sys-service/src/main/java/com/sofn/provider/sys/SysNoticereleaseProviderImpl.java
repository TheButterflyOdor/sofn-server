package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysNoticereleaseMapper;
import com.sofn.dao.sys.SysNoticereleaseExpandMapper;
import com.sofn.model.generator.SysNoticerelease;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	公告管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = SysNoticereleaseProvider.class)
public class SysNoticereleaseProviderImpl extends BaseProviderImpl<SysNoticerelease> implements SysNoticereleaseProvider {

    @Autowired
    private SysNoticereleaseExpandMapper SysNoticereleaseExpandMapper;

    @Autowired
    private SysNoticereleaseMapper sysNoticereleaseMapper;

    public List<SysNoticerelease> getPageInfo(Map<String, Object> map) {
        return SysNoticereleaseExpandMapper.getPageInfo(map);
    }

    @Override
    public long getRecordsTotal(String noticeType, String title, String releaseTime, String releasePerson) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("noticeType",noticeType);
        queryParams.put("title",title);
        queryParams.put("releaseTime",releaseTime);
        queryParams.put("releasePerson",releasePerson);
        return SysNoticereleaseExpandMapper.getCount(queryParams);
    }

    public int addNotice(SysNoticerelease sysNoticerelease){
        return sysNoticereleaseMapper.insert(sysNoticerelease) ;
    }

    public int updateNotice(SysNoticerelease sysNoticerelease){
        return sysNoticereleaseMapper.updateByPrimaryKey(sysNoticerelease);
    }

    public int deleteNotice(String id){
        SysNoticerelease sysNoticerelease = sysNoticereleaseMapper.selectByPrimaryKey(id);
        sysNoticerelease.setDelFlag("Y");
        return sysNoticereleaseMapper.updateByPrimaryKey(sysNoticerelease);
    }
}
