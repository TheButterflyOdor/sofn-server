package com.sofn.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleData;
import com.sofn.model.generator.SysCodeRuleField;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.sys.SysCodeRuleDataProvider;
import com.sofn.provider.sys.SysCodeRuleFieldProvider;
import com.sofn.provider.sys.SysCodeRuleProvider;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/10/18.
 */
@Service
public class SysCodeRuleService extends BaseService<SysCodeRuleProvider,SysCodeRule> {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SysCodeRuleService.class);
//    private SysCodeRuleProvider sysCodeRuleProvider;
    private SysCodeRuleFieldProvider sysCodeRuleFieldProvider;
    private SysCodeRuleDataProvider sysCodeRuleDataProvider;

    @DubboReference
    public void setSysCodeRuleProvider(SysCodeRuleProvider provider) {
        this.provider = provider;
    }
    @DubboReference
    public void setSysCodeRuleFieldProvider(SysCodeRuleFieldProvider sysCodeRuleFieldProvider){
        this.sysCodeRuleFieldProvider = sysCodeRuleFieldProvider;
    }

    @DubboReference
    public void setSysCodeRuleDataProvider(SysCodeRuleDataProvider sysCodeRuleDataProvider){
        this.sysCodeRuleDataProvider = sysCodeRuleDataProvider;
    }



//    @Autowired
//    private SysCodeRuleFieldService sysCodeRuleFieldService;
//
//    @Autowired
//    private SysCodeRuleDataService sysCodeRuleDataService;


    public int getRecordsTotal(){
        return provider.getRecordsTotal();
    }
    public List<SysCodeRule> getPageList(Page page){
        return provider.getSysCodeList(page);
    }
    public Map queryCodeRuleById(String codeRuleId){
        SysCodeRule rule=super.queryById(codeRuleId);
        List<SysCodeRuleField> sysCodeRuleFields=sysCodeRuleFieldProvider.queryByRuleId(codeRuleId);
        List<SysCodeRuleData> sysCodeRuleDatas=sysCodeRuleDataProvider.queryByRuleId(codeRuleId);
        Map<String ,Object> map=new HashMap<String,Object>();
        map.put("rule",rule);
        map.put("ruleFields",sysCodeRuleFields);
        map.put("ruleDatas",sysCodeRuleDatas);
        return map;
    }
    /**
     * 添加编码规则 JSON格式
     * @return
     */
    public String saveSysCodeRule(JSONObject jsonObject,String uid) throws ParseException {
        String rule_cole = "";
        String rule_name = (String)jsonObject.get("rule_name");
        //编码规则
        SysCodeRule sysCodeRule  = new SysCodeRule();
        sysCodeRule.setRuleName(rule_name);
        Date updateTime=new Date();
//        sysCodeRule.setId(StringUtils.getUUIDString());
        sysCodeRule.setDelFlag("N");
        //判断是否有id
        if(jsonObject.containsKey("id")){
            sysCodeRule.setId(jsonObject.get("id").toString());
        }
        //判断是否有创建时间
        if(jsonObject.containsKey("create_time")) {
            sysCodeRule.setCreateTime(sdf.parse(jsonObject.get("create_time").toString()));
            sysCodeRule.setCreateBy(jsonObject.get("create_by").toString());
        }
        else {
            sysCodeRule.setCreateTime(updateTime);
            sysCodeRule.setCreateBy(uid);
            jsonObject.put("create_time",sdf.format(updateTime));
            jsonObject.put("create_by",uid);
        }
        sysCodeRule.setUpdateTime(updateTime);
        sysCodeRule.setUpdateBy(uid);
//        provider.addSysCodeRule(sysCodeRule);
        boolean isNew=false;
        if(sysCodeRule.getId()==null||sysCodeRule.getId().trim().equals("")){
            isNew=true;
            sysCodeRule.setId(StringUtils.getUUIDString());
        }
//        sysCodeRule=provider.update(sysCodeRule);
        //设置json里边的id
        jsonObject.put("id",sysCodeRule.getId());

        List<Map<String,Object>> rules = (List<Map<String,Object>>) jsonObject.get("rules");
//        List<SysCodeRuleField> sysCodeRuleFieldList = new ArrayList<SysCodeRuleField>();
//        List<SysCodeRuleData> sysCodeRuleDataList = new ArrayList<SysCodeRuleData>();
        //删除原有数据
        sysCodeRuleFieldProvider.deleteByRuleId(sysCodeRule.getId());
        sysCodeRuleDataProvider.deleteByRuleId(sysCodeRule.getId());
        for(int i=0;i<rules.size();i++){
            String field_name = rules.get(i).get("name").toString();
            //编码规则--码段
            SysCodeRuleField sysCodeRuleField = new SysCodeRuleField();
            sysCodeRuleField.setFieldName(field_name);
            sysCodeRuleField.setSortId(String.valueOf(i+1));
            sysCodeRuleField.setRuleId(sysCodeRule.getId());
            sysCodeRuleField.setDelFlag("N");
//            sysCodeRuleField.setCreateBy(uid);
//                sysCodeRule.setCreateTime(updateTime);

            //判断是否有id
            if(rules.get(i).containsKey("id")){
                sysCodeRuleField.setId(rules.get(i).get("id").toString());
            }
            //判断是否有创建时间
            if(rules.get(i).containsKey("create_time")) {
                sysCodeRuleField.setCreateTime(sdf.parse(rules.get(i).get("create_time").toString()));
                sysCodeRuleField.setCreateBy(rules.get(i).get("create_by").toString());
            }
            else {
                sysCodeRuleField.setCreateTime(updateTime);
                sysCodeRuleField.setCreateBy(uid);
                rules.get(i).put("create_time",sdf.format(updateTime));
                rules.get(i).put("create_by",uid);
            }
            sysCodeRuleField.setCreateTime(updateTime);
            sysCodeRuleField.setUpdateTime(updateTime);
            sysCodeRuleField.setUpdateBy(uid);
            if (sysCodeRuleField.getFieldName().equals("农业行业代码")) {
                sysCodeRuleField.setFixed("Y");
                sysCodeRuleField.setGroupMode("or");
            }
            if (sysCodeRuleField.getFieldName().equals("平台级别")) {
                sysCodeRuleField.setFixed("Y");
                sysCodeRuleField.setGroupMode("or");
            }
            sysCodeRuleField=sysCodeRuleFieldProvider.insert(sysCodeRuleField);
            //设置json里边的id
            rules.get(i).put("id",sysCodeRuleField.getId());

            List<Map<String,String>> maplist = (List<Map<String, String>>) rules.get(i).get("children");
            //删除原有数据
//            sysCodeRuleDataProvider.deleteByFieldId(sysCodeRuleField.getId());
            for(int j = 0;j<maplist.size();j++){
                Map<String,String> map = maplist.get(j);
                //编码规则--码段值
                SysCodeRuleData sysCodeRuleData = new SysCodeRuleData();
                sysCodeRuleData.setFieldName(field_name);
                sysCodeRuleData.setSortId(String.valueOf(j+1));
                sysCodeRuleData.setItemName(map.get("item_val"));
                String num=map.get("item_num").toString();
                sysCodeRuleData.setItemVal(num);
                rule_cole += num;
//                sysCodeRuleDataList.add(sysCodeRuleData);

                sysCodeRuleData.setRuleId(sysCodeRule.getId());
//            sysCodeRuleData.setFieldName(sysCodeRuleField.getFieldName());
                sysCodeRuleData.setFieldId(sysCodeRuleField.getId());
//            sysCodeRuleData.setId(StringUtils.getUUIDString());
                sysCodeRuleData.setDelFlag("N");
//                sysCodeRuleData.setCreateBy(uid);
//                sysCodeRuleData.setCreateTime(updateTime);

                //判断是否有id
                if(map.containsKey("id")){
                    sysCodeRuleData.setId(map.get("id").toString());
                }
                //判断是否有创建时间
                if(map.containsKey("create_time")) {
                    sysCodeRuleData.setCreateTime(sdf.parse(map.get("create_time").toString()));
                    sysCodeRuleData.setCreateBy(map.get("create_by").toString());
                }
                else {
                    sysCodeRuleData.setCreateTime(updateTime);
                    sysCodeRuleData.setCreateBy(uid);
                    map.put("create_time",sdf.format(updateTime));
                    map.put("create_by",uid);
                }
                sysCodeRuleData.setUpdateTime(updateTime);
                sysCodeRuleData.setUpdateBy(uid);
//            sysCodeRuleDataProvider.addSysCodeRuleData(sysCodeRuleData);
                sysCodeRuleData=sysCodeRuleDataProvider.insert(sysCodeRuleData);
                //设置json里边的id
                map.put("id",sysCodeRuleData.getId());
            }
        }
        //检查json是否同步设置了ID
        sysCodeRule.setRuleCode(rule_cole);
        sysCodeRule.setReservedField1(jsonObject.toJSONString());
        if(isNew){
            provider.insert(sysCodeRule);
        }else
            sysCodeRule=provider.update(sysCodeRule);
        //需要删除不在此id列表中的数据
        return sysCodeRule.getId();

    }
        /**
         * 添加编码规则
         * @param sysCodeRule
         * @param sysCodeRuleFields
         * @param sysCodeRuleDatas
         * @param opUId
         * @return
         */
    public int saveSysCodeRule(SysCodeRule sysCodeRule, List<SysCodeRuleField> sysCodeRuleFields, List<SysCodeRuleData> sysCodeRuleDatas,String opUId) {
        int result = 1;
        Date updateTime=new Date();
//        sysCodeRule.setId(StringUtils.getUUIDString());
        sysCodeRule.setDelFlag("N");
        sysCodeRule.setCreateBy(opUId);
        sysCodeRule.setCreateTime(updateTime);
        sysCodeRule.setUpdateTime(updateTime);
        sysCodeRule.setUpdateBy(opUId);
//        provider.addSysCodeRule(sysCodeRule);
        sysCodeRule=provider.update(sysCodeRule);
        //
        SysCodeRuleField sysCodeRuleField = new SysCodeRuleField();
        for (int i = 0; i < sysCodeRuleFields.size(); i++) {
            sysCodeRuleField = sysCodeRuleFields.get(i);
//            sysCodeRuleField.setId(StringUtils.getUUIDString());
            sysCodeRuleField.setRuleId(sysCodeRule.getId());
            sysCodeRuleField.setDelFlag("N");
            sysCodeRuleField.setCreateBy(opUId);
            sysCodeRuleField.setCreateTime(updateTime);
            sysCodeRuleField.setUpdateTime(updateTime);
            sysCodeRuleField.setUpdateBy(opUId);
            if (sysCodeRuleField.getFieldName().equals("农业行业代码")) {
                sysCodeRuleField.setFixed("Y");
                sysCodeRuleField.setGroupMode("or");
            }
            if (sysCodeRuleField.getFieldName().equals("平台级别")) {
                sysCodeRuleField.setFixed("Y");
                sysCodeRuleField.setGroupMode("or");
            }
//            sysCodeRuleFieldProvider.addSysCodeRuleField(sysCodeRuleField);
            try{
//                sysCodeRuleFieldProvider.addSysCodeRuleField(sysCodeRuleField);
                sysCodeRuleFieldProvider.update(sysCodeRuleField);
            }catch (Exception ex){
                LOG.error("编码规则添加异常error",ex);
                LOG.debug("编码规则添加测试debug",ex);
                LOG.info("编码规则添加信息info",ex);
                LOG.warn("编码规则warn",ex);
            }

        }
        //删除原有数据
        SysCodeRuleData sysCodeRuleData = new SysCodeRuleData();
        for (int j = 0; j < sysCodeRuleDatas.size(); j++) {
            sysCodeRuleData = sysCodeRuleDatas.get(j);
            sysCodeRuleData.setRuleId(sysCodeRule.getId());
//            sysCodeRuleData.setFieldName(sysCodeRuleField.getFieldName());
            sysCodeRuleData.setFieldId(sysCodeRuleField.getId());
//            sysCodeRuleData.setId(StringUtils.getUUIDString());
            sysCodeRuleData.setDelFlag("N");
            sysCodeRuleData.setCreateBy(opUId);
            sysCodeRuleData.setCreateTime(updateTime);
            sysCodeRuleData.setUpdateTime(updateTime);
            sysCodeRuleData.setUpdateBy(opUId);
//            sysCodeRuleDataProvider.addSysCodeRuleData(sysCodeRuleData);
            sysCodeRuleDataProvider.update(sysCodeRuleData);
        }
        return result;
    }

/*
    *//**
     * 修改编码规则
     * @param sysCodeRule
     * @param sysCodeRuleField
     * @param sysCodeRuleData
     * @return
     *//*
    @Transactional
    public int modifySysCodeRule(SysCodeRule sysCodeRule, SysCodeRuleField sysCodeRuleField, SysCodeRuleData sysCodeRuleData){
        int result = 0;
        //
        sysCodeRule.setUpdateBy(WebUtil.getCurrentUserId());
        sysCodeRule.setUpdateTime(new Date());
        //
        sysCodeRuleField.setUpdateBy(WebUtil.getCurrentUserId());
        sysCodeRuleField.setUpdateTime(new Date());
        //
        sysCodeRuleData.setUpdateBy(WebUtil.getCurrentUserId());
        sysCodeRuleData.setUpdateTime(new Date());

        try {
            sysCodeRuleFieldProvider.updateSysCodeRuleField(sysCodeRuleField);
            provider.updateSysCodeRule(sysCodeRule);
            sysCodeRuleDataProvider.updateSysCodeRuleData(sysCodeRuleData);
            result = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    *//**
     * 软删除删除编码规则
     * @param sysCodeRule
     * @param sysCodeRuleField
     * @param sysCodeRuleData
     * @return
     *//*
    @Transactional
    public int delteSysCodeRule(SysCodeRule sysCodeRule, SysCodeRuleField sysCodeRuleField, SysCodeRuleData sysCodeRuleData){
        int result = 0;
        sysCodeRule.setDelFlag("Y");
        sysCodeRule.setUpdateTime(new Date());
        sysCodeRuleField.setDelFlag("Y");
        sysCodeRuleField.setUpdateTime(new Date());
        sysCodeRuleData.setDelFlag("Y");
        sysCodeRuleData.setUpdateTime(new Date());
        try {
            sysCodeRuleFieldProvider.delSysCodeRuleField(sysCodeRuleField.getId());
            provider.delSysCodeRule(sysCodeRule.getId());
            sysCodeRuleDataProvider.delSysCodeRuleData(sysCodeRuleData.getId());
            result = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    *//**
     * 删除编码规则
     * @param sysCodeRule
     * @param sysCodeRuleField
     * @param sysCodeRuleData
     * @return
     *//*
    @Transactional
    public int deleteSysCodeRule(SysCodeRule sysCodeRule, SysCodeRuleField sysCodeRuleField, SysCodeRuleData sysCodeRuleData){
        int result = 0;
        try {
            sysCodeRuleFieldProvider.delSysCodeRuleField(sysCodeRuleField.getId());
            provider.delSysCodeRule(sysCodeRule.getId());
            sysCodeRuleDataProvider.delSysCodeRuleData(sysCodeRuleData.getId());
            result = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }*/
}
