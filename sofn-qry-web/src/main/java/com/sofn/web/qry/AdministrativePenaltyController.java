package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.DownloadExcelUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AlesProduceAdminPunish;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.qry.AlesProduceAdminPunishDto;
import com.sofn.service.qry.ProduceAdminPunishService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**

 /**
 * @author sofn
 * @version 2016年09月20日 下午 5:05
 */
@RestController
@Api(value = "行政处罚",description = "行政处罚")
@RequestMapping(value = "/administrativePenalty")
public class AdministrativePenaltyController extends BaseController {
    @Autowired
    private ProduceAdminPunishService produceAdminPunishService;
    /**
     * "新增行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "修改行政处罚")
    @SystemControllerLog(description = "修改行政处罚",operationType="修改")
    @RequestMapping(value = "/updateAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> updateAdministrativePenalty(@RequestBody AlesProduceAdminPunish produceAdminPunish){

        produceAdminPunishService.update(produceAdminPunish);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     * 根据条件获取行政处罚列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取行政处罚列表")
    @SystemControllerLog(description = "获取行政处罚列表",operationType="查询")
    @Authorization
    @RequestMapping(value = "/getAdministrativePenaltyList",method = RequestMethod.POST)
    public Map<String, Object> getAdministrativePenaltyList(TtsScltxxcjRegiter entity, String taskYear, String dateBegin, String area,
                                                            String dateEnd, int start, int length, String queryCon,@RequestHeader  String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        PageInfo<AlesProduceAdminPunish> pageInfo = produceAdminPunishService.getProduceAdminPunishList(entity,taskYear,dateBegin,area,dateEnd,
                ((start+1)/length)+1,length,queryCon);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }

    /**
     * 导出行政处罚数据
     */
    @ApiOperation(value = "导出行政处罚任务数据")
    @SystemControllerLog(description = "导出行政处罚任务数据", operationType = "查询")
    @RequestMapping(value = "/exportSubjSv")
    public void exportSubjSv(TtsScltxxcjRegiter entity, String taskYear,String dateBegin,String area,String dateEnd,
                             String queryCon)  {
        List<AlesProduceAdminPunishDto> list = produceAdminPunishService.getAllProduceAdminPunishList(entity, taskYear, dateBegin, area, dateEnd, queryCon);
        List<List<String>> datas=new ArrayList<>();
        //表头
        List<String> dataTitle=new ArrayList<>();
        dataTitle.add("行政处罚决定案号");
        dataTitle.add("当事主体");
        dataTitle.add("当事主体身份码");
        dataTitle.add("所属行业");
        dataTitle.add("主体类型");
        dataTitle.add("当事人");
        dataTitle.add("案件名称");
        dataTitle.add("违法产品名称");
        dataTitle.add("标称生产企业");
        dataTitle.add("生产日期或批次");
        dataTitle.add("案件定性");
        dataTitle.add("行政处罚决定");
        dataTitle.add("决定书");
        dataTitle.add("执法单位");
        dataTitle.add("执法人员");
        datas.add(dataTitle);
        //遍历
        for (AlesProduceAdminPunishDto a:list){
            List<String> data=new LinkedList<>();
            data.add(a.getPunishCode());
            data.add(a.getEnterpriseName());
            data.add(a.getEnterpriseCode());
            data.add(a.getEntityIndustryName());
            data.add(a.getEntityTypeName());
            data.add(a.getLegalPerson());
            data.add(a.getCaseName());
            data.add(a.getProductName());
            data.add(a.getEnterpriseName());
            data.add(a.getManufactureDate());
            data.add(a.getPunishQualitative());
            data.add(a.getPunishResult());
            data.add(a.getPunishFilesName());
            data.add(a.getEnforceLawId());
            data.add(a.getEnforceLawPeople());
            datas.add(data);
        }
        try {
            DownloadExcelUtil.exportDataByExcel("行政处罚",null,datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 1
     * 根据ID获取单个行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个行政处罚")
    @SystemControllerLog(description = "获取单个行政处罚",operationType="查询")
    @RequestMapping(value = "/findAdministrativePenaltyById",method = RequestMethod.POST)
    public Map<String, Object> findAdministrativePenaltyById(@RequestBody AlesProduceAdminPunish produceAdminPunish){
        produceAdminPunish = produceAdminPunishService.findProduceAdminPunish(produceAdminPunish.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("produceAdminPunish",produceAdminPunish);
        return map;
    }
    /**
     * 暂加的根据ID获取生产经营主体enterpriseId
     * @param
     * @return
     */
    @ApiOperation(value = "根据ID获取生产经营主体")
    @SystemControllerLog(description = "根据ID获取生产经营主体",operationType="查询")
    @RequestMapping(value = "/findEnterpriseById",method = RequestMethod.POST)
    public Map<String,Object> findEnterpriseById( String entityIdCode){
        Map<String,Object> map = new HashMap<>();
        map.put(ApiConstants.CODE,ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("enterprise",produceAdminPunishService.findEnterpriseById(entityIdCode));
        return map;
    }

    /**
     * 删除行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "删除行政处罚")
    @RequestMapping(value = "/deleteAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> deleteAdministrativePenalty(){
        return null;
    }
    /**
     * 打印行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "打印行政处罚")
    @RequestMapping(value = "/printAdministrativePenalty",method = RequestMethod.POST)
    public Map<String, Object> printAdministrativePenalty(){
        return null;
    }

    @ApiOperation(value = "根据ID获取生产经营主体的不良记录列表")
    @SystemControllerLog(description = "根据ID获取生产经营主体的不良记录列表",operationType="查询")
    @RequestMapping(value = "/getAsmsSubjEntBadrecordByIdList",method = RequestMethod.POST)
    public Object getAsmsSubjEntBadrecordByIdList(Page page, String enterpriseId){
        PageInfo pageInfo = produceAdminPunishService.getAsmsSubjEntBadrecordByIdList(page, enterpriseId);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 文件上传
     */
    @ApiOperation(value = "文件上传")
    @SystemControllerLog(description = "文件上传",operationType = "上传")
//    @RequiresPermissions("asms.smtask.fileUpload")
    @RequestMapping(value = "/fileUpload")
    public Object fileUpload(HttpServletRequest request) {
        String address = "";
        try {
            address = super.uploadFile(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setSuccessModelMap(new ModelMap(), address);
    }
    /**
     * 2
     * 根据ID获取单个行政处罚
     * @param
     * @return
     */
    @ApiOperation(value = "获取单个行政处罚")
    @SystemControllerLog(description = "获取单个行政处罚",operationType="查询")
    @RequestMapping(value = "/getPunishiById",method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> getPunishiById(String id,@RequestHeader String token){
        Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ApiConstants.CODE, ApiMsgConstants.TOKEN_ILLEGAL_CODE);
            return map;
        }
        AlesProduceAdminPunish produceAdminPunish = produceAdminPunishService.getPunishById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("produceAdminPunish",produceAdminPunish);
        return map;
    }
}
