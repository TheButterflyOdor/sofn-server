package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsModelCheckItemExpandMapper;
import com.sofn.dao.generator.AdsModelCheckObjectMapper;
import com.sofn.dao.ads.AdsModelCheckObjectExpandMapper;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelCheckObject;
import com.sofn.model.generator.AdsModelObjectItemMapping;
import com.sofn.provider.ads.AdsModelCheckObjectProvider;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	模型_检测对象 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsModelCheckObjectProvider.class)
public class AdsModelCheckObjectProviderImpl extends BaseProviderImpl<AdsModelCheckObject> implements AdsModelCheckObjectProvider {

    @Autowired
    private AdsModelCheckObjectExpandMapper AdsModelCheckObjectExpandMapper;
    @Autowired
    private AdsModelCheckObjectMapper adsModelCheckObjectMapper;
    @Autowired
    private AdsModelCheckItemExpandMapper adsModelCheckItemExpandMapper;

    public PageInfo<AdsModelCheckObject> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckObjectExpandMapper.getPageInfo(map);
        long count = AdsModelCheckObjectExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<AdsModelCheckObject> getPageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckObjectExpandMapper.getPageInfoAll(map);
        long count = AdsModelCheckObjectExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<AdsModelCheckObject> getPageInfoByModelId(Map<String, Object> map) {
        //根据模型id查询检测对象
        PageInfo pageInfo = new PageInfo();
        List<AdsModelCheckObject> list = AdsModelCheckObjectExpandMapper.getPageInfoByModelId(map);
        long count = AdsModelCheckObjectExpandMapper.getCountByModelId(map);
        if(list!=null && list.size()>0){
            for(AdsModelCheckObject obj : list){
                //查询检测对象下的检测项目
                List<AdsModelCheckItem> items = adsModelCheckItemExpandMapper.getPageInfoByCheckObjectId(obj.getId());
                obj.setItemList(items);
            }
        }
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    @Transactional
    public int insert(AdsModelCheckObject adsModelCheckObject){
        int result = 0;
        if(null != adsModelCheckObject.getName()){
            adsModelCheckObject.setName(adsModelCheckObject.getName().trim());
            Map<String,Object> map = new HashedMap();
            map.put("name",adsModelCheckObject.getName().trim());
            long count = AdsModelCheckObjectExpandMapper.selectByName(map);
            if(count > 0 ){
                result = 2;
            }else{
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.replace("-", "");
                adsModelCheckObject.setId(uuid);
                adsModelCheckObjectMapper.insert(adsModelCheckObject);
                result = 1;
            }
        }
        return result;
    }


    @Transactional
    public int updateByPrimaryKey(AdsModelCheckObject adsModelCheckObject){
        int result = 0;
        System.out.print(adsModelCheckObject.getName());
        if(null != adsModelCheckObject.getId() && null != adsModelCheckObject.getName()){
            adsModelCheckObject.setName(adsModelCheckObject.getName().trim());
            Map<String,Object> map = new HashedMap();
            map.put("name",adsModelCheckObject.getName().trim());
            map.put("id",adsModelCheckObject.getId());
            long count = AdsModelCheckObjectExpandMapper.selectByName(map);
            if(count > 0 ){
                result = 2;
            }else{
                adsModelCheckObjectMapper.update(adsModelCheckObject);
                result = 1;
            }
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteByID(AdsModelCheckObject adsModelCheckObject){
        int result = 0;
        if(null != adsModelCheckObject){
            adsModelCheckObjectMapper.deleteByID(adsModelCheckObject);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteAll(AdsModelCheckObject adsModelCheckObject) {
        int result = 0;
        if(adsModelCheckObject!=null){
            String ids = adsModelCheckObject.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelCheckObject ModelCheckObject = new AdsModelCheckObject();
                        ModelCheckObject.setId(id);
                        ModelCheckObject.setUpdateBy(adsModelCheckObject.getUpdateBy());
                        adsModelCheckObjectMapper.deleteByID(ModelCheckObject);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public AdsModelCheckObject queryByModelIdWithName(Map<String, Object> map){
        AdsModelCheckObject adsModelCheckObject = AdsModelCheckObjectExpandMapper.queryByModelIdWithName(map);
        return adsModelCheckObject;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelCheckItem> getPageInfoChecked(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckObjectExpandMapper.getPageInfoChecked(map);
        long count = AdsModelCheckObjectExpandMapper.getCountChecked(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelCheckItem> getPageInfoUnChecked(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckObjectExpandMapper.getPageInfoUnChecked(map);
        long count = AdsModelCheckObjectExpandMapper.getCountUnChecked(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int insertIntoMapping(AdsModelObjectItemMapping adsModelObjectItemMapping) {
        int result = 0;
        if(adsModelObjectItemMapping!=null){
            String ids = adsModelObjectItemMapping.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelObjectItemMapping ModelObjectItemMapping = new AdsModelObjectItemMapping();
                        String uuid = UUID.randomUUID().toString();
                        uuid = uuid.replace("-", "");
                        ModelObjectItemMapping.setId(uuid);
                        ModelObjectItemMapping.setItemId(id);
                        ModelObjectItemMapping.setObjectId(adsModelObjectItemMapping.getObjectId());
                        ModelObjectItemMapping.setCreateBy(adsModelObjectItemMapping.getCreateBy());
                        AdsModelCheckObjectExpandMapper.insertIntoMapping(ModelObjectItemMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteMappingByID(AdsModelObjectItemMapping adsModelObjectItemMapping){
        int result = 0;
        if(null != adsModelObjectItemMapping){
            AdsModelCheckObjectExpandMapper.deleteMappingByID(adsModelObjectItemMapping);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteMappingAll(AdsModelObjectItemMapping adsModelObjectItemMapping){
        int result = 0;
        if(adsModelObjectItemMapping!=null){
            String ids = adsModelObjectItemMapping.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelObjectItemMapping ModelObjectItemMapping = new AdsModelObjectItemMapping();
                        ModelObjectItemMapping.setId(id);
                        AdsModelCheckObjectExpandMapper.deleteMappingByID(ModelObjectItemMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public AdsModelCheckObject queryByName(Map<String, Object> map){
        AdsModelCheckObject adsModelCheckObject = AdsModelCheckObjectExpandMapper.queryByName(map);
        return adsModelCheckObject;
    }

}
