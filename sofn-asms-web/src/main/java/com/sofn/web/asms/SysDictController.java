package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.DictType;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.service.asms.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年11月16日 上午 9:43
 */
@RestController
@Api(value = "获取字典选项",description = "获取字典选项")
@RequestMapping(value = "/sysDict",method = RequestMethod.POST)
public class SysDictController extends BaseController{

    @Autowired
    private SysDictService sysDictService;

    @ApiOperation(value = "获取数据字典")
    @SystemControllerLog(description = "获取数据字典",operationType = "查询")
    @RequestMapping(value = "/getSysDictByCode")
    @Authorization
    public Object getSysDictByCode(@RequestHeader String token, @RequestBody String type){
        String dict = "";
        switch (type) {
            case "0":
                dict = sysDictService.getSysDictByCode(DictType.ORGTYPE);//机构类别
                break;
            case "1":
                dict = sysDictService.getSysDictByCode(DictType.ORGLEVEL);//机构级别
                break;
            case "2":
                dict = sysDictService.getSysDictByCode(DictType.INDUSTRYTYPE);//行业分类
                break;
            case "3":
                dict = sysDictService.getSysDictByCode(DictType.POSTTYPE);//职务
                break;
            case "4":
                dict = sysDictService.getSysDictByCode(DictType.POSTLEVEL);//职级
                break;
            case "5":
                dict = sysDictService.getSysDictByCode(DictType.YEAR);//年份
                break;
            case "6":
                dict = sysDictService.getSysDictByCode(DictType.ORGMODE);//组织形式
                break;
            case "7":
                dict = sysDictService.getSysDictByCode(DictType.SUBJTYPE);//主体类型
                break;
            case "8":
                dict = sysDictService.getSysDictByCode(DictType.SUBJPROPERTY);//主体属性
                break;
            case "9":
                dict = sysDictService.getSysDictByCode(DictType.UNITTYPE);//数量单位
                break;
            case "10":
                dict = sysDictService.getSysDictByCode(DictType.QTMODE);//质检情况
                break;
            case "11":
                dict = sysDictService.getSysDictByCode(DictType.PRODUCTFROM);//产品来源
                break;
            case "12":
                dict = sysDictService.getSysDictByCode(DictType.ISSUETYPE);//问题类型
                break;
            case "13":
                dict = sysDictService.getSysDictByCode(DictType.MONITORMODEL);//监测模型
                break;
            case "14":
                dict = sysDictService.getSysDictByCode(DictType.PATROLSCOPE);//巡查范围
                break;
            case "15":
                dict = sysDictService.getSysDictByCode(DictType.PATROLSUBJTYPE);//巡查主体类型
                break;
            case "16":
                dict = sysDictService.getSysDictByCode(DictType.PATROLRESULT);//巡查结果
                break;
            case "17":
                dict = sysDictService.getSysDictByCode(DictType.PATROLPERIOD);//巡查季度
                break;
            case "18":
                dict = sysDictService.getSysDictByCode(DictType.QUALIFICATIONCERTIFICATE);//资质证书
                break;
            case "19":
                dict = sysDictService.getSysDictByCode(DictType.MONITORTYPE);//监测类型
                break;
            case "20":
                dict = sysDictService.getSysDictByCode(DictType.DETECITEM);//检测项目
                break;
            case "21":
                dict = sysDictService.getSysDictByCode(DictType.SAMPLETACHE);//抽样环节
                break;
            case "22":
                dict = sysDictService.getSysDictByCode(DictType.LEADUNIT);//牵头单位
                break;
            case "23":
                dict = sysDictService.getSysDictByCode(DictType.DETECSTANDARD);//检测标准
                break;
            case "24":
                dict = sysDictService.getSysDictByCode(DictType.SAMPLEMETHOD);//抽样方法
                break;
            case "25":
                dict = sysDictService.getSysDictByCode(DictType.ORGNATURE);//机构性质
                break;
            case "26":
                dict = sysDictService.getSysDictByCode(DictType.JUDGESTANDARD);//判断标准
                break;
            case "27":
                dict = sysDictService.getSysDictByCode(DictType.MONITORBATCH);//监测批次
                break;
            case "28":
                dict = sysDictService.getSysDictByCode(DictType.MONITORKIND);//监测种类
                break;
            case "29":
                dict = sysDictService.getSysDictByCode(DictType.COMMONOPINION);//常用意见
                break;
            case "30":
                dict = sysDictService.getSysDictByCode(DictType.TEMPTYPE);//临时主体类型
        }
        return setSuccessModelMap(new ModelMap(),dict);
    }

    @ApiOperation(value = "获取全部数据字典")
    @SystemControllerLog(description = "获取全部数据字典",operationType = "查询")
    @RequestMapping(value = "/getAllSysDict")
    @Authorization
    public Object getAllSysDict(@RequestHeader String token){
        Map map = new HashMap();
        map.put("orgType",sysDictService.getSysDictListByCode(DictType.ORGTYPE));//机构类别
        map.put("orgLevel",sysDictService.getSysDictListByCode(DictType.ORGLEVEL));//机构级别
        map.put("industryType",sysDictService.getSysDictListByCode(DictType.INDUSTRYTYPE));//行业分类
        map.put("postType",sysDictService.getSysDictListByCode(DictType.POSTTYPE));//职务
        map.put("postLevel",sysDictService.getSysDictListByCode(DictType.POSTLEVEL));//职级
        map.put("year",sysDictService.getSysDictListByCode(DictType.YEAR));//年份
        map.put("orgMode",sysDictService.getSysDictListByCode(DictType.ORGMODE));//组织形式
        map.put("subjType",sysDictService.getSysDictListByCode(DictType.SUBJTYPE));//主体类型
        map.put("subjProperty",sysDictService.getSysDictListByCode(DictType.SUBJPROPERTY));//主体属性
        map.put("unitType",sysDictService.getSysDictListByCode(DictType.UNITTYPE));//数量单位
        map.put("qtMode",sysDictService.getSysDictListByCode(DictType.QTMODE));//质检情况
        map.put("productFrom",sysDictService.getSysDictListByCode(DictType.PRODUCTFROM));//产品来源
        map.put("issueType",sysDictService.getSysDictListByCode(DictType.ISSUETYPE));//问题类型
        map.put("monitorModel",sysDictService.getSysDictListByCode(DictType.MONITORMODEL));//监测模型
        map.put("patrolScope",sysDictService.getSysDictListByCode(DictType.PATROLSCOPE));//巡查范围
        map.put("patrolSubjType",sysDictService.getSysDictListByCode(DictType.PATROLSUBJTYPE));//巡查主体类型
        map.put("patrolResult",sysDictService.getSysDictListByCode(DictType.PATROLRESULT));//巡查结果
        map.put("patrolPeriod",sysDictService.getSysDictListByCode(DictType.PATROLPERIOD));//巡查季度
        map.put("qualificationCertificate",sysDictService.getSysDictListByCode(DictType.QUALIFICATIONCERTIFICATE));//资质证书
        map.put("monitorType",sysDictService.getSysDictListByCode(DictType.MONITORTYPE));//监测类型
        map.put("detecItem",sysDictService.getSysDictListByCode(DictType.DETECITEM));//检测项目
        map.put("sampleTache",sysDictService.getSysDictListByCode(DictType.SAMPLETACHE));//抽样环节
        map.put("leadUnit",sysDictService.getSysDictListByCode(DictType.LEADUNIT));//牵头单位
        map.put("detecStandard",sysDictService.getSysDictListByCode(DictType.DETECSTANDARD));//检测标准
        map.put("sampleMethod",sysDictService.getSysDictListByCode(DictType.SAMPLEMETHOD));//抽样方法
        map.put("orgNature",sysDictService.getSysDictListByCode(DictType.ORGNATURE));//资质
        map.put("judgeStandard",sysDictService.getSysDictListByCode(DictType.JUDGESTANDARD));//判断标准
        map.put("monitorBatch",sysDictService.getSysDictListByCode(DictType.MONITORBATCH));//监测批次
        map.put("monitorKind",sysDictService.getSysDictListByCode(DictType.MONITORKIND));//监测种类
        map.put("commonOpinion",sysDictService.getSysDictListByCode(DictType.COMMONOPINION));//常用意见
        return setSuccessModelMap(new ModelMap(),map);
    }

    @ApiOperation(value = "获取数据字典列表")
    @SystemControllerLog(description = "获取数据字典列表",operationType = "查询")
    @RequestMapping(value = "/getDictListByCode")
    @Authorization
    public Object getDictListByCode(@RequestHeader String token, HttpServletRequest request, String type){
        List<SysDictData> dict = new ArrayList<>();
        switch (type) {
            case "23":
                dict = sysDictService.getSysDictListByCode(DictType.DETECSTANDARD);//检测标准
                break;
            case "26":
                dict = sysDictService.getSysDictListByCode(DictType.JUDGESTANDARD);//判断标准
                break;
            case "20":
                dict = sysDictService.getSysDictListByCode(DictType.DETECITEM);//检测项
                break;
        }
        return setSuccessModelMap(new ModelMap(),dict);
    }

    @ApiOperation(value = "获取数据字典列表")
    @SystemControllerLog(description = "获取数据字典列表",operationType = "查询")
    @RequestMapping(value = "/getStandardList")
    @Authorization
    public Object getStandardList(@RequestHeader String token, ModelMap modelMap){
        Page page = new Page();
        page.setStart(0L);
        page.setLength(100L);
        PageInfo<SysTestStandardModel> pageInfo = sysDictService.getStandardList(page);
        modelMap.put("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
}
