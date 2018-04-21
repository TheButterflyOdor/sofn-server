package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesPdStandard;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AlesPdStandardExpandMapper extends BaseExpandMapper {
    //根据参数查询
    List<AlesPdStandard> getListByParams(Map<String, Object> params);
    //根据参数删除
    boolean delByParm(Map<String, Object> params);
}