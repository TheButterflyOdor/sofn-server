package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysCodeRuleData;

import java.util.List;

/**
 * Created by wuye on 2016/12/14.
 */
@MyBatisDao
public interface SysCodeRuleDataExpandMapper extends BaseExpandMapper {
    void deleteByFieldId(String fieldId);
    void deleteByRuleId(String ruleId);
    List<SysCodeRuleData> queryByRuleId(String ruleId);
    SysCodeRuleData queryByFieldId(String ruleId);
}
