package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesWtTaskEnterprise;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface AlesWtTaskEnterpriseExpandMapper extends BaseExpandMapper {
    //查询集合
    List<AlesWtTaskEnterprise> getListByParams(Map<String, Object> params);
    //查询单个
    AlesWtTaskEnterprise geEntByParams(Map<String, Object> params);

    boolean delByParm(Map<String, Object> params);
}