/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsInspectionAssessExpandMapper;
import com.sofn.model.generator.AsmsInspectionAssess;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AsmsInspectionAssessProvider.class)
@CacheConfig(cacheNames = "AsmsInspectionAssess")
public class AsmsInspectionAssessProviderImpl extends BaseProviderImpl<AsmsInspectionAssess> implements AsmsInspectionAssessProvider {
    @Autowired
    private AsmsInspectionAssessExpandMapper mapper;

    @Override
    @Transactional
    public boolean delOldDate(AsmsInspectionAssess taskUser) {
        if (!StringUtil.isNotBlank(taskUser.getInspectionTaskId())) {
            return false;
        }
        return mapper.delOldDate(taskUser);
    }

    @Override
    public PageInfo<Map<String, Object>> getPages(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = mapper.getPagesList(params);
        long count = mapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public Long getRealCount(Map<String, Object> params) {
        return mapper.getRealCount(params);
    }

    @Override
    public List<AsmsInspectionAssess> getPersonByTaskId(String taskId) {
        return mapper.getPersonByTaskId(taskId);
    }

}
