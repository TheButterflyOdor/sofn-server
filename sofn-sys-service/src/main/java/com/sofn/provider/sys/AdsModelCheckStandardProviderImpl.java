package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsModelCheckStandardMapper;
import com.sofn.dao.sys.AdsModelCheckStandardExpandMapper;
import com.sofn.model.generator.AdsModelCheckStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	模型_检测标准 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsModelCheckStandardProvider.class)
public class AdsModelCheckStandardProviderImpl extends BaseProviderImpl<AdsModelCheckStandard> implements AdsModelCheckStandardProvider {

    @Autowired
    private AdsModelCheckStandardExpandMapper AdsModelCheckStandardExpandMapper;
    @Autowired
    private AdsModelCheckStandardMapper adsModelCheckStandardMapper;


    public PageInfo<AdsModelCheckStandard> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckStandardExpandMapper.getPageInfo(map);
        long count = AdsModelCheckStandardExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelCheckStandard> getPageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelCheckStandardExpandMapper.getPageInfoAll(map);
        long count = AdsModelCheckStandardExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int insert(AdsModelCheckStandard adsModelCheckStandard){
        int result = 0;
        if(null != adsModelCheckStandard.getName() && null != adsModelCheckStandard.getValue()){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsModelCheckStandard.setId(uuid);
            adsModelCheckStandardMapper.insert(adsModelCheckStandard);
            result = 1;
        }
        return result;
    }

    @Transactional
    public int updateByPrimaryKey(AdsModelCheckStandard adsModelCheckStandard){
        int result = 0;
        if(null != adsModelCheckStandard.getId() && null != adsModelCheckStandard.getName() && null != adsModelCheckStandard.getValue()){
            adsModelCheckStandardMapper.update(adsModelCheckStandard);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteByID(AdsModelCheckStandard adsModelCheckStandard){
        int result = 0;
        if(null != adsModelCheckStandard){
            adsModelCheckStandardMapper.deleteByID(adsModelCheckStandard);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteAll(AdsModelCheckStandard adsModelCheckStandard) {
        int result = 0;
        if(adsModelCheckStandard!=null){
            String ids = adsModelCheckStandard.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelCheckStandard ModelCheckStandard = new AdsModelCheckStandard();
                        ModelCheckStandard.setId(id);
                        ModelCheckStandard.setUpdateBy(adsModelCheckStandard.getUpdateBy());
                        adsModelCheckStandardMapper.deleteByID(ModelCheckStandard);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public AdsModelCheckStandard queryByItemId(Map<String, Object> map){
        AdsModelCheckStandard adsModelCheckStandard = AdsModelCheckStandardExpandMapper.queryByItemId(map);
        return adsModelCheckStandard;
    }

}
