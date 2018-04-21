package com.sofn.provider.asms;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsCheckTaskEnterpriseExpandMapper;
import com.sofn.model.generator.AsmsCheckTaskEnterprise;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = AsmsCheckTaskEnterpriseProvider.class)
@CacheConfig(cacheNames = "AsmsCheckTaskEnterprise")
public class AsmsCheckTaskEnterpriseProviderImpl extends BaseProviderImpl<AsmsCheckTaskEnterprise> implements AsmsCheckTaskEnterpriseProvider {
    @Autowired
    private AsmsCheckTaskEnterpriseExpandMapper expandMapper;

    @Override
    public List<AsmsCheckTaskEnterprise> getListByParams(Map<String, Object> params) {
        return expandMapper.getListByParams(params);
    }

    @Override
    @Transactional
    public boolean delByParm(Map<String, Object> params) {
        if (!StringUtil.isNotBlank(String.valueOf(params.get("checkTaskObjectId")))) {
            return false;
        }
        return expandMapper.delByParm(params);
    }
}
