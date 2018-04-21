package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysNoticerelease;

import java.util.List;
import java.util.Map;

/**
 * 公告管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface SysNoticereleaseExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取公告管理列表
    */
    List<SysNoticerelease> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取公告管理数据条数
	*/
    long getCount(Map<String, Object> map);

}
