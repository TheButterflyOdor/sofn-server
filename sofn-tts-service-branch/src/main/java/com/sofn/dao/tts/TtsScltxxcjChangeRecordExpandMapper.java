package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 注册主体信息表变更记录表 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjChangeRecordExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取注册主体信息表变更记录表列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取注册主体信息表变更记录表数据条数
	*/
    long getCount(Map<String, Object> map);
}
