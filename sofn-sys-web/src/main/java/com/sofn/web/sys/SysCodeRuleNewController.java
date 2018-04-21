package com.sofn.web.sys;

import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleNew;
import com.sofn.service.sys.SysCodeRuleNewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@RestController
@Api(value = "编码规则管理（新）", description = "编码规则管理（新）")
@RequestMapping("/sysCodeRuleNew")
public class SysCodeRuleNewController extends BaseController {
    @Autowired
    private SysCodeRuleNewService sysCodeRuleService;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SysCodeRuleNewController.class);
    //查询所有资源信息
    @ApiOperation(value = "编码规则查询")
    /*@RequiresPermissions("sys.codeRule.read")*/
    @RequestMapping("/read/list")
    public Object getList(ModelMap modelMap,@RequestParam(value = "start", required = false)Long start, @RequestParam(value = "length", required = false)Long length){
        int count=sysCodeRuleService.getRecordsTotal();
        Page page =new Page();
        page.setStart(start);
        page.setLength(length);
        page.setRecordsTotal(count);
        // 分页计算
        page.doPage();
        List<SysCodeRuleNew> beans=null;
        //如果总数为0就不再查下数据，减小数据库压力
        if(count>0) {
            beans = sysCodeRuleService.getPageList(page);
        }else{
            beans=new ArrayList<SysCodeRuleNew>();
        }
        modelMap.addAttribute("data", beans);
        modelMap.addAttribute("page", page);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 下载附件
     *
     * @param ruleId
     * @return
     */
    @RequestMapping( value = "/read/img/{ruleId}",method = RequestMethod.GET)
    @ApiOperation(value = "编码规则示例图片")
    public void img(@PathVariable(value = "ruleId") String ruleId, HttpServletRequest
                                 request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            // 设置输出的格式
            SysCodeRuleNew codeRuleNew= sysCodeRuleService.queryById(ruleId);
            response.setHeader("content-length", codeRuleNew.getDemoImg().length + "");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            //写到输出流
            out.write(codeRuleNew.getDemoImg());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Authorization
    @ApiOperation(value = "编码规则查询")
    @RequestMapping("/read/getRule")
    public Object getRule(ModelMap modelMap,@RequestParam(value = "ruleId", required = true)String ruleId){
        SysCodeRuleNew sysCodeRuleNew=sysCodeRuleService.queryRuleAndField(ruleId);
        modelMap.addAttribute("data", sysCodeRuleNew);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "生成追溯码测试")
    @RequestMapping(value = "/read/test",method = RequestMethod.GET)
    public Object test(ModelMap modelMap,@RequestParam(value = "num", required = true)int num){
        SysCodeConvert sysCodeConvert= sysCodeRuleService.test(num,"");
        modelMap.addAttribute("code",sysCodeConvert);
        return setSuccessModelMap(modelMap);
    }
}
