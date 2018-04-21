package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsRecheckObjectExpandMapper;
import com.sofn.model.generator.AsmsRecheckObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-3-17.
 */

@DubboService(interfaceClass = AsmsRecheckObjectProvider.class)
@CacheConfig(cacheNames = "AsmsRecheckObject")
public class AsmsRecheckObjectProviderImpl extends BaseProviderImpl<AsmsRecheckObject> implements AsmsRecheckObjectProvider {

    @Autowired
    private AsmsRecheckObjectExpandMapper objectExpandMapper;

    @Override
    public PageInfo<AsmsRecheckObject> reCheckObjlist(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsRecheckObject> list = objectExpandMapper.getPagesList(params);
        long count = objectExpandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<AsmsRecheckObject> getObjsByTaskId(String id) {
        List<AsmsRecheckObject> list = objectExpandMapper.getObjsByTaskId(id);
        return list;
    }
}
