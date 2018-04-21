/**
 *
 */
package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesJdccTaskSampleExpandMapper;
import com.sofn.model.generator.AlesJdccTaskSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AlesJdccTaskSampleProvider.class)
@CacheConfig(cacheNames = "AlesTaskSample")
public class AlesJdccTaskSampleProviderImpl extends BaseProviderImpl<AlesJdccTaskSample> implements AlesJdccTaskSampleProvider {

    @Autowired
    private AlesJdccTaskSampleExpandMapper expandMapper;

}
