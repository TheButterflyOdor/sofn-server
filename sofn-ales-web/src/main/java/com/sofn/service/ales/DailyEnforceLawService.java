package com.sofn.service.ales;



import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.ales.DailyEnforceLawProvider;
import com.sofn.provider.asms.AsmsBaseInspectionProvider;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.asms.AsmsSubjEntBadrecordProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 4:50
 */

@Service
public class DailyEnforceLawService extends BaseService<DailyEnforceLawProvider,AlesDailyEnforceLaw> {
    @DubboReference
    public void setDailyEnforceLawProvider(DailyEnforceLawProvider provider) {
        this.provider = provider;
    }

    private SysUserProvider sysUserProvider;

    private AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    private AsmsBaseInspectionProvider baseInspectionProvider;

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider asmsSubjDetectionProvider) {
        this.asmsSubjDetectionProvider = asmsSubjDetectionProvider;
    }

    @DubboReference
    public void setProvider(AsmsBaseInspectionProvider baseInspectionProvider){
        this.baseInspectionProvider = baseInspectionProvider;
    }

    private AsmsSubjEntBadrecordProvider subjEntBadrecordProvider;
    @DubboReference
    public void setEntBadRecordProvider(AsmsSubjEntBadrecordProvider provider){
        subjEntBadrecordProvider = provider;
    }

    private TtsScltxxcjRegiterProvider regiterProvider;
    @DubboReference
    public void setRegiterProvider(TtsScltxxcjRegiterProvider regiterProvider){
        this.regiterProvider = regiterProvider;
    }
    private AsmsSubjEntBadrecordProvider asmsSubjEntBadrecordProvider;
    @DubboReference
    public void setAsmsSubjEntBadrecordProvider(AsmsSubjEntBadrecordProvider provider){
        this.asmsSubjEntBadrecordProvider = asmsSubjEntBadrecordProvider;
    }

    public PageInfo getDailyEnforceLawList(AlesDailyEnforceLaw dailyEnforceLaw, String areaId, String taskYear, String dateBegin, String dateEnd,
                                           int pageNum, int pageSize, String queryCon,String createOrgId,String enforceLawResultFlag) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("areaId", areaId);
        queryParams.put("taskYear", taskYear);
        queryParams.put("dateBegin", dateBegin);
        queryParams.put("dateEnd", dateEnd);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("queryCon", queryCon);
        queryParams.put("createOrgId", createOrgId);
        if(enforceLawResultFlag!=null&&StringUtils.isNotBlank(enforceLawResultFlag)) {
            queryParams.put("enforceLawResultFlag", enforceLawResultFlag.split(","));
        }
        return provider.getDailyEnforceLawList(queryParams);
    }

    /**
     * 新增执法日志
     * 修改执法日志
     *
     * @param aisDailyEnforceLaw
     * @return
     */
    public int addDailyEnforceLaw(AlesDailyEnforceLaw aisDailyEnforceLaw) {
        if (StringUtils.isNullEmpty(aisDailyEnforceLaw.getId())) {
            aisDailyEnforceLaw.setId(StringUtils.getUUIDString().toString().replace("-", ""));
            aisDailyEnforceLaw.setDelFlag("N");
            aisDailyEnforceLaw.setCreateBy(WebUtil.getCurrentUserId());
            aisDailyEnforceLaw.setCreateTime(new Date());
            /**
             * 如果现场巡查不合格 将数据同步到不良记录
             */
            if(aisDailyEnforceLaw.getEnforceLawResultFlag()!=null&&aisDailyEnforceLaw.getEnforceLawResultFlag().equals("2")){
                this.addSubjEntBadrecord(aisDailyEnforceLaw);
            }
            return provider.addDailyEnforceLaw(aisDailyEnforceLaw);
        } else {
            aisDailyEnforceLaw.setUpdateTime(new Date());
            aisDailyEnforceLaw.setUpdateBy(WebUtil.getCurrentUserId());
            return provider.updateDailyEnforceLaw(aisDailyEnforceLaw);
        }

    }

    /**
     * 根据ID查看当前执法日志
     *
     * @param id
     * @return
     */
    public AlesDailyEnforceLaw findDailyEnforceLaw(String id) {

        return provider.findDailyEnforceLawById(id);
    }

//    /**
//     *
//     * @param id
//     * @return
//     */
//    public int deleteDailyEnforceLaw（String id）{
//
//        return provider.deleteDailyEnforceLaw(id);
//    }
    /**
     * 获取用户列表总数
     */
    public int getUsersCount(String token) {
        Organization o = this.getOrganizationByToken(token);
        if (o != null) {
            return sysUserProvider.getRecordsTotal(null, "1", o.getId());
        } else {
            return 0;
        }
    }

    /**
     * 获取分页数据
     */
    public List<SysUserBean> getUserList(Page page,String keyword, String token) {
        Organization o = this.getOrganizationByToken(token);
        if (o != null) {
            return sysUserProvider.getSysUserList(page, keyword, "1", o.getId());
        } else {
            return null;
        }
    }

    /**
     * 获取机构用户
     * */
    public PageInfo<SysUserBean> getSysUserListByOrgId(com.sofn.util.Page page, String orgId, String userName){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("userType","NORMAL");//只查询机构内部用户，如果查全部则去掉该查询条件
        queryParams.put("status","1");
        queryParams.put("organizationId",orgId);
        queryParams.put("userName",userName);
        return baseInspectionProvider.getSysUserListByOrgId(queryParams);
    }

    /**
     * 根据登录用户所属机构行政区划查询所有检测机构
     */
    public PageInfo getOrgs(AsmsSubjDetection subjDetection, com.sofn.util.Page page, String token) {
        Map<String, Object> queryMap = new HashMap<>();
        Organization o = this.getOrganizationByToken(token);//用户所属机构
        String orgLevel = o.getOrgLevel();
        String regionId = o.getRegionId();
        if ("2".equals(orgLevel)) {//省级
            queryMap.put("regParm", regionId.substring(0, 2) + "%");
        } else if ("3".equals(orgLevel)) {//市级
            queryMap.put("regParm", regionId.substring(0, 4) + "%");
        } else if ("4".equals(orgLevel)) {//县级
            queryMap.put("regParm", regionId + "%");
        } else if ("1".equals(orgLevel)) {//部级
            queryMap.put("regParm", null);
        }
        queryMap.put("dtName", StringUtil.isNotBlank(subjDetection.getDtName()) ? subjDetection.getDtName() : null);
        queryMap.put("areaId", StringUtil.isNotBlank(subjDetection.getDtAreaId()) ? subjDetection.getDtAreaId() : null);
        queryMap.put("dateBegin", null);
        queryMap.put("dateEnd", null);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return asmsSubjDetectionProvider.getSubjDetectionList(queryMap);
    }

    /**
     * 验证输入的任务名称是否已经存在
     * @param taskName
     * @return
     */
    public Boolean equalsTaskName(String taskName){
        if(provider.findByName(taskName)==0){
            return  true;
        }else {
            return false;
        }
    }
    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    /**
     * 如果现场巡查不合格 添加到不良记录
     * @param aisDailyEnforceLaw
     * @return
     */
    public int addSubjEntBadrecord(AlesDailyEnforceLaw aisDailyEnforceLaw){
        AsmsSubjEntBadrecord subjEntBadrecord = new AsmsSubjEntBadrecord();
        subjEntBadrecord.setId(StringUtils.getUUIDString());
        subjEntBadrecord.setDelFlag("N");
        subjEntBadrecord.setEnterpriseId(aisDailyEnforceLaw.getEnterpriseId());
        subjEntBadrecord.setBadrecordContent(aisDailyEnforceLaw.getEnforceLawResult());
        subjEntBadrecord.setCreateBy(WebUtil.getCurrentUserId());
        subjEntBadrecord.setCreateTime(new Date());
        subjEntBadrecord.setSourceId(aisDailyEnforceLaw.getId());
        subjEntBadrecord.setSourceType("4");
        //现场巡查暂时没有附件
        subjEntBadrecord.setBadrecordFile("");
        subjEntBadrecord.setBadrecordFileName("现场巡查暂时没有附件");
        return subjEntBadrecordProvider.addSubjEntBadrecord(subjEntBadrecord);
    }

    /**
     * 获取生产经营主体信息
     * @param entity
     * @return
     */
    public TtsScltxxcjRegiter findSubjEntById(TtsScltxxcjRegiter entity){
        return regiterProvider.selectByEntityId(entity.getId());
    }

    /**
     * 获取生产经营主体
     * @param entityIdCode
     * @return
     */
    public TtsScltxxcjRegiter findEnterpriseById(String entityIdCode){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("entityIdCode",entityIdCode);
        return regiterProvider.selectByEntityIdCode(params);
    }
    /**
     * 获取临时生产经验主体
     * @param entityIdCode
     * @return
     */
    public AsmsSubjEntTemp findEnterpriseTmpById(String entityIdCode){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("entityIdCode",entityIdCode);
        return asmsSubjEntBadrecordProvider.selectByEntityIdCode(params);
    }


}
