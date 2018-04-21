
package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ads.AdsCheckInfoExpandMapper;
import com.sofn.model.generator.AdsCheckInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
*	检测信息 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsCheckInfoProvider.class)
public class AdsCheckInfoProviderImpl extends BaseProviderImpl<AdsCheckInfo> implements AdsCheckInfoProvider {

    @Autowired
    private AdsCheckInfoExpandMapper adsCheckInfoExpandMapper;

    public PageInfo<AdsCheckInfo> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getPageInfo(map);
        long count = adsCheckInfoExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<AdsCheckInfo> getInfo(Map<String, Object> map) {
        List<AdsCheckInfo> list = adsCheckInfoExpandMapper.getInfo(map);
        return list;
    }

    public String getNextId(Map<String, Object> map) {
        String id = adsCheckInfoExpandMapper.getNextId(map);
        return id;
    }

    public String getBeforeId(Map<String, Object> map) {
        String id = adsCheckInfoExpandMapper.getBeforeId(map);
        return id;
    }

    public void updateResult(Map<String, Object> map) {
        adsCheckInfoExpandMapper.updateResult(map);
    }

    public PageInfo<AdsCheckInfo> getPageInfoParam(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getPageInfoParam(map);
        long count = adsCheckInfoExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
//    使用的分页
    public PageInfo<AdsCheckInfo> getPageInfoWithParam(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getPageInfoWithParam(map);
        long count = adsCheckInfoExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsCheckInfo> getInfoByOrgName(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getInfoByOrgName(map);
        long count = adsCheckInfoExpandMapper.getOrgNameCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    public PageInfo<AdsCheckInfo> getInfoByOrgNameAll(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getInfoByOrgNameAll(map);
        long count = adsCheckInfoExpandMapper.getOrgNameCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<Map<String,Object>> getYear() {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getYear();
        return list;
    }
    public List<Map<String,Object>> getSampleName(String monitorClass) {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getSampleName(monitorClass);
        return list;
    }
    public List<Map<String,Object>> getTaskName(Map<String, Object> map) {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getTaskName(map);
        return list;
    }
    public List<Map<String,Object>> getResult() {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getResult();
        return list;
    }
    public List<Map<String,Object>> getSampleDeparment(Map<String, Object> map) {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getSampleDeparment(map);
        return list;
    }
    public List<Map<String,Object>> getCheckOrgan(Map<String, Object> map) {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getCheckOrgan(map);
        return list;
    }
    public List<Map<String,Object>> getCheckLink() {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getCheckLink();
        return list;
    }
    public List<Map<String,Object>> getTestedDeparment(Map<String, Object> map) {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getTestedDeparment(map);
        return list;
    }

    public PageInfo<AdsCheckInfo> getInfoProject(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getInfoProject(map);
        long count = adsCheckInfoExpandMapper.getCountProject(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public AdsCheckInfo queryBySampleCode(Map<String, Object> map) {
        AdsCheckInfo adsCheckInfo = adsCheckInfoExpandMapper.queryBySampleCode(map);
        return adsCheckInfo;
    }

    public AdsCheckInfo queryBySampleCodeAndOrganId(Map<String, Object> map) {
        AdsCheckInfo adsCheckInfo = adsCheckInfoExpandMapper.queryBySampleCodeAndOrganId(map);
        return adsCheckInfo;
    }

    public List<Map<String,Object>> getSampleBySampleCode(String sampleCode) {
        List<Map<String, Object>> adsMonitorSample = adsCheckInfoExpandMapper.getSampleBySampleCode(sampleCode);
        return adsMonitorSample;
    }
    public void deleteInFlag(Map<String, Object> map) {
        adsCheckInfoExpandMapper.deleteInFlag(map);
    }

    public AdsCheckInfo queryByMyId(Map<String, Object> map) {
        AdsCheckInfo adsCheckInfo = adsCheckInfoExpandMapper.queryByMyId(map);
        return adsCheckInfo;
    }

    public AdsCheckInfo queryBySampleId(Map<String, Object> map) {
        AdsCheckInfo adsCheckInfo = adsCheckInfoExpandMapper.queryBySampleId(map);
        return adsCheckInfo;
    }

    public List<Map<String,Object>> getProducingArea() {
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getProducingArea();
        return list;
    }

    public PageInfo<AdsCheckInfo> getPageInfoWithOrgTaskId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = adsCheckInfoExpandMapper.getPageInfoWithOrgTaskId(map);
        long count = adsCheckInfoExpandMapper.getCountWithOrgTaskId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public void resetResult(Map<String, Object> map) {
        adsCheckInfoExpandMapper.resetResult(map);
    }

    public List<AdsCheckInfo> getCheckList(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getCheckList(map);

    }

    public List<AdsCheckInfo> getCheckListWithParam(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getCheckListWithParam(map);

    }

    public List<AdsCheckInfo> getCheckListByCheck(String[] idsArr){
        return  adsCheckInfoExpandMapper.getCheckListByCheck(idsArr);

    }
    public void updateReport(Map<String, Object> map) {
        adsCheckInfoExpandMapper.updateReport(map);
    }

    public List<AdsCheckInfo> getCheckListAll(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getCheckListAll(map);

    }
    public List<AdsCheckInfo> getOrgList(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getOrgList(map);

    }
    public List<AdsCheckInfo> getOrgListAll(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getOrgListAll(map);

    }
    public List<AdsCheckInfo> getProjectList(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getProjectList(map);

    }
    public List<AdsCheckInfo> getSampleList(Map<String, Object> map){
        return  adsCheckInfoExpandMapper.getSampleList(map);

    }

    public int getFinish(Map<String, Object> map) {
        int count = adsCheckInfoExpandMapper.getFinish(map);
        return count;
    }

    public List<Map<String,Object>> getCheckInfo(String checkInfoId) {

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id",checkInfoId);
        AdsCheckInfo adsCheckInfo = adsCheckInfoExpandMapper.queryByMyId(queryParams);

        Map<String,Object> map1 = new HashMap<>();

        map1.put("adsCheckInfo",adsCheckInfo);

        list.add(map1);

        //通过机构任务ID查询任务名称
        String TaskName = adsCheckInfoExpandMapper.queryTaskName(adsCheckInfo.getOrganTaskId());

        Map<String,Object> map2 = new HashMap<>();

        map2.put("TaskName",TaskName);

        list.add(map2);

        return list;
    }


    public void addAdsCheckInfo(AdsCheckInfo adsCheckInfo){
        String uuid = UUID.randomUUID().toString();
        String id = uuid.replace("-", "");
        adsCheckInfo.setId(id);
        Date date=new Date();
        adsCheckInfo.setCreateTime(date);
        adsCheckInfo.setUpdateTime(date);
        adsCheckInfoExpandMapper.addAdsCheckInfo(adsCheckInfo);
    }
}
