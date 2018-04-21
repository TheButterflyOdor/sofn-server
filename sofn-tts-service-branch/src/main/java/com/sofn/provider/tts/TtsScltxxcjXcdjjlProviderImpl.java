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
    public List<Map<String,Object>> pageForZsm(Map<String, Object> map) {
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.pageForZsm(map);
        return list;
    }


    /**
     * 追溯查询，销售是本级
     * @param map
     * @return
     */
    @Override
    public List<Map<String,Object>> getXsbaseZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getXsbaseZsm(map);
        return list;
    }

    @Override
    public List<Map<String,Object>> getInfoforpc(Map<String, Object> map) {
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getInfoBypc(map);
        return list;
    }


    @Override
    public List<Map<String,Object>> getPcToUpInfo(Map<String, Object> map) {
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getPcToUpInfo(map);
        return list;
    }


    /**
     * 合成批次流向
     * @param pc
     * @return
     */
    @Override
    public List<Map<String,Object>> getHclx(Map<String,Object> pc) {

        return TtsScltxxcjXcdjjlExpandMapper.getHclx(pc);
    }

    @Override
    public PageInfo<TtsScltxxcjXcdjjl> getPageInfoPrint(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getPageInfoPrint(map);
        long count = TtsScltxxcjXcdjjlExpandMapper.getPrintCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getInfobypc(Map<String, Object> queryParams) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getInfoforpc(queryParams);
        long count = TtsScltxxcjXcdjjlExpandMapper.getCountforpc(queryParams);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> getProofByRraceProof(Map<String, Object> queryParams) {
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getProofByRraceProof(queryParams);
        return list;
    }

    @Override
    public List<Map<String, Object>> getPrintInfoForPc(Map<String, Object> queryParams) {
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getPrintInfoForPc(queryParams);
        return list;
    }

    @Override
    public List<Map<String, Object>> getPrintForProof(Map<String, Object> queryParams) {
        List<Map<String,Object>> list = TtsScltxxcjXcdjjlExpandMapper.getPrintForProof(queryParams);
        return list;
    }

    @Override
    public TtsScltxxcjXcdjjl getRecordByXspc(String productXspc) {
        return TtsScltxxcjXcdjjlExpandMapper.getRecordByXspc(productXspc);
    }


}
