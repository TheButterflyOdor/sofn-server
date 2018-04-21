package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjCustomerMapper;
import com.sofn.dao.tts.TtsScltxxcjCustomerExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCustomer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*	客户信息管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjCustomerProvider.class)
public class TtsScltxxcjCustomerProviderImpl extends BaseProviderImpl<TtsScltxxcjCustomer> implements TtsScltxxcjCustomerProvider {

    @Autowired
    private TtsScltxxcjCustomerExpandMapper TtsScltxxcjCustomerExpandMapper;

    public PageInfo<TtsScltxxcjCustomer> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjCustomerExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjCustomerExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public void editDelFlag(String id) {
        TtsScltxxcjCustomerExpandMapper.updateDelFlag(id);
    }



    public List<TtsScltxxcjCustomer> getCustomerList(Map<String, Object> map){
        List<TtsScltxxcjCustomer> list = new ArrayList<TtsScltxxcjCustomer>();
        list = TtsScltxxcjCustomerExpandMapper.getCusromerList(map);
        return list;
    }

    @Override
    public List<TtsScltxxcjCustomer> getCusromerListNotCustomerEntityID(Map<String, Object> map) {
        List<TtsScltxxcjCustomer> list = new ArrayList<TtsScltxxcjCustomer>();
        list = TtsScltxxcjCustomerExpandMapper.getCusromerListNotCustomerEntityID(map);
        return list;
    }

    @Override
    public TtsScltxxcjCustomer checkCustomer(Map<String, Object> map) {

        return TtsScltxxcjCustomerExpandMapper.checkCustomer(map);
    }
}
