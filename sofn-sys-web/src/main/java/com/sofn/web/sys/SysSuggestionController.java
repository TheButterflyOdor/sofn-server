package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.model.sys.SysSuggestionQueryParamDTO;
import com.sofn.model.sys.SysSuggestionQueryResultDTO;
import com.sofn.service.sys.SysSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 追溯系统优化建议查询控制器
 *
 * Created by Administrator on 2018/1/19/019.
 */
@RestController
@RequestMapping(value = "/sysSuggestion")
public class SysSuggestionController extends BaseController {
    @Autowired
    private SysSuggestionService sysSuggestionService;

    @Authorization
    @SystemControllerLog(description = "根据查询条件分页查询追溯系统优化建议信息", operationType = "查询")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Object queryList(SysSuggestionQueryParamDTO queryParam) {
        PageInfo<SysSuggestionQueryResultDTO> pageInfo = sysSuggestionService.getSuggestionList(queryParam);

        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    @Authorization
    @SystemControllerLog(description = "根据ID查询追溯系统优化建议信息", operationType = "查询")
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public Object queryOne(String id) {
        SysSuggestionQueryResultDTO model = sysSuggestionService.getSuggestion(id);

        return setSuccessModelMap(new ModelMap(), model);
    }
}
