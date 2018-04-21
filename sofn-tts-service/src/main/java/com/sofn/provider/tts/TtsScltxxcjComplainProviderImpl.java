package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.TtsScltxxcjComplainMapper;
import com.sofn.dao.tts.TtsScltxxcjComplainExpandMapper;
import com.sofn.model.generator.TtsScltxxcjComplain;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*	投诉举报 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjComplainProvider.class)
public class TtsScltxxcjComplainProviderImpl extends BaseProviderImpl<TtsScltxxcjComplain> implements TtsScltxxcjComplainProvider {

    @Autowired
    private TtsScltxxcjComplainExpandMapper TtsScltxxcjComplainExpandMapper;

    public PageInfo<TtsScltxxcjComplain> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        long count = 0;
//        if(null != map.get("userIdcode") && StringUtils.isNotBlank(map.get("userIdcode").toString())){
            list = TtsScltxxcjComplainExpandMapper.getPageInfo(map);
            count = TtsScltxxcjComplainExpandMapper.getCount(map);
//        }
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<TtsScltxxcjComplain> getInterPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjComplainExpandMapper.getInterPageInfo(map);
        long count = TtsScltxxcjComplainExpandMapper.getInterCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<TtsScltxxcjComplain> getEntityPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        long count = 0;
//        if(null != map.get("userIdcode") && StringUtils.isNotBlank(map.get("userIdcode").toString())){
        list = TtsScltxxcjComplainExpandMapper.getEntityPageInfo(map);
        count = TtsScltxxcjComplainExpandMapper.getEntityCount(map);
//        }
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
