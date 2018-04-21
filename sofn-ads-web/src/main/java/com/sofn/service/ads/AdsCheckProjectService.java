package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsCheckProject;
import com.sofn.provider.ads.AdsCheckProjectProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 检测项目 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsCheckProjectService extends BaseService<AdsCheckProjectProvider, AdsCheckProject> {

    @DubboReference
    public void setAdsCheckProjectProvider(AdsCheckProjectProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsCheckProject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }


}