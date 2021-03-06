package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjScglExpandMapper;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjScglAndUserInfo;
import jxl.write.Boolean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	生产管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjScglProvider.class)
public class TtsScltxxcjScglProviderImpl extends BaseProviderImpl<TtsScltxxcjScgl> implements TtsScltxxcjScglProvider {

    @Autowired
    private TtsScltxxcjScglExpandMapper TtsScltxxcjScglExpandMapper;

    public PageInfo<TtsScltxxcjScgl> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjScglExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjScglExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<TtsScltxxcjScgl> getSphcByIds(Map<String, Object> map) {
        List<TtsScltxxcjScgl> list = TtsScltxxcjScglExpandMapper.getSphcByIds(map);
        return list;
    }

    @Override
    public List<Map<String, Object>> getCheckProduct(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list = TtsScltxxcjScglExpandMapper.getCheckProduct(map);
        return list;
    }

    public PageInfo<TtsScltxxcjScgl> getslaughterProduct(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> slaughterList = TtsScltxxcjScglExpandMapper.getslaughterPageInfo(map);
        long slaughterCount = TtsScltxxcjScglExpandMapper.getslaughterCount(map);
        pageInfo.setList(slaughterList);
        pageInfo.setTotal(slaughterCount);
        return pageInfo;
    }

    @Override
    public void updateProductAmount(String id, String amount) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("amount", amount);
        TtsScltxxcjScglExpandMapper.updateProductAmount(params);
    }


    /**
     * 通过产品批次修改产品库存情况
     * @param map
     */
    @Override
    public void updateProductStoreCount(Map<String, Object> map) {

        TtsScltxxcjScglExpandMapper.updateProductStoreCount(map);
    }

    @Override
    public void updateProductStoreFreezeCount(Map<String, Object> map) {
        TtsScltxxcjScglExpandMapper.updateProductStoreFreezeCount(map);
    }

    /**
     * 采购管理通过，销售登记ID，修改生产管理库存
     * @param id
     */
    @Override
    public long updateStoreCountForCgqr(String id) {
       return TtsScltxxcjScglExpandMapper.updateStoreCountForCgqr(id);
    }

    @Override
    public List<TtsScltxxcjScgl> getTypeList(String entityIdcode) {
        return TtsScltxxcjScglExpandMapper.getTypeList(entityIdcode);
    }


    /**
     * 通过批次码查询生产登记信息
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getBaseForPc(Map<String, Object> map) {
        return TtsScltxxcjScglExpandMapper.getBaseForPc(map);
    }

    @Override
    public TtsScltxxcjScglAndUserInfo getInfoByPcCodeForUsual(String code) {
        return TtsScltxxcjScglExpandMapper.getInfoByPcCodeForUsual(code);
    }

    @Override
    public TtsScltxxcjScglAndUserInfo getInfoByPcCodeForGov(String code) {
        return TtsScltxxcjScglExpandMapper.getInfoByPcCodeForGov(code);
    }

    @Override
    public TtsScltxxcjScgl getproductInfo(String id) {
        return TtsScltxxcjScglExpandMapper.getproductInfo(id);
    }

    @Override
    public void updateState(String id) {
        TtsScltxxcjScglExpandMapper.updateState(id);
    }

//    @Override
//    public TtsScltxxcjScglAndUserInfo getInfoByRsCodeForUsual(String code) {
//        return TtsScltxxcjScglExpandMapper.getInfoByRsCodeForUsual(code);
//    }
//
//    @Override
//    public TtsScltxxcjScglAndUserInfo getInfoByRsCodeForGov(String code) {
//        return TtsScltxxcjScglExpandMapper.getInfoByRsCodeForGov(code);
//    }
}
