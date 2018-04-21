package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.asms.AsmsBaseInspectionProvider;
import com.sofn.provider.asms.AsmsCommonOpinionProvider;
import com.sofn.provider.asms.AsmsSubjEntBadrecordProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.tts.TtsScltxxcjNotificationProvider;
import com.sofn.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sofn
 * @version 2016年08月25日 下午 4:50
 */
@Service
public class AsmsBaseInspectionService extends BaseService<AsmsBaseInspectionProvider,AsmsBaseInspection> {

    @DubboReference
    public void setProvider(AsmsBaseInspectionProvider provider){
        this.provider = provider;
    }

    private AsmsSubjEntBadrecordProvider subjEntBadrecordProvider;
    @DubboReference
    public void setEntBadRecordProvider(AsmsSubjEntBadrecordProvider provider){
        subjEntBadrecordProvider = provider;
    }

    private SysUserProvider sysUserProvider;
    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    private AsmsCommonOpinionProvider commonOpinionProvider;
    @DubboReference
    public void setProvider(AsmsCommonOpinionProvider commonOpinionProvider) {
        this.commonOpinionProvider = commonOpinionProvider;
    }

    private TtsScltxxcjNotificationProvider ttsScltxxcjNotificationProvider;
    @DubboReference
    public void setProvider(TtsScltxxcjNotificationProvider ttsScltxxcjNotificationProvider) {
        this.ttsScltxxcjNotificationProvider = ttsScltxxcjNotificationProvider;
    }

    @Transactional
    public int addBaseInspection(CurrentUser user, AsmsBaseInspection asmsBaseInspection, boolean saveCommonOpinion){
        //定义返回结果
        int result = 0;
        //获取前端常用意见
        String optinion = asmsBaseInspection.getInspectionView().trim();
        AsmsCommonOpinion asmsCommonOpinion = new AsmsCommonOpinion();
        //通过saveCommonOpinion判断是否保存常用意见
        if(saveCommonOpinion){
            List<AsmsCommonOpinion> list = commonOpinionProvider.getOpinionListByUserId(user.getId());
            //如果已有内容完全一样的巡查意见，则直接修改时间
            for(Iterator <AsmsCommonOpinion>  iter=list.iterator();iter.hasNext();){
                asmsCommonOpinion =iter.next();
                if (optinion.equals(asmsCommonOpinion.getDictValue())){
                    asmsCommonOpinion.setUpdateTime(new Date());
                    commonOpinionProvider.updateAsmsCommonOpinion(asmsCommonOpinion);
                }
            }
            //长度小于6则新增，大于6则替换最久的一条
            if(list.size()<6){
                asmsCommonOpinion.setUpdateTime(new Date());
                asmsCommonOpinion.setId(StringUtils.getUUIDString());
                asmsCommonOpinion.setUserId(user.getId());
                asmsCommonOpinion.setDictValue(optinion);
                if(optinion.length()>10){
                    //判断获取的意见的的长度  10个字符以外 截取10个字符 直接作为场景意见的标题
                    asmsCommonOpinion.setDictName(optinion.substring(0,10));
                }else {
                    //10个字符以内 不截取 直接作为场景意见的标题
                    asmsCommonOpinion.setDictName(optinion);
                }
                commonOpinionProvider.addAsmsCommonOpinion(asmsCommonOpinion);

            }
            if(list.size()>=6){
                //修改时间最远的那条数据，改为现在的常用意见，并修改时间
                //获取时间最远的那条数据-list已按照时间顺序排列
                asmsCommonOpinion = list.get(0);
                asmsCommonOpinion.setUpdateTime(new Date());
                asmsCommonOpinion.setDictValue(optinion);
                if(optinion.length()>10){
                    //判断获取的意见的的长度  10个字符以外 截取10个字符 直接作为场景意见的标题
                    asmsCommonOpinion.setDictName(optinion.substring(0,10));
                }else {
                    asmsCommonOpinion.setDictName(optinion);
                }
                //10个字符以内 不截取 直接作为场景意见的标题
                commonOpinionProvider.updateAsmsCommonOpinion(asmsCommonOpinion);
            }
        }
        //前端传过来
        String[] inspectionUserName = asmsBaseInspection.getInspectionUserName().split("##");
        asmsBaseInspection.setInspectionUserName(inspectionUserName[0]);
        //如果ID为空，则为新增，不为空则为修改
        if(StringUtils.isNullEmpty(asmsBaseInspection.getId())){
            //新增时生成基地巡查的一些信息
            asmsBaseInspection.setId(StringUtils.getUUIDString());
            asmsBaseInspection.setElCheckState("0");//新增基地巡查的时候执法检查设为未执行
            asmsBaseInspection.setDelFlag("N");
            asmsBaseInspection.setCreateTime(new Date());
            asmsBaseInspection.setCreateBy(user.getId());
            //新增时进行判断，如果不合格则添加一条不良记录--调用不良记录接口
            //这是一个事务
            if(asmsBaseInspection.getInspectionResult()!=null&&asmsBaseInspection.getInspectionResult().equals("2")) {
                this.addSubjEntBadrecord(asmsBaseInspection);
            }
            result = provider.addBaseInspection(inspectionUserName[1],asmsBaseInspection);
            //新增基地巡查,巡查结果为不合格或整改向企业推送消息
            try {
                if("2".equals(asmsBaseInspection.getInspectionResult())||"3".equals(asmsBaseInspection.getInspectionResult())){
                    TtsScltxxcjNotification ttsScltxxcjNotification = new TtsScltxxcjNotification();
                    ttsScltxxcjNotification.setIsread("N");
                    ttsScltxxcjNotification.setTitle("基地巡查结果通知");
                    ttsScltxxcjNotification.setContent(asmsBaseInspection.getInspectionSvName()+"（"+asmsBaseInspection.getInspectionUserName()+"）在" + new SimpleDateFormat("yyyy-MM-dd").format(asmsBaseInspection.getInspectionTime())+"对基地进行巡查，巡查结果为："+("2".equals(asmsBaseInspection.getInspectionResult())?"不合格":"整改")+"，巡查意见为："+asmsBaseInspection.getInspectionView()+"。");
                    ttsScltxxcjNotification.setFromId(user.getId());//当前登录账户id
                    ttsScltxxcjNotification.setFromName(user.getUserName());//当前登录用户名
                    ttsScltxxcjNotification.setToId(asmsBaseInspection.getUserIdcode());//接收消息主体userIdecode
                    ttsScltxxcjNotification.setToName(asmsBaseInspection.getEnterpriseName());//接收消息主体名称
                    ttsScltxxcjNotification.setCreateBy(user.getUserName());//当前登录用户名
                    ttsScltxxcjNotification.setCreateTime(new Date());
                    ttsScltxxcjNotificationProvider.update(ttsScltxxcjNotification);
                }
            }catch (Exception e){
                result = -1;
                e.printStackTrace();
            }
        }else{
            //首先查询原基地巡查结果。合格（1）-再次是合格，不用处理，再次是不合格，添加不良记录。
            //不合格（2）-修改为合格，则删除不良记录，修改为不合格，不用处理。
            AsmsBaseInspection before = this.findBaseInspectionById(asmsBaseInspection.getId());
            //添加不良记录
            if(before.getInspectionResult()!=null&&before.getInspectionResult().equals("1")&&asmsBaseInspection.getInspectionResult()!=null&&asmsBaseInspection.getInspectionResult().equals("2")) {
                this.addSubjEntBadrecord(asmsBaseInspection);
            }
            //删除不良记录
            if(before.getInspectionResult()!=null&&before.getInspectionResult().equals("2")&&asmsBaseInspection.getInspectionResult()!=null&&asmsBaseInspection.getInspectionResult().equals("1")) {
                subjEntBadrecordProvider.deleteBadrecordByPrimaryKey(asmsBaseInspection.getId());
            }
            asmsBaseInspection.setUpdateBy(user.getId());
            asmsBaseInspection.setUpdateTime(new Date());
            if(inspectionUserName!=null&&inspectionUserName.length>1){
                result = provider.updateBaseInspection(inspectionUserName[1],asmsBaseInspection);
            }else{
                result = provider.updateBaseInspection("",asmsBaseInspection);
            }
        }
        return result;
    }

    public int addSubjEntBadrecord(AsmsBaseInspection asmsBaseInspection){
        AsmsSubjEntBadrecord subjEntBadrecord = new AsmsSubjEntBadrecord();
        subjEntBadrecord.setId(StringUtils.getUUIDString());
        subjEntBadrecord.setDelFlag("N");
        subjEntBadrecord.setEnterpriseId(asmsBaseInspection.getEnterpriseId());
        subjEntBadrecord.setBadrecordContent(asmsBaseInspection.getInspectionView());
        subjEntBadrecord.setCreateBy(WebUtil.getCurrentUserId());
        subjEntBadrecord.setCreateTime(new Date());
        subjEntBadrecord.setSourceId(asmsBaseInspection.getId());
        subjEntBadrecord.setSourceType("1");
        subjEntBadrecord.setBadrecordFile(asmsBaseInspection.getInspectionImages());//添加基地巡查现场照片路径
        subjEntBadrecord.setBadrecordFileName(asmsBaseInspection.getInspectionImagesName());//添加基地巡查现场照片名称
        return subjEntBadrecordProvider.addSubjEntBadrecord(subjEntBadrecord);
    }

    public PageInfo getBaseInspectionAllList(AsmsBaseInspection asmsBaseInspection,String dateBegin,String dateEnd,
                                          Page page,String enterpriseIndustryId,String entityScaleId,
                                             String enterpriseName,String area){
        Map<String,Object> queryParams = new HashMap<>();
        //只能获取当前机构
        queryParams.put("inspectionSvId",asmsBaseInspection.getInspectionSvId());
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("inspectionResult",asmsBaseInspection.getInspectionResult());
        queryParams.put("enterpriseIndustryId",enterpriseIndustryId.split(","));
        queryParams.put("entityScaleId",entityScaleId);
        queryParams.put("enterpriseName",enterpriseName);
        queryParams.put("area",area);
        return provider.getBaseInspectionAllList(queryParams);
    }

    public PageInfo getBaseInspectionList(AsmsBaseInspection asmsBaseInspection, String dateBegin, String dateEnd,
                                          Page page, String inspectionPersonId, String inspectionType, String enterpriseName, String area, AsmsInspectionTask task){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("inspectionResult",asmsBaseInspection.getInspectionResult());
        queryParams.put("inspectionPersonId",inspectionPersonId);
        queryParams.put("inspectionType",inspectionType);
        queryParams.put("enterpriseName",enterpriseName);
        queryParams.put("area",area);
        //考核类型有年度、季度、月度3种类型
        String taskDateBegin = "";
        String taskDateEnd = "";
        if(task.getTaskDateType().equals("年度")){
            taskDateBegin = task.getTaskDateYear()+"-1-1";
            taskDateEnd = Integer.valueOf(task.getTaskDateYear())+1+"-1-1";
        }else if(task.getTaskDateType().equals("季度")){
            if ("1".equals(task.getTaskDateQuarter())){
                taskDateBegin = task.getTaskDateYear()+"-1-1";
                taskDateEnd = task.getTaskDateYear()+"-4-1";
            }else if ("2".equals(task.getTaskDateQuarter())){
                taskDateBegin = task.getTaskDateYear()+"-4-1";
                taskDateEnd = task.getTaskDateYear()+"-7-1";
            }else if ("3".equals(task.getTaskDateQuarter())){
                taskDateBegin = task.getTaskDateYear()+"-7-1";
                taskDateEnd = task.getTaskDateYear()+"-10-1";
            }else if ("4".equals(task.getTaskDateQuarter())){
                taskDateBegin = task.getTaskDateYear()+"-10-1";
                taskDateEnd = Integer.valueOf(task.getTaskDateYear())+1+"-1-1";
            }
        }else if(task.getTaskDateType().equals("月度")){
            taskDateBegin = task.getTaskDateYear()+"-"+task.getTaskDateMonths()+"-1";
            if("12".equals(task.getTaskDateMonths())) {
                taskDateEnd = Integer.valueOf(task.getTaskDateYear()) + 1 + "-1-1";
            }else{
                taskDateEnd = task.getTaskDateYear()+"-"+(Integer.valueOf(task.getTaskDateMonths()) + 1 )+ "-1";
            }
        }
        queryParams.put("taskDateBegin",taskDateBegin);
        queryParams.put("taskDateEnd",taskDateEnd);
        return provider.getBaseInspectionList(queryParams);
    }

    /**
     * 获取机构用户
     * */
    public PageInfo<SysUserBean> getSysUserListByOrgId(Page page, String orgId,String userName){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("pageOffset",page.getPageOffset());
        queryParams.put("pageTail",page.getPageTail());
        queryParams.put("userType","NORMAL");//只查询机构内部用户，如果查全部则去掉该查询条件
        queryParams.put("status","1");
        queryParams.put("organizationId",orgId);
        queryParams.put("userName",userName);
        return provider.getSysUserListByOrgId(queryParams);
    }

    public AsmsBaseInspection findBaseInspectionById(String id){
        return provider.findBaseInspectionById(id);
    }

//    public int updateBaseInspection(AsmsBaseInspection asmsBaseInspection){
//        return provider.updateBaseInspection(asmsBaseInspection);
//    }

    public int deleteBaseInspection(String id){
        return provider.deleteBaseInspection(id);
    }

//    获取常用意见列表 用过userId
    public  List<AsmsCommonOpinion> getOpinionListByUserId(String userId){
         return  commonOpinionProvider.getOpinionListByUserId(userId);
    }

}
