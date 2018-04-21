package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.asms.AsmsSubjDetectionDto;
import com.sofn.model.generator.AsmsSubjDetection;
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
    List<AsmsSubjDetectionDto> getSubjDetectionListForSys(Map<String, Object> map);
    long getSubjDetectionCountForSys(Map<String, Object> map);
    long getSubjDetectionCount(Map<String, Object> map);

    List<Map<String,Object>> getSubjDtChangeList(Map<String, Object> map);

    long getSubjDtChangeCount(Map<String, Object> map);

    List<Map<String,Object>> getChangeListByDtId(Map<String, Object> map);

    long getChangeCountByDtId(Map<String, Object> map);

    List<Map<String,Object>> getSubjDtCancelList(Map<String, Object> map);

    long getSubjDtCancelCount(Map<String, Object> map);

    List<Map<String,Object>> getSubjDtRevokeList(Map<String, Object> map);

    long getSubjDtRevokeCount(Map<String, Object> map);

    int updateSubjDtStatusById(@Param("id")String id,@Param("status")String status);

    //检测机构查询是有重复（机构名称、机构代码2者都要不一样）
    List<AsmsSubjDetection> getSubjDetectionByCondition(Map<String,Object> map);
}
