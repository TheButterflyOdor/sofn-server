package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.asms.AsmsSubjSuperviseExpandMapper;
import com.sofn.dao.generator.AsmsSubjSuperviseMapper;
import com.sofn.dao.generator.AsmsSubjSvCancelMapper;
import com.sofn.dao.generator.AsmsSubjSvChangeMapper;
import com.sofn.dao.generator.AsmsSubjSvRevokeMapper;
import com.sofn.model.asms.AsmsSubjSuperviseDto;
import com.sofn.model.generator.AsmsSubjSupervise;
import com.sofn.model.generator.AsmsSubjSvCancel;
import com.sofn.model.generator.AsmsSubjSvChange;
import com.sofn.model.generator.AsmsSubjSvRevoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:35
 */
@DubboService(interfaceClass = AsmsSubjSuperviseProvider.class)
public class AsmsSubjSuperviseProviderImpl extends BaseProviderImpl<AsmsSubjSupervise> implements AsmsSubjSuperviseProvider {

    @Autowired
    private AsmsSubjSuperviseMapper subjSuperviseMapper;
    @Autowired
    private AsmsSubjSuperviseExpandMapper subjectSuperviseExpandMapper;
    @Autowired
    private AsmsSubjSvRevokeMapper subjSvRevokeMapper;
    @Autowired
    private AsmsSubjSvChangeMapper subjSvChangeMapper;
    @Autowired
    private AsmsSubjSvCancelMapper subjSvCancelMapper;

    @Override
    public PageInfo getSubjSuperviseList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjSuperviseDto> list = subjectSuperviseExpandMapper.getSubjSuperviseListNew(map);
        long count = subjectSuperviseExpandMapper.getSubjSuperviseCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getSubjSuperviseListByArea(String areaId) {
        if(StringUtils.isBlank(areaId)){
            return null;
        }
        List<AsmsSubjSupervise> list = subjectSuperviseExpandMapper.getSubjSuperviseListByArea(areaId);
        if(list!=null&&!list.isEmpty()){
            Map map = new HashMap();
            map.put("name",list.get(0).getSvName());
            map.put("contactPhone",list.get(0).getSvContactPhone());
            return map;
        }
        return null;
    }

    @Override
    public PageInfo getSubjSvChangeList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectSuperviseExpandMapper.getSubjSvChangeCount(map));
        pageInfo.setList(subjectSuperviseExpandMapper.getSubjSvChangeList(map));
        return pageInfo;
    }

    //统计变更待审核数量
    @Override
    public long countChangeToAudit(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSubjSvChangeCount(map);
    }

    @Override
    public PageInfo getChangeListBySvId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectSuperviseExpandMapper.getChangeCountBySvId(map));
        pageInfo.setList(subjectSuperviseExpandMapper.getChangeListBySvId(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSubjSvCancelList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectSuperviseExpandMapper.getSubjSvCancelCount(map));
        pageInfo.setList(subjectSuperviseExpandMapper.getSubjSvCancelList(map));
        return pageInfo;
    }

    //统计注销待审核数量
    @Override
    public long countCancelToAudit(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSubjSvCancelCount(map);
    }

    @Override
    public PageInfo getSubjSvRevokeList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectSuperviseExpandMapper.getSubjSvRevokeCount(map));
        pageInfo.setList(subjectSuperviseExpandMapper.getSubjSvRevokeList(map));
        return pageInfo;
    }

    //统计撤销待审核数量
    @Override
    public long countRevokeToAudit(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSubjSvRevokeCount(map);
    }

    @Override
    public int addSubjSupervise(AsmsSubjSupervise subjSupervise) {
        return subjSuperviseMapper.insert(subjSupervise);
    }

    @Override
    public int importSubjSupervise(List<AsmsSubjSupervise> list) {
        return subjectSuperviseExpandMapper.importSubjSupervise(list);
    }

    @Override
    @Transactional
    public int addSubjSvRevoke(AsmsSubjSvRevoke subjSvRevoke) {
        //第一步，先修改被撤销的监管机构的状态位
        int resultOne = subjectSuperviseExpandMapper.updateSubjSvStatusById(subjSvRevoke.getSvId(),"3");
        //第二步，插入撤销申请
        int resultTwo = subjSvRevokeMapper.insert(subjSvRevoke);
        return resultOne+resultTwo;
    }

    @Override
    @Transactional
    public int addSubjSvChange(AsmsSubjSvChange subjSvChange) {
        //第一步，先修改变更的监管机构的状态位
        int resultOne = subjectSuperviseExpandMapper.updateSubjSvStatusById(subjSvChange.getApplySvId(),"1");
        //第二步，插入变更申请
        int resultTwo = subjSvChangeMapper.insert(subjSvChange);
        return resultOne+resultTwo;
    }

    @Override
    @Transactional
    public int addSubjSvCancel(AsmsSubjSvCancel subjSvCancel) {
        //第一步，先修改注销的监管机构的状态位
        int resultOne = subjectSuperviseExpandMapper.updateSubjSvStatusById(subjSvCancel.getSvId(),"2");
        //第二步，插入注销申请
        int resultTwo = subjSvCancelMapper.insert(subjSvCancel);
        return resultOne+resultTwo;
    }

    @Override
    public AsmsSubjSupervise findSubjSuperviseById(String id) {
        return subjSuperviseMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjSvChange findSubjSvChangeById(String id) {
        return subjSvChangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjSvCancel findSubjSvCancelById(String id) {
        return subjSvCancelMapper.selectByPrimaryKey(id);
    }

    @Override
    public AsmsSubjSvRevoke findSubjSvRevokeById(String id) {
        return subjSvRevokeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void auditSubjSvChange(AsmsSubjSupervise subjSupervise, AsmsSubjSvChange subjSvChange) {
        //第一步，先修改申请表的状态
        subjSvChangeMapper.updateByPrimaryKey(subjSvChange);
        //第二步，修改监管机构主体信息
        subjSupervise.setStatus("0");
        if("1".equals(subjSvChange.getAuditState())) {
            subjSupervise.setSvName(subjSvChange.getSvName());
            subjSupervise.setSvCode(subjSvChange.getSvCode());
            subjSupervise.setSvType(subjSvChange.getSvType());
            subjSupervise.setSvTypeId(subjSvChange.getSvTypeId());
            subjSupervise.setSvLevel(subjSvChange.getSvLevel());
            subjSupervise.setSvLevelId(subjSvChange.getSvLevelId());
            subjSupervise.setSvArea(subjSvChange.getSvArea());
            subjSupervise.setSvAreaId(subjSvChange.getSvAreaId());
            subjSupervise.setSvAddress(subjSvChange.getSvAddress());
            subjSupervise.setSvLeader(subjSvChange.getSvLeader());
            subjSupervise.setSvLeaderPhone(subjSvChange.getSvLeaderPhone());
            subjSupervise.setSvContact(subjSvChange.getSvContact());
            subjSupervise.setSvContactPhone(subjSvChange.getSvContactPhone());
            subjSupervise.setSvContactQQ(subjSvChange.getSvContactQQ());
            subjSupervise.setSvContactEmail(subjSvChange.getSvContactEmail());
            subjSupervise.setSvPostcode(subjSvChange.getSvPostcode());
            //行业id
            subjSupervise.setIndustryId(subjSvChange.getIndustryId());
            //行业名称
            subjSupervise.setIndustryName(subjSvChange.getIndustryName());
        }
        subjSuperviseMapper.updateByPrimaryKey(subjSupervise);
    }

    @Override
    @Transactional
    public void auditSubjSvCancel(AsmsSubjSupervise subjSupervise, AsmsSubjSvCancel subjSvCancel) {
        //第一步，先修改申请表的状态
        subjSvCancelMapper.updateByPrimaryKey(subjSvCancel);
        //第二步，修改监管机构主体信息
        if("1".equals(subjSvCancel.getAuditState())) {
            subjSupervise.setStatus("4");//状态改为已注销
            subjSupervise.setDelFlag("Y");//逻辑删除
        }else{
            subjSupervise.setStatus("0");//状态改为正常
        }
        subjSuperviseMapper.updateByPrimaryKey(subjSupervise);
    }

    @Override
    @Transactional
    public void auditSubjSvRevoke(AsmsSubjSupervise subjSupervise, AsmsSubjSvRevoke subjSvRevoke) {
        //第一步，先修改申请表的状态
        subjSvRevokeMapper.updateByPrimaryKey(subjSvRevoke);
        //第二步，修改监管机构主体信息
        if("1".equals(subjSvRevoke.getAuditState())) {
            subjSupervise.setStatus("5");//状态改为已撤销
            subjSupervise.setDelFlag("Y");//逻辑删除
        }else{
            subjSupervise.setStatus("0");//状态改为正常
        }
        subjSuperviseMapper.updateByPrimaryKey(subjSupervise);
    }

    @Override
    public List getSvUserList(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSvUserList(map);
    }

    @Override
    public List getElUserList(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getElUserList(map);
    }

    @Override
    public List getDtUserList(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getDtUserList(map);
    }

    @Override
    public List<Map<String, Object>> getSuperiorSvList(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSuperiorSvList(map);
    }

    @Override
    public List<Map<String, Object>> getSuperiorElList(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSuperiorElList(map);
    }

    @Override
    public List<Map<String, Object>> getSuperiorDtList(Map<String, Object> map) {
        return subjectSuperviseExpandMapper.getSuperiorDtList(map);
    }

    @Override
    public List<AsmsSubjSupervise> getSubjSuperviseByCondition(Map<String,Object> map) {
        return subjectSuperviseExpandMapper.getSubjSuperviseByCondition(map);
    }

    public PageInfo getSubjSuperviseListForSys(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjSuperviseDto> list = subjectSuperviseExpandMapper.getSubjSuperviseListForSys(map);
        long count = subjectSuperviseExpandMapper.getSubjSuperviseCountForSys(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}
