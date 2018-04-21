package com.sofn.service.sys;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysCodeRuleData;
import com.sofn.provider.sys.SysCodeRuleDataProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuye on 2016/12/15.
 */
@Service
public class SysCodeRuleDataService extends BaseService<SysCodeRuleDataProvider,SysCodeRuleData>{

    @DubboReference
    public void setProvider(SysCodeRuleDataProvider provider){this.provider = provider;}


    /**
     * 添加编码--码段值
     * @param sysCodeRuleData
     * @return
     */
    public int addSysCodeRuleData(SysCodeRuleData sysCodeRuleData){
        return provider.addSysCodeRuleData(sysCodeRuleData);
    }

    /**
     * 修改编码--码段值
     * @param sysCodeRuleData
     * @return
     */
    public int updateSysCodeRuleData(SysCodeRuleData sysCodeRuleData){
        return provider.updateSysCodeRuleData(sysCodeRuleData);
    }


    /**
     * 删除编码--码段值
     * @param id
     * @return
     */
    public int delSysCodeRuleData(String id){
        return provider.delSysCodeRuleData(id);
    }


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public SysCodeRuleData queryById(String id){
        return provider.queryById(id);
    }


    /**
     * 根据ruleID查询
     * @param ruleId
     * @return
     */
    public List<SysCodeRuleData> queryByRuleId(String ruleId){
        return provider.queryByRuleId(ruleId);
    }


    /**
     * 根据fieldId查询
     * @param fieldId
     * @return
     */
    public SysCodeRuleData queryByFieldId(String fieldId){
        return provider.queryByFieldId(fieldId);
    }
}
