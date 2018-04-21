package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.AsmsSubjDtChange;
import com.sofn.model.generator.AsmsSubjDtRevoke;
import com.sofn.model.qry.AsmsSubjDetectionDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper 扩展
 *
 * @author sofn
 * @version 2016年09月12日 下午 4:02
 */
@MyBatisDao
public interface AsmsSubjDetectionExpandMapper extends BaseExpandMapper{

    List<AsmsSubjDetection> getSubjDetectionList(Map<String, Object> map);
    List<AsmsSubjDetectionDto> getSubjDetectionListNew(Map<String, Object> map);

    long getSubjDetectionCount(Map<String, Object> map);

    List<Map<String,Object>> getSubjDtChangeList(Map<String, Object> map);

    long getSubjDtChangeCount(Map<String, Object> map);

    List<Map<String,Object>> getChangeListByDtId(Map<String, Object> map);

    long getChangeCountByDtId(Map<String, Object> map);

    List<Map<String,Object>> getSubjDtCancelList(Map<String, Object> map);

    long getSubjDtCancelCount(Map<String, Object> map);

    List<Map<String,Object>> getSubjDtRevokeList(Map<String, Object> map);

    long getSubjDtRevokeCount(Map<String, Object> map);

    int updateSubjDtStatusById(@Param("id") String id, @Param("status") String status);

    //检测机构查询是有重复（机构名称、机构代码2者都要不一样）
    List<AsmsSubjDetection> getSubjDetectionByCondition(Map<String, Object> map);

    //根据AsmsSubjDetection  id查询AsmsSubjDetection
    AsmsSubjDetection selectAsmsSubjDetectionByPrimaryKey(String id);

    //AsmsSubjDtChange  AsmsSubjDtChange
    AsmsSubjDtChange selectAsmsSubjDtChangeByPrimaryKey(String id);

    //根据AsmsSubjDtCancel id查询AsmsSubjDtCancel
    AsmsSubjDtCancel selectAsmsSubjDtCancelByPrimaryKey(String id);

    //根据AsmsSubjDtRevoke  id查询AsmsSubjDtRevoke
    AsmsSubjDtRevoke selectAsmsSubjDtRevokeByPrimaryKey(String id);
}
