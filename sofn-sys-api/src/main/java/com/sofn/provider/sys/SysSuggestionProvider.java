package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.model.sys.SysSuggestionQueryParamDTO;
import com.sofn.model.sys.SysSuggestionQueryResultDTO;

/**
 * 追溯系统优化建议查询Provider
 */
public interface SysSuggestionProvider {
    /**
     * 按条件分页查询优化建议
     * @param queryParam 查询参数
     * @return 分页查询结果
     */
    PageInfo<SysSuggestionQueryResultDTO> getSuggestionList(SysSuggestionQueryParamDTO queryParam);

    /**
     * 查询某一条优化建议
     * @param id 优化建议记录ID
     * @return 优化建议信息
     */
    SysSuggestionQueryResultDTO getSuggestion(String id);
}
