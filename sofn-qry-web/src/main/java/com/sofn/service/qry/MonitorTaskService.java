package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.QueryParameter;
import com.sofn.provider.qry.MonitorTaskProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月23日 上午 10:58
 */
@Service
public class MonitorTaskService extends BaseService {

    private MonitorTaskProvider provider;

    @DubboReference
    public void setProvider(MonitorTaskProvider provider) {
        this.provider = provider;
    }

    public PageInfo<List<Map<String, Object>>> getRoutineMonitorList(String token, AsmsRoutineMonitor r,String leadUnitId, QueryParameter p, String areaId) {
        Organization org = WebUtil.getCurrentUser(token).getOrganization();//获取机构信息
        //机构级别+区域确定管辖范围
        String orgLevel = org.getOrgLevel();//机构级别
        String regionId = org.getRegionId();//区域id
        String regParm=null;
        if(!"1".equals(orgLevel)) {  //非部级
            regParm = regionId.substring(0, (Integer.parseInt(orgLevel) - 1) * 2)+"%";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("regParm",regParm);
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        params.put("leadUnitId",StringUtil.isNotBlank(leadUnitId)?leadUnitId:null);
        params.put("createOrgId", StringUtil.isNotBlank(r.getCreateOrgId()) ? r.getCreateOrgId() : null);
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        params.put("rmName", StringUtil.isNotBlank(r.getRmName()) ? "%" + r.getRmName() + "%" : null);
        params.put("rmState", StringUtil.isNotBlank(r.getRmState()) ? r.getRmState() : null);
        params.put("rmYear", StringUtil.isNotBlank(r.getRmYear()) ? r.getRmYear() : null);
        params.put("areaId",StringUtil.isNotBlank(areaId)?areaId:null);
        PageInfo<List<Map<String, Object>>> i = provider.getRoutineMonitorList(params);
        return i;
    }
    public PageInfo<List<Map<String, Object>>> getSpecialMonitorList(String token, AsmsSpecialMonitor r,String leadUnitId, QueryParameter p) {
        Organization org = WebUtil.getCurrentUser(token).getOrganization();//获取机构信息
        //机构级别+区域确定管辖范围
        String orgLevel = org.getOrgLevel();//机构级别
        String regionId = org.getRegionId();//区域id
        String regParm=null;
        if(!"1".equals(orgLevel)) {  //非部级
            regParm = regionId.substring(0, (Integer.parseInt(orgLevel) - 1) * 2)+"%";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("regParm",regParm);
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        params.put("leadUnitId",StringUtil.isNotBlank(leadUnitId)?leadUnitId:null);
        params.put("createOrgId", StringUtil.isNotBlank(r.getCreateOrgId()) ? r.getCreateOrgId() : null);
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        params.put("smName", StringUtil.isNotBlank(r.getSmName()) ? "%" + r.getSmName() + "%" : null);
        params.put("smState", StringUtil.isNotBlank(r.getSmState()) ? r.getSmState() : null);
        params.put("smYear", StringUtil.isNotBlank(r.getSmYear()) ? r.getSmYear() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getSpecialMonitorList(params);
        return i;
    }

    public PageInfo<List<Map<String, Object>>> getCheckTaskList(String token, AsmsCheckTask r, QueryParameter p) {
        Map<String, Object> params = new HashMap<>();
        Organization org = WebUtil.getCurrentUser(token).getOrganization();
        //根据机构行政区划
        String orgLevel = org.getOrgLevel();
        String regionId = org.getRegionId();
        if ("2".equals(orgLevel)) {//省级
            params.put("regParm", regionId.substring(0, 2) + "%");
        } else if ("3".equals(orgLevel)) {//市级
            params.put("regParm", regionId.substring(0, 4) + "%");
        } else if ("4".equals(orgLevel)) {//县级
            params.put("regParm", regionId + "%");
        } else if ("1".equals(orgLevel)) {//部级
            params.put("regParm", null);
        }
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        if (!"1".equals(orgLevel)) {
            if (StringUtil.isNotBlank(p.getQueryXian())) {
                params.put("taskAreaId", p.getQueryXian() + "%");
            } else if (StringUtil.isNotBlank(p.getQueryShi())) {
                params.put("taskAreaId", p.getQueryShi() + "%");
            } else if (StringUtil.isNotBlank(p.getQuerySheng())) {
                params.put("taskAreaId", p.getQuerySheng() + "%");
            }
        }
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        params.put("taskName", StringUtil.isNotBlank(r.getTaskName()) ? "%" + r.getTaskName() + "%" : null);
        params.put("state", StringUtil.isNotBlank(r.getState()) ? r.getState() : null);
        if ("1".equals(org.getOrgLevel())) {
            params.put("taskLevel", "0");//部级
        } else if ("2".equals(org.getOrgLevel()) || "3".equals(org.getOrgLevel()) || "4".equals(org.getOrgLevel())) {
            params.put("taskLevel", "1");//省级 //市//县
        } else {
            params.put("taskLevel", "-1");//不显示
        }
        PageInfo<List<Map<String, Object>>> i = provider.getCheckTaskList(params);
        return i;
    }

    public PageInfo<List<Map<String, Object>>> getReCheckTaskList(String token,AsmsRecheckTask r, String dateBegin, String dateEnd, int pageNum, int pageSize) {
        //机构+级别确定管辖范围
        Organization org = WebUtil.getCurrentUser(token).getOrganization();
        String orgLevel = org.getOrgLevel();//机构级别
        String regionId = org.getRegionId();//区域
        String regParm=null;
        if(!"1".equals(orgLevel)){//非部级
            regParm=regionId.substring(0,(Integer.parseInt(orgLevel)-1)*2)+"%";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("regParm",regParm);
        //page
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        params.put("dateBegin", dateBegin);
        params.put("dateEnd", dateEnd);
        params.put("recheckTaskName", StringUtil.isNotBlank(r.getRecheckTaskName()) ? "%" + r.getRecheckTaskName() + "%" : null);
        params.put("recheckTaskYear", StringUtil.isNotBlank(r.getRecheckTaskYear()) ? "%" + r.getRecheckTaskYear() + "%" : null);
        params.put("state", StringUtil.isNotBlank(r.getState()) ? r.getState() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getReCheckTaskList(params);
        return i;
    }

    public AsmsCheckTask getCheckTaskById(String id) {
        return provider.getCheckTaskById(id);
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsCheckBearUnit> getUnitByTaskId(String taskId) {
        return provider.getUnitByTaskId(taskId);
    }

    public List<AsmsJcStandard> getJcListByTaskId(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", id);
        return provider.getJcListByTaskId(params);
    }

    public List<AsmsPdStandard> getPdListByTaskId(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", id);
        return provider.getPdListByTaskId(params);
    }

    public List<Map<String, Object>> getObjById(String id) {
        return provider.getObjById(id);
    }

    public AsmsRecheckTask getReCheckTaskById(String id) {
        return provider.getReCheckTaskById(id);
    }

    public List<Map<String, Object>> getReObjById(String id) {
        return provider.getReObjById(id);
    }

    public AsmsRoutineMonitor getRoutineMonitorById(String id) {
        return provider.getRoutineMonitorById(id);
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsRoutineLeadUnit> getRmUnitByTaskId(String taskId) {
        return provider.getRmUnitByTaskId(taskId);
    }

    public AsmsSpecialMonitor getSpecialMonitorById(String id) {
        return provider.getSpecialMonitorById(id);
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsSpecialLeadUnit> getSmUnitByTaskId(String taskId) {
        return provider.getSmUnitByTaskId(taskId);
    }

    /**
     * 报告列表
     * 部级任务：需要通过承担单位id关联该部级任务子任务获取报告
     * 非部级任务：直接通过任务id获取报告
     */
    public PageInfo<List<Map<String, Object>>> listCheckTaskReport(AsmsCheckTaskReport r, int pageNum, int pageSize, String CdUnitId, String taskLevel) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        if ("0".equals(taskLevel)) {
            List<AsmsCheckTask> list = provider.getTaskByCreateOrgIdAndParentTaskId(r.getMonitorTaskId(), CdUnitId);
            if (list.size() != 0) {
                if (list.get(0) != null) {
                    params.put("taskId", list.get(0).getId());
                } else {
                    params.put("taskId", "-1");
                }
            } else {
                params.put("taskId", "-1");
            }
        } else {
            params.put("taskId", StringUtil.isNotBlank(r.getMonitorTaskId()) ? r.getMonitorTaskId() : "-1");//所属任务id[必要条件]
        }
        params.put("monitorTask", StringUtil.isNotBlank(r.getMonitorTask()) ? "%" + r.getMonitorTask() + "%" : null);//所属任务名称
        params.put("years", StringUtil.isNotBlank(r.getYears()) ? r.getYears() : null);//年份
        PageInfo<List<Map<String, Object>>> i = provider.listCheckTaskReport(params);
        return i;
    }

    /**
     * 根据监测信息id获取检测详情列表
     */
    public PageInfo<AdsInfoProject> getReportByJcInfo(AdsInfoProject a, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkInfoId", a.getCheckInfoId());//检测信息id
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        PageInfo<AdsInfoProject> adsList = provider.getPageInfoWithCheckInfoId(params);//检测详情列表
        return adsList;
    }

    /**
     * 根据检测信息id获取检测信息基本信息
     */
    public List<Map<String, Object>> getJbInfoByJcId(AdsInfoProject a) {
        return provider.getCheckInfo(a.getCheckInfoId());
    }

    /**
     * 根据报告获取监测信息列表
     */
    public PageInfo<AdsCheckInfo> getJclistByReport(AsmsCheckTaskReport r, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        params.put("organTaskId", r.getOrganTaskId());//机构任务id（对应监测对象id）
        PageInfo<AdsCheckInfo> pages = provider.getPageInfoWithOrgTaskId(params);
        return pages;
    }

    /**
     * 根据id获取抽样单详情
     */
    public Map<String, Object> getTaskSampleById(AdsMonitorSample mon) {
        return provider.findAdsMonitorSampleById(mon);
    }

    /**
     * 根据检测对象id&父级任务id获取抽样单列表[监督抽查]
     */
    public PageInfo<AdsMonitorSample> getTaskSampleListByJcInfo(AsmsCheckTask mon, QueryParameter q, String taskObjId, String JdccCdUnit, String sonTaskId) {
        Map<String, Object> params = new HashMap<>();
        List<String> taskIds = new ArrayList<>();
        AsmsCheckTask task = this.getCheckTaskById(mon.getId());
        if ("0".equals(task.getTaskLevel())) {//部级用户
            if (sonTaskId == null) {//部级用户选择子任务
                List<AsmsCheckTask> sonTasks = provider.getTaskByCreateOrgIdAndParentTaskId(task.getId(), JdccCdUnit);
                if (sonTasks.size() != 0) {
                    for (AsmsCheckTask t : sonTasks) {
                        taskIds.add(t.getId());
                    }
                }
                params.put("supId", taskIds);
            } else {
                taskIds.add(sonTaskId);
                params.put("supId", taskIds);
            }

        } else {
            taskIds.add(mon.getId());
            params.put("supId", taskIds);
        }
        //page
        params.put("pageNum", ((q.getStart() + 1) / q.getLength()) + 1);
        params.put("pageSize", q.getLength());
        //query
        if (taskIds.size() == 0) {
            PageInfo<AdsMonitorSample> lis = new PageInfo<AdsMonitorSample>();
            List<AdsMonitorSample> l = new ArrayList<>();
            lis.setList(l);
            lis.setTotal(0);
            return lis;
        }
        params.put("organTaskId", taskObjId);//检测对象id
        return provider.getAdsMonitorSamplePageInfoByCode(params);
    }

    /**
     * 根据任务id与牵头单位id获取抽样单列表[例行监测、专项检测]
     */
    public PageInfo<AdsMonitorSample> getZLTaskSampleListByJcInfo(AsmsCheckTask mon, QueryParameter q) {
        Map<String, Object> params = new HashMap<>();
        //page
        params.put("pageNum", ((q.getStart() + 1) / q.getLength()) + 1);
        params.put("pageSize", q.getLength());
        //query
        params.put("supId", mon.getId());//任务id
        params.put("organId", mon.getParentTaskId());//牵头单位id
        return provider.findAllAdsMonitorSample(params);
    }

    /**
     * 根据父任务id和承担单位id获取子任务
     */
    public List<AsmsCheckTask> getTasksByCreateOrgIdAndParentTaskId(AsmsCheckTask r) {
        return provider.getTaskByCreateOrgIdAndParentTaskId(r.getParentTaskId(), r.getCreateOrgId());
    }

    /**
     * 例行监测查询已有任务所有的发布机构信息
     */
    public List<AsmsRoutineMonitor> getRmOrgList(String token) {
        Organization org = WebUtil.getCurrentUser(token).getOrganization();
        String orgLevel = org.getOrgLevel();
        String regionId = org.getRegionId();
        String regParm=null;
        if(!"1".equals(orgLevel)){
        regParm=regionId.substring(0, (Integer.parseInt(orgLevel)-1)*2)+"%";
        }
        Map<String,Object> params=new HashMap<>();
        params.put("regParm",regParm);
        return provider.findRmOrgList(params);
    }

    /**
     *  例行监测查询已有任务所有的牵头单位信息
     */
    public List<AsmsRoutineLeadUnit> getRmLeadUnitList(String token){
        Organization org = WebUtil.getCurrentUser(token).getOrganization();
        String orgLevel = org.getOrgLevel();
        String regionId = org.getRegionId();
        String regParm=null;
        if(!"1".equals(orgLevel)){
            regParm=regionId.substring(0, (Integer.parseInt(orgLevel)-1)*2)+"%";
        }
        Map<String,Object> params=new HashMap<>();
        params.put("regParm",regParm);
        return provider.getRmLeadUnitList(params);
    }

    /**
     * 专项监测查询已有任务所有的发布机构信息
     */
    public List<AsmsSpecialMonitor> getSpecialRmOrgList(String token) {
        Organization org = WebUtil.getCurrentUser(token).getOrganization();
        String orgLevel = org.getOrgLevel();
        String regionId = org.getRegionId();
        String regParm=null;
        if(!"1".equals(orgLevel)){
            regParm=regionId.substring(0, (Integer.parseInt(orgLevel)-1)*2)+"%";
        }
        Map<String,Object> params=new HashMap<>();
        params.put("regParm",regParm);
        return provider.findSpecialRmOrgList(params);
    }

    /**
     *  专项监测查询已有任务所有的牵头单位信息
     */
    public List<AsmsSpecialLeadUnit> getSpecialRmLeadUnitList(String token){
        Organization org = WebUtil.getCurrentUser(token).getOrganization();
        String orgLevel = org.getOrgLevel();
        String regionId = org.getRegionId();
        String regParm=null;
        if(!"1".equals(orgLevel)){
            regParm=regionId.substring(0, (Integer.parseInt(orgLevel)-1)*2)+"%";
        }
        Map<String,Object> params=new HashMap<>();
        params.put("regParm",regParm);
        return provider.findSpecialRmLeadUnitList(params);
    }

    /**
     * 根据监测系统提供的接口获取牵头单位例行监测报告列表
     * @param adsFile
     * @param taskId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getPageInfoAdsFileBySupId(AdsFile adsFile,String taskId,int pageNum, int pageSize){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("supId",taskId);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        return provider.getPageInfoAdsFileBySupId(queryParams);
    }
}
