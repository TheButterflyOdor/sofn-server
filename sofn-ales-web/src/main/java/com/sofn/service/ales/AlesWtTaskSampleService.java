package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.ads.AdsMonitorSampleProvider;
import com.sofn.provider.ales.AlesTaskSampleProvider;
import com.sofn.provider.ales.AlesWtObjectCriterionProvider;
import com.sofn.provider.ales.AlesWtTaskMonitorProvider;
import com.sofn.provider.ales.AlesWtTaskSampleProvider;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author LiBing
 */
@Service
public class AlesWtTaskSampleService extends BaseService<AlesWtTaskSampleProvider, AlesWtTaskSample> {
    @DubboReference
    public void setProvider(AlesWtTaskSampleProvider provider) {
        this.provider = provider;
    }
    @DubboReference
    public void setProvider(AlesTaskSampleProvider alesTaskSamplePraovider) {
        this.alesTaskSamplePraovider = alesTaskSamplePraovider;
    }

    AlesWtTaskMonitorProvider alesWtTaskMonitorProvider;
    @DubboReference
    public void setProvider(AlesWtTaskMonitorProvider alesWtTaskMonitorProvider) {
        this.alesWtTaskMonitorProvider = alesWtTaskMonitorProvider;
    }
    AlesWtObjectCriterionProvider alesWtObjectCriterionProvider;
    @DubboReference
    public void setProvider(AlesWtObjectCriterionProvider alesWtObjectCriterionProvider) {
        this.alesWtObjectCriterionProvider = alesWtObjectCriterionProvider;
    }


    AlesTaskSampleProvider alesTaskSamplePraovider;

    AdsMonitorSampleProvider adsMonitorSampleProvider;//检测系统-抽样单管理

    AsmsCheckTaskProvider asmsCheckTaskProvider;//监管系统-监督检查


    @DubboReference
    public void setProvider(AsmsCheckTaskProvider asmsCheckTaskProvider) {
        this.asmsCheckTaskProvider = asmsCheckTaskProvider;
    }

    @DubboReference
    public void setProvider(AdsMonitorSampleProvider adsMonitorSampleProvider) {
        this.adsMonitorSampleProvider = adsMonitorSampleProvider;
    }

    public AlesWtTaskSample findAdsMonitorSample(String sampleCode) {
        return provider.getLocalSampleBySampleCode(sampleCode);
    }

    /**
     * 根据任务id 添加样品抽样单
     */
    public int addAlesWtTaskSample(AlesWtTaskSample alesWtTaskSample) {
        alesWtTaskSample.setIsSync("0");//待发布  未同步
        alesWtTaskSample.setId(StringUtils.getUUIDString().toString().replace("-", ""));//设置委托检测任务ID
        alesWtTaskSample.setDelFlag("N");//初始化 删除标志
        alesWtTaskSample.setCreateTime(new Date());
        return provider.addAlesWtTaskSample(alesWtTaskSample);
    }

    /**
     * 修改样品抽样单
     *
     * @param alesWtTaskSample
     */
    public void updateAlesWtTaskSample(AlesWtTaskSample alesWtTaskSample) {
        AlesWtTaskSample wtTaskSample = provider.queryById(alesWtTaskSample.getId());
        alesWtTaskSample.setCreateTime(wtTaskSample.getCreateTime());
        alesWtTaskSample.setCreateBy(wtTaskSample.getCreateBy());
        alesWtTaskSample.setDelFlag(wtTaskSample.getDelFlag());
        this.update(alesWtTaskSample);
    }

    /**
     * 获取委托检的样品列表
     */
    public PageInfo getAlesWtTaskSamplelist(AlesWtTaskSample alesWtTaskSample, String wtTaskId, String sampleName, String queryCon, String sampleCode, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("wtTaskId", wtTaskId);
        params.put("sampleName", sampleName);
        params.put("queryCon", queryCon);
        params.put("sampleCode", sampleCode);
        return provider.list(params);
    }

//    public AlesWtObjectCriterion queryCheckByCode(String sampleCode) {
//        List<AlesWtObjectCriterion> cris =alesTaskSamplePraovider.getCriBySampleCode(sampleCode);
////        AlesTaskSample sample = alesTaskSamplePraovider.getSampleBySampleCode(sampleCode);
////        AlesWtTaskMonitor alesWtTaskMonitor = alesWtTaskMonitorProvider.queryById(sample.getOrganTaskId());
////        AlesWtObjectCriterion cri =alesWtObjectCriterionProvider.queryByTaskId(alesWtTaskMonitor.getSuperviseCheckTaskId());
//        return cri;
//    }
}
