package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.provider.sys.SysArgiProductProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Service
public class SysProductListService extends BaseService<SysArgiProductProvider, SysArgiProduct> {

    @DubboReference
    public void setSysDictProvider(SysArgiProductProvider provider) {
        this.provider = provider;
    }

    /**
     * 根据行业code和产品名称查询产品列表
     *
     * @param industryCode
     * @param ProName
     * @return
     */
    public PageInfo<Map<String, Object>> getProductByArgs(String industryCode, String ProName, int pageNum, int pageSize) {
        return this.provider.queryByNameOrAlias(industryCode,ProName,pageNum,pageSize);
    }

    /**
     * 根据屠宰前产品id获取其屠宰后产品列表
     * @param pageNum
     * @param pageSize
     * @param productId
     * @return
     */
    public PageInfo<Map<String,Object>> getSlaProductPageInfo(int pageNum, int pageSize, String productId,String keyword) {
        return this.provider.queryProductForSlaughter(productId,keyword,pageNum,pageSize);
    }

    public List<Map<String, Object>> getSlaProduct(String productId, String keyWord) {
        return this.provider.queryProductForSlaughter(productId,keyWord);
    }

    public SysArgiProduct getProductInfoByName(String productName) {
        return this.provider.getProductInfoByName(productName);
    }
}
