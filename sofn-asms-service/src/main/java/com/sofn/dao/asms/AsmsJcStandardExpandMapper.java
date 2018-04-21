package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsJcStandard;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AsmsJcStandardExpandMapper extends BaseExpandMapper {

    List<AsmsJcStandard> getListByParams(Map<String, Object> params);

    boolean delByParm(Map<String, Object> params);
}