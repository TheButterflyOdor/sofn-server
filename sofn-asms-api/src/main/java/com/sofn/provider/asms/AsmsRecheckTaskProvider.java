/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsRecheckObject;
import com.sofn.model.generator.AsmsRecheckTask;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AsmsRecheckTaskProvider extends BaseProvider<AsmsRecheckTask> {

    /**
     * 任务列表
     */
    PageInfo<AsmsRecheckTask> list(Map<String, Object> params);

    /**
     * 根据taskId获取关联对象
     */
    List<AsmsRecheckObject> getObjById(String id);

    /**
     * 新增复检任务
     */
    AsmsRecheckTask addAsmsRecheckTask(AsmsRecheckTask asmsRecheckTask);

    /**
     * 修改复检任务
     */
    AsmsRecheckTask updateAsmsRecheckTask(AsmsRecheckTask asmsRecheckTask);
}
