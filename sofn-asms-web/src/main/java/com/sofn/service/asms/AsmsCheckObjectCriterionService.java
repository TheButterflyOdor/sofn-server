package com.sofn.service.asms;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsCheckObjectCriterion;
import com.sofn.provider.asms.AsmsCheckObjectCriterionProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsCheckObjectCriterionService extends BaseService<AsmsCheckObjectCriterionProvider, AsmsCheckObjectCriterion> {

    @DubboReference
    public void setProvider(AsmsCheckObjectCriterionProvider provider) {
        this.provider = provider;
    }


    public void delByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        provider.delByParm(params);
    }

    public List<AsmsCheckObjectCriterion> getListByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        return provider.getListByParams(params);
    }

    public boolean updateSpperLimits(String JSONArray) {
        List<AsmsCheckObjectCriterion> criterions = JSONObject.parseArray(JSONArray, AsmsCheckObjectCriterion.class);
        return provider.updateSpperLimitByIds(criterions);
    }

    public List<AsmsCheckObjectCriterion> getDetectionItemBySampleCode(String sampleCode){
        Map<String,Object> map = new HashMap();
        map.put("sampleCode",sampleCode);
        return provider.getDetectionItemBySampleCode(map);
    }
}
