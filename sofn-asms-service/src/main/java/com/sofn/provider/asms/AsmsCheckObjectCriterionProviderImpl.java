package com.sofn.provider.asms;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsCheckObjectCriterionExpandMapper;
import com.sofn.model.generator.AsmsCheckObjectCriterion;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService(interfaceClass = AsmsCheckObjectCriterionProvider.class)
@CacheConfig(cacheNames = "AsmsCheckObjectCriterion")
public class AsmsCheckObjectCriterionProviderImpl extends BaseProviderImpl<AsmsCheckObjectCriterion> implements AsmsCheckObjectCriterionProvider {
    @Autowired
    private AsmsCheckObjectCriterionExpandMapper expandMapper;

    @Override
    public List<AsmsCheckObjectCriterion> getListByParams(Map<String, Object> params) {
        return expandMapper.getListByParams(params);
    }

    @Override
    @Transactional
    public boolean delByParm(Map<String, Object> params) {
        if (!StringUtil.isNotBlank(String.valueOf(params.get("checkTaskObjectId")))) {
            return false;
        }
        boolean b;
        try {
            b = expandMapper.delByParm(params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return b;
    }

    @Override
    @Transactional
    public boolean updateSpperLimitByIds(List<AsmsCheckObjectCriterion> criterions) {
        if (criterions != null && !criterions.isEmpty()) {
            Map<String, Object> params = new HashMap<>(8);
            for (AsmsCheckObjectCriterion criterion : criterions) {
                String id = criterion.getId();
                BigDecimal spperLimit = criterion.getSpperLimit();
                if (StringUtil.isNotBlank(id) && spperLimit != null) {
                    params.put("id", id);
                    params.put("spperLimit", spperLimit);
                }
                if (!expandMapper.updateSpperLimitById(params)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<AsmsCheckObjectCriterion> getDetectionItemBySampleCode(Map<String, Object> params) {
        return expandMapper.getDetectionItemBySampleCode(params);
    }
}
