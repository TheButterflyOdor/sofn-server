package com.sofn.web.sys;

import com.sofn.core.base.BaseController;
import com.sofn.model.generator.SysCodeRuleData;
import com.sofn.service.sys.SysCodeRuleDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/10/18.
 */
@RestController
@RequestMapping("/sysCodeRuleData")
public class SysCodeRuleDataController extends BaseController {
    @Autowired
    private SysCodeRuleDataService sysCodeRuleDataService;


    /**
     * 根据ID查询
     * @param fieldId
     * @return
     */
    @ApiOperation(value = "根据fileID获取数据")
    @RequestMapping(value = "/queryByFieldId",method = RequestMethod.POST)
    public SysCodeRuleData queryByFieldId(String fieldId){
        return sysCodeRuleDataService.queryByFieldId(fieldId);
    }



}
