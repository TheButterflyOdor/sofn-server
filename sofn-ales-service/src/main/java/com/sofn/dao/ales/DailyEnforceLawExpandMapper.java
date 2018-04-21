package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface DailyEnforceLawExpandMapper extends BaseExpandMapper{
    //查新集合
    List<Map<String,Object>> selectAllByCondition(Map<String, Object> map);

    long getDailyEnforceLawCount(Map<String, Object> map);

    int getTaskNameCount(String taskName);
}