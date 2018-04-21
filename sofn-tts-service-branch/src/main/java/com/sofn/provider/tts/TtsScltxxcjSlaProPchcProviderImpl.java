package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjSlaProPchcMapper;
import com.sofn.dao.tts.TtsScltxxcjSlaProPchcExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaProPchc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	屠宰后产品批次合成 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjSlaProPchcProvider.class)
public class TtsScltxxcjSlaProPchcProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaProPchc> implements TtsScltxxcjSlaProPchcProvider {

    @Autowired
    private TtsScltxxcjSlaProPchcExpandMapper TtsScltxxcjSlaProPchcExpandMapper;

    public PageInfo<TtsScltxxcjSlaProPchc> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjSlaProPchcExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjSlaProPchcExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    public int insertBySelect(Map<String, Object> map){

        return TtsScltxxcjSlaProPchcExpandMapper.insertBySelect(map);
    }
}
