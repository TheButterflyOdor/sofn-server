package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysFlow;
import org.apache.ibatis.annotations.Param;
import com.sofn.core.persistence.Page;
import java.util.List;
import java.util.Map;

@MyBatisDao
public interface SysFlowExpandMapper extends BaseExpandMapper {


    //获取满足条件的记录数
    long getAllCount(Map<String, Object> map);

    List<SysFlow> getSysFlowLists(@Param("pager") Page pager, @Param("status")String status,@Param("flowName")String flowName);

}