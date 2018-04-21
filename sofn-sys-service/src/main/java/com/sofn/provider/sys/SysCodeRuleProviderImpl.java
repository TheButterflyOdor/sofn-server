package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysCodeRuleMapper;
import com.sofn.dao.sys.SysCodeRuleExpandMapper;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleData;
import com.sofn.model.generator.SysCodeRuleField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 */
@DubboService(interfaceClass = SysCodeRuleProvider.class)
public class SysCodeRuleProviderImpl extends BaseProviderImpl<SysCodeRule> implements SysCodeRuleProvider {

    @Autowired
    private SysCodeRuleExpandMapper sysCodeRuleExpandMapper;

    @Autowired
    private SysCodeRuleMapper sysCodeRuleMapper;

    @Override
    public List<SysCodeRule> getSysCodeList(Page page){
        return sysCodeRuleExpandMapper.getSysCodeListByPage(page);
    }

    @Override
    public int getRecordsTotal() {
        int count = sysCodeRuleExpandMapper.getCount();
        return count;
    }

    @Override
    public int addSysCodeRule(SysCodeRule sysCodeRule) {
        return sysCodeRuleMapper.insert(sysCodeRule);
    }
    @Override
    public int addSysCodeRuleAll(SysCodeRule sysCodeRule, List<SysCodeRuleField> sysCodeRuleFields, List<SysCodeRuleData> sysCodeRuleDatas, String opUId){

        return 0;
    }
    @Override
    public int updateSysCodeRule(SysCodeRule sysCodeRule) {
        return sysCodeRuleMapper.updateByPrimaryKey(sysCodeRule);
    }

    @Override
    public int delSysCodeRule(String sysCodeRuleId) {
        return sysCodeRuleMapper.deleteByPrimaryKey(sysCodeRuleId);
    }

    @Override
    public SysCodeRule insert(SysCodeRule record) {
        try {
            record.setUpdateTime(new Date());
            logger.debug("{}", record.getDelFlag());
            logger.debug("{}", record.getClass().getSimpleName());
            String key = record.getClass().getSimpleName();
            if (StringUtils.isBlank(record.getId())) {
                record.setId(createId(key));
            }
            record.setCreateTime(new Date());
            record.setDelFlag("N");
            sysCodeRuleMapper.insert(record);
            //RedisUtil.set(getCacheKey(record.getId()), record);
            //RedisUtil.set(getCacheKey(record.getId(), "update"), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return record;
    }
}
