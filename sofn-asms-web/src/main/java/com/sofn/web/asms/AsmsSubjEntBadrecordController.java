package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.model.generator.AsmsSubjEntBadrecord;
import com.sofn.service.asms.AsmsSubjEntBadrecordService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@RestController
@Api(value = "不良记录", description = "不良记录")
@RequestMapping(value = "/subjEntBadrecord",method = RequestMethod.POST)
public class AsmsSubjEntBadrecordController extends BaseController {
    @Autowired
    private AsmsSubjEntBadrecordService asmsSubjEntBadrecordService;

    @ApiOperation(value = "获取不良记录信息列表")
    @RequestMapping(value = "/getAsmsSubjEntBadrecordList", method = RequestMethod.POST)
    @Authorization
    public Map<String, Object> getAsmsSubjEntBadrecordList(@RequestHeader String token, AsmsSubjEntBadrecord asmsSubjEntBadrecord, String area, String entityIndustry, String entityType, int start, int length, String queryCon) {
        PageInfo<AsmsSubjEntBadrecord> pageInfo = asmsSubjEntBadrecordService.getAsmsSubjEntBadrecordList(asmsSubjEntBadrecord,area,entityIndustry,entityType,
                ((start+1)/length)+1,length,queryCon);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }
    @ApiOperation(value = "获取单个不良记录详情")
    @RequestMapping(value = "/findBaseInspectionById",method = RequestMethod.POST)
    @Authorization
    public Object findBaseInspectionById(@RequestHeader String token,@RequestBody AsmsSubjEntBadrecord asmsSubjEntBadrecord){
        asmsSubjEntBadrecord = asmsSubjEntBadrecordService.findBadrecordById(asmsSubjEntBadrecord.getId());
        return setSuccessModelMap(new ModelMap(),asmsSubjEntBadrecord);
    }
    @ApiOperation(value = "根据IDCODE获取生产经营主体")
    @RequestMapping(value = "/findEnterpriseById",method = RequestMethod.POST)
    @Authorization
    public Map<String,Object> findEnterpriseById( String entityIdCode){
        Map<String,Object> map = new HashMap<>();
        map.put(ApiConstants.CODE,ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("enterprise",asmsSubjEntBadrecordService.findEnterpriseById(entityIdCode));
        return map;
    }
    @ApiOperation(value = "根据IDCODE获取临时生产经营主体")
    @RequestMapping(value = "/findEnterpriseTmpById",method = RequestMethod.POST)
    @Authorization
    public Map<String,Object> findEnterpriseTmpById(@RequestHeader String token, String entityIdCode){
        Map<String,Object> map = new HashMap<>();
        map.put(ApiConstants.CODE,ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("dataSubjEntTemp",asmsSubjEntBadrecordService.findEnterpriseTmpById(entityIdCode));
        return map;
    }
    @ApiOperation(value = "根据ID获取生产经营主体的不良记录列表")
    @RequestMapping(value = "/getAsmsSubjEntBadrecordByIdList",method = RequestMethod.POST)
    @Authorization
    public Object getAsmsSubjEntBadrecordByIdList(@RequestHeader String token,Page page, String enterpriseId){
        PageInfo pageInfo = asmsSubjEntBadrecordService.getAsmsSubjEntBadrecordByIdList(page, enterpriseId);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }
    //仅处理sourceType为2,3,4,类型为1从页面调用已有监管接口
    @ApiOperation(value = "根据不良记录类型和id获取不良记录详情")
    @RequestMapping(value = "findSubjEntBadcordDetail")
    @Authorization
    public Object findSubjEntBadcordDetail(@RequestHeader(value="token", required = true)String token,
                                           @ApiParam(required = true, value = "不良记录来源ID") @RequestParam(value="sourceId", required = true)String sourceId,
                                           @ApiParam(required = true, value = "不良记录来源类型") @RequestParam(value="sourceType", required = true)String sourceType){
        return asmsSubjEntBadrecordService.findSubjEntBadcordDetail(sourceId, sourceType);
    }
}
