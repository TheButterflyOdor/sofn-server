package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsCheckModelMapper;
import com.sofn.dao.ads.AdsCheckModelExpandMapper;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsModelAttributeMapping;
import com.sofn.provider.ads.AdsCheckModelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	检测模型 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsCheckModelProvider.class)
public class AdsCheckModelProviderImpl extends BaseProviderImpl<AdsCheckModel> implements AdsCheckModelProvider {

    @Autowired
    private AdsCheckModelExpandMapper AdsCheckModelExpandMapper;
    @Autowired
    private AdsCheckModelMapper adsCheckModelMapper;

    public PageInfo<AdsCheckModel> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsCheckModelExpandMapper.getPageInfo(map);
        long count = AdsCheckModelExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<Map<String,Object>> selectAll(Map<String, Object> map) {
        List<Map<String,Object>> list = AdsCheckModelExpandMapper.selectAll(map);
        return list;
    }

    public PageInfo<AdsCheckModel> getPageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsCheckModelExpandMapper.getPageInfoAll(map);
        long count = AdsCheckModelExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int insert(AdsCheckModel adsCheckModel){
        int result = 0;
        if(null != adsCheckModel.getModelName()){
            adsCheckModel.setModelName(adsCheckModel.getModelName().trim());
            long count = AdsCheckModelExpandMapper.selectByName(adsCheckModel);
            if(count > 0 ){
                result = 2;
            }else{
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                adsCheckModel.setId(uuid);
                adsCheckModelMapper.insert(adsCheckModel);
                result = 1;
            }
        }
        return result;
    }

    @Transactional
    public int updateByPrimaryKey(AdsCheckModel adsCheckModel){
        int result = 0;
        if(null != adsCheckModel.getId() && null != adsCheckModel.getModelName()){
            adsCheckModel.setModelName(adsCheckModel.getModelName().trim());
            long count = AdsCheckModelExpandMapper.selectByName(adsCheckModel);
            if(count > 0 ){
                result = 2;
            }else{
                adsCheckModelMapper.update(adsCheckModel);
                result = 1;
            }
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteByID(AdsCheckModel adsCheckModel){
        int result = 0;
        if(null != adsCheckModel){
            adsCheckModelMapper.deleteByID(adsCheckModel);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteAll(AdsCheckModel adsCheckModel) {
        int result = 0;
        if(adsCheckModel!=null){
            String ids = adsCheckModel.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsCheckModel CheckModel = new AdsCheckModel();
                        CheckModel.setId(id);
                        CheckModel.setUpdateBy(adsCheckModel.getUpdateBy());
                        adsCheckModelMapper.deleteByID(CheckModel);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelAttributeMapping> getPageInfoChecked(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsCheckModelExpandMapper.getPageInfoChecked(map);
        long count = AdsCheckModelExpandMapper.getCountChecked(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelAttributeMapping> getPageInfoUnChecked(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsCheckModelExpandMapper.getPageInfoUnChecked(map);
        long count = AdsCheckModelExpandMapper.getCountUnChecked(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int insertIntoMapping(AdsModelAttributeMapping adsModelAttributeMapping) {
        int result = 0;
        if(adsModelAttributeMapping!=null){
            String ids = adsModelAttributeMapping.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelAttributeMapping ModelAttributeMapping = new AdsModelAttributeMapping();
                        String uuid = UUID.randomUUID().toString();
                        uuid = uuid.replace("-", "");
                        ModelAttributeMapping.setId(uuid);
                        ModelAttributeMapping.setModelId(adsModelAttributeMapping.getModelId());
                        ModelAttributeMapping.setObjectId(id);
                        ModelAttributeMapping.setType(adsModelAttributeMapping.getType());
                        ModelAttributeMapping.setCreateBy(adsModelAttributeMapping.getCreateBy());
                        adsCheckModelMapper.insertIntoMapping(ModelAttributeMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteMappingByID(AdsModelAttributeMapping adsModelAttributeMapping){
        int result = 0;
        if(null != adsModelAttributeMapping){
            adsModelAttributeMapping.setUpdateBy("3");
            adsCheckModelMapper.deleteMappingByID(adsModelAttributeMapping);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteMappingAll(AdsModelAttributeMapping adsModelAttributeMapping){
        int result = 0;
        if(adsModelAttributeMapping!=null){
            String ids = adsModelAttributeMapping.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelAttributeMapping ModelAttributeMapping = new AdsModelAttributeMapping();
                        ModelAttributeMapping.setId(id);
                        adsCheckModelMapper.deleteMappingByID(ModelAttributeMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public int enableAdsCheckModel(Map<String, Object> map){
        int result = 0;
        long count = AdsCheckModelExpandMapper.findCountOfCheckModel(map);
        if(count >= 3){
            AdsCheckModelExpandMapper.updateIsEnable(map);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelAttributeMapping> getPageInfoCheckedAndJudgeStandard(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsCheckModelExpandMapper.getPageInfoCheckedAndJudgeStandard(map);
        long count = AdsCheckModelExpandMapper.getCountCheckedAndJudgeStandard(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
