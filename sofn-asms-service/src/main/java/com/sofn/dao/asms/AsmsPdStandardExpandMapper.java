package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsPdStandard;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AsmsPdStandardExpandMapper extends BaseExpandMapper {

    List<AsmsPdStandard> getListByParams(Map<String, Object> params);

    boolean delByParm(Map<String, Object> params);
}