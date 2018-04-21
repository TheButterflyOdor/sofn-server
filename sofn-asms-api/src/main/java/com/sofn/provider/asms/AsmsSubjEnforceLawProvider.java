package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsSubjElCancel;
import com.sofn.model.generator.AsmsSubjElChange;
import com.sofn.model.generator.AsmsSubjElRevoke;
import com.sofn.model.generator.AsmsSubjEnforceLaw;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:34
 */
public interface AsmsSubjEnforceLawProvider extends BaseProvider<AsmsSubjEnforceLaw>{

    PageInfo getSubjEnforceLawList(Map<String, Object> map);

    PageInfo getSubjEnforceLawListForSys(Map<String, Object> map);

    PageInfo getSubjElChangeList(Map<String, Object> map);

    //统计变更待审核数量
    long countChangeToAudit(Map<String,Object> map);

    PageInfo getSubjElCancelList(Map<String, Object> map);

    //统计注销待审核数量
    long countCancelToAudit(Map<String,Object> map);

    PageInfo getSubjElRevokeList(Map<String, Object> map);

    //统计撤销待审核数量
    long countRevokeToAudit(Map<String,Object> map);

    PageInfo getChangeListByElId(Map<String, Object> map);

    int addSubjEnforceLaw(AsmsSubjEnforceLaw subjEnforceLaw);

    int addSubjElRevoke(AsmsSubjElRevoke subjElRevoke);

    int addSubjElChange(AsmsSubjElChange subjElChange);

    int addSubjElCancel(AsmsSubjElCancel subjElCancel);

    AsmsSubjEnforceLaw findSubjEnforceLawById(String id);

    AsmsSubjElChange findSubjElChangeById(String id);

    AsmsSubjElCancel findSubjElCancelById(String id);

    AsmsSubjElRevoke findSubjElRevokeById(String id);

    void auditSubjElChange(AsmsSubjEnforceLaw subjEnforceLaw, AsmsSubjElChange subjElChange);

    void auditSubjElCancel(AsmsSubjEnforceLaw subjEnforceLaw, AsmsSubjElCancel subjElCancel);

    void auditSubjElRevoke(AsmsSubjEnforceLaw subjEnforceLaw, AsmsSubjElRevoke subjElRevoke);

    //执法机构查询是有重复（所属区域、机构名称、机构代码3者都要不一样）
    List<AsmsSubjEnforceLaw> getSubjEnforceLawByCondition(Map<String,Object> map);
}
