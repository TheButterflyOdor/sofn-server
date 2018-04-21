package com.sofn.web.mobile;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.model.generator.AdsCheckInfo;
import com.sofn.model.generator.AdsMonitorSample;
import com.sofn.model.generator.AdsMonitorTask;
import com.sofn.service.ads.AdsCheckInfoService;
import com.sofn.service.ads.AdsMonitorSampleService;
import com.sofn.service.ads.AdsMonitorTaskService;
import com.sofn.service.ads.AdsOrganTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2016-11-29.
 */
@RestController
@Api(value = "移动端抽样信息数据接口", description = "移动端抽样信息数据接口")
@RequestMapping(value = "/adsMobileMonitorSample",method = RequestMethod.POST)
public class AdsMobileMonitorSampleController extends BaseController {
    @Autowired
    private AdsMonitorSampleService adsMonitorSampleService;
    @Autowired
    private AdsCheckInfoService     adsCheckInfoService;
    @Autowired
    public AdsMonitorTaskService adsMonitorTaskService;
    /**
     * 新增监测抽样单记录
     *
     * @param adsMonitorSample
     * @return
     */
    @ApiOperation(value = "新增监测抽样单数据")
    @RequestMapping(value = "/insertAdsMonitorSample", method = RequestMethod.POST)
    public Object insertAdsMonitorSample(@RequestBody AdsMonitorSample adsMonitorSample) {
        try {
            String monitorTaskId = adsMonitorSample.getMonitorTaskId();
            String sampleName = adsMonitorSample.getSampleName();
            String sampleCode = adsMonitorSample.getSampleCode();
            AdsMonitorTask adsMonitorTask = adsMonitorTaskService.queryById(monitorTaskId);
            String[] CheckObject = adsMonitorTask.getCheckObject().split("、");
            List<String> list = Arrays.asList(CheckObject);
            boolean contains = list.contains(sampleName);//包含样品名称
            if(!contains){
                throw new IllegalParameterException("检测对象不存在");
            }
            AdsMonitorSample ads=adsMonitorSampleService.findAdsMonitorSample(sampleCode);
            if(null!=ads){
                throw new IllegalParameterException("样品编码已经存在");
            }
            String uuid = UUID.randomUUID().toString();
            String id = uuid.replace("-", "");
            adsMonitorSample.setId(id);
            String productTraceabilityCode = adsMonitorSample.getProductTraceabilityCode();//产品编码
            IdGenerator idGen = new IdGenerator();
            String getProductTraceabilityCode = idGen.getProductTemporaryCode(IdGenerator.ServiceType.Retrospect,
                    IdGenerator.MainBodyCategories.MainManagementBody,
                    IdGenerator.OrganizationForms.Individual,
                    productTraceabilityCode, "12345678");//临时码
            adsMonitorSample.setProductTraceabilityCode(getProductTraceabilityCode);
            adsMonitorSampleService.addAdsMonitorSample(adsMonitorSample);
            AdsCheckInfo adsCheckInfo = new AdsCheckInfo();
            adsCheckInfo.setOrganTaskId(adsMonitorSample.getOrganTaskId());
            adsCheckInfo.setSampleBarCode(adsMonitorSample.getSampleCode());
            adsCheckInfo.setMonitorSampleId(id);
            adsCheckInfo.setCheckOrganId(adsMonitorSample.getSampleOrganId());
            adsCheckInfo.setCheckLink("1");
            long time = new Date().getTime();
            adsCheckInfo.setCheckTime(new Timestamp(time));
            adsCheckInfo.setResult("-1");
            adsCheckInfo.setCheckReport("0");
            adsCheckInfo.setOrganTaskId(adsMonitorSample.getOrganTaskId());
            adsCheckInfo.setTestedDeparment(adsMonitorSample.getTestedDeparment());
            adsCheckInfo.setSampleDeparment(adsMonitorSample.getSampleDeparment());
            adsCheckInfo.setCheckOrgan(adsMonitorSample.getSampleOrgan());
            adsCheckInfo.setSampleCode(adsMonitorSample.getSampleCode());
            adsCheckInfo.setSampleName(adsMonitorSample.getSampleName());
            adsCheckInfo.setProductTraceabilityCode(getProductTraceabilityCode);
            adsCheckInfoService.add(adsCheckInfo);
            return setSuccessModelMap(new ModelMap(), HttpCode.OK);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR,e.toString());
        }
    }


    @ApiOperation(value = "获取监测抽样单信息列表")
    @RequestMapping(value = "/getSamplePageInfo", method = RequestMethod.POST)
    public Object getSamplePageInfo(AdsMonitorSample adsMonitorSample, int start, int length) {
        PageInfo<AdsMonitorSample> pageInfo = adsMonitorSampleService.getPageInfo(adsMonitorSample, ((start + 1) / length) + 1, length);
        return pageInfo;

    }

    @ApiOperation(value = " 查询抽样单详情")
    @RequestMapping(value = "/findAdsMonitorSampleById", method = RequestMethod.POST)
    public Object findAdsMonitorSampleById(String sampleCode,String organTaskId) {
        try {
            Map<String, Object> map = adsMonitorSampleService.findAdsMonitorSampleById(sampleCode,organTaskId);
            return setModelMap(new ModelMap(), HttpCode.OK, map);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


}
