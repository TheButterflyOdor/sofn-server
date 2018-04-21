package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysTestItemModel;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/11/13/013.
 */
public interface SysTestItemProvider extends BaseProvider<SysTestItemModel> {
    /**
     * 根据检测项目名称和标准号获取检测项目数据
     * @param itemName 检测项目名称
     * @param standardCode   标准号
     * @param page     分页参数
     * @return 检测项目数据列表
     */
    PageInfo<SysTestItemModel> getTestItems(String itemName, String standardCode, Page page);

    /**
     * 根据检测项ID获取检测项目数据
     * @param id 检测项ID
     * @return 检测项目数据
     */
    SysTestItemModel getTestItem(String id);
    /**
     * 新增检测项目
     * @param model 检测项目数据
     * @return 检测项目数据
     */
    SysTestItemModel addTestItem(SysTestItemModel model);

    /**
     * 更新检测项目
     * @param model 新的检测项目数据(根据ID去更新)
     * @return 检测项目数据
     */
    SysTestItemModel updateTestItem(SysTestItemModel model);

    /**
     * 根据检测项目ID删除检测项目数据
     * @param ids 检测项目ID(多个id使用逗号分隔)
     * @param account 登录用户账号
     * @return 删除条数
     */
    Integer deleteTestItems(String ids, String account);

    /**
     * 判断在指定标准号下是否已存在检测项
     * @param standardCode 标准号
     * @param itemName 检测项名称
     * @return true or false
     */
    Boolean isExistTestItem(String standardCode, String itemName);

    /**
     * 更新标准号
     * @param oldStandardCode 旧的标准号
     * @param newStandardCode 新的标准号
     * @return
     */
    int updateTestItemBystandardCode(String oldStandardCode, String newStandardCode);
}
