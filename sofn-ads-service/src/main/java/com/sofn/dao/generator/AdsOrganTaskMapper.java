package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsOrganTask;

/**
 * 机构任务Mapper
 * @author yangran
 */
@MyBatisDao
public interface AdsOrganTaskMapper extends BaseMapper<AdsOrganTask> {

    /**
     * 逻辑删除机构任务
     * @param adsOrganTask
     * @return
     */
    int deleteOrganTaskByLogic(AdsOrganTask adsOrganTask);

}