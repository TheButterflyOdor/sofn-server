package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysTemplate;

import java.util.List;
import java.util.Map;

/**
 * 模板管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface SysTemplateExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取模板管理列表
    */
    List<SysTemplate> getPageInfo(Map<String,Object> map);
    

	/**
	*  获取模板管理数据条数
	*/
    long getCount(Map<String, Object> map);

    SysTemplate getTemplateByName(String name);

    int updateTempStatus(Map<String,Object> map);

    SysTemplate foundById(String tid);

}
