package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sys.SysSuggestionExpandMapper;
import com.sofn.model.sys.SysSuggestionQueryParamDTO;
import com.sofn.model.sys.SysSuggestionQueryResultDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 追溯系统优化建议查询Provider实现类
 */
@DubboService(interfaceClass = SysSuggestionProvider.class)
public class SysSuggestionProviderImpl implements SysSuggestionProvider {
    @Autowired
    private SysSuggestionExpandMapper sysSuggestionExpandMapper;
    @Override
    public PageInfo<SysSuggestionQueryResultDTO> getSuggestionList(SysSuggestionQueryParamDTO queryParam) {
        PageHelper.startPage(queryParam.getPageNum(), queryParam.getPageSize());
        List<SysSuggestionQueryResultDTO> list = sysSuggestionExpandMapper.getSuggestionList(queryParam);
        PageInfo<SysSuggestionQueryResultDTO> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public SysSuggestionQueryResultDTO getSuggestion(String id) {
        return sysSuggestionExpandMapper.getSuggestion(id);
    }
}
