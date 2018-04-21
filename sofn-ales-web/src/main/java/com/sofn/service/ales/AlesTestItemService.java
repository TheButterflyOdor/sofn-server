package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.provider.sys.SysTestItemProvider;
import com.sofn.provider.sys.SysTestStandardProvider;
import org.springframework.stereotype.Service;

import java.io.Serializable;

import static com.sun.tools.doclint.Entity.nu;

/*
*
 * @author liujh
 * @version 2017年12月25日11:41:41
 * implements Serializable
*/

@Service
public class AlesTestItemService extends BaseService<SysTestStandardProvider, SysTestStandardModel> {
    @DubboReference
    public void setProvider(SysTestStandardProvider provider) {this.provider = provider;}
    private SysTestItemProvider sysTestItemProvider;
    @DubboReference
    public void setProvider(SysTestItemProvider sysTestItemProvider) {this.sysTestItemProvider = sysTestItemProvider;}

    public PageInfo<SysTestStandardModel> getTestStandards(String standardCode, String standardName, Page page) {
        return provider.getTestStandards(standardCode, standardName, page);
    }
//    public PageInfo<SysTestItemModel> getTestitem(String itemName, String standardCode, Page page){
//        return provider.getTestitemlist(itemName, standardCode, page);
//    }
    public PageInfo<SysTestItemModel> getTestitemlist(String itemName, String standardCode, Page page){
        return sysTestItemProvider.getTestItems(itemName, standardCode, page);
    }

}
