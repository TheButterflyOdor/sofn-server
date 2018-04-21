package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjSlaSrecordMapper;
import com.sofn.dao.tts.TtsScltxxcjSlaSrecordExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjSlaSrecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	屠宰产品销售记录 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjSlaSrecordProvider.class)
public class TtsScltxxcjSlaSrecordProviderImpl extends BaseProviderImpl<TtsScltxxcjSlaSrecord> implements TtsScltxxcjSlaSrecordProvider {

    @Autowired
    private TtsScltxxcjSlaSrecordExpandMapper TtsScltxxcjSlaSrecordExpandMapper;

    public PageInfo<TtsScltxxcjSlaSrecord> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjSlaSrecordExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjSlaSrecordExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int insertByTemp(TtsScltxxcjSlaSale ttsScltxxcjXsdj) {
        return TtsScltxxcjSlaSrecordExpandMapper.insertByTemp(ttsScltxxcjXsdj);
    }

    @Override
    public void updateXsmxStatus(Map<String, Object> map) {
        TtsScltxxcjSlaSrecordExpandMapper.updateXsmxStatus(map);
    }

    @Override
    public List<TtsScltxxcjSlaSrecord> queryBySaleId(Map<String, Object> map) {
        return TtsScltxxcjSlaSrecordExpandMapper.queryByXsdjId(map);
    }

    @Override
    public void deleteRecordFlag(String id) {
        TtsScltxxcjSlaSrecordExpandMapper.deleteRecordFlag(id);
    }

}
