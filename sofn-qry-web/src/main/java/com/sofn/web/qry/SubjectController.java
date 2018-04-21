package com.sofn.web.qry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.TtsScltxxcjRegiterDto2;
import com.sofn.service.qry.SubjectService;
import com.sofn.util.Page;
import com.sofn.util.Translate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author sofn
 * @version 2017年03月17日 下午 2:29
 */
@RestController
@Api(value = "备案主体查询",description = "备案主体查询")
@RequestMapping(value = "/subject",method = RequestMethod.POST)
public class SubjectController extends BaseController{

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "根据token获取用户信息")
    @SystemControllerLog(description = "根据token获取用户信息",operationType = "查询")
    @RequestMapping(value = "/findCurrentUserByToken")
    @Authorization
    public Object findCurrentUserByToken(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("user",user);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据条件获取监管机构主体列表")
    @SystemControllerLog(description = "根据条件获取监管机构主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjSuperviseList")
    @Authorization
    public Object getSubjSuperviseList(@RequestHeader String token, AsmsSubjSupervise subjSupervise, Page page, String areaId, String dateBegin, String dateEnd){
        PageInfo pageInfo = subjectService.getSubjSuperviseList(token,subjSupervise,page,areaId,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据条件获取执法机构主体列表")
    @SystemControllerLog(description = "根据条件获取执法机构主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEnforceLawList")
    @Authorization
    public Object getSubjEnforceLawList(@RequestHeader String token, AsmsSubjEnforceLaw subjEnforceLaw, Page page, String dateBegin, String dateEnd){
        PageInfo pageInfo = subjectService.getSubjEnforceLawList(token,subjEnforceLaw, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据条件获取检测机构主体列表")
    @SystemControllerLog(description = "根据条件获取检测机构主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjDetectionList")
    @Authorization
    public Object getSubjDetectionList(@RequestHeader String token, AsmsSubjDetection subjDetection, Page page, String dateBegin, String dateEnd){
        PageInfo pageInfo = subjectService.getSubjDetectionList(token,subjDetection,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据条件获取生产经营主体列表")
    @SystemControllerLog(description = "获取生产经营主体列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntList")
    @Authorization
    public Object getSubjEntList(@RequestHeader String token, TtsScltxxcjRegiterDto2 entity, Page page, String dateBegin,String dateEnd){
        PageInfo pageInfo = subjectService.getSubjEntList(entity,page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "通过生产经营主体ID获取变更列表")
    @SystemControllerLog(description = "通过生产经营主体ID获取变更列表",operationType = "查询")
    @RequestMapping(value = "/getChangeListByEntId")
    @Authorization
    public Object getChangeListByEntId(@RequestHeader String token, String entityId,Page page){
        PageInfo pageInfo = subjectService.getChangeListByEntId(entityId,page.getStart(),page.getLength());
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取生产经营主体不良记录信息列表")
    @SystemControllerLog(description = "获取生产经营主体不良记录信息列表",operationType = "查询")
    @RequestMapping(value = "/getEntBadRecordList", method = RequestMethod.POST)
    @Authorization
    public Object getEntBadRecordList(@RequestHeader String token, Page page, String enterpriseId) {
        PageInfo pageInfo = subjectService.getAsmsSubjEntBadrecordByIdList(page, enterpriseId);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "查看监管机构主体详情")
    @SystemControllerLog(description = "查看监管机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjSuperviseById")
    @Authorization
    public Object findSubjSuperviseById(@RequestHeader String token, @RequestBody AsmsSubjSupervise subjSupervise){
        Assert.isNotBlank(subjSupervise.getId(),"ID");
        subjSupervise = subjectService.findSubjSuperviseById(subjSupervise.getId());
        return setSuccessModelMap(new ModelMap(), subjSupervise);
    }

    @ApiOperation(value = "查看执法机构主体详情")
    @SystemControllerLog(description = "查看执法机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEnforceLawById")
    @Authorization
    public Object findSubjEnforceLawById(@RequestHeader String token, @RequestBody AsmsSubjEnforceLaw subjEnforceLaw){
        Assert.isNotBlank(subjEnforceLaw.getId(),"ID");
        subjEnforceLaw = subjectService.findSubjEnforceLawById(subjEnforceLaw.getId());
        return setSuccessModelMap(new ModelMap(),subjEnforceLaw);
    }

    @ApiOperation(value = "查看检测机构主体详情")
    @SystemControllerLog(description = "查看检测机构主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjDetectionById")
    @Authorization
    public Object findSubjDetectionById(@RequestHeader String token, @RequestBody AsmsSubjDetection subjDetection){
        Assert.isNotBlank(subjDetection.getId(),"ID");
        subjDetection = subjectService.findSubjDetectionById(subjDetection.getId());
        return setSuccessModelMap(new ModelMap(),subjDetection);
    }

    @ApiOperation(value = "通过ID获取生产经营主体详情")
    @SystemControllerLog(description = "通过ID获取生产经营主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntById")
    @Authorization
    public Object findSubjEntById(@RequestHeader String token, @RequestBody TtsScltxxcjRegiterDto2 register){
        register = subjectService.findSubjEntById(register);
        if(register!=null&&register.getId()!=null&&!"".equals(register.getId())){
            return setSuccessModelMap(new ModelMap(),register);
        }
        return setModelMap(new ModelMap(),HttpCode.NOT_FOUND_DATA);
    }

    @ApiOperation(value = "通过主体身份码获取生产经营主体详情")
    @SystemControllerLog(description = "通过主体身份码获取生产经营主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntByEntityIdCode")
    @Authorization
    public Object findSubjEntByEntityIdCode(@RequestHeader String token, @RequestBody TtsScltxxcjRegiterDto2 register){
        register = subjectService.findSubjEntByEntityIdCode(register);
        if(register!=null&&register.getId()!=null&&!"".equals(register.getId())){
            return setSuccessModelMap(new ModelMap(),register);
        }
        return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
    }

    @ApiOperation(value = "通过ID获取临时生产经营主体详情")
    @SystemControllerLog(description = "通过ID获取临时生产经营主体详情",operationType = "查询")
    @RequestMapping(value = "/findSubjEntTempById")
    @Authorization
    public Object findSubjEntTempById(@RequestHeader String token, @RequestBody AsmsSubjEntTemp subjEntTemp){
        subjEntTemp = subjectService.findSubjEntTempById(subjEntTemp.getId());
        return setSuccessModelMap(new ModelMap(),subjEntTemp);
    }

    @ApiOperation(value = "获取监管机构的变更记录列表")
    @SystemControllerLog(description = "获取监管机构的变更记录列表",operationType = "查询")
    @RequestMapping(value = "/getChangeListBySvId")
    @Authorization
    public Object getChangeListBySvId(@RequestHeader String token, Page page,String id){
        PageInfo pageInfo = subjectService.getChangeListBySvId(page, id);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取执法机构变更历史列表")
    @SystemControllerLog(description = "获取执法机构变更历史列表",operationType = "查询")
    @RequestMapping(value = "/getChangeListByElId",method = RequestMethod.POST)
    @Authorization
    public Object getChangeListByElId(@RequestHeader String token, Page page,String id){
        PageInfo pageInfo = subjectService.getChangeListByElId(page,id);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "获取检测机构变更历史列表")
    @SystemControllerLog(description = "获取检测机构变更历史列表",operationType = "查询")
    @RequestMapping(value = "/getChangeListByDtId",method = RequestMethod.POST)
    @Authorization
    public Object getChangeListByDtId(@RequestHeader String token, Page page,String id){
        PageInfo pageInfo = subjectService.getChangeListByDtId(page,id);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "通过ID获取单个监管机构主体变更申请")
    @SystemControllerLog(description = "通过ID获取单个监管机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/findSubjSvChangeById")
    @Authorization
    public Object findSubjSvChangeById(@RequestHeader String token, @RequestBody AsmsSubjSvChange subjSvChange){
        Assert.isNotBlank(subjSvChange.getId(),"ID");
        subjSvChange = subjectService.findSubjSvChangeById(subjSvChange.getId());
        JSONObject jsonObject = JSONArray.parseObject(subjSvChange.getChangeBeforeField());
        AsmsSubjSupervise subjSupervise = jsonObject.getObject("before",AsmsSubjSupervise.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjSvChange", subjSvChange);
        modelMap.addAttribute("subjSv", subjSupervise);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "通过ID获取单个执法机构主体变更申请")
    @SystemControllerLog(description = "通过ID获取单个执法机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/findSubjElChangeById")
    @Authorization
    public Object findSubjElChangeById(@RequestHeader String token, @RequestBody AsmsSubjElChange subjElChange){
        Assert.isNotBlank(subjElChange.getId(),"ID");
        subjElChange = subjectService.findSubjElChangeById(subjElChange.getId());
        JSONObject jsonObject = JSONArray.parseObject(subjElChange.getChangeBeforeField());
        AsmsSubjEnforceLaw subjEnforceLaw = jsonObject.getObject("before",AsmsSubjEnforceLaw.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjElChange", subjElChange);
        modelMap.addAttribute("subjEl", subjEnforceLaw);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "通过ID获取单个检测机构主体变更申请")
    @SystemControllerLog(description = "通过ID获取单个检测机构主体变更申请",operationType = "查询")
    @RequestMapping(value = "/findSubjDtChangeById")
    @Authorization
    public Object findSubjDtChangeById(@RequestHeader String token, @RequestBody AsmsSubjDtChange subjDtChange){
        Assert.isNotBlank(subjDtChange.getId(),"ID");
        subjDtChange = subjectService.findSubjDtChangeById(subjDtChange.getId());
        JSONObject jsonObject = JSONArray.parseObject(subjDtChange.getChangeBeforeField());
        AsmsSubjDetection subjDetection = jsonObject.getObject("before",AsmsSubjDetection.class);
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("subjDtChange", subjDtChange);
        modelMap.addAttribute("subjDt", subjDetection);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "导出监管机构数据")
    @SystemControllerLog(description = "导出监管机构数据",operationType = "查询")
    @RequestMapping(value = "/exportSubjSv",method = RequestMethod.POST)
    @Authorization
    public void exportSubjSv(String token, HttpServletResponse response, AsmsSubjSupervise subjSupervise, Page page,
                       String areaId, String dateBegin, String dateEnd) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjSuperviseList(token,subjSupervise,page,areaId,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ResponseEntity<byte[]> responseE = null ;
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
        dateTitle.add("机构名称");
        dateTitle.add("机构代码");
        dateTitle.add("所属行业");//添增的表头名
        dateTitle.add("机构级别");
        dateTitle.add("所属区域");
        dateTitle.add("详细地址");
        dateTitle.add("机构负责人");
        dateTitle.add("负责人电话");
        dateTitle.add("备案时间");//添加表头名（备案时间）
        dateTitle.add("联系人");
        dateTitle.add("联系人电话");
        dateTitle.add("联系人QQ");
        dateTitle.add("电子邮箱");
        dateTitle.add("邮编");
        datas.add(dateTitle);
        for(int i=0;i<pageInfo.getList().size();i++){
            AsmsSubjSupervise a = (AsmsSubjSupervise) pageInfo.getList().get(i);
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i+1));
            data.add(a.getSvName());
            data.add(a.getSvCode());
            data.add(a.getIndustryName());//添加所属行业数据
            switch (a.getSvLevel()){
                case "0":
                    data.add("部级");
                    break;
                case "1":
                    data.add("省级");
                    break;
                case "2":
                    data.add("市级");
                    break;
                case "3":
                    data.add("县级");
                    break;
                default:
                    data.add("");
                    break;
            }
            data.add(a.getSvArea());
            data.add(a.getSvAddress());
            data.add(a.getSvLeader());
            data.add(a.getSvLeaderPhone());
            data.add(Translate.getFormat(a.getCreateTime()));//备案时间
            data.add(a.getSvContact());
            data.add(a.getSvContactPhone());
            data.add(a.getSvContactQQ());
            data.add(a.getSvContactEmail());
            data.add(a.getSvPostcode());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("监管机构",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "导出执法机构数据")
    @SystemControllerLog(description = "导出执法机构数据",operationType = "查询")
    @RequestMapping(value = "/exportSubjEl",method = RequestMethod.POST)
    @Authorization
    public void exportSubjEl(String token, HttpServletResponse response, AsmsSubjEnforceLaw subjEnforceLaw, Page page,
                             String dateBegin, String dateEnd) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjEnforceLawList(token,subjEnforceLaw, page, dateBegin, dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ResponseEntity<byte[]> responseE = null ;
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
        dateTitle.add("机构名称");
        dateTitle.add("机构类别");//添加机构类别的表头名
        dateTitle.add("机构代码");
        dateTitle.add("机构级别");
        dateTitle.add("所属区域");
        dateTitle.add("详细地址");
        dateTitle.add("机构负责人");
        dateTitle.add("负责人电话");
        dateTitle.add("备案时间");//添加备案时间表头名
        dateTitle.add("联系人");
        dateTitle.add("联系人电话");
        dateTitle.add("联系人QQ");
        dateTitle.add("电子邮箱");
        dateTitle.add("邮编");
        dateTitle.add("单位性质");
        dateTitle.add("具体办事机构");
        dateTitle.add("执法人数");
        datas.add(dateTitle);
        for(int i=0;i<pageInfo.getList().size();i++){
            AsmsSubjEnforceLaw a = (AsmsSubjEnforceLaw) pageInfo.getList().get(i);
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i+1));
            data.add(a.getElName());
            data.add(a.getElType());//添加页面上机构类别数据
            data.add(a.getElCode());
            switch (a.getElLevel()){
                case "0":
                    data.add("部级");
                    break;
                case "1":
                    data.add("省级");
                    break;
                case "2":
                    data.add("市级");
                    break;
                case "3":
                    data.add("县级");
                    break;
                default:
                    data.add("");
                    break;
            }
            data.add(a.getElArea());
            data.add(a.getElAddress());
            data.add(a.getElLeader());
            data.add(a.getElLeaderPhone());
            data.add(Translate.getFormat(a.getCreateTime()));//备案时间
            data.add(a.getElContact());
            data.add(a.getElContactPhone());
            data.add(a.getElContactQQ());
            data.add(a.getElContactEmail());
            data.add(a.getElPostcode());
            data.add(a.getElUnitNature());
            data.add(a.getElWorkBody());
            data.add(a.getElPeopleNum());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("执法机构",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "导出检测机构数据")
    @SystemControllerLog(description = "导出检测机构数据",operationType = "查询")
    @RequestMapping(value = "/exportSubjDt",method = RequestMethod.POST)
    @Authorization
    public void exportSubjDt(String token, HttpServletResponse response, AsmsSubjDetection subjDetection, Page page,
                             String dateBegin, String dateEnd) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjDetectionList(token,subjDetection,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ResponseEntity<byte[]> responseE = null ;
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
        dateTitle.add("机构名称");
        dateTitle.add("机构类别");//添加机构类别表头名
        dateTitle.add("机构代码");
        dateTitle.add("机构性质");
        dateTitle.add("资质");
        dateTitle.add("依托单位");
        dateTitle.add("机构级别");
        dateTitle.add("所属区域");
        dateTitle.add("通讯地址");
        dateTitle.add("法人");
        dateTitle.add("机构负责人");
        dateTitle.add("负责人电话");
        dateTitle.add("备案时间");//添加备案时间
        dateTitle.add("联系人");
        dateTitle.add("联系人电话");
        dateTitle.add("联系人QQ");
        dateTitle.add("电子邮箱");
        dateTitle.add("邮编");
        dateTitle.add("技术负责人");
        dateTitle.add("质量负责人");
        dateTitle.add("参数");
        dateTitle.add("产品范围");
        datas.add(dateTitle);
        for(int i=0;i<pageInfo.getList().size();i++){
            AsmsSubjDetection a = (AsmsSubjDetection) pageInfo.getList().get(i);
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i+1));
            data.add(a.getDtName());
            data.add(a.getDtType());//添加页面显示机构类别数据
            data.add(a.getDtCode());
            data.add(a.getDtNature());
            data.add(a.getDtQualifications());
            data.add(a.getDtRelyOnUnit());
            switch (a.getDtLevel()){
                case "0":
                    data.add("部级");
                    break;
                case "1":
                    data.add("省级");
                    break;
                case "2":
                    data.add("市级");
                    break;
                case "3":
                    data.add("县级");
                    break;
                default:
                    data.add("");
                    break;
            }
            data.add(a.getDtArea());
            data.add(a.getDtAddress());
            data.add(a.getDtLegalMan());
            data.add(a.getDtLeader());
            data.add(a.getDtLeaderPhone());
            data.add(Translate.getFormat(a.getCreateTime()));//备案时间
            data.add(a.getDtContact());
            data.add(a.getDtContactPhone());
            data.add(a.getDtContactQQ());
            data.add(a.getDtContactEmail());
            data.add(a.getDtPostcode());
            data.add(a.getDtTechnicalDirector());
            data.add(a.getDtQualityDirector());
            data.add(a.getDtParameter());
            data.add(a.getDtProductRange());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("检测机构",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "导出生产经营主体数据")
    @SystemControllerLog(description = "导出生产经营主体数据",operationType = "查询")
    @RequestMapping(value = "/exportSubjEnt",method = RequestMethod.POST)
    public void exportSubjEnt(String token, TtsScltxxcjRegiterDto2 entity, Page page, String dateBegin,String dateEnd) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjEntList(entity,page,dateBegin,dateEnd);
        page.setRecordsTotal(pageInfo.getTotal());
        ResponseEntity<byte[]> responseE = null ;
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
       // dateTitle.add("企业名称");
        dateTitle.add("主体名称");//更改表头名（主体名称）
        dateTitle.add("主体类型");
        dateTitle.add("主体属性");
        dateTitle.add("所属行业");
        dateTitle.add("组织形式");//添加表头名（组织形式）
        dateTitle.add("证件类型");
        dateTitle.add("企业工商注册号");
        dateTitle.add("营业期限");
        dateTitle.add("不良记录");//添加表头名(不良记录)
        dateTitle.add("所属区域");//
        dateTitle.add("详细地址");
        dateTitle.add("法人姓名");
        dateTitle.add("法人身份证号");
        dateTitle.add("法人联系电话");
        dateTitle.add("联系人姓名");
        dateTitle.add("联系人电话");
        dateTitle.add("联系人电子邮箱");
        dateTitle.add("联系人传真号码");
        datas.add(dateTitle);
        for(int i=0;i<pageInfo.getList().size();i++){
            TtsScltxxcjRegiterDto2 a = (TtsScltxxcjRegiterDto2) pageInfo.getList().get(i);
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i+1));
            data.add(a.getEnterpriseName());
            data.add(a.getEntityTypeName());
            data.add(a.getEntityPropertyName());
            data.add(a.getEntityIndustryName());
            data.add(a.getEntityScaleName());// 添加组织形式信息数据
            data.add(a.getCardType());
            data.add(a.getCreditCode());
            if(a.getIsLong()!=null&&"true".equals(a.getIsLong())){
                data.add("长期");
            }else{
                if(a.getBusinessOperationStart()!=null&&a.getBusinessOperationEnd()!=null) {
                    data.add(DateUtil.format(a.getBusinessOperationStart(),"yyyy年MM月dd日") + "至" + DateUtil.format(a.getBusinessOperationEnd(),"yyyy年MM月dd日"));
                }else{
                    data.add("");
                }
            }
            data.add(a.getBadRecord());// 添加不良记录信息
            data.add(Translate.getArae(a.getArea()));
            data.add(a.getAddress());
            data.add(a.getLegalName());
            data.add(a.getLegalIdnumber());
            data.add(a.getLegalPhone());
            data.add(a.getContactName());
            data.add(a.getContactPhone());
            data.add(a.getContactEmail());
            data.add(a.getFaxNumber());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("生产经营主体",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "根据主体码获取产品列表")
    @SystemControllerLog(description = "根据主体码获取产品列表",operationType = "查询")
    @RequestMapping(value = "/getProductListByEntityIdcode",method = RequestMethod.POST)
    @Authorization
    public Object getProductList (@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getProductListByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据主体码获取基地列表")
    @SystemControllerLog(description = "根据主体码获取产品列表",operationType = "查询")
    @RequestMapping(value = "/getSubjEntBaseByEntityIdcode",method = RequestMethod.POST)
    @Authorization
    public Object getSubjEntBaseByEntityIdcode (@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getSubjEntBaseByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据主体码获取产品批次列表")
    @SystemControllerLog(description = "根据主体码获取产品批次列表",operationType = "查询")
    @RequestMapping(value = "/getBathByEntityIdcode",method = RequestMethod.POST)
    @Authorization
    public Object getBithByEntityIdcode (@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getBathByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    @ApiOperation(value = "根据id查询合成批次列表")
    @SystemControllerLog(description = "根据id查询合成批次列表",operationType = "查询")
    @RequestMapping(value = "/getHcBathById",method = RequestMethod.POST)
    @Authorization
    public Object getHcBathById (@RequestHeader String token, Page page, String hcid){
        PageInfo pageInfo = subjectService.getHcBathById(page,hcid);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    @ApiOperation(value = "根据主体码查询销售历史")
    @SystemControllerLog(description = "根据id查询合成批次列表",operationType = "查询")
    @RequestMapping(value = "/getSaleByEntityIdcode",method = RequestMethod.POST)
    @Authorization
    public Object getSaleByEntityIdcode (@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getSaleByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据ID获取单条销售登记数据及对应的客户信息信息（销售历史）
     * @param id
     * @return
     */
    @ApiOperation(value = "获取单条销售登记数据及对应的客户信息信息")
    @RequestMapping(value = "/getXsdjAndCustomerById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条销售登记数据及对应的客户信息信息",operationType="查询")
    @Authorization
    public Map<String,Object> getXsdjAndCustomerById(@RequestHeader String token, String id){
        TtsScltxxcjXsdjAndCustomer vo  = subjectService.getXsdjAndCustomerById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",vo);
        return map;
    }

    /**
     * 根据销售登记ID获取单条销售退回数据信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据销售登记ID获取单条销售退回数据信息")
    @RequestMapping(value = "/queryByXsjlId",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据销售登记ID获取单条销售退回数据信息",operationType="查询")
    @Authorization
    public Map<String,Object> queryByXsjlId(@RequestHeader String token, String id){
        TtsScltxxcjXsthAndCustomer ttsScltxxcjXsthAndCustomer = subjectService.queryByXsjlId(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("vo",ttsScltxxcjXsthAndCustomer);
        return map;
    }

    /**
     * 根据销售登记ID获取单条销售退回数据信息
     * @param token
     * @param page
     * @param entityIdcode
     * @return
     */
    @ApiOperation(value = "根据销售登记ID获取单条销售退回数据信息")
    @RequestMapping(value = "/getSubjCgglListByEntityIdcode",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据销售登记ID获取单条销售退回数据信息",operationType="查询")
    @Authorization
    public Object getSubjCgglListByEntityIdcode(@RequestHeader String token, Page page, String entityIdcode, String isCirculatesEnd,String confirmState){
        PageInfo pageInfo = subjectService.getSubjCgglListByEntityIdcode(page,entityIdcode,isCirculatesEnd,confirmState);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据主体码获取销售打印列表
     * @param token
     * @param page
     * @param entityIdcode
     * @return
     */
    @ApiOperation(value = "根据主体码获取销售打印列表")
    @RequestMapping(value = "/getSalePrintListByEntityIdcode",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体码获取销售打印列表",operationType="查询")
    @Authorization
    public Object getSalePrintListByEntityIdcode(@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getSalePrintListByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据主体码获取库存打印列表
     * @param token
     * @param page
     * @param entityIdcode
     * @return
     */
    @ApiOperation(value = "根据主体码获取库存打印列表")
    @RequestMapping(value = "/getStockPrintListByEntityIdcode",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体码获取库存打印列表",operationType="查询")
    @Authorization
    public Object getStockPrintListByEntityIdcode(@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getStockPrintListByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据主体码获取获取屠宰库存信息列表
     * @param token
     * @param page
     * @param entityIdcode
     * @return
     */
    @ApiOperation(value = "根据主体码获取获取屠宰库存信息列表")
    @RequestMapping(value = "/getSlaughterListByEntityIdcode",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体码获取获取屠宰库存信息列表",operationType="查询")
    @Authorization
    public Object getSlaughterListByEntityIdcode(@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getSlaughterListByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据条件查询主体和批次总数
     * @param token
     * @param page
     * @param enterpriseName
     * @return
     */
    @ApiOperation(value="根据条件查询主体和批次总数")
    @RequestMapping(value = "/getSubjEntAndBathList",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体码获取获取屠宰库存信息列表",operationType="查询")
    @Authorization
    public Object getSubjEntAndBathList(@RequestHeader String token, Page page, String enterpriseName, String area){
        PageInfo pageInfo = subjectService.getSubjEntAndBathList(page,enterpriseName,area);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据条件查询主体和交易信息
     * @param token
     * @param page
     * @param enterpriseName
     * @return
     */
    @ApiOperation(value="根据条件查询主体和交易信息")
    @RequestMapping(value = "/getSubjEntAndTransactionInfoList",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件查询主体和交易信息",operationType="查询")
    @Authorization
    public Object getSubjEntAndTransactionInfoList(@RequestHeader String token, Page page, String enterpriseName, String area){
        PageInfo pageInfo = subjectService.getSubjEntAndTransactionInfoList(page,enterpriseName,area);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据主体码查询交易记录
     * @param token
     * @param page
     * @param entityIdcode
     * @return
     */
    @ApiOperation(value="根据主体码查询交易记录")
    @RequestMapping(value = "/getTransactionInfoCountByEntityIdcode",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体码查询交易记录",operationType="查询")
    @Authorization
    public Object getTransactionInfoListByEntityIdcode(@RequestHeader String token, Page page, String entityIdcode){
        PageInfo pageInfo = subjectService.getTransactionInfoListByEntityIdcode(page,entityIdcode);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value="根据条件查询主体和打印信息")
    @RequestMapping(value = "/getSubjEntAndPrintInfoList",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件查询主体和打印信息",operationType="查询")
    @Authorization
    public Object getSubjEntAndPrintInfoList(@RequestHeader String token, Page page, String area, String enterpriseName,
                                             String productName, String  productSort, String productScglId){
        PageInfo pageInfo = subjectService.getSubjEntAndPrintInfoList(page,area, enterpriseName, productName, productSort, productScglId);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "导出追溯码数据")
    @SystemControllerLog(description = "导出追溯码数据",operationType = "查询")
    @RequestMapping(value = "/exportTraceCode",method = RequestMethod.POST)
    @Authorization
    public void exportTraceCode(HttpServletResponse response,String token, Page page, String enterpriseName, String area) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjEntAndBathList(page, enterpriseName, area);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
        dateTitle.add("企业名称");
        dateTitle.add("生成批次总数");
        datas.add(dateTitle);
        int i = 1;
        for (Object o : pageInfo.getList()) {
            HashMap m = (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i++));
            data.add(m.get("ENTERPRISE_NAME")==null?"":m.get("ENTERPRISE_NAME").toString());
            data.add(m.get("BATH_COUNT")==null?"":m.get("BATH_COUNT").toString());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("追溯码查询", null, datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "导出主体交易数据")
    @SystemControllerLog(description = "导出主体交易数据",operationType = "查询")
    @RequestMapping(value = "/exportTransactionInfo",method = RequestMethod.POST)
    @Authorization
    public void exportTransactionInfo(HttpServletResponse response,String token, Page page, String enterpriseName, String area) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjEntAndTransactionInfoList(page, enterpriseName, area);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
        dateTitle.add("企业名称");
        dateTitle.add("已确认销售数量");
        dateTitle.add("待确认销售数量");
        dateTitle.add("已确认采购数量");
        dateTitle.add("待确认采购数量");
        datas.add(dateTitle);
        int i = 1;
        for (Object o : pageInfo.getList()) {
            HashMap m = (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i++));
            data.add(m.get("ENTERPRISE_NAME")==null?"":m.get("ENTERPRISE_NAME").toString());
            data.add(m.get("XSY_COU")==null?"":m.get("XSY_COU").toString());
            data.add(m.get("XSN_COU")==null?"":m.get("XSN_COU").toString());
            data.add(m.get("SHY_COU")==null?"":m.get("SHY_COU").toString());
            data.add(m.get("SHN_COU")==null?"":m.get("SHN_COU").toString());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("主体交易查询", null, datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "导出追溯码打印数据")
    @SystemControllerLog(description = "导出追溯码打印数据",operationType = "查询")
    @RequestMapping(value = "/exportPrint",method = RequestMethod.POST)
    @Authorization
    public void exportPrint(HttpServletResponse response,String token, Page page, String area, String enterpriseName,
                            String productName, String  productSort, String productScglId) {
        page.setStart(0);
        page.setLength(100000000);
        PageInfo pageInfo = subjectService.getSubjEntAndPrintInfoList(page,area, enterpriseName, productName, productSort, productScglId);
        page.setRecordsTotal(pageInfo.getTotal());
        List<List<String>> datas = new ArrayList<>();
        List<String> dateTitle = new ArrayList<>();
        dateTitle.add("序号");
        dateTitle.add("企业名称");
        dateTitle.add("产品种类");
        dateTitle.add("产品名称");
        dateTitle.add("追溯码");
        datas.add(dateTitle);
        int i = 1;
        String traceCode = "";
        String fromZsm;
        for (Object o : pageInfo.getList()) {
            HashMap m = (HashMap) o;
            List<String> data = new LinkedList<>();
            data.add(String.valueOf(i++));
            data.add(m.get("ENTERPRISE_NAME")==null?"":m.get("ENTERPRISE_NAME").toString());
            data.add(m.get("PRODUCT_SORT")==null?"":m.get("PRODUCT_SORT").toString());
            data.add(m.get("PRODUCT_NAME")==null?"":m.get("PRODUCT_NAME").toString());
            productScglId = m.get("PRODUCT_SCGL_ID")==null?"":m.get("PRODUCT_SCGL_ID").toString();
            fromZsm = m.get("FROMZSM")==null?"":m.get("FROMZSM").toString();
            if(StringUtils.isNotBlank(fromZsm)){
                traceCode = fromZsm;
            }else {
                traceCode = productScglId;
            }
            data.add(traceCode);
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("打印查询", null, datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
