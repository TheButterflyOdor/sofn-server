package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsTaskProject;

import java.util.List;
import java.util.Map;

/**
 * 监测任务_检测项目 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsTaskProjectExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取监测任务_检测项目列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取监测任务_检测项目数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取所有监测任务_检测项目列表
     */
    List<AdsTaskProject> getEntityWithTaskId(Map<String, Object> map);

}
