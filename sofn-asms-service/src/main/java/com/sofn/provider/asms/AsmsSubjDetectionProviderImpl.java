package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsSubjDetectionExpandMapper;
import com.sofn.dao.generator.AsmsSubjDetectionMapper;
import com.sofn.dao.generator.AsmsSubjDtCancelMapper;
import com.sofn.dao.generator.AsmsSubjDtChangeMapper;
import com.sofn.dao.generator.AsmsSubjDtRevokeMapper;
import com.sofn.model.asms.AsmsSubjDetectionDto;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.AsmsSubjDtChange;
import com.sofn.model.generator.AsmsSubjDtRevoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:35
 */
@DubboService(interfaceClass = AsmsSubjDetectionProvider.class)
public class AsmsSubjDetectionProviderImpl extends BaseProviderImpl<AsmsSubjDetection> implements AsmsSubjDetectionProvider {

    @Autowired
    private AsmsSubjDetectionMapper subjDetectionMapper;
    @Autowired
    private AsmsSubjDetectionExpandMapper subjDetectionExpandMapper;
    @Autowired
    private AsmsSubjDtRevokeMapper subjDtRevokeMapper;
    @Autowired
    private AsmsSubjDtChangeMapper subjDtChangeMapper;
    @Autowired
    private AsmsSubjDtCancelMapper subjDtCancelMapper;

    @Override
    public PageInfo getSubjDetectionList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjDetectionDto> list = subjDetectionExpandMapper.getSubjDetectionListNew(map);
        long count = subjDetectionExpandMapper.getSubjDetectionCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo getSubjDetectionListForSys(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjDetectionDto> list = subjDetectionExpandMapper.getSubjDetectionListForSys(map);
        long count = subjDetectionExpandMapper.getSubjDetectionCountForSys(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo getSubjDtListByCondition(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjDetection> list = subjDetectionExpandMapper.getSubjDetectionList(map);
        long count = subjDetectionExpandMapper.getSubjDetectionCount(map);
        pageInfo.setTotal(count);
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public PageInfo getSubjDtChangeList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjDetectionExpandMapper.getSubjDtChangeCount(map));
        pageInfo.setList(subjDetectionExpandMapper.getSubjDtChangeList(map));
        return pageInfo;
    }

    @Override
    public long countChangeToAudit(Map<String, Object> map) {
        return subjDetectionExpandMapper.getSubjDtChangeCount(map);
    }

    @Override
    public PageInfo getChangeListByDtId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjDetectionExpandMapper.getChangeCountByDtId(map));
        pageInfo.setList(subjDetectionExpandMapper.getChangeListByDtId(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSubjDtCancelList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjDetectionExpandMapper.getSubjDtCancelCount(map));
        pageInfo.setList(subjDetectionExpandMapper.getSubjDtCancelList(map));
        return pageInfo;
    }

    @Override
    public long countCancelToAudit(Map<String, Object> map) {
        return subjDetectionExpandMapper.getSubjDtCancelCount(map);
    }

    @Override
    public PageInfo getSubjDtRevokeList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjDetectionExpandMapper.getSubjDtRevokeCount(map));
        pageInfo.setList(subjDetectionExpandMapper.getSubjDtRevokeList(map));
        return pageInfo;
    }

    @Override
    public long countRevokeToAudit(Map<String, Object> map) {
        return subjDetectionExpandMapper.getSubjDtRevokeCount(map);
    }

    @Override
    public int addSubjDetection(AsmsSubjDetection subjDetection) {
        return subjDetectionMapper.insert(subjDetection);
    }

    @Override
    @Transactional
    public int addSubjDtRevoke(AsmsSubjDtRevoke subjDtRevoke) {
        //第一步，修改检测机构主体的状态位
        int resultOne = subjDetectionExpandMapper.updateSubjDtStatusById(subjDtRevoke.getDtId(),"3");
        //第二步，新增检测机构主体撤销申请
        int resultTwo = subjDtRevokeMapper.insert(subjDtRevoke);
        return resultOne+resultTwo;
    }

    @Override
    @Transactional
    public int addSubjDtChange(AsmsSubjDtChange subjDtChange) {
        //第一步，先修改变更的检测机构的状态位
        int resultOne = subjDetectionExpandMapper.updateSubjDtStatusById(subjDtChange.getApplyDtId(), "1");
        //第二步，插入变更申请
        subjDtChange.setAuditState("0");
        int resultTwo = subjDtChangeMapper.insert(subjDtChange);
        return resultOne+resultTwo;
    }

    @Override
    @Transactional
    public int addSubjDtCancel(AsmsSubjDtCancel subjDtCancel) {
        //第一步，先修改注销的检测机构的状态位
        int resultOne = subjDetectionExpandMapper.updateSubjDtStatusById(subjDtCancel.getDtId(), "2");
        //第二步，插入注销申请
        subjDtCancel.setAuditState("0");
        int resultTwo = subjDtCancelMapper.insert(subjDtCancel);
        return resultOne+resultTwo;
    }

    @Override
    public AsmsSubjDetection findSubjDetectionById(String id) {
        return subjDetectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjDtChange findSubjDtChangeById(String id) {
        return subjDtChangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjDtCancel findSubjDtCancelById(String id) {
        return subjDtCancelMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjDtRevoke findSubjDtRevokeById(String id) {
        return subjDtRevokeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void auditSubjDtChange(AsmsSubjDetection subjDetection, AsmsSubjDtChange subjDtChange) {
        //第一步，先修改申请表的状态
        subjDtChangeMapper.updateByPrimaryKey(subjDtChange);
        subjDetection.setStatus("0");
        //第二步，修改检测机构主体信息
        if("1".equals(subjDtChange.getAuditState())) {
            subjDetection.setDtName(subjDtChange.getDtName());
            subjDetection.setDtCode(subjDtChange.getDtCode());
            subjDetection.setDtType(subjDtChange.getDtType());
            subjDetection.setDtTypeId(subjDtChange.getDtTypeId());
            subjDetection.setDtTypeId(subjDtChange.getDtTypeId());
            subjDetection.setDtNature(subjDtChange.getDtNature());
            subjDetection.setDtNatureId(subjDtChange.getDtNatureId());
            subjDetection.setDtQualifications(subjDtChange.getDtQualifications());
            subjDetection.setDtQualificationsId(subjDtChange.getDtQualificationsId());
            subjDetection.setDtRelyOnUnit(subjDtChange.getDtRelyOnUnit());
            subjDetection.setDtLevel(subjDtChange.getDtLevel());
            subjDetection.setDtLevelId(subjDtChange.getDtLevelId());
            subjDetection.setDtArea(subjDtChange.getDtArea());
            subjDetection.setDtAreaId(subjDtChange.getDtAreaId());
            subjDetection.setDtAddress(subjDtChange.getDtAddress());
            subjDetection.setDtLegalMan(subjDtChange.getDtLegalMan());
            subjDetection.setDtLeader(subjDtChange.getDtLeader());
            subjDetection.setDtLeaderPhone(subjDtChange.getDtLeaderPhone());
            subjDetection.setDtContact(subjDtChange.getDtContact());
            subjDetection.setDtContactPhone(subjDtChange.getDtContactPhone());
            subjDetection.setDtContactEmail(subjDtChange.getDtContactEmail());
            subjDetection.setDtContactQQ(subjDtChange.getDtContactQQ());
            subjDetection.setDtPostcode(subjDtChange.getDtPostcode());
            subjDetection.setDtTechnicalDirector(subjDtChange.getDtTechnicalDirector());
            subjDetection.setDtQualityDirector(subjDtChange.getDtQualityDirector());
            subjDetection.setDtParameter(subjDtChange.getDtParameter());
            subjDetection.setDtProductRange(subjDtChange.getDtProductRange());
        }
        subjDetectionMapper.updateByPrimaryKey(subjDetection);
    }

    @Override
    @Transactional
    public void auditSubjDtCancel(AsmsSubjDetection subjDetection, AsmsSubjDtCancel subjDtCancel) {
        //第一步，先修改申请表的状态
        subjDtCancelMapper.updateByPrimaryKey(subjDtCancel);
        //第二步，修改检测机构主体信息
        if("1".equals(subjDtCancel.getAuditState())) {
            subjDetection.setStatus("4");//审核通过，状态改为已注销
            subjDetection.setDelFlag("Y");//逻辑删除
        }else{
            subjDetection.setStatus("0");//审核未通过，状态改为正常
        }
        subjDetectionMapper.updateByPrimaryKey(subjDetection);
    }

    @Override
    @Transactional
    public void auditSubjDtRevoke(AsmsSubjDetection subjDetection, AsmsSubjDtRevoke subjDtRevoke) {
        //第一步，先修改申请表的状态
        subjDtRevokeMapper.updateByPrimaryKey(subjDtRevoke);
        //第二步，修改检测机构主体信息
        if("1".equals(subjDtRevoke.getAuditState())) {
            subjDetection.setStatus("5");//审核通过，状态改为已撤销
            subjDetection.setDelFlag("Y");//逻辑删除
        }else{
            subjDetection.setStatus("0");//审核未通过，状态改为正常
        }
        subjDetectionMapper.updateByPrimaryKey(subjDetection);
    }

    @Override
    public List<AsmsSubjDetection> getSubjDetectionByCondition(Map<String,Object> map) {
        return subjDetectionExpandMapper.getSubjDetectionByCondition(map);
    }
}
