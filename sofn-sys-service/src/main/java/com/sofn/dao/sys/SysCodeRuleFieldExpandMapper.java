package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysCodeRuleField;

import java.util.List;

/**
 * Created by wuye on 2016/12/14.
 */
@MyBatisDao
public interface SysCodeRuleFieldExpandMapper extends BaseExpandMapper {
    void deleteByRuleId(String ruleId);
    List<SysCodeRuleField> queryByRuleId(String ruleId);
}
