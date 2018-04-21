/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsCheckTaskReportExpandMapper;
import com.sofn.model.generator.AsmsCheckTaskReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AsmsCheckTaskReportProvider.class)
@CacheConfig(cacheNames = "AsmsCheckTaskReport")
public class AsmsCheckTaskReportProviderImpl extends BaseProviderImpl<AsmsCheckTaskReport> implements AsmsCheckTaskReportProvider {

    @Autowired
    private AsmsCheckTaskReportExpandMapper expandMapper;

    @Override
    public PageInfo<List<Map<String, Object>>> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getPagesList(params);
        long count = expandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public boolean addReport(AsmsCheckTaskReport report) {
        try {
            super.update(report);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
