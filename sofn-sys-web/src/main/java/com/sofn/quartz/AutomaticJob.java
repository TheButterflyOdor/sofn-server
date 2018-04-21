package com.sofn.quartz;

import com.sofn.core.base.BaseController;
import com.sofn.core.constant.OperateLog;
import com.sofn.core.util.RedisUtil;
import com.sofn.model.generator.SysOperateLog;
import com.sofn.service.sys.SysOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/** 定义作业类
 * Created by wuye on 2017/1/11 0011.
 */
public class AutomaticJob extends BaseController{

    @Autowired
    private SysOperateLogService sysOperateLogService;

    /**
     * 业务逻辑处理
     */
    public void dojob() {
        //开启线程执行持久化任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 执行业务逻辑
                logger.info("启动操作日志持久化定时任务");
                List<OperateLog> list = RedisUtil.keys();
                for (int i =0 ; i<list.size();i++){
                    OperateLog operateLog = list.get(i);
                    //数据转换
                    SysOperateLog sysOperateLog = new SysOperateLog();
                    sysOperateLog.setId(operateLog.getId());
                    sysOperateLog.setOperateUserid(operateLog.getOperateUserid());
                    sysOperateLog.setOperateUsername(operateLog.getOperateUsername());
                    sysOperateLog.setOperateTime(operateLog.getOperateTime());
                    sysOperateLog.setOperateIp(operateLog.getOperateIp());
                    sysOperateLog.setUserToken(operateLog.getUserToken());
                    sysOperateLog.setVisitUrl(operateLog.getVisitUrl());
                    sysOperateLog.setVisitParam(operateLog.getVisitParam());
                    sysOperateLog.setStatus(operateLog.getStatus());
                    sysOperateLog.setFailInfo(operateLog.getFailInfo());
                    sysOperateLog.setDelFlag(operateLog.getDelFlag());
                    sysOperateLog.setOperateType(operateLog.getOperateType());
                    sysOperateLog.setOperateDescribe(operateLog.getOperateDescribe());
                    sysOperateLog.setCreateBy(operateLog.getCreateBy());
                    sysOperateLog.setCreateTime(operateLog.getCreateTime());
                    sysOperateLog.setUpdateBy(operateLog.getUpdateBy());
                    sysOperateLog.setUpdateTime(operateLog.getUpdateTime());
                    //存入数据库
                    if(sysOperateLogService.addSysOperatelog(sysOperateLog)>0){
                        logger.info("持久化操作日志成功");
                    }else{
                        logger.info("持久化操作日志失败");
                    }
                    System.out.println("第"+i+"条"+operateLog.getOperateIp());
                }
            }
        }).start();
    }
}
