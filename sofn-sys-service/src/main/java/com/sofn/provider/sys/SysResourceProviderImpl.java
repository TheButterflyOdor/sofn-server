package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysResourceMapper;
import com.sofn.dao.sys.SysResourceExpandMapper;
import com.sofn.model.generator.SysResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
@DubboService(interfaceClass = SysResourceProvider.class)
public class SysResourceProviderImpl extends BaseProviderImpl<SysResource> implements SysResourceProvider {
    @Autowired
    private SysResourceExpandMapper sysResourceExpandMapper;

    @Override
    public PageInfo<SysResource> getPageList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = sysResourceExpandMapper.getList(map);
        long count = sysResourceExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<SysResource> getQueryList(Map<String, Object> map) {
        PageInfo page = new PageInfo();
        List<Map<String, Object>> list = sysResourceExpandMapper.getQueryList(map);
        long count = sysResourceExpandMapper.getQueryCount(map);
        page.setList(list);
        page.setTotal(count);
        return page;
    }

    @Override
    public Integer getRepeatResourceCount(Map<String, Object> map) {
        return sysResourceExpandMapper.getRepeatRescourceCount(map);
    }


}
