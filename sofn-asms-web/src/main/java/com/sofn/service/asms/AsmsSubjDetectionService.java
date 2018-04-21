package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.DictType;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysOrganizationProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.util.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:29
 */
@Service
public class AsmsSubjDetectionService extends BaseService<AsmsSubjDetectionProvider,AsmsSubjDetection>{

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider provider){
        this.provider = provider;
    }

    private SysUserProvider sysUserProvider;
    @DubboReference
    public void setSysUserProvider(SysUserProvider sysUserProvider){
        this.sysUserProvider = sysUserProvider;
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

    public PageInfo getSubjDetectionList(String token,AsmsSubjDetection subjDetection,Page page,String dateBegin,String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("dtName",subjDetection.getDtName());
        queryMap.put("dtLevel",Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);
        queryMap.put("areaId",subjDetection.getDtAreaId());
        queryMap.put("dateBegin",dateBegin);
        queryMap.put("dateEnd",dateEnd);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        return provider.getSubjDetectionList(queryMap);
    }

    public PageInfo getSubjDetectionListForSys(String token,AsmsSubjDetection subjDetection,Page page,String dateBegin,String dateEnd){
        CurrentUser currentUser = WebUtil.getCurrentUser(token) ;
        String cUserRegionId = currentUser.getRegionId();
        if(StringUtils.isEmpty(currentUser.getOrgLevel())){
            return null ;
        }
        int cUsersvLevel = Integer.parseInt(currentUser.getOrgLevel()) -1 ;
        if(cUsersvLevel == 0){
            cUserRegionId = "";
        }else if(cUsersvLevel == 1){
            cUserRegionId = cUserRegionId.substring(0,2);
        }else if(cUsersvLevel == 2){
            cUserRegionId = cUserRegionId.substring(0,4);
        }else{
            cUserRegionId = cUserRegionId.substring(0,6);
        }

        Map<String,Object> queryMap = new HashMap<>();

        queryMap.put("cUserRegionId",cUserRegionId);
        queryMap.put("cUsersvLevel",cUsersvLevel+"");
        queryMap.put("dtName",subjDetection.getDtName());
        queryMap.put("areaId",subjDetection.getDtAreaId());
        queryMap.put("dateBegin",dateBegin);
        queryMap.put("dateEnd",dateEnd);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        return provider.getSubjDetectionListForSys(queryMap);
    }

    /**
     * 查询检测机构主体变更申请列表
     * @param subjDtChange
     * @param page
     * @param date
     * @return
     */
    public PageInfo getSubjDtChangeList(String token,AsmsSubjDtChange subjDtChange,Page page,String date){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("dtName", subjDtChange.getDtName());
        queryMap.put("areaId", subjDtChange.getDtAreaId());
        queryMap.put("dtLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("date", date);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("auditState",subjDtChange.getAuditState());
        return provider.getSubjDtChangeList(queryMap);
    }

    //统计变更待审核数量
    public long countChangeToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("auditState","0");
        queryMap.put("dtLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("areaId", areaId);
        return provider.countChangeToAudit(queryMap);
    }

    /**
     * 查询检测机构主体注销申请列表
     * @param areaId
     * @param page
     * @param dtName
     * @param date
     * @return
     */
    public PageInfo getSubjDtCancelList(String token,String areaId,Page page,String dtName,String date,String auditState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("dtName", dtName);
        queryMap.put("areaId", areaId);
        queryMap.put("dtLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("date", date);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("auditState",auditState);
        return provider.getSubjDtCancelList(queryMap);
    }

    //统计注销待审核数量
    public long countCancelToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("auditState","0");
        queryMap.put("dtLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("areaId", areaId);
        return provider.countCancelToAudit(queryMap);
    }

    /**
     * 查询检测机构主体撤销申请列表
     * @param areaId
     * @param page
     * @param dtName
     * @param date
     * @return
     */
    public PageInfo getSubjDtRevokeList(String token,String areaId,Page page,String dtName,String date,String auditState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("dtName", dtName);
        queryMap.put("areaId", areaId);
        queryMap.put("date", date);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("auditState",auditState);
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只能看本机构
        return provider.getSubjDtRevokeList(queryMap);
    }

    //统计撤销待审核数量
    public long countRevokeToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("auditState","0");
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只能看本机构
        queryMap.put("areaId", areaId);
        return provider.countRevokeToAudit(queryMap);
    }

    @Transactional
    public int addSubjDetection(String token,AsmsSubjDetection subjDetection){
        //查询是否有检测机构（机构名称、机构代码、机构区域和机构级别其中的一个相同）--有的话返回一个标识
        Map<String,Object> map = new HashMap();
        map.put("name",subjDetection.getDtName());
        map.put("code",subjDetection.getDtCode());
        //去除区域和机构级别唯一性限制
        //map.put("dtAreaId",subjDetection.getDtAreaId());
        //map.put("dtLevelId",subjDetection.getDtLevelId());
        List<AsmsSubjDetection> list = provider.getSubjDetectionByCondition(map);
        if(list!=null&&!list.isEmpty()){
            /*if(subjDetection.getDtAreaId().equals("110000")) {//部级也是北京市
                for (AsmsSubjDetection queryResult : list) {
                    if (queryResult.getDtLevel().equals(subjDetection.getDtLevel())) {
                        return 2;
                    }
                }
            }else {*/
                return 2;
           // }
        }
        //设置检测机构一些字段信息
        subjDetection.setId(StringUtils.getUUIDString());
        subjDetection.setCreateBy(WebUtil.getCurrentUserId());
        subjDetection.setCreateTime(new Date());
        subjDetection.setStatus("0");
        subjDetection.setDelFlag("N");
        //同步组织机构
        SysOrganization sysOrganization = new SysOrganization();
        sysOrganization.setOrgName(subjDetection.getDtName());
        sysOrganization.setOrgType(RSAUtils.Module.ADS.name());
        sysOrganization.setRegionName(subjDetection.getDtArea());
        sysOrganization.setTypeId(subjDetection.getDtTypeId());
        sysOrganization.setLevelId(subjDetection.getDtLevelId());
        sysOrganization.setOrgLevel(String.valueOf(Integer.valueOf(subjDetection.getDtLevel())+1));
        if("1".equals(subjDetection.getDtLevel())){
            sysOrganization.setRegionId(subjDetection.getDtAreaId().substring(0,2)+"0000");
        }else if("2".equals(subjDetection.getDtLevel())){
            sysOrganization.setRegionId(subjDetection.getDtAreaId().substring(0,4)+"00");
        }else{
            sysOrganization.setRegionId(subjDetection.getDtAreaId());
        }
//        sysOrganization.setRegionId(subjDetection.getDtAreaId());
        sysOrganization.setOrgId(subjDetection.getId());
        sysOrganization.setDelFlag("N");
        SysUser sysUser = sysUserProvider.saveSysOranizationUser(sysOrganization, token,null);//返回机构管理员
        if(sysUser==null||sysUser.getId().isEmpty()){
            return 3;
        }
        return provider.addSubjDetection(subjDetection);
    }

    public int addSubjDtRevoke(String token,AsmsSubjDtRevoke subjDtRevoke){
        subjDtRevoke.setId(StringUtils.getUUIDString());
        subjDtRevoke.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjDtRevoke.setApplySvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjDtRevoke.setApplyTime(new Date());
        subjDtRevoke.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjDtRevoke.setDelFlag("N");
        sysUserProvider.cancelSysOranization(subjDtRevoke.getDtId(),token,4);//撤销申请中
        return provider.addSubjDtRevoke(subjDtRevoke);
    }

    public List<String> importSubjDetection(MultipartFile file, HttpServletRequest request) throws Exception{
        //机构类型和机构级别要跟数据字典关联
        List<SysDictData> orgTypeList = sysDictProvider.getDictByType(DictType.ORGTYPE);//机构类型
        List<SysDictData> orgLevelList = sysDictProvider.getDictByType(DictType.ORGLEVEL);//机构级别
        List<SysDictData> orgNatureList = sysDictProvider.getDictByType(DictType.ORGNATURE);//机构性质
        List<SysDictData> qualificationList = sysDictProvider.getDictByType(DictType.QUALIFICATIONCERTIFICATE);//资质
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet;//工作簿
        int rowNum;//每个工作簿的行数
        Row row;//工作簿里的一行
        Cell cell;//每个单元格
        boolean errorFlag = false;
        List<String> msgList = new ArrayList<>();//用来存放错误信息
        List<AsmsSubjDetection> subjDtList = new ArrayList<>();//用来存放机构主体对象
        int startRowIndex = 2;//导入的起始行数
        for(int sheetIndex = 0;sheetIndex<workbook.getNumberOfSheets();sheetIndex++){//循环Sheet
            sheet = workbook.getSheetAt(sheetIndex);
            rowNum = sheet.getLastRowNum()+1;//有多少行
            for(int rowIndex = startRowIndex;rowIndex<rowNum;rowIndex++){//循环Sheet里面的row
                row = sheet.getRow(rowIndex);
                int msgRowIndex = rowIndex+1;
                AsmsSubjDetection subjDetection = new AsmsSubjDetection();
                //机构名称
                //机构名称不能为空，长度不能超过50个汉字--简单判断
                cell = row.getCell(1);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构名称不能为空并且不能超过50个汉字！");
                }else {
                    subjDetection.setDtName(this.getCellValue(cell)); //机构名称
                }
                //机构代码
                //机构代码不能为空，长度不能超过50个汉字--简单判断
                cell = row.getCell(2);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构代码不能为空并且不能超过50个汉字！");
                }else {
                    subjDetection.setDtCode(this.getCellValue(cell));//机构代码
                }
                for(SysDictData dictData:orgTypeList){ //机构类型
                    if("检测机构".equals(dictData.getDictName())){
                        subjDetection.setDtTypeId(dictData.getId());
                    }
                }
                subjDetection.setDtType("检测机构");
                //机构性质
                //机构性质不能为空--简单判断
                cell = row.getCell(3);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构性质不能为空！");
                }
                for(SysDictData dictData:orgNatureList){//机构性质
                    if(this.getCellValue(cell)!=null&&this.getCellValue(cell).equals(dictData.getDictName())){
                        subjDetection.setDtNatureId(dictData.getId());
                    }
                }
                if (cell!=null){
                    subjDetection.setDtNature(this.getCellValue(cell));
                }
                //机构资质
                //机构资质不能为空--简单判断
                cell = row.getCell(4);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构资质不能为空！");
                }
                for(SysDictData dictData:qualificationList){//资质
                    if(this.getCellValue(cell)!=null&&this.getCellValue(cell).equals(dictData.getDictName())){
                        subjDetection.setDtQualificationsId(dictData.getId());
                    }
                }
                if(cell!=null){
                    subjDetection.setDtQualifications(this.getCellValue(cell));
                }

                //机构代码
                //机构代码不能为空，长度不能超过50个汉字--简单判断
                cell = row.getCell(5);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，依托单位不能为空并且不能超过50个汉字！");
                }else {
                    subjDetection.setDtRelyOnUnit(this.getCellValue(cell));//依托单位
                }
                //机构级别
                //机构级别不能为空--简单判断
                cell = row.getCell(6);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构级别不能为空！");
                }
                for(SysDictData sysDictData:orgLevelList){//机构级别
                    if(this.getCellValue(cell)!=null&&this.getCellValue(cell).equals(sysDictData.getDictName())){
                        subjDetection.setDtLevelId(sysDictData.getId());
                    }
                }
                if (cell!=null) {
                    switch (this.getCellValue(cell)) {
                        case "部级":
                            subjDetection.setDtLevel("0");
                            break;
                        case "省级":
                            subjDetection.setDtLevel("1");
                            break;
                        case "市级":
                            subjDetection.setDtLevel("2");
                            break;
                        case "县级":
                            subjDetection.setDtLevel("3");
                            break;
                        default:
                            errorFlag = true;
                            msgList.add("第" + (sheetIndex + 1) + "个工作簿第" + msgRowIndex + "行，机构级别只能为部级、省级、市级、县级中的一种！");
                    }
                }
                //所属区域id
                //区域编码不能为空--简单判断
                cell = row.getCell(7);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属区域行政编号不能为空！");
                }else {
                    subjDetection.setDtAreaId(this.getCellValue(cell).trim());//所属区域id
                }
                //区域名称
                cell = row.getCell(8);
                StringBuilder areaSb=new StringBuilder();
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                cell = row.getCell(9);
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                cell = row.getCell(10);
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                subjDetection.setDtArea(areaSb.toString());//所属区域
                //地址
                //地址不能为空--简单判断
                cell = row.getCell(11);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，地址不能为空并且不能超过50个汉字！");
                }else {
                    subjDetection.setDtAddress(this.getCellValue(cell));//地址
                }
                //法人
                //法人不能为空--简单判断
                cell = row.getCell(12);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，法人不能为空！");
                }else {
                    subjDetection.setDtLegalMan(this.getCellValue(cell));//法人
                }
                //负责人
                //负责人不能为空--简单判断
                cell = row.getCell(13);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，负责人不能为空！");
                }else {
                    subjDetection.setDtLeader(this.getCellValue(cell));//负责人
                }
                //负责人电话
                cell = row.getCell(14);
                if(cell!=null) {
                    subjDetection.setDtLeaderPhone(this.getCellValue(cell));//负责人电话
                }
                //联系人
                //联系人不能为空--简单判断
                cell = row.getCell(15);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人不能为空！");
                }else {
                    subjDetection.setDtContact(this.getCellValue(cell));//联系人
                }
                //联系人电话
                //联系人电话不能为空--简单判断
                cell = row.getCell(16);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人电话不能为空！");
                }else {
                    subjDetection.setDtContactPhone(this.getCellValue(cell));//联系人电话
                }
                //联系人qq
                cell = row.getCell(17);
                if(cell!=null){
                    subjDetection.setDtContactQQ(this.getCellValue(cell));//联系人qq
                }
                //电子邮箱
                cell = row.getCell(18);
                if(cell!=null){
                    subjDetection.setDtContactEmail(this.getCellValue(cell));//联系邮箱
                }
                //邮编
                cell = row.getCell(19);
                if(cell!=null){
                    if(!"".equals(this.getCellValue(cell))&&this.getCellValue(cell).length()!=6) {
                        errorFlag = true;
                        msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，邮编长度只能为6位！");
                    }else {
                        subjDetection.setDtPostcode(this.getCellValue(cell));//邮编
                    }
                }
                //技术负责人
                //技术负责人不能为空--简单判断
                cell = row.getCell(20);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，技术负责人不能为空！");
                }else {
                    subjDetection.setDtTechnicalDirector(this.getCellValue(cell));//技术负责人
                }
                //质量负责人
                //质量负责人不能为空--简单判断
                cell = row.getCell(21);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，质量负责人不能为空！");
                }else {
                    subjDetection.setDtQualityDirector(this.getCellValue(row.getCell(21)));//质量负责人
                }
                //参数
                //参数不能为空--简单判断
                cell = row.getCell(22);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，参数不能为空并且不能超过50个字！");
                }else {
                    subjDetection.setDtParameter(this.getCellValue(cell));//参数
                }
                //产品范围
                //产品范围不能为空--简单判断
                cell = row.getCell(23);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，产品范围不能为空并且不能超过50个字！");
                }else {
                    subjDetection.setDtProductRange(this.getCellValue(cell));//产品范围
                }
                subjDtList.add(subjDetection);
            }
        }
        if(errorFlag){
            return msgList;
        }else{
            for (int i=0;i<subjDtList.size();i++){
                AsmsSubjDetection subjDt=subjDtList.get(i);
                if(this.addSubjDetection(request.getHeader("token"),subjDt)==2){//添加数据并判断有无规则下的重复数据
                    //有
                    msgList.add("第"+(i+1)+"行数据添加失败");
                }
            }
            if (msgList.size()!=0 && null!=msgList){
                msgList.add("以上"+msgList.size()+"条数据与已有监测机构数据重复。规则如下：机构名称、机构代码、所属区域和机构级别其中的一个不能相同");
                return msgList;
            }
        }
        return new ArrayList<>();
    }

    public AsmsSubjDetection findSubjDetectionById(AsmsSubjDetection subjDetection){
        return provider.findSubjDetectionById(subjDetection.getId());
    }
    /**
     * 通过ID获取单个检测机构主体变更申请
     * @param subjDtChange
     * @return
     */
    public AsmsSubjDtChange findSubjDtChangeById(AsmsSubjDtChange subjDtChange){
        return provider.findSubjDtChangeById(subjDtChange.getId());
    }

    /**
     * 通过ID获取单个检测机构主体注销申请
     * @param subjDtCancel
     * @return
     */
    public AsmsSubjDtCancel findSubjDtCancelById(AsmsSubjDtCancel subjDtCancel){
        return provider.findSubjDtCancelById(subjDtCancel.getId());
    }

    /**
     * 通过ID获取单个检测机构主体撤销申请
     * @param subjDtRevoke
     * @return
     */
    public AsmsSubjDtRevoke findSubjDtRevokeById(AsmsSubjDtRevoke subjDtRevoke){
        return provider.findSubjDtRevokeById(subjDtRevoke.getId());
    }

    /**
     * 审核检测机构主体变更
     * @param subjDtChange
     */
    public void auditSubjDtChange(String token,AsmsSubjDtChange subjDtChange){
        subjDtChange.setAuditTime(new Date());
        subjDtChange.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjDtChange.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjDetection subjDetection = new AsmsSubjDetection();
        subjDetection.setId(subjDtChange.getApplyDtId());
        subjDetection = this.findSubjDetectionById(subjDetection);
        //审核通过,同步组织机构信息
        if("1".equals(subjDtChange.getAuditState())){
            SysOrganization sysOrganization = sysOrganizationProvider.findSysOrganizationByOrgId(subjDetection.getId());
            if(StringUtils.isNotBlank(subjDtChange.getDtName())){
                sysOrganization.setOrgName(subjDtChange.getDtName());
            }
            if(StringUtils.isNotBlank(subjDtChange.getDtArea())){
                sysOrganization.setRegionName(subjDtChange.getDtArea());
            }
            if(StringUtils.isNotBlank(subjDtChange.getDtAreaId())){
                sysOrganization.setRegionId(subjDtChange.getDtAreaId());
            }
            sysOrganizationProvider.updateSysOrganization(sysOrganization);
        }
        provider.auditSubjDtChange(subjDetection, subjDtChange);
    }

    /**
     * 审核检测机构主体注销申请
     * @param subjDtCancel
     */
    public void auditSubjDtCancel(String token,AsmsSubjDtCancel subjDtCancel){
        subjDtCancel.setAuditTime(new Date());
        subjDtCancel.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjDtCancel.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjDetection subjDetection = new AsmsSubjDetection();
        subjDetection.setId(subjDtCancel.getDtId());
        subjDetection = this.findSubjDetectionById(subjDetection);
        provider.auditSubjDtCancel(subjDetection,subjDtCancel);
        //注销机构之后再注销相关账号
        if("1".equals(subjDtCancel.getAuditState())){
            sysUserProvider.cancelSysOranization(subjDetection.getId(),token,3);//已注销
        }
        if("2".equals(subjDtCancel.getAuditState())){
            sysUserProvider.cancelSysOranization(subjDetection.getId(),token,1);//恢复正常
        }
    }

    /**
     * 审核检测机构主体撤销申请
     * @param subjDtRevoke
     */
    public void auditSubjDtRevoke(String token,AsmsSubjDtRevoke subjDtRevoke){
        subjDtRevoke.setAuditTime(new Date());
        subjDtRevoke.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjDtRevoke.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjDetection subjDetection = new AsmsSubjDetection();
        subjDetection.setId(subjDtRevoke.getDtId());
        subjDetection = this.findSubjDetectionById(subjDetection);
        provider.auditSubjDtRevoke(subjDetection, subjDtRevoke);
        //撤销机构之后再注销相关账号
        if("1".equals(subjDtRevoke.getAuditState())){
            sysUserProvider.cancelSysOranization(subjDetection.getId(),token,5);//已撤销
        }
        if("2".equals(subjDtRevoke.getAuditState())){
            sysUserProvider.cancelSysOranization(subjDetection.getId(),token,1);//恢复正常
        }
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
            fileD.mkdir();
        }
        //文件路径
        StringBuilder sb = new StringBuilder();
        byte[] imgByte = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while(iterator.hasNext()){
            FileItem fileItem = iterator.next();
            InputStream inputStream = fileItem.getInputStream();
            if(fileItem.getName()!=null) {
                String fileName = UUID.randomUUID().toString().replace("-", "") + fileItem.getName().substring(fileItem.getName().lastIndexOf("."),fileItem.getName().length());
                File file = new File(tomcatPath +"\\"+ fileName);
                sb.append("uploadFile/baseInspection/"+com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd") +"/" + fileName);
                fileItem.write(file);

//            int ch;
//            while((ch=inputStream.read())!=-1){
//                byteArrayOutputStream.write(ch);
//            }
                imgByte = IOUtils.toByteArray(inputStream);
                for(int i=0;i<imgByte.length;i++){
                    System.out.print(imgByte[i]);
                }
            }

        }
        Map<String,Object> map = new HashMap<>();
        map.put("path",sb.toString());
        map.put("img",imgByte);
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
