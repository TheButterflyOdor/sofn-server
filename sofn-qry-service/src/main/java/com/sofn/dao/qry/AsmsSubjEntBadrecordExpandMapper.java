package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@MyBatisDao
public interface AsmsSubjEntBadrecordExpandMapper extends BaseExpandMapper {

    List<Map<String,Object>> selectAllByCondition(Map<String, Object> map);
    long getAsmsSubjEntBadrecordCount(Map<String, Object> map);
    Map<String,Object> findEnterpriseById(String enterpriseId);

    List<Map<String,Object>> findSubjEntBadrecodeById(Map<String, Object> map);
    long getSubjEntBadrecordByIdCount(Map<String, Object> map);
    int deleteBadrecordByPrimaryKey(String id);


}
