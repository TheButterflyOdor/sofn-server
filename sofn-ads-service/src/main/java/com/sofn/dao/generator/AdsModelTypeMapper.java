package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsModelType;

/**
 * 模型_种类Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelTypeMapper extends BaseMapper<AdsModelType> {
    /**
     *  新增模型_种类
     */
    int insert(AdsModelType adsModelType);

    /**
     *  根据ID修改模型_种类
     */
    int update (AdsModelType adsModelType);
    /**
     *  根据ID删除模型_种类(修改删除标志为Y)
     */
    int deleteByID(AdsModelType adsModelType);
}