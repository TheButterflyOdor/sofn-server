package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysTestItemModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17/017.
 */
@MyBatisDao
public interface SysTestItemExpandMapper extends BaseMapper<SysTestItemModel> {
    List<SysTestItemModel> getTestItems(@Param("itemName") String itemName, @Param("standardCode") String standardCode);
    SysTestItemModel getTestItem(@Param("id") String id);
    int addTestItem(SysTestItemModel model);
    int updateTestItem(SysTestItemModel model);
    int updateTestItemBystandardCode(@Param("oldStandardCode") String oldStandardCode, @Param("newStandardCode") String newStandardCode);
    int deleteTestItems(@Param("idList") List<String> idList, @Param("account") String account, @Param("operateTime") Date operateTime);
    int isExistTestItem(@Param("standardCode") String standardCode, @Param("itemName") String itemName);
}
