package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sys.SysPesticideResidueExpandMapper;
import com.sofn.dao.sys.SysTestItemExpandMapper;
import com.sofn.dao.sys.SysTestStandardExpandMapper;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.SysPesticideResidueModel;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.model.generator.SysTestStandardModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/21/021.
 */
@DubboService(interfaceClass = SysPesticideResidueProvider.class)
public class SysPesticideResidueProviderImpl extends BaseProviderImpl<SysPesticideResidueModel> implements SysPesticideResidueProvider {
    @Autowired
    private SysPesticideResidueExpandMapper sysPesticideResidueExpandMapper;
    @Autowired
    private SysTestItemExpandMapper sysTestItemExpandMapper;
    @Autowired
    private SysTestStandardExpandMapper sysTestStandardExpandMapper;

    @Override
    public PageInfo<SysPesticideResidueModel> getPesticideResidues(String standardCode, String testItemName, String testObjectName, Page page) {
        PageHelper.startPage(page.getPageNumber().intValue() + 1, page.getLength().intValue());
        List<SysPesticideResidueModel> list = sysPesticideResidueExpandMapper.getPesticideResidues(standardCode, testItemName, testObjectName);
        PageInfo<SysPesticideResidueModel> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public SysPesticideResidueModel getPesticideResidue(String id) {
        return sysPesticideResidueExpandMapper.getPesticideResidue(id);
    }

    @Override
    public SysPesticideResidueModel addPesticideResidue(SysPesticideResidueModel model) {
        model.setId(UUID.randomUUID().toString());
        model.setCreateTime(new Date());
        sysPesticideResidueExpandMapper.addPesticideResidue(model);
        return model;
    }

    @Override
    public SysPesticideResidueModel updatePesticideResidue(SysPesticideResidueModel model) {
        model.setUpdateTime(new Date());
        sysPesticideResidueExpandMapper.updatePesticideResidue(model);
        return model;
    }

    @Override
    public Integer deletePesticideResidue(String ids, String account) {
        List<String> idList = Arrays.asList(ids.split(","));
        return sysPesticideResidueExpandMapper.deletePesticideResidues(idList, account, new Date());
    }

    @Override
    public PageInfo<SysArgiProduct> getArgiProductsByName(String typeId, String productName, int pageNum) {
        PageHelper.startPage(pageNum, 24);
        List<SysArgiProduct> list = sysPesticideResidueExpandMapper.getArgiProducts(typeId, productName);
        PageInfo<SysArgiProduct> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    @Override
    public boolean isExistPesticideResidue(String testItemId, String testObjectId) {
        return sysPesticideResidueExpandMapper.isExistPesticideResidue(testItemId, testObjectId) > 0 ? true : false;
    }

    @Override
    public PageInfo<SysTestItemModel> getTestItems(String itemName, String standardCode, int pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<SysTestItemModel> list = sysTestItemExpandMapper.getTestItems(itemName, standardCode);
        PageInfo<SysTestItemModel> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }

    @Override
    public List<SysTestStandardModel> getAllStandardCodes() {
        return sysTestStandardExpandMapper.getTestStandards("","");
    }
}
