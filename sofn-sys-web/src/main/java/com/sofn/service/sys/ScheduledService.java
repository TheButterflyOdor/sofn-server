package com.sofn.service.sys;

import com.sofn.core.base.RedisService;
import com.sofn.core.constant.OperateLog;
import com.sofn.model.generator.SysOperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuye on 2016/12/16.
 */
@Service
public class ScheduledService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SysOperateLogService sysOperateLogService;

    @Scheduled(cron = "0 0 1 * * *")
    public void scheduledRedis(){
        List<OperateLog> list = redisService.queryOperateLog();
        OperateLog operateLog = new OperateLog();
        for (int i = 0; i<=list.size();i++){
            SysOperateLog sysOperateLog = new SysOperateLog();
            operateLog = list.get(i);
            sysOperateLog.setId(operateLog.getId());
            sysOperateLog.setDelFlag(operateLog.getDelFlag());
            sysOperateLog.setFailInfo(operateLog.getFailInfo());
            sysOperateLog.setOperateIp(operateLog.getOperateIp());
            sysOperateLog.setOperateTime(operateLog.getOperateTime());
            sysOperateLog.setOperateUserid(operateLog.getOperateUserid());
            sysOperateLog.setOperateUsername(operateLog.getOperateUsername());
            sysOperateLog.setStatus(operateLog.getStatus());
            sysOperateLog.setReservedField1(operateLog.getReservedField1());
            sysOperateLog.setReservedField2(operateLog.getReservedField2());
            sysOperateLog.setUserToken(operateLog.getUserToken());
            sysOperateLog.setVisitParam(operateLog.getVisitParam());
            sysOperateLog.setVisitUrl(operateLog.getVisitUrl());
            sysOperateLog.setCreateBy(operateLog.getCreateBy());
            sysOperateLog.setCreateTime(operateLog.getCreateTime());
            sysOperateLog.setUpdateBy(operateLog.getUpdateBy());
            sysOperateLog.setUpdateTime(operateLog.getUpdateTime());
            sysOperateLogService.addSysOperatelog(sysOperateLog);
            sysOperateLog.setRemark(operateLog.getRemark());
            sysOperateLogService.addSysOperatelog(sysOperateLog);
        }
    }
}
