package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelCheckObject;
import com.sofn.model.generator.AdsModelObjectItemMapping;
import com.sofn.provider.ads.AdsModelCheckObjectProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 模型_检测对象 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsModelCheckObjectService extends BaseService<AdsModelCheckObjectProvider, AdsModelCheckObject> {

    @DubboReference
    public void setAdsModelCheckObjectProvider(AdsModelCheckObjectProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsModelCheckObject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public PageInfo<AdsModelCheckObject> getPageInfoAll(AdsModelCheckObject bean, int pageNum, int pageSize, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organId",bean.getOrganId());
        return provider.getPageInfoAll(queryParams);
    }

    public int insert (AdsModelCheckObject adsModelCheckObject){
        return provider.insert(adsModelCheckObject);
    }

    public int updateByPrimaryKey(AdsModelCheckObject adsModelCheckObject){
        return provider.updateByPrimaryKey(adsModelCheckObject);
    }

    public int deleteByID(AdsModelCheckObject adsModelCheckObject){
        return provider.deleteByID(adsModelCheckObject);
    }

    public int deleteAll(AdsModelCheckObject adsModelCheckObject){
        return provider.deleteAll(adsModelCheckObject);
    }

    public PageInfo getPageInfoByModelId(AdsModelCheckObject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("model_id",bean.getModel_id());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoByModelId(queryParams);
    }

    public AdsModelCheckObject queryByModelIdWithName(String modelId,String sampleName){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId", modelId);
        queryParams.put("sampleName", sampleName);
        return provider.queryByModelIdWithName(queryParams);
    }

    public PageInfo getPageInfoChecked(AdsModelCheckItem bean, int pageNum, int pageSize, String objectId, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("objectId", objectId);
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoChecked(queryParams);
    }

    public PageInfo getPageInfoUnChecked(AdsModelObjectItemMapping bean, int pageNum, int pageSize, String objectId, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("objectId", objectId);
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoUnChecked(queryParams);
    }

    public int insertIntoMapping(AdsModelObjectItemMapping adsModelObjectItemMapping){
        return provider.insertIntoMapping(adsModelObjectItemMapping);
    }

    public int deleteMappingByID(AdsModelObjectItemMapping adsModelObjectItemMapping){
        return provider.deleteMappingByID(adsModelObjectItemMapping);
    }

    public int deleteMappingAll(AdsModelObjectItemMapping adsModelObjectItemMapping){
        return provider.deleteMappingAll(adsModelObjectItemMapping);
    }

    public AdsModelCheckObject queryByName(String sampleName){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleName", sampleName);
        return provider.queryByName(queryParams);
    }

}