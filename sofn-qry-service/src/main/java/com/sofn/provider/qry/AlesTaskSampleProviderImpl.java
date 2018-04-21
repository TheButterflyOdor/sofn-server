/**
 *
 */
package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.AlesTaskSampleExpandMapper;
import com.sofn.model.generator.AlesTaskSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AlesTaskSampleProvider.class)
@CacheConfig(cacheNames = "AlesTaskSample")
public class AlesTaskSampleProviderImpl extends BaseProviderImpl<AlesTaskSample> implements AlesTaskSampleProvider {

    @Autowired
    private AlesTaskSampleExpandMapper expandMapper;

    @Override
    public PageInfo<AlesTaskSample> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = expandMapper.getPagesList(params);
        long count = expandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<AlesTaskSample> getSampleListByjcxxId(String jcxxId) {
        AlesTaskSample a = new AlesTaskSample();
        a.setOrganTaskId(jcxxId);
        List<AlesTaskSample> list = expandMapper.getSampleListByjcxxId(a);
        return list;
    }

    @Override
    public AlesTaskSample getSampleBySampleCode(String sampleCode) {
        AlesTaskSample a = new AlesTaskSample();
        a.setSampleCode(sampleCode);
        return expandMapper.getSampleBySampleCode(a);
    }

    @Override
    public AlesTaskSample getLocalSampleBySampleCode(String sampleCode) {
        AlesTaskSample a = new AlesTaskSample();
        a.setSampleCode(sampleCode);
        return expandMapper.getLocalSampleBySampleCode(a);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getSampleListByObjIds(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = expandMapper.getSampleListByTaskIdPagesList(params);
        long count = expandMapper.getSampleListByTaskIdPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public AlesTaskSample getSampleById(String id) {
        try {
            AlesTaskSample AlesTaskSample = expandMapper.selectByPrimaryKey(id);
            logger.debug(AlesTaskSample.toString());
            return AlesTaskSample;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
