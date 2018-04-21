package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检测机构 service 业务逻辑
 * @author yangran
 * @version 2016-11-22
 */
@Service
public class AdsSubjDetectionService extends BaseService<AsmsSubjDetectionProvider, AsmsSubjDetection> {

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AsmsSubjDetection bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        int pageOffset = 1 + pageSize * (pageNum - 1);
        int pageTail = pageNum * pageSize;
        queryParams.put("dtName",bean.getDtName());
        queryParams.put("pageTail", pageTail);
        queryParams.put("pageOffset", pageOffset);
        queryParams.put("areaId", bean.getDtAreaId());
        return provider.getSubjDtListByCondition(queryParams);
    }
}
