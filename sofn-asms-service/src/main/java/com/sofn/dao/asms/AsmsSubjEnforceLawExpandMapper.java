package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.asms.AsmsSubjEnforceLawDto;
import com.sofn.model.generator.AsmsSubjElChange;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper 扩展
 *
 * @author sofn
 * @version 2016年09月27日 上午 9:49
 */
@MyBatisDao
public interface AsmsSubjEnforceLawExpandMapper extends BaseExpandMapper{

    //获取监管机构管辖内的执法机构列表-按条件检索
    List<AsmsSubjEnforceLaw> getSubjectEnforceLawList(Map<String,Object> map);
    //获取监管机构管辖内的执法机构列表-按条件检索
    List<AsmsSubjEnforceLawDto> getSubjectEnforceLawListNew(Map<String,Object> map);
    List<AsmsSubjEnforceLawDto> getSubjectEnforceLawListForSys(Map<String,Object> map);
    long getSubjectEnforceLawCountForSys(Map<String,Object> map);
    long getSubjectEnforceLawCount(Map<String,Object> map);

    //获取监管机构管辖内的执法机构变更申请列表-按条件检索
    List<Map<String,Object>> getSubjElChangeList(Map<String,Object> map);

    long getSubjElChangeCount(Map<String,Object> map);

    //获取监管机构管辖内的执法机构注销申请列表-按条件检索
    List<Map<String,Object>> getSubjElCancelList(Map<String,Object> map);

    long getSubjElCancelCount(Map<String,Object> map);

    //获取监管机构管辖内的监管机构对执法机构发起的撤销申请列表-按条件检索
    List<Map<String,Object>> getSubjElRevokeList(Map<String,Object> map);

    long getSubjElRevokeCount(Map<String,Object> map);

    //获取执法机构自身的变更申请历史列表-按条件检索
    List<AsmsSubjElChange> getChangeListByElId(Map<String, Object> map);

    long getChangeCountByElId(Map<String, Object> map);

    int updateSubjElStatusById(@Param("id")String id, @Param("status")String status);

    //执法机构查询是有重复（所属区域、机构名称、机构代码3者都要不一样）
    List<AsmsSubjEnforceLaw> getSubjEnforceLawByCondition(Map<String,Object> map);
}
