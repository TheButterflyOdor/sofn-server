/**
 * 
 */
package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleNew;
import com.sofn.model.generator.SysRegion;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
public interface SysCodeRuleNewProvider extends BaseProvider<SysCodeRuleNew> {
    /**
     * 分页查询
     * @return
     */
    List<SysCodeRuleNew> getSysCodeList(Page page);
    public int getRecordsTotal();
    SysCodeRuleNew getDemoImg(String ruleId);
    SysCodeRuleNew queryRuleAndField(String ruleId);
}
