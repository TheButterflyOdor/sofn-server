package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsModelJudgeStandard;

/**
 * 模型_判定标准Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelJudgeStandardMapper extends BaseMapper<AdsModelJudgeStandard> {

    int deleteByLogic(AdsModelJudgeStandard adsModelJudgeStandard);
}