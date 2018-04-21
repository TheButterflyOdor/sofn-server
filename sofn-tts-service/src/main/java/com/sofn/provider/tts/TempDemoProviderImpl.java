package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TempDemoExpandMapper;
import com.sofn.model.generator.TempDemo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@DubboService(interfaceClass = TempDemoProvider.class)
public class TempDemoProviderImpl extends BaseProviderImpl<TempDemo> implements TempDemoProvider {

    @Autowired
    private TempDemoExpandMapper tempDemoExpandMapper;

    public PageInfo<TempDemo> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = tempDemoExpandMapper.getTempDemoList(map);
        long count = tempDemoExpandMapper.getTempDemoCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


}