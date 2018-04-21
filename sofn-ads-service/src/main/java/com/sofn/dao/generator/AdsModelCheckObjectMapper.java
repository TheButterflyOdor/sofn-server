package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsModelCheckObject;

/**
 * 模型_检测对象Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelCheckObjectMapper extends BaseMapper<AdsModelCheckObject> {

    /**
     *  新增模型_检测对象
     */
    int insert(AdsModelCheckObject adsModelCheckObject);

    /**
     *  根据ID修改模型_检测对象
     */
    int update (AdsModelCheckObject adsModelCheckObject);
    /**
     *  根据ID删除模型_检测对象(修改删除标志为Y)
     */
    int deleteByID(AdsModelCheckObject adsModelCheckObject);
}