package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.provider.tts.TtsScltxxcjProductProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
@Service
public class ProductListService extends BaseService<TtsScltxxcjProductProvider,TtsScltxxcjProduct> {

    @DubboReference
    public void setProductListProvider(TtsScltxxcjProductProvider productListProvider){
        this.provider = productListProvider;
    }

    public PageInfo<TtsScltxxcjProduct> findList(TtsScltxxcjProduct ttsScltxxcjProduct,String dateBegin, String dateEnd, int pageNum, int pageSize){
        Map<String, Object> queryParams = new HashMap<String,Object>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

}
