package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.generator.SysCodeRecord;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@MyBatisDao
public interface SysCodeConvertExpandMapper extends BaseExpandMapper {
    SysCodeConvert queryCode(SysCodeConvert sysCodeConvert);
}
