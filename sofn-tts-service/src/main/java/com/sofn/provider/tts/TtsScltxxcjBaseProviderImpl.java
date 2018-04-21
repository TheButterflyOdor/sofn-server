package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjBaseMapper;
import com.sofn.dao.tts.TtsScltxxcjBaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjBase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	基地信息管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjBaseProvider.class)
public class TtsScltxxcjBaseProviderImpl extends BaseProviderImpl<TtsScltxxcjBase> implements TtsScltxxcjBaseProvider {

    @Autowired
    private TtsScltxxcjBaseExpandMapper TtsScltxxcjBaseExpandMapper;

    public PageInfo<TtsScltxxcjBase> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjBaseExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjBaseExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int updateStatus(TtsScltxxcjBase tscltxxcjbase) {
        return TtsScltxxcjBaseExpandMapper.changeStatus(tscltxxcjbase);
    }

    @Override
    public void deleteById(Map<String, Object> queryParams) {
        TtsScltxxcjBaseExpandMapper.removeById(queryParams);
    }

    @Override
    public PageInfo<TtsScltxxcjBase> queryBaseByEntity(Map<String, Object> queryParams) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjBaseExpandMapper.findBaseByEntityCode(queryParams);
        long count = TtsScltxxcjBaseExpandMapper.findBaseCountByEntityCode(queryParams);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public void addBase(TtsScltxxcjBase ttsScltxxcjBase) {
        TtsScltxxcjBaseExpandMapper.addBase(ttsScltxxcjBase);
        //注释
    }

    @Override
    public String findBaseMainProcById(Map<String, Object> params) {
        return TtsScltxxcjBaseExpandMapper.findBaseMainProcById(params);
    }

    @Override
    public void updateMainProcs(Map<String, Object> params) {
        TtsScltxxcjBaseExpandMapper.updateMainProcs(params);
    }

}
