package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjProductTypeMapper;
import com.sofn.dao.tts.TtsScltxxcjProductTypeExpandMapper;
import com.sofn.model.generator.TtsScltxxcjProductType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	产品品种管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjProductTypeProvider.class)
public class TtsScltxxcjProductTypeProviderImpl extends BaseProviderImpl<TtsScltxxcjProductType> implements TtsScltxxcjProductTypeProvider {

    @Autowired
    private TtsScltxxcjProductTypeExpandMapper TtsScltxxcjProductTypeExpandMapper;

    public PageInfo<TtsScltxxcjProductType> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjProductTypeExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjProductTypeExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int updateStatus(TtsScltxxcjProductType ttsScltxxcjProductType) {
        return TtsScltxxcjProductTypeExpandMapper.changeStatus(ttsScltxxcjProductType);
    }

    @Override
    public List<TtsScltxxcjProductType> getTypeList() {
        return TtsScltxxcjProductTypeExpandMapper.getList();
    }

    @Override
    public TtsScltxxcjProductType getProTypeByCode(Map<String,Object> queryParams) {

        return TtsScltxxcjProductTypeExpandMapper.getTypeByCode(queryParams);
    }
}
