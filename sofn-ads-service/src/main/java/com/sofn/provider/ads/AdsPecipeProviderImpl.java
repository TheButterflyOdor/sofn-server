package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsCheckInfoExpandMapper;
import com.sofn.dao.ads.AdsMonitorSampleExpandMapper;
import com.sofn.dao.ads.AdsPecipeExpandMapper;
import com.sofn.dao.generator.AdsPecipeMapper;
import com.sofn.model.generator.AdsMonitorSample;
import com.sofn.model.generator.AdsOrganTask;
import com.sofn.model.generator.AdsPecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
*	回执单 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsPecipeProvider.class)
public class AdsPecipeProviderImpl extends BaseProviderImpl<AdsPecipe> implements AdsPecipeProvider {

    @Autowired
    private AdsPecipeExpandMapper adsPecipeExpandMapper;
    @Autowired
    private AdsPecipeMapper adsPecipeMapper;
    @Autowired
    private AdsMonitorSampleExpandMapper adsMonitorSampleExpandMapper;
    @Autowired
    private AdsCheckInfoExpandMapper adsCheckInfoExpandMapper;

    public PageInfo<AdsPecipe> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsPecipeExpandMapper.getPageInfo(map);
        long count = adsPecipeExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<AdsPecipe> getAdsPecipePageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsPecipeExpandMapper.getAdsPecipePageInfo(map);
        long count = adsPecipeExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;

    }

    @Override
    @Transactional
    public int insert(AdsPecipe adsPecipe){
        int report = 0;
        AdsOrganTask adsOrganTask = new AdsOrganTask();
        adsOrganTask.setId(adsPecipe.getOrganTaskId());
        adsOrganTask.setUpdateBy(adsPecipe.getUpdateBy());
        if(adsPecipe.getReceiptClass().equals("S")){
            //更改抽样单上报状态
            String ids = adsPecipe.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        AdsMonitorSample adsMonitorSample = new AdsMonitorSample();
                        adsMonitorSample.setUpdateBy(adsPecipe.getCreateBy());
                        adsMonitorSample.setId(id);
                        adsMonitorSample.setSampleReport("3");
                        adsMonitorSampleExpandMapper.updateAdsMonitorSampleById(adsMonitorSample);
                        report++;
                    }
                }
            }
            if(report == adsPecipe.getFinishNum().intValue()){
                adsOrganTask.setSampleReportStatus(new BigDecimal(3));
                adsOrganTask.setSampleFinishNum(new BigDecimal(0));
            }else if(report < adsPecipe.getFinishNum().intValue()){
                adsOrganTask.setSampleReportStatus(new BigDecimal(4));
                adsOrganTask.setSampleFinishNum(new BigDecimal(adsPecipe.getFinishNum().intValue() - report));
            }
        }else if(adsPecipe.getReceiptClass().equals("D")){
            //更改检测单上报状态
            String ids = adsPecipe.getIds();
            if(ids!=null && ids.length() > 0){
                String[] arr = ids.split(",");
                if(arr!=null && arr.length > 0){
                    for (String id : arr){
                        Map<String, Object> queryParams = new HashMap<>();
                        queryParams.put("id", id);
                        queryParams.put("receiptClass","D");
                        adsCheckInfoExpandMapper.updateReport(queryParams);
                        report++;
                    }
                }
            }
            if(report == adsPecipe.getFinishNum().intValue()){
                adsOrganTask.setCheckReportStatus(new BigDecimal(3));
                adsOrganTask.setCheckFinishNum(new BigDecimal(0));
            }else if(report < adsPecipe.getFinishNum().intValue()){
                adsOrganTask.setCheckReportStatus(new BigDecimal(4));
                adsOrganTask.setCheckFinishNum(new BigDecimal(adsPecipe.getFinishNum().intValue() - report));
            }
        }
        if(report != 0){
            adsPecipeExpandMapper.updateStatusByID(adsOrganTask);
            //创建回执单
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            adsPecipe.setId(uuid);
            adsPecipeMapper.insert(adsPecipe);
            return 1;
        }else{
            return 0;
        }
    }

}
