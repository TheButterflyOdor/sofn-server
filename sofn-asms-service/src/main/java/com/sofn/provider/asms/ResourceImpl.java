package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.SysResourceExpandMapper;
import com.sofn.dao.generator.SysResourceMapper;
import com.sofn.model.generator.Resource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 4:35
 */
@DubboService(interfaceClass = ResourceProvider.class)
public class ResourceImpl extends BaseProviderImpl<Resource> implements ResourceProvider {
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysResourceExpandMapper sysResourceExpandMapper;

    @Override
    public PageInfo<Resource> getSysResourceList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = sysResourceExpandMapper.selectAllByCondition(map);
        long count = sysResourceExpandMapper.getSysResourceCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    //    @Override
//    public long getSysResourceCount(Map<String, Object> map);{
//
//        return null;
//    }
    @Override
    public Resource findSysResourceById(String id) {
        Resource sysResource = sysResourceMapper.selectByPrimaryKey(id);
        return sysResource;
    }
}
