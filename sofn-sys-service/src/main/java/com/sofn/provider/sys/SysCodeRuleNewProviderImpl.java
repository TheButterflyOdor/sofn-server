package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysCodeRuleMapper;
import com.sofn.dao.generator.SysCodeRuleNewMapper;
import com.sofn.dao.sys.SysCodeRuleExpandMapper;
import com.sofn.dao.sys.SysCodeRuleNewExpandMapper;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleData;
import com.sofn.model.generator.SysCodeRuleField;
import com.sofn.model.generator.SysCodeRuleNew;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@DubboService(interfaceClass = SysCodeRuleNewProvider.class)
public class SysCodeRuleNewProviderImpl extends BaseProviderImpl<SysCodeRuleNew> implements SysCodeRuleNewProvider {

    @Autowired
    private SysCodeRuleNewExpandMapper sysCodeRuleExpandMapper;

    @Autowired
    private SysCodeRuleNewMapper sysCodeRuleNewMapper;


    @Override
    public List<SysCodeRuleNew> getSysCodeList(Page page) {
        return sysCodeRuleExpandMapper.getSysCodeListByPage(page);
    }

    @Override
    public int getRecordsTotal() {
        int count = sysCodeRuleExpandMapper.getCount();
        return count;
    }

    @Override
    public SysCodeRuleNew getDemoImg(String ruleId) {
        return sysCodeRuleExpandMapper.getDemoImg(ruleId);
    }

    @Override
    public SysCodeRuleNew queryRuleAndField(String ruleId) {
        return sysCodeRuleExpandMapper.queryRuleAndField(ruleId);
    }
}
