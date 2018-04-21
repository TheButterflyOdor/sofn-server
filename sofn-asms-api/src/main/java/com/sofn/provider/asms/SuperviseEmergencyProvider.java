package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsEmergencyTask;

import java.util.Map;

/**
 * Created by zhangdong on 2016/9/27 11:21.
 */
public interface SuperviseEmergencyProvider extends BaseProvider<AsmsEmergencyTask> {
    /**
     * 新增应急任务
     * @param asmsEmergencyTask
     * @return
     */
    int addAsmsEmergencyTask(AsmsEmergencyTask asmsEmergencyTask);

    /**
     * 修改应急任务
     * @param asmsEmergencyTask
     * @return
     */
    int updateAsmsEmergencyTask(AsmsEmergencyTask asmsEmergencyTask);

    /**
     * 查询应急任务列表
     * @param map
     * @return
     */
    PageInfo getSuperviseEmergencyList(Map<String, Object> map);

    /**
     * 根据id查询应急任务
     * @param id
     * @return
     */
    AsmsEmergencyTask findAsmsEmergencyTaskById(String id);
}
