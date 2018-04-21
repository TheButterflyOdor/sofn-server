package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.exception.BusinessException;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.qry.ZsmQueryExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;
import com.sofn.model.qry.SaleInfoLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by czx on 2017/3/17.
 */
@DubboService(interfaceClass = ZsmQueryProvider.class)
public class ZsmQueryProviderImpl extends BaseProviderImpl implements ZsmQueryProvider {
    @Autowired
    private ZsmQueryExpandMapper zsmQueryExpandMapper;

    @Override
    public TtsScltxxcjCgglAndCustomer getCgglForZsm(Map<String, Object> map) {
        return zsmQueryExpandMapper.getCgglForZsm(map);
    }

    @Override
    public long zsmIsCgOrXs(Map<String, Object> map) {
        return zsmQueryExpandMapper.zsmIsCgOrXs(map);
    }

    @Override
    public PageInfo<Map<String, Object>> getInfoForNewPc(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = zsmQueryExpandMapper.getInfoForNewPc(map);
        long count = zsmQueryExpandMapper.getCountForNewPc(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getBaseForPc(Map<String, Object> map) {
        return zsmQueryExpandMapper.getBaseForPc(map);
    }

    @Override
    public PageInfo<Map<String, Object>> pageForZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = zsmQueryExpandMapper.pageForZsm(map);
        long count = zsmQueryExpandMapper.pageForCountZsm(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getXsbaseZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = zsmQueryExpandMapper.getXsbaseZsm(map);
        long count = zsmQueryExpandMapper.getXsbaseZsmCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getPcToUpInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = zsmQueryExpandMapper.getPcToUpInfo(map);
        long count = zsmQueryExpandMapper.getPcToUpCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getInfoforpc(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = zsmQueryExpandMapper.getInfoforpc(map);
        long count = zsmQueryExpandMapper.getCountforpc(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getHclx(Map<String, Object> pc) {
        return zsmQueryExpandMapper.getHclx(pc);
    }

    @Override
    public List<TtsScltxxcjXsdjAndCustomer> getXsdjForZsm(Map<String, Object> map) {
        return zsmQueryExpandMapper.getXsdjForZsm(map);
    }

    @Override
    public PageInfo<Map<String, Object>> getXsUpZsm(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = zsmQueryExpandMapper.getXsUpZsm(map);
        long count = zsmQueryExpandMapper.getXsUpZsmCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public long existsTrace(Map<String, Object> map) {
        return zsmQueryExpandMapper.existsTrace(map);
    }

    /**
     * 通过追溯码获取采购主体信息
     *
     * @param traceCode 追溯码
     * @return
     */
    @Override
    public TtsScltxxcjCgglAndCustomer getCgSubjectInfoByTraceCode(String traceCode) {
        return zsmQueryExpandMapper.getCgSubjectInfoByTraceCode(traceCode);
    }

    /**
     * 通过追溯码获取销售主体信息
     *
     * @param traceCode 追溯码
     * @return
     */
    @Override
    public TtsScltxxcjXsdjAndCustomer getXsSubjectInfoByTraceCode(String traceCode) {
        return zsmQueryExpandMapper.getXsSubjectInfoByTraceCode(traceCode);
    }

    /**
     * 通过追溯码获取生产管理记录
     *
     * @param traceCode 追溯码
     * @return
     */
    @Override
    public TtsScltxxcjScgl getProductionProductInfo(String traceCode) {
        return zsmQueryExpandMapper.getProductionProductInfo(traceCode);
    }

    /**
     * 通过追溯码获取合成后产品登记信息
     *
     * @param traceCode 追溯码
     * @return
     */
    @Override
    public TtsScltxxcjScgl getSynthesisProductInfo(String traceCode) {
        return zsmQueryExpandMapper.getSynthesisProductInfo(traceCode);
    }

    /**
     * 通过批次码获取采购对象
     *
     * @param batchCode 批次码
     * @return
     */
    @Override
    public List<Map<String, Object>> getCgSubjectInfoByBatchCode(String batchCode) {
        return zsmQueryExpandMapper.getCgSubjectInfoByBatchCode(batchCode);
    }

    /**
     * 通过批次码获取入市对象
     *
     * @param batchCode 批次码
     * @return
     */
    @Override
    public List<Map<String, Object>> getRsSubjectInfoByBatchCode(String batchCode) {
        return zsmQueryExpandMapper.getRsSubjectInfoByBatchCode(batchCode);
    }

    /**
     * 通过客户id查询客户信息（入市对象）
     *
     * @param customerIds 客户ids
     * @return
     */
    @Override
    public List<Map<String, Object>> getRsSubjectInfoByIds(List<String> customerIds) {
        return zsmQueryExpandMapper.getRsSubjectInfoByIds(customerIds);
    }

    /**
     * 通过主体身份码查询主体信息（流通对象）
     *
     * @param entityIdCode 主体码
     * @return
     */
    @Override
    public Map<String, Object> getSubjectInfoByEntityIdCode(String entityIdCode) {
        return zsmQueryExpandMapper.getSubjectInfoByEntityIdCode(entityIdCode);
    }

    /**
     * 通过销售登记id获取生产管理信息
     *
     * @param saleRegistrationId 销售登记ID
     * @return
     */
    @Override
    public List<TtsScltxxcjScgl> getScglInfos(String saleRegistrationId) {
        return zsmQueryExpandMapper.getScglInfos(saleRegistrationId);
    }

    /**
     * 通过生产Id获取本批次合成前产品
     *
     * @param productionId 生产管理ID
     * @return
     */
    @Override
    public List<TtsScltxxcjScgl> getHcScglInfos(String productionId) {
        return zsmQueryExpandMapper.getHcScglInfos(productionId);
    }

    /**
     * 通过追溯码查询销售关系
     *
     * @param traceCode 追溯码
     * @return ForceData {@link ForceData}
     * @throws IllegalParameterException
     * @throws DataParseException
     */
    @Override
    public ForceData findSaleRelationByTraceCode(String traceCode) throws IllegalParameterException, DataParseException {
        return findSaleRelation(0, traceCode);
    }

    /**
     * 通过批次码查询销售关系
     *
     * @param batchCode 批次码
     * @return ForceData {@link ForceData}
     * @throws IllegalParameterException
     * @throws DataParseException
     */
    @Override
    public ForceData findSaleRelationByBatchCode(String batchCode) throws IllegalParameterException, DataParseException {
        return findSaleRelation(1, batchCode);
    }

    private ForceData findSaleRelation(int type, String code) throws IllegalParameterException, DataParseException {
        if (type != 0 && type != 1) {
            throw new DataParseException("服务端异常.");
        }
        if (StringUtils.isNullBlank(code)) {
            throw new IllegalParameterException("参数不能为空.");
        }
        String flag = null;
        try {
            flag = StringUtils.substring(code, 15, 18);
            if (!flag.equals(".3.") && !flag.equals(".2.")) {
                throw new IllegalParameterException("参数非法.");
            }
        } catch (Exception e) {
            throw new IllegalParameterException("参数非法.");
        }
        long num = 0;
        ForceData forceData = null;
        if (type == 0) {//追溯码
            num = zsmQueryExpandMapper.existTraceCode(code);
            if (num <= 0) {
                throw new BusinessException("不存在此记录.");
            }
            //获取数据
            forceData = getByCurrentTraceCode(code);
            return forceData;
        }
        num = zsmQueryExpandMapper.existBatchCode(code);
        if (num <= 0) {
            throw new BusinessException("不存在此记录.");
        }
        //获取数据
        return forceData;
    }

    private void getDownByCurrentBatchCode(List<SaleInfoData> saleInfoData, String batchCode) {
        //检测次批次产品是否进行过合并
        //获取合成后的批次产品
        //获取当前批次产品


    }

    private ForceData getByCurrentTraceCode(String traceCode) {
        ForceData forceData = new ForceData();
        List<SaleInfoData> saleInfoDatas = new ArrayList<SaleInfoData>();
        List<SaleInfoLink> saleInfoLinks = new ArrayList<SaleInfoLink>();
        Map<String, SaleInfoData> map = getSubjectSaleInfoByTraceCode(traceCode);
        SaleInfoData upstream = map.get("upstream");
        SaleInfoData downstream = map.get("downstream");
        SaleInfoData saleInfoData = null;
        if (downstream != null) {
            saleInfoData = downstream;
            saleInfoData.setCategory(1);
            saleInfoDatas.add(saleInfoData);
        } else {
            saleInfoData = upstream;
            saleInfoData.setCategory(1);
            saleInfoDatas.add(upstream);
        }
        upstream(saleInfoDatas, saleInfoLinks, upstream);
        int start = saleInfoDatas.size() - 1;
        downstream(saleInfoDatas, saleInfoLinks, downstream);
        for (SaleInfoLink saleInfoLink : saleInfoLinks) {
            if ((start + "").equals(saleInfoLink.getSource() + "") && saleInfoLink.getTarget().equals("0")) {
                saleInfoLink.setSource("0");
            }
        }
        forceData.setData(saleInfoDatas);
        forceData.setLinks(saleInfoLinks);
        return forceData;
    }

    private void upstream(List<SaleInfoData> saleInfoDatas, List<SaleInfoLink> saleInfoLinks, SaleInfoData subjectSaleInfoDto) {
        int i = saleInfoDatas.size();
        List<SaleInfoData> list = subjectSaleInfoDto.getChildren();
        if (list != null && list.size() != 0) {
            for (int j = 0; j < list.size(); j++) {
                saleInfoDatas.add(list.get(j));
                saleInfoLinks.add(new SaleInfoLink(i + "", (i - 1) + ""));
                upstream(saleInfoDatas, saleInfoLinks, list.get(j));
            }
        }
    }

    private void downstream(List<SaleInfoData> saleInfoDatas, List<SaleInfoLink> saleInfoLinks, SaleInfoData subjectSaleInfoDto) {
        int i = saleInfoDatas.size();
        if (subjectSaleInfoDto == null) {
            return;
        }
        List<SaleInfoData> list = subjectSaleInfoDto.getChildren();
        if (list != null && list.size() != 0) {
            for (int j = 0; j < list.size(); j++) {
                saleInfoDatas.add(list.get(j));
                saleInfoLinks.add(new SaleInfoLink((i - 1) + "", (saleInfoDatas.size() - 1) + ""));
                downstream(saleInfoDatas, saleInfoLinks, list.get(j));
            }

        }
    }

    /**
     * 通过追溯码获取主体销售信息
     *
     * @param traceCode
     * @return
     */
    @Override
    public Map<String, SaleInfoData> getSubjectSaleInfoByTraceCode(String traceCode) {
        Map<String, SaleInfoData> subjectSaleInfoDtos = new HashMap<String, SaleInfoData>();
        //上游末节点（采购对象）
        SaleInfoData subjectSaleInfoDto = new SaleInfoData();
        //采购主体
        TtsScltxxcjCgglAndCustomer ttsScltxxcjCgglAndCustomer = zsmQueryExpandMapper.getCgSubjectInfoByTraceCode(traceCode);
        if (ttsScltxxcjCgglAndCustomer == null) {
            logger.info("追溯码{}不存在！", traceCode);
            return subjectSaleInfoDtos;
        }

        subjectSaleInfoDto.setSubjectName(ttsScltxxcjCgglAndCustomer.getName());
        subjectSaleInfoDto.setSubjectAddress(ttsScltxxcjCgglAndCustomer.getAddress());
        subjectSaleInfoDto.setLegalName(ttsScltxxcjCgglAndCustomer.getUserName());
        subjectSaleInfoDto.setContactInformation(ttsScltxxcjCgglAndCustomer.getPhone());
        subjectSaleInfoDto.setName(ttsScltxxcjCgglAndCustomer.getName());

        //销售主体
        TtsScltxxcjXsdjAndCustomer ttsScltxxcjXsdjAndCustomer = zsmQueryExpandMapper.getXsSubjectInfoByTraceCode(traceCode);
        //上游树对象
        SaleInfoData xsSubjectInfo = getXsSubjectInfoByTraceCode(ttsScltxxcjXsdjAndCustomer);
        subjectSaleInfoDto.getChildren().add(xsSubjectInfo);
        //下游树对象
        SaleInfoData cgSubjectInfo = getCgSubjectInfoByTraceCode(ttsScltxxcjCgglAndCustomer);
        subjectSaleInfoDtos.put("rootUp", subjectSaleInfoDto);
        subjectSaleInfoDtos.put("rootDown", cgSubjectInfo);
        return subjectSaleInfoDtos;
    }

    /**
     * 获取采购者向下销售
     *
     * @param ttsScltxxcjCgglAndCustomer
     * @return
     */
    private SaleInfoData getCgSubjectInfoByTraceCode(TtsScltxxcjCgglAndCustomer ttsScltxxcjCgglAndCustomer) {
        //检测ttsScltxxcjCgglAndCustomer是否进行过合并
        SaleInfoData subjectSaleInfoDto = null;
        TtsScltxxcjScgl productionProductInfo = zsmQueryExpandMapper.getProductionProductInfo(ttsScltxxcjCgglAndCustomer.getFromzsm());
        if (productionProductInfo == null) {
            logger.info("来源追溯码为{}的数据异常！", ttsScltxxcjCgglAndCustomer.getFromzsm());
            return subjectSaleInfoDto;
        }
        TtsScltxxcjScgl ttsScltxxcjScgl = zsmQueryExpandMapper.getSynthesisProductInfo(productionProductInfo.getFromzsm());
        //采购对象信息（流通）
        List<Map<String, Object>> cgSubjectInfos = null;
        //入市对象信息
        List<Map<String, Object>> rsSubjectInfos = null;
        if (ttsScltxxcjScgl == null) {//未合并
            cgSubjectInfos = zsmQueryExpandMapper.getCgSubjectInfoByBatchCode(productionProductInfo.getProductPc());
            rsSubjectInfos = zsmQueryExpandMapper.getRsSubjectInfoByBatchCode(productionProductInfo.getProductPc());
        } else {
            cgSubjectInfos = zsmQueryExpandMapper.getCgSubjectInfoByBatchCode(ttsScltxxcjScgl.getProductPc());
            rsSubjectInfos = zsmQueryExpandMapper.getRsSubjectInfoByBatchCode(ttsScltxxcjScgl.getProductPc());
        }

        if ((cgSubjectInfos == null || cgSubjectInfos.size() == 0) && (rsSubjectInfos == null || rsSubjectInfos.size() == 0)) {
            return subjectSaleInfoDto;
        }

        //销售主体信息
        subjectSaleInfoDto = new SaleInfoData();
        Map<String, Object> subjectInfo = null;
        if (cgSubjectInfos != null && cgSubjectInfos.size() != 0) {
            subjectInfo = cgSubjectInfos.get(0);
        } else {
            subjectInfo = rsSubjectInfos.get(0);
        }

        subjectSaleInfoDto.setProductBatchCode((String) subjectInfo.get("BATCHCODE"));
        subjectSaleInfoDto.setProductName(ttsScltxxcjCgglAndCustomer.getProductName());
        subjectSaleInfoDto.setProductType(ttsScltxxcjCgglAndCustomer.getProductSort());
        BigDecimal salesNum = new BigDecimal(0);
        for (Map<String, Object> map : cgSubjectInfos) {
            BigDecimal saleNum = (BigDecimal) map.get("SALEAMOUNT");
            salesNum = salesNum.add(saleNum);
        }
        for (Map<String, Object> map : rsSubjectInfos) {
            BigDecimal saleNum = (BigDecimal) map.get("SALEAMOUNT");
            salesNum = salesNum.add(saleNum);
        }
        subjectSaleInfoDto.setSalesNum(salesNum);
        subjectSaleInfoDto.setSalesUnit((String) subjectInfo.get("UNITNAME"));
        subjectSaleInfoDto.setQualityInspection(ttsScltxxcjCgglAndCustomer.getZjresult());
        subjectSaleInfoDto.setSubjectIdentityCode(ttsScltxxcjCgglAndCustomer.getEntityIdcode());
        subjectSaleInfoDto.setSubjectName(ttsScltxxcjCgglAndCustomer.getName());
        subjectSaleInfoDto.setSubjectAddress(ttsScltxxcjCgglAndCustomer.getAddress());
        subjectSaleInfoDto.setLegalName(ttsScltxxcjCgglAndCustomer.getUserName());
        subjectSaleInfoDto.setContactInformation(ttsScltxxcjCgglAndCustomer.getPhone());
        subjectSaleInfoDto.setName(ttsScltxxcjCgglAndCustomer.getName());

        if (rsSubjectInfos != null && rsSubjectInfos.size() != 0) {//入市节点
            Set<String> customerIds = new HashSet<String>();
            //为当前主体添加入市末节点
            for (Map<String, Object> map : rsSubjectInfos) {
                customerIds.add((String) map.get("CUSTOMERID"));
            }
            List<String> ids = new ArrayList<String>();
            ids.addAll(customerIds);
            List<Map<String, Object>> subjectInfos = zsmQueryExpandMapper.getRsSubjectInfoByIds(ids);
            for (Map<String, Object> info : subjectInfos) {
                SaleInfoData dto = new SaleInfoData();
                dto.setSubjectName((String) info.get("NAME"));
                dto.setSubjectAddress((String) info.get("ADDRESS"));
                dto.setLegalName((String) info.get("USERNAME"));
                dto.setContactInformation((String) info.get("PHONE"));
                dto.setName((String) info.get("NAME"));
                subjectSaleInfoDto.getChildren().add(dto);
            }
        }

        if (cgSubjectInfos != null && cgSubjectInfos.size() != 0) {//流通销售
            //递归采购企业
            for (Map<String, Object> map : cgSubjectInfos) {
                String entityIdCode = (String) map.get("CUSTOMERENTITYIDCODE");
                String traceCode = (String) map.get("FROMZSM");
                //采购主体
                TtsScltxxcjCgglAndCustomer cg = zsmQueryExpandMapper.getCgSubjectInfoByTraceCode(traceCode);
                SaleInfoData subjectSaleInfoDto1 = getCgSubjectInfoByTraceCode(cg);
                //为当前主体添加流通节点
                if (subjectSaleInfoDto1 == null) {//不存在流通入市
                    Map<String, Object> entityInfo = zsmQueryExpandMapper.getSubjectInfoByEntityIdCode(entityIdCode);
                    SaleInfoData dto = new SaleInfoData();
                    dto.setSubjectName((String) entityInfo.get("ENTITYNAME"));
                    dto.setSubjectAddress((String) entityInfo.get("ADDRESS"));
                    dto.setLegalName((String) entityInfo.get("LEGALNAME"));
                    dto.setContactInformation((String) entityInfo.get("LEGALPHONE"));
                    dto.setName((String) entityInfo.get("ENTITYNAME"));
                    subjectSaleInfoDto.getChildren().add(dto);
                } else {
                    subjectSaleInfoDto.getChildren().add(subjectSaleInfoDto1);
                }
            }
        }

        return subjectSaleInfoDto;
    }

    /**
     * 获取销售者上游
     *
     * @param ttsScltxxcjXsdjAndCustomer
     * @return
     */
    private SaleInfoData getXsSubjectInfoByTraceCode(TtsScltxxcjXsdjAndCustomer ttsScltxxcjXsdjAndCustomer) {
        SaleInfoData subjectSaleInfoDto = new SaleInfoData();
        //获取批次产品信息
        List<TtsScltxxcjScgl> ttsScltxxcjScgls = zsmQueryExpandMapper.getScglInfos(ttsScltxxcjXsdjAndCustomer.getId());
        //封装销售主体信息
        StringBuffer batchCode = new StringBuffer();
        StringBuffer zjresult = new StringBuffer();
        for (TtsScltxxcjScgl ttsScltxxcjScgl : ttsScltxxcjScgls) {
            batchCode.append(ttsScltxxcjScgl.getProductPc()).append(";");
            zjresult.append(ttsScltxxcjScgl.getMediResult()).append(";");
        }
        subjectSaleInfoDto.setProductBatchCode(batchCode.toString().substring(0, batchCode.toString().length() - 1));
        subjectSaleInfoDto.setProductName(ttsScltxxcjXsdjAndCustomer.getProductName());
        subjectSaleInfoDto.setProductType(ttsScltxxcjXsdjAndCustomer.getProductSort());
        subjectSaleInfoDto.setSalesNum(ttsScltxxcjXsdjAndCustomer.getSaleAmount());
        subjectSaleInfoDto.setSalesUnit(ttsScltxxcjXsdjAndCustomer.getHarvestUnit());
        subjectSaleInfoDto.setQualityInspection(zjresult.toString().substring(0, zjresult.toString().length() - 1));
        subjectSaleInfoDto.setSubjectIdentityCode(ttsScltxxcjXsdjAndCustomer.getEntityIdcode());
        subjectSaleInfoDto.setSubjectName(ttsScltxxcjXsdjAndCustomer.getName());
        subjectSaleInfoDto.setSubjectAddress(ttsScltxxcjXsdjAndCustomer.getAddress());
        subjectSaleInfoDto.setLegalName(ttsScltxxcjXsdjAndCustomer.getUserName());
        subjectSaleInfoDto.setContactInformation(ttsScltxxcjXsdjAndCustomer.getPhone());
        subjectSaleInfoDto.setName(ttsScltxxcjXsdjAndCustomer.getName());

        for (TtsScltxxcjScgl ttsScltxxcjScgl : ttsScltxxcjScgls) {
            String productionId = ttsScltxxcjScgl.getId();
            String fromTraceCode = ttsScltxxcjScgl.getFromzsm();
            //判断本批次产品是否是合成产品
            //获取合成前产品
            List<TtsScltxxcjScgl> ttsScltxxcjScgls1 = zsmQueryExpandMapper.getHcScglInfos(productionId);
            if (ttsScltxxcjScgls1 == null || ttsScltxxcjScgls1.size() == 0) {//本批次产品不是合成产品
                //判断是否是采购
                //获取销售对象信息
                TtsScltxxcjXsdjAndCustomer ttsScltxxcjXsdjAndCustomer1 = zsmQueryExpandMapper.getXsSubjectInfoByTraceCode(fromTraceCode);
                if (ttsScltxxcjXsdjAndCustomer1 == null) {//非采购
                    return subjectSaleInfoDto;
                }
                SaleInfoData subjectSaleInfoDto1 = getXsSubjectInfoByTraceCode(ttsScltxxcjXsdjAndCustomer1);
                subjectSaleInfoDto.getChildren().add(subjectSaleInfoDto1);
            } else {
                for (TtsScltxxcjScgl ttsScltxxcjScgl1 : ttsScltxxcjScgls1) {
                    //判断是否是采购
                    //获取销售对象信息
                    TtsScltxxcjXsdjAndCustomer ttsScltxxcjXsdjAndCustomer1 = zsmQueryExpandMapper.getXsSubjectInfoByTraceCode(ttsScltxxcjScgl1.getFromzsm());
                    if (ttsScltxxcjXsdjAndCustomer1 == null) {//非采购
                        return subjectSaleInfoDto;
                    }
                    SaleInfoData subjectSaleInfoDto1 = getXsSubjectInfoByTraceCode(ttsScltxxcjXsdjAndCustomer1);
                    subjectSaleInfoDto.getChildren().add(subjectSaleInfoDto1);
                }
            }
        }

        return subjectSaleInfoDto;
    }
}
