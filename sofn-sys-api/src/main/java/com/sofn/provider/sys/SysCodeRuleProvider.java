package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleData;
import com.sofn.model.generator.SysCodeRuleField;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 */
public interface SysCodeRuleProvider extends BaseProvider<SysCodeRule> {
    /**
     * 分页查询
     * @return
     */
    List<SysCodeRule> getSysCodeList(Page page);

    int getRecordsTotal();

    int addSysCodeRule(SysCodeRule sysCodeRule);
    int addSysCodeRuleAll(SysCodeRule sysCodeRule, List<SysCodeRuleField> sysCodeRuleFields, List<SysCodeRuleData> sysCodeRuleDatas, String opUId);

    int updateSysCodeRule(SysCodeRule sysCodeRule);

    int delSysCodeRule(String sysCodeRuleId);

    public SysCodeRule insert(SysCodeRule record) ;
//    SysCodeRule queryById(String id);
}
