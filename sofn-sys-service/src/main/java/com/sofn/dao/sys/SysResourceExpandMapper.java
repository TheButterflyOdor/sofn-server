package com.sofn.dao.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17.
 */
@MyBatisDao
public interface SysResourceExpandMapper extends BaseExpandMapper {
    List<Map<String,Object>> getList(Map<String, Object> map);
    long getCount(Map<String, Object> map);
    long getQueryCount(Map<String, Object> map);
    List<Map<String,Object>> getQueryList(Map<String, Object> map);
    Integer getRepeatRescourceCount(Map<String, Object> map);
}
