package com.sofn.provider.ales;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesWtObjectCriterion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AlesWtObjectCriterionProvider extends BaseProvider<AlesWtObjectCriterion> {

    /**
     * 自定义获得列表
     * taskId
     * 任务下所有受检项目
     * checkTaskObjectId
     * 检测对象下受检项目
     */
    List<AlesWtObjectCriterion> getListByParams(Map<String, Object> params);

    /**
     * 自定义物理删除
     * checkTaskObjectId
     * 删除检测对象下所有受检项目
     */
    @Transactional
    boolean delByParm(Map<String, Object> params);

    /**
     * 受检项目上限值修改
     * List<AsmsCheckObjectCriterion>
     * 需要修改上限值的列表
     * id
     * 受检项目id
     * spperLimit
     * 受检项目上限值
     */
    @Transactional
    boolean updateSpperLimitByIds(List<AlesWtObjectCriterion> criterions);

//    /**
//     * 根据taskId获取受检项目
//     * @return
//     */
//    AlesWtObjectCriterion queryByTaskId(String taskId);
}
