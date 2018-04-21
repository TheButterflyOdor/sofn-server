package com.sofn.freemarker;

import com.sofn.core.util.FreeMarkerUtil;
import com.sofn.model.generator.AdsMonitorTask;

import javax.servlet.ServletContext;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成word文档
 * @author yangran
 */
public class CreateDoc {

    /**
     * 生成监测任务总览word文档数据
     * @param adsMonitorTask
     * @param context
     * @return
     * @throws UnsupportedEncodingException
     */
    public static ByteArrayOutputStream createAdsTaskDoc(AdsMonitorTask adsMonitorTask, ServletContext context) throws UnsupportedEncodingException {
        BigDecimal b = new BigDecimal(0);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("taskName", adsMonitorTask.getTaskName()!=null ? adsMonitorTask.getTaskName():"");
        root.put("releaseUnit",adsMonitorTask.getReleaseUnit()!=null ? adsMonitorTask.getReleaseUnit():"");
        root.put("year",adsMonitorTask.getYear()!=null ? adsMonitorTask.getYear():"");
        root.put("startTime",adsMonitorTask.getStartTime()!=null ? adsMonitorTask.getStartTime():"");
        root.put("endTime",adsMonitorTask.getEndTime()!=null ? adsMonitorTask.getEndTime():"");
        root.put("deadline",adsMonitorTask.getDeadline()!=null ? adsMonitorTask.getDeadline():"");
        root.put("monitorClass",adsMonitorTask.getMonitorClass()!=null ? adsMonitorTask.getMonitorClass():"");
        root.put("industry",adsMonitorTask.getIndustry()!=null ? adsMonitorTask.getIndustry():"");
        root.put("separation",b.equals(adsMonitorTask.getSeparation())? "否" : "是");  //抽检分离
        root.put("attachment",adsMonitorTask.getAttachment()!=null ? adsMonitorTask.getAttachment():"无");
        root.put("comment",adsMonitorTask.getComment()!=null ? adsMonitorTask.getComment():"");
        root.put("sampleLink",adsMonitorTask.getSampleLink()!=null ? adsMonitorTask.getSampleLink():"");
        root.put("checkObject",adsMonitorTask.getCheckObject()!=null ? adsMonitorTask.getCheckObject():"");
        root.put("checkProject",adsMonitorTask.getCheckProject()!=null ? adsMonitorTask.getCheckProject():"");
        root.put("organ_list",adsMonitorTask.getAdsOrganTaskList()!=null ? adsMonitorTask.getAdsOrganTaskList():"");
        FreeMarkerUtil freemk = new FreeMarkerUtil("/template/doc/", "task.ftl");
        return freemk.processTemplate(root,context);
    }
    public static ByteArrayOutputStream createAdsSampleDoc(Map<String, Object> map, ServletContext context) throws UnsupportedEncodingException {
        BigDecimal b = new BigDecimal(0);
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("monitorClass", map.get("monitorClass")!=null ? map.get("monitorClass"):"");
        root.put("PRODUCT_TRACEABILITY_CODE", map.get("PRODUCT_TRACEABILITY_CODE")!=null ? map.get("PRODUCT_TRACEABILITY_CODE"):"");
        root.put("SAMPLE_CODE", map.get("SAMPLE_CODE")!=null ? map.get("SAMPLE_CODE"):"");
        root.put("SAMPLE_NAME", map.get("SAMPLE_NAME")!=null ? map.get("SAMPLE_NAME"):"");
        root.put("SAMPLE_CODE", map.get("SAMPLE_CODE")!=null ? map.get("SAMPLE_CODE"):"");
        root.put("TRADE_MARK", map.get("TRADE_MARK")!=null ? map.get("TRADE_MARK"):"");
        root.put("PACKING", map.get("PACKING")!=null ? map.get("PACKING"):"");
        root.put("GRADE", map.get("GRADE")!=null ? map.get("GRADE"):"");
        root.put("IDENTIFY", map.get("IDENTIFY")!=null ? map.get("IDENTIFY"):"");
        root.put("SPECIFICATION", map.get("SPECIFICATION")!=null ? map.get("SPECIFICATION"):"");
        root.put("STANDARD", map.get("STANDARD")!=null ? map.get("STANDARD"):"");
        root.put("PRODUCE_DATE", map.get("PRODUCE_DATE")!=null ? map.get("PRODUCE_DATE"):"");
        root.put("PRODUCING_AREA_NAME", map.get("PRODUCING_AREA_NAME")!=null ? map.get("PRODUCING_AREA_NAME"):"");
        root.put("PRODUCE_CERTIFICATE", map.get("PRODUCE_CERTIFICATE")!=null ? map.get("PRODUCE_CERTIFICATE"):"");
        root.put("CERTIFICATE_CODE", map.get("CERTIFICATE_CODE")!=null ? map.get("CERTIFICATE_CODE"):"");
        root.put("SAMPLE_NUMBER", map.get("SAMPLE_NUMBER")!=null ? map.get("SAMPLE_NUMBER"):"");
        root.put("SAMPLE_NUMBER_UNIT", map.get("SAMPLE_NUMBER_UNIT")!=null ? map.get("SAMPLE_NUMBER_UNIT"):"");
        root.put("SAMPLE_BASE", map.get("SAMPLE_BASE")!=null ? map.get("SAMPLE_BASE"):"");
        root.put("SAMPLE_BASE_UNIT", map.get("SAMPLE_BASE_UNIT")!=null ? map.get("SAMPLE_BASE_UNIT"):"");
        root.put("SAMPLE_PLACE", map.get("SAMPLE_PLACE")!=null ? map.get("SAMPLE_PLACE"):"");
        root.put("TESTED_DEPARMENT", map.get("TESTED_DEPARMENT")!=null ? map.get("TESTED_DEPARMENT"):"");
        root.put("TESTED_ADDRESS", map.get("TESTED_ADDRESS")!=null ? map.get("TESTED_ADDRESS"):"");
        root.put("TESTED_LEGALREP", map.get("TESTED_LEGALREP")!=null ? map.get("TESTED_LEGALREP"):"");
        root.put("TESTED_LINKMAN", map.get("TESTED_LINKMAN")!=null ? map.get("TESTED_LINKMAN"):"");
        root.put("TESTED_LINKMANPHONE", map.get("TESTED_LINKMANPHONE")!=null ? map.get("TESTED_LINKMANPHONE"):"");
        root.put("TESTED_LINKMANFAX", map.get("TESTED_LINKMANFAX")!=null ? map.get("TESTED_LINKMANFAX"):"");
        root.put("TESTED_PERSON", map.get("TESTED_PERSON")!=null ? map.get("TESTED_PERSON"):"");
        root.put("TESTED_PERSONPHONE", map.get("TESTED_PERSONPHONE")!=null ? map.get("TESTED_PERSONPHONE"):"");
        root.put("TESTED_PERSONFAX", map.get("TESTED_PERSONFAX")!=null ? map.get("TESTED_PERSONFAX"):"");
        root.put("PRODUCTIONS_TATUS", map.get("PRODUCTIONS_TATUS")!=null ? map.get("PRODUCTIONS_TATUS"):"");
        root.put("PRODUCTION_DEPARMENT", map.get("PRODUCTION_DEPARMENT")!=null ? map.get("PRODUCTION_DEPARMENT"):"");
        root.put("PRODUCTION_ADDRESS", map.get("PRODUCTION_ADDRESS")!=null ? map.get("PRODUCTION_ADDRESS"):"");
        root.put("PRODUCTION_ZIPCODE", map.get("PRODUCTION_ZIPCODE")!=null ? map.get("PRODUCTION_ZIPCODE"):"");
        root.put("PRODUCTION_LINKMAN", map.get("PRODUCTION_LINKMAN")!=null ? map.get("PRODUCTION_LINKMAN"):"");
        root.put("PRODUCTION_LINKMANPHONE", map.get("PRODUCTION_LINKMANPHONE")!=null ? map.get("PRODUCTION_LINKMANPHONE"):"");
        root.put("PRODUCTION_LINKMANFAX", map.get("PRODUCTION_LINKMANFAX")!=null ? map.get("PRODUCTION_LINKMANFAX"):"");
        root.put("SAMPLE_DEPARMENT", map.get("SAMPLE_DEPARMENT")!=null ? map.get("SAMPLE_DEPARMENT"):"");
        root.put("SAMPLE_LINKMAN", map.get("SAMPLE_LINKMAN")!=null ? map.get("SAMPLE_LINKMAN"):"");
        root.put("SAMPLE_ADDRESS", map.get("SAMPLE_ADDRESS")!=null ? map.get("SAMPLE_ADDRESS"):"");
        root.put("SAMPLE_ZIPCODE", map.get("SAMPLE_ZIPCODE")!=null ? map.get("SAMPLE_ZIPCODE"):"");
        root.put("SAMPLE_PHONE", map.get("SAMPLE_PHONE")!=null ? map.get("SAMPLE_PHONE"):"");
        root.put("SAMPLE_FAX", map.get("SAMPLE_FAX")!=null ? map.get("SAMPLE_FAX"):"");
        root.put("SAMPLE_EMAIL", map.get("SAMPLE_EMAIL")!=null ? map.get("SAMPLE_EMAIL"):"");
        root.put("PROOF", map.get("PROOF")!=null ? map.get("PROOF"):"");
        root.put("SAMPLE_DATE", map.get("SAMPLE_DATE")!=null ? map.get("SAMPLE_DATE"):"");
        root.put("SAMPLE_PERSON", map.get("SAMPLE_PERSON")!=null ? map.get("SAMPLE_PERSON"):"");

        FreeMarkerUtil freemk = new FreeMarkerUtil("/template/doc/", "抽样单下载 .ftl");
        return freemk.processTemplate(root,context);
    }

}
