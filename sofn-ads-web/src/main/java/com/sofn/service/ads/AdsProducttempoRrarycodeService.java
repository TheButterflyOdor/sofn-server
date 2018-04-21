package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsProducttempoRrarycode;
import com.sofn.provider.ads.AdsProducttempoRrarycodeProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 产品临时码 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsProducttempoRrarycodeService extends BaseService<AdsProducttempoRrarycodeProvider, AdsProducttempoRrarycode> {

    @DubboReference
    public void setAdsProducttempoRrarycodeProvider(AdsProducttempoRrarycodeProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsProducttempoRrarycode bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }


}