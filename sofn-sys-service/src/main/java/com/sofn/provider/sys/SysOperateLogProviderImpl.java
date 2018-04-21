package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysOperateLogMapper;
import com.sofn.dao.sys.SysOperateLogExpandMapper;
import com.sofn.model.generator.SysOperateLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/16.
 */
@DubboService(interfaceClass = SysOperateLogProvider.class)
public class SysOperateLogProviderImpl extends BaseProviderImpl<SysOperateLog> implements SysOperateLogProvider {

    @Autowired
    private SysOperateLogMapper sysOperateLogMapper;

    @Autowired
    private SysOperateLogExpandMapper sysOperateLogExpandMapper;

    @Override
    public List<SysOperateLog> getPageInfo(Map<String, Object> map) {
        return sysOperateLogExpandMapper.getPageInfo(map);
    }

    @Override
    public long getCount(Map<String, Object> map) {
        return sysOperateLogExpandMapper.getCount(map);
    }

    @Override
    public int addSysOperateLog(SysOperateLog sysOperateLog) {
        return sysOperateLogMapper.insert(sysOperateLog);
    }

    @Override
    public List<SysOperateLog> findByIp(String operateIp) {
        return sysOperateLogExpandMapper.findByIp(operateIp);
    }


}
