package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsModelSampleLink;

/**
 * 模型_抽样环节Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelSampleLinkMapper extends BaseMapper<AdsModelSampleLink> {

    /**
     *  新增模型_抽样环节
     */
    int insert(AdsModelSampleLink adsModelSampleLink);

    /**
     *  根据ID修改模型_抽样环节
     */
    int update(AdsModelSampleLink adsModelSampleLink);
    /**
     *  根据ID删除模型_抽样环节(修改删除标志为Y)
     */
    int deleteByID(AdsModelSampleLink adsModelSampleLink);
}