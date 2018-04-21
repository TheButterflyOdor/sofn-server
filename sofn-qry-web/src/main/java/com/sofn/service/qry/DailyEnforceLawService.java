package com.sofn.service.qry;


import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.qry.AlesDailyEnforceLawDto;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.qry.AsmsSubjDetectionProvider;
import com.sofn.provider.qry.DailyEnforceLawProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.util.Page;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.*;

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

    private SysDictProvider sysDictProvider;
    @DubboReference
    public void setSysDictProvider(SysDictProvider sysDictProvider){
        this.sysDictProvider = sysDictProvider;
    }

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider asmsSubjDetectionProvider) {
        this.asmsSubjDetectionProvider = asmsSubjDetectionProvider;
    }

    public PageInfo getDailyEnforceLawList(TtsScltxxcjRegiter entity, String areaId, String taskYear, String dateBegin, String dateEnd,
                                           Page page, String queryCon, String enforceLawResult) {
        Map<String, Object> queryParams = new HashMap<>();
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            queryParams.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //主体类型
        if (entity.getEntityType()!=null && !"".equals(entity.getEntityType().trim())) {
            queryParams.put("entityType",getBothEntityType(entity.getEntityType().split(",")));
        }
        queryParams.put("areaId", areaId);
        queryParams.put("taskYear", taskYear);
        queryParams.put("dateBegin", dateBegin);
        queryParams.put("dateEnd", dateEnd);
        //queryParams.put("pageOffset",page.getPageOffset());
        //queryParams.put("pageTail",page.getPageTail());
        queryParams.put("pageNum",((page.getStart()+1)/page.getLength())+1);
        queryParams.put("pageSize",page.getLength());
        queryParams.put("queryCon", queryCon);
        queryParams.put("enforceLawResult", enforceLawResult);
        return provider.getDailyEnforceLawList(queryParams);
    }

    /**
     * 根据条件查询所有列表（不分页）
     */
    public List<AlesDailyEnforceLawDto> getAllDailyEnforceLawList(TtsScltxxcjRegiter entity, String areaId, String taskYear, String dateBegin, String dateEnd,
                                                                  String queryCon,String enforceLawResult){
        Map<String, Object> queryParams = new HashMap<>();
        //所属行业
        if (entity.getEntityIndustry()!=null && !"".equals(entity.getEntityIndustry().trim())) {
            queryParams.put("entityIndustry",entity.getEntityIndustry().split(","));
        }
        //主体类型
        if (entity.getEntityType()!=null && !"".equals(entity.getEntityType().trim())) {
            queryParams.put("entityType",entity.getEntityType().split(","));
        }
        queryParams.put("areaId", areaId);
        queryParams.put("taskYear", taskYear);
        queryParams.put("dateBegin", dateBegin);
        queryParams.put("dateEnd", dateEnd);
        queryParams.put("queryCon", queryCon);
        queryParams.put("enforceLawResult", enforceLawResult);
        return provider.getAllDailyEnforceLawList(queryParams);
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
    public List<SysUserBean> getUserList(com.sofn.core.persistence.Page page,String keyword, String token) {
        Organization o = this.getOrganizationByToken(token);
        if (o != null) {
            return sysUserProvider.getSysUserList(page, keyword, "1", o.getId());
        } else {
            return null;
        }
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

    //根据临时主体id获取全部产业id,由于页面使用临时主体产业字典与主体字典id不同
    public Object[] getBothEntityType(String[] entityType){
        List<String> entityTypeName = new ArrayList<>();
        List<String> entityTypeId = new ArrayList<>(Arrays.asList(entityType));
        List<SysDictData> entityTypeTempList = sysDictProvider.getDictByType(DictType.TEMPTYPE);
        List<SysDictData> entityTypeList = sysDictProvider.getDictByType(DictType.SUBJTYPE);
        for (int i = 0;i < entityTypeTempList.size();i++){
            for (int j = 0;j < entityType.length;j++){
                if(entityType[j].equals(entityTypeTempList.get(i).getId())){
                    entityTypeName.add(entityTypeTempList.get(i).getDictName());
                }
            }
        }
        for (int i = 0;i < entityTypeName.size();i++){
            for (int j = 0;j< entityTypeList.size();j++){
                if(entityTypeName.get(i).equals(entityTypeList.get(j).getDictName())){
                    entityTypeId.add(entityTypeList.get(j).getId());
                }
            }
        }
        return entityTypeId.toArray();
    }
}
