package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.SysCategoryEnum;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.*;
import com.sofn.model.sys.SysDocumentQueryParam;
import com.sofn.provider.ads.AdsCheckInfoProvider;
import com.sofn.provider.ales.AlesTaskSampleProvider;
import com.sofn.provider.asms.AsmsCheckObjectCriterionProvider;
import com.sofn.provider.sys.SysDocumentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检测信息 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsCheckInfoService extends BaseService<AdsCheckInfoProvider, AdsCheckInfo> {
    private AsmsCheckObjectCriterionProvider asmsCheckObjectCriterionProvider;

    private AlesTaskSampleProvider alesTaskSamplePraovider;

    private SysDocumentProvider sysDocumentProvider;

    @Autowired
    private AdsInfoProjectService adsInfoProjectService;

    @DubboReference
    public void setAdsCheckInfoProvider(AdsCheckInfoProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setSysDocumentProviderProvider(SysDocumentProvider provider) {
        this.sysDocumentProvider = provider;
    }

    @DubboReference
    public void setAsmsCheckObjectCriterionProvider(AsmsCheckObjectCriterionProvider provider) {
        this.asmsCheckObjectCriterionProvider = provider;
    }

    public PageInfo getPageInfo(AdsCheckInfo bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public List<AdsCheckInfo> getInfo(String organId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organId", organId);
        return provider.getInfo(queryParams);
    }

    public String getNextId(String sampleCode,String organId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleCode", sampleCode);
        queryParams.put("organId", organId);
        return provider.getNextId(queryParams);
    }

    public String getBeforeId(String sampleCode,String organId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleCode", sampleCode);
        queryParams.put("organId", organId);
        return provider.getBeforeId(queryParams);
    }

    public void updateResult(String id,String result) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        queryParams.put("result", result);
        provider.updateResult(queryParams);
    }
    public void addAdsCheckInfo(AdsCheckInfo adsCheckInfo) {
        provider.addAdsCheckInfo(adsCheckInfo);
    }
    public List<Map<String,Object>> getYear() {
        return provider.getYear();
    }

    public List<Map<String,Object>> getSampleName(String monitorClass) {
        return provider.getSampleName( monitorClass);
    }

    public List<Map<String,Object>> getTaskName(String monitorClass,String organId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass",monitorClass);
        queryParams.put("organId",organId);
        return provider.getTaskName(queryParams);
    }

    public List<Map<String,Object>> getResult() {
        return provider.getResult();
    }

    public List<Map<String,Object>> getSampleDeparment(String  monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass",monitorClass );
        return provider.getSampleDeparment(queryParams);
    }
    public List<Map<String,Object>> getCheckOrgan(String  monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass",monitorClass );
        return provider.getCheckOrgan(queryParams);
    }
    public List<Map<String,Object>> getCheckLink() {
        return provider.getCheckLink();
    }

    public List<Map<String,Object>> getTestedDeparment(String  monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass",monitorClass );
        return provider.getTestedDeparment(queryParams);
    }



    public PageInfo getPageInfoParam(AdsCheckInfo bean, int pageNum, int pageSize,String startTime,String endTime,String producingArea,String cityCode,String monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkOrgan",bean.getCheckOrgan() );
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("productTraceabilityCode",bean.getProductTraceabilityCode());
        queryParams.put("sampleCode",bean.getSampleCode());
        queryParams.put("sampleName",bean.getSampleName());
        queryParams.put("result",bean.getResult());
        queryParams.put("sampleDeparment",bean.getSampleDeparment());
        queryParams.put("checkLink",bean.getCheckLink());
        queryParams.put("sampleDate",bean.getSampleDate());
        queryParams.put("testedDeparment",bean.getTestedDeparment());
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("producingArea",producingArea);
        queryParams.put("cityCode",cityCode);
        queryParams.put("monitorClass",monitorClass);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoParam(queryParams);
    }
    public PageInfo getPageInfoWithParam(AdsCheckInfo bean, int pageNum, int pageSize,String startTime,String endTime,String producingArea,String cityCode,String monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkOrganId",bean.getCheckOrganId());
        queryParams.put("checkOrgan",bean.getCheckOrgan() );
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("productTraceabilityCode",bean.getProductTraceabilityCode());
        queryParams.put("sampleCode",bean.getSampleCode());
        queryParams.put("sampleName",bean.getSampleName());
        queryParams.put("result",bean.getResult());
        queryParams.put("sampleDeparment",bean.getSampleDeparment());
        queryParams.put("checkLink",bean.getCheckLink());
        queryParams.put("sampleDate",bean.getSampleDate());
        queryParams.put("testedDeparment",bean.getTestedDeparment());
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("producingArea",producingArea);
        queryParams.put("cityCode",cityCode);
        queryParams.put("monitorClass",monitorClass);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfoWithParam(queryParams);
    }

 public PageInfo getInfoByOrgName(AdsCheckInfo bean, int pageNum, int pageSize,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkOrgan",bean.getCheckOrgan() );
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("productTraceabilityCode",bean.getProductTraceabilityCode());
        queryParams.put("sampleCode",bean.getSampleCode());
        queryParams.put("sampleName",bean.getSampleName());
        queryParams.put("result",bean.getResult());
        queryParams.put("sampleDeparment",bean.getSampleDeparment());
        queryParams.put("checkLink",bean.getCheckLink());
        queryParams.put("sampleDate",bean.getSampleDate());
        queryParams.put("testedDeparment",bean.getTestedDeparment());
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("producingArea",producingArea);
        queryParams.put("cityCode",cityCode);
        queryParams.put("monitorClass",monitorClass);
        queryParams.put("orgId",orgId);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getInfoByOrgName(queryParams);
    }


    public PageInfo getInfoByOrgNameAll(AdsCheckInfo bean, int pageNum, int pageSize,String startTime,String endTime,String producingArea,String cityCode,String monitorClass,String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkOrgan",bean.getCheckOrgan() );
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("productTraceabilityCode",bean.getProductTraceabilityCode());
        queryParams.put("sampleCode",bean.getSampleCode());
        queryParams.put("sampleName",bean.getSampleName());
        queryParams.put("result",bean.getResult());
        queryParams.put("sampleDeparment",bean.getSampleDeparment());
        queryParams.put("checkLink",bean.getCheckLink());
        queryParams.put("sampleDate",bean.getSampleDate());
        queryParams.put("testedDeparment",bean.getTestedDeparment());
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("producingArea",producingArea);
        queryParams.put("cityCode",cityCode);
        queryParams.put("monitorClass",monitorClass);
        queryParams.put("orgId",orgId);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getInfoByOrgNameAll(queryParams);
    }

    public PageInfo getInfoProject(AdsCheckInfo bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleCode", bean.getSampleCode());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getInfoProject(queryParams);
    }

    public AdsCheckInfo queryBySampleCode(String SAMPLE_CODE) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("SAMPLE_CODE", SAMPLE_CODE);
        return provider.queryBySampleCode(queryParams);
    }

    public AdsCheckInfo queryBySampleCodeAndOrganId(String SAMPLE_CODE,String organTaskId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("SAMPLE_CODE", SAMPLE_CODE);
        queryParams.put("organTaskId", organTaskId);
        return provider.queryBySampleCodeAndOrganId(queryParams);
    }

    public List<Map<String,Object>> getSampleBySampleCode(String sampleCode){
        return provider.getSampleBySampleCode(sampleCode);
    }

    public void deleteInFlag(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        provider.deleteInFlag(queryParams);
    }

    public AdsCheckInfo queryByMyId(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.queryByMyId(queryParams);
    }

    public AdsCheckInfo queryBySampleId(String sampleId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleId", sampleId);
        return provider.queryBySampleId(queryParams);
    }

    public List<Map<String,Object>> getProducingArea() {
        return provider.getProducingArea();
    }

    public PageInfo getPageInfoWithOrgTaskId(AdsCheckInfo bean, int pageNum, int pageSize,String organTaskId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("organTaskId", organTaskId);
        return provider.getPageInfoWithOrgTaskId(queryParams);
    }

    public void resetResult(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        provider.resetResult(queryParams);
    }

    public List<AdsCheckInfo> getCheckList(String  monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass", monitorClass);
        return provider.getCheckList(queryParams);
    }

    /**
     * 根据查询条件获取AdsCheckInfo集合
     * @param monitorClass
     * @return
     */
    public List<AdsCheckInfo> getCheckListWithParam(AdsCheckInfo bean,String startTime,String endTime,String producingArea,String cityCode,String monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkOrganId",bean.getCheckOrganId());
        queryParams.put("checkOrgan",bean.getCheckOrgan() );
        queryParams.put("year", bean.getYear());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("productTraceabilityCode",bean.getProductTraceabilityCode());
        queryParams.put("sampleCode",bean.getSampleCode());
        queryParams.put("sampleName",bean.getSampleName());
        queryParams.put("result",bean.getResult());
        queryParams.put("sampleDeparment",bean.getSampleDeparment());
        queryParams.put("checkLink",bean.getCheckLink());
        queryParams.put("sampleDate",bean.getSampleDate());
        queryParams.put("testedDeparment",bean.getTestedDeparment());
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("producingArea",producingArea);
        queryParams.put("cityCode",cityCode);
        queryParams.put("monitorClass",monitorClass);
        return provider.getCheckListWithParam(queryParams);
    }
    public List<AdsCheckInfo> getCheckListByCheck(String ids) {
        String[] idsArr = ids.split(",");
        return provider.getCheckListByCheck(idsArr);
    }

    public void updateReport(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        provider.updateReport(queryParams);
    }

    public List<AdsCheckInfo> getCheckListAll(String  monitorClass) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass", monitorClass);
        return provider.getCheckListAll(queryParams);
    }
    public List<AdsCheckInfo> getOrgList(String  monitorClass,String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass", monitorClass);
        queryParams.put("orgId", orgId);
        return provider.getOrgList(queryParams);
    }
    public List<AdsCheckInfo> getOrgListAll(String  monitorClass,String orgId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorClass", monitorClass);
        queryParams.put("orgId", orgId);
        return provider.getOrgListAll(queryParams);
    }
    public List<AdsCheckInfo> getProjectList(String  sampleCode) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleCode", sampleCode);
        return provider.getProjectList(queryParams);
    }
    public List<AdsCheckInfo> getSampleList(String  sampleCode) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleCode", sampleCode);
        return provider.getSampleList(queryParams);
    }

    public int getFinish(String organTaskId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId", organTaskId);
        return provider.getFinish(queryParams);
    }

    public void inzertInfoProject(String id,String sampleCode) {
        Map<String,Object> map = new HashMap();
        map.put("sampleCode",sampleCode);
        List<AsmsCheckObjectCriterion> list = asmsCheckObjectCriterionProvider.getDetectionItemBySampleCode(map);
        for(AsmsCheckObjectCriterion asmsCheckObjectCriterion : list){
            Timestamp timestamp = new Timestamp(new Date().getTime());
            AdsInfoProject adsInfoProject = new AdsInfoProject();
            if(null == asmsCheckObjectCriterion.getCriterionName()){
                continue;
            }
            adsInfoProject.setCheckProject(asmsCheckObjectCriterion.getCriterionName());
            adsInfoProject.setCheckNum("0");
            adsInfoProject.setJudgeNum(String.valueOf(asmsCheckObjectCriterion.getSpperLimit()));
            adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
            adsInfoProject.setCheckInfoId(id);
            adsInfoProject.setCreateBy("qq");
            adsInfoProject.setCreateTime(timestamp);
            adsInfoProject.setUpdateBy("qq");
            adsInfoProject.setUpdateTime(timestamp);
            adsInfoProjectService.add(adsInfoProject);
        }
    }

    public void inzertInfoProjectForC(String id,String sampleCode) {
        Map<String,Object> map = new HashMap();
        map.put("sampleCode",sampleCode);
        List<AlesWtObjectCriterion> list = alesTaskSamplePraovider.getCriBySampleCode(sampleCode);
        for(AlesWtObjectCriterion alesWtObjectCriterion : list){
            Timestamp timestamp = new Timestamp(new Date().getTime());
            AdsInfoProject adsInfoProject = new AdsInfoProject();
            if(null == alesWtObjectCriterion.getCriterionName()){
                continue;
            }
            adsInfoProject.setCheckProject(alesWtObjectCriterion.getCriterionName());
            adsInfoProject.setCheckNum("0");
            adsInfoProject.setJudgeNum(String.valueOf(alesWtObjectCriterion.getSpperLimit()));
            adsInfoProject.setResult("-1");//1:代表合格 0:代表不合格 -1:代表未检测
            adsInfoProject.setCheckInfoId(id);
            adsInfoProject.setCreateBy("qq");
            adsInfoProject.setCreateTime(timestamp);
            adsInfoProject.setUpdateBy("qq");
            adsInfoProject.setUpdateTime(timestamp);
            adsInfoProjectService.add(adsInfoProject);
        }
    }

    public PageInfo<SysDocument> getDocumentList(SysDocumentQueryParam q) {
        q.setPageNum(1);
        q.setPageSize(10);
        q.setApplyTo(SysCategoryEnum.ADS.getValue());
        return sysDocumentProvider.getDocumentList(q);
    }

    public SysDocument getDocument(String id) {
        return sysDocumentProvider.getDocument(id);
    }

}

