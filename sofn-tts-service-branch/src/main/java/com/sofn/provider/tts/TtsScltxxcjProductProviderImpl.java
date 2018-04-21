package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjProductExpandMapper;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.model.generator.TtsScltxxcjScgl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	产品管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjProductProvider.class)
public class TtsScltxxcjProductProviderImpl extends BaseProviderImpl<TtsScltxxcjProduct> implements TtsScltxxcjProductProvider {


    @Autowired
    private TtsScltxxcjProductExpandMapper TtsScltxxcjProductExpandMapper;

    public PageInfo<TtsScltxxcjProduct> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjProductExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjProductExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int changeStatus(TtsScltxxcjProduct ttsScltxxcjProduct) {
        return TtsScltxxcjProductExpandMapper.changeStatus(ttsScltxxcjProduct);
    }

    @Override
    public PageInfo<TtsScltxxcjProduct> findProList(Map<String, Object> queryParams) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjProductExpandMapper.findProList(queryParams);
        long count = TtsScltxxcjProductExpandMapper.getProCount(queryParams);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<TtsScltxxcjProduct> queryProByEntityCode(Map<String, Object> queryParams) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjProductExpandMapper.findProByEntityCode(queryParams);
        long count = TtsScltxxcjProductExpandMapper.queryProCountByEntityCode(queryParams);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<TtsScltxxcjScgl> getProType(String entityId) {
        return TtsScltxxcjProductExpandMapper.getProType(entityId);
    }

    @Override
    public PageInfo<TtsScltxxcjProduct> getProductByTerm(Map<String, Object> queryParams) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjProductExpandMapper.getProductByTerm(queryParams);
        long count = TtsScltxxcjProductExpandMapper.getProductByTermCount(queryParams);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<TtsScltxxcjScgl> getProName(Map<String, Object> queryParams) {
        return TtsScltxxcjProductExpandMapper.getProName(queryParams);
    }

    @Override
    public boolean isExistedPro(TtsScltxxcjProduct ttsScltxxcjProduct) {
        long countPro = TtsScltxxcjProductExpandMapper.isExistedPro(ttsScltxxcjProduct);
        return countPro > 0 ? true : false;
    }

    @Override
    public PageInfo<TtsScltxxcjProduct> getProductforEntityInfo( Map<String, Object> queryParams) {

        PageInfo pageInfo = new PageInfo();

        List<Map<String,Object>> list = TtsScltxxcjProductExpandMapper.getProductforEntity(queryParams);
        long count = TtsScltxxcjProductExpandMapper.getProductforEntityCount(queryParams);
        pageInfo.setList(list);
        pageInfo.setTotal(count);

        return pageInfo;
    }

}
