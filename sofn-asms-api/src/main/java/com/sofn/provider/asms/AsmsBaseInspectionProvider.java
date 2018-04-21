package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.sys.SysUserBean;

import java.util.Map;

/**
 * @author sofn
 * @version 2016年08月25日 下午 4:32
 */
public interface AsmsBaseInspectionProvider extends BaseProvider<AsmsBaseInspection> {

    int addBaseInspection(String inspectionUserId, AsmsBaseInspection baseInspection);

    /**
     * 巡查人员考核报告列表接口-查询对应人员相关的基地巡查报告
     *
     * @param map inspectionPersonId-巡查人员ID
     * @return
     */
    PageInfo<AsmsBaseInspection> getBaseInspectionList(Map<String, Object> map);

    /**
     * 根据条件查询基地巡查报告
     *
     * @param map
     * @return
     */
    PageInfo getBaseInspectionAllList(Map<String, Object> map);

    AsmsBaseInspection findBaseInspectionById(String id);

    int updateBaseInspection(String inspectionUserId, AsmsBaseInspection baseInspection);

    int deleteBaseInspection(String id);

    /**
     * 监督抽查调用接口
     *
     * @param elState--执法状态，为0表示未进行执法检查，为64位ID则表示有执法检查
     * @param baseInspectionId--基地巡查ID
     * @return
     */
    int updateElState(String elState, String baseInspectionId);

    /**
     * 巡查人员列表
     *
     * @param map
     * @return
     */
    PageInfo<SysUserBean> getSysUserListByOrgId(Map<String,Object> map);
}
