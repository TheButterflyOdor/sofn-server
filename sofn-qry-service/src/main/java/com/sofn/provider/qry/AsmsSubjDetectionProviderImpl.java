package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.AsmsSubjDetectionExpandMapper;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.model.generator.AsmsSubjDtCancel;
import com.sofn.model.generator.AsmsSubjDtChange;
import com.sofn.model.generator.AsmsSubjDtRevoke;
import com.sofn.model.qry.AsmsSubjDetectionDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年09月08日 下午 4:35
 */
@DubboService(interfaceClass = AsmsSubjDetectionProvider.class)
public class AsmsSubjDetectionProviderImpl extends BaseProviderImpl<AsmsSubjDetection> implements AsmsSubjDetectionProvider {

    @Autowired
    private AsmsSubjDetectionExpandMapper subjDetectionExpandMapper;

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
    public AsmsSubjDetection findSubjDetectionById(String id) {
        return subjDetectionExpandMapper.selectAsmsSubjDetectionByPrimaryKey(id);
    }

    @Override
    public AsmsSubjDtChange findSubjDtChangeById(String id) {
        return subjDetectionExpandMapper.selectAsmsSubjDtChangeByPrimaryKey(id);
    }

    @Override
    public AsmsSubjDtCancel findSubjDtCancelById(String id) {
        return subjDetectionExpandMapper.selectAsmsSubjDtCancelByPrimaryKey(id);
    }

    @Override
    public AsmsSubjDtRevoke findSubjDtRevokeById(String id) {
        return subjDetectionExpandMapper.selectAsmsSubjDtRevokeByPrimaryKey(id);
    }

    @Override
    public List<AsmsSubjDetection> getSubjDetectionByCondition(Map<String,Object> map) {
        return subjDetectionExpandMapper.getSubjDetectionByCondition(map);
    }
}
