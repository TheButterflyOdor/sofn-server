package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjCppchcExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCppchc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	产品批次合成 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjCppchcProvider.class)
public class TtsScltxxcjCppchcProviderImpl extends BaseProviderImpl<TtsScltxxcjCppchc> implements TtsScltxxcjCppchcProvider {

    @Autowired
    private TtsScltxxcjCppchcExpandMapper TtsScltxxcjCppchcExpandMapper;

    public PageInfo<TtsScltxxcjCppchc> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjCppchcExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjCppchcExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    public int insertBySelect(Map<String, Object> map){

        return TtsScltxxcjCppchcExpandMapper.insertBySelect(map);
    }

    @Override
    public List<Map<String,Object>> getInfoForNewPc(Map<String, Object> map) {
        List<Map<String,Object>> list = TtsScltxxcjCppchcExpandMapper.getInfoForNewPc(map);
        return list;
    }
}
