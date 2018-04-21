package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysCodeRuleFieldNew;

import java.util.List;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@MyBatisDao
public interface SysCodeRuleFieldNewExpandMapper extends BaseExpandMapper {
    List<SysCodeRuleFieldNew> queryByRuleId(String ruleId);

}
