package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/27.
 */
@MyBatisDao
public interface AsmsEmergencyTaskExpandMapper extends BaseExpandMapper {

    List<Map<String,Object>> getAsmsEmergencyTaskAllList(Map<String, Object> map);

    long getAsmsEmergencyTaskAllCount(Map<String, Object> map);
}
