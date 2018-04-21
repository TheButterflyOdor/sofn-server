package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsMonitorObjectExpandMapper;
import com.sofn.dao.generator.AsmsMonitorObjectMapper;
import com.sofn.model.generator.AsmsMonitorObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * Created by LIB on 2016/12/26.
 */
@DubboService(interfaceClass = AsmsMonitorObjectProvider.class)
@CacheConfig(cacheNames = "AsmsMonitorObject")
public class AsmsMonitorObjectProviderImpl extends BaseProviderImpl<AsmsMonitorObject> implements AsmsMonitorObjectProvider {
    @Autowired
    private AsmsMonitorObjectExpandMapper objExpandMapper;//扩展关联表mapp-抽查对象
    @Autowired
    private AsmsMonitorObjectMapper asmsMonitorObjectMapper;

    @Override
    public PageInfo<List<Map<String, Object>>> objList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = objExpandMapper.getPagesList(params);
        long count = objExpandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<AsmsMonitorObject> getMobjListByTaskId(AsmsMonitorObject o) {
        return objExpandMapper.getMobjListByTaskId(o);
    }

    @Override
    public AsmsMonitorObject findAsmsMonitorObjectById(String id) {
        return asmsMonitorObjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean findAsmsMonitorByIdAndIsSample(String id, String isSample) {
        Map<String, Object> map = new HashedMap();
        map.put("id",id);
        map.put("isSample",isSample);
        if(objExpandMapper.findAsmsMonitorByIdAndIsSample(map)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean findAsmsMonitorByIdAndIsSync(String id, String isSync) {
        Map<String, Object> map = new HashedMap();
        map.put("id",id);
        map.put("isSync",isSync);
        if(objExpandMapper.findAsmsMonitorByIdAndIsSync(map)>0){
            return true;
        }
        return false;
    }


}
