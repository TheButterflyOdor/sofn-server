package com.sofn.provider.ales;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesPdStandardExpandMapper;
import com.sofn.model.generator.AlesPdStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@DubboService(interfaceClass = AlesPdStandardProvider.class)
@CacheConfig(cacheNames = "AlesPdStandard")
public class AlesPdStandardProviderImpl extends BaseProviderImpl<AlesPdStandard> implements AlesPdStandardProvider {
    @Autowired
    private AlesPdStandardExpandMapper expandMapper;

    @Override
    public List<AlesPdStandard> getListByParams(Map<String, Object> params) {
        return expandMapper.getListByParams(params);
    }

    @Override
    @Transactional
    public boolean delByParm(Map<String, Object> params) {
        return expandMapper.delByParm(params);
    }
}
