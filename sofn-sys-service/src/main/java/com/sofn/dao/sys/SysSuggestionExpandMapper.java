package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.model.sys.SysSuggestionQueryParamDTO;
import com.sofn.model.sys.SysSuggestionQueryResultDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/1/19/019.
 */
@MyBatisDao
public interface SysSuggestionExpandMapper {
    List<SysSuggestionQueryResultDTO> getSuggestionList(SysSuggestionQueryParamDTO queryParam);
    SysSuggestionQueryResultDTO getSuggestion(@Param("id") String id);
}
