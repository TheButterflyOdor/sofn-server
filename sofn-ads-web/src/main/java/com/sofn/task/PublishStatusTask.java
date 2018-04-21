package com.sofn.task;

import com.sofn.model.generator.AdsFile;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.service.ads.AdsFileService;
import com.sofn.service.ads.AdsMonitorTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */
@Component
public class PublishStatusTask {
    @Autowired
    private AdsMonitorTaskService adsMonitorTaskService;
    @Autowired
    private AdsFileService adsFileService;

    /**
     * 本方法对过期任务状态进行标识
     */
    public void taskCycle() {
        //查询发布状态是已发布或执行中的任务，并且该任务的结束时间超过系统当前时间
        List<AdsMonitorTask> adsMonitorTaskList = adsMonitorTaskService.findExpiredTask();
        for(AdsMonitorTask amt : adsMonitorTaskList){
            AdsFile adsFile = new AdsFile();
            adsFile.setMonitorTaskId(amt.getId());
            adsFile.setSource("承担单位");
            List<AdsFile> list = adsFileService.getAdsFileByCondition(adsFile);
            if(null != list){
                if(list.size() != 0){
                    //正常结束任务，publishstatus = 3
                    amt.setPublishStatus(new BigDecimal(3));
                    adsMonitorTaskService.updatePublishStatus(amt);
                    //同步监管任务状态
                    if(amt.getMonitorClass().equals("复检任务") || amt.getMonitorClass().equals("监督抽查"))
                        adsMonitorTaskService.updateAsmsTaskStateByIdAndType(amt.getId(),amt.getMonitorClass(),"5");
                    else
                        adsMonitorTaskService.updateAsmsTaskStateByIdAndType(amt.getSupId(),amt.getMonitorClass(),"5");
                }else{
                    //未提交报告，未完成，异常结束。publishstatus = 4
                    amt.setPublishStatus(new BigDecimal(4));
                    adsMonitorTaskService.updatePublishStatus(amt);
                    //同步监管任务状态
                    if(amt.getMonitorClass().equals("复检任务") || amt.getMonitorClass().equals("监督抽查"))
                        adsMonitorTaskService.updateAsmsTaskStateByIdAndType(amt.getId(),amt.getMonitorClass(),"4");
                    else
                        adsMonitorTaskService.updateAsmsTaskStateByIdAndType(amt.getSupId(),amt.getMonitorClass(),"4");
                }
            }
        }
    }
}
