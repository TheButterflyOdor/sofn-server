package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysTestStandardModel;

import java.util.List;


/**
 * Created by Administrator on 2017/11/13/013.
 */
public interface SysTestStandardProvider extends BaseProvider<SysTestStandardModel> {
    /**
     * 根据标准号或标准名称获取检测标准数据
     * @param standardCode 标准号
     * @param standardName 标准名称
     * @param page         分页参数
     * @return 检测标准数据列表
     */
    PageInfo<SysTestStandardModel> getTestStandards(String standardCode, String standardName, Page page);


    List<SysTestStandardModel> querylist();

    /**
     * 新增检测标准
     * @param model 检测标准数据
     * @return 检测标准数据
     */
    SysTestStandardModel addTestStandard(SysTestStandardModel model);

    /**
     * 更新检测标准
     * @param model 新的检测标准数据(根据ID去更新)
     * @param isUpdateStandardCode
     * @return 检测标准数据
     */
    SysTestStandardModel updateTestStandard(SysTestStandardModel model, boolean isUpdateStandardCode);

    /**
     * 根据检测标准ID获取检测标准数据
     * @param id 检测标准ID
     * @return 检测标准数据
     */
    SysTestStandardModel getTestStandard(String id);

    /**
     * 根据检测标准ID删除检测标准数据
     * @param ids 检测标准ID（多个ID逗号分隔）
     * @param account 账号
     */
    Integer deleteTestStandards(String ids, String account);

    /**
     * 判断指定的标准号是否已存在（标准号在数据库中加了唯一约束）
     * @param standardCode 标准号
     * @return true or false
     */
    Boolean isExistStandardCode(String standardCode);
}
