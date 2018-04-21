package com.sofn.provider.ads;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.ads.AdsNewModelExpandMapper;
import com.sofn.model.generator.AdsCheckModelMapping;
import com.sofn.thread.ads.SaveCheckPackageItemsThread;
import com.sofn.thread.ads.SaveModelConfigThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@DubboService(interfaceClass = AdsNewModelProvider.class)
public class AdsNewModelProviderImpl extends BaseProviderImpl<AdsCheckModelMapping> implements AdsNewModelProvider {
    @Autowired
    private AdsNewModelExpandMapper adsNewModelExpandMapper;


    public void excelInsert(List<AdsCheckModelMapping> checkItemsList) {
        adsNewModelExpandMapper.excelInsert(checkItemsList);

    }

    public int addCheckItems(AdsCheckModelMapping adsCheckModelMapping) {
        int result = 0;
        long count = adsNewModelExpandMapper.getCheckItemsCount(adsCheckModelMapping);
        if (count > 0) {
            result = 1;//表示新增项目已经存在
        } else {
            adsNewModelExpandMapper.addCheckItems(adsCheckModelMapping);
            result = 2;
        }
        return result;
    }

    public AdsCheckModelMapping getCheckItemById(String checkId) {
        return adsNewModelExpandMapper.getCheckItemById(checkId);
    }

    public int saveCheckItem(AdsCheckModelMapping adsCheckModelMapping) {
        //先判断该检测项目是否已被引用
        int result = 0;
        long count1 = adsNewModelExpandMapper.isOrNotUse(adsCheckModelMapping);
        if (count1 > 0) {
            result = 3;//表示该检测项目已被模型引用
            return result;
        }
        long count = adsNewModelExpandMapper.getCheckItemsCount(adsCheckModelMapping);
        if (count > 0) {
            result = 1;//表示新增项目已经存在
        } else {
            adsNewModelExpandMapper.saveCheckItem(adsCheckModelMapping);
            result = 2;
        }
        return result;
    }

    public int isOrNotUse(AdsCheckModelMapping adsCheckModelMapping) {
        int result = 0;
        long count1 = adsNewModelExpandMapper.isOrNotUse(adsCheckModelMapping);
        if (count1 > 0) {
            result = 3;//表示该检测项目已被模型引用
        } else {
            result = 2;
        }
        return result;
    }

    public PageInfo<AdsCheckModelMapping> getPageInfoAll(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsNewModelExpandMapper.getPageInfoAll(map);
        long count = adsNewModelExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsCheckModelMapping> getPageInfoAllByDetail(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsNewModelExpandMapper.getPageInfoAllByDetail(map);
        long count = adsNewModelExpandMapper.getCountAllByDetail(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public int deleteAll(AdsCheckModelMapping adsCheckModelMapping) {
        int result = 0;
        if (adsCheckModelMapping != null) {
            String ids = adsCheckModelMapping.getIds();
            if (ids != null && ids.length() > 0) {
                String[] arr = ids.split(",");
                if (arr != null && arr.length > 0) {
                    for (String id : arr) {
                        AdsCheckModelMapping modelMapping = new AdsCheckModelMapping();
                        modelMapping.setCheckId(id);
                        modelMapping.setCreateDate(new Date());
                        adsNewModelExpandMapper.deleteByID(modelMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;

    }

    //检测模型的分页查询
    public PageInfo<AdsCheckModelMapping> getModelPageInfoAll(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsNewModelExpandMapper.getModelPageInfoAll(map);
        long count = adsNewModelExpandMapper.getModelCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    //新增监测模型配置
    public int addMonitorModel(AdsCheckModelMapping adsCheckModelMapping) {
        int result = 0;
        long count = adsNewModelExpandMapper.getMonitorModelCount(adsCheckModelMapping);
        if (count > 0) {
            result = 1;//表示新增项目已经存在
        } else {
            adsNewModelExpandMapper.addMonitorModel(adsCheckModelMapping);
            result = 2;
        }
        return result;
    }


    //删除检测模型
    public int deleteCheckModel(AdsCheckModelMapping adsCheckModelMapping) {
        int result = 0;
        if (adsCheckModelMapping != null) {
            String ids = adsCheckModelMapping.getIds();
            if (ids != null && ids.length() > 0) {
                String[] arr = ids.split(",");
                if (arr != null && arr.length > 0) {
                    for (String id : arr) {
                        AdsCheckModelMapping modelMapping = new AdsCheckModelMapping();
                        modelMapping.setModelId(id);
                        modelMapping.setUpdateDate(new Date());
                        modelMapping.setUpdateBy(adsCheckModelMapping.getUpdateBy());
                        adsNewModelExpandMapper.deleteCheckModel(modelMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;

    }

    //删除检测模型配置信息
    public int delAdsCheckModelCongif(AdsCheckModelMapping adsCheckModelMapping) {
        //先判断是否启用  已启用则不能删
        int result = 0;
        if (adsCheckModelMapping != null) {
            String ids = adsCheckModelMapping.getIds();
            if (ids != null && ids.length() > 0) {
                long count = adsNewModelExpandMapper.isExist(adsCheckModelMapping);
                if (count > 0) {
                    result = 1;//改模型已启用  不能删除模型配置项
                } else {
                    String[] arr = ids.split(",");
                    if (arr != null && arr.length > 0) {
                        for (String id : arr) {
                            AdsCheckModelMapping modelMapping = new AdsCheckModelMapping();
                            modelMapping.setId(id);
                            adsNewModelExpandMapper.delAdsCheckModelCongfig(modelMapping);
                            result = 2;
                        }
                    }
                }

            }
        }
        return result;
    }

    public void updateByPrimaryKey(AdsCheckModelMapping adsCheckModelMapping) {
        adsCheckModelMapping.setUpdateDate(new Date());
        adsNewModelExpandMapper.updateByPrimaryKey(adsCheckModelMapping);
    }

    public AdsCheckModelMapping selectByPrimaryKey(String modelId) {
        AdsCheckModelMapping adsCheckModelMapping = this.adsNewModelExpandMapper.selectByPrimaryKey(modelId);
        return adsCheckModelMapping;
    }

    public List<AdsCheckModelMapping> getProductNameList(String modelId) {
        return adsNewModelExpandMapper.getProductNameList(modelId);
    }

    public List<AdsCheckModelMapping> getModelConfigListOrderByProductName(String modelId) {
        List<AdsCheckModelMapping> list = adsNewModelExpandMapper.getModelConfigListOrderByProductName(modelId);
        return list;
    }

    public PageInfo<AdsCheckModelMapping> getModelConfigPageInfoAll(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsNewModelExpandMapper.getModelConfigPageInfoAll(map);
        long count = adsNewModelExpandMapper.getModelConfigCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Transactional
    public int saveModelConfig(AdsCheckModelMapping adsCheckModelMapping) {
        //result 忽略冲突项个数
        int result = 0;
        //通过redis保存忽略冲突个数
        JSONObject jObject = new JSONObject();
        jObject.put("result", result);
        jObject.put("Finished", 0);

        RedisUtil.set(adsCheckModelMapping.getModelId(), jObject);

        Thread thread = new SaveModelConfigThread(adsCheckModelMapping, adsNewModelExpandMapper);
        thread.start();
        return result;
    }

    public void addModelConfigValue(AdsCheckModelMapping adsCheckModelMapping) {
        if (adsCheckModelMapping != null) {
            if (adsCheckModelMapping != null) {
                String ids = adsCheckModelMapping.getIds();
                String unitMeasureStr = adsCheckModelMapping.getUnitMeasure();
                String[] unitMeasureArr = new String[]{};
                if (StringUtils.isNotBlank(unitMeasureStr)) {
                    unitMeasureArr = unitMeasureStr.split(",");
                }
                if (ids != null && ids.length() > 0) {
                    String[] arr = ids.split(",");
                    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
                    if (arr != null && arr.length > 0) {
                        for (int i = 0; 2 * i < arrayList.size(); i++) {
                            AdsCheckModelMapping modelMapping = new AdsCheckModelMapping();
                            if (("ND").equals(arrayList.get(2 * i))) {
                                modelMapping.setMaxValue(0);
                            } else {
                                modelMapping.setMaxValue((Float.parseFloat(arrayList.get(2 * i))));
                            }
                            modelMapping.setId(arrayList.get(2 * i + 1));
                            modelMapping.setUnitMeasure(unitMeasureArr[2 * i]);
                            adsNewModelExpandMapper.addModelConfigValue(modelMapping);
                        }

                    }
                }
            }
        }
    }

    public PageInfo<AdsCheckModelMapping> getModelConfigDetail(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = adsNewModelExpandMapper.getModelConfigDetail(map);
        long count = adsNewModelExpandMapper.getModelConfigDetailCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Transactional
    public int saveCheckPackageItems(AdsCheckModelMapping adsCheckModelMapping) {
        //result 忽略的冲突项数
        int result = 0;
        //通过redis保存忽略冲突个数
        JSONObject jObject = new JSONObject();
        jObject.put("result", result);
        jObject.put("Finished", 0);

        RedisUtil.set(adsCheckModelMapping.getModelId(), jObject);

        Thread thread = new SaveCheckPackageItemsThread(adsCheckModelMapping, adsNewModelExpandMapper);
        thread.start();

        return result;
    }

    public int enableAdsCheckModel(AdsCheckModelMapping adsCheckModelMapping) {
        //更新前先判断该模型是否有配置信息
        int result;
        Map<String, Object> map = new HashMap<>();
        map.put("modelId", adsCheckModelMapping.getModelId());
        long count = adsNewModelExpandMapper.getModelConfigDetailCount(map);
        if (count > 0) {
            adsNewModelExpandMapper.enableAdsCheckModel(adsCheckModelMapping);
            result = 1;//已启用
        } else {
            result = 2;//请先进行模型配置
        }
        return result;
    }

    //监测任务查询可使用检测模型列表
    public List<Map<String, Object>> selectAllModel(Map<String, Object> map) {
        List<Map<String, Object>> list = adsNewModelExpandMapper.selectAllModel(map);
        return list;
    }

    public List<AdsCheckModelMapping> queryByModelIdAndName(Map<String, Object> map) {
        List<AdsCheckModelMapping> list = adsNewModelExpandMapper.queryByModelIdAndName(map);
        return list;
    }
}
