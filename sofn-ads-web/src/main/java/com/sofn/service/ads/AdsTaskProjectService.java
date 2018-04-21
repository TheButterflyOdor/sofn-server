package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsTaskProject;
import com.sofn.provider.ads.AdsTaskProjectProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监测任务_检测项目 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsTaskProjectService extends BaseService<AdsTaskProjectProvider, AdsTaskProject> {

    @DubboReference
    public void setAdsTaskProjectProvider(AdsTaskProjectProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsTaskProject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public List<AdsTaskProject> getEntityWithTaskId(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId", id);
        return provider.getEntityWithTaskId(queryParams);
    }


}