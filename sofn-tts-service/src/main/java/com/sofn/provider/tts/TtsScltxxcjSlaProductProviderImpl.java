package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjSlaProductMapper;
import com.sofn.dao.tts.TtsScltxxcjSlaProductExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	屠宰后产品 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjSlaProductProvider.class)
public class TtsScltxxcjSlaProductProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaProduct> implements TtsScltxxcjSlaProductProvider {

    @Autowired
    private TtsScltxxcjSlaProductExpandMapper TtsScltxxcjSlaProductExpandMapper;

    public PageInfo<TtsScltxxcjSlaProduct> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjSlaProductExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjSlaProductExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<TtsScltxxcjSlaProduct> getProductList() {
        return TtsScltxxcjSlaProductExpandMapper.getProductList();
    }

}
