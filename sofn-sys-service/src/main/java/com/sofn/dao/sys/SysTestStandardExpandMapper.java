package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysTestStandardModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/13/013.
 */
@MyBatisDao
public interface SysTestStandardExpandMapper extends BaseMapper<SysTestStandardModel> {
    List<SysTestStandardModel> getTestStandards(@Param("standardCode") String standardCode, @Param("standardName") String standardName);
    SysTestStandardModel getTestStandard(@Param("id") String id);
    int updateTestStandard(@Param("sysTestStandardModel") SysTestStandardModel sysTestStandardModel, @Param("isUpdateStandardCode") boolean isUpdateStandardCode);
    int addTestStandard(SysTestStandardModel sysTestStandardModel);
    int deleteTestStandards(@Param("idList") List<String> idList, @Param("account") String account, @Param("operateTime") Date operateTime);
    int isExistStandardCode(@Param("standardCode") String standardCode);
    List<SysTestStandardModel> querylist();
}
