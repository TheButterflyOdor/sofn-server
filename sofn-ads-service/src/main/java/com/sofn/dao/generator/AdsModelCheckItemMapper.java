package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsModelCheckItem;
import com.sofn.model.generator.AdsModelObjectItemMapping;

/**
 * 模型_检测项目Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelCheckItemMapper extends BaseMapper<AdsModelCheckItem> {

    /**
     * 逻辑删除检测项目
     * @param adsModelCheckItem
     * @return
     */
    int deleteByLogic(AdsModelCheckItem adsModelCheckItem);

    /**
     * 批量插入对象数据
     * @param adsModelObjectItemMapping
     * @return
     */
    int insertIntoMapping(AdsModelObjectItemMapping adsModelObjectItemMapping);

    /**
     * 根据ID删除单条模型_检测项目中的检测对象
     * @param adsModelObjectItemMapping
     * @return
     */
    int deleteMappingByID(AdsModelObjectItemMapping adsModelObjectItemMapping);
}