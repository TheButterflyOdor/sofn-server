package com.sofn.service.sys;

import com.sofn.core.base.BaseService;
import com.sofn.core.base.RedisService;
import com.sofn.core.constant.DictType;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.sys.SysDictTypeDto;
import com.sofn.provider.sys.SysDictProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/**
 * Created by: dong4j.
 * Date: 2016-10-19.
 * Time: 15:58.
 * Description:
 */
@Service
public class SysDictService extends BaseService<SysDictProvider, SysDictType>{
    /**
     * The Redis service.
     */
    @Autowired
    private RedisService redisService;

    /**
     * Set provider.
     *
     * @param provider the provider
     */
    @DubboReference
    public void setProvider(SysDictProvider provider){
        this.provider = provider;
    }

    /**
     * Get dict types list.
     *
     * @param type the type
     * @return the list
     */
    @Cacheable(value = "default")
    public List<SysDictTypeDto> getDictTypes(String type,String id,  String keyWord){
        return provider.getDictTypes(type,id,keyWord);
    }

    @Cacheable(value = "default")
    public List<SysDictType> getDictTypes2(String type,String id,  String keyWord){
        return provider.getDictTypes2(type,id,keyWord);
    }

    /**
     * Delete dict data sys dict data.
     *
     * @param idStr the id
     * @return the sys dict data
     */
    public Integer deleteDictData(String idStr){
        return provider.deleteDictData(idStr);
    }

    /**
     * Update valid sys dict data.
     *
     * @param id           the id
     * @param whetherValid the whether valid
     * @return the sys dict data
     */
    public SysDictData updateValid(String id, String whetherValid){
        return provider.updateValid(id, whetherValid);
    }

    public List<SysDictData> getDictDataByType(String id, String keyWord, Page page){
        return provider.getDictDataByType(id, keyWord,page);
    }

    public SysDictData addDictData(SysDictData sysDictData){
        return provider.addDictData(sysDictData);
    }

    public SysDictData getDictDataById(String id){
        return provider.getDictDataById(id);
    }

    public SysDictData updateDictData(SysDictData sysDictData){
        return provider.updateDictData(sysDictData);
    }
    public Integer getRecordsTotal(String id, String keyWord){
        return provider.getRecordsTotal(id, keyWord);
    }

    public Integer getDictTypesTotal(){
        return provider.getDictTypesTotal();
    }

    public Integer updateDictType(String enable, String pid, String subDictType){
        return provider.updateDictType(enable,pid,subDictType);
    }

    public Integer updateDictType2(String enable,String id){
        return provider.updateDictType2(enable,id);
    }

    public List<SysDictData> getDictByType(DictType dictType, String[] ids){
        return provider.getDictByType(dictType, ids);
    }

    public List<SysDictData> getDictByTypes(String code){
        return provider.getDictByTypes(code);
    }

    public List<SysDictData> getDictByTypeName(DictType dictType) {
        return provider.getDictByTypeName(dictType);
    }
}
