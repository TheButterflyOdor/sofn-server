package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsSubjSupervise;
import com.sofn.model.generator.AsmsSubjSvCancel;
import com.sofn.model.generator.AsmsSubjSvChange;
import com.sofn.model.generator.AsmsSubjSvRevoke;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:34
 */
public interface AsmsSubjSuperviseProvider extends BaseProvider<AsmsSubjSupervise>{

    PageInfo getSubjSuperviseList(Map<String,Object> map);

    /**
     * 查出级别=县级，被辖区域的监管机构--追溯系统调用
     * @param areaId
     * @return
     */
    Map<String,Object> getSubjSuperviseListByArea(String areaId);

    PageInfo getSubjSvChangeList(Map<String,Object> map);

    //统计变更待审核数量
    long countChangeToAudit(Map<String,Object> map);

    PageInfo getChangeListBySvId(Map<String,Object> map);

    PageInfo getSubjSvCancelList(Map<String,Object> map);

    //统计注销待审核数量
    long countCancelToAudit(Map<String,Object> map);

    PageInfo getSubjSvRevokeList(Map<String,Object> map);

    //统计撤销待审核数量
    long countRevokeToAudit(Map<String,Object> map);

    int addSubjSupervise(AsmsSubjSupervise subjSupervise);

    //批量导入监管机构--暂时不用
    int importSubjSupervise(List<AsmsSubjSupervise> list);

    int addSubjSvRevoke(AsmsSubjSvRevoke subjSvRevoke);

    int addSubjSvChange(AsmsSubjSvChange subjSvChange);

    int addSubjSvCancel(AsmsSubjSvCancel subjSvCancel);

    AsmsSubjSupervise findSubjSuperviseById(String id);

    AsmsSubjSvChange findSubjSvChangeById(String id);

    AsmsSubjSvCancel findSubjSvCancelById(String id);

    AsmsSubjSvRevoke findSubjSvRevokeById(String id);

    void auditSubjSvChange(AsmsSubjSupervise subjSupervise,AsmsSubjSvChange subjSvChange);

    void auditSubjSvCancel(AsmsSubjSupervise subjSupervise,AsmsSubjSvCancel subjSvCancel);

    void auditSubjSvRevoke(AsmsSubjSupervise subjSupervise,AsmsSubjSvRevoke subjSvRevoke);

    List getSvUserList(Map<String,Object> map);

    List getElUserList(Map<String,Object> map);

    List getDtUserList(Map<String,Object> map);

    List<Map<String,Object>> getSuperiorSvList(Map<String,Object> map);

    List<Map<String,Object>> getSuperiorElList(Map<String,Object> map);

    List<Map<String,Object>> getSuperiorDtList(Map<String,Object> map);

    //监管机构查询是有重复（所属区域、机构名称、机构代码3者都要不一样）
    List<AsmsSubjSupervise> getSubjSuperviseByCondition(Map<String,Object> map);
    //为系统管理机构用户调用
    PageInfo getSubjSuperviseListForSys(Map<String, Object> map);
}
