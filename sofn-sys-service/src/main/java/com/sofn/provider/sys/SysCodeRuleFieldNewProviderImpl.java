package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysCodeRuleFieldMapper;
import com.sofn.dao.sys.SysCodeRuleFieldExpandMapper;
import com.sofn.dao.sys.SysCodeRuleFieldNewExpandMapper;
import com.sofn.model.generator.SysCodeRuleField;
import com.sofn.model.generator.SysCodeRuleFieldNew;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author gaoyi
 * @version 2017/4/26
 */
@DubboService(interfaceClass = SysCodeRuleFieldNewProvider.class)
public class SysCodeRuleFieldNewProviderImpl extends BaseProviderImpl<SysCodeRuleFieldNew> implements SysCodeRuleFieldNewProvider{

    @Autowired
    private SysCodeRuleFieldMapper sysCodeRuleFieldMapper;

    @Autowired
    private SysCodeRuleFieldNewExpandMapper sysCodeRuleFieldNewExpandMapper;
}
