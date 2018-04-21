package com.sofn.web.tts;

import com.alibaba.dubbo.common.json.JSON;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.util.DateUtil;
import com.sofn.model.generator.TtsScltxxcjCgglAndUserInfo;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjScglAndUserInfo;
import com.sofn.model.generator.TtsScltxxcjXsdjAndUserInfo;
import com.sofn.model.tts.SamplingInfo;
import com.sofn.service.tts.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
@RestController
@Api(value = "二维码条件查询", description = "二维码条件查询")
@RequestMapping(value = "/query", method = RequestMethod.POST)
public class QueryController extends BaseController {

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;

    @Autowired
    public TtsScltxxcjXsdjService ttsScltxxcjXsdjService;

    @Autowired
    public TtsScltxxcjCgglService ttsScltxxcjCgglService;
    @Autowired
    public QueryInfoService queryInfoService;

    /**
     * 根据不同的用户扫描不同的码获取注册主体信息表列表
     *
     * @param code
     * @param userType
     * @return
     */
    @ApiOperation(value = "根据不同的用户扫描不同的码获取注册主体信息表列表")
    @RequestMapping(value = "/getSubjectInfo", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据不同的用户扫描不同的码获取注册主体信息表列表", operationType = "查询")
    public Map<String, Object> getSubjectInfo(String code, String userType) {
        Map<String, Object> map = new HashMap<String, Object>();
        //判断传入的是什么类型的码，MainBodyIdentityCode：主体身份码，MainBodyUserCode：主体用户码，
        // ProductBatchCode：产品批次码，ProductTraceabilityCode：产品追溯码，IntoCityTraceability：入市追溯凭证
        String codeType = IdGenerator.getEncodingPropertie(code).toString().trim();
        if (codeType.equals("MainBodyIdentityCode")) {
            TtsScltxxcjRegiter ttsScltxxcjRegiter = ttsScltxxcjRegiterService.getSubjectInfo(userType, code);
            map.put("data", ttsScltxxcjRegiter);
        } else if (codeType.equals("MainBodyUserCode")) {
            TtsScltxxcjRegiter ttsScltxxcjRegiter = ttsScltxxcjRegiterService.getInfoByUserId(userType, code);
            map.put("data", ttsScltxxcjRegiter);
        } else if (codeType.equals("ProductBatchCode")) {
            TtsScltxxcjScglAndUserInfo ttsScltxxcjScglAndUserInfo = ttsScltxxcjScglService.getInfoByPcCode(userType, code);
            map.put("data", ttsScltxxcjScglAndUserInfo);
        } else if (codeType.equals("ProductTraceabilityCode")) {
            //判断是否存在与采购表中，若存在，则存在上游的说法
            long result = ttsScltxxcjCgglService.isExists(code);
            if (result > 0) {
                TtsScltxxcjCgglAndUserInfo ttsScltxxcjCgglAndUserInfo = ttsScltxxcjCgglService.getInfoByTraceCodeFromCg(userType, code);
                map.put("data", ttsScltxxcjCgglAndUserInfo);
            } else {
                map.put("data", "追溯码不存在！");
            }
        } else if (codeType.equals("IntoCityTraceability")) {
            TtsScltxxcjXsdjAndUserInfo ttsScltxxcjXsdjAndUserInfo = ttsScltxxcjXsdjService.getInfoByRsCode(userType, code);
            map.put("data", ttsScltxxcjXsdjAndUserInfo);
        }
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    /**
     * 根据传入的code和用户类型查询相关信息（测试新接口）
     *
     * @param code
     * @param userType
     * @return
     */
    @ApiOperation(value = "根据传入的code和用户类型查询相关信息")
    @RequestMapping(value = "/interfaceTest", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据不同的用户扫描不同的码获取注册主体信息表列表", operationType = "查询")
    public Map<String, Object> interfaceTest(String code, String userType) {
        List<Map<String, Object>> list = new ArrayList<>();
        list = queryInfoService.getInfoTest(userType, code);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("list", list);
        return map;
    }

    /**
     * 根据不同的码获取例行监测抽样单信息
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "根据不同的码获取例行监测抽样单信息")
    @PostMapping(value = "/getSampleSingleInfo/{code:.+}")
    @SystemControllerLog(description = "根据不同的码获取例行监测抽样单信息", operationType = "查询")
    public Object getSampleSingleInfo(@PathVariable String code) {
        //判断传入的是什么类型的码
        // production：产品批次码，circulate：产品追溯码，enterMarket：入市追溯凭证
        IdGenerator.TracingCodeType codeType = null;
        try {
            codeType = IdGenerator.getTracingCodeType(code);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        SamplingInfo samplingInfo = null;//抽样信息
        if (IdGenerator.TracingCodeType.production.equals(codeType)) {
            samplingInfo = queryInfoService.getSamplingInfoByBatchCode(code);
        } else if (IdGenerator.TracingCodeType.circulate.equals(codeType)) {
            samplingInfo = queryInfoService.getSamplingInfoByTraceCode(code);
        } else if (IdGenerator.TracingCodeType.enterMarket.equals(codeType)) {
            samplingInfo = queryInfoService.getSamplingInfoByTraceProof(code);
        } else {
            return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
        if (samplingInfo == null) {
            return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
        }
        return setSuccessModelMap(new ModelMap(), samplingInfo);
    }

    /**
     * 根据追溯、批次短码获取最终生产产品及主体信息
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "根据追溯、批次短码获取最终生产产品及主体信息")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "参数错误.", response = IllegalParameterException.class),
            @ApiResponse(code = 500, message = "服务器出错.", response = DataParseException.class)
    })
    @GetMapping(value = "/getProAndSubInfo/{code}")
    @CrossOrigin
    @SystemControllerLog(description = "根据追溯、批次短码获取最终生产产品及主体信息", operationType = "查询")
    public Object getProAndSubInfo(@PathVariable("code") String code) {
        Map map = queryInfoService.getInfoByTraceCode(code);
        if (map == null) {
            return setModelMap(new ModelMap(), HttpCode.OK, "无此记录信息.");
        }
        return setSuccessModelMap(new ModelMap(), map);
    }

    /**
     * 根据追溯、批次短码获取最终生产产品及主体信息（解决兼容性问题）
     *
     * @param code
     * @return
     */
    @ApiOperation(value = "根据追溯、批次短码获取最终生产产品及主体信息")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "参数错误.", response = IllegalParameterException.class),
            @ApiResponse(code = 500, message = "服务器出错.", response = DataParseException.class)
    })
    @GetMapping(value = "/getProAndSubInfoByJsonp")
    @CrossOrigin
    @SystemControllerLog(description = "根据追溯、批次短码获取最终生产产品及主体信息", operationType = "查询")
    public void getProAndSubInfoByJsonp(String code, String callback ,HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String result = "";
        Map map = queryInfoService.getInfoByTraceCode(code);
        if (map == null) {
            result = "{\"httpCode\":"+ HttpCode.OK +",\"msg\": \"无此记录信息.\"}";
        }else {
            for (Object key : map.keySet()) {
                if (null != map.get(key) && map.get(key) instanceof Date) {
                    Date date = (Date) map.get(key);
                    map.put(key,DateUtil.format(date));
                }
            }
            result = JSON.json(map);
        }
        result=callback+"("+result+")";
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
