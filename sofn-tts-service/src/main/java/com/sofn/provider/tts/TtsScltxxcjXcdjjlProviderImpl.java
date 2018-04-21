package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjXcdjjlMapper;
import com.sofn.dao.tts.TtsScltxxcjXcdjjlExpandMapper;
import com.sofn.model.generator.TtsScltxxcjXcdjjl;
import com.sofn.model.generator.TtsScltxxcjXsdj;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	销售记录 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjXcdjjlProvider.class)
public class TtsScltxxcjXcdjjlProviderImpl extends BaseProviderImpl<TtsScltxxcjXcdjjl> implements TtsScltxxcjXcdjjlProvider {

    @Autowired
    private TtsScltxxcjXcdjjlExpandMapper TtsScltxxcjXcdjjlExpandMapper;

    public PageInfo<TtsScltxxcjXcdjjl> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjXcdjjlExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int insertByTemp(TtsScltxxcjXsdj ttsScltxxcjXsdj) {

        return TtsScltxxcjXcdjjlExpandMapper.insertByTemp(ttsScltxxcjXsdj);
    }

    @Override
    public int updateXsmxStatus(Map<String, Object> map) {
        return TtsScltxxcjXcdjjlExpandMapper.updateXsmxStatus(map);
    }

    @Override
    public int deleteXsmxFlag(String id) {
        return TtsScltxxcjXcdjjlExpandMapper.deleteXsmxFlag(id);
    }

    @Override
    public List<TtsScltxxcjXcdjjl> queryByXsdjId(Map<String, Object> map) {

        return TtsScltxxcjXcdjjlExpandMapper.queryByXsdjId(map);
    }

    @Override
    public PageInfo<Map<String, Object>> pageForZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.pageForZsm(map);
        long count = TtsScltxxcjXcdjjlExpandMapper.pageForCountZsm(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    /**
     * 追溯查询，销售是本级
     * @param map
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getXsbaseZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getXsbaseZsm(map);
        long count = TtsScltxxcjXcdjjlExpandMapper.getXsbaseZsmCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getInfoforpc(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getInfoforpc(map);
        long count = TtsScltxxcjXcdjjlExpandMapper.getCountforpc(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    public PageInfo<Map<String, Object>> getPcToUpInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getPcToUpInfo(map);
        long count = TtsScltxxcjXcdjjlExpandMapper.getPcToUpCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    /**
     * 合成批次流向
     * @param pc
     * @return
     */
    @Override
    public Map<String, Object> getHclx(Map<String,Object> pc) {

        return TtsScltxxcjXcdjjlExpandMapper.getHclx(pc);
    }


}
