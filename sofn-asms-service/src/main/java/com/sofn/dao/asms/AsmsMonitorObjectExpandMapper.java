package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsMonitorObject;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsMonitorObjectExpandMapper extends BaseExpandMapper {

    List<Map<String, Object>> getPagesList(Map<String, Object> map);

    Long getPageCount(Map<String, Object> params);

    boolean delByTaskId(AsmsMonitorObject t);

    List<Map<String, Object>> getObjById(String id);

    List<Map<String, Object>> getMyTaskObjPagesList(Map<String, Object> map);

    Long getMyTaskObjPageCount(Map<String, Object> params);

    //修改检测对象是否已提交抽样单状态
    boolean updateIsSample(AsmsMonitorObject mo);

    //根据任务id获取检测对象列表
    List<AsmsMonitorObject> getMonObjByTaskId(AsmsMonitorObject mo);

    /**
     * 根据任务id获取监测对象
     */
    List<AsmsMonitorObject> getMobjListByTaskId(AsmsMonitorObject o);

    /**
     * 根据监测对象id获取和抽样单是否提交状态
     */

    Long findAsmsMonitorByIdAndIsSample(Map<String, Object> map);

    /**
     * 根据监测对象id和是否与检测系统同步状态查询
     */
    Long findAsmsMonitorByIdAndIsSync(Map<String, Object> map);


}