/**
 *
 */
package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesEntrustDetection;
import com.sofn.model.generator.AlesEntrustSample;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AlesEntrustDetectionProvider extends BaseProvider<AlesEntrustDetection> {

    PageInfo<List<Map<String, Object>>> list(Map<String, Object> params);

    @Transactional
    int addAlesEntrustDetection(AlesEntrustDetection alesEntrustDetection);

    //插入关联表信息
    @Transactional
    int addGlInfo(AlesEntrustSample o);

    //删除关联表信息
    @Transactional
    boolean delGlInfoByTaskId(String taskId);

    //根据id获取关联对象
    List<Map<String, Object>> getObjById(String id);

    //根据委托检测任务ID更新相关的检测对象状态
    boolean updateMonitorObjectStatus(String entrustDetectionTaskId,String status);

    //根据检测对象ID更新抽样单状态
    boolean updateSampleStatus(String monitorObjectId,String status);

}
