package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.provider.tts.TtsScltxxcjProductProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjProductService extends BaseService<TtsScltxxcjProductProvider, TtsScltxxcjProduct> {

    @Autowired
    public SSOLoginService sSOLoginService;

    @DubboReference
    public void setTtsScltxxcjProductProvider(TtsScltxxcjProductProvider provider) {
        this.provider = provider;
    }
    public PageInfo getPageInfo(TtsScltxxcjProduct bean, int pageNum, int pageSize,TtsScltxxcjRegiter user) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("user",user);
        queryParams.put("product",bean);
        return provider.getPageInfo(queryParams);
    }

    public int updateStatus(TtsScltxxcjProduct ttsScltxxcjProduct){
        return provider.changeStatus(ttsScltxxcjProduct);
    }

    /**
     * 根据所属行业查询产品信息
     * @param ttsScltxxcjProduct
     * @param pageNum 起始页
     * @param pageSize 结束页
     * @return
     */
    public PageInfo<TtsScltxxcjProduct> getProductPageInfo(TtsScltxxcjProduct ttsScltxxcjProduct, int pageNum, int pageSize,TtsScltxxcjRegiter user) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("pro",ttsScltxxcjProduct);
        queryParams.put("user",user);
        return provider.findProList(queryParams);
    }

    /**
     * 根据主体身份码获取产品信息列表
     * @param pageNum
     * @param pageSize
     * @param entityCode
     * @return
     */
    public PageInfo<TtsScltxxcjProduct> queryProByEntityCode(int pageNum, int pageSize,String entityCode){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("entityCode",entityCode);
        return provider.queryProByEntityCode(queryParams);
    }

    public List<TtsScltxxcjScgl> getProType(String id) {
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(id);
        String entityId = reg.getEntityIdcode();
        return  provider.getProType(entityId);
    }
    /**
     * 根据行业类型获取产品信息列表
     * @param pageNum
     * @param pageSize
     * @param industry
     * @param entity_id
     * @param typeName
     * @return
     */
    public PageInfo<TtsScltxxcjProduct> getProductByTerm(int pageNum, int pageSize, String entity_id, String industry, String typeName) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityCode",reg.getEntityIdcode());
        queryParams.put("industry",industry);
        queryParams.put("typeName",typeName);
        return provider.getProductByTerm(queryParams);
    }

    public List<TtsScltxxcjScgl> getProName(String id, String typeName,String industry) {
        Map<String, Object> queryParams = new HashMap<>();
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(id);
        String entityId = reg.getEntityIdcode();
        queryParams.put("entityId",entityId);
        queryParams.put("typeName",typeName);
        queryParams.put("industry",industry);
        return  provider.getProName(queryParams);
    }

    public boolean isExistedPro(TtsScltxxcjProduct ttsScltxxcjProduct){
        return provider.isExistedPro(ttsScltxxcjProduct);
    }

}