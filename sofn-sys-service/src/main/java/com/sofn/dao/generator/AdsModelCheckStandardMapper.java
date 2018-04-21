package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsModelCheckStandard;

/**
 * 模型_检测标准Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelCheckStandardMapper extends BaseMapper<AdsModelCheckStandard> {

    /**
     *  新增模型_检测标准
     */
    int insert(AdsModelCheckStandard adsModelCheckStandard);

    /**
     *  根据ID修改模型_检测标准
     */
    int update(AdsModelCheckStandard adsModelCheckStandard);
    /**
     *  根据ID删除模型_检测标准(修改删除标志为Y)
     */
    int deleteByID(AdsModelCheckStandard adsModelCheckStandard);
}