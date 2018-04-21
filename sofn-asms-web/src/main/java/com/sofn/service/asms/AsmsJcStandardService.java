package com.sofn.service.asms;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsJcStandard;
import com.sofn.provider.asms.AsmsJcStandardProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsJcStandardService extends BaseService<AsmsJcStandardProvider, AsmsJcStandard> {

    @DubboReference
    public void setProvider(AsmsJcStandardProvider provider) {
        this.provider = provider;
    }


    public void delByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        provider.delByParm(params);
    }

    public List<AsmsJcStandard> getListByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        return provider.getListByParams(params);
    }
}
