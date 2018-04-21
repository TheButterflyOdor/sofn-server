package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysResource;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
public interface SysResourceProvider extends BaseProvider<SysResource> {
    /**
     * 分页条件查询
     * @param map
     * @return
     */
    PageInfo<SysResource> getPageList(Map<String, Object> map);

    /**
     * 分页条件查询
     * @param map
     * @return
     */
    PageInfo<SysResource> getQueryList(Map<String, Object> map);

    /**
     * 查询重复资源数量
     *
     * @param map 查询条件map（按名字和单位查询）
     * @return 重复数量
     */
    Integer getRepeatResourceCount(Map<String, Object> map);

}
