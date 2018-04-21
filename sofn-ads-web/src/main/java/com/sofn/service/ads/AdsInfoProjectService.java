package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AdsInfoProject;
import com.sofn.model.generator.AlesWtObjectCriterion;
import com.sofn.model.generator.AsmsCheckObjectCriterion;
import com.sofn.provider.ads.AdsInfoProjectProvider;
import com.sofn.provider.ales.AlesTaskSampleProvider;
import com.sofn.provider.asms.AsmsCheckObjectCriterionProvider;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检测信息_检测项目 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class AdsInfoProjectService extends BaseService<AdsInfoProjectProvider, AdsInfoProject> {
    private AsmsCheckObjectCriterionProvider asmsCheckObjectCriterionProvider;

    private  AlesTaskSampleProvider alesTaskSampleProvider;

    @DubboReference
    public void setAdsInfoProjectProvider(AdsInfoProjectProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setAsmsCheckObjectCriterionProvider(AsmsCheckObjectCriterionProvider provider) {
        this.asmsCheckObjectCriterionProvider = provider;
    }

    @DubboReference
    public void setAlesTaskSampleProvider(AlesTaskSampleProvider alesTaskSampleProvider) {
        this.alesTaskSampleProvider = alesTaskSampleProvider;
    }

    public PageInfo getPageInfo(AdsInfoProject bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public List<AdsInfoProject> getPageInfoWithCheckInfoId(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkInfoId", id);
        return provider.getPageInfoWithCheckInfoId(queryParams);
    }

    public void updateResult(String id,String checkNum,String result,String lod,String loo) {
        Map<String, Object> queryParams = new HashMap<>();
        //去除多余的0
        String newCheckNum = checkNum.replaceFirst("^0*", "");
        if(newCheckNum.startsWith(".")){
            newCheckNum = "0"+newCheckNum;
        }
        if("".equals(newCheckNum)){
            newCheckNum = "0";
        }
        queryParams.put("id", id);
        queryParams.put("checkNum", newCheckNum);
        queryParams.put("result", result);
        queryParams.put("result", result);
        queryParams.put("lod", lod);
        queryParams.put("loo", loo);
        provider.updateResult(queryParams);
    }

    public AdsInfoProject queryByProjectAndInfoId(String checkInfoId,String CHECK_PROJECT) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkInfoId", checkInfoId);
        queryParams.put("CHECK_PROJECT", CHECK_PROJECT);
        return provider.queryByProjectAndInfoId(queryParams);
    }

    public List<AdsInfoProject> queryByInfoId(String checkInfoId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkInfoId", checkInfoId);
        return provider.queryByInfoId(queryParams);
    }

    public List<AdsInfoProject> queryListByInfoId(String checkInfoId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("checkInfoId", checkInfoId);
        return provider.queryListByInfoId(queryParams);
    }

    public void setInfo(String id,String result) {
        alesTaskSampleProvider.sampleResult(id,result);
    }

    public void updateInfoProject(List<AdsInfoProject> myList,String sampleCode) {
        Map<String,Object> map = new HashMap();
        map.put("sampleCode",sampleCode);
        List<AsmsCheckObjectCriterion> list = asmsCheckObjectCriterionProvider.getDetectionItemBySampleCode(map);
        for(AdsInfoProject adsInfoProject : myList){
            for(AsmsCheckObjectCriterion asmsCheckObjectCriterion : list){
                if(adsInfoProject.getCheckProject().equals(asmsCheckObjectCriterion.getCriterionName())){
                    Map<String, Object> queryParams = new HashMap<>();
                    queryParams.put("id", adsInfoProject.getId());
//                    if(null!=asmsCheckObjectCriterion.getSpperLimit()){
//                        queryParams.put("max", asmsCheckObjectCriterion.getSpperLimit().toString());
//                    }else{
//                        queryParams.put("max", asmsCheckObjectCriterion.getSpperLimit());
//                    }
                    queryParams.put("max", String.valueOf(asmsCheckObjectCriterion.getSpperLimit()));
                    if(null!=asmsCheckObjectCriterion.getSpperLimit()){
                        provider.updateMax(queryParams);
                    }
                }
            }
        }
    }

    public void updateInfoProjectForC(List<AdsInfoProject> myList,String sampleCode) {
        Map<String,Object> map = new HashMap();
        map.put("sampleCode",sampleCode);
        List<AlesWtObjectCriterion> list = alesTaskSampleProvider.getCriBySampleCode(sampleCode);
        for(AdsInfoProject adsInfoProject : myList){
            for(AlesWtObjectCriterion alesWtObjectCriterion : list){
                if(adsInfoProject.getCheckProject().equals(alesWtObjectCriterion.getCriterionName())){
                    Map<String, Object> queryParams = new HashMap<>();
                    queryParams.put("id", adsInfoProject.getId());
//                    if(null!=asmsCheckObjectCriterion.getSpperLimit()){
//                        queryParams.put("max", asmsCheckObjectCriterion.getSpperLimit().toString());
//                    }else{
//                        queryParams.put("max", asmsCheckObjectCriterion.getSpperLimit());
//                    }
                    queryParams.put("max", String.valueOf(alesWtObjectCriterion.getSpperLimit()));
                    if(null!=alesWtObjectCriterion.getSpperLimit()){
                        provider.updateMax(queryParams);
                    }
                }
            }
        }
    }

}