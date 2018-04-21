package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesProduceAdminPunish;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/21.
 */
@MyBatisDao
public interface ProduceAdminPunishExpandMapper extends BaseExpandMapper {
    //查询集合
    List<Map<String,Object>> getProduceAdminPunishAllList(Map<String, Object> map);

    long getProduceAdminPunishAllCount(Map<String, Object> map);
    //根据id查询
    AlesProduceAdminPunish getPunishById(String id);

    //根据id删除对象
    void deleteInfo(String id);

}
