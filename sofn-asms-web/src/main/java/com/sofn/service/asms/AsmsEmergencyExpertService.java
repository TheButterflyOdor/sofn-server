package com.sofn.service.asms;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.AsmsEmergencyExpert;
import com.sofn.provider.asms.AsmsEmergencyExpertProvider;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhangdong on 2016/9/28.
 */
@Service
public class AsmsEmergencyExpertService extends BaseService<AsmsEmergencyExpertProvider,AsmsEmergencyExpert> {
    @DubboReference
    public void setAsmsEmergencyExpertProvider(AsmsEmergencyExpertProvider provider){

        this.provider = provider;
    }
    public int addAsmsEmergencyExpert(AsmsEmergencyExpert asmsEmergencyExpert){
        asmsEmergencyExpert.setId(StringUtils.getUUIDString());
        asmsEmergencyExpert.setDelFlag("N");
        asmsEmergencyExpert.setCreateTime(new Date());
        return provider.addAsmsEmergencyExpert(asmsEmergencyExpert);
    }
    public int deleteemergencyId(String emergencyId){
        return provider.deleteExpert(emergencyId);
    }
}
