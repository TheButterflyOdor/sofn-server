package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsCheckProjectExpandMapper;
import com.sofn.model.generator.AdsCheckProject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	检测项目 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsCheckProjectProvider.class)
public class AdsCheckProjectProviderImpl extends BaseProviderImpl<AdsCheckProject> implements AdsCheckProjectProvider {

    @Autowired
    private AdsCheckProjectExpandMapper AdsCheckProjectExpandMapper;

    public PageInfo<AdsCheckProject> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsCheckProjectExpandMapper.getPageInfo(map);
        long count = AdsCheckProjectExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
