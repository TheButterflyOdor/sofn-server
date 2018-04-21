package com.sofn.service.ales;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesPdStandard;
import com.sofn.provider.ales.AlesPdStandardProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlesPdStandardService extends BaseService<AlesPdStandardProvider, AlesPdStandard> {

    @DubboReference
    public void setProvider(AlesPdStandardProvider provider) {
        this.provider = provider;
    }


    public void delByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        provider.delByParm(params);
    }

    public List<AlesPdStandard> getListByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        return provider.getListByParams(params);
    }
}
