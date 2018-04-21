package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsPecipe;
import com.sofn.provider.ads.AdsPecipeProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 回执单 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsPecipeService extends BaseService<AdsPecipeProvider, AdsPecipe> {

    @DubboReference
    public void setadsPecipeProvider(AdsPecipeProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(AdsPecipe bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }
    public PageInfo getAdsPecipePageInfo(AdsPecipe bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getAdsPecipePageInfo(queryParams);
    }


    public int insert(AdsPecipe adsPecipe){
        return provider.insert(adsPecipe);
    }
}