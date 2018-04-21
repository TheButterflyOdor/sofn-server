package com.sofn.service.qry;

import com.alibaba.fastjson.JSONObject;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * Created by sofn on 2017/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class ZsmQueryServiceTest {
    @Autowired
    private ZsmQueryService zsmQueryService;

    @Test
    public void getSubjectSaleInfoByTraceCode() throws Exception {
        String traceCodeType = "0";
        String traceCode = "1.2.156.326.8.1.3.1.1.123456998745622222.010101.20170116172059";
        Map<String, SaleInfoData> subjectSaleInfoDtos = zsmQueryService.getSubjectSaleInfoByTraceCode(traceCodeType, traceCode);
        System.out.println("*************************************************");
        System.out.println("******************upstream上游*******************************");
        System.out.println(JSONObject.toJSONString(subjectSaleInfoDtos.get("rootUp")));
        System.out.println("******************downstream下游*******************************");
        System.out.println(JSONObject.toJSONString(subjectSaleInfoDtos.get("rootDown")));
        System.out.println("*************************************************");
    }

    @Test
    public void findSaleRelationByTraceCode() throws Exception {
        String traceCode = "1.2.156.326.8.1.3.1.1.123456998745622222.010101.20170116172059";
        ForceData forceData = zsmQueryService.findSaleRelationByTraceCode(traceCode);
        System.out.println("*************************************************");
        System.out.println("******************节点数据*******************************");
        System.out.println(JSONObject.toJSONString(forceData.getData()));
        System.out.println("******************线数据*******************************");
        System.out.println(JSONObject.toJSONString(forceData.getLinks()));
        System.out.println("*************************************************");
    }
}