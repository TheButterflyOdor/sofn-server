package com.sofn.provider.qry;

import com.sofn.model.qry.SysTemplate;

import java.util.List;
import java.util.Map;

/**
 *框架测试
 * Liw
 */
public interface TestExpandProvider {
    List<SysTemplate> getPageInfo(Map<String,Object> map);
    long getCount(Map<String,Object> map);
}
