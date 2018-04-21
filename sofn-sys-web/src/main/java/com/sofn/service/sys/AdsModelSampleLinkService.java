package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsModelSampleLink;
import com.sofn.provider.sys.AdsModelSampleLinkProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型_抽样环节 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsModelSampleLinkService extends BaseService<AdsModelSampleLinkProvider, AdsModelSampleLink> {

    @DubboReference
    public void setAdsModelSampleLinkProvider(AdsModelSampleLinkProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsModelSampleLink bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public PageInfo<AdsModelSampleLink> getPageInfoAll(AdsModelSampleLink bean, int pageNum, int pageSize, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoAll(queryParams);
    }

    public int insert (AdsModelSampleLink adsModelSampleLink){
        return provider.insert(adsModelSampleLink);
    }

    public int updateByPrimaryKey(AdsModelSampleLink adsModelSampleLink){
        return provider.updateByPrimaryKey(adsModelSampleLink);
    }

    public int deleteByID(AdsModelSampleLink adsModelSampleLink){
        return provider.deleteByID(adsModelSampleLink);
    }

    public int deleteAll(AdsModelSampleLink adsModelSampleLink){
        return provider.deleteAll(adsModelSampleLink);
    }
}