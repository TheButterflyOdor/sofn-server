package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsMonitorObject;

import java.util.List;
import java.util.Map;

/**
 * Created by LIB on 2016/12/26.
 */
public interface AsmsMonitorObjectProvider  extends BaseProvider<AsmsMonitorObject> {

    /**
     * 根据任务获取监测对象列表(分页)
     * */
    PageInfo<List<Map<String, Object>>> objList(Map<String, Object> params);

    /**
     * 根据任务id获取监测对象
     * */
    List<AsmsMonitorObject> getMobjListByTaskId(AsmsMonitorObject o);

    /**
     * 根据监测对象id获取监测对象
     */
    AsmsMonitorObject findAsmsMonitorObjectById(String id);

    /**
     *根据监测对象id和抽样单提交状态查询
     */
    boolean findAsmsMonitorByIdAndIsSample(String id,String isSample);

    /**
     *根据监测对象id和是否与检测系统是否同步查询
     */
    boolean findAsmsMonitorByIdAndIsSync(String id,String isSync);

}
