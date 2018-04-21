package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsTaskProjectMapper;
import com.sofn.dao.ads.AdsTaskProjectExpandMapper;
import com.sofn.model.generator.AdsTaskProject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	监测任务_检测项目 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsTaskProjectProvider.class)
public class AdsTaskProjectProviderImpl extends BaseProviderImpl<AdsTaskProject> implements AdsTaskProjectProvider {

    @Autowired
    private AdsTaskProjectExpandMapper AdsTaskProjectExpandMapper;

    public PageInfo<AdsTaskProject> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsTaskProjectExpandMapper.getPageInfo(map);
        long count = AdsTaskProjectExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<AdsTaskProject> getEntityWithTaskId(Map<String, Object> map) {
        List<AdsTaskProject> list = AdsTaskProjectExpandMapper.getEntityWithTaskId(map);
        return list;
    }

}
