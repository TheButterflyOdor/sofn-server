/**
 * 
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsInspectionTask;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AsmsInspectionTaskProvider extends BaseProvider<AsmsInspectionTask> {

    AsmsInspectionTask findById(String id);

    PageInfo<List<Map<String, Object>>> getPages (Map<String, Object> params);
}
