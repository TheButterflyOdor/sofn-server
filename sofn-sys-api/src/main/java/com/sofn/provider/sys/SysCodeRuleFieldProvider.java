package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysCodeRuleField;

import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/14.
 */
public interface SysCodeRuleFieldProvider extends BaseProvider<SysCodeRuleField> {

   /* int addSysCodeRuleField(SysCodeRuleField sysCodeRuleField);

    int updateSysCodeRuleField(SysCodeRuleField sysCodeRuleField);

    int delSysCodeRuleField(String id);

//    SysCodeRuleField queryById(String id);

    SysCodeRuleField queryByRuleId(String ruleId);*/
   void deleteByRuleId(String ruleId);
   List<SysCodeRuleField> queryByRuleId(String ruleId);
   SysCodeRuleField insert(SysCodeRuleField record) ;
}
