package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjSlaProduct;
import com.sofn.provider.tts.TtsScltxxcjSlaProductProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 屠宰后产品 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjSlaProductService extends BaseService<TtsScltxxcjSlaProductProvider, TtsScltxxcjSlaProduct> {

    @DubboReference
    public void setTtsScltxxcjSlaProductProvider(TtsScltxxcjSlaProductProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjSlaProduct bean, int pageNum, int pageSize,String productName) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("productName",productName);
        return provider.getPageInfo(queryParams);
    }


    public List<TtsScltxxcjSlaProduct> getProductList() {
        return provider.getProductList();
    }
}