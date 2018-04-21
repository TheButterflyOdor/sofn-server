package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsMonitorTask;

/**
 * 监测任务Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsMonitorTaskMapper extends BaseMapper<AdsMonitorTask> {

    AdsMonitorTask selectByPrimaryKey(String id);

    int update (AdsMonitorTask adsMonitorTask);
}