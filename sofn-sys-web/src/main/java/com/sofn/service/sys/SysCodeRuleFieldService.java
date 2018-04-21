package com.sofn.service.sys;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysCodeRuleField;
import com.sofn.provider.sys.SysCodeRuleFieldProvider;
import org.springframework.stereotype.Service;

/**
 * Created by wuye on 2016/12/15.
 */
@Service
public class SysCodeRuleFieldService extends BaseService<SysCodeRuleFieldProvider,SysCodeRuleField>{

    @DubboReference
    public void setProvider(SysCodeRuleFieldProvider provider){this.provider = provider;}

/*
    *//**
     * 添加编码--码段
     * @param sysCodeRuleField
     * @return
     *//*
    public int addSysCodeRuleField(SysCodeRuleField sysCodeRuleField){
        return provider.addSysCodeRuleField(sysCodeRuleField);
    }


    *//**
     * 修改编码--码段
     * @param sysCodeRuleField
     * @return
     *//*
    public int updateSysCodeRuleField(SysCodeRuleField sysCodeRuleField){
         return provider.updateSysCodeRuleField(sysCodeRuleField);
    }

    *//**
     * 删除编码--码段
     * @param id
     * @return
     *//*
    public int delSysCodeRuleField(String id){
        return provider.delSysCodeRuleField(id);
    }

    *//**
     * 根据ID查询
     * @param id
     * @return
     *//*
    public SysCodeRuleField queryById(String id){
        return provider.queryById(id);
    }


    *//**
     * 根据RULEID查询
     * @param ruleId
     * @return
     *//*
    public SysCodeRuleField queryByRuleId(String ruleId){
        return provider.queryByRuleId(ruleId);
    }*/
}
