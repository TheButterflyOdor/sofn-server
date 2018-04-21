package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysCodeRuleData;

import java.util.List;

/**
 * Created by wuye on 2016/12/14.
 */
public interface SysCodeRuleDataProvider extends BaseProvider<SysCodeRuleData> {

    int addSysCodeRuleData(SysCodeRuleData sysCodeRuleData);

    int updateSysCodeRuleData(SysCodeRuleData sysCodeRuleData);

    int delSysCodeRuleData(String id);

    //SysCodeRuleData queryById(String id);

    List<SysCodeRuleData> queryByRuleId(String ruleId);

    SysCodeRuleData queryByFieldId(String fieldId);

    SysCodeRuleData insert(SysCodeRuleData record) ;

    void deleteByFieldId(String fieldId);
    void deleteByRuleId(String ruleId);
}
