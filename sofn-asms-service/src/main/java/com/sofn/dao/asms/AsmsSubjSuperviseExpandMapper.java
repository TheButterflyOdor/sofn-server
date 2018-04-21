package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.asms.AsmsSubjSuperviseDto;
import com.sofn.model.generator.AsmsSubjSupervise;
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
public interface AsmsSubjSuperviseExpandMapper extends BaseExpandMapper{

    List<AsmsSubjSupervise> getSubjSuperviseList(Map<String,Object> map);
    List<AsmsSubjSuperviseDto> getSubjSuperviseListNew(Map<String,Object> map);

    /**
     * 查出级别=县级，被辖区域的监管机构--追溯系统调用
     * @param areaId
     * @return
     */
    List<AsmsSubjSupervise> getSubjSuperviseListByArea(String areaId);

    long getSubjSuperviseCount(Map<String,Object> map);

    List<Map<String,Object>> getSubjSvChangeList(Map<String,Object> map);

    long getSubjSvChangeCount(Map<String,Object> map);

    List<Map<String,Object>> getChangeListBySvId(Map<String,Object> map);

    long getChangeCountBySvId(Map<String,Object> map);

    List<Map<String,Object>> getSubjSvCancelList(Map<String,Object> map);

    long getSubjSvCancelCount(Map<String,Object> map);

    List<Map<String,Object>> getSubjSvRevokeList(Map<String,Object> map);

    long getSubjSvRevokeCount(Map<String,Object> map);

    int updateSubjSvStatusById(@Param("id")String id,@Param("status")String status);

    List<Map<String,Object>> getSvUserList(Map<String,Object> map);

    List<Map<String,Object>> getElUserList(Map<String,Object> map);

    List<Map<String,Object>> getDtUserList(Map<String,Object> map);

    List<Map<String,Object>> getSuperiorSvList(Map<String,Object> map);

    List<Map<String,Object>> getSuperiorElList(Map<String,Object> map);

    List<Map<String,Object>> getSuperiorDtList(Map<String,Object> map);

    //监管机构查询是有重复（所属区域+级别+所属行业、机构名称、机构代码3者都要不一样）
    List<AsmsSubjSupervise> getSubjSuperviseByCondition(Map<String,Object> map);

    long getSubjSuperviseCountForSys(Map<String,Object> map);
    List<AsmsSubjSuperviseDto> getSubjSuperviseListForSys(Map<String,Object> map);

    //批量导入监管机构--暂时不用
    int importSubjSupervise(List<AsmsSubjSupervise> list);
}
