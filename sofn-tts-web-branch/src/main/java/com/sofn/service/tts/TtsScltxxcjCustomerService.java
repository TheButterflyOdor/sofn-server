package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjCustomer;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.provider.tts.TtsScltxxcjCustomerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户信息管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjCustomerService extends BaseService<TtsScltxxcjCustomerProvider, TtsScltxxcjCustomer> {

    @DubboReference
    public void setTtsScltxxcjCustomerProvider(TtsScltxxcjCustomerProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public SSOLoginService ssoLoginService;

    public PageInfo getPageInfo(TtsScltxxcjCustomer bean, int pageNum, int pageSize,TtsScltxxcjRegiter user) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("customer",bean);
        queryParams.put("user",user);
        return provider.getPageInfo(queryParams);
    }


    public PageInfo getPageInfoForEntryId(TtsScltxxcjCustomer bean, int pageNum, int pageSize,TtsScltxxcjRegiter user,String isrs,String userName) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("customer",bean);
        queryParams.put("user",user);
        queryParams.put("isrs",isrs);
        queryParams.put("userName",userName);
        return provider.getPageInfo(queryParams);
    }

    /**
     * 获取登录账户主体的常客户信息 常客户信息 身份码是非空
     * @param id
     * @return
     */
    public List<TtsScltxxcjCustomer> getCustomerList(String id){
        Map<String,Object> map = new HashMap<String,Object>();
        TtsScltxxcjRegiter reg = ssoLoginService.getEntityByRedis(id);
        //
        map.put("entityIdCode",reg.getEntityIdcode());
        return provider.getCustomerList(map);
    }

    /**
     * 获取登录账户主体的常客户信息 常客户信息 身份码是空
     * @param id
     * @return
     */
    public List<TtsScltxxcjCustomer> getCusromerListNotCustomerEntityID(String id){
        Map<String,Object> map = new HashMap<String,Object>();
        TtsScltxxcjRegiter reg = ssoLoginService.getEntityByRedis(id);
        //
        map.put("entityIdCode",reg.getEntityIdcode());
        return provider.getCusromerListNotCustomerEntityID(map);
    }

    public void updateDelFlag(String id) {
        provider.editDelFlag(id);
    }

    public void batchUpdateDelFlag(String[] ids) {
        provider.batchEditDelFlag(ids);
    }

    /**
     * 校验当前客户是否存在  注：客户的主体身份码和用户码必须为空
     * @param userName
     * @param name
     * @param phone
     * @param address
     * @return
     */
    public String checkCustomer(String userName,String name,String phone,String address){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName",userName);
        map.put("name",name);
        map.put("phone",phone);
        map.put("address",address);
        TtsScltxxcjCustomer vo = provider.checkCustomer(map);
        if(null == vo){
            return "";
        }
        return vo.getId();
    }


    /**
     * 查询客户信息是否存在
     * @param map
     * @return
     */
    public TtsScltxxcjCustomer checkCustomer(Map<String,Object> map){
        TtsScltxxcjCustomer vo = provider.checkCustomer(map);
        return vo;
    }
}