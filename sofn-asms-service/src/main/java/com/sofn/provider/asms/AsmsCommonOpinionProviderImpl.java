package com.sofn.provider.asms;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsCommonOpinionExpandMapper;
import com.sofn.dao.generator.AsmsCommonOpinionMapper;
import com.sofn.model.generator.AsmsCommonOpinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@DubboService(interfaceClass = AsmsCommonOpinionProvider.class)
@CacheConfig(cacheNames = "AsmsCommonOpinion")
public class AsmsCommonOpinionProviderImpl extends BaseProviderImpl<AsmsCommonOpinion> implements AsmsCommonOpinionProvider {

    @Autowired
    private AsmsCommonOpinionExpandMapper asmsCommonOpinionExpandMapper;
    @Autowired
    private AsmsCommonOpinionMapper asmsCommonOpinionMapper;
    @Override
    public List<AsmsCommonOpinion> getOpinionListByUserId(String userId) {
        return asmsCommonOpinionExpandMapper.getOpinionListByUserId(userId);
    }

    @Override
    public int addAsmsCommonOpinion(AsmsCommonOpinion asmsCommonOpinion) {
        return  asmsCommonOpinionMapper.insert(asmsCommonOpinion);
    }

    @Override
    public int updateAsmsCommonOpinion(AsmsCommonOpinion asmsCommonOpinion) {
        return asmsCommonOpinionMapper.updateByPrimaryKey(asmsCommonOpinion);
    }

}
