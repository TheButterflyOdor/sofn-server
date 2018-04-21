package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.provider.sys.SysTestStandardProvider;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/14/014.
 */
@Service
public class SysTestStandardService extends BaseService<SysTestStandardProvider, SysTestStandardModel> {
    @DubboReference
    public void setProvider(SysTestStandardProvider provider) {
        this.provider = provider;
    }

    public PageInfo<SysTestStandardModel> getTestStandards(String standardCode, String standardName, Page page) {
        return provider.getTestStandards(standardCode, standardName, page);
    }

    public SysTestStandardModel getTestStandard(String id) {
        return provider.getTestStandard(id);
    }

    public SysTestStandardModel addTestStandard(SysTestStandardModel model){
        return provider.addTestStandard(model);
    }

    public SysTestStandardModel updateTestStandard(SysTestStandardModel model, boolean isUpdateStandardCode) {
        return provider.updateTestStandard(model, isUpdateStandardCode);
    }

    public Integer deleteTestStandards(String ids, String account) {
        return provider.deleteTestStandards(ids, account);
    }

    public Boolean isExistStandardCode(String standardCode) {
        return provider.isExistStandardCode(standardCode);
    }
}
