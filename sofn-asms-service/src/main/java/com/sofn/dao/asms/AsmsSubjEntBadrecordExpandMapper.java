package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsSubjEntTemp;

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

    List<Map<String,Object>> findSubjEntBadrecodeById(Map<String,Object> map);
    long getSubjEntBadrecordByIdCount(Map<String,Object> map);
    int deleteBadrecordByPrimaryKey(String id);
    /**
     * 通过EntityIdCode查询实体对象
     *
     * @param map
     * @return
     */
    AsmsSubjEntTemp selectByEntityIdCode(Map<String, Object> map);


}
