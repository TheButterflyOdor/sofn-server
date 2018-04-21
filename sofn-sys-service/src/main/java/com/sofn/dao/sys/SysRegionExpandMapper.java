package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysRegion;

import java.util.List;
import java.util.Map;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysRegionExpandMapper extends BaseExpandMapper {
    List<Map<String,Object>> getByCondition(SysRegion sysRegion);
    Long getPageCount(SysRegion sysRegion);

    List<String> recursionQuery(Object id);

    List<SysRegion> recursionQueryMap(SysRegion sysRegion);

    List<SysRegion> recursionQueryUp(SysRegion sysRegion);

    List<Map<String,Object>> getByKeyword(Map<String,Object> m);

    long getPageCountByKeyword(Map<String,Object> m);

    SysRegion getRegionByRegionCode(String regionCode);
}