package com.sofn.provider.qry;

import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.TraceQueryExpandMapper;
import com.sofn.model.qry.ForceData;
import com.sofn.model.qry.SaleInfoData;
import com.sofn.model.qry.SaleInfoLink;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 *
 */
@DubboService(interfaceClass = TraceQueryProvider.class)
public class TraceQueryProviderImpl extends BaseProviderImpl implements TraceQueryProvider {
    @Autowired
    private TraceQueryExpandMapper traceQueryExpandMapper;

    /**
     * 批次码/追溯码查询
     * type非1或2默认为2
     *
     * @param code 追溯或批次码
     * @param type 1树图2力向图
     * @return
     */
    @Override
    public Object selectBytraceOrBatchCode(String code, int type) throws IllegalParameterException, DataParseException {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        IdGenerator.TracingCodeType tracingCodeType = IdGenerator.getTracingCodeType(code);

        if (tracingCodeType == null) {
            throw new IllegalParameterException("参数非法.");
        }

        SaleInfoData saleInfoData = null;
        if (IdGenerator.TracingCodeType.circulate.equals(tracingCodeType)) {//追溯码
            //验证追溯码是否存在
            saleInfoData = traceQueryExpandMapper.findByTraceCode(code);
            if (saleInfoData == null) {
                return null;
            }
        } else if (IdGenerator.TracingCodeType.production.equals(tracingCodeType)) {
            //验证批次码是否存在
            saleInfoData = traceQueryExpandMapper.findByBatchCode(code);
            if (saleInfoData == null) {
                return null;
            }
        } else {
            throw new IllegalParameterException("参数非法.");
        }

        Object object = null;
        switch (type) {
            case 1:
                object = getTreeData(saleInfoData);
                break;
            case 2:
                object = getForceData(saleInfoData);
                break;
            default:
                object = getForceData(saleInfoData);
        }
        return object;
    }

    /**
     * 获取力向图数据结构
     *
     * @param saleInfoData
     * @return
     */
    private ForceData getForceData(SaleInfoData saleInfoData) {
        //上游
        List<SaleInfoData> saleInfoDataList = new LinkedList<SaleInfoData>();
        //下游
        List<SaleInfoData> saleInfoDataListDown = new LinkedList<SaleInfoData>();
        getUpBySaleInfoData(saleInfoDataList, saleInfoData, null);
        getDownBySaleInfoData(saleInfoDataListDown, saleInfoData, null);

        ForceData forceData = new ForceData();
        List<SaleInfoData> data = new ArrayList<SaleInfoData>();
        List<SaleInfoLink> links = new ArrayList<SaleInfoLink>();

        Iterator<SaleInfoData> sListIterator = saleInfoDataList.iterator();
        while (sListIterator.hasNext()) {
            SaleInfoData saleInfoData1 = sListIterator.next();
            String id = saleInfoData1.getId();
            String superId = saleInfoData1.getSuperId();
            if (superId == null) {
                sListIterator.remove();
            } else {
                links.add(new SaleInfoLink(id, superId));
            }
        }

        for (SaleInfoData saleInfoData1 : saleInfoDataListDown) {
            String id = saleInfoData1.getId();
            String superId = saleInfoData1.getSuperId();
            if (superId != null) {
                links.add(new SaleInfoLink(superId, id));
            }
        }

        data.addAll(saleInfoDataList);
        data.addAll(saleInfoDataListDown);

        forceData.setData(data);
        forceData.setLinks(links);

        return forceData;
    }

    /**
     * 获取树形图数据结构
     *
     * @param saleInfoData
     * @return
     */
    private Map<String, SaleInfoData> getTreeData(SaleInfoData saleInfoData) {
        //上游
        List<SaleInfoData> saleInfoDataList = new LinkedList<SaleInfoData>();
        //下游
        List<SaleInfoData> saleInfoDataListDown = new LinkedList<SaleInfoData>();
        getUpBySaleInfoData(saleInfoDataList, saleInfoData, null);
        getDownBySaleInfoData(saleInfoDataListDown, saleInfoData, null);

        Map<String, SaleInfoData> saleInfoDataMap = new HashMap<String, SaleInfoData>();

        // 节点列表（散列表，用于临时存储节点对象）
        Map<String, SaleInfoData> nodeListUp = new HashMap<String, SaleInfoData>();
        Map<String, SaleInfoData> nodeListDown = new HashMap<String, SaleInfoData>();
        // 根节点
        SaleInfoData rootUp = null;
        SaleInfoData rootDown = null;
        for (Iterator<SaleInfoData> it = saleInfoDataList.iterator(); it.hasNext(); ) {
            SaleInfoData saleInfoData1 = it.next();
            if (saleInfoData1.getSuperId() == null || saleInfoData1.getSuperId() == "") {
                nodeListUp.put(saleInfoData1.getId(), saleInfoData1.clone());
            } else {
                nodeListUp.put(saleInfoData1.getId(), saleInfoData1);
            }
        }
        for (Iterator<SaleInfoData> it = saleInfoDataListDown.iterator(); it.hasNext(); ) {
            SaleInfoData saleInfoData1 = it.next();
            nodeListDown.put(saleInfoData1.getId(), saleInfoData1);
        }

        // 构造无序的多叉树
        for (SaleInfoData node : nodeListUp.values()) {
            if (node.getSuperId() == null || node.getSuperId().equals("")) {
                rootUp = node;
            } else {
                nodeListUp.get(node.getSuperId()).getChildren().add(node);
            }
        }
        for (SaleInfoData node : nodeListDown.values()) {
            if (node.getSuperId() == null || node.getSuperId().equals("")) {
                rootDown = node;
            } else {
                nodeListDown.get(node.getSuperId()).getChildren().add(node);
            }
        }

        saleInfoDataMap.put("rootUp", rootUp);
        saleInfoDataMap.put("rootDown", rootDown);
        return saleInfoDataMap;
    }

    /**
     * @param saleInfoDataList 下游结果集
     * @param saleInfoData     当前节点
     * @param superId          父类ID
     */
    private void getDownBySaleInfoData(List<SaleInfoData> saleInfoDataList, SaleInfoData saleInfoData, String superId) {
        //当前批次销售情况
        String id = saleInfoData.getProductBatchCode();
        String name = saleInfoData.getSubjectName();
        saleInfoData.setId(id);
        saleInfoData.setSuperId(superId);
        saleInfoData.setName(id);
        saleInfoData.setLabel(name);
        if (StringUtils.isBlank(superId)) {
            saleInfoData.setCategory(0);//当前节点
        } else {
            saleInfoData.setCategory(1);//流通节点
        }
        saleInfoDataList.add(saleInfoData);

        //查询入市
        List<SaleInfoData> saleInfoDataList1 = traceQueryExpandMapper.findIntoCityByBatchCode(id);
        for (SaleInfoData saleInfoData1 : saleInfoDataList1) {
            String id1 = saleInfoData1.getProductBatchCode();
            String name1 = saleInfoData1.getSubjectName();
            saleInfoData1.setId(id1);
            saleInfoData1.setSuperId(id);
            saleInfoData1.setName(id1);
            saleInfoData1.setLabel(name1);
            saleInfoData1.setCategory(2);//入市节点
            saleInfoDataList.add(saleInfoData1);
        }

        //流通批次信息
        Set<SaleInfoData> saleInfoDatas = new HashSet<SaleInfoData>();


        //查询流通
        List<SaleInfoData> saleInfoDataList2 = traceQueryExpandMapper.findCirculationByBatchCode(id);
        saleInfoDatas.addAll(saleInfoDataList2);

        //当前批次合成后批次销售情况
        SaleInfoData saleInfoData1 = traceQueryExpandMapper.findHcByBatchCode(id);
        if (saleInfoData1 != null) {
            String id1 = saleInfoData1.getProductBatchCode();
            //查询入市
            List<SaleInfoData> saleInfoDataList3 = traceQueryExpandMapper.findIntoCityByBatchCode(id1);
            for (SaleInfoData saleInfoData2 : saleInfoDataList3) {
                String id2 = saleInfoData2.getProductBatchCode();
                String name2 = saleInfoData2.getSubjectName();
                saleInfoData2.setId(id2);
                saleInfoData2.setSuperId(id);
                saleInfoData2.setName(id2);
                saleInfoData2.setLabel(name2);
                saleInfoData2.setCategory(2);//入市节点
                saleInfoDataList.add(saleInfoData2);
            }

            //查询流通
            List<SaleInfoData> saleInfoDataList4 = traceQueryExpandMapper.findCirculationByBatchCode(id1);
            saleInfoDatas.addAll(saleInfoDataList4);
        }

        for (SaleInfoData saleInfoData2 : saleInfoDatas) {
            getDownBySaleInfoData(saleInfoDataList, saleInfoData2, id);
        }
    }

    /**
     * @param saleInfoDataList 上游结果集
     * @param saleInfoData     当前节点
     * @param superId          父类ID
     */
    private void getUpBySaleInfoData(List<SaleInfoData> saleInfoDataList, SaleInfoData saleInfoData, String superId) {
        String id = saleInfoData.getProductBatchCode();
        String name = saleInfoData.getSubjectName();
        saleInfoData.setId(id);
        saleInfoData.setSuperId(superId);
        saleInfoData.setName(id);
        saleInfoData.setLabel(name);
        if (StringUtils.isBlank(superId)) {
            saleInfoData.setCategory(0);//当前节点
        } else {
            saleInfoData.setCategory(1);//流通节点
        }

        //上游批次信息
        Set<SaleInfoData> saleInfoDatas = new HashSet<SaleInfoData>();

        //检验saleInfoData是否进行过合成
        List<SaleInfoData> saleInfoDataList1 = traceQueryExpandMapper.findBeforeHcByBatchCode(saleInfoData.getProductBatchCode());
        if (saleInfoDataList1 == null || saleInfoDataList1.size() == 0) {//不是合成产品
            //获取上游
            List<SaleInfoData> saleInfoDataList2 = traceQueryExpandMapper.findUpByBatchCode(id);
            if (saleInfoDataList2 != null && saleInfoDataList2.size() != 0) {
                saleInfoDatas.addAll(saleInfoDataList2);
            }
        } else {
            for (SaleInfoData saleInfoData1 : saleInfoDataList1) {
                //获取上游
                List<SaleInfoData> saleInfoDataList2 = traceQueryExpandMapper.findUpByBatchCode(saleInfoData1.getProductBatchCode());
                if (saleInfoDataList2 != null && saleInfoDataList2.size() != 0) {
                    saleInfoDatas.addAll(saleInfoDataList2);
                }
            }
        }

        if (saleInfoDatas.size() == 0) {
            saleInfoData.setCategory(3);//最终生产主体节点
        }

        saleInfoDataList.add(saleInfoData);

        for (SaleInfoData saleInfoData1 : saleInfoDatas) {
            getUpBySaleInfoData(saleInfoDataList, saleInfoData1, id);
        }
    }

}
