package com.sofn.service.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.qry.TtsScltxxcjRegiterDto2;
import com.sofn.provider.qry.SubjectProvider;
import com.sofn.util.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月17日 下午 2:38
 */
@Service
public class SubjectService extends BaseService{

    private SubjectProvider provider;
    @DubboReference
    public void setProvider(SubjectProvider provider){
        this.provider = provider;
    }

    public PageInfo getSubjSuperviseList(String token, AsmsSubjSupervise subjSupervise, Page page, String areaId, String dateBegin, String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("svLevelId",subjSupervise.getSvLevelId());
        queryMap.put("currentOrgLevel",Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);//部省市县-对应1234
        queryMap.put("svName",subjSupervise.getSvName());
        queryMap.put("areaId",subjSupervise.getSvAreaId());
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("areaId",areaId);
        queryMap.put("dateBegin", dateBegin);
        queryMap.put("dateEnd", dateEnd);
        queryMap.put("status", subjSupervise.getStatus());//不同状态
        queryMap.put("svLeader", subjSupervise.getSvLeader());//机构负责人
        //机构级别处理
        String svLevelStr = subjSupervise.getSvLevel();
        if(StringUtils.isNotBlank(svLevelStr)){
            queryMap.put("svLevel", svLevelStr.split(","));
        }
        if(subjSupervise.getIndustryId()!=null&&!subjSupervise.getIndustryId().isEmpty()) {
            queryMap.put("industryId", subjSupervise.getIndustryId().split(","));//所属行业
        }
        return provider.getSubjSuperviseList(queryMap);
    }

    public PageInfo getSubjEnforceLawList(String token, AsmsSubjEnforceLaw subjEnforceLaw, Page page, String dateBegin, String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        if("ASMS".equals(WebUtil.getCurrentUser(token).getOrgType())) {
            queryMap.put("currentOrgLevel", Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);//部省市县-对应1234,监管高一级别
        }else{
            queryMap.put("currentOrgLevel", WebUtil.getCurrentUser(token).getOrgLevel());//部省市县-对应1234
        }
        queryMap.put("elName",subjEnforceLaw.getElName());
        queryMap.put("elAreaId",subjEnforceLaw.getElAreaId());
//        queryMap.put("elLevel",Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("dateBegin",dateBegin);
        queryMap.put("dateEnd",dateEnd);
        queryMap.put("status", subjEnforceLaw.getStatus());
        queryMap.put("elUnitNature", subjEnforceLaw.getElUnitNature());
        queryMap.put("elWorkBody", subjEnforceLaw.getElWorkBody());
        queryMap.put("elLeader", subjEnforceLaw.getElLeader());
        return provider.getSubjEnforceLawList(queryMap);
    }

    public PageInfo getSubjDetectionList(String token, AsmsSubjDetection subjDetection, Page page, String dateBegin, String dateEnd){
        Map<String,Object> queryMap = new HashMap<>();
        if("ASMS".equals(WebUtil.getCurrentUser(token).getOrgType())) {
            queryMap.put("currentOrgLevel", Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);//部省市县-对应1234,监管高一级别
        }else{
            queryMap.put("currentOrgLevel", WebUtil.getCurrentUser(token).getOrgLevel());//部省市县-对应1234
        }
        queryMap.put("dtName",subjDetection.getDtName());
//        queryMap.put("dtLevel",Integer.valueOf(WebUtil.getCurrentUser(token).getOrgLevel())-1);
        queryMap.put("areaId",subjDetection.getDtAreaId());
        queryMap.put("dateBegin",dateBegin);
        queryMap.put("dateEnd",dateEnd);
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("status", subjDetection.getStatus());
        //机构性质
        String dtNatureId = subjDetection.getDtNatureId();
        if(StringUtils.isNotBlank(dtNatureId)){
            queryMap.put("dtNatureId", dtNatureId.split(","));
        }
        //资质证书
        String dtQualificationsId = subjDetection.getDtQualificationsId();
        if(StringUtils.isNotBlank(dtQualificationsId)){
            queryMap.put("dtQualificationsId", dtQualificationsId.split(","));
        }
        //负责人
        queryMap.put("dtLeader", subjDetection.getDtLeader());
        return provider.getSubjDetectionList(queryMap);
    }

    public PageInfo<TtsScltxxcjRegiterDto2> getSubjEntList(TtsScltxxcjRegiterDto2 entity, Page page, String dateBegin,String dateEnd) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        map.put("badRecord",entity.getBadRecord());
//        map.put("entityIndustry",entity.getEntityIndustry());
        if(entity.getEntityIndustry()!=null&&!"".equals(entity.getEntityIndustry().trim())){
            map.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        if(entity.getEntityType()!=null&&!"".equals(entity.getEntityType().trim())){
            map.put("entityType",entity.getEntityType().split(","));
        }
        if(entity.getEntityScale()!=null&&!"".equals(entity.getEntityScale().trim())){
            map.put("entityScale",entity.getEntityScale().split(","));
        }
//        map.put("entityType",entity.getEntityType());
//        map.put("entityScale",entity.getEntityScale());
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("entityIdcode",entity.getEntityIdcode());
        map.put("area",entity.getArea());
        //主体状态
        if(StringUtils.isNotBlank(entity.getApproveStatus())){
            map.put("approveStatus",entity.getApproveStatus().split(","));
        }
        //主体属性
        if(StringUtils.isNotBlank(entity.getEntityProperty())){
            map.put("entityProperty",entity.getEntityProperty().split(","));
        }
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        map.put("legalName",entity.getLegalName());
        List list = provider.getSubjEntList(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(provider.getSubjEntTotal(map));
        return pageInfo;
    }

    public PageInfo<TtsScltxxcjUserChangeRecord> getChangeListByEntId(String entityId,long start,long length){
        Map<String,Object> map = new HashMap();
        map.put("pageNum",start/length+1);
        map.put("pageSize",length);
        map.put("entityId",entityId);
        return provider.getPageInfo(map);
    }

    public PageInfo getAsmsSubjEntBadrecordByIdList(Page page, String enterpriseId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("enterpriseId",enterpriseId);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        return provider.getAsmsSubjEntBadrecordByIdList(queryMap);
    }

    public AsmsSubjSupervise findSubjSuperviseById(String id){
        return provider.findSubjSuperviseById(id);
    }

    public AsmsSubjEnforceLaw findSubjEnforceLawById(String id){
        return provider.findSubjEnforceLawById(id);
    }

    public AsmsSubjDetection findSubjDetectionById(String id){
        return provider.findSubjDetectionById(id);
    }
    public TtsScltxxcjRegiterDto2 findSubjEntById(TtsScltxxcjRegiterDto2 entity){
        return provider.findSubjEntById(entity.getId());
    }

    public TtsScltxxcjRegiterDto2 findSubjEntByEntityIdCode(TtsScltxxcjRegiterDto2 entity){
        Map queryParam = new HashMap();
        queryParam.put("entityIdCode",entity.getEntityIdcode());
        return provider.findSubjEntByEntityIdCode(queryParam);
    }

    public AsmsSubjEntTemp findSubjEntTempById(String id){
        return provider.findSubjEntTempById(id);
    }

    public PageInfo getChangeListBySvId(Page page,String id){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("svId", id);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return provider.getChangeListBySvId(queryMap);
    }

    public PageInfo getChangeListByElId(Page page,String id){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("elId", id);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return provider.getChangeListByElId(queryMap);
    }

    public PageInfo getChangeListByDtId(Page page,String id){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("dtId", id);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return provider.getChangeListByDtId(queryMap);
    }

    public AsmsSubjSvChange findSubjSvChangeById(String id){
        return provider.findSubjSvChangeById(id);
    }

    public AsmsSubjElChange findSubjElChangeById(String id){
        return provider.findSubjElChangeById(id);
    }

    public AsmsSubjDtChange findSubjDtChangeById(String id){
        return provider.findSubjDtChangeById(id);
    }

    public PageInfo getProductListByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        return provider.getProductListByEntityIdcode(queryMap);
    }

    public PageInfo getSubjEntBaseByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        return provider.getSubjEntBaseByEntityIdcode(queryMap);
    }
    public PageInfo getBathByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        queryMap.put("noBillStratrus","2");
        return provider.getBathByEntityIdcode(queryMap);
    }
    public PageInfo getHcBathById(Page page,String hcid){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("hcid",hcid);
        return provider.getHcBathById(queryMap);
    }
    public PageInfo getSaleByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        return provider.getSaleByEntityIdcode(queryMap);
    }

    public TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id){
        TtsScltxxcjXsdjAndCustomer vo = provider.getXsdjAndCustomerById(id);
        return vo;
    }

    public TtsScltxxcjXsthAndCustomer queryByXsjlId(String id) {
        return provider.queryByXsjlId(id);
    }

    public PageInfo<TtsScltxxcjXsdjAndCustomer> getSubjCgglListByEntityIdcode(Page page,String entityIdcode, String isCirculatesEnd,String confirmState){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        queryMap.put("isCirculatesEnd",isCirculatesEnd);
        queryMap.put("confirmState",confirmState);
        return provider.getSubjCgglListByEntityIdcode(queryMap);
    }

    public PageInfo getSalePrintListByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        return provider.getSalePrintListByEntityIdcode(queryMap);
    }

    public PageInfo getStockPrintListByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        return provider.getStockPrintListByEntityIdcode(queryMap);
    }

    public PageInfo getSlaughterListByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode);
        return provider.getSlaughterListByEntityIdcode(queryMap);
    }

    public PageInfo getSubjEntAndBathList(Page page,String enterpriseName, String area){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("enterpriseName",enterpriseName==null?"":enterpriseName.trim());
        queryMap.put("area",area==null?"":area.trim());
        return provider.getSubjEntAndBathList(queryMap);
    }

    public PageInfo getSubjEntAndTransactionInfoList(Page page,String enterpriseName, String area){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("enterpriseName",enterpriseName==null?"":enterpriseName.trim());
        queryMap.put("area",area==null?"":area.trim());
        return provider.getSubjEntAndTransactionInfoList(queryMap);
    }

    public PageInfo getTransactionInfoListByEntityIdcode(Page page,String entityIdcode){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("entityIdcode",entityIdcode==null?"":entityIdcode.trim());
        return provider.getTransactionInfoListByEntityIdcode(queryMap);
    }

    public PageInfo getSubjEntAndPrintInfoList(Page page, String area, String enterpriseName, String productName,
                                               String  productSort, String productScglId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("pageOffset",page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        queryMap.put("area",area==null?"":area.trim());//所在区域区域
        queryMap.put("enterpriseName",enterpriseName==null?"":enterpriseName.trim());//主体名称
        queryMap.put("productName",productName==null?"":productName.trim());//产品名称
        queryMap.put("productSort",productSort==null?"":productSort.trim());//产品种类
        queryMap.put("productScglId",productScglId==null?"":productScglId.trim());//追溯码

        return provider.getSubjEntAndPrintInfoList(queryMap);
    }

}
