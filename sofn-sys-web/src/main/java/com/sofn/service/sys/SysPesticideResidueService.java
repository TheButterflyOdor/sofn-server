package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.SysPesticideResidueModel;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.provider.sys.SysPesticideResidueProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21/021.
 */
@Service
public class SysPesticideResidueService extends BaseService<SysPesticideResidueProvider, SysPesticideResidueModel> {
    @DubboReference
    public void setProvider(SysPesticideResidueProvider provider) {
        this.provider = provider;
    }

    public PageInfo<SysPesticideResidueModel> getPesticideResidues(String standardCode, String testItemName, String testObjectName, Page page) {
        return provider.getPesticideResidues(standardCode, testItemName, testObjectName, page);
    }

    public SysPesticideResidueModel getPesticideResidue(String id) {
        return provider.getPesticideResidue(id);
    }

    public SysPesticideResidueModel addPesticideResidue(SysPesticideResidueModel model) {
        return provider.addPesticideResidue(model);
    }

    public SysPesticideResidueModel updatePesticideResidue(SysPesticideResidueModel model) {
        return provider.updatePesticideResidue(model);
    }

    public Integer deletePesticideResidue(String ids, String account) {
        return provider.deletePesticideResidue(ids, account);
    }

    public PageInfo<SysArgiProduct> getArgiProductsByName(String typeId, String productName, int pageNum) {
        return provider.getArgiProductsByName(typeId, productName, pageNum);
    }

    public boolean isExistPesticideResidue(String testItemId, String testObjectId) {
        return provider.isExistPesticideResidue(testItemId, testObjectId);
    }

    public PageInfo<SysTestItemModel> getTestItems(String itemName, String standardCode, int pageNum) {
        return provider.getTestItems(itemName, standardCode, pageNum);
    }

    public List<SysTestStandardModel> getAllStandardCodes() {
        return provider.getAllStandardCodes();
    }
}
