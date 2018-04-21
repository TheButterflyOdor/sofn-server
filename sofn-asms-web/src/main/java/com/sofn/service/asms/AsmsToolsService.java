package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.Organization;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.asms.AsmsSubjDetectionProvider;
import com.sofn.provider.asms.AsmsSubjEnforceLawProvider;
import com.sofn.provider.sys.SysUserProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsToolsService extends BaseService {

    private SysUserProvider sysUserProvider;

    private AsmsSubjDetectionProvider asmsSubjDetectionProvider;

    private AsmsSubjEnforceLawProvider asmsSubjEnforceLawProvider;

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

    /**
     * 用户列表
     */
    public PageInfo<SysUserBean> getUsersByToken(String token, QueryParameter queryParameter, String keyword) {
        PageInfo<SysUserBean> pageInfo = new PageInfo<>();
        Organization organization = this.getOrganizationByToken(token);
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
        Map<String, Object> map = new HashMap<>();
        Organization organization = this.getOrganizationByToken(token);//用户所属机构
        String orgLevel = organization.getOrgLevel();
        String regionId = organization.getRegionId();
        map.putAll(AsmsServiceBoth.buidRegParm(orgLevel, regionId));
        map.put("dtName", StringUtil.isNotBlank(subjDetection.getDtName()) ? subjDetection.getDtName() : null);
        map.put("areaId", StringUtil.isNotBlank(subjDetection.getDtAreaId()) ? subjDetection.getDtAreaId() : null);
        map.put("dateBegin", null);
        map.put("dateEnd", null);
        map.put("pageOffset", page.getPageOffset());
        map.put("pageTail", page.getPageTail());
        return asmsSubjDetectionProvider.getSubjDetectionList(map);
    }

    /**
     * 根据登录用户所属机构行政区划查询所有执法机构
     */
    public PageInfo getZfOrgs(AsmsSubjEnforceLaw subjEnforceLaw, com.sofn.util.Page page, String token) {
        Map<String, Object> map = new HashMap<>();
        Organization organization = this.getOrganizationByToken(token);
        String orgLevel = organization.getOrgLevel();
        String regionId = organization.getRegionId();
        map.putAll(AsmsServiceBoth.buidRegParm(orgLevel, regionId));
        map.put("elName", StringUtil.isNotBlank(subjEnforceLaw.getElName()) ? subjEnforceLaw.getElName() : null);
        map.put("elAreaId", StringUtil.isNotBlank(subjEnforceLaw.getElAreaId()) ? subjEnforceLaw.getElAreaId() : null);
        map.put("dateBegin", null);
        map.put("dateEnd", null);
        map.put("pageOffset", page.getPageOffset());
        map.put("pageTail", page.getPageTail());
        return asmsSubjEnforceLawProvider.getSubjEnforceLawList(map);
    }

    /**
     * 根据用户token获取organization
     */
    public Organization getOrganizationByToken(String token) {
        return sysUserProvider.findSysUserOrganization(token);
    }

}
