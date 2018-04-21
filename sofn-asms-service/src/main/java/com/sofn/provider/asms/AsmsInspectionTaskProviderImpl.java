/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsInspectionTaskExpandMapper;
import com.sofn.model.generator.AsmsInspectionTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AsmsInspectionTaskProvider.class)
@CacheConfig(cacheNames = "AsmsInspectionTask")
public class AsmsInspectionTaskProviderImpl extends BaseProviderImpl<AsmsInspectionTask> implements AsmsInspectionTaskProvider {
    @Autowired
    private AsmsInspectionTaskExpandMapper mapper;

    @Override
    public AsmsInspectionTask findById(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return mapper.findById(map);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getPages(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = mapper.getPagesList(params);
        long count = mapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}
