package com.sofn.web.qry;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;
import com.sofn.service.qry.TraceQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */
@RestController
@Api(value = "追溯码查询", description = "追溯码查询")
@RequestMapping(value = "/traceQuery", method = RequestMethod.POST)
public class TraceQueryController extends BaseController {
    @Autowired
    public TraceQueryService traceQueryService;

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
        return (ForceData) traceQueryService.getBytraceOrBatchCode(traceCode, 2);
    }

    /**
     * 追溯码查询销售链条(树图)
     *
     * @param traceCode 追溯码
     * @return
     */
    @ApiOperation(value = "追溯码查询销售链条(树图)")
    @SystemControllerLog(description = "追溯码查询销售链条(树图)", operationType = "追溯码查询销售链条(树图)")
    @RequestMapping(value = "/getTreeDataByTraceCode/{traceCode:.+}", method = RequestMethod.POST)
    public Map<String, SaleInfoData> getTreeDataByTraceCode(@PathVariable String traceCode) {
        return (Map<String, SaleInfoData>) traceQueryService.getBytraceOrBatchCode(traceCode, 1);
    }

}
