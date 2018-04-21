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
import com.sofn.provider.asms.AsmsSubjEnforceLawProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysOrganizationProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.util.Page;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:29
 */
@Service
public class AsmsSubjEnforceLawService extends BaseService<AsmsSubjEnforceLawProvider,AsmsSubjEnforceLaw>{

    @DubboReference
    public void setProvider(AsmsSubjEnforceLawProvider provider){
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

    public PageInfo getSubjEnforceLawList(String token,AsmsSubjEnforceLaw subjEnforceLaw,Page page,String dateBegin,String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("elName",subjEnforceLaw.getElName());
        queryMap.put("elAreaId",subjEnforceLaw.getElAreaId());
        queryMap.put("elLevel",Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("dateBegin",dateBegin);
        queryMap.put("dateEnd",dateEnd);
        return provider.getSubjEnforceLawList(queryMap);
    }

    public PageInfo getSubjEnforceLawListForSys(String token,AsmsSubjEnforceLaw subjEnforceLaw,Page page,String dateBegin,String dateEnd){
        CurrentUser currentUser = WebUtil.getCurrentUser(token) ;
        String cUserRegionId = currentUser.getRegionId();
        if(StringUtils.isEmpty(currentUser.getOrgLevel())){
            return null ;
        }
        int cUserLevel = Integer.parseInt(currentUser.getOrgLevel()) -1 ;
        if(cUserLevel == 0){
            cUserRegionId = "";
        }else if(cUserLevel == 1){
            cUserRegionId = cUserRegionId.substring(0,2);
        }else if(cUserLevel == 2){
            cUserRegionId = cUserRegionId.substring(0,4);
        }else{
            cUserRegionId = cUserRegionId.substring(0,6);
        }
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("cUserRegionId",cUserRegionId);
        queryMap.put("cUserLevel",cUserLevel+"");
        queryMap.put("elName",subjEnforceLaw.getElName());
        queryMap.put("elAreaId",subjEnforceLaw.getElAreaId());
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("dateBegin",dateBegin);
        queryMap.put("dateEnd",dateEnd);
        return provider.getSubjEnforceLawListForSys(queryMap);
    }

    /**
     * 查询执法机构主体变更申请列表
     * @param subjElChange
     * @param page
     * @param date
     * @return
     */
    public PageInfo getSubjElChangeList(String token,AsmsSubjElChange subjElChange, Page page, String date){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("date",date);
        queryMap.put("elName",subjElChange.getElName());
        queryMap.put("elAreaId",subjElChange.getElAreaId());
        queryMap.put("elLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("auditState",subjElChange.getAuditState());
        return provider.getSubjElChangeList(queryMap);
    }

    //统计变更待审核数量
    public long countChangeToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("auditState","0");
        queryMap.put("elLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("elAreaId", areaId);
        return provider.countChangeToAudit(queryMap);
    }

    /**
     * 查询执法机构主体注销申请列表
     * @param areaId
     * @param page
     * @param elName
     * @param date
     * @return
     */
    public PageInfo getSubjElCancelList(String token,String areaId, Page page, String elName, String date,String auditState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("elName",elName);
        queryMap.put("areaId",areaId);
        queryMap.put("date",date);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("elLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("auditState",auditState);
        return provider.getSubjElCancelList(queryMap);
    }

    //统计注销待审核数量
    public long countCancelToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("elLevel", String.valueOf(Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1));//获取本地区同级机构,系统管理里多1
        queryMap.put("areaId", areaId);
        queryMap.put("auditState","0");
        return provider.countCancelToAudit(queryMap);
    }

    /**
     * 查询执法机构主体撤销申请列表
     * @param areaId
     * @param page
     * @param elName
     * @param date
     * @return
     */
    public PageInfo getSubjElRevokeList(String token,String areaId,Page page,String elName,String date,String auditState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("elName",elName);
        queryMap.put("areaId",areaId);
        queryMap.put("date",date);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只能看本机构
        queryMap.put("auditState",auditState);
        return provider.getSubjElRevokeList(queryMap);
    }

    //统计撤销待审核数量
    public long countRevokeToAudit(String token,String areaId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("auditState","0");
        queryMap.put("applySvId", WebUtil.getCurrentUser(token).getOrgId());//只能看本机构
        queryMap.put("areaId", areaId);
        return provider.countRevokeToAudit(queryMap);
    }

    public int addSubjEnforceLaw(String token,AsmsSubjEnforceLaw subjEnforceLaw){
        //查询是否有执法机构（机构名称、机构代码、所属区域和机构级别其中的一个相同）--有的话返回一个标识
        Map<String,Object> map = new HashMap();
        map.put("areaId",subjEnforceLaw.getElAreaId());
        map.put("name",subjEnforceLaw.getElName());
        //map.put("code",subjEnforceLaw.getElCode());
        //map.put("elLevelId",subjEnforceLaw.getElLevelId());
        List<AsmsSubjEnforceLaw> list = provider.getSubjEnforceLawByCondition(map);
        if(list!=null&&!list.isEmpty()){
            /*if(subjEnforceLaw.getElAreaId().equals("110000")) {//部级也是北京市
                for (AsmsSubjEnforceLaw queryResult : list) {
                    if (queryResult.getElLevel().equals(subjEnforceLaw.getElLevel())) {
                        return 2;
                    }
                }
            }else {*/
                return 2;
           // }
        }
        //设置执法机构一些字段信息
        subjEnforceLaw.setId(StringUtils.getUUIDString());
        subjEnforceLaw.setCreateBy(WebUtil.getCurrentUserId(token));
        subjEnforceLaw.setCreateTime(new Date());
        subjEnforceLaw.setStatus("0");//0表示没有流程，1表示还有变更待审核，2表示还有注销待审核，3表示还有撤销待审核
        subjEnforceLaw.setDelFlag("N");//N表示未删除，Y表示已逻辑删除
        //同步组织机构
        SysOrganization sysOrganization = new SysOrganization();
        sysOrganization.setOrgName(subjEnforceLaw.getElName());
        sysOrganization.setOrgType(RSAUtils.Module.ALES.name());
        sysOrganization.setRegionName(subjEnforceLaw.getElArea());
        sysOrganization.setTypeId(subjEnforceLaw.getElTypeId());
        sysOrganization.setLevelId(subjEnforceLaw.getElLevelId());
        sysOrganization.setOrgLevel(String.valueOf(Integer.valueOf(subjEnforceLaw.getElLevel())+1));
        if("1".equals(subjEnforceLaw.getElLevel())){
            sysOrganization.setRegionId(subjEnforceLaw.getElAreaId().substring(0,2)+"0000");
        }else if("2".equals(subjEnforceLaw.getElLevel())){
            sysOrganization.setRegionId(subjEnforceLaw.getElAreaId().substring(0,4)+"00");
        }else{
            sysOrganization.setRegionId(subjEnforceLaw.getElAreaId());
        }
//        sysOrganization.setRegionId(subjEnforceLaw.getElAreaId());
        sysOrganization.setOrgId(subjEnforceLaw.getId());
        sysOrganization.setDelFlag("N");
        SysUser sysUser = sysUserProvider.saveSysOranizationUser(sysOrganization,token,null);//返回机构管理员
        if(sysUser==null||sysUser.getId().isEmpty()){
            return 3;
        }
        return provider.addSubjEnforceLaw(subjEnforceLaw);
    }

    public List<String> importSubjEnforceLaw(MultipartFile file, HttpServletRequest request) throws Exception{
        //机构类型和机构级别要跟数据字典关联
        List<SysDictData> orgTypeList = sysDictProvider.getDictByType(DictType.ORGTYPE);//机构类型
        List<SysDictData> orgLevelList = sysDictProvider.getDictByType(DictType.ORGLEVEL);//机构级别
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet;//工作簿
        int rowNum;//每个工作簿的行数
        Row row;//工作簿里的一行
        Cell cell;//每个单元格
        boolean errorFlag = false;
        List<String> msgList = new ArrayList<>();//用来存放错误信息
        List<AsmsSubjEnforceLaw> subjElList = new ArrayList<>();//用来存放机构主体对象
        int startRowIndex = 2;//导入的起始行数
        for(int sheetIndex = 0;sheetIndex<workbook.getNumberOfSheets();sheetIndex++){//循环Sheet
            sheet = workbook.getSheetAt(sheetIndex);
            rowNum = sheet.getLastRowNum()+1;//有多少行
            for(int rowIndex = startRowIndex;rowIndex<rowNum;rowIndex++){//循环Sheet里面的row
                row = sheet.getRow(rowIndex);
                int msgRowIndex = rowIndex+1;
                AsmsSubjEnforceLaw subjEnforceLaw = new AsmsSubjEnforceLaw();
                //机构名称
                //机构名称不能为空，长度不能超过50个汉字--简单判断
                cell=row.getCell(1);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构名称不能为空并且不能超过50个汉字！");
                }else {
                    subjEnforceLaw.setElName(this.getCellValue(cell));
                }
                //机构代码
                //机构代码不能为空，长度不能超过50个汉字--简单判断
                cell = row.getCell(2);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构代码不能为空并且不能超过50个汉字！");
                }else {
                    subjEnforceLaw.setElCode(this.getCellValue(cell));
                }
                //机构级别
                //机构级别不能为空--简单判断
                cell = row.getCell(3);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，机构级别不能为空！");
                }
                for(SysDictData sysDictData:orgLevelList){
                    if(this.getCellValue(cell)!=null&&this.getCellValue(cell).equals(sysDictData.getDictName())){
                        subjEnforceLaw.setElLevelId(sysDictData.getId());
                    }
                }
                if(cell!=null) {
                    switch (this.getCellValue(row.getCell(3))) {
                        case "部级":
                            subjEnforceLaw.setElLevel("0");
                            break;
                        case "省级":
                            subjEnforceLaw.setElLevel("1");
                            break;
                        case "市级":
                            subjEnforceLaw.setElLevel("2");
                            break;
                        case "县级":
                            subjEnforceLaw.setElLevel("3");
                            break;
                        default:
                            errorFlag = true;
                            msgList.add("第" + (sheetIndex + 1) + "个工作簿第" + msgRowIndex + "行，机构级别只能为部级、省级、市级、县级中的一种！");
                    }
                }
                //所属区域id
                //区域编码不能为空--简单判断
                cell = row.getCell(4);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属区域行政编号不能为空！");
                }else {
                    subjEnforceLaw.setElAreaId(this.getCellValue(cell).trim());//机构区域id
                }
                //区域名称
                cell = row.getCell(5);
                StringBuilder areaSb=new StringBuilder();
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                cell = row.getCell(6);
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                cell = row.getCell(7);
                if(cell!=null){
                    areaSb.append(this.getCellValue(cell));
                }
                subjEnforceLaw.setElArea(areaSb.toString());//机构区域

                //地址
                //地址不能为空--简单判断
                cell = row.getCell(8);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，地址不能为空并且不能超过50个汉字！");
                }else {
                    subjEnforceLaw.setElAddress(this.getCellValue(row.getCell(8)));//地址
                }
                //负责人
                //负责人不能为空--简单判断
                cell = row.getCell(9);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，负责人不能为空！");
                }else {
                    subjEnforceLaw.setElLeader(this.getCellValue(row.getCell(9)));//负责人
                }
                //负责人电话
                cell = row.getCell(10);
                if(cell!=null) {
                    subjEnforceLaw.setElLeaderPhone(this.getCellValue(row.getCell(10)));//负责人电话
                }
                //联系人
                //联系人不能为空--简单判断
                cell = row.getCell(11);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人不能为空！");
                }else {
                    subjEnforceLaw.setElContact(this.getCellValue(row.getCell(11)));//联系人
                }
                //联系人电话
                //联系人电话不能为空--简单判断
                cell = row.getCell(12);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人电话不能为空！");
                }else {
                    subjEnforceLaw.setElContactPhone(this.getCellValue(cell));//联系人电话
                }
                //联系人qq
                cell = row.getCell(13);
                if(cell!=null){
                    subjEnforceLaw.setElContactQQ(this.getCellValue(cell));//联系人qq
                }
                //电子邮箱
                cell = row.getCell(14);
                if(cell!=null){
                    subjEnforceLaw.setElContactEmail(this.getCellValue(cell));//邮箱
                }
                //邮编
                cell = row.getCell(15);
                if(cell!=null){
                    if(!"".equals(this.getCellValue(cell))&&this.getCellValue(cell).length()!=6) {
                        errorFlag = true;
                        msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，邮编长度只能为6位！");
                    }else {
                        subjEnforceLaw.setElPostcode(this.getCellValue(cell));//邮编
                    }
                }
                for(SysDictData dictData:orgTypeList){//机构类型
                    if("执法机构".equals(dictData.getDictName())){
                        subjEnforceLaw.setElTypeId(dictData.getId());
                    }
                }
                subjEnforceLaw.setElType("执法机构");
                //机构性质
                cell = row.getCell(16);
                if(cell!=null){
                    subjEnforceLaw.setElUnitNature(this.getCellValue(cell));//机构性质
                }
                //地址
                //地址不能为空--简单判断
                cell = row.getCell(17);
                if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，具体办事单位不能为空并且不能超过50个汉字！");
                }else {
                    subjEnforceLaw.setElWorkBody(this.getCellValue(cell));//具体办事单位
                }
                //联系人电话不能为空--简单判断
                cell = row.getCell(18);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，执法人数不能为空！");
                }else {
                    subjEnforceLaw.setElPeopleNum(this.getCellValue(cell));//执法人数
                }
                subjElList.add(subjEnforceLaw);
            }
        }
        if(errorFlag){//有不合法数据
            return msgList;
        }else {
            //可以导入
            for (int i=0;i<subjElList.size();i++){
                AsmsSubjEnforceLaw subjEl=subjElList.get(i);
                if(this.addSubjEnforceLaw(request.getHeader("token"),subjEl)==2){//添加数据并判断有无规则下的重复数据
                    //有
                    msgList.add("第"+(i+1)+"行数据添加失败");
                }
            }
            if (msgList.size()!=0 && null!=msgList){
                msgList.add("以上"+msgList.size()+"条数据与已有执法机构数据重复。规则如下：机构名称、机构代码、所属区域和机构级别其中的一个不能相同");
                return msgList;
            }
        }
        return new ArrayList<>();
    }

    public int addSubjElRevoke(String token,AsmsSubjElRevoke subjElRevoke){
        subjElRevoke.setId(StringUtils.getUUIDString());
        subjElRevoke.setApplyTime(new Date());
        subjElRevoke.setApplySvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjElRevoke.setApplyUserId(WebUtil.getCurrentUserId(token));
        subjElRevoke.setAuditState("0");//0-未审核，1-审核通过，2-审核不通过
        subjElRevoke.setDelFlag("N");//N-未删除，Y-逻辑删除
        sysUserProvider.cancelSysOranization(subjElRevoke.getElId(),token,4);//撤销申请中
        return provider.addSubjElRevoke(subjElRevoke);
    }

    public AsmsSubjEnforceLaw findSubjEnforceLawById(AsmsSubjEnforceLaw subjEnforceLaw){
        return provider.findSubjEnforceLawById(subjEnforceLaw.getId());
    }

    /**
     * 通过ID获取单个执法机构主体变更申请
     * @param subjElChange
     * @return
     */
    public AsmsSubjElChange findSubjElChangeById(AsmsSubjElChange subjElChange){
        return provider.findSubjElChangeById(subjElChange.getId());
    }

    /**
     * 通过ID获取单个执法机构主体注销申请
     * @param subjElCancel
     * @return
     */
    public AsmsSubjElCancel findSubjElCancelById(AsmsSubjElCancel subjElCancel){
        return provider.findSubjElCancelById(subjElCancel.getId());
    }

    /**
     * 通过ID获取单个执法机构主体撤销申请
     * @param subjElRevoke
     * @return
     */
    public AsmsSubjElRevoke findSubjElRevokeById(AsmsSubjElRevoke subjElRevoke){
        return provider.findSubjElRevokeById(subjElRevoke.getId());
    }

    /**
     * 审核执法机构主体变更
     * @param subjElChange
     */
    public void auditSubjElChange(String token,AsmsSubjElChange subjElChange){
        subjElChange.setAuditTime(new Date());
        subjElChange.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjElChange.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjEnforceLaw subjEnforceLaw = new AsmsSubjEnforceLaw();
        subjEnforceLaw.setId(subjElChange.getApplyElId());
        subjEnforceLaw = this.findSubjEnforceLawById(subjEnforceLaw);
        //审核通过,同步组织机构信息
        if("1".equals(subjElChange.getAuditState())){
            SysOrganization sysOrganization = sysOrganizationProvider.findSysOrganizationByOrgId(subjEnforceLaw.getId());
            if(StringUtils.isNotBlank(subjElChange.getElName())){
                sysOrganization.setOrgName(subjElChange.getElName());
            }
            if(StringUtils.isNotBlank(subjElChange.getElArea())){
                sysOrganization.setRegionName(subjElChange.getElArea());
            }
            if(StringUtils.isNotBlank(subjElChange.getElAreaId())){
                sysOrganization.setRegionId(subjElChange.getElAreaId());
            }
            sysOrganizationProvider.updateSysOrganization(sysOrganization);
        }
        provider.auditSubjElChange(subjEnforceLaw, subjElChange);
    }

    /**
     * 审核执法机构主体注销申请
     * @param subjElCancel
     */
    public void auditSubjElCancel(String token,AsmsSubjElCancel subjElCancel){
        subjElCancel.setAuditTime(new Date());
        subjElCancel.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjElCancel.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjEnforceLaw subjEnforceLaw = new AsmsSubjEnforceLaw();
        subjEnforceLaw.setId(subjElCancel.getElId());
        subjEnforceLaw = this.findSubjEnforceLawById(subjEnforceLaw);
        provider.auditSubjElCancel(subjEnforceLaw, subjElCancel);
        //注销机构之后再注销相关账号
        if("1".equals(subjElCancel.getAuditState())){
            sysUserProvider.cancelSysOranization(subjEnforceLaw.getId(),token,3);//已注销
        }
        if("2".equals(subjElCancel.getAuditState())){
            sysUserProvider.cancelSysOranization(subjEnforceLaw.getId(),token,1);//恢复正常
        }
    }

    /**
     * 审核执法机构主体撤销申请
     * @param subjElRevoke
     */
    public void auditSubjElRevoke(String token,AsmsSubjElRevoke subjElRevoke){
        subjElRevoke.setAuditTime(new Date());
        subjElRevoke.setAuditSvId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        subjElRevoke.setAuditUserId(WebUtil.getCurrentUserId(token));
        AsmsSubjEnforceLaw subjEnforceLaw = new AsmsSubjEnforceLaw();
        subjEnforceLaw.setId(subjElRevoke.getElId());
        subjEnforceLaw = this.findSubjEnforceLawById(subjEnforceLaw);
        provider.auditSubjElRevoke(subjEnforceLaw,subjElRevoke);
        //撤销机构之后再注销相关账号
        if("1".equals(subjElRevoke.getAuditState())){
            sysUserProvider.cancelSysOranization(subjEnforceLaw.getId(),token,5);//已撤销
        }
        if("2".equals(subjElRevoke.getAuditState())){
            sysUserProvider.cancelSysOranization(subjEnforceLaw.getId(),token,1);//恢复正常
        }
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
