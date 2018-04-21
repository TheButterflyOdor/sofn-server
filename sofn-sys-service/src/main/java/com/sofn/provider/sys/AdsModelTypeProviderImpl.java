package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsModelTypeMapper;
import com.sofn.dao.sys.AdsModelTypeExpandMapper;
import com.sofn.model.generator.AdsModelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	模型_种类 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsModelTypeProvider.class)
public class AdsModelTypeProviderImpl extends BaseProviderImpl<AdsModelType> implements AdsModelTypeProvider {

    @Autowired
    private AdsModelTypeExpandMapper AdsModelTypeExpandMapper;
    @Autowired
    private AdsModelTypeMapper adsModelTypeMapper;

    public PageInfo<AdsModelType> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelTypeExpandMapper.getPageInfo(map);
        long count = AdsModelTypeExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelType> getPageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelTypeExpandMapper.getPageInfoAll(map);
        long count = AdsModelTypeExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    @Transactional
    public int insert(AdsModelType adsModelType){
        int result = 0;
        if(null != adsModelType.getName()){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsModelType.setId(uuid);
            adsModelTypeMapper.insert(adsModelType);
            result = 1;
        }
        return result;
    }


    @Transactional
    public int updateByPrimaryKey(AdsModelType adsModelType){
        int result = 0;
        if(null != adsModelType.getId() && null != adsModelType.getName()){
            adsModelTypeMapper.update(adsModelType);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteByID(AdsModelType adsModelType){
        int result = 0;
        if(null != adsModelType){
            adsModelTypeMapper.deleteByID(adsModelType);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteAll(AdsModelType adsModelType) {
        int result = 0;
        if(adsModelType!=null){
            String ids = adsModelType.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelType ModelType = new AdsModelType();
                        ModelType.setId(id);
                        ModelType.setUpdateBy(adsModelType.getUpdateBy());
                        adsModelTypeMapper.deleteByID(ModelType);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

}
