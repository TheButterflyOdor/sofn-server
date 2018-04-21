package com.sofn.service.sys;


import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.provider.sys.SysArgiProductProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
@Service
public class SysArgiProductService extends BaseService<SysArgiProductProvider,SysArgiProduct>{
    @DubboReference
    public void setProvider(SysArgiProductProvider provider) {
        this.provider = provider;
    }

    public List<Map<String,Object>> getByNameOrAlias (String codeType,String keyword){
        return provider.getByNameOrAlias(codeType,keyword);
    }

    public PageInfo<Map<String,Object>> queryByCondition(SysArgiProduct sysArgiProduct,String keyword,Page page){
        return provider.queryByCondition(sysArgiProduct,keyword,page);
    }

    public PageInfo<Map<String,Object>> queryProductBeforeSlaughter(String keyword,Page page){
        return provider.queryProductBeforeSlaughter(keyword,page);
    }

    public void deleteArgiProduct(Object[] ids) {
        String userId=WebUtil.getCurrentUserId();
        provider.deleteArgiProduct(ids,userId);
    }

    public PageInfo<Map<String,Object>> recursionQuery(SysArgiProduct sysArgiProduct) {
        return provider.recursionQuery(sysArgiProduct);
    }

    public PageInfo<Map<String,Object>> recursionQuery(SysArgiProduct sysArgiProduct, String keyword) {
        return provider.recursionQuery(sysArgiProduct,keyword);
    }

    public PageInfo<Map<String,Object>> queryByNameOrAlias(String codeType, String keyword,int start,int length){
        return provider.queryByNameOrAlias(codeType,keyword,start,length);
    }

    public PageInfo<Map<String,Object>> queryProductForSlaughter(String productCode,String keyword,int start,int length){
        return provider.queryProductForSlaughter(productCode,keyword,start,length);
    }

    public List<Map<String,Object>> queryByProductCode(String productCode){
        return provider.queryByProductCode(productCode);
    }

}
