package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.core.constant.DictType;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.sys.SysDictTypeDto;

import java.util.List;

/**
 * Created by: dong4j.
 * Date: 2016-10-19.
 * Time: 16:25.
 * Description:
 */
public interface SysDictProvider extends BaseProvider<SysDictType> {
    /**
     * Gets dict types.
     * 根据参数获取所有字典类型数据
     *
     * @param type    the type
     * @param id      the id
     * @param keyWord the key word
     * @return the dict types
     */
    List<SysDictTypeDto> getDictTypes(String type,String id,String keyWord);

    List<SysDictType> getDictTypes2(String type,String id,String keyWord);

    /**
     * Delete dict data sys dict data.
     * 删除字典数据
     *
     * @param idStr the id
     * @return the sys dict data
     */
    Integer deleteDictData(String idStr);

    /**
     * Update valid sys dict data.
     * 更新字典数据是否有效
     *
     * @param id           the id
     * @param whetherValid the whether valid
     * @return the sys dict data
     */
    SysDictData updateValid(String id, String whetherValid);

    /**
     * Gets dict data by type.
     *
     * @param id      the id
     * @param keyWord the key word
     * @param page    the page
     * @return the dict data by type
     */
    List<SysDictData> getDictDataByType(String id,String keyWord,Page page);

    /**
     * Add dict data sys dict data.
     *
     * @param sysDictData the sys dict data
     * @return the sys dict data
     */
    SysDictData addDictData(SysDictData sysDictData);

    /**
     * Gets dict data by id.
     * 根据Id查询字典数据
     *
     * @param id the id
     * @return the dict data by id
     */
    SysDictData getDictDataById(String id);

    /**
     * Update dict data sys dict data.
     *
     * @param sysDictData the sys dict data
     * @return the sys dict data
     */
    SysDictData updateDictData(SysDictData sysDictData);

    /**
     * Gets records total.
     *
     * @param id      the id
     * @param keyWord the key word
     * @return the records total
     */
    Integer getRecordsTotal(String id, String keyWord);

    /**
     * Gets dict types total.
     *
     * @return the dict types total
     */
    Integer getDictTypesTotal();

    /**
     * Update dict type integer.
     *
     * @param enable      the enable
     * @param pid         the pid
     * @param subDictType the sub dict type
     * @return the integer
     */
    Integer updateDictType(String enable, String pid, String subDictType);

    Integer updateDictType2(String enable,String id);

    /**
     * Gets dict date.
     * 根据枚举类型获取字典数据
     *
     * @param dictType the dict type
     * @return the dict date
     */
    List<SysDictData> getDictByType(DictType dictType);

    List<SysDictData> getDictByTypes(String codes);


    /**
     * Gets dict by type.
     * 根据行业 id 获取不同的单位字典
     *
     * @param dictType the dict type
     * @param ids       the ids
     * @return the dict by type
     */
    List<SysDictData> getDictByType(DictType dictType,String[] ids);
    /**
     * 根据单位名称获取单位字典
     * @param name
     * @return
     */
    SysDictData getIdByName(String name);

    /**
     * 根据字典分类名称获取该类别下面的字典数据
     *
     * @param dictTypeName 字典分类名称
     * @return 字典数据列表
     */
    List<SysDictData> getDictByTypeName(String dictTypeName);

    /**
     * 根据字典枚举名称获取该类别下面的字典数据
     *
     * @param dictType 字典枚举
     * @return 字典数据列表
     */
    List<SysDictData> getDictByTypeName(DictType dictType);
}
