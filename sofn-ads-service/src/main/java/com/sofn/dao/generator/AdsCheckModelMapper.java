package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsCheckModel;
import com.sofn.model.generator.AdsModelAttributeMapping;

/**
 * 检测模型Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsCheckModelMapper extends BaseMapper<AdsCheckModel> {

    /**
     *  新增检测模型
     */
    int insert(AdsCheckModel adsCheckModel);

    /**
     *  根据ID修改模型_检测对象
     */
    int update (AdsCheckModel adsCheckModel);

    /**
     *  根据ID删除检测模型(修改删除标志为Y)
     */
    int deleteByID(AdsCheckModel adsCheckModel);

    /**
     * 批量插入子表数据
     * @param adsModelAttributeMapping
     * @return
     */
    int insertIntoMapping(AdsModelAttributeMapping adsModelAttributeMapping);

    /**
     * 根据ID删除单条子表中的数据
     * @param adsModelAttributeMapping
     * @return
     */
    int deleteMappingByID(AdsModelAttributeMapping adsModelAttributeMapping);
}