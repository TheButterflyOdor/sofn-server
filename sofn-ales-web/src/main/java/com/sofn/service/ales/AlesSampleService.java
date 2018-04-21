package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.AlesSample;
import com.sofn.provider.ales.AlesSampleProvider;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlesSampleService extends BaseService<AlesSampleProvider, AlesSample>   {
    @DubboReference
    public void setAlesSampleProvider(AlesSampleProvider provider) {
        this.provider = provider;
    }

    //获取机构变更历史列表
    public PageInfo getAlesSampleList(AlesSample alesSample, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkTaskId", alesSample.getId());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getAlesSampleList(queryParams);
    }

    //添加机构变更历史
    public int addAlesSample(AlesSample alesSample) {
        alesSample.setId(StringUtils.getUUIDString());
        alesSample.setCreateTime(new Date());
        alesSample.setDelFlag("N");
        return provider.addAlesSample(alesSample);
    }

    public List<Map<String,Object>> getWtjcMaxSampleCode(Map<String, Object> map){
        return provider.getWtjcMaxSampleCode(map);
    }

    public List<Map<String,Object>> getJdccMaxSampleCode(Map<String, Object> map){
        return provider.getJdccMaxSampleCode(map);
    }

    public String getCheckInfoIdBySampleId(String sampleId){
        return provider.getCheckInfoIdBySampleId(sampleId);
    }
}
