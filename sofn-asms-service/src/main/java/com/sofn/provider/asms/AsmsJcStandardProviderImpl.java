package com.sofn.provider.asms;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsJcStandardExpandMapper;
import com.sofn.model.generator.AsmsJcStandard;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = AsmsJcStandardProvider.class)
@CacheConfig(cacheNames = "AsmsJcStandard")
public class AsmsJcStandardProviderImpl extends BaseProviderImpl<AsmsJcStandard> implements AsmsJcStandardProvider {

    @Autowired
    private AsmsJcStandardExpandMapper expandMapper;

    @Override
    public List<AsmsJcStandard> getListByParams(Map<String, Object> params) {
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
