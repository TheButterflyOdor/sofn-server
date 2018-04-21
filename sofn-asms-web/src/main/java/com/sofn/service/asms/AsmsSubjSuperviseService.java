package com.sofn.service.asms;

import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.eos.workflow.api.IWFRelativeDataManager;
import com.eos.workflow.api.IWFWorkItemManager;
import com.eos.workflow.data.WFWorkItem;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.model.asms.UserAndSubj;
import com.sofn.model.generator.*;
import com.sofn.provider.asms.AsmsSubjSuperviseProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysOrganizationProvider;
import com.sofn.provider.sys.SysRoleProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.util.BpsConnectorUtil;
import com.sofn.util.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:29
 */
@Service
public class AsmsSubjSuperviseService extends BaseService<AsmsSubjSuperviseProvider,AsmsSubjSupervise>{

    @DubboReference
    public void setProvider(AsmsSubjSuperviseProvider provider){
        this.provider = provider;
    }

    private SysUserProvider sysUserProvider;
    @DubboReference
    public void setSysUserProvider(SysUserProvider sysUserProvider){
        this.sysUserProvider = sysUserProvider;
    }

    private SysRoleProvider sysRoleProvider;
    @DubboReference
    public void setSysRoleProvider(SysRoleProvider sysRoleProvider){
        this.sysRoleProvider = sysRoleProvider;
    }

    private SysDictProvider sysDictProvider;
    @DubboReference
    public void setSysDictProvider(SysDictProvider sysDictProvider){
        this.sysDictProvider = sysDictProvider;
    }

    private SysOrganizationProvider sysOrganizationProvider;
    @DubboReference
    public void setSysOrganizationProvider(SysOrganizationProvider sysOrganizationProvider){
        this.sysOrganizationProvider = sysOrganizationProvider;
    }

    public PageInfo getSubjSuperviseList(String token,AsmsSubjSupervise subjSupervise,Page page,String areaId,String dateBegin,String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("svId",WebUtil.getCurrentUser(token).getOrgId());
        queryMap.put("svLevelId",subjSupervise.getSvLevelId());
        queryMap.put("svLevel",WebUtil.getCurrentUser(token).getOrgLevel());
        queryMap.put("svName",subjSupervise.getSvName());
        queryMap.put("areaId",subjSupervise.getSvAreaId());
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("areaId",areaId);
        queryMap.put("dateBegin", dateBegin);
        queryMap.put("dateEnd", dateEnd);
        return provider.getSubjSuperviseList(queryMap);
    }
    public PageInfo getSubjSuperviseListForSys(String token,AsmsSubjSupervise subjSupervise,Page page,String areaId,String dateBegin,String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        String regionId = currentUser.getRegionId();
        if(StringUtils.isEmpty(currentUser.getOrgLevel())){
            return null ;
        }
        int svLevel = Integer.parseInt(currentUser.getOrgLevel()) -1 ;
        if(svLevel == 0){
            regionId = "";
        }else if(svLevel == 1){
            regionId = regionId.substring(0,2);
        }else if(svLevel == 2){
            regionId = regionId.substring(0,4);
        }else if(svLevel == 3){
            regionId = regionId.substring(0,6);
        }
        int cUsersvLevelStart = svLevel ;
        //int cUsersvLevelEnd = svLevel+1 ;
        //if(svLevel != 0){
        //    cUsersvLevelEnd = cUsersvLevelStart= svLevel +1 ;
        //}
        //queryMap.put("svId",currentUser.getOrgId());
        //queryMap.put("svLevelId",subjSupervise.getSvLevelId());
        //queryMap.put("svLevel",subjSupervise.getSvLevel());
        queryMap.put("svName",subjSupervise.getSvName());
        queryMap.put("cUsersvLevelStart",cUsersvLevelStart+"");
        //只撤能销下一级监管机构,改为管辖范围内所有监管机构
        //queryMap.put("cUsersvLevelEnd",cUsersvLevelEnd+"");
        queryMap.put("cUserRegionId",regionId);
        //queryMap.put("areaId",subjSupervise.getSvAreaId());
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("areaId",areaId);
        queryMap.put("dateBegin", dateBegin);
        queryMap.put("dateEnd", dateEnd);
        return provider.getSubjSuperviseListForSys(queryMap);
    }

    /**
     * 查询监管机构主体变更申请列表
     * @param subjSvChange
     * @param page
     * @param date
     * @return
     */
    public PageInfo getSubjSvChangeList(String token,AsmsSubjSvChange subjSvChange,Page page,String date){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("svName", subjSvChange.getSvName());
        queryMap.put("areaId", subjSvChange.getSvAreaId());
        queryMap.put("date", date);
        queryMap.put("auditState", subjSvChange.getAuditState());
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只获取当前机构
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return provider.getSubjSvChangeList(queryMap);
    }

    //统计变更待审核数量
    public long countChangeToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只能看本机构
        queryMap.put("auditState", "0");
        queryMap.put("areaId", areaId);
        return provider.countChangeToAudit(queryMap);
    }

    public PageInfo getChangeListBySvId(Page page,String token){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("svId", WebUtil.getCurrentUser(token).getOrganization().getOrgId());
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return provider.getChangeListBySvId(queryMap);
    }

    /**
     * 查询监管机构主体注销申请列表
     * @param page
     * @param date
     * @param svName
     * @param areaId
     * @return
     */
    public PageInfo getSubjSvCancelList(String token,Page page,String date,String svName,String areaId,String auditState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        queryMap.put("date", date);
        queryMap.put("svName", svName);
        queryMap.put("areaId", areaId);
        queryMap.put("svLevel", WebUtil.getCurrentUser(token).getOrgLevel());//字典存的0123，组织表存的1234，不用转换自动对应下一级
        queryMap.put("auditState", auditState);
        return provider.getSubjSvCancelList(queryMap);
    }

    //统计注销待审核数量
    public long countCancelToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("svLevel", WebUtil.getCurrentUser(token).getOrgLevel());//字典存的0123，组织表存的1234，不用转换自动对应下一级，只能看下一级
        queryMap.put("auditState", "0");
        queryMap.put("areaId", areaId);
        return provider.countCancelToAudit(queryMap);
    }

    /**
     * 查询监管机构主体撤销申请列表
     * @param page
     * @param date
     * @param svName
     * @param areaId
     * @return
     */
    public PageInfo getSubjSvRevokeList(String token,Page page,String date,String svName,String areaId,String auditState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        queryMap.put("date", date);
        queryMap.put("svName", svName);
        queryMap.put("areaId", areaId);
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只获取当前机构
        queryMap.put("auditState", auditState);
        return provider.getSubjSvRevokeList(queryMap);
    }

    //统计撤销待审核数量
    public long countRevokeToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只获取当前机构
        queryMap.put("auditState", "0");
        queryMap.put("areaId", areaId);
        return provider.countRevokeToAudit(queryMap);
    }

    public int addSubjSupervise(String token,AsmsSubjSupervise subjSupervise){
        //查询是否有监管机构（机构名称、机构代码、所属区域+机构级别+所属行业三者其中的一个相同）--有的话返回一个标识
        Map<String,Object> map = new HashMap();
        map.put("areaId",subjSupervise.getSvAreaId());
        map.put("name",subjSupervise.getSvName());
        map.put("code",subjSupervise.getSvCode());
        map.put("svLevelId",subjSupervise.getSvLevelId());
        map.put("industryId",subjSupervise.getIndustryId().split(","));
        List<AsmsSubjSupervise> list = provider.getSubjSuperviseByCondition(map);
        if(list!=null&&!list.isEmpty()) {
            return 2;
        }
        //设置监管机构一些字段信息
        subjSupervise.setId(StringUtils.getUUIDString());
        subjSupervise.setCreateBy(WebUtil.getCurrentUserId(token));
        subjSupervise.setCreateTime(new Date());
        subjSupervise.setStatus("0");//0-未有任何流程，1-待变更，2-待注销，3-待撤销
        subjSupervise.setDelFlag("N");//N-未删除，Y-逻辑删除
        //同步组织机构
        SysOrganization sysOrganization = new SysOrganization();
        sysOrganization.setOrgName(subjSupervise.getSvName());
        sysOrganization.setOrgType(RSAUtils.Module.ASMS.name());
        sysOrganization.setRegionName(subjSupervise.getSvArea());
        sysOrganization.setTypeId(subjSupervise.getSvTypeId());
        sysOrganization.setLevelId(subjSupervise.getSvLevelId());
        sysOrganization.setOrgLevel(String.valueOf(Integer.valueOf(subjSupervise.getSvLevel())+1));
        if("1".equals(subjSupervise.getSvLevel())){
            sysOrganization.setRegionId(subjSupervise.getSvAreaId().substring(0,2)+"0000");
        }else if("2".equals(subjSupervise.getSvLevel())){
            sysOrganization.setRegionId(subjSupervise.getSvAreaId().substring(0,4)+"00");
        }else{
            sysOrganization.setRegionId(subjSupervise.getSvAreaId());
        }
//        sysOrganization.setRegionId(subjSupervise.getSvAreaId());
        sysOrganization.setOrgId(subjSupervise.getId());
        sysOrganization.setDelFlag("N");
        SysUser sysUser = sysUserProvider.saveSysOranizationUser(sysOrganization,token,subjSupervise.getIndustryValue());//返回机构管理员
        if(sysUser==null||sysUser.getId().isEmpty()){
            return 3;
        }
        int result = provider.addSubjSupervise(subjSupervise);
        return result;
    }

    public int addSubjSvRevoke(String token,AsmsSubjSvRevoke subjSvRevoke){
        subjSvRevoke.setId(StringUtils.getUUIDString());
        subjSvRevoke.setApplyTime(new Date());
        subjSvRevoke.setApplySvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjSvRevoke.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjSvRevoke.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjSvRevoke.setDelFlag("N");//N-未删除，Y-逻辑删除
        sysUserProvider.cancelSysOranization(subjSvRevoke.getSvId(),token,4);//撤销申请中
        return provider.addSubjSvRevoke(subjSvRevoke);
    }

    public int addSubjSvChange(String token,AsmsSubjSvChange subjSvChange){
        subjSvChange.setApplySvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        AsmsSubjSupervise subjSupervise = this.findSubjSuperviseById(subjSvChange.getApplySvId());
        //查询是否有监管机构（机构名称、机构代码不能一样）--有的话返回一个标识0
        Map<String,Object> map = new HashMap();
        map.put("areaId",subjSvChange.getSvAreaId());
        map.put("name",subjSvChange.getSvName());
        map.put("code",subjSvChange.getSvCode());
        map.put("svLevelId",subjSvChange.getSvLevelId());
        map.put("industryId",subjSvChange.getIndustryId().split(","));
        List<AsmsSubjSupervise> list = provider.getSubjSuperviseByCondition(map);
        if(list!=null&&!list.isEmpty()) {
            for (AsmsSubjSupervise as:list){
                if(as.getId().equals(subjSupervise.getId())){
                    continue;
                }else {
                    return 0;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"before\":{").append("svName:").append("\"").append(subjSupervise.getSvName()).append("\",");
        sb.append("svCode:").append("\"").append(subjSupervise.getSvCode()).append("\",");
        sb.append("svType:").append("\"").append(subjSupervise.getSvType()).append("\",");
        sb.append("svTypeId:").append("\"").append(subjSupervise.getSvTypeId()).append("\",");
        sb.append("svLevel:").append("\"").append(subjSupervise.getSvLevel()).append("\",");
        sb.append("svLevelId:").append("\"").append(subjSupervise.getSvLevelId()).append("\",");
        sb.append("svArea:").append("\"").append(subjSupervise.getSvArea()).append("\",");
        sb.append("svAreaId:").append("\"").append(subjSupervise.getSvAreaId()).append("\",");
        sb.append("svAddress:").append("\"").append(subjSupervise.getSvAddress()).append("\",");
        sb.append("svLeader:").append("\"").append(subjSupervise.getSvLeader()).append("\",");
        sb.append("svLeaderPhone:").append("\"").append(subjSupervise.getSvLeaderPhone()).append("\",");
        sb.append("svContact:").append("\"").append(subjSupervise.getSvContact()).append("\",");
        sb.append("svContactPhone:").append("\"").append(subjSupervise.getSvContactPhone()).append("\",");
        sb.append("svContactQQ:").append("\"").append(subjSupervise.getSvContactQQ()).append("\",");
        sb.append("svContactEmail:").append("\"").append(subjSupervise.getSvContactEmail()).append("\",");
        sb.append("industryId:").append("\"").append(subjSupervise.getIndustryId()).append("\",");
        sb.append("industryName:").append("\"").append(subjSupervise.getIndustryName()).append("\",");
        sb.append("svPostcode:").append("\"").append(subjSupervise.getSvPostcode()).append("\"}}");
        StringBuilder content = new StringBuilder();
        if(!subjSupervise.getSvName().equals(subjSvChange.getSvName())){
            content.append("机构名称变更为：").append(subjSvChange.getSvName()).append("。");
        }
        if(!subjSupervise.getSvCode().equals(subjSvChange.getSvCode())){
            content.append("机构代码变更为：").append(subjSvChange.getSvCode()).append("。");
        }
        if(!subjSupervise.getSvType().equals(subjSvChange.getSvType())){
            content.append("机构类型变更为：").append(subjSvChange.getSvType()).append("。");
        }
        if(!subjSupervise.getSvLevel().equals(subjSvChange.getSvLevel())){
            content.append("机构等级变更为：").append(subjSvChange.getSvLevel()).append("。");
        }
        if(this.equalsStrNoEqual(subjSupervise.getSvArea(),subjSvChange.getSvArea())){
            content.append("所属区域变更为：").append(subjSvChange.getSvArea()).append("。");
        }
        if(!subjSupervise.getSvAddress().equals(subjSvChange.getSvAddress())){
            content.append("通讯地址变更为：").append(subjSvChange.getSvAddress()).append("。");
        }
        if(!subjSupervise.getSvLeader().equals(subjSvChange.getSvLeader())){
            content.append("负责人变更为：").append(subjSvChange.getSvLeader()).append("。");
        }
        if(this.equalsStrNoEqual(subjSupervise.getSvLeaderPhone(),subjSvChange.getSvLeaderPhone())){
            content.append("负责人电话变更为：").append(subjSvChange.getSvLeaderPhone()).append("。");
        }
        if(!subjSupervise.getSvContact().equals(subjSvChange.getSvContact())){
            content.append("联系人变更为：").append(subjSvChange.getSvContact()).append("。");
        }
        if(!subjSupervise.getSvContactPhone().equals(subjSvChange.getSvContactPhone())){
            content.append("联系人电话变更为：").append(subjSvChange.getSvContactPhone()).append("。");
        }
        if(this.equalsStrNoEqual(subjSupervise.getSvContactQQ(),subjSvChange.getSvContactQQ())){
            content.append("联系人QQ变更为：").append(subjSvChange.getSvContactQQ()).append("。");
        }
        if(this.equalsStrNoEqual(subjSupervise.getSvContactEmail(),subjSvChange.getSvContactEmail())){
            content.append("联系人电子邮箱变更为：").append(subjSvChange.getSvContactEmail()).append("。");
        }
        if(this.equalsStrNoEqual(subjSupervise.getIndustryName(),subjSvChange.getIndustryName())){
            content.append("所属行业变更为：").append(subjSvChange.getIndustryName()).append("。");
        }
        if(this.equalsStrNoEqual(subjSupervise.getSvPostcode(),subjSvChange.getSvPostcode())){
            content.append("邮编变更为：").append(subjSvChange.getSvPostcode()).append("。");
        }
        subjSvChange.setId(StringUtils.getUUIDString());
        subjSvChange.setChangeBeforeField(sb.toString().replace("null",""));
        subjSvChange.setChangeContent(content.toString());
        subjSvChange.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjSvChange.setApplyTime(new Date());
        subjSvChange.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjSvChange.setDelFlag("N");//N-未删除，Y-逻辑删除
//        return provider.addSubjSvChange(subjSvChange);
        //发起监管机构信息变更申请
        //登录bps客户端
        int resultState = -1;
        //创建流程
        long  id = -1;
        try {
            IBPSServiceClient client = BpsConnectorUtil.getIBPSServiceClient();
            IWFWorkItemManager workItemManager = client.getWorkItemManager();
            IWFRelativeDataManager relativeDataManager = client.getRelativeDataManager();
            id = client.getProcessInstManager().createAndStartProcessInstance("SubjSvChange",
                    "监管信息变更","监管信息变更");
            List<WFWorkItem> workItemList =workItemManager.queryNextWorkItemsByProcessInstID(id, false);
            WFWorkItem item = workItemList.get(0);
            Map<String,Object> relativeData = new Hashtable<String,Object>();
            long workItemId = item.getWorkItemID();
            subjSvChange.setWorkItemId(String.valueOf(workItemId));
            relativeData.put("subjSvChange",subjSvChange);
            //完成变更申请流程
            workItemManager.finishWorkItemWithRelativeData(item.getWorkItemID(), relativeData, false);
            //得到处理结果
            resultState = (int)relativeDataManager.getRelativeData(id,"resultState");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(resultState==-1){
            provider.addSubjSvChange(subjSvChange);
        }
        return resultState;
    }

    public boolean equalsStrNoEqual(String before,String after){
        if((StringUtils.isBlank(before)&&StringUtils.isNotBlank(after))||(StringUtils.isNotBlank(before)&&!before.equals(after))){
            return true;
        }else{
            return false;
        }
    }

    public int addSubjSvCancel(String token,AsmsSubjSvCancel subjSvCancel){
        subjSvCancel.setId(StringUtils.getUUIDString());
        subjSvCancel.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjSvCancel.setSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjSvCancel.setApplyTime(new Date());
        subjSvCancel.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjSvCancel.setDelFlag("N");//N-未删除，Y-逻辑删除
        sysUserProvider.cancelSysOranization(sysUserProvider.findSysUserOrganization(token).getOrgId(),token,3);//注销申请中
        return provider.addSubjSvCancel(subjSvCancel);
    }

    public int addSubjSvCancelForApp(String token,String applyReason,String attachment,String attachmentName){
        AsmsSubjSvCancel subjSvCancel = new AsmsSubjSvCancel();
        subjSvCancel.setId(StringUtils.getUUIDString());
        subjSvCancel.setApplyUserId(WebUtil.getCurrentUser(token).getId());
        subjSvCancel.setSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjSvCancel.setApplyTime(new Date());
        subjSvCancel.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjSvCancel.setDelFlag("N");//N-未删除，Y-逻辑删除
        subjSvCancel.setApplyReason(applyReason);
        subjSvCancel.setAttachment(attachment);
        subjSvCancel.setAttachmentName(attachmentName);
        sysUserProvider.cancelSysOranization(sysUserProvider.findSysUserOrganization(token).getOrgId(),token,3);//注销申请中
        return provider.addSubjSvCancel(subjSvCancel);
    }

    public List<String> importSubjSupervise(MultipartFile file, HttpServletRequest request) throws Exception{
        //机构类型和机构级别要跟数据字典关联
        List<SysDictData> orgTypeList = sysDictProvider.getDictByType(DictType.ORGTYPE);//机构类型
        List<SysDictData> orgLevelList = sysDictProvider.getDictByType(DictType.ORGLEVEL);//机构级别
        List<SysDictData> orgIndustryList=sysDictProvider.getDictByType(DictType.INDUSTRYTYPE);//所属行业
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet;//工作簿
        int rowNum;//每个工作簿的行数
        Row row;//工作簿里的一行
        Cell cell;//每个单元格
        boolean errorFlag = false;
        List<String> msgList = new ArrayList<>();//用来存放错误信息
        List<AsmsSubjSupervise> subjSvList = new ArrayList<>();//用来存放机构主体对象
        int startRowIndex = 2;//导入的起始行数
        for(int sheetIndex = 0;sheetIndex<workbook.getNumberOfSheets();sheetIndex++){//循环Sheet
            sheet = workbook.getSheetAt(sheetIndex);
            rowNum = sheet.getLastRowNum()+1;//有多少行
            for(int rowIndex = startRowIndex;rowIndex<rowNum;rowIndex++){//循环Sheet里面的row
                row = sheet.getRow(rowIndex);
                int msgRowIndex = rowIndex+1;
                AsmsSubjSupervise subjectSupervise = new AsmsSubjSupervise();
                //机构名称
                //机构名称不能为空，长度不能超过50个汉字--简单判断
                cell = row.getCell(1);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构名称不能为空并且不能超过50个汉字！");
                }else {
                    subjectSupervise.setSvName(this.getCellValue(cell));
                }
                //机构代码
                //机构代码不能为空，长度不能超过50个汉字--简单判断
                cell = row.getCell(2);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构代码不能为空并且不能超过50个汉字！");
                }else {
                    subjectSupervise.setSvCode(this.getCellValue(cell));
                }
                //机构级别
                //机构级别不能为空--简单判断
                cell = row.getCell(3);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构级别不能为空！");
                }
                for(SysDictData sysDictData:orgLevelList){
                    if(cell!=null&&this.getCellValue(cell).equals(sysDictData.getDictName())){
                        subjectSupervise.setSvLevelId(sysDictData.getId());
                    }
                }
                if(cell!=null) {
                    switch (this.getCellValue(cell)) {
                        case "部级":
                            subjectSupervise.setSvLevel("0");
                            break;
                        case "省级":
                            subjectSupervise.setSvLevel("1");
                            break;
                        case "市级":
                            subjectSupervise.setSvLevel("2");
                            break;
                        case "县级":
                            subjectSupervise.setSvLevel("3");
                            break;
                        case "区县级":
                            subjectSupervise.setSvLevel("3");
                            break;
                        default:
                            errorFlag = true;
                            msgList.add("第" + (sheetIndex + 1) + "个工作簿第" + msgRowIndex + "行，机构级别只能为部级、省级、市级、县级中的一种！");
                    }
                }
                //所属行业
                //所属行业不能为空--简单判断
                cell = row.getCell(4);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属行业不能为空！");
                }else {
                    StringBuilder industryNameSb = new StringBuilder();
                    StringBuilder industryValueSb = new StringBuilder();
                    StringBuilder industryIdSb = new StringBuilder();
                    for (SysDictData industryData : orgIndustryList) {
                        if (cell != null && this.getCellValue(cell).contains(industryData.getDictName())) {
                            industryNameSb.append(industryData.getDictName()).append(",");
                            industryValueSb.append(industryData.getDictValue()).append(",");
                            industryIdSb.append(industryData.getId()).append(",");
                        }
                    }
                    if(industryNameSb.toString().trim().length()>0) {
                        subjectSupervise.setIndustryName(industryNameSb.toString().trim().substring(0,industryNameSb.toString().trim().length()-1));  //所属行业名称
                    }
                    if(industryValueSb.toString().trim().length()>0) {
                        subjectSupervise.setIndustryValue(industryValueSb.toString().trim().substring(0,industryValueSb.toString().trim().length()-1));//所属行业值
                    }
                    if(industryIdSb.toString().trim().length()>0) {
                        subjectSupervise.setIndustryId(industryIdSb.toString().trim().substring(0,industryIdSb.toString().trim().length()-1));//所属行业id
                    }
                }
                //所属区域id
                //区域编码不能为空--简单判断
                cell = row.getCell(5);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属区域行政编号不能为空！");
                }else {
                    subjectSupervise.setSvAreaId(this.getCellValue(cell));
                }
                //区域名称
                cell = row.getCell(6);
                StringBuilder areaSb=new StringBuilder();
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                cell = row.getCell(7);
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                cell = row.getCell(8);
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                subjectSupervise.setSvArea(areaSb.toString());
                //地址
                //地址不能为空--简单判断
                cell = row.getCell(9);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，地址不能为空并且不能超过50个汉字！");
                }else {
                    subjectSupervise.setSvAddress(this.getCellValue(cell));
                }
                //负责人
                //负责人不能为空--简单判断
                cell = row.getCell(10);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，负责人不能为空！");
                }else {
                    subjectSupervise.setSvLeader(this.getCellValue(cell));
                }
                //负责人电话
                cell = row.getCell(11);
                if(cell!=null) {
                    subjectSupervise.setSvLeaderPhone(this.getCellValue(cell));
                }
                //联系人
                //联系人不能为空--简单判断
                cell = row.getCell(12);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人不能为空！");
                }else {
                    subjectSupervise.setSvContact(this.getCellValue(cell));
                }
                //联系人电话
                //联系人电话不能为空--简单判断
                cell = row.getCell(13);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人电话不能为空！");
                }else {
                    subjectSupervise.setSvContactPhone(this.getCellValue(cell));
                }
                //联系人qq
                cell = row.getCell(14);
                if(cell!=null){
                    subjectSupervise.setSvContactQQ(this.getCellValue(cell));
                }
                //电子邮箱
                cell = row.getCell(15);
                if(cell!=null){
                    subjectSupervise.setSvContactEmail(this.getCellValue(cell));
                }
                //邮编
                cell = row.getCell(16);
                if(cell!=null){
                    if(!"".equals(this.getCellValue(cell))&&this.getCellValue(cell).length()!=6) {
                        errorFlag = true;
                        msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，邮编长度只能为6位！");
                    }else {
                        subjectSupervise.setSvPostcode(this.getCellValue(cell));//邮编
                    }
                }
                for(SysDictData dictData:orgTypeList){
                    if("监管机构".equals(dictData.getDictName())){
                        subjectSupervise.setSvTypeId(dictData.getId());
                    }
                }
                subjectSupervise.setSvType("监管机构");
                subjSvList.add(subjectSupervise);
            }
        }
        if(errorFlag){
            return msgList;
        }else{
            for (int i=0;i<subjSvList.size();i++){
                AsmsSubjSupervise subj=subjSvList.get(i);
                if(this.addSubjSupervise(request.getHeader("token"), subj)==2){//添加进数据库并判断是否有规则下的重复数据
                    //有规则下的重复数据
                    msgList.add("第"+(i+1)+"行数据添加失败!");
                }
            }
            //提示消息
            if(msgList.size()!=0 && null!=msgList){
                msgList.add("以上"+msgList.size()+"条数据与已有监管机构数据重复。规则如下：机构名称、机构代码、所属区域+机构级别+所属行业三者其中的一个不能相同。");
                return msgList;
            }
        }
        return new ArrayList<>();
    }

    public AsmsSubjSupervise findSubjSuperviseById(String id){
        return provider.findSubjSuperviseById(id);
    }

    //通过token获取组织机构
    public Organization findSysOrganizationByToken(String token){
        return sysUserProvider.findSysUserOrganization(token);
    }

    public Set getUserIndustryRoleByToken(String userId){
        List<SysRole> list = sysRoleProvider.getSysRolesByUserId(userId);
        Set set = new HashSet();
        for(SysRole sysRole:list){
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("01-JG-INDUSTRY")){
                set.add("01");
            }
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("02-JG-INDUSTRY")){
                set.add("02");
            }
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("03-JG-INDUSTRY")){
                set.add("03");
            }
        }
        return set;
    }

    public String getUserIndustryByToken(String userId){
        List<SysRole> list = sysRoleProvider.getSysRolesByUserId(userId);
        Set<String> set = new HashSet();
        for(SysRole sysRole:list){
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("01-JG-INDUSTRY")){
                set.add("01");
            }
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("02-JG-INDUSTRY")){
                set.add("02");
            }
            if(StringUtils.isNotBlank(sysRole.getRoleCode())&&sysRole.getRoleCode().startsWith("03-JG-INDUSTRY")){
                set.add("03");
            }
        }

        String inndustry = "";
        List<SysDictData> sysDictDataList = sysDictProvider.getDictByType(DictType.INDUSTRYTYPE);
        for (String str : set) {
            for (SysDictData sysDictData:sysDictDataList){
                if(sysDictData!=null&&sysDictData.getDictValue()!=null&&sysDictData.getDictValue().equals(str)){
                    inndustry += sysDictData.getId()+",";
                }
            }
        }
        if(inndustry.endsWith(",")){
            inndustry = inndustry.substring(0,inndustry.length()-1);
        }
        return inndustry;
    }



    /**
     * 通过ID获取单个监管机构主体变更申请
     * @param id
     * @return
     */
    public AsmsSubjSvChange findSubjSvChangeById(String id){
        return provider.findSubjSvChangeById(id);
    }

    /**
     * 通过ID获取单个监管机构主体注销申请
     * @param id
     * @return
     */
    public AsmsSubjSvCancel findSubjSvCancelById(String id){
        return provider.findSubjSvCancelById(id);
    }

    /**
     * 通过ID获取单个监管机构主体撤销申请
     * @param id
     * @return
     */
    public AsmsSubjSvRevoke findSubjSvRevokeById(String id){
        return provider.findSubjSvRevokeById(id);
    }

    /**
     * 审核监管机构主体变更
     * @param subjSvChange
     */
    public void auditSubjSvChange(String token,AsmsSubjSvChange subjSvChange){
//        subjSvChange.setAuditTime(new Date());
//        subjSvChange.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
//        subjSvChange.setAuditUserId(WebUtil.getCurrentUserId(token));
//        AsmsSubjSupervise subjSupervise = this.findSubjSuperviseById(subjSvChange.getApplySvId());
//        //审核通过,同步组织机构信息
//        if("1".equals(subjSvChange.getAuditState())){
//            SysOrganization sysOrganization = sysOrganizationProvider.findSysOrganizationByOrgId(subjSupervise.getId());
//            if(StringUtils.isNotBlank(subjSvChange.getSvName())){
//                sysOrganization.setOrgName(subjSvChange.getSvName());
//            }
//            if(StringUtils.isNotBlank(subjSvChange.getSvArea())){
//                sysOrganization.setRegionName(subjSvChange.getSvArea());
//            }
//            if(StringUtils.isNotBlank(subjSvChange.getSvAreaId())){
//                sysOrganization.setRegionId(subjSvChange.getSvAreaId());
//            }
//            sysOrganizationProvider.updateSysOrganization(sysOrganization);
//            //查询条件
//            Map<String,Object> map = new HashMap<>();
//            map.put("userType","SYSADMIN");
//            map.put("organizationId",sysUserProvider.findSysUserOrganization(token).getId());
//            List<SysUser> sysUsers = sysUserProvider.queryByParam(map);
//            if(sysUsers.size()==1){
//                SysUser sysUser = sysUsers.get(0);
//                sysUser.setUserName(subjSvChange.getSvName()+"_admin");
//                sysUserProvider.update(sysUser);
//            }
//        }
//        provider.auditSubjSvChange(subjSupervise, subjSvChange);
        subjSvChange.setAuditTime(new Date());
        subjSvChange.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjSvChange.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjSupervise subjSupervise = this.findSubjSuperviseById(subjSvChange.getApplySvId());
        Map<String,Object> relativeData = new Hashtable<String,Object>();
        subjSupervise.setStatus("0");
        relativeData.put("subjSvChange",subjSvChange);
        subjSupervise.setSvName(subjSvChange.getSvName());
        subjSupervise.setSvCode(subjSvChange.getSvCode());
        subjSupervise.setSvType(subjSvChange.getSvType());
        subjSupervise.setSvTypeId(subjSvChange.getSvTypeId());
        subjSupervise.setSvLevel(subjSvChange.getSvLevel());
        subjSupervise.setSvLevelId(subjSvChange.getSvLevelId());
        subjSupervise.setSvArea(subjSvChange.getSvArea());
        subjSupervise.setSvAreaId(subjSvChange.getSvAreaId());
        subjSupervise.setSvAddress(subjSvChange.getSvAddress());
        subjSupervise.setSvLeader(subjSvChange.getSvLeader());
        subjSupervise.setSvLeaderPhone(subjSvChange.getSvLeaderPhone());
        subjSupervise.setSvContact(subjSvChange.getSvContact());
        subjSupervise.setSvContactPhone(subjSvChange.getSvContactPhone());
        subjSupervise.setSvContactQQ(subjSvChange.getSvContactQQ());
        subjSupervise.setSvContactEmail(subjSvChange.getSvContactEmail());
        subjSupervise.setSvPostcode(subjSvChange.getSvPostcode());
        //行业id
        subjSupervise.setIndustryId(subjSvChange.getIndustryId());
        //行业名称
        subjSupervise.setIndustryName(subjSvChange.getIndustryName());
        int resultState = -1;
        try {
            //登录bps客户端
            IBPSServiceClient client = BpsConnectorUtil.getIBPSServiceClient();
            IWFWorkItemManager workItemManager = client.getWorkItemManager();
//            BPSServiceClientFactory.getLoginManager().setCurrentUser("fish", "fish");
            String workItemIdString = provider.findSubjSvChangeById(subjSvChange.getId()).getWorkItemId();
            Long workItemId = Long.parseLong(workItemIdString);
            List<WFWorkItem> workItemList =workItemManager.queryNextWorkItemsByWorkItemID(workItemId, false);
            WFWorkItem item = workItemList.get(0);
            relativeData.put("subjSupervise",subjSupervise);
            //完成审批工作项
            workItemManager.finishWorkItemWithRelativeData(item.getWorkItemID(), relativeData, false);
            resultState = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if("1".equals(subjSvChange.getAuditState())){
                SysOrganization sysOrganization = sysOrganizationProvider.findSysOrganizationByOrgId(subjSupervise.getId());
                if(StringUtils.isNotBlank(subjSvChange.getSvName())){
                    sysOrganization.setOrgName(subjSvChange.getSvName());
                }
                if(StringUtils.isNotBlank(subjSvChange.getSvArea())){
                    sysOrganization.setRegionName(subjSvChange.getSvArea());
                }
                if(StringUtils.isNotBlank(subjSvChange.getSvAreaId())){
                    sysOrganization.setRegionId(subjSvChange.getSvAreaId());
                }
                sysOrganizationProvider.updateSysOrganization(sysOrganization);
                //查询条件
                Map<String,Object> map = new HashMap<>();
                map.put("userType","SYSADMIN");
                map.put("organizationId",sysUserProvider.findSysUserOrganization(token).getId());
                List<SysUser> sysUsers = sysUserProvider.queryByParam(map);
                if(sysUsers.size()==1){
                    SysUser sysUser = sysUsers.get(0);
                    sysUser.setUserName(subjSvChange.getSvName()+"_admin");
                    sysUserProvider.update(sysUser);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(resultState == -1){
            provider.auditSubjSvChange(subjSupervise, subjSvChange);
        }
    }

    /**
     * 审核监管机构主体注销申请
     * @param subjSvCancel
     */
    public void auditSubjSvCancel(String token,AsmsSubjSvCancel subjSvCancel){
        subjSvCancel.setAuditTime(new Date());
        subjSvCancel.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjSvCancel.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjSupervise subjSupervise = this.findSubjSuperviseById(subjSvCancel.getSvId());
        provider.auditSubjSvCancel(subjSupervise, subjSvCancel);
        //注销机构之后再注销相关账号
        if("1".equals(subjSvCancel.getAuditState())){
            sysUserProvider.cancelSysOranization(subjSupervise.getId(),token,2);//已注销
        }
        if("2".equals(subjSvCancel.getAuditState())){
            sysUserProvider.cancelSysOranization(subjSupervise.getId(),token,1);//恢复正常
        }
    }

    /**
     * 审核监管机构主体撤销申请
     * @param subjSvRevoke
     */
    public void auditSubjSvRevoke(String token,AsmsSubjSvRevoke subjSvRevoke) {
        subjSvRevoke.setAuditTime(new Date());
        subjSvRevoke.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjSvRevoke.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjSupervise subjSupervise = this.findSubjSuperviseById(subjSvRevoke.getSvId());
        provider.auditSubjSvRevoke(subjSupervise,subjSvRevoke);
        //撤销机构之后再注销相关账号
        if("1".equals(subjSvRevoke.getAuditState())){
            sysUserProvider.cancelSysOranization(subjSupervise.getId(),token,5);//已撤销
        }
        if("2".equals(subjSvRevoke.getAuditState())){
            sysUserProvider.cancelSysOranization(subjSupervise.getId(),token,1);//恢复正常
        }
    }

    /**
     * 修改密码
     * @param account
     * @param oldPwd
     * @param newPwd
     * @param token
     * @return
     */
    public boolean changePwd(String account, String oldPwd, String newPwd,String token){
        return sysUserProvider.changePwd(account,oldPwd,newPwd,token);
    }

    /** 根据条件获取用户信息 **/
    public List<Map<String,Object>> getUserByCondition(UserAndSubj userAndSubj){
        Map<String,Object> map = new HashMap();
        map.put("account", userAndSubj.getAccount());
        map.put("name", userAndSubj.getName());
        map.put("code", userAndSubj.getCode());
        map.put("level", userAndSubj.getLevel());
        map.put("areaId", userAndSubj.getAreaId());
        map.put("leader", userAndSubj.getLeader());
        List list = null;
        if(userAndSubj.getType().equals("0")) {
            list = provider.getSvUserList(map);
        }else if(userAndSubj.getType().equals("1")) {
            list = provider.getElUserList(map);
        }else if(userAndSubj.getType().equals("2")) {
            list = provider.getDtUserList(map);
        }
        return list;
    }

    /** 根据条件获取上级机构联系方式 **/
    public List<Map<String,Object>> getSuperiorList(UserAndSubj userAndSubj){
        Map<String,Object> map = new HashMap();
        map.put("name", userAndSubj.getName());
        map.put("code", userAndSubj.getCode());
        map.put("level", userAndSubj.getLevel());
        map.put("areaId", userAndSubj.getAreaId());
        map.put("superiorLevel", userAndSubj.getSuperiorLevel());
        map.put("superiorAreaId", userAndSubj.getSuperiorAreaId());
        List list = null;
        if(userAndSubj.getType().equals("0")) {
            list = provider.getSuperiorSvList(map);
        }else if(userAndSubj.getType().equals("1")) {
            list = provider.getSuperiorElList(map);
        }else if(userAndSubj.getType().equals("2")) {
            list = provider.getSuperiorDtList(map);
        }
        return list;
    }

    /** 根据条件重置密码 **/
    public boolean resetPwd(String account, String newPwd){
        return sysUserProvider.changePwdByAccount(account,newPwd);
    }

    /**
     * 单纯上传文件--暂加
     * @param request
     * @throws Exception
     */
    public Map<String,Object> upload(HttpServletRequest request) throws Exception{
        //创建一个基于磁盘的文件项的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置工厂约束
        factory.setSizeThreshold(4096);//设置缓冲区大小，这里是4kb
        //创建一个新的文件上传机制
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置整体请求大小约束
        upload.setFileSizeMax(4194304);// 设置最大文件尺寸，这里是4MB
        //可能有多个文件
        List<FileItem> items;
        items = upload.parseRequest(request);
        Iterator<FileItem> iterator = items.iterator();
        String tomcatPath = request.getSession().getServletContext().getRealPath("")+"\\uploadFile\\baseInspection\\"
                +com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd");
        File fileD = new File(tomcatPath);
        if(!fileD.exists()){
            fileD.mkdirs();
        }
        //文件路径
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            FileItem fileItem = iterator.next();
            if(fileItem.getName()!=null) {
                String fileName = UUID.randomUUID().toString().replace("-", "") + fileItem.getName().substring(fileItem.getName().lastIndexOf("."),fileItem.getName().length());
                File file = new File(tomcatPath +"\\"+ fileName);
                sb.append("uploadFile/baseInspection/"+com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd") +"/" + fileName);
                fileItem.write(file);
            }

        }
        Map<String,Object> map = new HashMap<>();
        map.put("path",sb.toString());
        return map;
    }

    public String getCellValue(Cell cell){
        String value = "";
        cell.setCellType(1);
        switch(cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue()+"";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
        }
        return value;
    }
}
