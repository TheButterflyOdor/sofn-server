package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 投诉举报 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjComplainExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取投诉举报列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取投诉举报数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取实体信息列表
     */
    List<Map<String,Object>> getEntityPageInfo(Map<String, Object> map);


    /**
     *  获取实体数据条数
     */
    long getEntityCount(Map<String, Object> map);

    /**
     *  获取投诉举报列表
     */
    List<Map<String,Object>> getInterPageInfo(Map<String, Object> map);


    /**
     *  获取投诉举报数据条数
     */
    long getInterCount(Map<String, Object> map);

}
