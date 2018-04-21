package com.sofn.service.ales;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.MQInfo;
import com.sofn.provider.asms.AsmsMQInfoProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sofn
 * @version 2018年03月01日 下午 3:39
 */
@Service
public class AlesMQInfoService {

    @DubboReference
    public void setProvider(AsmsMQInfoProvider asmsMQInfoProvider) {
        this.asmsMQInfoProvider = asmsMQInfoProvider;
    }
    private AsmsMQInfoProvider asmsMQInfoProvider;

    public List<MQInfo> getForConsumer(String consumer){
        return asmsMQInfoProvider.getForConsumer(consumer);
    }

}
