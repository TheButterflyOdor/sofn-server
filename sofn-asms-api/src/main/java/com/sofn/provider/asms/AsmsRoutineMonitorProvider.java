/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsRoutineLeadUnit;
import com.sofn.model.generator.AsmsRoutineMonitor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AsmsRoutineMonitorProvider extends BaseProvider<AsmsRoutineMonitor> {

    PageInfo<List<Map<String, Object>>> list(Map<String, Object> params);

    PageInfo<List<AsmsRoutineMonitor>> listByCeateOrgId(Map<String, Object> params);

    /**
     * 插入关联表信息
     */
    @Transactional
    int addGlInfo(AsmsRoutineLeadUnit o);

    /**
     * 删除关联表信息
     */
    @Transactional
    boolean delGlInfoByTaskId(String taskId);

    /**
     * 根据任务id获取检测单位列表
     */
    List<AsmsRoutineLeadUnit> getUnitByTaskId(String taskId);

    /**
     * 【监测系统--查询监管下发任务列表】
     *
     * @author LiBing
     * @see “参数说明：Map params：
     * leadUnitId :承担单位id
     * rmName:任务名称
     * dateBegin：开始时间
     * dateEnd：结束时间
     */
    PageInfo<List<Map<String, Object>>> getTaskListByADS(Map<String, Object> params);

    /**
     * 新增例行监测任务
     */
    @Transactional
    AsmsRoutineMonitor addAsmsRoutineMonitor(AsmsRoutineMonitor asmsRoutineMonitor);

    /**
     * 修改例行监测任务
     */
    @Transactional
    AsmsRoutineMonitor updateAsmsRoutineMonitor(AsmsRoutineMonitor asmsRoutineMonitor);

}
