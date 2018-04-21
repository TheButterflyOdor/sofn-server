package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjCgglExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCggl;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjCgglAndUserInfo;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	采购管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjCgglProvider.class)
public class TtsScltxxcjCgglProviderImpl extends BaseProviderImpl<TtsScltxxcjCggl> implements TtsScltxxcjCgglProvider {

    @Autowired
    private TtsScltxxcjCgglExpandMapper ttsScltxxcjCgglExpandMapper;

    @Override
    public PageInfo<TtsScltxxcjCggl> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = ttsScltxxcjCgglExpandMapper.getPageInfo(map);
        long count = ttsScltxxcjCgglExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<TtsScltxxcjCgglAndCustomer> getCgglForZsm(Map<String, Object> map) {

        return ttsScltxxcjCgglExpandMapper.getCgglForZsm(map);
    }

    @Override
    public long zsmIsCgOrXs(Map<String, Object> map) {
        return ttsScltxxcjCgglExpandMapper.zsmIsCgOrXs(map);
    }

    @Override
        public long isExists(String code) {
        return ttsScltxxcjCgglExpandMapper.isExists(code);
    }

    @Override
    public TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCg(String code) {
        return ttsScltxxcjCgglExpandMapper.getInfoByTraceCodeFromCg(code);
    }

    @Override
    public TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCgforUsual(String code) {
        return ttsScltxxcjCgglExpandMapper.getInfoByTraceCodeFromCgforUsual(code);
    }

    /**
     * 一键收货
     * @param map
     */
    @Override
    public List<TtsScltxxcjXsdjAndCustomer> allPurchase(Map<String, Object> map) {
        //1,根据主体身份码查出所有待收货信息
        List<TtsScltxxcjXsdjAndCustomer> list = ttsScltxxcjCgglExpandMapper.getAllPurchase(map);
        return list;
    }

}
