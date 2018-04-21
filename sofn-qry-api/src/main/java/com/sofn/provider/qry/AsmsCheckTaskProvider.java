/**
 *
 */
package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsCheckBearUnit;
import com.sofn.model.generator.AsmsCheckTask;
import com.sofn.model.generator.AsmsMonitorObject;
import com.sofn.model.generator.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AsmsCheckTaskProvider extends BaseProvider<AsmsCheckTask> {

    /**
     * 根据查询条件获取所有未删除任务列表
     * */
    List<AsmsCheckTask> getTaskList(Map<String, Object> params);

    /**
     * 【执法系统-承担任务 接口】
     * 新监督抽查任务任务列表
     * */
    List<Map<String, Object>> getCheckTaskByUser(Map<String, Object> params);

    /***
     *监督抽查列表(监管系统-检测任务-监督抽查分页)
     */
    PageInfo<List<Map<String, Object>>> list(Map<String, Object> params);

    /***
     *机构所属监督抽查列表(监管系统-检测任务-复检任务分页)
     */
    PageInfo<List<AsmsCheckTask>> listByOrg(Map<String, Object> params);

    /***
     *父级任务列表(监管系统-检测任务-部级监督抽查任务分页)
     */
    PageInfo<List<Map<String, Object>>> getParentTaskList(Map<String, Object> params);

    /***
     *检测对象列表(分页)
     */
    PageInfo<List<Map<String, Object>>> getMyTaskObjById(Map<String, Object> params);

    /**
     * 根据任务key获取所属检测对象列表
     * */
    List<Map<String,Object>> getObjById(String id);

    /**
     * 根据任务id获取检测单位列表
     * */
    List<AsmsCheckBearUnit> getUnitByTaskId(String taskId);

    /**
     *【监测系统 接口】
     * 获取监督抽查任务列表
     * @see “参数说明：Map params：需包含分页参数、如有查询条件需加入查询条件，条件属性为AsmsCheckTask属性
     *          SysUser：当前登录用户信息”
     * @author LiBing
     * */
    PageInfo<List<Map<String, Object>>> getTaskListByADS(Map<String, Object> params, SysUser user);

    /**
     * 根据id获取检测对象
     * */
    //AsmsMonitorObject getMonitorObjectById(String id);

   /**
     * 根据任务key获取所属检测对象列表
     * */
    List<AsmsMonitorObject> getMonObjByTaskId(String taskId);

    /***
     * 【执法系统-承担任务 接口】
     * 历史任务(分页)
     */
    PageInfo<List<Map<String, Object>>> getHistoryCheckTask(Map<String, Object> params);

    /**
     * 根据创建机构id和父级任务id获取任务信息
     * */
    List<AsmsCheckTask> getTaskByCreateOrgIdAndParentTaskId(String parentTaskId, String CreateOrgId);

    /**
     * 查询承担任务
     */
    List<AsmsCheckTask> getCheckTaskList(Map<String,Object> params);

}
