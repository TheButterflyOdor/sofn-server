package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsCheckObjectCriterion;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AsmsCheckObjectCriterionExpandMapper extends BaseExpandMapper {

    List<AsmsCheckObjectCriterion> getListByParams(Map<String, Object> params);

    boolean delByParm(Map<String, Object> params);

    boolean updateSpperLimitById(Map<String, Object> params);

    List<AsmsCheckObjectCriterion> getDetectionItemBySampleCode(Map<String, Object> params);
}