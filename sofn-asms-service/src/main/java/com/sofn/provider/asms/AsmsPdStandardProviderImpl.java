package com.sofn.provider.asms;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsPdStandardExpandMapper;
import com.sofn.model.generator.AsmsPdStandard;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = AsmsPdStandardProvider.class)
@CacheConfig(cacheNames = "AsmsPdStandard")
public class AsmsPdStandardProviderImpl extends BaseProviderImpl<AsmsPdStandard> implements AsmsPdStandardProvider {
    @Autowired
    private AsmsPdStandardExpandMapper expandMapper;

    @Override
    public List<AsmsPdStandard> getListByParams(Map<String, Object> params) {
        return expandMapper.getListByParams(params);
    }

    @Override
    @Transactional
    public boolean delByParm(Map<String, Object> params) {
        if (!StringUtil.isNotBlank(String.valueOf(params.get("taskId")))) {
            return false;
        }
        return expandMapper.delByParm(params);
    }
}
