package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysCodeRecord;
import com.sofn.model.generator.SysCodeRuleNew;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@MyBatisDao
public interface SysCodeRecordExpandMapper extends BaseExpandMapper {
    Integer getMaxOrderCode(SysCodeRecord sysCodeRecord);

    Integer updateMaxOrderCode(SysCodeRecord sysCodeRecord);
}
