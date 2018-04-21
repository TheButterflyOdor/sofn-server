package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjCppchc;
import com.sofn.provider.tts.TtsScltxxcjCppchcProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品批次合成 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjCppchcService extends BaseService<TtsScltxxcjCppchcProvider, TtsScltxxcjCppchc> {

    @DubboReference
    public void setTtsScltxxcjCppchcProvider(TtsScltxxcjCppchcProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjCppchc bean, int pageNum, int pageSize,String hcid) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("hcid", hcid);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }


    /**
     * 批量加入合成记录
     * 特别说明：在批次插入数据的时候，因为合成后数据来源与合成前数据，并且合成前数据
     * 可能具有销售行为，则进入合成明细表时，明细表中对应的收获数量为库存数量，和原始记录不一致
     * @param map
     * @return
     */
    public int insertBySelect(Map<String, Object> map){

        return provider.insertBySelect(map);
    }


    /**
     * 通过pc号，查询产品数据来源
     * @return
     */
    public List<Map<String,Object>> getInfoForNewPc(Map<String,Object> map){

        return provider.getInfoForNewPc(map);
    }
}