package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 投诉建议信息管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjNotificationExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取投诉建议信息管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取投诉建议信息管理数据条数
	*/
    long getCount(Map<String, Object> map);

}
