package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.provider.sys.SysArgiProductProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yangran
 * @version 2016年12月15日
 */
@Service
public class AdsSysArgiProductService extends BaseService<SysArgiProductProvider, SysArgiProduct> {

    @DubboReference
    public void setProvider(SysArgiProductProvider provider) {
        this.provider = provider;
    }


    /**
     * 查询系统产品分页数据
     * 畜牧业就查询所有的可屠宰的产品，内部实现调用queryProductBeforeSlaughter
     *
     * @param codeType
     * @param keyword
     * @param start
     * @param length
     * @return
     */
    public PageInfo<Map<String, Object>> getArgiProductByArgs(String codeType, String keyword, int start, int length) {
        return provider.queryByNameOrAlias(codeType, keyword, start, length);
    }

    /**
     * 查询农产品所有数据
     * @param sysArgiProduct
     * @param keyword
     * @param page
     * @return
     */
    public PageInfo<Map<String,Object>> queryByCondition(SysArgiProduct sysArgiProduct,String keyword,Page page){
        return provider.queryByCondition(sysArgiProduct,keyword,page);
    }

    public List<Map<String, Object>> getByNameOrAlias(String codeType, String keyword) {
        return provider.getByNameOrAlias(codeType, keyword);
    }
}
