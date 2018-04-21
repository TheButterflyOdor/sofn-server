package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.qry.SubjectExpandMapper;
import com.sofn.model.generator.*;
import com.sofn.model.qry.TtsScltxxcjRegiterDto2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年03月17日 下午 2:42
 */
@DubboService(interfaceClass = SubjectProvider.class)
public class SubjectProviderImpl extends BaseProviderImpl implements SubjectProvider{

    @Autowired
    private SubjectExpandMapper subjectExpandMapper;

    @Override
    public PageInfo getSubjSuperviseList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjSupervise> list = subjectExpandMapper.getSubjSuperviseList(map);
        long count = subjectExpandMapper.getSubjSuperviseCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo getSubjEnforceLawList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjEnforceLaw> list = subjectExpandMapper.getSubjectEnforceLawList(map);
        long count = subjectExpandMapper.getSubjectEnforceLawCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo getSubjDetectionList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsSubjDetection> list = subjectExpandMapper.getSubjDetectionList(map);
        long count = subjectExpandMapper.getSubjDetectionCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<TtsScltxxcjRegiterDto2> getSubjEntList(Map<String, Object> map) {
        return subjectExpandMapper.getSubjEntListNew(map);
    }

    @Override
    public long getSubjEntTotal(Map<String, Object> map) {
        return subjectExpandMapper.getSubjEntTotal(map);
    }

    @Override
    public PageInfo<TtsScltxxcjUserChangeRecord> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        long count = 0;
        if(null != map.get("entityId") && StringUtils.isNotBlank(map.get("entityId").toString())){
            list = subjectExpandMapper.getPageInfo(map);
            count = subjectExpandMapper.getCount(map);
        }
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<AsmsSubjEntBadrecord> getAsmsSubjEntBadrecordByIdList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectExpandMapper.getSubjEntBadrecordByIdCount(map));
        pageInfo.setList(subjectExpandMapper.findSubjEntBadrecodeById(map));
        return pageInfo;
    }

    @Override
    public AsmsSubjSupervise findSubjSuperviseById(String id) {
        return subjectExpandMapper.findSubjSuperviseById(id);
    }

    @Override
    public AsmsSubjEnforceLaw findSubjEnforceLawById(String id) {
        return subjectExpandMapper.findSubjEnforceLawById(id);
    }

    @Override
    public AsmsSubjDetection findSubjDetectionById(String id) {
        return subjectExpandMapper.findSubjDetectionById(id);
    }

    @Override
    public TtsScltxxcjRegiterDto2 findSubjEntById(String id) {
        return subjectExpandMapper.findSubjEntById(id);
    }

    @Override
    public TtsScltxxcjRegiterDto2 findSubjEntByEntityIdCode(Map<String, Object> map) {
        return subjectExpandMapper.findSubjEntByEntityIdCode(map);
    }

    @Override
    public AsmsSubjEntTemp findSubjEntTempById(String id) {
        return subjectExpandMapper.findSubjEntTempById(id);
    }

    @Override
    public PageInfo getChangeListBySvId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectExpandMapper.getChangeCountBySvId(map));
        pageInfo.setList(subjectExpandMapper.getChangeListBySvId(map));
        return pageInfo;
    }

    @Override
    public PageInfo getChangeListByElId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getChangeListByElId(map));
        pageInfo.setTotal(subjectExpandMapper.getChangeCountByElId(map));
        return pageInfo;
    }

    @Override
    public PageInfo getChangeListByDtId(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(subjectExpandMapper.getChangeCountByDtId(map));
        pageInfo.setList(subjectExpandMapper.getChangeListByDtId(map));
        return pageInfo;
    }

    @Override
    public AsmsSubjSvChange findSubjSvChangeById(String id) {
        return subjectExpandMapper.findSubjSvChangeById(id);
    }

    @Override
    public AsmsSubjElChange findSubjElChangeById(String id) {
        return subjectExpandMapper.findSubjElChangeById(id);
    }

    @Override
    public AsmsSubjDtChange findSubjDtChangeById(String id) {
        return subjectExpandMapper.findSubjDtSvChangeById(id);
    }

    @Override
    public PageInfo getProductListByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getProductListByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getProductCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSubjEntBaseByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSubjEntBaseByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getSubjEntBaseCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getBathByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getBathByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getBathCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getHcBathById(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getHcBathById(map));
        pageInfo.setTotal(subjectExpandMapper.getHcCountBathById(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSaleByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSaleByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getSaleCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id) {
        return subjectExpandMapper.getXsdjAndCustomerById(id);
    }

    @Override
    public TtsScltxxcjXsthAndCustomer queryByXsjlId(String id) {
        return subjectExpandMapper.queryByXsjlId(id);
    }

    @Override
    public PageInfo<TtsScltxxcjXsdjAndCustomer>  getSubjCgglListByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSubjCgglListByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getSubjCgglCountListByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSalePrintListByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSalePrintListByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getSalePrintCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getStockPrintListByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getStockPrintListByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getStockPrintCountByEntityIdcode(map));
        return pageInfo;
    }
    @Override
    public PageInfo getSlaughterListByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSlaughterListByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getSlaughterCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSubjEntAndBathList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSubjEntAndBathList(map));
        pageInfo.setTotal(subjectExpandMapper.getSubjEntAndBathCount(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSubjEntAndTransactionInfoList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSubjEntAndTransactionInfoList(map));
        pageInfo.setTotal(subjectExpandMapper.getSubjEntAndTransactionInfoCount(map));
        return pageInfo;
    }

    @Override
    public PageInfo getTransactionInfoListByEntityIdcode(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getTransactionInfoListByEntityIdcode(map));
        pageInfo.setTotal(subjectExpandMapper.getTransactionInfoCountByEntityIdcode(map));
        return pageInfo;
    }

    @Override
    public PageInfo getSubjEntAndPrintInfoList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjectExpandMapper.getSubjEntAndPrintInfoList(map));
        pageInfo.setTotal(subjectExpandMapper.getSubjEntAndPrintInfoCount(map));
        return pageInfo;
    }
}
