package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.Organization;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsRoutineMonitor;
import com.sofn.model.generator.AsmsSpecialMonitor;
import com.sofn.provider.asms.AsmsRoutineMonitorProvider;
import com.sofn.provider.asms.AsmsSpecialMonitorProvider;
import com.sofn.provider.sys.SysUserProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangran
 * @version 2016年11月18日
 */
@Service
public class AdsRoutineMonitorService extends BaseService<AsmsRoutineMonitorProvider, AsmsRoutineMonitor> {

    private SysUserProvider sysUserProvider;

    private AsmsSpecialMonitorProvider asmsSpecialMonitorProvider;

    @DubboReference
    public void setProvider(SysUserProvider sysUserProvider) {
        this.sysUserProvider = sysUserProvider;
    }

    @DubboReference
    public void setProvider(AsmsSpecialMonitorProvider asmsSpecialMonitorProvider) {
        this.asmsSpecialMonitorProvider = asmsSpecialMonitorProvider;
    }

    @DubboReference
    public void setProvider(AsmsRoutineMonitorProvider provider) {
        this.provider = provider;
    }

    /**
     * 查询监管下发例行监测列表
     * @param r
     * @param dateBegin
     * @param dateEnd
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<List<Map<String, Object>>> list(AsmsRoutineMonitor r,QueryParameter p, String dateBegin, String dateEnd, int pageNum, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        CurrentUser token =WebUtil.getCurrentUser(p.getToken());
        params.put("leadUnitId", StringUtil.isNotBlank(token.getOrgId()) ? token.getOrgId() : null);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        //query
        params.put("dateBegin", StringUtil.isNotBlank(dateBegin) ? dateBegin : null);
        params.put("dateEnd", StringUtil.isNotBlank(dateEnd) ? dateEnd : null);
        params.put("rmName", StringUtil.isNotBlank(r.getRmName()) ? "%"+r.getRmName()+"%" : null);
        params.put("rmState", StringUtil.isNotBlank(r.getRmState()) ? r.getRmState() : null);
        PageInfo<List<Map<String, Object>>> i = provider.getTaskListByADS(params);
        return i;
    }

    /**
     * 查询监管下发专项监测列表
     * @param r
     * @param p
     * @return
     */
    public PageInfo<List<Map<String, Object>>> getSpecialList(AsmsSpecialMonitor r, QueryParameter p) {
        Map<String, Object> params = new HashMap<>();

        CurrentUser token =WebUtil.getCurrentUser(p.getToken());
        params.put("leadUnitId", StringUtil.isNotBlank(token.getOrgId()) ? token.getOrgId() : null);
        /*Organization org = this.getOrganizationByToken(p.getToken());
        //根据机构行政区划
        String orgLevel = org.getOrgLevel();
        String regionId = org.getRegionId();
        if ("2".equals(orgLevel)){//省级
            params.put("regParm",regionId.substring(0,2)+"%");
        }else if("3".equals(orgLevel)){//市级
            params.put("regParm",regionId.substring(0,4)+"%");
        }else if("4".equals(orgLevel)){//县级
            params.put("regParm",regionId+"%");
        }else if("1".equals(orgLevel)){//部级
            params.put("regParm",null);
        }*/
        //page
        params.put("pageNum", ((p.getStart() + 1) / p.getLength()) + 1);
        params.put("pageSize", p.getLength());
        //query
        params.put("dateBegin", StringUtil.isNotBlank(p.getDateBegin()) ? p.getDateBegin() : null);
        params.put("dateEnd", StringUtil.isNotBlank(p.getDateEnd()) ? p.getDateEnd() : null);
        //
        params.put("smName", StringUtil.isNotBlank(r.getSmName()) ? "%"+r.getSmName()+"%" : null);
        //params.put("smState", StringUtil.isNotBlank(r.getSmState()) ? r.getSmState() : null);
        //params.put("smYear", StringUtil.isNotBlank(r.getSmYear()) ? r.getSmYear() : null);
        PageInfo<List<Map<String, Object>>> i = asmsSpecialMonitorProvider.getTaskListByADS(params);
        return i;
    }

    /**
     * 根据用户token获取organization
     * */
    public Organization getOrganizationByToken(String token){
        return sysUserProvider.findSysUserOrganization(token);
    }

}
