package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsModelSampleLinkMapper;
import com.sofn.dao.sys.AdsModelSampleLinkExpandMapper;
import com.sofn.model.generator.AdsModelSampleLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	模型_抽样环节 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsModelSampleLinkProvider.class)
public class AdsModelSampleLinkProviderImpl extends BaseProviderImpl<AdsModelSampleLink> implements AdsModelSampleLinkProvider {

    @Autowired
    private AdsModelSampleLinkExpandMapper AdsModelSampleLinkExpandMapper;
    @Autowired
    private AdsModelSampleLinkMapper adsModelSampleLinkMapper;

    public PageInfo<AdsModelSampleLink> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelSampleLinkExpandMapper.getPageInfo(map);
        long count = AdsModelSampleLinkExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public PageInfo<AdsModelSampleLink> getPageInfoAll(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsModelSampleLinkExpandMapper.getPageInfoAll(map);
        long count = AdsModelSampleLinkExpandMapper.getCountAll(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    @Transactional
    public int insert(AdsModelSampleLink adsModelSampleLink){
        int result = 0;
        if(null != adsModelSampleLink.getName()){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsModelSampleLink.setId(uuid);
            adsModelSampleLinkMapper.insert(adsModelSampleLink);
            result = 1;
        }
        return result;
    }


    @Transactional
    public int updateByPrimaryKey(AdsModelSampleLink adsModelSampleLink){
        int result = 0;
        if(null != adsModelSampleLink.getId() && null != adsModelSampleLink.getName()){
            adsModelSampleLinkMapper.update(adsModelSampleLink);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteByID(AdsModelSampleLink adsModelSampleLink){
        int result = 0;
        if(null != adsModelSampleLink){
            adsModelSampleLinkMapper.deleteByID(adsModelSampleLink);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteAll(AdsModelSampleLink adsModelSampleLink) {
        int result = 0;
        if(adsModelSampleLink!=null){
            String ids = adsModelSampleLink.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsModelSampleLink ModelSampleLink = new AdsModelSampleLink();
                        ModelSampleLink.setId(id);
                        ModelSampleLink.setUpdateBy(adsModelSampleLink.getUpdateBy());
                        adsModelSampleLinkMapper.deleteByID(ModelSampleLink);
                    }
                    result = 1;
                }
            }
        }
        return result;
    }

}
