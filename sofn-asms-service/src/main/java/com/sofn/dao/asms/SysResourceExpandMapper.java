package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface SysResourceExpandMapper extends BaseExpandMapper{

    List<Map<String,Object>> selectAllByCondition(Map<String, Object> map);
    long getSysResourceCount(Map<String, Object> map);

}