package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjXsdj;
import com.sofn.model.generator.TtsScltxxcjXsth;
import com.sofn.model.generator.TtsScltxxcjXsthAndCustomer;
import com.sofn.provider.tts.TtsScltxxcjXsthProvider;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 销售退回 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjXsthService extends BaseService<TtsScltxxcjXsthProvider, TtsScltxxcjXsth> {

    @DubboReference
    public void setTtsScltxxcjXsthProvider(TtsScltxxcjXsthProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjXsth bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }


    /**
     * 销售登记数据，在进行退回的时候，进行数据记录
     * @param vo
     */
    public void insertXsth(TtsScltxxcjXsdj vo,String xsthyy){
        //销售退回记录及原因
        TtsScltxxcjXsth th = new TtsScltxxcjXsth();
        //
        th.setXsjlid(vo.getId());
        th.setProductId(vo.getProductId());
        th.setProductName(vo.getProductName());
        th.setProductSort(vo.getProductSort());
        th.setProductIndustry(vo.getProductIndustry());
        //
        th.setSaleTime(vo.getSaleTime());
        th.setSaleAmount(vo.getSaleAmount());
        th.setSaleUser(vo.getSaleUser());
        //
        th.setProductXspc(vo.getProductXspc());
        th.setProductXspcSl(vo.getProductXspcSl());
        //
        th.setEntityIdcode(vo.getEntityIdcode());
        th.setUserIdcode(vo.getUserIdcode());
        th.setBuyComUserId(vo.getBuyComUserId());
        th.setCustomerEntityIdcode(vo.getCustomerEntityIdcode());
        th.setCustomerUserIdcode(vo.getCustomerUserIdcode());
        th.setSourceEntity(vo.getSourceEntity());
        //
        th.setXsthyy(xsthyy);
        add(th);
    }
    /**
     * 屠宰销售登记数据，在进行退回的时候，进行数据记录
     * @param tvo
     */
    public void insertPurchaseRetreat(TtsScltxxcjSlaSale tvo, String xsthyy) {
        TtsScltxxcjXsth th = new TtsScltxxcjXsth();
        //
        th.setXsjlid(tvo.getId());
        th.setProductId(tvo.getProductId());
        th.setProductName(tvo.getProductName());
        th.setProductSort(tvo.getProductSort());
        th.setProductIndustry(tvo.getProductIndustry());
        //
        th.setSaleTime(tvo.getSaleTime());
        th.setSaleAmount(tvo.getSaleAmount());
        th.setSaleUser(tvo.getSaleUser());
        //
        th.setProductXspc(tvo.getProductXspc());
        th.setProductXspcSl(tvo.getProductXspcSl());
        //
        th.setEntityIdcode(tvo.getEntityIdcode());
        th.setUserIdcode(tvo.getUserIdcode());
        th.setBuyComUserId(tvo.getBuyComUserId());
        th.setCustomerEntityIdcode(tvo.getCustomerEntityIdcode());
        th.setCustomerUserIdcode(tvo.getCustomerUserIdcode());
        th.setXsthyy(xsthyy);
        add(th);
    }

    public TtsScltxxcjXsthAndCustomer queryByXsjlId(String id) {
        return provider.queryByXsjlId(id);
    }

    public TtsScltxxcjXsthAndCustomer querySaleInfoById(String id) {
        return provider.querySaleInfoById(id);
    }
}