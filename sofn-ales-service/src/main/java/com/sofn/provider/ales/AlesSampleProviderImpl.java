package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesSampleExpandMapper;
import com.sofn.dao.generator.AlesSampleMapper;
import com.sofn.model.generator.AlesSample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/10/9.
 */
@DubboService(interfaceClass = AlesSampleProvider.class)
public class AlesSampleProviderImpl extends BaseProviderImpl<AlesSample> implements AlesSampleProvider {
    @Autowired
    private AlesSampleExpandMapper alesSampleExpandMapper;
    @Autowired
    private AlesSampleMapper alesSampleMapper;
    //添加抽样信息
    @Override
    public int addAlesSample(AlesSample alesSample){
       return alesSampleMapper.insert(alesSample);
    }
    //根据相关条件查询机构变更历史列表
    @Override
    public PageInfo getAlesSampleList(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = alesSampleExpandMapper.getAlesSampleAllList(map);
        long count = alesSampleExpandMapper.getAlesSampleAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 根据参数查询
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> getWtjcMaxSampleCode(Map<String, Object> map) {
        return alesSampleExpandMapper.getWtjcMaxSampleCode(map);
    }

    /**
     * 根据参数查询
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> getJdccMaxSampleCode(Map<String, Object> map) {
        return alesSampleExpandMapper.getJdccMaxSampleCode(map);
    }

    /**
     * 根据抽样单id查询
     * @param sampleId
     * @return
     */
    @Override
    public String getCheckInfoIdBySampleId(String sampleId) {
        return alesSampleExpandMapper.getCheckInfoIdBySampleId(sampleId);
    }
}
