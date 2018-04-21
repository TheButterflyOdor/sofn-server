package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AsmsCheckTask;
import com.sofn.model.qry.SysTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-15.
 * Time: 11:59.
 * Description:
 */
@MyBatisDao
public interface TestExpandMapper extends BaseMapper {
	List<SysTemplate> getPageInfo(Map<String, Object> map);
	long getCount(Map<String, Object> map);
	AsmsCheckTask selectByPrimaryKey(String id);
}
