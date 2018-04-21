package com.sofn.service.asms;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsPdStandard;
import com.sofn.provider.asms.AsmsPdStandardProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsPdStandardService extends BaseService<AsmsPdStandardProvider, AsmsPdStandard> {

    @DubboReference
    public void setProvider(AsmsPdStandardProvider provider) {
        this.provider = provider;
    }


    public void delByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        provider.delByParm(params);
    }

    public List<AsmsPdStandard> getListByTaskId(String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        return provider.getListByParams(params);
    }
}
