package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.model.generator.SysPesticideResidueModel;
import com.sofn.provider.ads.AdsNewModelProvider;
import com.sofn.provider.sys.SysPesticideResidueProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@Service
public class AdsNewModelService extends BaseService<AdsNewModelProvider,AdsCheckModelMapping>{

    @DubboReference
    public void setAdsNewModelProvider(AdsNewModelProvider provider){
        this.provider = provider;
    }

    private SysPesticideResidueProvider sysPesticideResidueProvider;
    @DubboReference
    public void setSysPesticideResidueProvider(SysPesticideResidueProvider sysPesticideResidueProvider){
        this.sysPesticideResidueProvider = sysPesticideResidueProvider;
    }
    /**
     * excel数据导入
     * @param checkItemsList
     * @return
     */
    public void excelInsert(List<AdsCheckModelMapping> checkItemsList){
        provider.excelInsert(checkItemsList);
    }

    public int addCheckItems(AdsCheckModelMapping adsCheckModelMapping){
         return provider.addCheckItems(adsCheckModelMapping);
    }

    /**
     * 查询检测项目
     * @param pageNum
     * @param pageSize
     * @param itemName
     * @return
     */
    public PageInfo getPageInfoAll(int pageNum, int pageSize, String itemName,String idsArray) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("itemName", itemName);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        if(StringUtils.isNotBlank(idsArray)){
            queryParams.put("idsArray", idsArray);
            String[] arr = idsArray.split(",");
            ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
            queryParams.put("arrayList",arrayList);
        }
        return provider.getPageInfoAll(queryParams);
    }

    /**
     * 查询例行监测详情检测项目
     * @param pageNum
     * @param pageSize
     * @param itemName
     * @return
     */
    public PageInfo getPageInfoAllByDetail(int pageNum, int pageSize, String itemName,String nameArray) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("itemName", itemName);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        if(StringUtils.isNotBlank(nameArray)){
            queryParams.put("nameArray", nameArray);
            String[] arr = nameArray.split("、");
            ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
            queryParams.put("arrayList",arrayList);
        }
        return provider.getPageInfoAllByDetail(queryParams);
    }

    /**
     * 根据id查询检测项目名称
     * @param adsCheckModelMapping
     * @return
     */
    public AdsCheckModelMapping getCheckItemById( AdsCheckModelMapping adsCheckModelMapping){
        return provider.getCheckItemById(adsCheckModelMapping.getCheckId());
    }

    /**
     * 保存修改的检测项目
     * @param adsCheckModelMapping
     */
    public int saveCheckItem(AdsCheckModelMapping adsCheckModelMapping){
       return provider.saveCheckItem(adsCheckModelMapping);
    }

    /**
     * 判断该检测项是否被模型引用
     * @param adsCheckModelMapping
     * @return
     */
    public int isOrNotUse(AdsCheckModelMapping adsCheckModelMapping){
        return provider.isOrNotUse(adsCheckModelMapping);
    }

    public int deleteAll(AdsCheckModelMapping adsCheckModelMapping){
        return provider.deleteAll(adsCheckModelMapping);
    }

    /**
     * 查询检测模型
     * @param adsCheckModelMapping
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    public PageInfo getModelPageInfoAll(AdsCheckModelMapping adsCheckModelMapping, int pageNum, int pageSize, String modelName,String industry) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelName", modelName);
        queryParams.put("organId", adsCheckModelMapping.getOrganId());
        queryParams.put("industry", industry);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organId",adsCheckModelMapping.getOrganId());
        return provider.getModelPageInfoAll(queryParams);
    }

    /**
     * 新增监测模型配置
     * @param adsCheckModelMapping
     * @return
     */
    public int addMonitorModel(AdsCheckModelMapping adsCheckModelMapping){
        return provider.addMonitorModel(adsCheckModelMapping);
    }

    public int deleteCheckModel(AdsCheckModelMapping adsCheckModelMapping){
        return provider.deleteCheckModel(adsCheckModelMapping);
    }

    /**
     * g根据id更新检测模型信息
     * @param adsCheckModelMapping
     */
    public void updateByPrimaryKey(AdsCheckModelMapping adsCheckModelMapping){
        provider.updateByPrimaryKey(adsCheckModelMapping);
    }


    /**
     * 根据modelId 查询模型信息
     * @param modelId
     * @return
     */
    public AdsCheckModelMapping selectByPrimaryKey(String modelId){
        return provider.selectByPrimaryKey(modelId);
    }

    public Map<String,List<AdsCheckModelMapping>> getModelConfigGroupList(AdsCheckModelMapping adsCheckModelMapping){
        String modelId = adsCheckModelMapping.getModelId();
        List<AdsCheckModelMapping> productNameList = provider.getProductNameList(modelId);
        List<AdsCheckModelMapping> modelMappingList = provider.getModelConfigListOrderByProductName(modelId);

        Map<String,List<AdsCheckModelMapping>> mapList = new HashMap<>();
        if(!productNameList.isEmpty() && !modelMappingList.isEmpty()){
            for(int i=0; i<productNameList.size(); i++){
                List<AdsCheckModelMapping> modelMappingTempList = new ArrayList<>();
                AdsCheckModelMapping productNameTempModel = productNameList.get(i);
                String name1 = productNameTempModel.getProductName();
                for(int j=0; j<modelMappingList.size(); j++){
                    AdsCheckModelMapping modelMappingTempModel = modelMappingList.get(j);
                    String name2 = modelMappingTempModel.getProductName();
                    if(name1.equals(name2)){
                        modelMappingTempList.add(modelMappingList.get(j));
                    }
                }
                mapList.put(name1,modelMappingTempList);
            }
        }

        Page page = new Page();
        page.setPageNumber(1L);
        page.setLength(10L);
        Set<String> keySet = mapList.keySet();
        for(Iterator<String> iterator = keySet.iterator(); iterator.hasNext();){
            String key = iterator.next();
            List<AdsCheckModelMapping> valueList = mapList.get(key);
            for(int k=0; k<valueList.size(); k++){
                AdsCheckModelMapping modelMappingTemp = valueList.get(k);
                String testObjectName = modelMappingTemp.getProductName();
                String testItemName = modelMappingTemp.getCheckName();
                String standardCode = modelMappingTemp.getCheckId();
                if(StringUtils.isNotBlank(standardCode)&&standardCode.contains("_")){
                    standardCode = standardCode.split("_")[0];
                }else{
                    standardCode = null;
                }
                PageInfo<SysPesticideResidueModel> pageInfo= sysPesticideResidueProvider.getPesticideResidues(standardCode, testItemName, testObjectName, page);
                if(pageInfo!=null && pageInfo.getList().size()>0){
                    SysPesticideResidueModel sysPesticideResidueModel= pageInfo.getList().get(0);
                    if(sysPesticideResidueModel!=null){
                        if(modelMappingTemp.getMaxValue()==0){
                            modelMappingTemp.setMaxValue(Float.parseFloat(sysPesticideResidueModel.getUpperBound()==null?"0":sysPesticideResidueModel.getUpperBound().toString()));
                        }
                        if(modelMappingTemp.getUnitMeasure()==null){
                            modelMappingTemp.setUnitMeasure(sysPesticideResidueModel.getUnitMeasure());
                        }
                    }
                }
                valueList.set(k,modelMappingTemp);
            }
        }

        return mapList;
    }

    public PageInfo getModelConfigPageInfoAll(AdsCheckModelMapping adsCheckModelMapping, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId",adsCheckModelMapping.getModelId());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        PageInfo<AdsCheckModelMapping> pageInfo =  provider.getModelConfigPageInfoAll(queryParams);
        List<AdsCheckModelMapping> list = pageInfo.getList();
        List<AdsCheckModelMapping> ModelMappingList = new ArrayList();
        Page page = new Page();
        page.setPageNumber(1L);
        page.setLength(10L);
        PageInfo<SysPesticideResidueModel> pageInfo1 = null;
        AdsCheckModelMapping adsCheckModelMappingTemp = null;
        SysPesticideResidueModel sysPesticideResidueModel = null;
        String testObjectName = null;
        String testItemName = null;
        String standardCode = null;
        for(int i=0;i<list.size();i++){
            adsCheckModelMappingTemp = list.get(i);
            testObjectName = adsCheckModelMappingTemp.getProductName();
            testItemName = adsCheckModelMappingTemp.getCheckName();
            standardCode = adsCheckModelMappingTemp.getCheckId();
            if(StringUtils.isNotBlank(standardCode)&&standardCode.contains("_")){
                standardCode = standardCode.split("_")[0];
            }else{
                standardCode = null;
            }
            pageInfo1 = sysPesticideResidueProvider.getPesticideResidues(standardCode, testItemName, testObjectName, page);
            if(pageInfo1!=null && pageInfo1.getList().size()>0){
                sysPesticideResidueModel = pageInfo1.getList().get(0);
                if(sysPesticideResidueModel!=null){
                    if(adsCheckModelMappingTemp.getMaxValue()==0){
                        adsCheckModelMappingTemp.setMaxValue(Float.parseFloat(sysPesticideResidueModel.getUpperBound()==null?"0":sysPesticideResidueModel.getUpperBound().toString()));
                    }
                    if(adsCheckModelMappingTemp.getUnitMeasure()==null){
                        adsCheckModelMappingTemp.setUnitMeasure(sysPesticideResidueModel.getUnitMeasure());
                    }
                }

            }
            ModelMappingList.add(adsCheckModelMappingTemp);
        }
        pageInfo.setList(ModelMappingList);
        return pageInfo;
    }

    /**
     * 批量保存模型配置信息
     * @param adsCheckModelMapping
     */
    public int  saveModelConfig(AdsCheckModelMapping adsCheckModelMapping){
       return provider.saveModelConfig(adsCheckModelMapping);
    }

    /**
     * 批量保存模型配置的值
     * @param adsCheckModelMapping
     */
    public void addModelConfigValue(AdsCheckModelMapping adsCheckModelMapping){
        provider.addModelConfigValue(adsCheckModelMapping);
    }


    /**
     * 查询监测模型详情信息
     * @param adsCheckModelMapping
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getModelConfigDetail(AdsCheckModelMapping adsCheckModelMapping,int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId",adsCheckModelMapping.getModelId());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        PageInfo<AdsCheckModelMapping> pageInfo =  provider.getModelConfigPageInfoAll(queryParams);
        List<AdsCheckModelMapping> list = pageInfo.getList();
        List<AdsCheckModelMapping> ModelMappingList = new ArrayList();
        Page page = new Page();
        page.setPageNumber(1L);
        page.setLength(10L);
        PageInfo<SysPesticideResidueModel> pageInfo1 = null;
        AdsCheckModelMapping adsCheckModelMappingTemp = null;
        SysPesticideResidueModel sysPesticideResidueModel = null;
        String testObjectName = null;
        String testItemName = null;
        String standardCode = null;
        for(int i=0;i<list.size();i++){
            adsCheckModelMappingTemp = list.get(i);
            testObjectName = adsCheckModelMappingTemp.getProductName();
            testItemName = adsCheckModelMappingTemp.getCheckName();
            standardCode = adsCheckModelMappingTemp.getCheckId();
            if(StringUtils.isNotBlank(standardCode)&&standardCode.contains("_")){
                standardCode = standardCode.split("_")[0];
            }else{
                standardCode = null;
            }
            pageInfo1 = sysPesticideResidueProvider.getPesticideResidues(standardCode, testItemName, testObjectName, page);
            if(pageInfo1!=null && pageInfo1.getList().size()>0){
                sysPesticideResidueModel = pageInfo1.getList().get(0);
                if(sysPesticideResidueModel!=null){
                    if(adsCheckModelMappingTemp.getMaxValue()==0){
                        adsCheckModelMappingTemp.setMaxValue(Float.parseFloat(sysPesticideResidueModel.getUpperBound()==null?"0":sysPesticideResidueModel.getUpperBound().toString()));
                    }
                    if(adsCheckModelMappingTemp.getUnitMeasure()==null){
                        adsCheckModelMappingTemp.setUnitMeasure(sysPesticideResidueModel.getUnitMeasure());
                    }
                }

            }
            ModelMappingList.add(adsCheckModelMappingTemp);
        }
        pageInfo.setList(ModelMappingList);
        return pageInfo;
    }

    /**
     * 批量保存检测包中的检测项
     * @param adsCheckModelMapping
     */
    public int saveCheckPackageItems(AdsCheckModelMapping adsCheckModelMapping){
        return provider.saveCheckPackageItems(adsCheckModelMapping);
    }

    /**
     * 启用模型配置
     * @param adsCheckModelMapping
     * @return
     */
    public int enableAdsCheckModel(AdsCheckModelMapping adsCheckModelMapping){
        return provider.enableAdsCheckModel(adsCheckModelMapping);
    }

    /**
     * 删除检测模型配置信息
     * @param adsCheckModelMapping
     */
    public int  delAdsCheckModelCongif(AdsCheckModelMapping adsCheckModelMapping){
        return  provider.delAdsCheckModelCongif(adsCheckModelMapping);
    }

    /**
     * 监测任务查询可使用检测模型列表
     * @param organId
     * @param
     * @return
     */
    public List<Map<String,Object>> selectAllModel(String organId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organId",organId);
        queryParams.put("isEnable","1");
        return provider.selectAllModel(queryParams);
    }

    /**
     * 通过模型ID和样品名称去获取所有的检测项目
     * @param
     * @return
     */
    public List<AdsCheckModelMapping> queryByModelIdAndName(String modelId,String name){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("modelId",modelId);
        queryParams.put("name",name);
        return provider.queryByModelIdAndName(queryParams);
    }

}
