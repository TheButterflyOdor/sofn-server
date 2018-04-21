package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.constant.DictType;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysDictDataMapper;
import com.sofn.dao.generator.SysDictTypeMapper;
import com.sofn.dao.sys.SysDictExpandMapper;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.sys.SysDictTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by: dong4j.
 * Date: 2016-10-19.
 * Time: 16:26.
 * Description:
 */
@DubboService(interfaceClass = SysDictProvider.class)
public class SysDictProviderImpl extends BaseProviderImpl<SysDictType> implements SysDictProvider{
    /**
     * The Sys dict type mapper.
     */
    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;
    /**
     * The Sys dict data mapper.
     */
    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    /**
     * The Sys dict expand mapper.
     */
    @Autowired
    private SysDictExpandMapper sysDictExpandMapper;

    @Override
    public List<SysDictTypeDto> getDictTypes(String type,String id, String keyWord) {
        // 查询所有父类
        List<SysDictTypeDto> parentList = sysDictExpandMapper.getParentList(id);
        if(type.equals("all")){
            for(SysDictTypeDto sysDictTypeDto: parentList){
                // 根据父类 id 查询所有子类
                List<SysDictType> subclassType = sysDictExpandMapper.getDictTypeByPid(sysDictTypeDto.getId(),keyWord);
                sysDictTypeDto.setSysDictType(subclassType);
            }
        }
        return parentList;
    }

    @Override
    public List<SysDictType> getDictTypes2(String type,String id,String keyWord){
        List<SysDictType> list = sysDictTypeMapper.selectByTypeId(id);
        return list;
    }

    @Override
    public Integer deleteDictData(String idStr) {
        String[] ids = idStr.split(",");
        for(String id : ids) {
            SysDictData sysDictData = sysDictDataMapper.selectByPrimaryKey(id);
            if(sysDictData == null){
                throw new DataParseException("参数错误");
            }
            sysDictExpandMapper.deleteDictData(id);
        }
        return 1;
    }

    @Override
    public SysDictData updateValid(String id, String whetherValid) {
        SysDictData sysDictData = sysDictDataMapper.selectByPrimaryKey(id);
        sysDictData.setEnable(whetherValid);
        sysDictDataMapper.updateByPrimaryKey(sysDictData);
        return sysDictData;
    }

    @Override
    public Integer getRecordsTotal(String id,String keyWord) {
        return sysDictExpandMapper.getRecordsTotal(id, keyWord);
    }

    @Override
    public Integer getDictTypesTotal() {
        return sysDictExpandMapper.getDictTypesTotal();
    }

    @Override
    @Transactional
    public Integer updateDictType(String enable, String pid, String subDictType) {
        String[] subDictTypeStr = subDictType.substring(0,subDictType.length() - 1).split(",");
        for(String str: subDictTypeStr){
            String[] subEnable = str.split("@");
            // 更新子类型
            sysDictExpandMapper.updateEnable(subEnable[0],subEnable[1]);
        }
        // 更新父类型
        sysDictExpandMapper.updateEnable(pid,enable);
        return 1;
    }

    @Override
    @Transactional
    public Integer updateDictType2(String enable,String id){
            return sysDictExpandMapper.updateEnable(enable,id);
    }

    @Override
    public List<SysDictData> getDictByType(DictType dictType) {
        return sysDictExpandMapper.getDictByType(dictType.getCode());
    }

    @Override
    public List<SysDictData> getDictByTypes(String codes) {
        return sysDictExpandMapper.getDictByType(codes);
    }

    @Override
    public List<SysDictData> getDictByType(DictType dictType, String[] ids) {
        SysDictData sysDictData = null;
        Set<SysDictData> sysDictDataSet = new HashSet<>();
        List<SysDictData> list = null;
        for (String id : ids) {
            list = sysDictExpandMapper.getUnits(dictType.getCode(), id);
            sysDictDataSet.addAll(list);
        }
        //return sysDictExpandMapper.getUnits(dictType.getCode(), id);
        return new ArrayList<SysDictData>(sysDictDataSet);
    }

    @Override
    public List<SysDictData> getDictDataByType(String id,String keyWord ,Page page) {
        return sysDictExpandMapper.getDictDataByType(id,keyWord,page);
    }

    @Override
    public SysDictData addDictData(SysDictData sysDictData) {
        sysDictData.setId(StringUtils.getUUIDString());
        sysDictData.setCreateTime(new Date());
//        sysDictData.setCreateBy(WebUtil.getCurrentUserId());
        sysDictData.setUpdateTime(new Date());
//        sysDictData.setUpdateBy(WebUtil.getCurrentUserId());
        sysDictData.setDelFlag("N");
        sysDictDataMapper.insert(sysDictData);
        return sysDictData;
    }

    @Override
    public SysDictData getDictDataById(String id) {
        return sysDictDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysDictData updateDictData(SysDictData sysDictData) {
        sysDictDataMapper.updateByPrimaryKey(sysDictData);
        return sysDictData;
    }
    @Override
    public SysDictData getIdByName(String name) {
        return sysDictExpandMapper.getIdByName(name);
    }

    @Override
    public List<SysDictData> getDictByTypeName(String dictTypeName) {
        return sysDictExpandMapper.getDictByName(dictTypeName);
    }

    @Override
    public List<SysDictData> getDictByTypeName(DictType dictType) {
        return sysDictExpandMapper.getDictByName(dictType.getName());
    }
}
