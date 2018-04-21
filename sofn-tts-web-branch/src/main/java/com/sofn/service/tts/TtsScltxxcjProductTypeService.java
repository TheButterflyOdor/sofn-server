package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjProductType;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.provider.tts.TtsScltxxcjProductTypeProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品品种管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjProductTypeService extends BaseService<TtsScltxxcjProductTypeProvider, TtsScltxxcjProductType> {

    @DubboReference
    public void setTtsScltxxcjProductTypeProvider(TtsScltxxcjProductTypeProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjProductType bean, int pageNum, int pageSize,TtsScltxxcjRegiter user) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("user",user);
        return provider.getPageInfo(queryParams);
    }


    public int changeStatus(TtsScltxxcjProductType ttsScltxxcjProductType) {
        return provider.updateStatus(ttsScltxxcjProductType);
    }

    public List<TtsScltxxcjProductType> getTypeList() {

        return provider.getTypeList();
    }

    public TtsScltxxcjProductType getType(String type) {
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("type",type);
        return provider.getProTypeByCode(queryParams);
    }
}