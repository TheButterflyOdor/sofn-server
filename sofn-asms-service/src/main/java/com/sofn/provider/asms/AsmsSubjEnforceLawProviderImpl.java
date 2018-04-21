
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsSubjEnforceLawExpandMapper;
import com.sofn.dao.generator.AsmsSubjElCancelMapper;
import com.sofn.dao.generator.AsmsSubjElChangeMapper;
import com.sofn.dao.generator.AsmsSubjElRevokeMapper;
import com.sofn.dao.generator.AsmsSubjEnforceLawMapper;
import com.sofn.model.asms.AsmsSubjEnforceLawDto;
import com.sofn.model.generator.AsmsSubjElCancel;
import com.sofn.model.generator.AsmsSubjElChange;
import com.sofn.model.generator.AsmsSubjElRevoke;
import com.sofn.model.generator.AsmsSubjEnforceLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:35
 */
@DubboService(interfaceClass = AsmsSubjEnforceLawProvider.class)
public class AsmsSubjEnforceLawProviderImpl extends BaseProviderImpl<AsmsSubjEnforceLaw> implements AsmsSubjEnforceLawProvider {

    @Autowired
    private AsmsSubjEnforceLawMapper subjEnforceLawMapper;
    @Autowired
    private AsmsSubjEnforceLawExpandMapper subjEnforceLawExpandMapper;
    @Autowired
    private AsmsSubjElRevokeMapper subjElRevokeMapper;
    @Autowired
    private AsmsSubjElChangeMapper subjElChangeMapper;
    @Autowired
    private AsmsSubjElCancelMapper subjElCancelMapper;

    @Override
    public PageInfo getSubjEnforceLawList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjEnforceLawDto> list = subjEnforceLawExpandMapper.getSubjectEnforceLawListNew(map);
        long count = subjEnforceLawExpandMapper.getSubjectEnforceLawCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    @Override
    public PageInfo getSubjEnforceLawListForSys(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();

        List<AsmsSubjEnforceLawDto> list = subjEnforceLawExpandMapper.getSubjectEnforceLawListForSys(map);
        long count = subjEnforceLawExpandMapper.getSubjectEnforceLawCountForSys(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo getSubjElChangeList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjEnforceLawExpandMapper.getSubjElChangeCount(map));
        pageInfo.setList(subjEnforceLawExpandMapper.getSubjElChangeList(map));
        return pageInfo;
    }

    @Override
    public long countChangeToAudit(Map<String, Object> map) {
        return subjEnforceLawExpandMapper.getSubjElChangeCount(map);
    }

    @Override
    public PageInfo getSubjElCancelList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjEnforceLawExpandMapper.getSubjElCancelCount(map));
        pageInfo.setList(subjEnforceLawExpandMapper.getSubjElCancelList(map));
        return pageInfo;
    }

    @Override
    public long countCancelToAudit(Map<String, Object> map) {
        return subjEnforceLawExpandMapper.getSubjElCancelCount(map);
    }

    @Override
    public PageInfo getSubjElRevokeList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjEnforceLawExpandMapper.getSubjElRevokeCount(map));
        pageInfo.setList(subjEnforceLawExpandMapper.getSubjElRevokeList(map));
        return pageInfo;
    }

    @Override
    public long countRevokeToAudit(Map<String, Object> map) {
        return subjEnforceLawExpandMapper.getSubjElRevokeCount(map);
    }

    @Override
    public PageInfo getChangeListByElId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjElChange> list = subjEnforceLawExpandMapper.getChangeListByElId(map);
        long count = subjEnforceLawExpandMapper.getChangeCountByElId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int addSubjEnforceLaw(AsmsSubjEnforceLaw subjEnforceLaw) {
        return subjEnforceLawMapper.insert(subjEnforceLaw);
    }

    @Transactional
    @Override
    public int addSubjElRevoke(AsmsSubjElRevoke subjElRevoke) {
        //第一步，先修改被撤销的执法机构的状态位
        int resultOne = subjEnforceLawExpandMapper.updateSubjElStatusById(subjElRevoke.getElId(),"3");
        //第二步，插入撤销申请
        int resultTwo = subjElRevokeMapper.insert(subjElRevoke);
        return resultOne+resultTwo;
    }
    //添加机构变更记录
    @Transactional
    @Override
    public int addSubjElChange(AsmsSubjElChange subjElChange){
        //第一步，先修改变更的执法机构的状态位
        int resultOne = subjEnforceLawExpandMapper.updateSubjElStatusById(subjElChange.getApplyElId(),"1");
        //第二步，插入变更申请
        int resultTwo = subjElChangeMapper.insert(subjElChange);
        return resultOne+resultTwo;
    }

    @Transactional
    @Override
    public int addSubjElCancel(AsmsSubjElCancel subjElCancel) {
        //第一步，先修改被注销的执法机构的状态位
        int resultOne = subjEnforceLawExpandMapper.updateSubjElStatusById(subjElCancel.getElId(),"2");
        //第二步，插入注销申请
        int resultTwo = subjElCancelMapper.insert(subjElCancel);
        return resultOne+resultTwo;
    }

    @Override
    public AsmsSubjEnforceLaw findSubjEnforceLawById(String id) {
        return subjEnforceLawMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjElChange findSubjElChangeById(String id) {
        return subjElChangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjElCancel findSubjElCancelById(String id) {
        return subjElCancelMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjElRevoke findSubjElRevokeById(String id) {
        return subjElRevokeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void auditSubjElChange(AsmsSubjEnforceLaw subjEnforceLaw, AsmsSubjElChange subjElChange) {
        //第一步，先修改申请表的状态
        subjElChangeMapper.updateByPrimaryKey(subjElChange);
        //第二步，修改执法机构主体信息
        subjEnforceLaw.setStatus("0");
        if("1".equals(subjElChange.getAuditState())) {
            subjEnforceLaw.setElName(subjElChange.getElName());
            subjEnforceLaw.setElCode(subjElChange.getElCode());
            subjEnforceLaw.setElType(subjElChange.getElType());
            subjEnforceLaw.setElTypeId(subjElChange.getElTypeId());
            subjEnforceLaw.setElLevel(subjElChange.getElLevel());
            subjEnforceLaw.setElLevelId(subjElChange.getElLevelId());
            subjEnforceLaw.setElArea(subjElChange.getElArea());
            subjEnforceLaw.setElAreaId(subjElChange.getElAreaId());
            subjEnforceLaw.setElAddress(subjElChange.getElAddress());
            subjEnforceLaw.setElLeader(subjElChange.getElLeader());
            subjEnforceLaw.setElLeaderPhone(subjElChange.getElLeaderPhone());
            subjEnforceLaw.setElContact(subjElChange.getElContact());
            subjEnforceLaw.setElContactPhone(subjElChange.getElContactPhone());
            subjEnforceLaw.setElContactQQ(subjElChange.getElContactQQ());
            subjEnforceLaw.setElContactEmail(subjElChange.getElContactEmail());
            subjEnforceLaw.setElPostcode(subjElChange.getElPostcode());
            subjEnforceLaw.setElUnitNature(subjElChange.getElUnitNature());
            subjEnforceLaw.setElWorkBody(subjElChange.getElWorkBody());
        }
        subjEnforceLawMapper.updateByPrimaryKey(subjEnforceLaw);
    }

    @Override
    @Transactional
    public void auditSubjElCancel(AsmsSubjEnforceLaw subjEnforceLaw, AsmsSubjElCancel subjElCancel) {
        //第一步，先修改申请表的状态
        subjElCancelMapper.updateByPrimaryKey(subjElCancel);
        //第二步，修改执法机构主体信息
        if("1".equals(subjElCancel.getAuditState())) {
            subjEnforceLaw.setStatus("4");//审核通过，状态改为已注销
            subjEnforceLaw.setDelFlag("Y");//逻辑删除
        }else{
            subjEnforceLaw.setStatus("0");//审核未通过，状态改为正常
        }
        subjEnforceLawMapper.updateByPrimaryKey(subjEnforceLaw);
    }

    @Override
    @Transactional
    public void auditSubjElRevoke(AsmsSubjEnforceLaw subjEnforceLaw, AsmsSubjElRevoke subjElRevoke) {
        //第一步，先修改申请表的状态
        subjElRevokeMapper.updateByPrimaryKey(subjElRevoke);
        //第二步，修改执法机构主体信息
        if("1".equals(subjElRevoke.getAuditState())) {
            subjEnforceLaw.setStatus("5");//审核通过，状态改为已撤销
            subjEnforceLaw.setDelFlag("Y");//逻辑删除
        }else{
            subjEnforceLaw.setStatus("0");//审核未通过，状态改为正常
        }
        subjEnforceLawMapper.updateByPrimaryKey(subjEnforceLaw);
    }

    @Override
    public List<AsmsSubjEnforceLaw> getSubjEnforceLawByCondition(Map<String,Object> map) {
        return subjEnforceLawExpandMapper.getSubjEnforceLawByCondition(map);
    }
}
