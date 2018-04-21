package com.sofn.model.sys;

import com.sofn.model.generator.SysUser;

@SuppressWarnings("serial")
public class SysUserBean extends SysUser {
	//后台向前台
//	private String deptName;
	private String orgName;
	private String roleName;
	private String postName;
	private String statusName;
	//前台向后台
	private String roleStr;

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr == null ? null : roleStr.trim();
	}
/*
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}*/

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public SysUserBean(){}
	public SysUserBean(SysUser user){
		super.setOrganizationId(user.getOrganizationId());
		super.setAccount(user.getAccount());
		super.setDelFlag(user.getDelFlag());
		super.setDeptId(user.getDeptId());
		super.setEmail(user.getEmail());
		super.setPassword(user.getPassword());
		super.setPhone(user.getPhone());
		super.setPostId(user.getPostId());
		super.setReservedField1(user.getReservedField1());
		super.setReservedField2(user.getReservedField2());
		super.setRoleId(user.getRoleId());
		super.setStatus(user.getStatus());
		super.setUserName(user.getUserName());
		super.setCreateBy(user.getCreateBy());
		super.setCreateTime(user.getCreateTime());
		super.setId(user.getId());
		super.setRemark(user.getRemark());
		super.setUpdateBy(user.getUpdateBy());
		super.setUpdateTime(user.getUpdateTime());
	}
}
