package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjXsdjExpandMapper;
import com.sofn.model.generator.TtsScltxxcjXsdj;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndUserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	销售登记 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjXsdjProvider.class)
public class TtsScltxxcjXsdjProviderImpl extends BaseProviderImpl<TtsScltxxcjXsdj> implements TtsScltxxcjXsdjProvider {

    @Autowired
    private TtsScltxxcjXsdjExpandMapper TtsScltxxcjXsdjExpandMapper;

    public PageInfo<TtsScltxxcjXsdj> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXsdjExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjXsdjExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    /**
     * 单独封装的带客户信息的单数据查询返回
     * @param id
     * @return
     */
    @Override
    public TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id) {

        return TtsScltxxcjXsdjExpandMapper.getXsdjAndCustomerById(id);
    }


    /**
     * 采购管理使用的分页
     * @param map
     * @return
     */
    @Override
    public PageInfo<TtsScltxxcjXsdjAndCustomer> queryPageForCg(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<TtsScltxxcjXsdjAndCustomer> list = TtsScltxxcjXsdjExpandMapper.queryPageForCg(map);
        long count = TtsScltxxcjXsdjExpandMapper.queryCountForCg(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    /**
     * 通过追溯码查询 销售登记，产品从哪里来，到哪里去
     * @param map
     * @return
     */
    @Override
    public List<TtsScltxxcjXsdjAndCustomer> getXsdjForZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        return TtsScltxxcjXsdjExpandMapper.getXsdjForZsm(map);
    }


    /**
     * 当销售是本级的时候，通过追溯码查询上游销售记录
     * @param map
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getXsUpZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = TtsScltxxcjXsdjExpandMapper.getXsUpZsm(map);
        long count = TtsScltxxcjXsdjExpandMapper.getXsUpZsmCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForUsual(String code) {
        return TtsScltxxcjXsdjExpandMapper.getInfoByRsCodeForUsual(code);
    }

    @Override
    public TtsScltxxcjXsdjAndUserInfo getInfoByRsCodeForGov(String code) {
        return TtsScltxxcjXsdjExpandMapper.getInfoByRsCodeForGov(code);
    }

    @Override
    public void updateComfigState(String id) {
        TtsScltxxcjXsdjExpandMapper.updateComfigState(id);
    }

    @Override
    public TtsScltxxcjXsdjAndCustomer getCgqrAndCustomerById(Map<String, Object> map) {
        return TtsScltxxcjXsdjExpandMapper.getCgqrAndCustomerById(map);
    }

    @Override
    public long existsTrace(Map<String, Object> map) {
        return TtsScltxxcjXsdjExpandMapper.existsTrace(map);
    }

}
