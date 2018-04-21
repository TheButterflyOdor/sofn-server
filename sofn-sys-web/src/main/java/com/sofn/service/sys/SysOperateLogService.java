package com.sofn.service.sys;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.provider.sys.SysOperateLogProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/16.
 */
@Service
public class SysOperateLogService extends BaseService<SysOperateLogProvider,SysOperateLog>{

    @DubboReference
    public void setSysOperateLogProvider(SysOperateLogProvider provider){this.provider = provider ;}

    /**
     * 分页查询数据
     * @param map
     * @return
     */
    public List<SysOperateLog> getPageInfo(Map<String,Object> map){
        return provider.getPageInfo(map);
    }

    public long getCount(Map<String,Object> map){
        return provider.getCount(map);
    }

    /**
     * 添加数据到数据库
     * @param sysOperateLog
     * @return
     */
    public int addSysOperatelog(SysOperateLog sysOperateLog){
        return provider.addSysOperateLog(sysOperateLog);
    }

    /**
     * 根据IP查询
     * @param operateIp
     * @return
     */
    public List<SysOperateLog> findByIp(String operateIp){
        return provider.findByIp(operateIp);
    }
}
