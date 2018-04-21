package com.sofn.provider.ales;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesWtObjectCriterionExpandMapper;
import com.sofn.model.generator.AlesWtObjectCriterion;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@DubboService(interfaceClass = AlesWtObjectCriterionProvider.class)
@CacheConfig(cacheNames = "AlesWtObjectCriterion")
public class AlesWtObjectCriterionProviderImpl extends BaseProviderImpl<AlesWtObjectCriterion> implements AlesWtObjectCriterionProvider {
    @Autowired
    private AlesWtObjectCriterionExpandMapper expandMapper;

    /**
     * 根据参数获取集合
     * @param params
     * @return
     */
    @Override
    public List<AlesWtObjectCriterion> getListByParams(Map<String, Object> params) {
        return expandMapper.getListByParams(params);
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
        boolean b;
        try {
            b = expandMapper.delByParm(params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return b;
    }

    /**
     *
     * @param criterions
     * @return
     */
    @Override
    @Transactional
    public boolean updateSpperLimitByIds(List<AlesWtObjectCriterion> criterions) {
        if (criterions != null && !criterions.isEmpty()) {
            Map<String, Object> params = new HashMap<>(8);
            for (AlesWtObjectCriterion criterion : criterions) {
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

//    @Override
//    public AlesWtObjectCriterion queryByTaskId(String taskId) {
//        AlesWtObjectCriterion cri=expandMapper.queryByTaskId(taskId);
//        return cri;
//    }
}
