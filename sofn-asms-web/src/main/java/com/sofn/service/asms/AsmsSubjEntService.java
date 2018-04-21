package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.DictType;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.OrgFormEnum;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.UserEnum;
import com.sofn.core.util.WebUtil;
import com.sofn.core.util.encrypt.RSAUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.asms.AsmsCheckTaskProvider;
import com.sofn.provider.asms.AsmsSubjEntProvider;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.tts.*;
import com.sofn.util.Page;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.zookeeper.KeeperException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:17
 */
@Service
public class AsmsSubjEntService extends BaseService<AsmsSubjEntProvider,AsmsSubjEntTemp>{

    @DubboReference
    public void setProvider(AsmsSubjEntProvider provider){
        this.provider = provider;
    }

    private AsmsCheckTaskProvider asmsCheckTaskProvider;
    @DubboReference
    public void setAsmsCheckTaskProvider(AsmsCheckTaskProvider asmsCheckTaskProvider){
        this.asmsCheckTaskProvider = asmsCheckTaskProvider;
    }
    private SysDictProvider sysDictProvider;
    @DubboReference
    public void setSysDictProvider(SysDictProvider sysDictProvider){
        this.sysDictProvider = sysDictProvider;
    }

    //主体采购,销售查询接口
    private TtsScltxxcjXsdjProvider ttsScltxxcjXsdjProvider;
    @DubboReference
    public void  setTtsScltxxcjXsdjProvider(TtsScltxxcjXsdjProvider ttsScltxxcjXsdjProvider){
        this.ttsScltxxcjXsdjProvider = ttsScltxxcjXsdjProvider;
    }

    //生产批次查询接口
    private TtsScltxxcjScglProvider ttsScltxxcjScglProvider;
    @DubboReference
    public void  setTtsScltxxcjScglProvider(TtsScltxxcjScglProvider ttsScltxxcjScglProvider){
        this.ttsScltxxcjScglProvider = ttsScltxxcjScglProvider;
    }

    //主体不良记录接口
    private TtsScltxxcjComplainProvider ttsScltxxcjComplainProvider;
    @DubboReference
    public void  setTtsScltxxcjComplainProvider(TtsScltxxcjComplainProvider TtsScltxxcjComplainProvider){
        this.ttsScltxxcjComplainProvider = TtsScltxxcjComplainProvider;
    }


    //生产经营主体信息、待审核的接口
    private TtsScltxxcjRegiterProvider regiterProvider;
    @DubboReference
    public void setRegiterProvider(TtsScltxxcjRegiterProvider regiterProvider){
        this.regiterProvider = regiterProvider;
    }

    private SysUserProvider sysUserProvider;
    @DubboReference
    public void setSysUserProvider(SysUserProvider sysUserProvider){
        this.sysUserProvider = sysUserProvider;
    }

    private TtsScltxxcjUserProvider ttsScltxxcjUserProvider;
    @DubboReference
    public void setTtsScltxxcjUserProvider(TtsScltxxcjUserProvider ttsScltxxcjUserProvider){
        this.ttsScltxxcjUserProvider = ttsScltxxcjUserProvider;
    }
    @DubboReference
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;

    //生产经营主体--变更、注销、撤销的接口
    private TtsScltxxcjUserChangeRecordProvider changeRecordProvider;
    @DubboReference
    public void setChangeRecordProvider(TtsScltxxcjUserChangeRecordProvider changeRecordProvider){
        this.changeRecordProvider = changeRecordProvider;
    }

    public PageInfo<TtsScltxxcjRegiter> getSubjEntList(TtsScltxxcjRegiter entity,Page page,String token) {
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
        Map<String,Object> map = new HashMap<>();
        //根据状态查询，有可能多个状态
        if(entity.getApproveStatus()!=null && !"".equals(entity.getApproveStatus().trim())){
            map.put("approveStatus",entity.getApproveStatus().split(","));
        }
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        map.put("badRecord",entity.getBadRecord());
        //所属行业
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            map.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //map.put("entityIndustry",entity.getEntityIndustry());
        map.put("entityType",entity.getEntityType());
        map.put("entityScale",entity.getEntityScale());
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("userIdcode",entity.getUserIdcode());
        map.put("area",entity.getArea());
        //map.put("approveStatus","1");//备案审核通过
        map.put("cUserRegionId",regionId);
        List list = regiterProvider.getSubList(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(regiterProvider.getSubjEntTotal(map));
        return pageInfo;
    }

    public PageInfo<AsmsSubjEntTemp> getSubjEntTempList(AsmsSubjEntTemp subjEntTemp,Page page,String dateBegin,String dateEnd){
        Map<String,Object> map = new HashMap<>();
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        //所属行业
        if (subjEntTemp.getEntityIndustry()!=null && !"".equals(subjEntTemp.getEntityIndustry().trim())) {
            map.put("entityIndustry",subjEntTemp.getEntityIndustry().split(","));
        }
        map.put("entityType",subjEntTemp.getEntityType());
        map.put("entityName",subjEntTemp.getEntityName());
        map.put("entityScale",subjEntTemp.getEntityScale());
        map.put("areaId",subjEntTemp.getAreaId());
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        return provider.getSubjEntTempList(map);
    }

    public List<AsmsSubjEntTemp> getSubjEntTempListByCode(String code){
        return provider.getSubjEntTempListByCode(code);
    }

    public PageInfo<TtsScltxxcjRegiter> getSubjEntRegisterList(TtsScltxxcjRegiter register,Page page,String dateBegin,String dateEnd){
        Map<String,Object> map = new HashMap<>();
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        //所属行业
        if (register.getEntityIndustry()!=null && !"".equals(register.getEntityIndustry().trim())) {
            map.put("entityIndustry",register.getEntityIndustry().split(","));
        }
        //map.put("entityIndustry",register.getEntityIndustry());
        map.put("entityType",register.getEntityType());
        map.put("enterpriseName",register.getEnterpriseName());
        map.put("entityScale",register.getEntityScale());
        map.put("area",register.getArea());
        map.put("beginDate",dateBegin);
        map.put("endDate",dateEnd);
        //根据状态查询，有可能多个状态//备案待审核-以前固定0，现在都能获取
        if(register.getApproveStatus()!=null && !"".equals(register.getApproveStatus().trim())){
            map.put("approveStatus",register.getApproveStatus().split(","));
        }
        List list = regiterProvider.getSubList(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(regiterProvider.getSubjEntTotal(map));
        return pageInfo;
    }

    public long countRegisterToAudit(TtsScltxxcjRegiter regiter){
        Map<String,Object> map = new HashMap<>();
        map.put("area",regiter.getArea());
        map.put("approveStatus","0");//备案待审核
        //全部所属行业
        if(regiter.getEntityIndustry()!=null && !"".equals(regiter.getEntityIndustry().trim()))
        map.put("entityIndustry",regiter.getEntityIndustry().split(","));
        return regiterProvider.getTotal(map);
    }

    public PageInfo getSubjEntChangeList(TtsScltxxcjRegiter entity,Page page,String dateBegin,String dateEnd){
        Map<String,Object> map = new HashMap<>();
        map.put("approveType","2");
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        //所属行业
        if(entity.getEntityIndustry()!=null&& !"".equals(entity.getEntityIndustry().trim())){
            map.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //map.put("entityIndustry",entity.getEntityIndustry());
        map.put("entityType",entity.getEntityType());
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("area",entity.getArea());
        map.put("entityScale",entity.getEntityScale());
        map.put("beginDate",dateBegin);
        map.put("endDate",dateEnd);
        map.put("approveStatus",entity.getApproveStatus());
        List list = changeRecordProvider.getEntityUserChangeRecordPageInfoByCondition(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(changeRecordProvider.getTotal(map));
        return pageInfo;
    }

    public long countChangeToAudit(TtsScltxxcjRegiter regiter){
        Map<String,Object> map = new HashMap<>();
        map.put("approveType","2");
        map.put("area",regiter.getArea());
        map.put("approveStatus","0");
        if(regiter.getEntityIndustry()!=null && !"".equals(regiter.getEntityIndustry().trim()))
            map.put("entityIndustry",regiter.getEntityIndustry().split(","));
        return changeRecordProvider.getTotal(map);
    }

    public PageInfo getSubjEntRevokeList(TtsScltxxcjRegiter entity,Page page,String dateBegin,String dateEnd){
        Map<String,Object> map = new HashMap<>();
        map.put("approveType","3");
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            map.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //map.put("entityIndustry",entity.getEntityIndustry());
        map.put("entityType",entity.getEntityType());
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("area",entity.getArea());
        map.put("entityScale",entity.getEntityScale());
        map.put("beginDate",dateBegin);
        map.put("endDate",dateEnd);
        map.put("approveStatus",entity.getApproveStatus());
        List list = changeRecordProvider.getEntityUserChangeRecordPageInfoByCondition(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(changeRecordProvider.getTotal(map));
        return pageInfo;
    }

    public long countRevokeToAudit(TtsScltxxcjRegiter regiter){
        Map<String,Object> map = new HashMap<>();
        map.put("approveType","3");
        map.put("area",regiter.getArea());
        map.put("approveStatus","0");
        if(regiter.getEntityIndustry()!=null && !"".equals(regiter.getEntityIndustry().trim()))
            map.put("entityIndustry",regiter.getEntityIndustry().split(","));
        return changeRecordProvider.getTotal(map);
    }

    public PageInfo getSubjEntCancelList(TtsScltxxcjRegiter entity,Page page,String dateBegin,String dateEnd){
        Map<String,Object> map = new HashMap<>();
        map.put("approveType","1");
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            map.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //map.put("entityIndustry",entity.getEntityIndustry());
        map.put("entityType",entity.getEntityType());
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("area",entity.getArea());
        map.put("entityScale",entity.getEntityScale());
        map.put("beginDate",dateBegin);
        map.put("endDate",dateEnd);
        map.put("approveStatus",entity.getApproveStatus());
        List list = changeRecordProvider.getEntityUserChangeRecordPageInfoByCondition(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(changeRecordProvider.getTotal(map));
        return pageInfo;
    }

    public long countCancelToAudit(TtsScltxxcjRegiter regiter){
        Map<String,Object> map = new HashMap<>();
        map.put("approveType","1");
        map.put("area",regiter.getArea());
        map.put("approveStatus","0");
        if(regiter.getEntityIndustry()!=null && !"".equals(regiter.getEntityIndustry().trim()))
            map.put("entityIndustry",regiter.getEntityIndustry().split(","));
        return changeRecordProvider.getTotal(map);
    }

    public TtsScltxxcjRegiter findSubjEntById(TtsScltxxcjRegiter entity){
        return regiterProvider.selectByEntityId(entity.getId());
    }

    public TtsScltxxcjRegiter findSubjEntByEntityIdCode(TtsScltxxcjRegiter entity){
        Map queryParam = new HashMap();
        queryParam.put("entityIdCode",entity.getEntityIdcode());
        return regiterProvider.selectByEntityIdCode(queryParam);
    }

    public TtsScltxxcjRegiter findSubjEntByUserIdCode(TtsScltxxcjRegiter entity){
        Map queryParam = new HashMap();
        queryParam.put("userIdCode",entity.getUserIdcode());
        return regiterProvider.selectByUserIdCode(queryParam);
    }

    public PageInfo<TtsScltxxcjUserChangeRecord> getChangeListByEntityId(String entityId,long start,long length){
        Map<String,Object> map = new HashMap();
        map.put("pageNum",start/length+1);
        map.put("pageSize",length);
        map.put("entityId",entityId);
        return changeRecordProvider.getPageInfo(map);
    }

    public AsmsSubjEntTemp findSubjEntTempById(AsmsSubjEntTemp subjEntTemp){
        return provider.findSubjEntTempById(subjEntTemp.getId());
    }

    public TtsScltxxcjUserChangeRecord findSubjEntChangeById(String id){
        TtsScltxxcjUserChangeRecord changeRecord = changeRecordProvider.selectById(id);
        return changeRecord;
    }

    public int addSubjEntRevokeApply(String token,TtsScltxxcjUserChangeRecord entity){
        entity.setId(StringUtils.getUUIDString());
        entity.setApproveStatus("0");
        entity.setApproveType("3");
        entity.setApplyUserId(WebUtil.getCurrentUserId(token));
        entity.setApplyUpdateName(WebUtil.getCurrentUserId(token));
        entity.setApplyUpdateTime(new Date());
        entity.setDelFlag("N");
        return changeRecordProvider.insert(entity);
    }

    public void auditSubjEntRegister(String token,TtsScltxxcjRegiter register) throws Exception {
        register.setApproveName(WebUtil.getCurrentUserId());
        register.setApproveTime(new Date());
        register.setApproveUserIdcode(WebUtil.getCurrentUserId());
        Map<String,Object> map = new HashedMap();
        map.put("id",register.getId());
        map.put("entityIdcode",register.getEntityIdcode());
        map.put("userIdcode",register.getUserIdcode());
        map.put("approveStatus",register.getApproveStatus());
        map.put("approveOpinion",register.getApproveOpinion());
        map.put("approveName",WebUtil.getCurrentUser(token).getUserName());
        map.put("approveTime",new Date());
        map.put("approveUserIdcode",WebUtil.getCurrentUser(token).getId());
        map.put("account",register.getAccount()!=null?register.getAccount():null);
        regiterProvider.updateEntityByEntity(map, token);
    }

    public int auditSubjEntChange(String token,TtsScltxxcjUserChangeRecord entity){
        Map<String,Object> map = new HashMap();
        map.put("id",entity.getId());
        map.put("approveType",entity.getApproveType());
        map.put("approveStatus",entity.getApproveStatus());
        map.put("approveOpinion",entity.getApproveOpinion());
        map.put("approveName",WebUtil.getCurrentUserId(token));
        map.put("approveTime",new Date());
        map.put("approveUserIdcode",WebUtil.getCurrentUserId(token));
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("area",entity.getArea());
        map.put("creditCode",entity.getCreditCode());
        map.put("address",entity.getAddress());
        map.put("contactName",entity.getContactName());
        map.put("contactPhone",entity.getContactPhone());
//        map.put("legalName",entity.getLegalName());
//        map.put("legalPhone",entity.getLegalPhone());

        map.put("isLong",entity.getIsLong());
        map.put("cardType",entity.getCardType());
        map.put("businessOperationStart",entity.getBusinessOperationStart());
        map.put("businessOperationEnd",entity.getBusinessOperationEnd());
        map.put("longitude",entity.getLongitude());
        map.put("latitude",entity.getLatitude());
        map.put("legalName",entity.getLegalName());
        map.put("legalIdnumber",entity.getLegalIdnumber());
        map.put("legalPhone",entity.getLegalPhone());

        map.put("documentImages",entity.getDocumentImages());
        map.put("updateBy",WebUtil.getCurrentUserId(token));
        map.put("updateTime",new Date());
        map.put("entityId", entity.getEntityId());
        map.put("businessLicenceimg", entity.getBusinessLicenceimg());
        map.put("organizationCertificateimg", entity.getOrganizationCertificateimg());
        map.put("positiveIdcardeimg", entity.getPositiveIdcardeimg());
        map.put("negativeIdcardimg", entity.getNegativeIdcardimg());
        map.put("handIdcardimg", entity.getHandIdcardimg());
        //调用追溯系统接口进行生产经营主体备案变更审核
        int result = changeRecordProvider.updateEntityUserChangeRecordByMap(map);
        return result;
    }

    public AsmsSubjEntTemp addSubjEntTemp(String token,AsmsSubjEntTemp subjEntTemp){
        subjEntTemp.setId(StringUtils.getUUIDString());
        subjEntTemp.setDelFlag("N");
        subjEntTemp.setEnable("0");
        subjEntTemp.setCreateBy(WebUtil.getCurrentUserId(token));
        subjEntTemp.setCreateTime(new Date());
        subjEntTemp.setCreateSuperviseId(sysUserProvider.findSysUserOrganization(token).getOrgId());
        //生成主体临时身份码
        String entityType = subjEntTemp.getEntityType();//主体类别
        String entityScale = "";//企业还是个人
        IdGenerator idGenerator = new IdGenerator();
        if("企业/个体工商户".equals(subjEntTemp.getEntityScale())||"合作社".equals(subjEntTemp.getEntityScale())){
            entityScale = "企业";
        }
        if("家庭农场".equals(subjEntTemp.getEntityScale())||"个人".equals(subjEntTemp.getEntityScale())){
            entityScale = "个人";
        }
        switch(entityType+"+"+entityScale){
            case "生产主体+企业":
                subjEntTemp.setIdentityCode(idGenerator.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainWorkingBody,
                        IdGenerator.OrganizationForms.Enterprise,subjEntTemp.getOrgCode()));
                break;
            case "生产主体+个人":
                subjEntTemp.setIdentityCode(idGenerator.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainWorkingBody,
                        IdGenerator.OrganizationForms.Individual,subjEntTemp.getOrgCode()));
                break;
            case "经营主体+企业":
                subjEntTemp.setIdentityCode(idGenerator.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainManagementBody,
                        IdGenerator.OrganizationForms.Enterprise,subjEntTemp.getOrgCode()));
                break;
            case "经营主体+个人":
                subjEntTemp.setIdentityCode(idGenerator.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainManagementBody,
                        IdGenerator.OrganizationForms.Individual,subjEntTemp.getOrgCode()));
                break;
            case "生产经营主体+企业":
                subjEntTemp.setIdentityCode(idGenerator.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                        IdGenerator.OrganizationForms.Enterprise,subjEntTemp.getOrgCode()));
                break;
            case "生产经营主体+个人":
                subjEntTemp.setIdentityCode(idGenerator.getTemporaryIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                        IdGenerator.OrganizationForms.Individual,subjEntTemp.getOrgCode()));
                break;
        }
        int result = provider.addSubjEntTemp(subjEntTemp);
        if(result==1) {
            return subjEntTemp;
        }else{
            return null;
        }

    }
    //判断生产经营主体是否满足注销条件
    public Map<String, Object> findSubjEntStateInfoForRevokeApply(String subjEntId){
        Map<String,Object> map = new HashMap();
        TtsScltxxcjRegiter ttsScltxxcjRegiter =  regiterProvider.selectByEntityId(subjEntId);
        String entityIdcode = ttsScltxxcjRegiter.getEntityIdcode();
        //判断主体是否处于备案变更申请中
        if("3".equals(ttsScltxxcjRegiter.getApproveStatus())){
            map.put("canRevokeApply",false);
            map.put("revokeApplyMsg","该主体处于备案变更申请中，不能申请注销");
            return map;
        }
        //判断主体是否处于备案变更申请中
        if(!"1".equals(ttsScltxxcjRegiter.getApproveStatus())){
            map.put("canRevokeApply",false);
            map.put("revokeApplyMsg","该主体状态异常，不能申请注销");
            return map;
        }
        //判断该主体是否有产品处于销售流通下游未确认状态
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("confirmState","N");
        queryParams.put("pageNum",1);
        queryParams.put("pageSize",10);
        queryParams.put("entityIdcode",entityIdcode);
        Long saleHistoryCount = ttsScltxxcjXsdjProvider.getPageInfo(queryParams).getTotal();
        if(saleHistoryCount>0){
            map.put("canRevokeApply",false);
            map.put("revokeApplyMsg","该主体有产品处于销售流通下游未确认状态，不能申请注销");
            return map;
        }
        //判断主体是否有采购产品处于未确认状态
        queryParams.clear();
        queryParams.put("confirmState","N");
        queryParams.put("isCirculatesEnd","N");
        queryParams.put("pageNum",1);
        queryParams.put("pageSize",10);
        queryParams.put("entityIdcode",entityIdcode);
        Long purchaseCount = ttsScltxxcjXsdjProvider.queryPageForCg(queryParams).getTotal();
        if(purchaseCount>0){
            map.put("canRevokeApply",false);
            map.put("revokeApplyMsg","该主体有采购产品处于未确认状态，不能申请注销");
            return map;
        }
        //判断主体是否被投诉且监管部门且未处理完毕
        queryParams.clear();
        queryParams.put("pageNum",1);
        queryParams.put("pageSize",10);
        TtsScltxxcjComplain bean = new TtsScltxxcjComplain();
        bean.setBeEntityIdcode(entityIdcode);
        bean.setStatus("0");
        queryParams.put("bean",bean);
        Long complainCount = ttsScltxxcjComplainProvider.getPageInfo(queryParams).getTotal();
        if(complainCount>0){
            map.put("canRevokeApply",false);
            map.put("revokeApplyMsg","该主体被投诉且监管部门未处理完毕，不能申请注销");
            return map;
        }
        //该主体基地巡查不合格，且被执法检查中
        //暂时未实现

        //判断该主体是否有产品处于抽样检测中(例行,专项,监督抽查,复检,委托检测)
        queryParams.clear();
        queryParams.put("pageNum",1);
        queryParams.put("pageSize",1000000);
        queryParams.put("entityIdcode",entityIdcode.trim());
        List<TtsScltxxcjScgl> ttsScltxxcjScgls = ttsScltxxcjScglProvider.getPageInfo(queryParams).getList();
        queryParams.clear();
        Long count = 0L;
        Map<String,Object> ttsScltxxcjScgl;
        for(int i=0; i < ttsScltxxcjScgls.size();i++){
            ttsScltxxcjScgl = (Map<String, Object>) ttsScltxxcjScgls.get(i);
            queryParams.put("productTraceabilityCode",ttsScltxxcjScgl.get("PRODUCT_PC"));
            count = asmsCheckTaskProvider.findSampleCountByProductTraceabilityCode(queryParams);
            if(count>0){
                map.put("canRevokeApply",false);
                map.put("revokeApplyMsg","该主体有产品处于抽样检测中，不能申请注销");
                return map;
            }
        }
        map.put("canRevokeApply",true);
        map.put("revokeApplyMsg","注销资格检查通过");
        return map;
    }

    public List<String> importSubjEnt(MultipartFile file, HttpServletRequest request) throws Exception {
        //主体属性 和主体类型 要跟数据字典关联
        List<SysDictData> subjTypeList = sysDictProvider.getDictByType(DictType.SUBJTYPE);//主体类型
        List<SysDictData> subjpropertyList = sysDictProvider.getDictByType(DictType.SUBJPROPERTY);//主体属性
        List<SysDictData> orgIndustryList=sysDictProvider.getDictByType(DictType.INDUSTRYTYPE);//所属行业
        SimpleDateFormat myFmt=new SimpleDateFormat("yy-MM-dd");
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet;//工作簿
        String sheetName;//工作簿名
        int rowNum;//每个工作簿的行数
        Row row;//工作簿里的一行
        Cell cell;//每个单元格
        boolean errorFlag = false;
        List<String> msgList = new ArrayList<>();//用来存放错误信息
        List<TtsScltxxcjRegiter> subjEntList = new ArrayList<>();//用来存放生产主体对象
        int startRowIndex = 2;//导入的起始行数
        for(int sheetIndex = 0;sheetIndex<workbook.getNumberOfSheets();sheetIndex++){//循环Sheet
            sheet = workbook.getSheetAt(sheetIndex);
            sheetName=workbook.getSheetName(sheetIndex);
            rowNum = sheet.getLastRowNum()+1;//有多少行
            for(int rowIndex = startRowIndex;rowIndex<rowNum;rowIndex++){//循环Sheet里面的row
                row = sheet.getRow(rowIndex);
                int msgRowIndex = rowIndex+1;
                TtsScltxxcjRegiter subjectEnt = new TtsScltxxcjRegiter();
                //账号
                //用户名由字母、数字和下划组成，最少4位，最多18位
                cell = row.getCell(1);
                Pattern pat = Pattern.compile("[a-zA-Z_0-9_\\.]{1,}");
                Matcher matcher = pat.matcher(cell.toString());
                boolean rs = matcher.matches();
                if(!rs||this.getCellValue(cell).length()>18||this.getCellValue(cell).length()<4){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，用户名由字母、数字和下划线组成，最少4位，最多18位！");
                }else {
                    subjectEnt.setAccount(this.getCellValue(cell));
                }
                //密码由8-16位的任意字符组成
                cell = row.getCell(2);
                if(this.getCellValue(cell).length()>16||this.getCellValue(cell).length()<8){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，密码由8-16位的任意字符组成！");
                }else {
                    subjectEnt.setPassword(this.getCellValue(cell));
                    subjectEnt.setRePassword(this.getCellValue(cell));
                }
                //主体名称
                cell = row.getCell(3);
                if(this.getCellValue(cell).length()>60||this.getCellValue(cell).length()<1){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，主体名称由1-60个字符组成！");
                }else {
                    subjectEnt.setEnterpriseName(this.getCellValue(cell));
                }
                //主体类型
                cell = row.getCell(4);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，主体类型不能为空！");
                }else {
                    for (SysDictData subjtype : subjTypeList) {
                        if (cell != null && this.getCellValue(cell).equals(subjtype.getDictName())) {
                            subjectEnt.setEntityTypeName(subjtype.getDictName());
                            subjectEnt.setEntityType(subjtype.getId());
                        }
                    }
                }
                //主体属性
                cell = row.getCell(5);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，主体属性不能为空！");
                }else {
                    StringBuilder entityPropertys = new StringBuilder();
                    StringBuilder entityPropertyNames = new StringBuilder();
                    for (SysDictData subjPro : subjpropertyList) {
                        if (cell != null && this.getCellValue(cell).contains(subjPro.getDictName())) {
                            entityPropertys.append(subjPro.getId()).append(",");
                            entityPropertyNames.append(subjPro.getDictName()).append(",");
                        }
                    }
                    if(entityPropertys.toString().trim().length()>0) {
                        subjectEnt.setEntityProperty(entityPropertys.toString().trim().substring(0,entityPropertys.toString().trim().length()-1));  //主体属性id
                    }
                    if(entityPropertyNames.toString().trim().length()>0) {
                        subjectEnt.setEntityPropertyName(entityPropertyNames.toString().trim().substring(0,entityPropertyNames.toString().trim().length()-1));//主体属性
                    }
                }
                //所属行业
                //所属行业不能为空--简单判断
                cell = row.getCell(6);
                if(cell==null||"".equals(this.getCellValue(cell))){
                    errorFlag = true;
                    msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属行业不能为空！");
                }else {
                    StringBuilder industryNameSb = new StringBuilder();
                    StringBuilder industryIdSb = new StringBuilder();
                    for (SysDictData industryData : orgIndustryList) {
                        if (cell != null && this.getCellValue(cell).contains(industryData.getDictName())) {
                            industryNameSb.append(industryData.getDictName()).append(",");
                            industryIdSb.append(industryData.getId()).append(",");
                        }
                    }
                    if(industryNameSb.toString().trim().length()>0) {
                        subjectEnt.setEntityIndustryName(industryNameSb.toString().trim().substring(0,industryNameSb.toString().trim().length()-1));  //所属行业名称
                    }
                    if(industryIdSb.toString().trim().length()>0) {
                        subjectEnt.setEntityIndustry(industryIdSb.toString().trim().substring(0,industryIdSb.toString().trim().length()-1));//所属行业id
                    }
                }
                String[] split = subjectEnt.getEntityIndustryName().split(",");
                for(String industry: split ){
                    if(industry!=null||industry.equals("种植业")){
                        //种植业年产量
                        cell = row.getCell(7);
                        if(cell==null||"".equals(this.getCellValue(cell))){
                            errorFlag = true;
                            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，年产量（种植业）不能为空！");
                        }else {
                            subjectEnt.setAnnualOutput(this.getCellValue(cell));
                        }
                        cell = row.getCell(8);
                        if(cell==null||"".equals(this.getCellValue(cell))){
                            errorFlag = true;
                            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，年产量（种植业）单位不能为空！");
                        }else {
                            subjectEnt.setUnitName(this.getCellValue(cell));
                        }
                    }
                    if(industry!=null||industry.equals("畜牧业")){
                        //种植业年产量
                        cell = row.getCell(9);
                        if(cell==null||"".equals(this.getCellValue(cell))){
                            errorFlag = true;
                            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，年产量（畜牧业）不能为空！");
                        }else {
                            subjectEnt.setAnnualOutputX(this.getCellValue(cell));
                        }
                        cell = row.getCell(10);
                        if(cell==null||"".equals(this.getCellValue(cell))){
                            errorFlag = true;
                            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，年产量（畜牧业）单位不能为空！");
                        }else {
                            subjectEnt.setUnitNameX(this.getCellValue(cell));
                        }
                    }
                    if(industry!=null||industry.equals("渔业")){
                        //种植业年产量
                        cell = row.getCell(11);
                        if(cell==null||"".equals(this.getCellValue(cell))){
                            errorFlag = true;
                            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，年产量（渔业）不能为空！");
                        }else {
                            subjectEnt.setAnnualOutputS(this.getCellValue(cell));
                        }
                        cell = row.getCell(12);
                        if(cell==null||"".equals(this.getCellValue(cell))){
                            errorFlag = true;
                            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，年产量（渔业）单位不能为空！");
                        }else {
                            subjectEnt.setUnitNameS(this.getCellValue(cell));
                        }
                    }
                }


                if(sheetName!=null&&sheetName.equals("个人")){
                    subjectEnt.setEntityScale(sysDictProvider.getIdByName("个人").getId());
                    subjectEnt.setEntityScaleName("个人");
                    //导入工作簿 组织形式是"个人"的解析方法
                    errorFlag = setEntPerson(row, errorFlag, msgList, sheetIndex, msgRowIndex, subjectEnt);

                }
                if(sheetName!=null&&sheetName.equals("合作社")){
                    subjectEnt.setEntityScale(sysDictProvider.getIdByName("合作社").getId());
                    subjectEnt.setEntityScaleName("合作社");
                    //导入工作簿 组织形式是"合作社"的解析方法
                    errorFlag = setEntprise(row, errorFlag, msgList, sheetIndex, msgRowIndex, subjectEnt);
                }
                if(sheetName!=null&&sheetName.equals("家庭农场")){
                    subjectEnt.setEntityScale(sysDictProvider.getIdByName("家庭农场").getId());
                    subjectEnt.setEntityScaleName("家庭农场");
                    //导入工作簿 组织形式是"家庭农场"的解析方法
                    errorFlag = setEntPerson(row, errorFlag, msgList, sheetIndex, msgRowIndex, subjectEnt);
                }
                if(sheetName!=null&&sheetName.equals("企业个体工商户")){
                    subjectEnt.setEntityScale(sysDictProvider.getIdByName("企业/个体工商户").getId());
                    subjectEnt.setEntityScaleName("企业/个体工商户");
                    //导入工作簿 组织形式是"企业"的解析方法
                    errorFlag = setEntprise(row, errorFlag, msgList, sheetIndex, msgRowIndex, subjectEnt);
                }
                subjEntList.add(subjectEnt);
            }
        }
        if(errorFlag){
            return msgList;
        }else{
            for (int i=0;i<subjEntList.size();i++){
                TtsScltxxcjRegiter subj=subjEntList.get(i);
                if(this.addSubjEnt(request.getHeader("token"), subj)==2){//添加进数据库并判断是否有规则下的重复数据
                    //有规则下的重复数据
                    msgList.add("第"+(i+1)+"行数据添加失败!");
                }
            }
            //提示消息
            if(msgList.size()!=0 && null!=msgList){
                msgList.add("以上"+msgList.size()+"条数据与已有生产经营主体数据重复。规则如下：用户名不同，主体类型+身份证号或主体类型+统一社会信用代码/营业执照号不同");
                return msgList;
            }
        }
        return new ArrayList<>();
    }
    //导入工作簿 组织形式是"企业，合作社"的解析方法
    private boolean setEntprise(Row row, boolean errorFlag, List<String> msgList, int sheetIndex, int msgRowIndex, TtsScltxxcjRegiter subjectEnt) throws ParseException {
        Cell cell;
        SimpleDateFormat myFmt;
        Pattern pat;
        Matcher matcher;
        boolean rs;//证件类型
        cell = row.getCell(13);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，证件类型不能为空！");
        }else{
            if(cell.equals("三证合一营业执照(无独立组织机构代码证)")){
                subjectEnt.setCardType("三证合一营业执照（无独立组织机构代码证）");//因为excel传入的括号为英文，数据库需要的为中文
            }else{
                subjectEnt.setCardType("普通营业执照（有独立组织机构代码证）");
            }

        }
        if (subjectEnt.getCardType().equals("三证合一营业执照（无独立组织机构代码证）")){
            //企业注册号
            cell = row.getCell(14);
            if(cell==null||"".equals(this.getCellValue(cell))){
                errorFlag = true;
                msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，企业注册号不能为空！");
            }else{
                subjectEnt.setCreditCode(this.getCellValue(cell));
            }
        }else{
            //企业注册号
            cell = row.getCell(14);
            if(cell==null||"".equals(this.getCellValue(cell))){
                errorFlag = true;
                msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，企业注册号不能为空！");
            }else{
                subjectEnt.setCreditCode(this.getCellValue(cell));
            }
            //组织机构代码证号
            cell = row.getCell(15);
            if(cell==null||"".equals(this.getCellValue(cell))){
                errorFlag = true;
                msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，组织机构代码证号不能为空！");
            }else{
                subjectEnt.setOrganizationCode(this.getCellValue(cell));
            }
        }
        myFmt=new SimpleDateFormat("yy-MM-dd");
        //营业期限开始时间
        cell = row.getCell(16);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，营业期限开始时间不能为空！");
        }else{
            if("长期".equals(this.getCellValue(cell))){
                subjectEnt.setIsLong("true");
            }else{
                subjectEnt.setBusinessOperationStart( myFmt.parse( this.getCellValue(cell)));
            }

        }
        //营业期限结束时间
        cell = row.getCell(17);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，营业期限结束时间不能为空！");
        }else{
            if("长期".equals(this.getCellValue(cell))){
                subjectEnt.setBusinessOperation(this.getCellValue(cell));
            }else{
                subjectEnt.setBusinessOperationEnd( myFmt.parse( this.getCellValue(cell)));
            }

        }
        //所属区域id
        //区域编码不能为空--简单判断
        cell = row.getCell(18);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属区域行政编号不能为空！");
        }else {
            subjectEnt.setArea(this.getCellValue(cell));
        }
        //区域名称
        cell = row.getCell(19);
        StringBuilder areaSb=new StringBuilder();
        if(cell!=null){
            areaSb.append(this.getCellValue(cell));
        }else {
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属省不能为空！");
        }
        cell = row.getCell(20);
        if(cell!=null){
            areaSb.append(this.getCellValue(cell));
        }else {
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属市不能为空！");
        }
        cell = row.getCell(21);
        if(cell!=null){
            areaSb.append(this.getCellValue(cell));
        }
        else {
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属县不能为空！");
        }
        //地址
        //地址不能为空--简单判断
        cell = row.getCell(22);
        if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，地址不能为空并且不能超过50个汉字！");
        }else {
            areaSb.append(this.getCellValue(cell));
            subjectEnt.setAddress(areaSb.toString());
        }
        //经度
        cell = row.getCell(23);
        pat = Pattern.compile("^\\d+(\\.\\d{1,5})?$");
        matcher = pat.matcher(cell.toString());
        rs = matcher.matches();
        if(!rs){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，经纬度小数点后保留5位！");
        }else {
            subjectEnt.setLongitude(new BigDecimal(this.getCellValue(cell)));
        }
        //纬度
        cell = row.getCell(24);
        pat = Pattern.compile("^\\d+(\\.\\d{1,5})?$");
        matcher = pat.matcher(cell.toString());
        rs = matcher.matches();
        if(!rs){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，经纬度小数点后保留5位！");
        }else {
            subjectEnt.setLatitude(new BigDecimal(this.getCellValue(cell)));
        }
        //法人姓名
        cell = row.getCell(25);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，法人姓名不能为空！");
        }else {
            subjectEnt.setLegalName(this.getCellValue(cell));
            subjectEnt.setRealName(this.getCellValue(cell));
        }
        //身份证号由18位组成
        cell = row.getCell(26);
        if(this.getCellValue(cell).length()!=18){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，身份证号由18位组成！");
        }else {
            subjectEnt.setLegalIdnumber(this.getCellValue(cell));
            subjectEnt.setIdcode(this.getCellValue(cell));
        }
        //手机号为11位
        cell = row.getCell(27);
        if(this.getCellValue(cell).length()!=11){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，手机号为11位！");
        }else {
            subjectEnt.setLegalPhone(this.getCellValue(cell));
        }
        //联系人姓名
        cell = row.getCell(28);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，联系人姓名不能为空！");
        }else {
            subjectEnt.setContactName(this.getCellValue(cell));

        }
        //联系人身份证号由18位组成
        cell = row.getCell(29);
        if(this.getCellValue(cell).length()!=18){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，联系人身份证号由18位组成！");
        }else {
            subjectEnt.setLegalIdnumber(this.getCellValue(cell));
            subjectEnt.setIdcode(this.getCellValue(cell));
        }
        //手机号为11位
        cell = row.getCell(30);
        if(this.getCellValue(cell).length()!=11){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，手机号为11位！");
        }else {
            subjectEnt.setContactPhone(this.getCellValue(cell));
        }
        //联系人邮箱
        cell = row.getCell(31);
        pat = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        matcher = pat.matcher(cell.toString());
        rs = matcher.matches();
        if(!rs||cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，请输入正确的邮箱！");
        }else {
            subjectEnt.setContactEmail(this.getCellValue(cell));
        }
        //传真
        cell = row.getCell(32);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，传真不能为空！");
        }else {
            subjectEnt.setFaxNumber(this.getCellValue(cell));
        }
        return errorFlag;
    }
    //导入工作簿 组织形式是"个人、家庭农场"的解析方法
    private boolean setEntPerson(Row row, boolean errorFlag, List<String> msgList, int sheetIndex, int msgRowIndex, TtsScltxxcjRegiter subjectEnt) {
        Cell cell;
        Pattern pat;
        Matcher matcher;
        boolean rs;
        //所属区域id
        //区域编码不能为空--简单判断
        cell = row.getCell(13);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属区域行政编号不能为空！");
        }else {
            subjectEnt.setArea(this.getCellValue(cell));
        }
        //区域名称
        cell = row.getCell(14);
        StringBuilder areaSb=new StringBuilder();
        if(cell!=null){
            areaSb.append(this.getCellValue(cell));
        }else {
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属省不能为空！");
        }
        cell = row.getCell(15);
        if(cell!=null){
            areaSb.append(this.getCellValue(cell));
        }else {
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属市不能为空！");
        }
        cell = row.getCell(16);
        if(cell!=null){
            areaSb.append(this.getCellValue(cell));
        }
        else {
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，所属县不能为空！");
        }
        //地址
        cell = row.getCell(17);
        if(cell==null||"".equals(this.getCellValue(cell))||this.getCellValue(cell).length()>50){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，地址不能为空并且不能超过50个汉字！");
        }else {
            areaSb.append(this.getCellValue(cell));
            subjectEnt.setAddress(areaSb.toString());
        }
        //经度
        cell = row.getCell(18);
        pat = Pattern.compile("^\\d+(\\.\\d{1,5})?$");
        matcher = pat.matcher(cell.toString());
        rs = matcher.matches();
        if(!rs){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，经纬度小数点后保留5位！");
        }else {
            subjectEnt.setLongitude(new BigDecimal(this.getCellValue(cell)));
        }
        //纬度
        cell = row.getCell(19);
        pat = Pattern.compile("^\\d+(\\.\\d{1,5})?$");
        matcher = pat.matcher(cell.toString());
        rs = matcher.matches();
        if(!rs){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，经纬度小数点后保留5位！");
        }else {
            subjectEnt.setLatitude(new BigDecimal(this.getCellValue(cell)));
        }
        //姓名由2-8个字符组成
        cell = row.getCell(20);
        if(this.getCellValue(cell).length()>8||this.getCellValue(cell).length()<2){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，姓名由2-8个字符组成！");
        }else {
            subjectEnt.setLegalName(this.getCellValue(cell));
            subjectEnt.setRealName(this.getCellValue(cell));
        }
        //身份证号由18位组成
        cell = row.getCell(21);
        if(this.getCellValue(cell).length()!=18){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，身份证号由18位组成！");
        }else {
            subjectEnt.setLegalIdnumber(this.getCellValue(cell));
            subjectEnt.setIdcode(this.getCellValue(cell));
        }
        //手机号为11位
        cell = row.getCell(22);
        if(this.getCellValue(cell).length()!=11){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，手机号为11位！");
        }else {
            subjectEnt.setLegalPhone(this.getCellValue(cell));
        }
        //联系人邮箱
        cell = row.getCell(23);
        pat = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        matcher = pat.matcher(cell.toString());
        rs = matcher.matches();
        if(!rs||cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行格式错误，请输入正确的邮箱！");
        }else {
            subjectEnt.setContactEmail(this.getCellValue(cell));
        }
        //传真
        cell = row.getCell(24);
        if(cell==null||"".equals(this.getCellValue(cell))){
            errorFlag = true;
            msgList.add("第"+(sheetIndex+1)+"个工作簿第"+msgRowIndex+"行，传真号码不能为空！");
        }else {
            subjectEnt.setFaxNumber(this.getCellValue(cell));
        }
        subjectEnt.setContactName(subjectEnt.getLegalName());
        subjectEnt.setContactPhone(subjectEnt.getLegalPhone());
        return errorFlag;
    }





    //得到单元格值的方法
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

    //添加生产经营主体的方法
    public int addSubjEnt(String token,TtsScltxxcjRegiter subj){
        //生产经营主体判重
        // 企业/合作社类型：主体类型+统一社会信用代码/营业执照号
        //个人/家庭农场类型：主体类型+身份证号
        Map<String,Object> map = new HashMap();
        if(subj.getEntityScaleName().equals("个人")||subj.getEntityScaleName().equals("家庭农场")){
            map.put("account",subj.getAccount());
            map.put("entityType",subj.getEntityType());
            map.put("legalIdnumber",subj.getLegalIdnumber());
            List<TtsScltxxcjRegiter> list = regiterProvider.getSubjEntByCondition(map);
            if(list!=null&&!list.isEmpty()) {
                return 2;
            }
        }
        if(subj.getEntityScaleName().equals("企业/个体工商户")||subj.getEntityScaleName().equals("合作社")){
            map.put("account",subj.getAccount());
            map.put("entityTypeName",subj.getEntityType());
            map.put("creditCode",subj.getCreditCode());
            List<TtsScltxxcjRegiter> list = regiterProvider.getSubjEntByUnionCredit(map);
            if(list!=null&&!list.isEmpty()) {
                return 2;
            }
        }
        //设置生产经营主体一些字段信息
        subj.setId(StringUtils.getUUIDString());
        subj.setCreateBy(WebUtil.getCurrentUserId(token));
        subj.setCreateTime(new Date());
        subj.setApproveStatus("1");//审批状态 0:注册待审核;1:审核通过;2:注册审核不通过;3变更待审核;4:注销待审核;5:撤销待审核;6:撤销通过;7:已注销
        subj.setDelFlag("N");//N-未删除，Y-逻辑删除
        subj.setRegisterTime(new Date());
        subj.setEntityIdcode(subj.getAccount());
        subj.setRealName(subj.getLegalName());
        //某些字段非空 所预先存入默认值
        subj.setLegalImages("系统批量导入待修改");
        subj.setOrganizationCertificateimg("系统批量导入待修改");
        subj.setPositiveIdcardeimg("系统批量导入待修改");
        subj.setNegativeIdcardimg("系统批量导入待修改");
        subj.setHandIdcardimg("系统批量导入待修改");

        //生成主体身份码
        IdGenerator idGenerator = new IdGenerator();
        //主体组织形式
        Integer entityScale = 0;
        if ("".equals(subj.getCreditCode()) || null == subj.getCreditCode()) {
            //个人/家庭农场
            entityScale = 3;
        } else {
            //企业、合作社
            entityScale = 0;
        }
        //主体类型
        Map<String, Object> params = new HashedMap();
        params.put("id", subj.getEntityType());
        Map<String, Object> m = regiterProvider.queryDictValueById(params);
        Integer entityType = Integer.parseInt(m.get("DICTVALUE").toString());
        switch (entityScale) {
            //个人
            case 3:
                switch (entityType) {
                    //经营主体
                    case 1:
                        map.put("entityIdcode",sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode()));
                        map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainManagementBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode(),token==null|| token == ""?null:token));
                        break;
                    //生产经营主体主体
                    case 2:
                        map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode()));
                        map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode(),token==null|| token == ""?null:token));
                        break;
                    //生产主体+其他
                    default:
                        map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainWorkingBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode()));
                        map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainWorkingBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode(),token==null|| token == ""?null:token));
                        break;
                }
                break;
            default:
                switch (entityType) {
                    //经营主体
                    case 1:
                        map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode()));
                        map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainManagementBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode(),token==null|| token == ""?null:token));
                        break;
                    //生产经营主体主体
                    case 2:
                        map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode()));
                        map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode(),token==null|| token == ""?null:token));
                        break;
                    //生产主体+其他
                    default:
                        map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainWorkingBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode()));
                        map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainWorkingBody,subj.getCreditCode()==null||subj.getCreditCode()==""?subj.getLegalIdnumber():subj.getCreditCode(),token==null|| token == ""?null:token));
                        break;
                }
                break;
        }
        subj.setEntityIdcode(map.get("entityIdcode").toString());
        subj.setUserIdcode(map.get("userIdcode").toString());


        //主体组织形式
        SysDictData typeData = sysDictProvider.getDictDataById(subj.getEntityScale());
        String type = typeData.getDictValue();
        //新建一个list集合，存入行业类型的字典值(用于角色授权)
        List<String> industryTypes = new ArrayList<>();
        String indus = subj.getEntityIndustry();
        String[] ids = indus.split(",");
        for (int i = 0; i < ids.length; i++) {
            SysDictData industry = sysDictProvider.getDictDataById(ids[i]);
            String industryValue = industry.getDictValue().toString().trim();
            industryTypes.add(industryValue);
        }
        //此处用于判断是否勾选了屠宰场，如前端名称发生变化，请相应改变包含字符串的判断
        boolean choiceAbattoir = false;
        String Propertys = subj.getEntityPropertyName();
        if (Propertys.indexOf("屠宰场") != -1) {
            choiceAbattoir = true;
        }
        //主体注册，token为空
        String tokenTmep = "";
        SysUser sysUser = new SysUser();
        sysUser.setAccount(subj.getAccount());
        sysUser.setPassword(subj.getPassword());
        if (!"".equals(type) && null != type) {
            if ("0".equals(type)) {//企业类型
                sysUserProvider.addSubjUser(tokenTmep, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.ENTERPRISE);
            } else if ("1".equals(type)) {//合作社
                sysUserProvider.addSubjUser(tokenTmep, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.COOPERATIVE);
            } else if ("2".equals(type)) {//农场
                sysUserProvider.addSubjUser(tokenTmep, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.FARM);
            } else if ("3".equals(type)) {//个人
                sysUserProvider.addSubjUser(tokenTmep, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.PERSONAL);
            }
        }
        if (subj.getEntityScaleName().equals("企业/个体工商户")
                || subj.getEntityScaleName().equals("合作社")){
            subj.setInfoUnique(subj.getCreditCode());
        }
        if(subj.getEntityScaleName().equals("个人")
                || subj.getEntityScaleName().equals("家庭农场")){
            subj.setInfoUnique(subj.getLegalIdnumber());
        }
        int result = regiterProvider.insertSubj(subj);
        if (result > 0) {
            TtsScltxxcjUser ttsScltxxcjUser = new TtsScltxxcjUser();
            if (!"".equals(type) && null != type) {
                if ("3".equals(type)) {//个人
                    ttsScltxxcjUser.setName(subj.getRealName());
                    ttsScltxxcjUser.setPhone(subj.getContactPhone());
                    ttsScltxxcjUser.setEmail(subj.getContactEmail());
                } else {//非个人
                    ttsScltxxcjUser.setName(subj.getContactName());
                    ttsScltxxcjUser.setPhone(subj.getContactPhone());
                    ttsScltxxcjUser.setEmail(subj.getContactEmail());
                }
            }
            ttsScltxxcjUser.setId(UUID.randomUUID().toString().replace("-", ""));
            ttsScltxxcjUser.setAccount(subj.getAccount());
            ttsScltxxcjUser.setCreateTime(new Date());
            ttsScltxxcjUser.setUpdateTime(new Date());
            ttsScltxxcjUser.setIsMain("1");
            ttsScltxxcjUser.setDelFlag("N");//删除标志
            ttsScltxxcjUser.setEntityIdcode(subj.getEntityIdcode());
            ttsScltxxcjUser.setUserIdcode(subj.getUserIdcode());
            ttsScltxxcjUserProvider.insertUser(ttsScltxxcjUser);
        }
        return result;
    }
}
