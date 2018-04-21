package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sys.SysTestItemExpandMapper;
import com.sofn.model.generator.SysTestItemModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/17/017.
 */
@DubboService(interfaceClass = SysTestItemProvider.class)
public class SysTestItemProviderImpl extends BaseProviderImpl<SysTestItemModel> implements SysTestItemProvider {
    @Autowired
    private SysTestItemExpandMapper sysTestItemExpandMapper;
    @Override
    public PageInfo<SysTestItemModel> getTestItems(String itemName, String standardCode, Page page) {
        PageHelper.startPage(page.getStart().intValue() + 1, page.getLength().intValue());
        List<SysTestItemModel> list = sysTestItemExpandMapper.getTestItems(itemName, standardCode);
        PageInfo<SysTestItemModel> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public SysTestItemModel getTestItem(String id) {
        return sysTestItemExpandMapper.getTestItem(id);
    }

    @Override
    public SysTestItemModel addTestItem(SysTestItemModel model) {
        model.setId(UUID.randomUUID().toString());
        model.setCreateTime(new Date());
        sysTestItemExpandMapper.addTestItem(model);
        return model;
    }

    @Override
    public SysTestItemModel updateTestItem(SysTestItemModel model) {
        model.setUpdateTime(new Date());
        sysTestItemExpandMapper.updateTestItem(model);
        return model;
    }

    @Override
    public Integer deleteTestItems(String ids, String account) {
        List<String> idList = Arrays.asList(ids.split(","));
        return sysTestItemExpandMapper.deleteTestItems(idList, account, new Date());
    }

    @Override
    public Boolean isExistTestItem(String standardCode, String itemName) {
        return sysTestItemExpandMapper.isExistTestItem(standardCode, itemName) > 0 ? true : false;
    }

    @Override
    public int updateTestItemBystandardCode(String oldStandardCode, String newStandardCode) {
        return sysTestItemExpandMapper.updateTestItemBystandardCode(oldStandardCode, newStandardCode);
    }
}
