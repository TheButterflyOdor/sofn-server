package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsRoutineLeadUnit;

import java.util.List;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsRoutineLeadUnitExpandMapper extends BaseExpandMapper {

    boolean delByTaskId(AsmsRoutineLeadUnit t);

    /**
     * 根据任务id获取检测单位列表
     */
    List<AsmsRoutineLeadUnit> getUnitByTaskId(AsmsRoutineLeadUnit t);

}