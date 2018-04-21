package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsModelJudgeStandard;
import com.sofn.provider.sys.AdsModelJudgeStandardProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 模型_判定标准 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsModelJudgeStandardService extends BaseService<AdsModelJudgeStandardProvider, AdsModelJudgeStandard> {

    @DubboReference
    public void setAdsModelJudgeStandardProvider(AdsModelJudgeStandardProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsModelJudgeStandard bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", bean.getName());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public int batchDelete(AdsModelJudgeStandard adsModelJudgeStandard){
        return provider.batchDelete(adsModelJudgeStandard);
    }

}