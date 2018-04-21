package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsModelType;
import com.sofn.provider.sys.AdsModelTypeProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型_种类 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsModelTypeService extends BaseService<AdsModelTypeProvider, AdsModelType> {

    @DubboReference
    public void setAdsModelTypeProvider(AdsModelTypeProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsModelType bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public PageInfo<AdsModelType> getPageInfoAll(AdsModelType bean, int pageNum, int pageSize, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoAll(queryParams);
    }

    public int insert (AdsModelType adsModelType){
        return provider.insert(adsModelType);
    }

    public int updateByPrimaryKey(AdsModelType adsModelType){
        return provider.updateByPrimaryKey(adsModelType);
    }

    public int deleteByID(AdsModelType adsModelType){
        return provider.deleteByID(adsModelType);
    }

    public int deleteAll(AdsModelType adsModelType){
        return provider.deleteAll(adsModelType);
    }
}