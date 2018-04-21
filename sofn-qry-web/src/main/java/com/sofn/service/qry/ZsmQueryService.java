package com.sofn.service.qry;


import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;
import com.sofn.provider.qry.ZsmQueryProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 追溯码查询入口
 * Created by Administrator on 2016/11/7.
 */
@Service
public class ZsmQueryService extends BaseService {
    private ZsmQueryProvider provider;

    @DubboReference
    public void setProvider(ZsmQueryProvider provider) {
        this.provider = provider;
    }

    /**
     * 通过追溯码和主体身份码，查询是否有对应的采购记录
     *
     * @param zsm
     * @param entity_id
     * @return
     */
    public long zsmIsCgOrXs(String zsm, String entity_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsm", zsm);
        map.put("entity_id", entity_id);
        return provider.zsmIsCgOrXs(map);
    }

    public long existsTrace(String zsm, String entityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zsm", zsm);
        map.put("entityId", entityId);
        return provider.existsTrace(map);
    }

    /**
     * 查询销售下游追溯信息
     *
     * @param pageNum
     * @param pageSize
     * @param fromzsm
     * @param tozsm
     * @return
     */
    public PageInfo<Map<String, Object>> getDownZsm(int pageNum, int pageSize, String fromzsm, String tozsm) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("fromzsm", fromzsm);
        queryParams.put("tozsm", tozsm);
        return provider.pageForZsm(queryParams);
    }

    /**
     * 追溯码查询入口
     *
     * @param zsm
     * @return
     */
    public TtsScltxxcjCgglAndCustomer getBaseZsm(String zsm) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fromzsm", zsm);
        return provider.getCgglForZsm(map);
    }

    /**
     * 获取上游追溯信息
     *
     * @param zsm
     * @return
     */
    public TtsScltxxcjXsdjAndCustomer getUpZsm(String zsm) {
        TtsScltxxcjXsdjAndCustomer vo = new TtsScltxxcjXsdjAndCustomer();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tozsm", zsm);
        map.put("fromzsm", "");
        List<TtsScltxxcjXsdjAndCustomer> list = provider.getXsdjForZsm(map);
        if (null == list) {
            return vo;
        }
        if (list.isEmpty()) {
            return vo;
        }
        vo = list.get(0);
        return vo;
    }

    /**
     * 本级为销售的时候，查询上游信息
     *
     * @param pageNum
     * @param pageSize
     * @param zsm
     * @return
     */
    public PageInfo<Map<String, Object>> getXsUpZsm(int pageNum, int pageSize, String zsm) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("zsm", zsm);
        return provider.getXsUpZsm(queryParams);
    }

    /**
     * 追溯查询，本级是销售
     *
     * @param pageNum
     * @param pageSize
     * @param fromzsm
     * @param tozsm
     * @return
     */
    public PageInfo<Map<String, Object>> getXsbaseZsm(int pageNum, int pageSize, String fromzsm, String tozsm, String entityId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("fromzsm", fromzsm);
        queryParams.put("tozsm", tozsm);
        queryParams.put("entityId", entityId);
        return provider.getXsbaseZsm(queryParams);
    }

    /**
     * 产品是是否是合成，如果是合成返回合成前产品信息  --查询上级信息（合成的情况）
     *
     * @param pc
     * @return
     */
    public PageInfo<Map<String, Object>> getIfhc(int pageNum, int pageSize, String pc) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("pc", pc);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getInfoForNewPc(queryParams);
    }

    /**
     * 通过批次码查询来源信息 --查询上级信息
     *
     * @param pc
     * @param status     当前状态(1未卖出2未卖完3已卖完4已合成)
     * @param billStatus 单据状态(0初始1合成2合并)
     * @param toZsm
     * @return
     */
    public PageInfo<Map<String, Object>> getTopForPc(String pc, String status, String billStatus, int pageNum, int pageSize, String toZsm) {
        PageInfo pageInfo = new PageInfo();
        //表示产品来源是收获 产品来源是收获，则无对应的上级来源，所以这里是无信息
        if (StringUtils.isBlank(toZsm)) {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            pageInfo.setList(list);
            pageInfo.setTotal(0);
        } else {
            //表示产品来源是采购
            Map<String, Object> queryParams = new HashMap<>();
            queryParams.put("pageNum", pageNum);
            queryParams.put("pageSize", pageSize);
            queryParams.put("zsm", toZsm);
            pageInfo = provider.getPcToUpInfo(queryParams);
        }
        return pageInfo;
    }

    /**
     * 通过产品登记批次码查询本级信息
     *
     * @param pc
     * @return
     */
    public Map<String, Object> getBaseForPc(String pc, String entityId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pc", pc);
        map.put("entityId", entityId);
        Map<String, Object> result = provider.getBaseForPc(map);
        return result;
    }

    /**
     * 下游批次追溯信息
     *
     * @param pageNum
     * @param pageSize
     * @param pc
     * @return
     */
    public PageInfo<Map<String, Object>> getInfoforpc(int pageNum, int pageSize, String pc, String entityId) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("pc", pc);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("entityId", entityId);
        return provider.getInfoforpc(queryParams);
    }

    /**
     * 合成流向
     *
     * @param pc
     * @return
     */
    public Map<String, Object> getHclx(String pc) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("pc", pc);
        return provider.getHclx(queryParams);
    }

    /**
     * 通过追溯/批次码查询主体销售信息
     *
     * @param traceCodeType 追溯码类型：0追溯码，1批次码
     * @param traceCode     追溯码
     * @return
     */
    public Map<String, SaleInfoData> getSubjectSaleInfoByTraceCode(String traceCodeType, String traceCode) {
        if (traceCodeType == null || traceCodeType == "" || (!traceCodeType.equals("0") && !traceCodeType.equals("1"))) {
            throw new DataParseException("追溯码类型异常（追溯码类型：0追溯码，1批次码）");
        }
        Map<String, SaleInfoData> subjectSaleInfoDtos = null;
        if (traceCodeType.equals("0")) {//追溯码
            subjectSaleInfoDtos = provider.getSubjectSaleInfoByTraceCode(traceCode);
            return subjectSaleInfoDtos;
        }
        //TODO 批次码查询
        return subjectSaleInfoDtos;
    }

    /**
     * 通过追溯码查询销售关系
     *
     * @param traceCode 追溯码
     * @return ForceData {@link com.sofn.model.qry.ForceData}
     * @throws IllegalParameterException
     * @throws DataParseException
     */
    public ForceData findSaleRelationByTraceCode(String traceCode) throws IllegalParameterException, DataParseException {
        return provider.findSaleRelationByTraceCode(traceCode);
    }
}
