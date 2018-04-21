package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

/**
 * Created by zhangdong on 2016/9/27.
 */
@MyBatisDao
public interface AsmsEmergencyTaskExpertExpandMapper extends BaseExpandMapper {

    int deleteByExpert(String id);
}
