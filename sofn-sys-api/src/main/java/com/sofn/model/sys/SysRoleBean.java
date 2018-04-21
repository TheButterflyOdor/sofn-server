package com.sofn.model.sys;

import com.sofn.model.generator.SysRole;

@SuppressWarnings("serial")
public class SysRoleBean extends SysRole {
	private String rn;
	private String deptName;
	private String statusName;

	public String getRn() {
		return rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
