package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.model.generator.AdsCheckPackage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public interface AdsNewModelProvider extends BaseProvider<AdsCheckModelMapping>{
    public void excelInsert(List<AdsCheckModelMapping> checkItemsList);

    public int addCheckItems(AdsCheckModelMapping adsCheckModelMapping);

    public PageInfo<AdsCheckModelMapping> getPageInfoAll(Map<String, Object> map);

    public PageInfo<AdsCheckModelMapping> getPageInfoAllByDetail(Map<String, Object> map);

    public int deleteAll(AdsCheckModelMapping adsCheckModelMapping);

    public AdsCheckModelMapping getCheckItemById(String checkId);

    public int saveCheckItem(AdsCheckModelMapping adsCheckModelMapping);

    public PageInfo<AdsCheckModelMapping> getModelPageInfoAll(Map<String, Object> map);

    public int addMonitorModel(AdsCheckModelMapping adsCheckModelMapping);

    public int deleteCheckModel(AdsCheckModelMapping adsCheckModelMapping);

    public void updateByPrimaryKey(AdsCheckModelMapping adsCheckModelMapping);

    public AdsCheckModelMapping selectByPrimaryKey(String modelId);

    public List<AdsCheckModelMapping> getProductNameList(String modelId);

    public List<AdsCheckModelMapping> getModelConfigListOrderByProductName(String modelId);

    public PageInfo<AdsCheckModelMapping> getModelConfigPageInfoAll(Map<String, Object> map);

    public int saveModelConfig(AdsCheckModelMapping adsCheckModelMapping);

    public void addModelConfigValue(AdsCheckModelMapping adsCheckModelMapping);

    public PageInfo<AdsCheckModelMapping> getModelConfigDetail(Map<String, Object> map);

    public int saveCheckPackageItems(AdsCheckModelMapping adsCheckModelMapping);

    public int enableAdsCheckModel(AdsCheckModelMapping adsCheckModelMapping);

    public int delAdsCheckModelCongif(AdsCheckModelMapping adsCheckModelMapping);

    public int isOrNotUse(AdsCheckModelMapping adsCheckModelMapping);

    public List<Map<String, Object>> selectAllModel(Map<String, Object> map);

    public List<AdsCheckModelMapping> queryByModelIdAndName(Map<String, Object> map);

}
