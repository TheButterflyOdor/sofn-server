package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysRegion;
import com.sofn.provider.sys.SysRegionProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dong4j on 16/9/13.
 * Description:
 */
@Service
public class SysRegionService extends BaseService<SysRegionProvider, SysRegion> {
    /**
     * Sets provider.
     *
     * @param provider the provider
     */
    @DubboReference
    public void setProvider(SysRegionProvider provider) {
        this.provider = provider;
    }

    /**
     * Login map.
     *
     * @return the map
     */
    public List<Map<String, Object>> getByRegionCondition(SysRegion sysRegion) {
        return provider.getByRegionCondition(sysRegion);
    }

    public PageInfo<List<Map<String, Object>>> getByRegionCondition(SysRegion sysRegion, Page page) {
        return provider.getByRegionCondition(sysRegion,page);
    }

    public PageInfo<List<Map<String,Object>>> getByRegionCondition(SysRegion sysRegion, Page page, String keyWord) {
        return provider.getByRegionCondition(sysRegion,page,keyWord);
    }

    void addRegion(SysRegion sysRegion){
        this.addRegion(sysRegion);
    }

    public void updateRegion(SysRegion sysRegion){
        this.update(sysRegion);
    }

    public void deleteRegions(Object[] ids){
        String userId=WebUtil.getCurrentUserId();
        provider.deleteRegionsons(ids,userId);
    }


    public List<SysRegion> recursionQuery(SysRegion sysRegion) {
        return provider.recursionQuery(sysRegion);
    }

    public String getRegionNameByRegionCode(String regionCode){
        return provider.getRegionNameByRegionCode(regionCode);
    }

}
