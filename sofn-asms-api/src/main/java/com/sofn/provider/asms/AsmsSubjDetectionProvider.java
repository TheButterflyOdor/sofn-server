package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.AsmsSubjDtChange;
import com.sofn.model.generator.AsmsSubjDtRevoke;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:34
 */
public interface AsmsSubjDetectionProvider extends BaseProvider<AsmsSubjDetection>{

    PageInfo getSubjDetectionList(Map<String, Object> map);
    PageInfo getSubjDetectionListForSys(Map<String, Object> map);

    /**
     * 获取检测机构列表--监测系统调用接口--唯一
     * @param map
     * pageTail -- 分页参数--截止位置（例如每页10个，第3页则是30）
     * pageOffset -- 分页参数--起始位置（例如每页10个，第3页则是21）
     * dtName -- 机构名称模糊查询
     */
    PageInfo getSubjDtListByCondition(Map<String, Object> map);

    PageInfo getSubjDtChangeList(Map<String, Object> map);

    //统计变更待审核数量
    long countChangeToAudit(Map<String,Object> map);

    /**
     * 获取检测机构自己的变更历史--监测系统调用--唯一
     * @param map
     * dtId-检测机构ID
     * pageTail-终止位置（例如每页10个，第3页则是30）
     * pageOffset-起始位置（例如每页10个，第3页则是21）
     * @return
     */
    PageInfo getChangeListByDtId(Map<String,Object> map);

    PageInfo getSubjDtCancelList(Map<String, Object> map);

    //统计注销待审核数量
    long countCancelToAudit(Map<String,Object> map);

    PageInfo getSubjDtRevokeList(Map<String, Object> map);

    //统计撤销待审核数量
    long countRevokeToAudit(Map<String,Object> map);

    int addSubjDetection(AsmsSubjDetection subjDetection);

    int addSubjDtRevoke(AsmsSubjDtRevoke subjDtRevoke);

    /**
     * 申请机构变更--监测系统申请变更调用--唯一
     * @param subjDtChange
     * 以dt开头的为修改字段-必填-要一一对应，如果有修改-传修改后的，没有修改-传修改前的
     * changeBeforeField-修改前字段，按json格式存{"before":{"字段"："内容"}}
     * changeContent-修改内容，文字显示修改了什么
     * 以apply开头的字段为申请信息-必填
     * 以audit开放的字段为审核信息-不填-审核时填
     * @return
     */
    int addSubjDtChange(AsmsSubjDtChange subjDtChange);

    /**
     * 申请机构注销--监测系统申请注销调用--唯一
     * @param subjDtCancel
     * dtId-注销的检测机构ID-必填
     * 以apply开头的字段为申请信息-必填
     * 以audit开放的字段为审核信息-不填-审核时填
     * @return
     */
    int addSubjDtCancel(AsmsSubjDtCancel subjDtCancel);

    AsmsSubjDetection findSubjDetectionById(String id);

    AsmsSubjDtChange findSubjDtChangeById(String id);

    AsmsSubjDtCancel findSubjDtCancelById(String id);

    AsmsSubjDtRevoke findSubjDtRevokeById(String id);

    void auditSubjDtChange(AsmsSubjDetection subjDetection, AsmsSubjDtChange subjDtChange);

    void auditSubjDtCancel(AsmsSubjDetection subjDetection, AsmsSubjDtCancel subjDtCancel);

    void auditSubjDtRevoke(AsmsSubjDetection subjDetection, AsmsSubjDtRevoke subjDtRevoke);

    //检测机构查询是有重复（机构名称、机构代码2者都要不一样）
    List<AsmsSubjDetection> getSubjDetectionByCondition(Map<String,Object> map);
}
