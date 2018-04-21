package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsModelCheckStandard;
import com.sofn.provider.sys.AdsModelCheckStandardProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 模型_检测标准 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsModelCheckStandardService extends BaseService<AdsModelCheckStandardProvider, AdsModelCheckStandard> {

    @DubboReference
    public void setAdsModelCheckStandardProvider(AdsModelCheckStandardProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsModelCheckStandard bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public PageInfo<AdsModelCheckStandard> getPageInfoAll(AdsModelCheckStandard bean, int pageNum, int pageSize, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoAll(queryParams);
    }

    public int insert (AdsModelCheckStandard adsModelCheckStandard){
        return provider.insert(adsModelCheckStandard);
    }

    public int updateByPrimaryKey(AdsModelCheckStandard adsModelCheckStandard){
        return provider.updateByPrimaryKey(adsModelCheckStandard);
    }

    public int deleteByID(AdsModelCheckStandard adsModelCheckStandard){
        return provider.deleteByID(adsModelCheckStandard);
    }

    public int deleteAll(AdsModelCheckStandard adsModelCheckStandard){
        return provider.deleteAll(adsModelCheckStandard);
    }

    public AdsModelCheckStandard queryByMyId(String id){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.queryByItemId(queryParams);
    }

}