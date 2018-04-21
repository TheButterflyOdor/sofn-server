package com.sofn.model.sys;

import com.sofn.model.generator.SysDept;

@SuppressWarnings("serial")
public class SysDeptDto extends SysDept {
	private String parentName;
	private String deptTypeName;
	private String deptLevelName;
	private String regionId;
	private String statusName;


	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getDeptTypeName() {
		return deptTypeName;
	}

	public void setDeptTypeName(String deptTypeName) {
		this.deptTypeName = deptTypeName;
	}

	public String getDeptLevelName() {
		return deptLevelName;
	}

	public void setDeptLevelName(String deptLevelName) {
		this.deptLevelName = deptLevelName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
