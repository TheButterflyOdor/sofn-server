package com.sofn.web.qry;

/**
 * Created by Administrator on 2016/9/20.
 */

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.Assert;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.AlesDailyEnforceLawDto;
import com.sofn.model.qry.QueryParameter;
import com.sofn.model.sys.SysUserBean;
import com.sofn.service.qry.DailyEnforceLawService;
import com.sofn.util.Translate;
import com.sun.javafx.fxml.expression.Expression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.sofn.util.Page;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 5:05
 */
@RestController
@Api(value = "日常执法", description = "日常执法")
@RequestMapping(value = "/dailyEnforceLaw", method = RequestMethod.POST)
public class DailyEnforceLawController extends BaseController {

    @Autowired
    private DailyEnforceLawService dailyEnforceLawService;

    @ApiOperation(value = "验证任务名称重复")
    @SystemControllerLog(description = "验证任务名称重复", operationType = "查询")
    @RequestMapping(value = "/equalsTaskName", method = RequestMethod.POST)
    public Object equalsTaskName(String taskName) {
        boolean b = dailyEnforceLawService.equalsTaskName(taskName);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("b",b);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据条件获取日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取日常执法任务列表")
    @SystemControllerLog(description = "获取日常执法任务列表", operationType = "查询")
    @RequestMapping(value = "/getDailyEnforceLawList", method = RequestMethod.POST)
    @Authorization
    public Object getDailyEnforceLawList(TtsScltxxcjRegiter entity, String areaId, String taskYear, String dateBegin,
                                         String dateEnd, Page page, String queryCon, String enforceLawResult ,@RequestHeader String token) {
        logger.info("token指令数据==>>"+token);
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        PageInfo<AlesDailyEnforceLaw> pageInfo = dailyEnforceLawService.getDailyEnforceLawList(entity, areaId, taskYear, dateBegin, dateEnd,
                page, queryCon,enforceLawResult);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap,pageInfo);
    }

    /**
     * 导出日常执法数据
     */
    @ApiOperation(value = "导出日常执法任务数据")
    @SystemControllerLog(description = "导出日常执法任务数据", operationType = "查询")
    @RequestMapping(value = "/exportSubjSv")
    public void exportSubjSv(TtsScltxxcjRegiter entity, String areaId, String taskYear, String dateBegin, String dateEnd,
                             String queryCon,String enforceLawResult,int flag)  {
        //查询数据
        List<AlesDailyEnforceLawDto> list = dailyEnforceLawService.getAllDailyEnforceLawList(entity, areaId, taskYear, dateBegin, dateEnd, queryCon,enforceLawResult);
        List<List<String>> datas=new ArrayList<>();
        //表头
        List<String> dataTitle=new ArrayList<>();
        dataTitle.add("年度");
        dataTitle.add("任务名称");
        dataTitle.add("所属行业");
        dataTitle.add("被执法对象");
        dataTitle.add("主体类型");
        dataTitle.add("区域");
        dataTitle.add("开始执法时间");
        dataTitle.add("执法结束时间");
        dataTitle.add("巡查结果");
        dataTitle.add("地址");
        dataTitle.add("巡查人数");
        dataTitle.add("执法人员");
        datas.add(dataTitle);
        //遍历
        for (AlesDailyEnforceLawDto a:list){
            List<String> data=new LinkedList<>();
            data.add(DateUtil.format(a.getTaskBeginTime(),"yyyy-MM-dd").substring(0,4));
            data.add(a.getTaskName());
            data.add(a.getEntityIndustryName());
            data.add(a.getEnterpriseName());
            data.add(a.getEntityTypeName());
            data.add(Translate.getArae(a.getAreaId()));
            data.add(DateUtil.format(a.getTaskBeginTime(),"yyyy-MM-dd"));
            data.add(DateUtil.format(a.getTaskEndTime(),"yyyy-MM-dd"));
            data.add(a.getEnforceLawResult());
            data.add(a.getEnterpriseAddress());
            data.add(a.getTaskPersonCount());
            data.add(a.getTaskPersonName());
            datas.add(data);
        }
        try {
            if(flag==0){
                DownloadExcelUtil.exportDataByExcel("日常执法",null,datas);
            }else if(flag==1){
                DownloadExcelUtil.exportDataByExcel("执法检查",null,datas);
            }else {
                DownloadExcelUtil.exportDataByExcel("日常执法",null,datas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据ID获取单个日常执法任务
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个日常执法任务")
    @SystemControllerLog(description = "获取单个日常执法任务", operationType = "查询")
    @RequestMapping(value = "/getDailyEnforceLawById", method = RequestMethod.POST)
    @Authorization
    public Object findDailyEnforceLawById(@RequestBody AlesDailyEnforceLaw dailyEnforceLaw, @RequestHeader String token) {
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }
        dailyEnforceLaw = dailyEnforceLawService.findDailyEnforceLaw(dailyEnforceLaw.getId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("dailyEnforceLaw",dailyEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 获取登录用户
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "根据登录用户获取用户所属机构信息")
    @SystemControllerLog(description = "根据登录用户获取用户所属机构信息", operationType = "查询")
    @RequestMapping(value = "/getOrgbyToken")
    public Object getOrgbyToken(@RequestHeader String token) {
        Organization o = dailyEnforceLawService.getOrganizationByToken(token);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("data", o);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据用户token获取当前用户所属机构下所有用户")
    @SystemControllerLog(description = "根据用户token获取当前用户所属机构下所有用户", operationType = "查询")
    @RequestMapping(value = "/getUsersListByUserToken")
    public Object getUsersListByUserToken(HttpServletRequest request, AsmsSubjEnforceLaw task, QueryParameter p, String keyword) {
        int count = dailyEnforceLawService.getUsersCount(p.getToken());
        com.sofn.core.persistence.Page page = new com.sofn.core.persistence.Page();
        page.setDraw(1);
        page.setStart((long) (p.getStart()));
        page.setLength((long) p.getLength());
        page.setRecordsTotal(count);
        page.doPage();
        List<SysUserBean> beans = null;
        if (count > 0) {
            beans = dailyEnforceLawService.getUserList(page, keyword, p.getToken());
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        return setSuccessModelMap(modelMap,beans);
    }

    /**
     * 检测机构列表
     * ***********JcOrgTools**************
     */

    @ApiOperation(value = "根据用户token获取用户所属机构下所有检测机构列表")
    @SystemControllerLog(description = "根据用户token获取用户所属机构下所有检测机构列表", operationType = "查询")
    @RequestMapping(value = "/getOrgsListByUserToken")
    public Object getOrgsListByUserToken(AsmsSubjDetection subjDetection, com.sofn.util.Page page, String token) {
        PageInfo pageInfo = dailyEnforceLawService.getOrgs(subjDetection, page, token);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        return setSuccessModelMap(modelMap, pageInfo.getList());
    }

}
