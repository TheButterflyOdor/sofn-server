package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysFlow;
import java.util.List;
@MyBatisDao
public interface SysFlowMapper extends BaseMapper<SysFlow> {
    int deleteByPrimaryKey(String id);

    int insert(SysFlow record);

    SysFlow selectByPrimaryKey(String id);

    List<SysFlow> selectAll();

    int updateByPrimaryKey(SysFlow record);
}