package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AlesEntrustDetectionExpandMapper extends BaseExpandMapper {
    //通过参数查询检测对象
    List<Map<String, Object>> getPagesList(Map<String, Object> map);
    //获取条数
    Long getPageCount(Map<String, Object> params);
    //更新检测对象状态
    boolean updateMonitorObjectStatus(@Param("entrustDetectionTaskId") String entrustDetectionTaskId, @Param("status") String status);
    //更新抽样单状态
    boolean updateSampleStatus(@Param("monitorObjectId") String monitorObjectId,@Param("status") String status);
}