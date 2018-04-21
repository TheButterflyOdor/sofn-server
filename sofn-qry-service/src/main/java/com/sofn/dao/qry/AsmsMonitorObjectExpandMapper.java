package com.sofn.dao.qry;

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

    void delByTaskId(AsmsMonitorObject t);

    List<Map<String, Object>> getObjById(String id);

    List<Map<String, Object>> getMyTaskObjPagesList(Map<String, Object> map);

    Long getMyTaskObjPageCount(Map<String, Object> params);

    //修改检测对象是否已提交抽样单状态
    int updateIsSample(AsmsMonitorObject mo);

    //根据任务id获取检测对象列表
    List<AsmsMonitorObject> getMonObjByTaskId(AsmsMonitorObject mo);

    /**
     * 根据任务id获取监测对象
     * */
    List<AsmsMonitorObject> getMobjListByTaskId(AsmsMonitorObject o);

}