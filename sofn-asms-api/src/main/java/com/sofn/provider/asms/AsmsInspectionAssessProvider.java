/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsInspectionAssess;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AsmsInspectionAssessProvider extends BaseProvider<AsmsInspectionAssess> {

    /**
     * 根据任务id删除巡查人员信息
     * inspectionTaskId
     * 考核任务id
     */
    @Transactional
    boolean delOldDate(AsmsInspectionAssess taskUser);

    /**
     * 巡查人员考核列表
     */
    PageInfo<Map<String, Object>> getPages(Map<String, Object> params);

    /**
     * 获取实际巡查次数
     */
    Long getRealCount(Map<String, Object> params);

    /**
     * 根据任务id获取巡查人员
     */
    List<AsmsInspectionAssess> getPersonByTaskId(String taskId);

}
