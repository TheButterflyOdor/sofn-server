package com.sofn.web.sys;

import com.sofn.core.base.BaseController;
import com.sofn.model.generator.SysCodeRuleField;
import com.sofn.service.sys.SysCodeRuleFieldService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/10/18.
 */
@RestController
@RequestMapping("/sysCodeRuleField")
public class SysCodeRuleFieldController extends BaseController {
    @Autowired
    private SysCodeRuleFieldService sysCodeRuleFieldService;

/*
    @ApiOperation(value = "根据ruleId获取数据")
    @RequestMapping(value = "/queryByRuleId",method = RequestMethod.POST)
    public SysCodeRuleField queryByRuleId(String ruleId){
        return sysCodeRuleFieldService.queryByRuleId(ruleId);
    }*/


}
