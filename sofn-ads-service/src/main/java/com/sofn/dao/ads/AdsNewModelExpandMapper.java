package com.sofn.dao.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.model.generator.AdsCheckPackage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@MyBatisDao
public interface AdsNewModelExpandMapper extends BaseExpandMapper {
    //excel数据导入
    public void excelInsert(List<AdsCheckModelMapping> checkItemsList);

    //新增检测项目
    public void addCheckItems(AdsCheckModelMapping adsCheckModelMapping);

    //分页查询检测项目
    List<Map<String, Object>> getPageInfoAll(Map<String, Object> map);

    /**
     *  获取全部检测项目数据条数
     */
    long getCountAll(Map<String, Object> map);

    List<Map<String, Object>> getPageInfoAllByDetail(Map<String, Object> map);

    /**
     *  获取全部检测项目数据条数
     */
    long getCountAllByDetail(Map<String, Object> map);

    /**
     *  根据ID删除检测模型(修改删除标志为Y)
     */
    int deleteByID(AdsCheckModelMapping adsCheckModelMapping);

    AdsCheckModelMapping getCheckItemById(String checkId);

    void saveCheckItem(AdsCheckModelMapping adsCheckModelMapping);

    long getCheckItemsCount(AdsCheckModelMapping adsCheckModelMapping);

    long isOrNotUse(AdsCheckModelMapping adsCheckModelMapping);

    long getConflict(Map<String, Object> map);

    long getPackageCheckItemsConflict(Map<String, Object> map);

    //分页查询检测模型
    List<Map<String, Object>> getModelPageInfoAll(Map<String, Object> map);

    long getModelCountAll(Map<String, Object> map);

    long getMonitorModelCount(AdsCheckModelMapping adsCheckModelMapping);
    void addMonitorModel(AdsCheckModelMapping adsCheckModelMapping);

    int deleteCheckModel(AdsCheckModelMapping adsCheckModelMapping);

    void updateByPrimaryKey(AdsCheckModelMapping adsCheckModelMapping);
    AdsCheckModelMapping selectByPrimaryKey(String modelId);

    List<AdsCheckModelMapping> getProductNameList(String modelId);

    List<AdsCheckModelMapping> getModelConfigListOrderByProductName(String modelId);

    //分页查询检测模型配置信息
    List<Map<String, Object>> getModelConfigPageInfoAll(Map<String, Object> map);
    long getModelConfigCountAll(Map<String, Object> map);
    void saveModelConfig(AdsCheckModelMapping adsCheckModelMapping);

    void addModelConfigValue(AdsCheckModelMapping adsCheckModelMapping);

    List<Map<String, Object>> getModelConfigDetail(Map<String, Object> map);

    long getModelConfigDetailCount(Map<String, Object> map);

    List<AdsCheckModelMapping> getPackageCheckItems(Map<String, Object> map);

    void enableAdsCheckModel(AdsCheckModelMapping adsCheckModelMapping);

    void delAdsCheckModelCongfig(AdsCheckModelMapping adsCheckModelMapping);

    long isExist(AdsCheckModelMapping adsCheckModelMapping);

    /**
     *  监测任务查询可使用检测模型列表
     */
    List<Map<String,Object>> selectAllModel(Map<String, Object> map);

    /**
     *  根据检测模型id查询相关检测项目
     */
    List<String> getCheckProductNameListByModelId(String modelId);

    /**
     *  根据检测模型id查询相关检测对象
     */
    List<String> getCheckObjectNameListByModelId(String modelId);

    /**
     *  根据检测模型id查询相关行业
     */
    String getIndustryByModelId(String modelId);

    List<AdsCheckModelMapping> queryByModelIdAndName(Map<String, Object> map);

}