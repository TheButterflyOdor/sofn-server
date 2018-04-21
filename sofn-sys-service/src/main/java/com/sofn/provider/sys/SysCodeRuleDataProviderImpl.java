package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.SysCodeRuleDataMapper;
import com.sofn.dao.sys.SysCodeRuleDataExpandMapper;
import com.sofn.model.generator.SysCodeRuleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wuye on 2016/12/15.
 */
@DubboService(interfaceClass = SysCodeRuleDataProvider.class)
public class SysCodeRuleDataProviderImpl extends BaseProviderImpl<SysCodeRuleData> implements SysCodeRuleDataProvider{

    @Autowired
    private SysCodeRuleDataMapper sysCodeRuleDataMapper;

    @Autowired
    private SysCodeRuleDataExpandMapper sysCodeRuleDataExpandMapper;

    @Override
    public int addSysCodeRuleData(SysCodeRuleData sysCodeRuleData) {
        return sysCodeRuleDataMapper.insert(sysCodeRuleData);
    }

    @Override
    public int updateSysCodeRuleData(SysCodeRuleData sysCodeRuleData) {
        return sysCodeRuleDataMapper.updateByPrimaryKey(sysCodeRuleData);
    }

    @Override
    public int delSysCodeRuleData(String id) {
        return sysCodeRuleDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysCodeRuleData> queryByRuleId(String ruleId) {
        return sysCodeRuleDataExpandMapper.queryByRuleId(ruleId);
    }

    @Override
    public SysCodeRuleData queryByFieldId(String fieldId) {
        return sysCodeRuleDataExpandMapper.queryByFieldId(fieldId);
    }

    @Override
    public SysCodeRuleData insert(SysCodeRuleData record) {
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
            sysCodeRuleDataMapper.insert(record);
            //RedisUtil.set(getCacheKey(record.getId()), record);
            //RedisUtil.set(getCacheKey(record.getId(), "update"), record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return record;
    }

    @Override
    public void deleteByFieldId(String fieldId) {
         sysCodeRuleDataExpandMapper.deleteByFieldId(fieldId);
    }
    @Override
    public void deleteByRuleId(String ruleId) {
        sysCodeRuleDataExpandMapper.deleteByRuleId(ruleId);
    }
}
