package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysTemplate;

import java.util.List;
import java.util.Map;

/**
 * 模板管理模型对象
 * @author moon.l
 *
 */
public interface SysTemplateProvider extends BaseProvider<SysTemplate> {

	List<SysTemplate> getPageInfo(Map<String,Object> map);

	long getRecordsTotal(String templateName,String templateType,String flags,String status, String orgType,String regId,String orgLevel);

	int addTemplate(SysTemplate sysTemplate);

	int updateTemplate(SysTemplate sysTemplate);

	int deleteTemplate(String id);

	SysTemplate getTemplateByName(String name);

	int updateTempStatus(Map<String,Object> map);

	SysTemplate foundById(String tid);
}