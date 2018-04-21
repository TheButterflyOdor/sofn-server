package com.sofn.service.ales;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesWtTaskEnterprise;
import com.sofn.provider.ales.AlesWtTaskEnterpriseProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AlesWtTaskEnterpriseService extends BaseService<AlesWtTaskEnterpriseProvider, AlesWtTaskEnterprise> {

    @DubboReference
    public void setProvider(AlesWtTaskEnterpriseProvider provider) {
        this.provider = provider;
    }


    public void delByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        provider.delByParm(params);
    }

    public AlesWtTaskEnterprise geEntByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        return provider.geEntByParams(params);
    }
    public List<AlesWtTaskEnterprise> getListByObjId(String monitorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId", monitorId);
        return provider.getListByParams(params);
    }
}
