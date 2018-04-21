package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 检测项目 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsCheckProjectExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取检测项目列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取检测项目数据条数
	*/
    long getCount(Map<String, Object> map);

}
