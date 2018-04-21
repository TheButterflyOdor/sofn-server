package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysArgiProduct;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface SysArgiProductProvider extends BaseProvider<SysArgiProduct> {
    PageInfo<Map<String,Object>> queryByCondition(SysArgiProduct sysArgiProduct,String keyword,Page page);

    //查询所有的畜牧业屠宰前产品
    PageInfo<Map<String,Object>> queryProductBeforeSlaughter(String keyword,Page page);

    PageInfo<Map<String,Object>> recursionQuery(SysArgiProduct sysArgiProduct);

    void deleteArgiProduct(Object[] ids, String userId);

    PageInfo<Map<String,Object>> recursionQuery(SysArgiProduct sysArgiProduct, String keyword);

    //查询所有的农产品
    //畜牧业就查询所有的可屠宰的产品，内部实现调用queryProductBeforeSlaughter
    //种植业就查所有的四五级产品，水产业就查第六级产品
    List<Map<String,Object>> getByNameOrAlias(String codeType,String keyword);

    List<Map<String,Object>> queryByProductCode(String productCode);

    //查询所有的农产品
    //畜牧业就查询所有的可屠宰的产品，内部实现调用queryProductBeforeSlaughter
    //种植业就查所有的四五级产品，水产业就查第六级产品（分页）
    PageInfo<Map<String,Object>> queryByNameOrAlias(String codeType,String keyword,int start,int length);

    //根据productCode查询它对应的第三级目录下面的四五级产品(分页查询)
    //start是当前页数 length是要展示的个数
    PageInfo<Map<String,Object>> queryProductForSlaughter(String productCode,String keyword,int start,int length);

    //根据productCode查询它对应的第三级目录下面的四五级产品
    List<Map<String,Object>> queryProductForSlaughter(String productCode,String keyword);

    PageInfo<Map<String,Object>> getSlaProductPageInfo(int pageNum, int pageSize, String productId);
    //根据产品名称获取产品信息
    SysArgiProduct getProductInfoByName(String productName);

}
