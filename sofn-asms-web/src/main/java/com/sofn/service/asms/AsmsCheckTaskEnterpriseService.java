package com.sofn.service.asms;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsCheckTaskEnterprise;
import com.sofn.provider.asms.AsmsCheckTaskEnterpriseProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsCheckTaskEnterpriseService extends BaseService<AsmsCheckTaskEnterpriseProvider, AsmsCheckTaskEnterprise> {

    @DubboReference
    public void setProvider(AsmsCheckTaskEnterpriseProvider provider) {
        this.provider = provider;
    }


    public void delByObjId(String monitorId){
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId",monitorId );
        provider.delByParm(params);
    }

    public List<AsmsCheckTaskEnterprise> getListByObjId(String monitorId){
        Map<String, Object> params = new HashMap<>();
        params.put("checkTaskObjectId",monitorId );
        return provider.getListByParams(params);
    }
}
