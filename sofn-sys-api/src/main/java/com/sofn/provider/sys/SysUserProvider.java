/**
 * 
 */
package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.constant.Organization;
import com.sofn.core.persistence.Page;
import com.sofn.core.persistence.Pager;
import com.sofn.core.util.OrgFormEnum;
import com.sofn.core.util.Result;
import com.sofn.core.util.UserEnum;
import com.sofn.model.generator.SysOrganization;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysUserBean;
import com.sofn.model.sys.SysUserDto;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author sofn
 * @version 2016年5月15日 上午11:21:47
 */
public interface SysUserProvider extends BaseProvider<SysUser> {
	int getRecordsTotal(String keyword, String status ,String organizationId );
	int getRecordsTotalNew(String keyword, String status ,String userId,String userType);
	List<SysUserDto> getSysUserList(Pager pager, String keyword);
	List<SysUserBean> getSysUserList(Page page, String keyword, String status,String organizationId );
	List<SysUserBean> getSysUserListNew(Page page, String keyword, String status,String userId,String userType);
	List<SysUser> queryByParam(Map<String,Object> param);

	/**
	 * 按条件分页查询用户信息
	 * @param param 查询条件
	 * @param pageNum 页号
	 * @param pageSize 每页记录条数
	 * @return 用户信息
	 */
	PageInfo<SysUser> queryByParamWithPaging(Map<String,Object> param, int pageNum, int pageSize);
	SysUserBean queryUserBeanById(String id);


	//以下对业务平台提供接口-----------------------------------------------------------------------------
	/**
	 * 根据登录用户查询同部门下的所有用户
	 * @param token
	 * @return	用户对象集合
	 */
	List<SysUser> getDeptUser(String token);

	/**
	 * 根据角色ID查询拥有该角色的所有用户
	 * @param roleId 角色ID
	 * @return 用户对象集合
	 */
	List<SysUser> getRoleUser(String roleId);

	/**
	 * 重置密码
	 * @param account 账号
	 * @param token
	 * @return
	 */
	boolean resetPwd(String account,String token);

	/**
	 * 修改密码
	 * @param account 账号
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @param token
	 * @return
	 */
	boolean changePwd(String account, String oldPwd, String newPwd,String token);
	/**
	 * 修改密码
	 * @param newPwd 新密码
	 * @return
	 */
	public boolean changePwdByAccount(String account,String newPwd) ;

	/**
	 * 添加用户
	 * @param user 用户对象
	 * @param token
	 * @return
	 */
	SysUser addUser(SysUserBean user, String token,UserEnum userEnum);

	/**
	 * 保存机构信息，并生成默认管理员账号
	 * @param oranization 机构信息对象
	 * @param token 登陆用户的token
	 * @param industrys 行业类型字典值以逗号分割的字符串，如"01,02,03"
	 * @return
	 */
	SysUser saveSysOranizationUser(SysOrganization oranization, String token,String industrys);

	/**
	 * 注销机构，同时注销机构下所有用户信息
	 * @param orgId 追溯系统机构ID
	 * @param token 登陆用户的token
     * @return
     */
	boolean cancelSysOranization(String orgId,String token,int status);
	/**
	 * 查询当前登陆用户的所属机构对象
	 * @param token 登陆用户的token
	 * @return
	 */
	Organization findSysUserOrganization(String token);

	/**
	 * 根据用户名模糊查询总数
	 * @param userName 用户姓名
	 * @return 用户总数
     */
	int getRecordsTotal(String userName);

	/**
	 * 根据用户名分页模糊查询用户列表
	 * @param start 起始数目 起始数为1
	 * @param length 获取对象数量
	 * @param userName 用户姓名
     * @return 分页用户List
     */
	List<SysUser> getSysUserList(int start,int length, String userName);

	/**
	 * 根据用户id获取同部门下的所有用户
	 * @param userId 用户id
	 * @return 用户集合
	 */
	List<SysUser> getSysUserListByUDept(String userId);

	/**
	 *
	 * @param userEnum 用户类型，企业主体用户：UserEnum.SUBJADMIN、个人用户：UserEnum.SUBJNORMAL
	 * @param sysUser 用户Bean
	 * @param industryTypes 企业类型的字典值
	 * @param choiceAbattoir 是否勾选了屠宰场
	 * @return
	 */
	Result addSubjUser(String token,UserEnum userEnum, SysUser sysUser, List<String> industryTypes, boolean choiceAbattoir,OrgFormEnum orgFormEnum);
	/**
	 * 通过账号模糊匹配获取用户信息
	 * @param param
	 * @return
	 */
	String getMaxAreaByAccount(Map<String, Object> param);

	/**
	 * 判断用户是否存在
	 * @param param
	 * @return 小于1表示存在
	 */
	int checkUserByAccount(Map<String, Object> param);

	/**
	 * 通过用户名和密码获取用户信息
	 * @param account 账号
	 * @param password 密码，明文
	 * @return
	 */
	public SysUser getSysUserByAccountPwd(String account,String password);

	public List<SysUserBean> getOrgUserRecordsByUserId(Page page, String status,String orgId);

	public int getOrgUserRecordsCountByUserId(String status,String orgId);

	/**
	 * 通过账户修改密码
	 * @param account 登录账户
	 * @param password 密码明文
	 * @return
	 */
	public boolean editPasswordByAccount(String account,String password);
}
