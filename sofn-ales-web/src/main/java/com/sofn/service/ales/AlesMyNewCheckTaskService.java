package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.provider.ads.AdsCheckInfoProvider;
import com.sofn.provider.ads.AdsInfoProjectProvider;
import com.sofn.provider.ads.AdsMonitorSampleProvider;
import com.sofn.provider.ales.AlesTaskSampleProvider;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import com.sofn.provider.asms.AsmsMonitorObjectProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.web.ales.AlesEnum;
import jodd.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@Service
public class AlesMyNewCheckTaskService extends BaseService<AlesTaskSampleProvider, AlesTaskSample> {

    private AdsMonitorSampleProvider adsMonitorSampleProvider;//检测系统-抽样单管理

    private AdsInfoProjectProvider adsInfoProjectProvider;

    private AdsCheckInfoProvider adsCheckInfoProvider;

    private AsmsCheckTaskProvider asmsCheckTaskProvider;//监管系统-监督检查

    private SysUserProvider sysUserProvider;//

    private AsmsMonitorObjectProvider asmsMonitorObjectProvider;
    //private AlesTaskSampleProvider alesTaskSampleProvider;

    @DubboReference
    public void setProvider(AlesTaskSampleProvider provider) {
        this.provider = provider;
    }

    /*@DubboReference
    public void  setProvider(AlesTaskSampleProvider alesTaskSampleProvider){
        this.alesTaskSampleProvider = alesTaskSampleProvider;
    }*/

    @DubboReference
    public void setProvider(AsmsCheckTaskProvider asmsCheckTaskProvider) {
        this.asmsCheckTaskProvider = asmsCheckTaskProvider;
    }

    @DubboReference
    public void setProvider(AdsCheckInfoProvider adsCheckInfoProvider) {
        this.adsCheckInfoProvider = adsCheckInfoProvider;
    }

    @DubboReference
    public void setProvider(AdsInfoProjectProvider adsInfoProjectProvider) {
        this.adsInfoProjectProvider = adsInfoProjectProvider;
    }

    @DubboReference
    public void setProvider(AdsMonitorSampleProvider adsMonitorSampleProvider) {
        this.adsMonitorSampleProvider = adsMonitorSampleProvider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsMonitorObjectProvider asmsMonitorObjectProvider) {
        this.asmsMonitorObjectProvider = asmsMonitorObjectProvider;
    }
    /*@DubboReference
    public void setProvider(AsmsMonitorObjectProvider AsmsMonitorObjectProvider)*/
    /**
     * 新任务
     */
    public List<Map<String, Object>> getCheckTaskByUser(AsmsCheckTask checkTask, String token) {
        Organization org = sysUserProvider.findSysUserOrganization(token);
        String orgId = org.getOrgId();
        Map<String, Object> params = new HashMap<>();
        if (checkTask.getTaskBeginTime() != null) {
            params.put("taskBeginTime", new SimpleDateFormat("yyyy-MM-dd").format(checkTask.getTaskBeginTime()));
        }
        if (checkTask.getTaskEndTime() != null) {
            params.put("taskEndTime", new SimpleDateFormat("yyyy-MM-dd").format(checkTask.getTaskEndTime()));
        }
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
        params.put("orgId", orgId);
       //params.put("state", AlesEnum.PUBLISHED.getCode());//任务状态码
      return asmsCheckTaskProvider.getNewCheckTaskByUser(params);
       // return asmsCheckTaskProvider.getCheckTaskByUser(params);//老的新任务接口

    }

    /**
     * 所有任务
     */
    public PageInfo<List<AsmsCheckTask>> getTaskPagesByToken(AsmsCheckTask checkTask, int pageNum, int pageSize, String token) {
        Organization org = sysUserProvider.findSysUserOrganization(token);
        String orgId = org.getOrgId();
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        if (checkTask.getTaskBeginTime() != null) {
            params.put("taskBeginTime", new SimpleDateFormat("yyyy-MM-dd").format(checkTask.getTaskBeginTime()));
        }
        if (checkTask.getTaskEndTime() != null) {
            params.put("taskEndTime", new SimpleDateFormat("yyyy-MM-dd").format(checkTask.getTaskEndTime()));
        }
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
        params.put("cyUnitId", orgId);
        params.put("state", AlesEnum.PUBLISHED.getCode());
        return asmsCheckTaskProvider.listByOrg(params);
    }

    /**
     * 根据id获取监督抽查任务信息
     */
    public AsmsCheckTask queryByid(String taskId) {
        return asmsCheckTaskProvider.queryById(taskId);
    }

    /**
     * 根据ID获取检测对象信息
     */
    public AsmsMonitorObject findAsmsMonitorObjectById(String id){
        return asmsMonitorObjectProvider.queryById(id);
    }

    /**
     * 检测信息
     */
    public PageInfo<List<Map<String, Object>>> getMyTaskObjById(AsmsCheckTask checkTask, String dateBegin, String dateEnd, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("taskId", checkTask.getId());
        return asmsCheckTaskProvider.getMyTaskObjById(params);
    }

    /**
     * 历史任务
     */
    public PageInfo<List<Map<String, Object>>> getHistoryCheckTaskListByUser(AsmsCheckTask checkTask, String dateBegin, String dateEnd, int pageNum, int pageSize, String token) {
        Organization org = sysUserProvider.findSysUserOrganization(token);
        String orgId = org.getOrgId();
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        params.put("dateBegin", StringUtil.isNotBlank(dateBegin) ? dateBegin : null);
        params.put("dateEnd", StringUtil.isNotBlank(dateEnd) ? dateEnd : null);
        params.put("taskName", StringUtil.isNotBlank(checkTask.getTaskName()) ? "%" + checkTask.getTaskName() + "%" : null);
       // params.put("state", AlesEnum.PUBLISHED.getCode());
        params.put("orgId", orgId);
      //  params.put("isSample", AlesEnum.IS_SAMPLE.getCode());
        return asmsCheckTaskProvider.getHistoryCheckTask(params);
    }

    /**
     * 根据任务id获取检测单位列表
     */
    public List<AsmsCheckBearUnit> getUnitByTaskId(String taskId) {
        return asmsCheckTaskProvider.getUnitByTaskId(taskId);
    }


    /**
     * 监测信息下抽样单列表
     */
    public PageInfo<AlesTaskSample> taskSampleListByJcInfo(AlesTaskSample taskSample, QueryParameter queryParameter) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", ((queryParameter.getStart() + 1) / queryParameter.getLength()) + 1);
        params.put("pageSize", queryParameter.getLength());
        params.put("organTaskId", StringUtil.isNotBlank(taskSample.getOrganTaskId()) ? taskSample.getOrganTaskId() : null);//关联监测信息id
       long lo = provider.judgeisSync(taskSample.getOrganTaskId());
        if(lo == 2){
            asmsCheckTaskProvider.updateObjIsSample(taskSample.getOrganTaskId(), AlesEnum.PART_SAMPLE.getCode());//更新监管任务状态(部分提交)
        }else if(lo == 1) {
            asmsCheckTaskProvider.updateObjIsSample(taskSample.getOrganTaskId(), AlesEnum.IS_SAMPLE.getCode());//更新监管任务状态(提交)
        }else {
            asmsCheckTaskProvider.updateObjIsSample(taskSample.getOrganTaskId(), AlesEnum.NON_SAMPLE.getCode());//更新监管任务状态(提交)
        }
        return provider.list(params);
    }

    /**
     * 新增抽样单
     */
    public boolean addSample(AlesTaskSample taskSample) {
        taskSample.setResult(AlesEnum.NOTDETECTED.getCode());
        taskSample.setIsSync(AlesEnum.NON_SAMPLE.getCode());
        AlesTaskSample alesTaskSample = this.add(taskSample);
        if (alesTaskSample != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改抽样单
     */
    public boolean updSample(AlesTaskSample taskSample) {
        super.update(taskSample);
        return true;
    }

    /**
     * 根据id获取抽样单信息
     */
    public AlesTaskSample getSampleById(String id) {
        return super.queryById(id);
    }

    /**
     * findAdsMonitorSample
     */
    public AlesTaskSample findAdsMonitorSample(String sampleCode) {
        return provider.getLocalSampleBySampleCode(sampleCode);
    }

    /**
     * 根据id删除抽样单
     */
    public boolean delSampleById(String id) {
        super.delete(id);
        return true;
    }

    /**
     * 提交抽样单
     * @param currentUser
     * @param taskId
     * @return
     */
    public String sinleSyncToAds(CurrentUser currentUser,String taskId,String code,String id) {
        List<AsmsMonitorObject> amsMobjlist = new ArrayList<>();
        String isSample = AlesEnum.NON_SAMPLE.getCode();
        List<Boolean> successful = new ArrayList<>();
            AsmsMonitorObject asmsMonitorObject = asmsMonitorObjectProvider.findAsmsMonitorObjectById(taskId);//获取检测对象表
            amsMobjlist.add(asmsMonitorObject);
        String isHaveDate = bliveBedomt(amsMobjlist,code);//判断数据存在
        if (AlesEnum.BLIVESUCCESS.getCode().equals(isHaveDate)) {
            successful.addAll(syncSamplesToAdsSystem(currentUser,amsMobjlist,code,id));//同步监测/监管/执法/相关联表信息
        }
        if (!successful.isEmpty()) {
            for (int i = 0; i < successful.size(); i++) {
                if (!successful.get(i)) {
                    if (i != 0) {
                        isSample = AlesEnum.PART_SAMPLE.getCode();//部分提交抽样单
                    } else {
                        isSample = AlesEnum.NON_SAMPLE.getCode();//未提交抽样单
                    }
                    break;
                }else{
                    isSample = AlesEnum.IS_SAMPLE.getCode();//已提交抽样单
                }
            }
        }else{
            isSample = AlesEnum.NO_SAMPLE.getCode();//无抽样单
            return isSample;
        }
        /**
         * 判断任务id下的监测对象是否还有未提交未上报的对象？
         */

        //asmsCheckTaskProvider.updateTaskIsSample(taskId, isSample);//更新监管任务状态。(现在不能将任务变成历史任务,情况查明原因);
        return isSample;
    }
    /**
     * 判断数据存在
     */
    private String bliveBedomt(List<AsmsMonitorObject> monitorObjects,String code) {
        List<AlesTaskSample> samples = new ArrayList<AlesTaskSample>();
        if (monitorObjects == null || monitorObjects.isEmpty()) {
            return AlesEnum.NO_SON_OBJECT.getCode();
        }
        if(code != null){
            samples  = provider.getSampleListByjcxxIds(monitorObjects.get(0).getId(),code);
            if (samples == null || samples.isEmpty()) {
                return AlesEnum.NO_SAMPLE.getCode();
            }
        }else {
            for (AsmsMonitorObject asmsMonitorObject : monitorObjects) {
                samples = provider.getSampleListByjcxxId(asmsMonitorObject.getId());
                if (samples == null || samples.isEmpty()) {
                    return AlesEnum.NO_SAMPLE.getCode();
                }
            }
        }
        return AlesEnum.BLIVESUCCESS.getCode();
    }
    /**
     * 同步数据
     */
    private List<Boolean> syncSamplesToAdsSystem(CurrentUser currentUser,List<AsmsMonitorObject> monitorObjects,String code,String id) {
        List<Boolean> successful = new ArrayList<>();
        List<AlesTaskSample> alesSampleList  = new ArrayList<>();
        List<AdsMonitorSample> adsSampleList = new ArrayList<>();
        for (AsmsMonitorObject asmsMonitorObject : monitorObjects) {
            if(code != null){
                AlesTaskSample  alesSample = provider.getLocaloleSampleBySampleCode(code,id);// 根据样品编码查询抽样单
                alesSampleList.add(alesSample);
            }else {
              alesSampleList  = provider.getSampleListByjcxxId(asmsMonitorObject.getId());// 根据监测信息id获取下面所有抽样单对象
            }
            for (AlesTaskSample alesSample : alesSampleList) {//遍历对象
                if (!AlesEnum.IS_SAMPLE.getCode().equals(alesSample.getIsSync())) {//
                    AdsMonitorSample adsSample = new AdsMonitorSample();
                    try {
                        BeanUtils.copyProperties(adsSample, alesSample);
                        adsSample.setSpecification(alesSample.getSpecificationGx());
                        adsSample.setComment(alesSample.getNote());
                        adsSample.setSampleBarCode(adsSample.getSampleCode());//抽样条码，暂时没用到，先传样品编码，后期有需要再修改
                        adsSample.setSampleId(currentUser.getOrganization().getOrgId());//抽样单位key，暂时也没用到，先传机构ID
                        adsSample.setSampleNumber(alesSample.getSampleAmount());
                        adsSample.setSampleNumberUnit(alesSample.getSampleUnitType());
                        adsSample.setSampleBaseUnit(alesSample.getSampleBaseUnitType());
                        adsSample.setFileName(alesSample.getAttachmentNames());
                        adsSample.setFileAddress(alesSample.getAttachments());
                        adsSample.setSamplePlace(alesSample.getSamplePlace());
                        adsSample.setProductionsTatus(alesSample.getSampleSource());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    adsSampleList.add(adsSample);
                }
            }
            boolean isSuccessful = adsMonitorSampleProvider.getAdsMonitorSampleList(adsSampleList);//数据同步监测系统
            if (isSuccessful) {//更新本地抽样单状态
                if(code != null){
                    provider.sampleUpdate(asmsMonitorObject.getId(),AlesEnum.IS_SAMPLE.getCode(),code,id);
                }else {
                    provider.syncIsTrue(asmsMonitorObject.getId(), AlesEnum.IS_SAMPLE.getCode());
                }
                //监管系统检测对象下是否有未提交的抽样单
               boolean bool = asmsMonitorObjectProvider.findAsmsMonitorByIdAndIsSample(asmsMonitorObject.getId().toString(),AlesEnum.NON_SAMPLE.getCode().toString());
                if(bool){//更新监管系统检测对象状态
                    asmsCheckTaskProvider.updateObjIsSample(asmsMonitorObject.getId(), AlesEnum.PART_SAMPLE.getCode());//部分提交
                }else {
                    asmsCheckTaskProvider.updateObjIsSample(asmsMonitorObject.getId(), AlesEnum.IS_SAMPLE.getCode());//已提交
                }
            }
            successful.add(isSuccessful);
        }

        return successful;
    }

    /**
     * 样品检测信息列表
     */
    public PageInfo<AdsCheckInfo> sampleAndResultList(AlesTaskSample alesTaskSample, QueryParameter queryParameter) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AlesServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("organTaskId", alesTaskSample.getOrganTaskId());
        return adsCheckInfoProvider.getPageInfoWithOrgTaskId(params);
    }

    /**
     * 检测结果
     */
    public List<AdsInfoProject> sampleResultList(AdsInfoProject adsInfoProject) {
        Map<String, Object> parame = new HashMap<>();
        parame.put("checkInfoId", adsInfoProject.getCheckInfoId());
        return adsInfoProjectProvider.getInfoForId(parame);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    /**
     *根据追溯码统计抽样单任务信息
     */
  /*  public Long countSubjectinfoTraceabilityCode(String productTraceabilityCode){
        String code ="GDJDCC263012018000004";
        Map<String, Object> param = new HashMap<>();
        param.put("productTraceabilityCode", productTraceabilityCode);
        long  count = provider.countSubjectinfoTraceabilityCode(param);
        return count;
    }*/
}
