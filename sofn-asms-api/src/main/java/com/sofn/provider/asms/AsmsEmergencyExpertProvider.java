package com.sofn.provider.asms;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsEmergencyExpert;

/**
 * Created by Administrator on 2016/9/28.
 */
public interface AsmsEmergencyExpertProvider extends BaseProvider<AsmsEmergencyExpert> {

    public int addAsmsEmergencyExpert(AsmsEmergencyExpert asmsEmergencyExpert);

    public int deleteExpert(String emergencyId);

}
