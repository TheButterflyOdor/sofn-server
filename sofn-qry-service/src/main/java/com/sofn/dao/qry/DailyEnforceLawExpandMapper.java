package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import com.sofn.model.qry.AlesDailyEnforceLawDto;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface DailyEnforceLawExpandMapper extends BaseExpandMapper{

    List<Map<String,Object>> selectAllByCondition(Map<String, Object> map);

    long getDailyEnforceLawCount(Map<String, Object> map);

    int getTaskNameCount(String taskName);

    //根据AlesDailyEnforceLaw id查询AlesDailyEnforceLaw
    AlesDailyEnforceLaw selectAlesDailyEnforceLawByPrimaryKey(String id);

    //根据查询条件查询日常执法列表
    List<AlesDailyEnforceLawDto> getAllDailyEnforceLawList(Map<String,Object> map);
}