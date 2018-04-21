package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelObjectItemMapping;
import com.sofn.provider.sys.AdsModelCheckItemProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型_检测项目 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsModelCheckItemService extends BaseService<AdsModelCheckItemProvider, AdsModelCheckItem> {

    @DubboReference
    public void setAdsModelCheckItemProvider(AdsModelCheckItemProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsModelCheckItem bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", bean.getName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public int insert(AdsModelCheckItem adsModelCheckItem){
        return provider.insert(adsModelCheckItem);
    }

    public int batchDelete(AdsModelCheckItem adsModelCheckItem){
        return provider.batchDelete(adsModelCheckItem);
    }

    public PageInfo getPageInfoChecked(AdsModelCheckItem bean,int pageNum, int pageSize, String objectId, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("objectId", objectId);
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoChecked(queryParams);
    }

    public PageInfo getPageInfoUnChecked(AdsModelObjectItemMapping bean,int pageNum, int pageSize, String objectId, String name){
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

    public List<AdsModelCheckItem> getCheckItemListByModelId(String model_id){
        return provider.getCheckItemListByModelId(model_id);
    }

    public List<AdsModelCheckItem> queryByObjId(String objId){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("objId", objId);
        return provider.queryByObjId(queryParams);
    }

}