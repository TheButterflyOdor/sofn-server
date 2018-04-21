package com.sofn.service.ales;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesWtObjectCriterion;
import com.sofn.provider.ales.AlesWtObjectCriterionProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AlesWtObjectCriterionService extends BaseService<AlesWtObjectCriterionProvider, AlesWtObjectCriterion> {

    @DubboReference
    public void setProvider(AlesWtObjectCriterionProvider provider) {
        this.provider = provider;
    }


    public void delByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        provider.delByParm(params);
    }

    public List<AlesWtObjectCriterion> getListByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        return provider.getListByParams(params);
    }

    public boolean updateSpperLimits(String JSONArray) {
        List<AlesWtObjectCriterion> criterions = JSONObject.parseArray(JSONArray, AlesWtObjectCriterion.class);
        return provider.updateSpperLimitByIds(criterions);
    }
}
