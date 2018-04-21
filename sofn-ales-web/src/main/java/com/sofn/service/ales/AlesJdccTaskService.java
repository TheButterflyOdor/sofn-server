package com.sofn.service.ales;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesJdccTaskSample;
import com.sofn.provider.ales.AlesJdccTaskSampleProvider;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import com.sofn.web.ales.AlesEnum;
import org.springframework.stereotype.Service;


/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@Service
public class AlesJdccTaskService extends BaseService<AlesJdccTaskSampleProvider, AlesJdccTaskSample> {
    @DubboReference
    public void setProvider(AlesJdccTaskSampleProvider provider) {
        this.provider = provider;
    }

    /**
     * 新增抽样单
     */
    public boolean addSample(AlesJdccTaskSample taskSample) {
        taskSample.setResult(AlesEnum.NOTDETECTED.getCode());
        taskSample.setIsSync(AlesEnum.NON_SAMPLE.getCode());
        AlesJdccTaskSample alesTaskSample = this.add(taskSample);
        if (alesTaskSample != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改抽样单
     */
    public boolean updSample(AlesJdccTaskSample taskSample) {
        super.update(taskSample);
        return true;
    }

    /**
     * 根据id获取抽样单信息
     */
    public AlesJdccTaskSample getSampleById(String id) {
        return super.queryById(id);
    }

}
