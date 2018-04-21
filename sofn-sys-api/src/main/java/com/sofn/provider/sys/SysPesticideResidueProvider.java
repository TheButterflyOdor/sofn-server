package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.SysPesticideResidueModel;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.model.generator.SysTestStandardModel;

import java.util.List;

/**
 * Created by Administrator on 2017/11/13/013.
 */
public interface SysPesticideResidueProvider extends BaseProvider<SysPesticideResidueModel> {

    /**
     * 根据查询条件获取检测农药残留限量数据
     *
     * @param standardCode   标准号
     * @param testItemName   检测项名称
     * @param testObjectName 检测对象名称(农产品分类名称)
     * @param page           分页参数
     * @return 检测农药残留限量数据列表
     */
    PageInfo<SysPesticideResidueModel> getPesticideResidues(String standardCode, String testItemName, String testObjectName, Page page);

    /**
     * 根据id获取检测农药残留限量数据
     * @param id   检测农药残留限量ID
     * @return     检测农药残留限量数据
     */
    SysPesticideResidueModel getPesticideResidue(String id);

    /**
     * 新增检测农药残留限量
     *
     * @param model 检测农药残留限量数据
     * @return 检测农药残留限量
     */
    SysPesticideResidueModel addPesticideResidue(SysPesticideResidueModel model);

    /**
     * 更新检测农药残留限量
     *
     * @param model 新的检测农药残留限量数据(根据ID去更新)
     * @return 检测农药残留限量
     */
    SysPesticideResidueModel updatePesticideResidue(SysPesticideResidueModel model);

    /**
     * 根据检测农药残留限量ID删除检测农药残留限量数据
     *
     * @param ids 检测农药残留限量ID
     * @param account
     * @return 删除条数
     */
    Integer deletePesticideResidue(String ids, String account);

    /**
     * 根据农产品名称或关键字获取农产品分类数据
     *
     * @param typeId   农产品分类ID
     * @param productName 农产品名称
     * @param pageNum  当前页号
     * @return 获取农产品分类数据列表
     */
    PageInfo<SysArgiProduct> getArgiProductsByName(String typeId, String productName, int pageNum);

    /**
     * 判断指定的检测对象的检测项的农药残留限量数据是否存在
     * @param testItemId     检测项ID
     * @param testObjectId   检测对象ID（农产品代码）
     * @return true or false
     */
    boolean isExistPesticideResidue(String testItemId, String testObjectId);

    /**
     * 根据检测项目名称和标准号获取检测项目数据
     *
     * @param itemName 检测项目名称
     * @param standardCode 标准号
     * @param pageNum 当前页号
     * @return 检测项目列表
     */
    PageInfo<SysTestItemModel> getTestItems(String itemName, String standardCode, int pageNum);

    /**
     * 获取所有标准号
     * @return 标准号数据
     */
    List<SysTestStandardModel> getAllStandardCodes();
}
