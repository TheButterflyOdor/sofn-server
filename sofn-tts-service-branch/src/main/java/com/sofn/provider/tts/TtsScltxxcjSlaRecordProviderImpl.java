package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjSlaRecordExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
@DubboService(interfaceClass = TtsScltxxcjSlaRecordProvider.class)
public class TtsScltxxcjSlaRecordProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaRecord> implements TtsScltxxcjSlaRecordProvider {

    @Autowired
    private TtsScltxxcjSlaRecordExpandMapper ttsScltxxcjSlaRecordExpandMapper;

    @Override
    public PageInfo findPageInfoById(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaRecordExpandMapper.findPageInfoById(params);
        long count = ttsScltxxcjSlaRecordExpandMapper.findPageInfoByIdCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<TtsScltxxcjSlaRecord> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjSlaRecordExpandMapper.getPageInfo(map);
        long count = ttsScltxxcjSlaRecordExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}
