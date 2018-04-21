package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysUserDto;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-15.
 * Time: 11:59.
 * Description:
 */
@MyBatisDao
public interface TestExpandMapper {
	/**
	 * Gets count.
	 *
	 * @return the count
	 */
	Integer getCount();

	/**
	 * Gets user info by id.
	 * 使用 map
	 *
	 * @param id the id
	 * @return the user info by id
	 */
	Map<String, Object> getUserInfoById(@Param("id")String id);

	/**
	 * Gets user info.
	 * 使用 dto 对象
	 * @param sysUserDto the sys user dto
	 * @return the user info
	 */
	SysUserDto getUserInfo(SysUserDto sysUserDto);

	int insertTest(SysUser sysUser);

	int updateTest(SysUser sysUser);
}
