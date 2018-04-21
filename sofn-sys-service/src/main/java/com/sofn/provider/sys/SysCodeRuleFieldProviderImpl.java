package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysCodeRuleFieldMapper;
import com.sofn.dao.sys.SysCodeRuleFieldExpandMapper;
import com.sofn.model.generator.SysCodeRuleField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wuye on 2016/12/15.
 */
@DubboService(interfaceClass = SysCodeRuleFieldProvider.class)
public class SysCodeRuleFieldProviderImpl extends BaseProviderImpl<SysCodeRuleField> implements SysCodeRuleFieldProvider{

    @Autowired
    private SysCodeRuleFieldMapper sysCodeRuleFieldMapper;

    @Autowired
    private SysCodeRuleFieldExpandMapper sysCodeRuleFieldExpandMapper;


/*
    @Override
    public int addSysCodeRuleField(SysCodeRuleField sysCodeRuleField) {
        int result = sysCodeRuleFieldMapper.insert(sysCodeRuleField);
        return result;
    }

    @Override
    public int updateSysCodeRuleField(SysCodeRuleField sysCodeRuleField) {
        return sysCodeRuleFieldMapper.updateByPrimaryKey(sysCodeRuleField);
    }

    @Override
    public int delSysCodeRuleField(String id) {
        return sysCodeRuleFieldMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysCodeRuleField queryByRuleId(String ruleId) {
        return sysCodeRuleFieldExpandMapper.queryByRuleId(ruleId);
    }*/
    @Override
    public void deleteByRuleId(String ruleId) {
         sysCodeRuleFieldExpandMapper.deleteByRuleId(ruleId);
    }
    @Override
    public List<SysCodeRuleField> queryByRuleId(String ruleId){
        return sysCodeRuleFieldExpandMapper.queryByRuleId(ruleId);
    }
    @Override
    public SysCodeRuleField insert(SysCodeRuleField record) {
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
            sysCodeRuleFieldMapper.insert(record);
            //RedisUtil.set(getCacheKey(record.getId()), record);
            //RedisUtil.set(getCacheKey(record.getId(), "update"), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return record;
    }
}
