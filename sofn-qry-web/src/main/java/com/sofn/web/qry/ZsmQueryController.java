package com.sofn.web.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;
import com.sofn.service.qry.ZsmQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
@RestController
@Api(value = "追溯码查询", description = "追溯码查询")
@RequestMapping(value = "/zsm", method = RequestMethod.POST)
public class ZsmQueryController extends BaseController {
    @Autowired
    public ZsmQueryService zsmQueryService;

    /**
     * 查询追溯码和主体身份码在采购管理中是否存在数据
     *
     * @param zsm
     * @return
     */
    @ApiOperation(value = "查询追溯码和主体身份码在采购管理中是否存在数据")
    @RequestMapping(value = "/zsmIsCgOrXs", method = RequestMethod.POST)
    public Object zsmIsCgOrXs(String zsm, String entity_id) {
        try {
            long result = zsmQueryService.zsmIsCgOrXs(zsm, entity_id);
            return setSuccessModelMap(new ModelMap(), result);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 追溯查询，本级是销售
     *
     * @return
     */
    @ApiOperation(value = "追溯码获取下游追溯信息")
    @SystemControllerLog(description = "追溯码信息", operationType = "查询下游")
    @RequestMapping(value = "/existsTrace", method = RequestMethod.POST)
    public Object existsTrace(String zsm, String entityId) {
        try {
            long result = zsmQueryService.existsTrace(zsm, entityId);
            return setSuccessModelMap(new ModelMap(), result);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 追溯码获取下游追溯信息
     *
     * @param start
     * @param length
     * @param fromzsm tozsm
     * @return
     */
    @ApiOperation(value = "追溯码获取下游追溯信息")
    @SystemControllerLog(description = "追溯码信息", operationType = "查询下游")
    @RequestMapping(value = "/getDownZsm", method = RequestMethod.POST)
    public Map<String, Object> getDownZsm(int start, int length, String fromzsm, String tozsm) {
        PageInfo<Map<String, Object>> pageInfo = zsmQueryService.getDownZsm(((start + 1) / length) + 1, length, fromzsm, tozsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    /**
     * 追溯码获取本级追溯信息
     *
     * @param zsm
     * @return
     */
    @ApiOperation(value = "追溯码获取本级追溯信息")
    @SystemControllerLog(description = "追溯码信息", operationType = "查询本级")
    @RequestMapping(value = "/getBaseZsm", method = RequestMethod.POST)
    public Map<String, Object> getBaseZsm(String zsm) {
        TtsScltxxcjCgglAndCustomer vo = zsmQueryService.getBaseZsm(zsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("base", vo);
        return map;
    }

    /**
     * 追溯码获取上级追溯信息
     *
     * @param zsm
     * @return
     */
    @ApiOperation(value = "追溯码获取上级追溯信息")
    @SystemControllerLog(description = "追溯码信息", operationType = "查询上级")
    @RequestMapping(value = "/getUpZsm", method = RequestMethod.POST)
    public Map<String, Object> getUpZsm(String zsm) {
        TtsScltxxcjXsdjAndCustomer vo = zsmQueryService.getUpZsm(zsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("up", vo);
        return map;
    }

    /**
     * 销售是本级的时候，查询上级追溯信息
     *
     * @param start
     * @param length
     * @param zsm
     * @return
     */
    @ApiOperation(value = "销售是本级的时候，查询上级追溯信息")
    @SystemControllerLog(description = "追溯码信息", operationType = "查询销售本级")
    @RequestMapping(value = "/getXsUpZsm", method = RequestMethod.POST)
    public Map<String, Object> getXsUpZsm(int start, int length, String zsm) {
        PageInfo<Map<String, Object>> pageInfo = zsmQueryService.getXsUpZsm(((start + 1) / length) + 1, length, zsm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    /**
     * 追溯查询，本级是销售
     *
     * @param start
     * @param length
     * @param fromzsm tozsm
     * @return
     */
    @ApiOperation(value = "追溯码获取下游追溯信息")
    @SystemControllerLog(description = "追溯码信息", operationType = "查询下游")
    @RequestMapping(value = "/getXsbaseZsm", method = RequestMethod.POST)
    public Map<String, Object> getXsbaseZsm(int start, int length, String fromzsm, String tozsm, String entityId) {
        PageInfo<Map<String, Object>> pageInfo = zsmQueryService.getXsbaseZsm(((start + 1) / length) + 1, length, fromzsm, tozsm, entityId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    /**
     * 通过批次信息查询对应上级产品信息
     *
     * @param pc
     * @param status
     * @param billStatus
     * @param start
     * @param length
     * @param fromzsm
     * @return
     */
    @ApiOperation(value = "批次码获取上级产品信息")
    @RequestMapping(value = "/getTopForPc", method = RequestMethod.POST)
    public Map<String, Object> getTopForPc(String pc, String status, String billStatus, int start, int length, String fromzsm) {
        PageInfo<Map<String, Object>> pageInfo = null;
        if ("1".equals(billStatus)) {
            pageInfo = zsmQueryService.getIfhc(((start + 1) / length) + 1, length, pc);
        } else {
            pageInfo = zsmQueryService.getTopForPc(pc, status, billStatus, ((start + 1) / length) + 1, length, fromzsm);
        }
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    /**
     * 追溯码获取本级追溯信息
     *
     * @param pc
     * @return
     */
    @ApiOperation(value = "批次码获取本级产品信息")
    @RequestMapping(value = "/getBaseForPc", method = RequestMethod.POST)
    public Map<String, Object> getBaseForPc(String pc, String entityId) {
        Map<String, Object> vo = zsmQueryService.getBaseForPc(pc, entityId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("base", vo);
        return map;
    }

    @ApiOperation(value = "批次码获取下游产品信息")
    @RequestMapping(value = "/getDownForPc", method = RequestMethod.POST)
    public Map<String, Object> getDownForPc(String pc, String entityId, int start, int length) {
        PageInfo<Map<String, Object>> pageInfo = zsmQueryService.getInfoforpc(((start + 1) / length) + 1, length, pc, entityId);
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    @ApiOperation(value = "批次码合成后流向")
    @RequestMapping(value = "/getHclx", method = RequestMethod.POST)
    public Map<String, Object> getHclx(String pc) {
        Map<String, Object> vo = zsmQueryService.getHclx(pc);
        //
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", vo);
        return map;
    }

    /*无权限追溯码查询*/

    /**
     * 追溯码查询销售链条(力向图)
     *
     * @param traceCode 追溯码
     * @return
     */
    @ApiOperation(value = "追溯码查询销售链条(力向图)")
    @SystemControllerLog(description = "追溯码查询销售链条(力向图)", operationType = "追溯码查询销售链条(力向图)")
    @RequestMapping(value = "/getForceDataByTraceCode/{traceCode:.+}", method = RequestMethod.POST)
    public ForceData getForceDataByTraceCode(@PathVariable String traceCode) {
        return zsmQueryService.findSaleRelationByTraceCode(traceCode);
    }

    /**
     * 追溯码查询销售链条
     *
     * @param traceCodeType 追溯码类型：0追溯码，1批次码
     * @return
     */
    @ApiOperation(value = "追溯码查询销售链条")
    @SystemControllerLog(description = "追溯码查询销售链条", operationType = "追溯码查询销售链条")
    @RequestMapping(value = "/getByTrace/{traceCodeType}/{traceCode:.+}", method = RequestMethod.POST)
    public Map<String, SaleInfoData> getByTraceCode(@PathVariable String traceCodeType, @PathVariable String traceCode) {
        return zsmQueryService.getSubjectSaleInfoByTraceCode(traceCodeType, traceCode);
    }
}
