package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjProposalExpandMapper;
import com.sofn.model.generator.TtsScltxxcjProposal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	平台优化建议 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjProposalProvider.class)
public class TtsScltxxcjProposalProviderImpl extends BaseProviderImpl<TtsScltxxcjProposal> implements TtsScltxxcjProposalProvider {

    @Autowired
    private TtsScltxxcjProposalExpandMapper TtsScltxxcjProposalExpandMapper;

    public PageInfo<TtsScltxxcjProposal> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjProposalExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjProposalExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
