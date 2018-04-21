package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AsmsSubjEntTemp;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUserChangeRecord;
import com.sofn.provider.asms.AsmsSubjEntProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import com.sofn.provider.tts.TtsScltxxcjUserChangeRecordProvider;
import com.sofn.util.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:17
 */
@Service
public class SubjEntService extends BaseService<AsmsSubjEntProvider,AsmsSubjEntTemp>{

    @DubboReference
    public void setProvider(AsmsSubjEntProvider provider){
        this.provider = provider;
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
    //生产经营主体--变更、注销、撤销的接口
    private TtsScltxxcjUserChangeRecordProvider changeRecordProvider;
    @DubboReference
    public void setChangeRecordProvider(TtsScltxxcjUserChangeRecordProvider changeRecordProvider){
        this.changeRecordProvider = changeRecordProvider;
    }

    public PageInfo<TtsScltxxcjRegiter> getSubjEntList(TtsScltxxcjRegiter entity,Page page) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        map.put("badRecord",entity.getBadRecord());
        map.put("entityIndustry",entity.getEntityIndustry());
        map.put("entityType",entity.getEntityType());
        map.put("entityScale",entity.getEntityScale());
        map.put("enterpriseName",entity.getEnterpriseName());
        map.put("entityIdcode",entity.getEntityIdcode());
        map.put("area",entity.getArea());
        if(entity.getApproveStatus()!=null && !"".equals(entity.getApproveStatus().trim())){
            map.put("approveStatus",entity.getApproveStatus().split(","));//可用的生产经营主体
        }
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
        map.put("entityIndustry",subjEntTemp.getEntityIndustry());
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

    public TtsScltxxcjRegiter findSubjEntById(TtsScltxxcjRegiter entity){
        return regiterProvider.selectByEntityId(entity.getId());
    }

    public TtsScltxxcjRegiter findSubjEntByEntityIdCode(TtsScltxxcjRegiter entity){
        Map queryParam = new HashMap();
        queryParam.put("entityIdCode",entity.getEntityIdcode());
        return regiterProvider.selectByEntityIdCode(queryParam);
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

    /**
     * 获取临时登记单位主体信息
     * @param subj
     * @return
     */
    /*public AsmsSubjEntTemp getSubjEntTempInfo(String subj){
        return provider.getSubjEntTempInfo(subj);
    }*/
}
