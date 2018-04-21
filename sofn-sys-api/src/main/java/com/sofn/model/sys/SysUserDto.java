package com.sofn.model.sys;

import com.sofn.model.generator.SysDept;
import com.sofn.model.generator.SysUser;

@SuppressWarnings("serial")
public class SysUserDto extends SysUser {
	private SysDept sysDept;

	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}


}
