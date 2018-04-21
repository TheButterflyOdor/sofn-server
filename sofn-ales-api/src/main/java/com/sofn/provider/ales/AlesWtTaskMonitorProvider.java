package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesWtTaskMonitor;

import java.util.List;
import java.util.Map;

public interface AlesWtTaskMonitorProvider extends BaseProvider<AlesWtTaskMonitor> {

    /**
     * 分页
     */
    PageInfo<AlesWtTaskMonitor> pages(Map<String, Object> params);

    /**
     * 列表
     */
    List<AlesWtTaskMonitor> getListByTaskId(AlesWtTaskMonitor alesWtTaskMonitor);
}
