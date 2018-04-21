package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsModelAttributeMapping;
import com.sofn.provider.ads.AdsCheckModelProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检测模型 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsCheckModelService extends BaseService<AdsCheckModelProvider, AdsCheckModel> {

    @DubboReference
    public void setAdsCheckModelProvider(AdsCheckModelProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsCheckModel bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public List<Map<String,Object>> selectAll() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("isEnable","1");
        return provider.selectAll(queryParams);
    }

    public PageInfo getPageInfoAll(AdsCheckModel bean, int pageNum, int pageSize, String modelName, String monitorType) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelName", modelName);
        queryParams.put("monitorType", monitorType);
        queryParams.put("industry",bean.getIndustry());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organID", bean.getOrganId());
        return provider.getPageInfoAll(queryParams);
    }

    public int insert (AdsCheckModel adsCheckModel){
        return provider.insert(adsCheckModel);
    }

    public int updateByPrimaryKey(AdsCheckModel adsCheckModel){
        return provider.updateByPrimaryKey(adsCheckModel);
    }

    public int deleteByID(AdsCheckModel adsCheckModel){
        return provider.deleteByID(adsCheckModel);
    }

    public int deleteAll(AdsCheckModel adsCheckModel){
        return provider.deleteAll(adsCheckModel);
    }

    public PageInfo getPageInfoChecked(AdsModelAttributeMapping bean, int pageNum, int pageSize, String modelId, String type, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId", modelId);
        queryParams.put("type", type);
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoChecked(queryParams);
    }

    public PageInfo getPageInfoUnChecked(AdsModelAttributeMapping bean,int pageNum, int pageSize, String modelId, String type, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId", modelId);
        queryParams.put("type", type);
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoUnChecked(queryParams);
    }

    public int insertIntoMapping(AdsModelAttributeMapping adsModelAttributeMapping){
        return provider.insertIntoMapping(adsModelAttributeMapping);
    }

    public int deleteMappingByID(AdsModelAttributeMapping adsModelAttributeMapping){
        return provider.deleteMappingByID(adsModelAttributeMapping);
    }

    public int deleteMappingAll(AdsModelAttributeMapping adsModelAttributeMapping){
        return provider.deleteMappingAll(adsModelAttributeMapping);
    }
    public int enableAdsCheckModel(AdsCheckModel adsCheckModel){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId", adsCheckModel.getId());
        queryParams.put("isEnable", adsCheckModel.getIsEnable());
        return provider.enableAdsCheckModel(queryParams);
    }

    public PageInfo getPageInfoCheckedAndJudgeStandard(AdsModelAttributeMapping bean, int pageNum, int pageSize, String modelId, String type, String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId", modelId);
        queryParams.put("type", type);
        queryParams.put("name", name);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoCheckedAndJudgeStandard(queryParams);
    }
}