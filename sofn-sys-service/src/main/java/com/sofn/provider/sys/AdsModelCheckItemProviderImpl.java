package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsModelCheckItemMapper;
import com.sofn.dao.sys.AdsModelCheckItemExpandMapper;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelObjectItemMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	模型_检测项目 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsModelCheckItemProvider.class)
public class AdsModelCheckItemProviderImpl extends BaseProviderImpl<AdsModelCheckItem> implements AdsModelCheckItemProvider {

    @Autowired
    private AdsModelCheckItemExpandMapper AdsModelCheckItemExpandMapper;
    @Autowired
    private AdsModelCheckItemMapper adsModelCheckItemMapper;

    public PageInfo<AdsModelCheckItem> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckItemExpandMapper.getPageInfo(map);
        long count = AdsModelCheckItemExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int insert(AdsModelCheckItem adsModelCheckItem) {
        int result =0;
        if(adsModelCheckItem!=null){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsModelCheckItem.setId(uuid);
            adsModelCheckItemMapper.insert(adsModelCheckItem);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int batchDelete(AdsModelCheckItem adsModelCheckItem) {
        int result =0;
        if(adsModelCheckItem!=null){
            String items = adsModelCheckItem.getItem_ids();
            if(items!=null && items.length() > 0){
                String[] ids = items.split(",");
                if(ids!=null && ids.length > 0){
                    for(String id : ids){
                        AdsModelCheckItem item = new AdsModelCheckItem();
                        item.setId(id);
                        item.setUpdateBy(adsModelCheckItem.getUpdateBy());
                        adsModelCheckItemMapper.deleteByLogic(item);    //逻辑删除检测项目
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelCheckItem> getPageInfoChecked(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckItemExpandMapper.getPageInfoChecked(map);
        long count = AdsModelCheckItemExpandMapper.getCountChecked(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelCheckItem> getPageInfoUnChecked(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckItemExpandMapper.getPageInfoUnChecked(map);
        long count = AdsModelCheckItemExpandMapper.getCountUnChecked(map);
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
                        adsModelCheckItemMapper.insertIntoMapping(ModelObjectItemMapping);
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
            adsModelCheckItemMapper.deleteMappingByID(adsModelObjectItemMapping);
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
                        adsModelCheckItemMapper.deleteMappingByID(ModelObjectItemMapping);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    public List<AdsModelCheckItem> getCheckItemListByModelId(String model_id) {
        return AdsModelCheckItemExpandMapper.getCheckItemListByModelId(model_id);
    }

    @Override
    @Transactional
    public List<AdsModelCheckItem> queryByObjId(Map<String, Object> map){
        List<AdsModelCheckItem> list = AdsModelCheckItemExpandMapper.queryByObjId(map);
        return list;
    }

}
