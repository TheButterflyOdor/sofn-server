/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsRecheckObjectExpandMapper;
import com.sofn.dao.asms.AsmsRecheckTaskExpandMapper;
import com.sofn.dao.generator.AsmsRecheckTaskMapper;
import com.sofn.model.generator.AsmsRecheckObject;
import com.sofn.model.generator.AsmsRecheckTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * @author LiB
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AsmsRecheckTaskProvider.class)
@CacheConfig(cacheNames = "AsmsRecheckTask")
public class AsmsRecheckTaskProviderImpl extends BaseProviderImpl<AsmsRecheckTask> implements AsmsRecheckTaskProvider {

    @Autowired
    private AsmsRecheckTaskExpandMapper expandMapper;
    @Autowired
    private AsmsRecheckObjectExpandMapper objectExpandMapper;
    @Autowired
    private AsmsRecheckTaskMapper asmsRecheckTaskMapper;

    @Override
    public PageInfo<AsmsRecheckTask> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsRecheckTask> list = expandMapper.getPagesList(params);
        long count = expandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<AsmsRecheckObject> getObjById(String id) {
        return objectExpandMapper.getObjsByTaskId(id);
    }

    @Override
    public AsmsRecheckTask addAsmsRecheckTask(AsmsRecheckTask asmsRecheckTask) {
        asmsRecheckTaskMapper.insert(asmsRecheckTask);
        return asmsRecheckTask;
    }

    @Override
    public AsmsRecheckTask updateAsmsRecheckTask(AsmsRecheckTask asmsRecheckTask) {
        asmsRecheckTaskMapper.updateByPrimaryKey(asmsRecheckTask);
        return asmsRecheckTask;
    }

}
