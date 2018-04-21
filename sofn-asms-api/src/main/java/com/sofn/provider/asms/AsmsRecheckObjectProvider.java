package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsRecheckObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-3-17.
 */
public interface AsmsRecheckObjectProvider  extends BaseProvider<AsmsRecheckObject> {


    /**
     * 复检对象列表
     * */
    PageInfo<AsmsRecheckObject> reCheckObjlist(Map<String, Object> params);

    /**
     *根据任务id获取复检对象列表
     */
    List<AsmsRecheckObject> getObjsByTaskId(String id);
}
