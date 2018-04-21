package com.sofn.dao.sys;

import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysDictDataMapper;
import com.sofn.dao.generator.SysDictTypeMapper;
import com.sofn.dao.generator.SysUserMapper;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-15.
 * Time: 12:53.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-dao.xml")
public class TestExpandMapperTest {
	private static Logger logger = LoggerFactory.getLogger(TestExpandMapperTest.class);
	@Autowired
	private TestExpandMapper testExpandMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysDictTypeMapper sysDictTypeMapper;
	@Autowired
	private SysDictDataMapper sysDictDataMapper;
	/**
	 * Gets user info by id.
	 * 使用 map
	 * @throws Exception the exception
	 */
	@Test
	public void getUserInfoById() throws Exception {
		Map<String, Object> map =  testExpandMapper.getUserInfoById("60e582b5e6f148249982ad22044aa80d1");
		for(String key : map.keySet()){
			logger.error("{}={}",key,map.get(key));
		}
	}

	@Test
	public void getUserInfo() throws Exception{
		SysUserDto sysUserDto = new SysUserDto();
		sysUserDto.setId("60e582b5e6f148249982ad22044aa80d1");
		sysUserDto = testExpandMapper.getUserInfo(sysUserDto);
		logger.error("id = {}",sysUserDto.getId());
		logger.error("deptName = {}",sysUserDto.getSysDept().getDeptName());
		logger.error("password = {}",sysUserDto.getPassword());
		logger.error("phone = {}",sysUserDto.getPhone());
		logger.error("deptId = {}",sysUserDto.getSysDept().getId());
		logger.error("userName = {}",sysUserDto.getUserName());
		logger.error("account = {}",sysUserDto.getAccount());
	}

	// 插入 SysUser 测试数据
	@Test
	public void insertSysUserTest(){
		SysUser sysUser = null;
		for(int i = 0 ; i < 100 ; i++){
			sysUser = new SysUser();
			sysUser.setAccount(StringUtils.getRandomString(6));
			sysUser.setDelFlag("1");
			sysUser.setDeptId(StringUtils.getUUIDString());
			sysUser.setEmail(StringUtils.getEmail(6,10));
			sysUser.setPassword(StringUtils.getRandomString(12));
			sysUser.setPhone(StringUtils.getTel());
			sysUser.setRoleId(StringUtils.getUUIDString());
			sysUser.setStatus("1");
			sysUser.setUserName(StringUtils.getRandomString(6));
			//sysUser.setUserName(StringUtils.getChineseName());
			sysUser.setPostId(StringUtils.getUUIDString());
			sysUser.setCreateBy("dong4j");
			sysUser.setCreateTime(new Date());
			sysUser.setId(StringUtils.getUUIDString());
			sysUser.setRemark("测试数据");
			sysUser.setUpdateBy("dong4j");
			sysUser.setUpdateTime(new Date());
			sysUserMapper.insert(sysUser);
		}
	}

	// 插入 SysDictType 测试数据
	@Test
	public void insertSysDictType(){
		SysDictType sysDictType = null;
		for(int i = 0; i < 1; i ++){
			sysDictType = new SysDictType();
			sysDictType.setId(StringUtils.getUUIDString());
			sysDictType.setName("监测批次");
			sysDictType.setDelFlag("N");
			sysDictType.setEnable("Y");
			sysDictType.setUpdateTime(new Date());
			sysDictType.setUpdateBy("dong4j");
			sysDictType.setCreateTime(new Date());
			sysDictType.setRemark("测试数据");
			sysDictType.setCreateBy("dong4j");
			sysDictType.setPid("1c09267f26bc409b8519e28debd6dde910d9fd4b2a4c4dc69c2b4b11a4b1b7b1");
			sysDictType.setCode("MonitorBatch");
			sysDictTypeMapper.insert(sysDictType);
		}
	}

	// 插入 SysDictData 测试数据
	@Test
	public void insertSysDictData(){
		SysDictData sysDictData = null;
		for(int i = 0 ; i < 3; i ++){
			sysDictData = new SysDictData();
			sysDictData.setId(StringUtils.getUUIDString());
			String dictId = "cb611a93ca8a46caab280475427b4a56f140411e6cee42a49e04e1afdd9498df";
			sysDictData.setTypeId(dictId);
			sysDictData.setCategorieId("");
			sysDictData.setDictCode(StringUtils.getRandomString(10));
			sysDictData.setDictValue(StringUtils.getRandomString(10));
			sysDictData.setDictName(StringUtils.getRandomString(10));
			sysDictData.setSpellName(null);
			sysDictData.setFixed("0");
			sysDictData.setDelFlag("N");
			sysDictData.setEnable("Y");
			sysDictData.setCreateTime(new Date());
			sysDictData.setUpdateTime(new Date());
			String name = StringUtils.getChineseName();
			sysDictData.setUpdateBy("dong4j");
			sysDictData.setRemark("测试数据");
			sysDictData.setCreateBy("dong4j");

			sysDictDataMapper.insert(sysDictData);
		}
	}

}