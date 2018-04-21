package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjProposal;
import com.sofn.model.sys.SysSuggestionQueryParamDTO;
import com.sofn.model.sys.SysSuggestionQueryResultDTO;
import com.sofn.provider.sys.SysSuggestionProvider;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/19/019.
 */
@Service
public class SysSuggestionService {
    @DubboReference
    private SysSuggestionProvider provider;

    public PageInfo<SysSuggestionQueryResultDTO> getSuggestionList(SysSuggestionQueryParamDTO queryParam) {
        return provider.getSuggestionList(queryParam);
    }

    public SysSuggestionQueryResultDTO getSuggestion(String id) {
        return provider.getSuggestion(id);
    }
}
