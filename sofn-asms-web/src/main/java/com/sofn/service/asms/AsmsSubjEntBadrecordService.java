package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsSubjEntBadrecord;
import com.sofn.model.generator.AsmsSubjEntTemp;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.provider.ads.AdsMonitorSampleProvider;
import com.sofn.provider.ales.DailyEnforceLawProvider;
import com.sofn.provider.ales.ProduceAdminPunishProvider;
import com.sofn.provider.asms.AsmsSubjEntBadrecordProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import com.sofn.util.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@Service
public class AsmsSubjEntBadrecordService  extends BaseService<AsmsSubjEntBadrecordProvider,AsmsSubjEntBadrecord> {
    @DubboReference
    public void setAsmsSubjEntBadrecordProvider(AsmsSubjEntBadrecordProvider provider){
        this.provider = provider;
    }
    private DailyEnforceLawProvider dailyEnforceLawProvider;
    @DubboReference
    public void setDailyEnforceLawProvider(DailyEnforceLawProvider dailyEnforceLawProvider){
        this.dailyEnforceLawProvider = dailyEnforceLawProvider;
    }
    private ProduceAdminPunishProvider produceAdminPunishProvider;
    @DubboReference
    public void setProduceAdminPunishProvider(ProduceAdminPunishProvider produceAdminPunishProvider){
        this.produceAdminPunishProvider = produceAdminPunishProvider;
    }
    private TtsScltxxcjRegiterProvider ttsScltxxcjRegiterProvider;
    @DubboReference
    public void setTtsScltxxcjRegiterProvider(TtsScltxxcjRegiterProvider provider){ ttsScltxxcjRegiterProvider = provider;
    }
    private AdsMonitorSampleProvider adsMonitorSampleProvider;
    @DubboReference
    public void setAdsMonitorSampleProvider(AdsMonitorSampleProvider adsMonitorSampleProvider){
        this.adsMonitorSampleProvider = adsMonitorSampleProvider;
    }
    public PageInfo getAsmsSubjEntBadrecordList(AsmsSubjEntBadrecord asmsSubjEntBadrecord,String area, String entityIndustry, String entityType,
                                                int pageNum, int pageSize, String queryCon){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("area",area);
        queryParams.put("entityIndustry",entityIndustry);
        queryParams.put("entityType",entityType);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("queryCon",queryCon);
        return provider.getAsmsSubjEntBadrecordList(queryParams);
    }
    public TtsScltxxcjRegiter findEnterpriseById(String entityIdCode){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("entityIdCode",entityIdCode);
        return ttsScltxxcjRegiterProvider.selectByEntityIdCode(params);
    }
    /**
     * 获取临时生产经验主体
     * @param entityIdCode
     * @return
     */
    public AsmsSubjEntTemp findEnterpriseTmpById(String entityIdCode){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("entityIdCode",entityIdCode);
        return provider.selectByEntityIdCode(params);
    }
    public AsmsSubjEntBadrecord findBadrecordById(String id){
        return provider.findBadrecordById(id);
    }


    public PageInfo getAsmsSubjEntBadrecordByIdList(Page page, String enterpriseId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("enterpriseId",enterpriseId);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        return provider.getAsmsSubjEntBadrecordByIdList(queryMap);
    }

    public Map findSubjEntBadcordDetail(String sourceId, String sourceType){
        Map<String,Object> map = new HashMap<>();
        if("2".equals(sourceType)){
            //行政处罚
            map.put("httpCode", 200);
            map.put("msg", "请求成功");
            map.put("produceAdminPunish",produceAdminPunishProvider.findProduceAdminPunishById(sourceId));
        }else if("3".equals(sourceType)){
            //抽样检测
            map.put("httpCode", 200);
            map.put("msg", "请求成功");
            map.put("productAndAdsCheckInfo",adsMonitorSampleProvider.getInfoBySampleId(sourceId));
        }else if("4".equals(sourceType)){
            //日常执法
            map.put("httpCode", 200);
            map.put("msg", "请求成功");
            map.put("dailyEnforceLaw", dailyEnforceLawProvider.findDailyEnforceLawById(sourceId));
        }else {
            map.put("httpCode", 500);
            map.put("msg", "未找到相关数据");
        }
        return map;
    }

}
