package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjSlaSrecord;
import com.sofn.model.generator.TtsScltxxcjXcdjjl;
import com.sofn.provider.tts.TtsScltxxcjSlaSrecordProvider;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 屠宰产品销售记录 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjSlaSrecordService extends BaseService<TtsScltxxcjSlaSrecordProvider, TtsScltxxcjSlaSrecord> {

    @DubboReference
    public void setTtsScltxxcjSlaSrecordProvider(TtsScltxxcjSlaSrecordProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjSlaSrecord bean, int pageNum, int pageSize, String productXspc ) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("productXspc", productXspc);
        return provider.getPageInfo(queryParams);
    }


    public int insertByTemp(TtsScltxxcjSlaSale ttsScltxxcjXsdj) {
        return provider.insertByTemp(ttsScltxxcjXsdj);
    }
    /**
     * 修改销售明细的销售确认状态为已确认
     * @param setId
     */
    public void updateXsmxStatus(Set<String> setId){
        String[] ids = new String[setId.size()];
        ids = setId.toArray(ids);
        Map<String,Object> map = new HashMap<String,Object>();
        //销售登记id
        map.put("ids",ids);
        provider.updateXsmxStatus(map);
    }
    /**
     * 修改销售明细的销售确认状态为已确认  传入销售登记id
     * @param xsdjId
     */
    public void updateXsmxStatus(String xsdjId){
        String[] ids = new String[]{xsdjId};
        Map<String,Object> map = new HashMap<String,Object>();
        //销售登记id
        map.put("ids",ids);
        provider.updateXsmxStatus(map);
    }
    /**
     * 通过条件查询全部的销售明细数据
     * @param map
     * @return
     */
    public List<TtsScltxxcjSlaSrecord> queryBySaleId(Map<String, Object> map){
        List<TtsScltxxcjSlaSrecord> list = new ArrayList<TtsScltxxcjSlaSrecord>();
        list = provider.queryBySaleId(map);
        return list;
    }

    public void deleteRecordFlag(String id) {
        provider.deleteRecordFlag(id);
    }
}