package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsProducttempoRrarycodeExpandMapper;
import com.sofn.model.generator.AdsProducttempoRrarycode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 产品临时码 providerImpl 实现
 * Created by moon.l
 */
@DubboService(interfaceClass = AdsProducttempoRrarycodeProvider.class)
public class AdsProducttempoRrarycodeProviderImpl extends BaseProviderImpl<AdsProducttempoRrarycode> implements AdsProducttempoRrarycodeProvider {

    @Autowired
    private AdsProducttempoRrarycodeExpandMapper AdsProducttempoRrarycodeExpandMapper;

    public PageInfo<AdsProducttempoRrarycode> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AdsProducttempoRrarycodeExpandMapper.getPageInfo(map);
        long count = AdsProducttempoRrarycodeExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    public AdsProducttempoRrarycode findById(String id) {
        return AdsProducttempoRrarycodeExpandMapper.findById(id);
    }

}
