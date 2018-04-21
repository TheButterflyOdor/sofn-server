package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.asms.AsmsSubjEnforceLawProvider;
import com.sofn.provider.sys.SysTestStandardProvider;
import com.sofn.provider.sys.SysUserProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlesToolsService extends BaseService {

    private SysUserProvider sysUserProvider;

    private AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    private AsmsSubjEnforceLawProvider asmsSubjEnforceLawProvider;

    private SysTestStandardProvider sysTestStandardProvider;

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsSubjDetectionProvider asmsSubjDetectionProvider) {
        this.asmsSubjDetectionProvider = asmsSubjDetectionProvider;
    }

    @DubboReference
    public void setProvider(AsmsSubjEnforceLawProvider asmsSubjEnforceLawProvider) {
        this.asmsSubjEnforceLawProvider = asmsSubjEnforceLawProvider;
    }

    @DubboReference
    public void setProvider(SysTestStandardProvider sysTestStandardProvider) {
        this.sysTestStandardProvider = sysTestStandardProvider;
    }

    /**
     * 用户列表
     */
    public PageInfo<SysUserBean> getUsersByToken(QueryParameter queryParameter, String keyword) {
        PageInfo<SysUserBean> pageInfo = new PageInfo<>();
        Organization organization = this.getOrganizationByToken(queryParameter.getToken());
        long total = sysUserProvider.getRecordsTotal(keyword, "1", organization.getId());
        Page page = new Page();
        page.setDraw(1);
        page.setStart((long) (queryParameter.getStart()));
        page.setLength((long) queryParameter.getLength());
        page.setRecordsTotal(total);
        page.doPage();
        pageInfo.setList(sysUserProvider.getSysUserList(page, keyword, "1", organization.getId()));
        pageInfo.setTotal(total);
        return pageInfo;
    }

    /**
     * 根据登录用户所属机构行政区划查询所有检测机构
     */
    public PageInfo getOrgs(AsmsSubjDetection subjDetection, com.sofn.util.Page page, String token) {
        Map<String, Object> queryMap = new HashMap<>();
        Organization organization = this.getOrganizationByToken(token);//用户所属机构
        String orgLevel = organization.getOrgLevel();
        String regionId = organization.getRegionId();
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
     * 根据登录用户所属机构行政区划查询所有执法机构
     */
    public PageInfo getZfOrgs(AsmsSubjEnforceLaw subjEnforceLaw, com.sofn.util.Page page, String token) {
        Map<String, Object> queryMap = new HashMap<>();
        Organization organization = this.getOrganizationByToken(token);//用户所属机构
        String orgLevel = organization.getOrgLevel();
        String regionId = organization.getRegionId();
        if ("2".equals(orgLevel)) {//省级
            queryMap.put("regParm", regionId.substring(0, 2) + "%");
        } else if ("3".equals(orgLevel)) {//市级
            queryMap.put("regParm", regionId.substring(0, 4) + "%");
        } else if ("4".equals(orgLevel)) {//县级
            queryMap.put("regParm", regionId + "%");
        } else if ("1".equals(orgLevel)) {//部级
            queryMap.put("regParm", null);
        }
        queryMap.put("elName", StringUtil.isNotBlank(subjEnforceLaw.getElName()) ? subjEnforceLaw.getElName() : null);
        queryMap.put("elAreaId", StringUtil.isNotBlank(subjEnforceLaw.getElAreaId()) ? subjEnforceLaw.getElAreaId() : null);
        queryMap.put("dateBegin", null);
        queryMap.put("dateEnd", null);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail", page.getPageTail());
        return asmsSubjEnforceLawProvider.getSubjEnforceLawList(queryMap);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

    public AsmsSubjEnforceLaw getTestingOrganizationInfo(String orgId) {
        return asmsSubjEnforceLawProvider.findSubjEnforceLawById(orgId);
    }

    public List<SysTestStandardModel> getTestStandard() {

        List<SysTestStandardModel>  SysTestStandardModels =sysTestStandardProvider.querylist();

        return  SysTestStandardModels;
    }
}
