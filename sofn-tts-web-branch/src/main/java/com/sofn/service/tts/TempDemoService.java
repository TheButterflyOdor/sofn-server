package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TempDemo;
import com.sofn.provider.tts.TempDemoProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Administrator on 2016/10/14.
 */
@Service
public class TempDemoService extends BaseService<TempDemoProvider, TempDemo> {

    @DubboReference
    public void setTempDemoProvider(TempDemoProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TempDemo bean, String name, String tel,int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name",name);
        queryParams.put("tel",tel);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }


}
