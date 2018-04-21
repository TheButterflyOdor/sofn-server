package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesWtObjectCriterion;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AlesWtObjectCriterionExpandMapper extends BaseExpandMapper {
    //查询集合
    List<AlesWtObjectCriterion> getListByParams(Map<String, Object> params);
    //删除
    boolean delByParm(Map<String, Object> params);
    //根据参数更新
    boolean updateSpperLimitById(Map<String, Object> params);
    //查询根据参数
    List<AlesWtObjectCriterion> queryByTaskId(Map<String, Object> params);
}