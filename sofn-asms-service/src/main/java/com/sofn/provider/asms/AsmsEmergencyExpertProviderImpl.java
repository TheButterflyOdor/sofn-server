package com.sofn.provider.asms;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsEmergencyTaskExpertExpandMapper;
import com.sofn.dao.generator.AsmsEmergencyExpertMapper;
import com.sofn.model.generator.AsmsEmergencyExpert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangdong on 2016/9/28.
 */
@DubboService(interfaceClass = AsmsEmergencyExpertProvider.class)
public class AsmsEmergencyExpertProviderImpl extends BaseProviderImpl<AsmsEmergencyExpert> implements AsmsEmergencyExpertProvider  {
    @Autowired
    private AsmsEmergencyExpertMapper asmsEmergencyExpertMapper;

    @Autowired
    private AsmsEmergencyTaskExpertExpandMapper asmsEmergencyExpertExpandMapper;
    public int addAsmsEmergencyExpert(AsmsEmergencyExpert asmsEmergencyExpert){
        return asmsEmergencyExpertMapper.insert(asmsEmergencyExpert);
    }

    @Override
    public int deleteExpert(String emergencyId) {
        return asmsEmergencyExpertExpandMapper.deleteByExpert(emergencyId);
    }
}
