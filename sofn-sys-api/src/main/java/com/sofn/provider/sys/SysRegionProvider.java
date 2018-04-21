/**
 * 
 */
package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysRegion;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author sofn
 * @version 2016年5月15日 上午11:21:47
 */
public interface SysRegionProvider extends BaseProvider<SysRegion> {
	PageInfo<List<Map<String,Object>>> getByRegionCondition(SysRegion sysRegion, Page page, String keyWord);

	public PageInfo<List<Map<String, Object>>> getByRegionCondition(SysRegion SysRegion, Page page);

	List<Map<String, Object>> getByRegionCondition(SysRegion SysRegion);

	void deleteRegionsons(Object[] ids, String userId);

    List<SysRegion> recursionQuery(SysRegion sysRegion);
    // 根据regionCode查询regionName，此regionName包含了父级的regionName
	String getRegionNameByRegionCode(String regionCode);

}
