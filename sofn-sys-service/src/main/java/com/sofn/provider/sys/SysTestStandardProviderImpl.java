package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sys.SysTestStandardExpandMapper;
import com.sofn.model.generator.SysTestStandardModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/14/014.
 */
@DubboService(interfaceClass = SysTestStandardProvider.class)
public class SysTestStandardProviderImpl extends BaseProviderImpl<SysTestStandardModel> implements SysTestStandardProvider {
    @Autowired
    private SysTestStandardExpandMapper sysTestStandardExpandMapper;

    @Override
    public PageInfo<SysTestStandardModel> getTestStandards(String standardCode, String standardName, Page page) {
        PageHelper.startPage(page.getStart().intValue() + 1, page.getLength().intValue());
        List<SysTestStandardModel> list = sysTestStandardExpandMapper.getTestStandards(standardCode, standardName);
        PageInfo<SysTestStandardModel> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<SysTestStandardModel> querylist() {
        List<SysTestStandardModel> querylist = sysTestStandardExpandMapper.querylist();
        return querylist;
    }


    @Override
    public SysTestStandardModel addTestStandard(SysTestStandardModel model) {
        model.setId(UUID.randomUUID().toString());
        model.setCreateTime(new Date());
        sysTestStandardExpandMapper.addTestStandard(model);
        return model;
    }

    @Override
    public SysTestStandardModel updateTestStandard(SysTestStandardModel model, boolean isUpdateStandardCode) {
        model.setUpdateTime(new Date());
        sysTestStandardExpandMapper.updateTestStandard(model, isUpdateStandardCode);
        return model;
    }

    @Override
    public SysTestStandardModel getTestStandard(String id) {
        return sysTestStandardExpandMapper.getTestStandard(id);
    }

    @Override
    public Integer deleteTestStandards(String ids, String account) {
        List<String> idList = Arrays.asList(ids.split(","));
        return sysTestStandardExpandMapper.deleteTestStandards(idList, account, new Date());
    }

    @Override
    public Boolean isExistStandardCode(String standardCode) {
        return sysTestStandardExpandMapper.isExistStandardCode(standardCode) > 0 ? true : false;
    }
}
