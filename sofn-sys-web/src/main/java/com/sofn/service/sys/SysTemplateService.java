package com.sofn.service.sys;

import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysTemplate;
import com.sofn.provider.sys.SysTemplateProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 模板管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class SysTemplateService extends BaseService<SysTemplateProvider, SysTemplate> {

    @DubboReference
    public void setSysTemplateProvider(SysTemplateProvider provider) {
        this.provider = provider;
    }

    public List<SysTemplate> getPageInfo(Page pager, String templateName, String templateType,String flags, String status, String orgType,String regId,String orgLevel) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pager",pager);
        map.put("templateName",templateName);
        map.put("templateType",templateType);
        map.put("orgType",orgType);
        map.put("regId",regId);
        map.put("orgLevel",orgLevel);
        map.put("flags",flags);
        map.put("status", status);

        return provider.getPageInfo(map);
    }

    public long getRecordsTotal(String templateName,String templateType,String flags,String status, String orgType,String regId,String orgLevel){
        return provider.getRecordsTotal(templateName,templateType,flags,status, orgType, regId,orgLevel);
    }

    @Transactional(rollbackFor=Exception.class)
    public int addTemplate(SysTemplate sysTemplate){
        sysTemplate.setStatus("Y");
        sysTemplate.setDelFlag("N");
        sysTemplate.setCreateTime(new Date());
        sysTemplate.setUpdateTime(new Date());
        sysTemplate.setId(UUID.randomUUID().toString());
        sysTemplate.setCreateBy(WebUtil.getCurrentUserId());
        sysTemplate.setUpdateBy(WebUtil.getCurrentUserId());
        return provider.addTemplate(sysTemplate);
    }
    /*public int addTemplateConfirm(TransactionContext transactionContext, SysTemplate sysTemplate){
        System.out.println("================addTemplateConfirm=======================s");
        return 10;
    }
    public int addTemplateCancel(TransactionContext transactionContext, SysTemplate sysTemplate){
        System.out.println("================addTemplateCancel=======================s");
        return 12;
    }*/
    public int updateTemplate(SysTemplate sysTemplate){
        sysTemplate.setUpdateBy(WebUtil.getCurrentUserId());
        sysTemplate.setUpdateTime(new Date());
        return provider.updateTemplate(sysTemplate);
    }

    public int deleteTemplate(String id){
        return provider.deleteTemplate(id);
    }

    /**
     * 通过附件名称获取附件相关信息
     * @param name
     * @return
     */
    public SysTemplate getTemplateByName(String name){
        return provider.getTemplateByName(name) ;
    }
    public int updateTempStatus(Map<String,Object> map){
        return provider.updateTempStatus(map) ;
    }
}