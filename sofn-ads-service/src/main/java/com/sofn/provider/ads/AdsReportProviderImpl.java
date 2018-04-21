package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsReportExpandMapper;
import com.sofn.model.generator.AdsCheckInfo;
import com.sofn.model.generator.AdsMonitorTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 *	监测报表 providerImpl 实现
 * Created by yangran
 */
@DubboService(interfaceClass = AdsReportProvider.class)
public class AdsReportProviderImpl implements AdsReportProvider {

    @Autowired
    private AdsReportExpandMapper adsReportExpandMapper;

    @Override
    public List<Map<String, Object>> getSamplingLinkReportList(Map<String, Object> map) {
        return adsReportExpandMapper.getSamplingLinkReportList(map);
    }

    @Override
    public List<Map<String, Object>> getCheckLinkReportList(Map<String, Object> map) {

        return adsReportExpandMapper.getCheckLinkReportList(map);
    }

    @Override
    public PageInfo<Map<String, Object>> getCheckObjectReportPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsReportExpandMapper.getCheckObjectReportList(map);
        int count = adsReportExpandMapper.getCheckObjectReportCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String,Object>> getSamplingAreaReportPageInfo(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsReportExpandMapper.getSamplingAreaReportList(map);
        long count = adsReportExpandMapper.getCountSamplingAreaReport(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getCheckAreaObjReportPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsReportExpandMapper.getCheckAreaObjList(map);
        int count = adsReportExpandMapper.getCheckAreaObjCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String,Object>> getCheckAreaReportPageInfo(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsReportExpandMapper.getCheckAreaReportList(map);
        long count = adsReportExpandMapper.getCountCheckAreaReport(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String,Object>> getSamplingOrganReportPageInfo(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsReportExpandMapper.getSamplingOrganReportList(map);
        long count = adsReportExpandMapper.getCountSamplingOrganReport(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String,Object>> getCheckOrganReportPageInfo(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsReportExpandMapper.getCheckOrganReportList(map);
        long count = adsReportExpandMapper.getCountCheckOrganReport(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> getSamplingTypeListByCondition(Map<String, Object> map) {
        return adsReportExpandMapper.getSamplingTypeListByCondition(map);
    }

    @Override
    public List<Map<String, Object>> getCheckTypeListByCondition(Map<String, Object> map) {
        return adsReportExpandMapper.getCheckTypeListByCondition(map);
    }

    @Override
    public List<Map<String, Object>> getCheckObjectListByCondition(Map<String, Object> map){
        return adsReportExpandMapper.getCheckObjectListByCondition(map);
    }

    @Override
    public List<Map<String, Object>> getCheckAreaObjListByCondition(Map<String, Object> map){
        return adsReportExpandMapper.getCheckAreaObjListByCondition(map);
    }
}
