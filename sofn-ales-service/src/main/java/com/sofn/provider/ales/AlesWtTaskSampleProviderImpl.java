/**
 *
 */
package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesWtTaskSampleExpandMapper;
import com.sofn.dao.generator.AlesWtTaskSampleMapper;
import com.sofn.model.generator.AlesWtTaskSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AlesWtTaskSampleProvider.class)
@CacheConfig(cacheNames = "AlesWtTaskSample")
public class AlesWtTaskSampleProviderImpl extends BaseProviderImpl<AlesWtTaskSample> implements AlesWtTaskSampleProvider {
    @Autowired
    private AlesWtTaskSampleMapper alesWtTaskSampleMapper;
    @Autowired
    private AlesWtTaskSampleExpandMapper expandMapper;

    /**
     *
     * @param params
     * @return
     */
    @Override
    public PageInfo<AlesWtTaskSample> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getList(params);
        long count = expandMapper.getCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     *
     * @param alesWtTaskSample
     * @return
     */
    @Override
    @Transactional
    public int addAlesWtTaskSample(AlesWtTaskSample alesWtTaskSample) {
        return alesWtTaskSampleMapper.insert(alesWtTaskSample);
    }

    /**
     *
     * @param jcxxId
     * @return
     */
    @Override
    public List<AlesWtTaskSample> getSampleListByWtTaskId(String jcxxId) {
        AlesWtTaskSample a = new AlesWtTaskSample();
        a.setWtTaskId(jcxxId);
        List<AlesWtTaskSample> list = expandMapper.getSampleListByWtTaskId(a);
        return list;
    }

    /**
     *
     * @param jcxxId
     * @param state
     * @return
     */
    @Override
    @Transactional
    public boolean syncIsTrue(String jcxxId, String state) {
        AlesWtTaskSample a = new AlesWtTaskSample();
        a.setWtTaskId(jcxxId);
        a.setIsSync(state);
        boolean x = expandMapper.syncIsTrue(a);
        return x;
    }

    /**
     *
     * @param sampleCode
     * @return
     */
    @Override
    public AlesWtTaskSample getSampleBySampleCode(String sampleCode) {
        AlesWtTaskSample a = new AlesWtTaskSample();
        a.setSampleCode(sampleCode);
        return expandMapper.getSampleBySampleCode(a);
    }

    /**
     *
     * @param sampleCode
     * @return
     */
    @Override
    public AlesWtTaskSample getLocalSampleBySampleCode(String sampleCode) {
        AlesWtTaskSample a = new AlesWtTaskSample();
        a.setSampleCode(sampleCode);
        return expandMapper.getLocalSampleBySampleCode(a);
    }
}
