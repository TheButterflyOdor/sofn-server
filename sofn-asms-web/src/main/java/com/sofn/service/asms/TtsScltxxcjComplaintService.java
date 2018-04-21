package com.sofn.service.asms;


import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.TtsScltxxcjComplain;
import com.sofn.model.generator.TtsScltxxcjNotification;
import com.sofn.provider.tts.TtsScltxxcjComplainProvider;
import com.sofn.provider.tts.TtsScltxxcjNotificationProvider;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年10月8日 下午 4:50
 */

@Service
public class TtsScltxxcjComplaintService extends BaseService<TtsScltxxcjComplainProvider,TtsScltxxcjComplain> {
    @DubboReference
    public void setProvider(TtsScltxxcjComplainProvider provider){
        this.provider = provider;
    }

    private TtsScltxxcjNotificationProvider ttsScltxxcjNotificationProvider;
    @DubboReference
    public void setProvider(TtsScltxxcjNotificationProvider ttsScltxxcjNotificationProvider) {
        this.ttsScltxxcjNotificationProvider = ttsScltxxcjNotificationProvider;
    }

    public PageInfo getTtsScltxxcjComplaintList(TtsScltxxcjComplain ttsScltxxcjComplain, String areaId,
                                              String complaintTitle,String status,String type,
                                          int pageNum, int pageSize,String queryCon){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("areaId",areaId);
        queryParams.put("complaintTitle",complaintTitle);
        queryParams.put("type",type);
        queryParams.put("status",status);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("queryCon",queryCon);
        return provider.getInterPageInfo(queryParams);
    }

    /**
     * 根据ID查看当前投诉处理
     * @param id
     * @return
     */
    public TtsScltxxcjComplain findTtsScltxxcjComplaint(String id){

        return provider.queryById(id);
    }
    public int updatettsScltxxcjComplaint(String token, TtsScltxxcjComplain ttsScltxxcjComplain){
        int result = 0;
        ttsScltxxcjComplain.setUpdateTime(new Date());
        ttsScltxxcjComplain.setAccTime(new Date());
        //投诉受理成功向投诉企业推送消息
        try{
            TtsScltxxcjNotification ttsScltxxcjNotification = new TtsScltxxcjNotification();
            ttsScltxxcjNotification.setIsread("N");
            ttsScltxxcjNotification.setTitle("投诉受理反馈");
            ttsScltxxcjNotification.setContent(WebUtil.getCurrentUser(token).getOrgName()+"（"+WebUtil.getCurrentUser(token).getUserName()+"）在"+new SimpleDateFormat("yyyy-MM-dd").format(ttsScltxxcjComplain.getAccTime())+"对投诉了进行受理，受理意见为"+ttsScltxxcjComplain.getAcceptance()+"，请到投诉反馈中查看详情。");
            ttsScltxxcjNotification.setFromId(ttsScltxxcjComplain.getAccUserIdcode());//当前登录账户id
            ttsScltxxcjNotification.setFromName(ttsScltxxcjComplain.getAccEntityIdcode());//当前登录用户名
            ttsScltxxcjNotification.setToId(ttsScltxxcjComplain.getUserIdcode());//接收消息主体userIdcode
            ttsScltxxcjNotification.setToName(ttsScltxxcjComplain.getComplaintCopName());//接收消息主体名称
            ttsScltxxcjNotification.setCreateBy(ttsScltxxcjComplain.getAccEntityIdcode());//登录账户id
            ttsScltxxcjNotification.setCreateTime(new Date());
            ttsScltxxcjNotificationProvider.update(ttsScltxxcjNotification);
        }catch (Exception e){
            result = -1;
            e.printStackTrace();
        }
        provider.update(ttsScltxxcjComplain);
        return result;
    }
}
