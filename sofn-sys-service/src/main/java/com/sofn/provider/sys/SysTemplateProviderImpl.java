package com.sofn.provider.sys;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysTemplateMapper;
import com.sofn.dao.sys.SysTemplateExpandMapper;
import com.sofn.model.generator.SysTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	模板管理 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = SysTemplateProvider.class)
public class SysTemplateProviderImpl extends BaseProviderImpl<SysTemplate> implements SysTemplateProvider {

    @Autowired
    private SysTemplateExpandMapper SysTemplateExpandMapper;

    @Autowired
    private SysTemplateMapper sysTemplateMapper;

    public List<SysTemplate> getPageInfo(Map<String,Object> map) {
        return SysTemplateExpandMapper.getPageInfo(map);
    }

    @Override
    public long getRecordsTotal(String templateName, String templateType,String flags,String status, String orgType,String regId,String orgLevel) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("templateName",templateName);
        queryParams.put("templateType",templateType);
        queryParams.put("orgType",orgType);
        queryParams.put("regId",regId);
        queryParams.put("orgLevel",orgLevel);
        queryParams.put("flags",flags);
        queryParams.put("status", status);
        return SysTemplateExpandMapper.getCount(queryParams);
    }

    @Override
    public int addTemplate(SysTemplate sysTemplate) {
        return sysTemplateMapper.insert(sysTemplate);
    }

    @Override
    public int updateTemplate(SysTemplate sysTemplate) {
        return sysTemplateMapper.updateByPrimaryKey(sysTemplate);
    }

    @Override
    public int deleteTemplate(String id) {
        SysTemplate sysTemplate = sysTemplateMapper.selectByPrimaryKey(id);
        sysTemplate.setDelFlag("Y");
        return sysTemplateMapper.updateByPrimaryKey(sysTemplate);
    }

    @Override
    public SysTemplate getTemplateByName(String name) {
        return SysTemplateExpandMapper.getTemplateByName(name);
    }

    @Override
    public int updateTempStatus(Map<String,Object> map) {
        return SysTemplateExpandMapper.updateTempStatus(map);
    }

    /**
     * 农产品信息采集
     * @param tid
     * @return
     */
    @Override
    public SysTemplate foundById(String tid) {
        return SysTemplateExpandMapper.foundById("sofn-ncpxxcjb");
    }

}
