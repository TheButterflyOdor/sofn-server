package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.provider.ads.AdsReportProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 监测报表 service 业务逻辑
 * @author yangran
 */
@Service
public class AdsReportService {

    protected AdsReportProvider provider;

    @DubboReference
    public void setAdsMonitorTaskProvider(AdsReportProvider provider) {
        this.provider = provider;
    }

    /**
     * 查询抽样环节报表数据
     * @param map
     * @return
     */
    public List<Map<String,Object>> getSamplingLinkReportList(Map<String,Object> map){
        return provider.getSamplingLinkReportList(map);
    }

    /**
     * 查询检测环节报表数据
     * @param map
     * @return
     */
    public List<Map<String, Object>> getCheckLinkReportList(Map<String,Object> map) {

        return provider.getCheckLinkReportList(map);
    }

    /**
     * 查询抽样地区报表数据
     * @param map
     * @return
     */
    public PageInfo<Map<String,Object>> getSamplingAreaReportPageInfo(Map<String,Object> map,int pageNum, int pageSize){
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return provider.getSamplingAreaReportPageInfo(map);
    }

    /**
     * 查询检测对象报表分页数据
     * @param map
     * @return
     */
    public PageInfo<Map<String,Object>> getCheckObjectReportPageInfo(Map<String,Object> map){
        return provider.getCheckObjectReportPageInfo(map);
    }

    /**
     * 查询检测地区、对象报表分页数据
     * @param map
     * @return
     */
    public PageInfo<Map<String,Object>> getCheckAreaObjReportPageInfo(Map<String,Object> map){
        return provider.getCheckAreaObjReportPageInfo(map);
    }

    /**
     * 查询检测地区报表数据
     * @param map
     * @return
     */
    public PageInfo<Map<String,Object>> getCheckAreaReportPageInfo(Map<String,Object> map,int pageNum, int pageSize){
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return provider.getCheckAreaReportPageInfo(map);
    }

    /**
     * 查询抽样机构报表数据
     * @param map
     * @return
     */
    public PageInfo<Map<String,Object>> getSamplingOrganReportPageInfo(Map<String,Object> map,int pageNum, int pageSize){
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return provider.getSamplingOrganReportPageInfo(map);
    }

    /**
     * 查询检测机构报表数据
     * @param map
     * @return
     */
    public PageInfo<Map<String,Object>> getCheckOrganReportPageInfo(Map<String,Object> map,int pageNum, int pageSize){
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return provider.getCheckOrganReportPageInfo(map);
    }

    /**
     * 根据条件查询抽样地区或机构报表列表
     * @param monitorTaskId
     * @return
     */
    public List<Map<String, Object>> getSamplingTypeListByCondition(String monitorTaskId,String type){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId", monitorTaskId);
        queryParams.put("type", type);
        return provider.getSamplingTypeListByCondition(queryParams);
    }

    /**
     * 根据条件查询检测地区或机构报表列表
     * @param monitorTaskId
     * @return
     */
    public List<Map<String, Object>> getCheckTypeListByCondition(String monitorTaskId,String type){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId", monitorTaskId);
        queryParams.put("type", type);
        return provider.getCheckTypeListByCondition(queryParams);
    }

    /**
     * 根据条件查询检测对象报表列表
     * @param monitorTaskId
     * @return
     */
    public List<Map<String, Object>> getCheckObjectListByCondition(String monitorTaskId){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId", monitorTaskId);
        return provider.getCheckObjectListByCondition(queryParams);
    }

    /**
     * 根据条件查询检测地区,对象报表列表
     * @param monitorTaskId
     * @return
     */
    public List<Map<String, Object>> getCheckAreaObjListByCondition(String monitorTaskId){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("monitorTaskId", monitorTaskId);
        return provider.getCheckAreaObjListByCondition(queryParams);
    }
}
