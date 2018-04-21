package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjNotificationMapper;
import com.sofn.dao.tts.TtsScltxxcjNotificationExpandMapper;
import com.sofn.model.generator.TtsScltxxcjNotification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	投诉建议信息管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjNotificationProvider.class)
public class TtsScltxxcjNotificationProviderImpl extends BaseProviderImpl<TtsScltxxcjNotification> implements TtsScltxxcjNotificationProvider {

    @Autowired
    private TtsScltxxcjNotificationExpandMapper TtsScltxxcjNotificationExpandMapper;
    @Autowired
    private TtsScltxxcjNotificationMapper  ttsScltxxcjNotificationMapper;

    public PageInfo<TtsScltxxcjNotification> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjNotificationExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjNotificationExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public void updateIsRead(String id){
        try {
            TtsScltxxcjNotification  record = queryById(id);
            record.setIsread("Y");
            ttsScltxxcjNotificationMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    };


}
