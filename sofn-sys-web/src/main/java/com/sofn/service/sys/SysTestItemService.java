package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.provider.sys.SysTestItemProvider;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/17/017.
 */
@Service
public class SysTestItemService extends BaseService<SysTestItemProvider, SysTestItemModel> {
    @DubboReference
    public void setProvider(SysTestItemProvider provider) {
        this.provider = provider;
    }
    public PageInfo<SysTestItemModel> getTestItems(String itemName, String standardCode, Page page) {
        return provider.getTestItems(itemName, standardCode, page);
    }

    public SysTestItemModel getTestItem(String id) {
        return provider.getTestItem(id);
    }

    public SysTestItemModel addTestItem(SysTestItemModel model) {
        return provider.addTestItem(model);
    }

    public SysTestItemModel updateTestItem(SysTestItemModel model) {
        return provider.updateTestItem(model);
    }

    public Integer deleteTestItems(String ids, String account) {
        return provider.deleteTestItems(ids, account);
    }

    public Boolean isExistTestItem(String standardCode, String itemName) {
        return provider.isExistTestItem(standardCode, itemName);
    }

    public int updateTestItemBystandardCode(String oldStandardCode, String newStandardCode) {
        return provider.updateTestItemBystandardCode(oldStandardCode, newStandardCode);
    }
}
