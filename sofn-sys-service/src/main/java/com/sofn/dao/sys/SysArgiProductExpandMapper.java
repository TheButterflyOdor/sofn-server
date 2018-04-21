package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysArgiProduct;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysArgiProductExpandMapper extends BaseExpandMapper {
    Long getPageCount(SysArgiProduct sysArgiProduct);

    //Long getPageCount(Map<String, Object> map);

    List<Map<String,Object>> getByCondition(SysArgiProduct sysArgiProduct);

    List<Map<String,Object>> getBykeyWord(Map<String,Object> map);

    List<Map<String,Object>> getAllProductBeforeSlaughter(Map<String,Object> map);

    List<Map<String,Object>> getByNameOrAlias(Map<String,Object> map);

    List<Map<String,Object>> queryProductForSlaughter(Map<String,Object> map);

    List<Map<String,Object>> getByProductCode(Map<String,Object> map);

    List<String> recursionQuery(Object id);

    List<Map<String,Object>> recursionQueryMap(SysArgiProduct sysArgiProduct);

    List<Map<String,Object>> recursionQueryMapByKeyword(Map<String, Object> map);

    long getPageCountByKeyword(Map<String, Object> map);
    long getPageCountByKeyword1(Map<String, Object> map);
    long getPageCountBeforeSlaughter(Map<String, Object> map);
    long getPageCountByNameOrAlias(Map<String, Object> map);

    long getPageCountForSlaughter(Map<String, Object> map);

    List<Map<String,Object>> getSlaProductPageInfo(Map<String, Object> map);
    long getSlaProductPageInfoCount(Map<String, Object> map);

    SysArgiProduct getProductInfoByName(String productName);
    //List<Map<String,Object>> getByCondition(Map<String, Object> map);

}