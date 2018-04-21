package com.sofn.web.sys;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.service.sys.SysCodeRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
@RestController
@Api(value = "编码规则管理", description = "编码规则管理")
@RequestMapping("/sysCodeRule")
public class SysCodeRuleController extends BaseController {
    @Autowired
    private SysCodeRuleService sysCodeRuleService;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SysCodeRuleController.class);
    //查询所有资源信息
    @Authorization
    @ApiOperation(value = "编码规则查询")
    /*@RequiresPermissions("sys.codeRule.read")*/
    @RequestMapping("/read/list")
    public Object getList(ModelMap modelMap,@RequestParam(value = "start", required = false)Long start, @RequestParam(value = "length", required = false)Long length){
//        logger.debug("进入列表方法****************************");
//        PageInfo<?> pageInfo = sysCodeRuleService.getSysCodeRuleList(start,length);
//        return setSuccessModelMap(modelMap,pageInfo);
        int count=sysCodeRuleService.getRecordsTotal();
        Page page =new Page();
        page.setStart(start);
        page.setLength(length);
        page.setRecordsTotal(count);
        // 分页计算
        page.doPage();
        List<SysCodeRule> beans=null;
        //如果总数为0就不再查下数据，减小数据库压力
        if(count>0) {
            //判断超级管理员权限
            beans = sysCodeRuleService.getPageList(page);
        }else{
            beans=new ArrayList<SysCodeRule>();
        }
        modelMap.addAttribute("data", beans);
        modelMap.addAttribute("page", page);
        return setSuccessModelMap(modelMap);
    }

    // 根据id查详细信息
    @ApiOperation(value = "编码规则详情")
    /*@RequiresPermissions("sys.codeRule.read")*/
    @RequestMapping(value = "/read/detail")
    public Object detail(String ruleId) {
        logger.debug("进入查询方法**************");
        ModelMap modelMap =new ModelMap();
        SysCodeRule record = sysCodeRuleService.queryById(ruleId);
//        Map record=sysCodeRuleService.queryCodeRuleById(ruleId);
        return setSuccessModelMap(modelMap, record.getReservedField1());
    }
    // 添加资源
    @ApiOperation(value = "编码规则")
   /* @RequiresPermissions("sys.codeRule.add")*/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @SystemControllerLog
    public Object add(@RequestBody JSONObject jsonObject,@RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        /*String rule_cole = "";
        String rule_name = (String)jsonObject.get("rule_name");
        //编码规则
        SysCodeRule sysCodeRule  = new SysCodeRule();
        sysCodeRule.setRule_name(rule_name);

        List<Map<String,Object>> rules = (List<Map<String,Object>>) jsonObject.get("rules");
        List<SysCodeRuleField> sysCodeRuleFieldList = new ArrayList<SysCodeRuleField>();
        List<SysCodeRuleData> sysCodeRuleDataList = new ArrayList<SysCodeRuleData>();
        for(int i=0;i<rules.size();i++){
            String field_name = rules.get(i).get("name").toString();
            //编码规则--码段
            SysCodeRuleField sysCodeRuleField = new SysCodeRuleField();
            sysCodeRuleField.setFieldName(field_name);
            sysCodeRuleField.setSortId(String.valueOf(i+1));
            sysCodeRuleFieldList.add(sysCodeRuleField);
            List<Map<String,String>> maplist = (List<Map<String, String>>) rules.get(i).get("children");
            for(int j = 0;j<maplist.size();j++){
                Map<String,String> map = maplist.get(j);
                //编码规则--码段值
                SysCodeRuleData sysCodeRuleData = new SysCodeRuleData();
                sysCodeRuleData.setFieldName(field_name);
                sysCodeRuleData.setSortId(String.valueOf(j+1));
                sysCodeRuleData.setItemName(map.get("item_val"));
                String num=map.get("item_num").toString();
                sysCodeRuleData.setItemVal(num);
                rule_cole += num;
                sysCodeRuleDataList.add(sysCodeRuleData);
            }
        }
        sysCodeRule.setRule_code(rule_cole);*/
        try {
            String id=sysCodeRuleService.saveSysCodeRule(jsonObject,u.getId());
        } catch (ParseException e) {
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        }

        return setSuccessModelMap(modelMap);
    }
/*
    // 修改资源
    @ApiOperation(value = "修改编码规则")
    *//*@RequiresPermissions("sys.codeRule.update")*//*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody JSONObject jsonObject,@RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);

        return setSuccessModelMap(modelMap);
    }*/

    // 删除资源
    @ApiOperation(value = "删除编码规则")
   /* @RequiresPermissions("sys.codeRule.delete")*/
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String dataInfo,@RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
//        CurrentUser u= WebUtil.getCurrentUser(token);
//        //未登录
//        if (u == null)
//            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        String[] jsonStr=dataInfo.split("\\|");
        for (int i = 0; i < jsonStr.length; i++) {
            logger.debug(jsonStr[i] + "  ");
            String oStr=jsonStr[i].toString();
            if(oStr==null||oStr.equals("")){
                continue;
            }
            sysCodeRuleService.delete(oStr);
//            SysCodeRule sysCodeRule = sysCodeRuleService.queryById(oStr);
//            sysCodeRule.setDelFlag("Y");
//            sysCodeRuleService.update(sysCodeRule);
        }
        return setSuccessModelMap(modelMap);
    }




}
