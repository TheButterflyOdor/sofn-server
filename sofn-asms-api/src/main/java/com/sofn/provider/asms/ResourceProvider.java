package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.Resource;

import java.util.Map;

/**
 * Created by zhangdong on 2016/9/20.
 */
public interface ResourceProvider extends BaseProvider<Resource> {

    public PageInfo<Resource> getSysResourceList(Map<String, Object> map);

    public Resource findSysResourceById(String id);


}
