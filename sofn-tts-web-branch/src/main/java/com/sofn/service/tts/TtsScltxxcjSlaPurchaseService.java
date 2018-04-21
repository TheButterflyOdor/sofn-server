package com.sofn.service.tts;

import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.generator.*;
import com.sofn.provider.tts.TtsScltxxcjSlaPurchaseProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by Administrator on 2016/11/8 0008.
 */
@Service
public class TtsScltxxcjSlaPurchaseService extends BaseService<TtsScltxxcjSlaPurchaseProvider, TtsScltxxcjSlaPurchase> {

    @DubboReference
    public void setTtsScltxxcjSlaPurchaseProvider(TtsScltxxcjSlaPurchaseProvider provider) {
        this.provider = provider;
    }

    /**
     *销售记录管理
     */
    @Autowired
    public TtsScltxxcjSlaSrecordService ttsScltxxcjSlaSrecordService;

    /**
     * 库存表
     */
    @Autowired
    public TtsScltxxcjSlaStorService ttsScltxxcjSlaStorService;

    /**
     * 销售登记
     */
    @Autowired
    public TtsScltxxcjSlaSaleService ttsScltxxcjSlaSaleService;

    /**
     * 销售退货
     */
    @Autowired
    public TtsScltxxcjXsthService ttsScltxxcjXsthService;
    /**
     * 获取采购列表
     */
    public PageInfo<TtsScltxxcjSlaCustomer> queryPageForPurchase(int pageNum, int pageSize, String confirmState,String isCirculatesEnd) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", ((pageNum+1)/pageSize)+1);
        queryParams.put("pageSize", pageSize);
        queryParams.put("confirmState",confirmState);
        queryParams.put("isCirculatesEnd",isCirculatesEnd);
        return ttsScltxxcjSlaSaleService.queryPageForPurchase(queryParams);
    }
    /**
     * 采购确认
     */
    public void updatePurchase(String id) {
        //确认时，先产生一条追溯码
        String traceCode = DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10;
        //获取销售登记信息
        TtsScltxxcjSlaSale vo = ttsScltxxcjSlaSaleService.queryById(id);
        vo.setToTraceCode(traceCode);
        ttsScltxxcjSlaSaleService.updateXsdjConfirm(vo);
        //将销售明细确认状态和时间加上
        ttsScltxxcjSlaSrecordService.updateXsmxStatus(id);
        //获取销售明细，查询条件
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("delFlag","N");
        map.put("xsdjId",id);
        Set<String> pcs = new HashSet<String>();
        List<TtsScltxxcjSlaSrecord> list = ttsScltxxcjSlaSrecordService.queryBySaleId(map);
        for (TtsScltxxcjSlaSrecord mx : list) {
            pcs.add(mx.getProductScglId());
        }
        ttsScltxxcjSlaStorService.updateStoreCountForPurchase(id);
        //添加采购记录
        TtsScltxxcjSlaPurchase slaPurchase = insertSlaPurchase(vo);
        //产品入库
        insertSaleStor(slaPurchase);
    }
    public void insertSaleStor(TtsScltxxcjSlaPurchase vo){
        TtsScltxxcjSlaStor scgl = new TtsScltxxcjSlaStor();
//        scgl.setProductId(vo.getProductId());
        scgl.setProductName(vo.getProductName());
        scgl.setProductType(vo.getProductSort());
        //
        scgl.setInitAmount(vo.getCgAmount());
        scgl.setHarvestTime(vo.getSaleTime());
//        scgl.setUnit("kg");
//        scgl.setProductSource("采购");
//        scgl.setMediCheck("送检");
//        scgl.setMediResult("合格");
//        scgl.setJoinFlag("1");
        //
        scgl.setFromTraceCode(vo.getToTraceCode());
//        ttsScltxxcjSlaStorService.addCppcdj(scgl);
    }
    /**
     * 根据销售流通记录，在采购确认后，产生采购记录
     * @param vo
     * @return
     */
    public TtsScltxxcjSlaPurchase insertSlaPurchase(TtsScltxxcjSlaSale vo) {
        TtsScltxxcjSlaPurchase cggl = new TtsScltxxcjSlaPurchase();
        //
        cggl.setXsdjid(vo.getId());
        cggl.setProductName(vo.getProductName());
        cggl.setProductSort(vo.getProductSort());
        //采购时间，采购数量，销售人
        cggl.setSaleTime(vo.getSaleTime());
        cggl.setCgAmount(vo.getSaleAmount());
        cggl.setSaleUser(vo.getSaleUser());
        //
        cggl.setProductCgpc(vo.getProductXspc());
        cggl.setProductCgpcSl(vo.getProductXspcSl());
        //采购记录，销售人主体身份码，用户码是销售记录的主体身份码，用户码
        cggl.setSaleEntityIdcode(vo.getEntityIdcode());
        cggl.setSaleUserIdcode(vo.getUserIdcode());
        cggl.setBuyComUserId(vo.getBuyComUserId());
        //采购记录，采购主体用户码是销售记录的客户码
        cggl.setEntityIdcode(vo.getCustomerEntityIdcode());
        cggl.setUserIdcode(vo.getCustomerUserIdcode());

        cggl.setFromTraceCode(vo.getToTraceCode());
        cggl.setProductModel("kg");

        return add(cggl);
    }
    /**
     * 退货
     */
    public void updatePurchaseRetreat(String id, String xsthyy) {
        //将销售登记数据，删除标记，标记为Y
        ttsScltxxcjSlaSaleService.delete(id);
        //根据销售登记id，将销售明细数据，删除标记，标记为Y
        ttsScltxxcjSlaSrecordService.deleteRecordFlag(id);
        //通过销售登记id，修改生产管理库存
        ttsScltxxcjSlaStorService.updateStoreCountForPurchase(id);
        //销售登记记录主体
        TtsScltxxcjSlaSale vo = ttsScltxxcjSlaSaleService.queryById(id);
        ttsScltxxcjXsthService.insertPurchaseRetreat(vo,xsthyy);
    }

    public PageInfo<TtsScltxxcjSlaPurchase> getPurchasePageInfo(TtsScltxxcjSlaPurchase ttsScltxxcjSlaPurchase, int pageNum, int pageSize, String productType, String productName, String startTime, String endTime, String keyWord, String confirmState) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", ((pageNum+1)/pageSize)+1);
        queryParams.put("pageSize", pageSize);
        return provider.getPurchasePageInfo(queryParams);
    }
}
