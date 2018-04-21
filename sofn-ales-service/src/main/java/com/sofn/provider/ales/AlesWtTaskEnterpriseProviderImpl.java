package com.sofn.provider.ales;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesWtTaskEnterpriseExpandMapper;
import com.sofn.model.generator.AlesWtTaskEnterprise;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@DubboService(interfaceClass = AlesWtTaskEnterpriseProvider.class)
@CacheConfig(cacheNames = "AlesWtTaskEnterprise")
public class AlesWtTaskEnterpriseProviderImpl extends BaseProviderImpl<AlesWtTaskEnterprise> implements AlesWtTaskEnterpriseProvider {
    @Autowired
    private AlesWtTaskEnterpriseExpandMapper expandMapper;

    /**
     *
     * @param params
     * @return
     */
    @Override
    public List<AlesWtTaskEnterprise> getListByParams(Map<String, Object> params) {
        return expandMapper.getListByParams(params);
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    public AlesWtTaskEnterprise geEntByParams(Map<String, Object> params) {
        return expandMapper.geEntByParams(params);
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    @Transactional
    public boolean delByParm(Map<String, Object> params) {
        if (!StringUtil.isNotBlank(String.valueOf(params.get("checkTaskObjectId")))) {
            return false;
        }
        return expandMapper.delByParm(params);
    }
}
