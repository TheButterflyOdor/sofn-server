package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesProduceAdminPunish;
import com.sofn.model.qry.AlesProduceAdminPunishDto;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/21.
 */
@MyBatisDao
public interface ProduceAdminPunishExpandMapper extends BaseExpandMapper {

    List<Map<String,Object>> getProduceAdminPunishAllList(Map<String, Object> map);

    long getProduceAdminPunishAllCount(Map<String, Object> map);

    AlesProduceAdminPunish getPunishById(String id);

    //所有行政处罚数据列表（不分页）
    List<AlesProduceAdminPunishDto> getAllProduceAdminPunishList(Map<String,Object> map);

}
