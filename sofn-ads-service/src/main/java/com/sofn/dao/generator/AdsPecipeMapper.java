package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsPecipe;

/**
 * 回执单Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsPecipeMapper extends BaseMapper<AdsPecipe> {

    /**
     *  创建回执单
     */
    int insert(AdsPecipe adsPecipe);
}