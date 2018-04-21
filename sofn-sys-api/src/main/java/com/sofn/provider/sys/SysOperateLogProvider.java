package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysOperateLog;

import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/16.
 */
public interface SysOperateLogProvider extends BaseProvider<SysOperateLog> {

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<SysOperateLog> getPageInfo(Map<String,Object> map);

    long getCount(Map<String,Object> map);


    int addSysOperateLog(SysOperateLog sysOperateLog);

    List<SysOperateLog> findByIp(String operateIp);

}
