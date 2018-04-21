package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjNotification;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.provider.tts.TtsScltxxcjNotificationProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 投诉建议信息管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjNotificationService extends BaseService<TtsScltxxcjNotificationProvider, TtsScltxxcjNotification> {

    @DubboReference
    public void setTtsScltxxcjNotificationProvider(TtsScltxxcjNotificationProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjNotification bean, int pageNum, int pageSize,String createTimeStart,String createTimeEnd,TtsScltxxcjRegiter user) {
        Map<String, Object> queryParams = new HashMap<String,Object>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("notification", bean);
        queryParams.put("user",user);
        queryParams.put("createTimeStart",createTimeStart);
        queryParams.put("createTimeEnd",createTimeEnd);
        return provider.getPageInfo(queryParams);
    }


}