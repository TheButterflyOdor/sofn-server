package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUserChangeRecord;
import com.sofn.provider.tts.TtsScltxxcjUserChangeRecordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 注册主体信息表变更记录表 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjUserChangeRecordService extends BaseService<TtsScltxxcjUserChangeRecordProvider, TtsScltxxcjUserChangeRecord> {

    @DubboReference
    public void setTtsScltxxcjUserChangeRecordProvider(TtsScltxxcjUserChangeRecordProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

    public PageInfo getPageInfo(TtsScltxxcjUserChangeRecord bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("entityId", bean != null ? bean.getEntityId() : "");
        return provider.getPageInfo(queryParams);
    }

    public Integer insert(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        return provider.insertRe(ttsScltxxcjUserChangeRecord);
    }

    ;

    public Integer updateEntityByZX(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        return provider.updateEntityByZX(ttsScltxxcjUserChangeRecord);
    }

    ;

    public boolean updateEntityInfoBy(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        boolean flag = true;
        try {
            if (ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("企业/个体工商户")
                    || ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("合作社")){
                ttsScltxxcjUserChangeRecord.setInfoUnique(ttsScltxxcjUserChangeRecord.getCreditCode());
            }
            if(ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("个人")
                    || ttsScltxxcjUserChangeRecord.getEntityScaleName().equals("家庭农场")){
                ttsScltxxcjUserChangeRecord.setInfoUnique(ttsScltxxcjUserChangeRecord.getLegalIdnumber());
            }
            //备案变更
            if(ttsScltxxcjUserChangeRecord.getApproveType().equals("2") || ttsScltxxcjUserChangeRecord.getApproveType() == "2"){
                //根据企业号判断备案变更类型
                //有企业注册号表示是企业或者合作社备案变更
                if (ttsScltxxcjUserChangeRecord.getChangeType().equals("1") || ttsScltxxcjUserChangeRecord.getChangeType() == "1") {
                    this.updateEntityInfoByFirm(ttsScltxxcjUserChangeRecord);
                    return flag;
                } else {
                    //没有企业注册号表示是个人或家庭农场变更
                    this.updateEntityInfoBySingle(ttsScltxxcjUserChangeRecord);
                    return flag;
                }
            }
            //备案注销
            if(ttsScltxxcjUserChangeRecord.getApproveType().equals("1") || ttsScltxxcjUserChangeRecord.getApproveType() == "1"){
                ttsScltxxcjUserChangeRecord.setUpdateTime(new Date());
                ttsScltxxcjUserChangeRecord.setApplyUpdateTime(new Date());
                ttsScltxxcjUserChangeRecord.setId(StringUtils.getUUIDString());
                this.insert(ttsScltxxcjUserChangeRecord);
                this.updateEntityByZX(ttsScltxxcjUserChangeRecord);
                return flag;
            }
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }finally {
            return flag;
        }
    }

    /**
     * 核心信息变更提交审核，同时修改主体状态，表示正在变更审核中
     *
     * @param ttsScltxxcjUserChangeRecord
     */
    private void examineEntity(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        this.insert(ttsScltxxcjUserChangeRecord);
        this.updateEntityByZX(ttsScltxxcjUserChangeRecord);
    }

    /**
     * 个人或者家庭农场备案变更申请
     *
     * @param ttsScltxxcjUserChangeRecord
     */
    private void updateEntityInfoBySingle(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        //非核心信息变更不用审核，直接进行数据修改
        TtsScltxxcjRegiter scltxxcjRegiter = new TtsScltxxcjRegiter();
        scltxxcjRegiter.setId(ttsScltxxcjUserChangeRecord.getEntityId());
        scltxxcjRegiter.setEntityType(ttsScltxxcjUserChangeRecord.getEntityType());
        scltxxcjRegiter.setEntityTypeName(ttsScltxxcjUserChangeRecord.getEntityTypeName());
        scltxxcjRegiter.setEntityProperty(ttsScltxxcjUserChangeRecord.getEntityProperty());
        scltxxcjRegiter.setEntityPropertyName(ttsScltxxcjUserChangeRecord.getEntityPropertyName());
        scltxxcjRegiter.setEntityIndustry(ttsScltxxcjUserChangeRecord.getEntityIndustry());
        scltxxcjRegiter.setEntityIndustryName(ttsScltxxcjUserChangeRecord.getEntityIndustryName());
        scltxxcjRegiter.setArea(ttsScltxxcjUserChangeRecord.getArea());
        scltxxcjRegiter.setAddress(ttsScltxxcjUserChangeRecord.getAddress());
        scltxxcjRegiter.setLongitude(ttsScltxxcjUserChangeRecord.getLongitude());
        scltxxcjRegiter.setLatitude(ttsScltxxcjUserChangeRecord.getLatitude());
        scltxxcjRegiter.setContactPhone(ttsScltxxcjUserChangeRecord.getContactPhone());
        scltxxcjRegiter.setContactEmail(ttsScltxxcjUserChangeRecord.getContactEmail());
//        scltxxcjRegiter.setEntityScale(ttsScltxxcjUserChangeRecord.getEntityScale());
        ttsScltxxcjRegiterService.updateByFirm(scltxxcjRegiter);
        //如果提交了核心信息的变更内容，则插入待审核申请记录
        if (!ttsScltxxcjUserChangeRecord.getUpdateContent().equals("") && ttsScltxxcjUserChangeRecord.getUpdateContent() != "") {
            //这里将数据插入待审核表
            ttsScltxxcjUserChangeRecord.setId(UUID.randomUUID().toString().replace("-", ""));
            ttsScltxxcjUserChangeRecord.setUpdateTime(new Date());
            ttsScltxxcjUserChangeRecord.setApplyUpdateTime(new Date());
            ttsScltxxcjUserChangeRecord.setDelFlag("N");
            this.examineEntity(ttsScltxxcjUserChangeRecord);
        }
    }

    /**
     * 企业或合作社备案变更申请
     *
     * @param ttsScltxxcjUserChangeRecord
     */
    private void updateEntityInfoByFirm(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        //非核心信息变更不用审核，直接进行数据修改
        TtsScltxxcjRegiter scltxxcjRegiter = new TtsScltxxcjRegiter();
        scltxxcjRegiter.setId(ttsScltxxcjUserChangeRecord.getEntityId());
        scltxxcjRegiter.setEntityScale(ttsScltxxcjUserChangeRecord.getEntityScale());
        scltxxcjRegiter.setEntityScaleName(ttsScltxxcjUserChangeRecord.getEntityScaleName());
        scltxxcjRegiter.setEntityType(ttsScltxxcjUserChangeRecord.getEntityType());
        scltxxcjRegiter.setEntityTypeName(ttsScltxxcjUserChangeRecord.getEntityTypeName());
        scltxxcjRegiter.setEntityProperty(ttsScltxxcjUserChangeRecord.getEntityProperty());
        scltxxcjRegiter.setEntityPropertyName(ttsScltxxcjUserChangeRecord.getEntityPropertyName());
        scltxxcjRegiter.setEntityIndustry(ttsScltxxcjUserChangeRecord.getEntityIndustry());
        scltxxcjRegiter.setEntityIndustryName(ttsScltxxcjUserChangeRecord.getEntityIndustryName());
        scltxxcjRegiter.setContactName(ttsScltxxcjUserChangeRecord.getContactName());
        scltxxcjRegiter.setContactPhone(ttsScltxxcjUserChangeRecord.getContactPhone());
        scltxxcjRegiter.setContactEmail(ttsScltxxcjUserChangeRecord.getContactEmail());
        ttsScltxxcjRegiterService.updateByFirm(scltxxcjRegiter);
        //如果提交了核心信息的变更内容，则插入待审核申请记录
        if (!ttsScltxxcjUserChangeRecord.getUpdateContent().equals("") && ttsScltxxcjUserChangeRecord.getUpdateContent() != "") {
            //这里将数据插入待审核表
            ttsScltxxcjUserChangeRecord.setId(UUID.randomUUID().toString().replace("-", ""));
            ttsScltxxcjUserChangeRecord.setUpdateTime(new Date());
            ttsScltxxcjUserChangeRecord.setApplyUpdateTime(new Date());
            ttsScltxxcjUserChangeRecord.setDelFlag("N");
            //插入备案申请
            this.examineEntity(ttsScltxxcjUserChangeRecord);
        }


    }
}