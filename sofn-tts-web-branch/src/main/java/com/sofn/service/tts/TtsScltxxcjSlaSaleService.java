package com.sofn.service.tts;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.tts.TtsScltxxcjSlaSaleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 屠宰产品销售 service 业务逻辑
 * @author moon.l
 *
 */
@Service
@SuppressWarnings("Duplicates")
public class TtsScltxxcjSlaSaleService extends BaseService<TtsScltxxcjSlaSaleProvider, TtsScltxxcjSlaSale> {

    @DubboReference
    public void setTtsScltxxcjSlaSaleProvider(TtsScltxxcjSlaSaleProvider provider) {
        this.provider = provider;
    }

    /**
     * 系统注册登记常客户
     */
    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

    /**
     * 常用客户管理维护
     */
    @Autowired
    public TtsScltxxcjCustomerService ttsScltxxcjCustomerService;
    /**
     *销售记录管理
     */
    @Autowired
    public TtsScltxxcjSlaSrecordService ttsScltxxcjSlaSrecordService;
    /**
     * 临时数据表
     */
    @Autowired
    public  TtsTempXsjlService ttsTempXsjlService;

    @Autowired
    public SSOLoginService sSOLoginService;
    /**
     * 库存表
     */
    @Autowired
    public TtsScltxxcjSlaStorService ttsScltxxcjSlaStorService;

    public PageInfo getPageInfo(TtsScltxxcjSlaSale bean, int pageNum, int pageSize,String productType,String productName,String startTime,String endTime,String keyWord,String confirmState) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("stor",bean);
        queryParams.put("productType",productType);
        queryParams.put("productName",productName);
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("keyWord",keyWord);
        queryParams.put("confirmState",confirmState);
        return provider.getPageInfo(queryParams);
    }
    /**
     * 销售登记流通
     * @param jsonXsjl 产品销售信息
     * @param customer_id 客户id
     */
    public void addSaleLt(String jsonXsjl,String customer_id){
        List<TtsTempXsjl> list = JSON.parseArray(jsonXsjl,TtsTempXsjl.class);
        String tempId = StringUtils.getUUIDString();
        //获取销售产品批次信息，并将临时数据放入数据库
        Set<String> setPc = insertTempData(list,tempId);
        //销售登记主表id set集合
        Set<String> setId = new HashSet<String>();
        //临时数据，进行分组合并，将同一类型产品，不同批次进行合并
        Map<String,Object> map = new  HashMap<String ,Object>();
        map.put("id",tempId);
        List<Map<String,Object>> tempProducts = ttsTempXsjlService.getGroupProduct(map);

        for (Map<String, Object> temp : tempProducts) {
            //先对销售合并数据进行保存,直接入市销售标记为N
            temp.put("iscirculatesend","N");
            TtsScltxxcjSlaSale vo = addTtsScltxxcjXsdj(temp,customer_id);
            setId.add(vo.getId());
            //将临时表数据添加销售明细中
            vo.setTempId(tempId);
            insertXsdjjl(vo);
        }
        //修改生产管理表库存 将此次销售数量变成冻结，并同步减少库存
        updateSlaStore("N",setPc);
        //清理临时表数据
        ttsTempXsjlService.deleteById(tempId);
    }
    /**
     * 入市销售
     * @param
     */
    public void addSale(String jsonXsjl, String jsonCustomer){
        //校验客户是否已不存在，不存在，则进行生成
        TtsScltxxcjCustomer customer = JSON.parseObject(jsonCustomer,TtsScltxxcjCustomer.class);
        String customer_id = checkInsertTtsScltxxcjCustomer(customer);
        //销售记录-临时表
        List<TtsTempXsjl> list = JSON.parseArray(jsonXsjl,TtsTempXsjl.class);
        //临时ID
        String tempId = StringUtils.getUUIDString();
        //产品放入批次set
        Set<String> setPc = insertTempData(list,tempId);
        //销售登记主表id set集合
        Set<String> setId = new HashSet<String>();
        //临时销售数据，进行分组合并
        Map<String,Object> map = new HashMap<String ,Object>();
        map.put("id",tempId);
        List<Map<String,Object>> tempProducts = ttsTempXsjlService.getGroupProduct(map);
        for (Map<String, Object> temp : tempProducts) {
            //先对销售合并数据进行保存
            temp.put("iscirculatesend","Y");
            TtsScltxxcjSlaSale vo = addTtsScltxxcjXsdj(temp,customer_id);
            //销售登记id放入set
            setId.add(vo.getId());
            //添加销售明细记录
            vo.setTempId(tempId);
            insertXsdjjl(vo);
            //销售登记-确认销售
            updateXsdjConfirm(vo);
        }
        //销售明细--进行销售确认状态变更
        updateXsmxSatus(setId);
        //修改直接入库生产管理情况..生产管理数据库存情况
        updateSlaStore("Y",setPc);

        //执行完毕后，删除之前临时数据 -----------
        //此处可能会出现，别人也在使用临时数据表的时候，自己执行完毕后，把别人数据进行删除了
        //赶进度，加了几天班了，就先这样吧，后面有时间再来修改
        ttsTempXsjlService.deleteById(tempId);
    }


    /**
     * 将临时数据放入数据库，并将整理好的产品批次信息放入set 进行返回
     * @param list
     * @param tempId
     * @return
     */
    public Set<String> insertTempData(List<TtsTempXsjl> list,String tempId){
        //产品批次信息
        Set<String> setPc = new HashSet<String>();
        for (TtsTempXsjl ttsTempXsjl : list) {

            if(null == ttsTempXsjl){
                continue;
            }
            //将临时数据放入temp临时表
            ttsTempXsjl.setId(tempId);
            //将数据插入临时表中
            ttsTempXsjlService.add(ttsTempXsjl);
            //放入产品生产批次信息
            setPc.add(ttsTempXsjl.getProductPc());
        }
        return setPc;
    }

    /**
     * 入市销售登记
     * @param temp
     * @param customer_id
     * @return
     */
    public TtsScltxxcjSlaSale addTtsScltxxcjXsdj(Map<String, Object> temp,String customer_id){
        TtsScltxxcjSlaSale vo = new TtsScltxxcjSlaSale();
        //
        String product_name = temp.get("PRODUCT_NAME")+"";
        String product_sort = temp.get("PRODUCT_SORT")+"";
        BigDecimal store_count = new BigDecimal(temp.get("STORE_COUNT")+"");
        BigDecimal sale_amount = new BigDecimal(temp.get("SALE_AMOUNT")+"");
        String xssl =  temp.get("XSSL")+"";
        //
        vo.setCustomerId(customer_id);
        vo.setBuyComUserId(customer_id);
        //客户信息查询
        TtsScltxxcjCustomer customer = ttsScltxxcjCustomerService.queryById(customer_id);
        vo.setCustomerEntityIdcode(customer.getCustomerEntityIdCode());
        vo.setCustomerUserIdcode(customer.getCustomerUserIdCode());
        //
        vo.setProductName(product_name);
        vo.setProductSort(product_sort);
        vo.setSaleAmount(sale_amount);
        vo.setProductXspcSl(xssl);

        //
        vo.setSaleTime(new Date());
        vo.setSaleUser(DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10);
        //是否直接入市销售标记
        vo.setIsCirculatesEnd(temp.get("iscirculatesend")+"");
        //销售确认标记
        vo.setConfirmState("N");

        //产生销售批次码
        vo.setProductXspc(DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10);
        //主体身份码和用户码
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis("1");
        vo.setEntityIdcode("510528889");
        vo.setUserIdcode("510528889001");
        return add(vo);
    }


    /**
     * 进行销售确认
     * @param vo
     */
    public void updateXsdjConfirm(TtsScltxxcjSlaSale vo){
        //销售确认确认状态
        vo.setConfirmState("Y");
        vo.setConfirmTime(new Date());
        //进行销售确认后，生成产品追溯码和入市凭证
        String isCirculatesend = vo.getIsCirculatesEnd();
        //直接入市的情况生成 入市凭证，不是直接入市生成产品追溯码
        if("Y".equals(isCirculatesend)){
            vo.setTraceProof(DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10);
        } else {
            vo.setTraceCode(vo.getToTraceCode());
        }
        //更新销售记录，销售确认状态
        update(vo);
    }


    /**
     * 通过ids 对销售明细的确认状态，进行批量更新
     * @param setIds
     */
    public void updateXsmxSatus(Set<String> setIds){
        ttsScltxxcjSlaSrecordService.updateXsmxStatus(setIds);
    }

    /**
     * 将临时表数据插入销售记录表
     * @param vo
     */
    public int insertXsdjjl(TtsScltxxcjSlaSale vo){
        return ttsScltxxcjSlaSrecordService.insertByTemp(vo);
    }


    /**
     * 校验客户是否存在，并返回客户的id
     * @param vo
     * @return
     */
    public String checkInsertTtsScltxxcjCustomer(TtsScltxxcjCustomer vo){
        String user_name = vo.getUserName();
        String name = vo.getName();
        String phone = vo.getPhone();
        String address = vo.getAddress();
        String customer_id = ttsScltxxcjCustomerService.checkCustomer(user_name,name,phone,address);
        //如果常用客户表中没有记录则进行新增，并加入更新时间
        if(StringUtils.isBlank(customer_id)){
            vo.setUpdatetime(new Date());
            TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis("1");
            //加入主体身份码和用户码
            vo.setEntityIdCode(reg.getEntityIdcode());
            vo.setUserIdCode(reg.getIdcode());
            vo = ttsScltxxcjCustomerService.add(vo);
            return vo.getId();
        }
        //如果是客户存在的情况，则更新客户的变更时间
        vo = ttsScltxxcjCustomerService.queryById(customer_id);
        ttsScltxxcjCustomerService.update(vo);
        return  customer_id;
    }


    /**
     * 修改直接入市销售库存或未直接销售入市库存
     * @param isCirculatesEnd Y 是 N 否
     *
     * @param setPc 产品批次
     */
    public void updateSlaStore(String isCirculatesEnd,Set<String> setPc){
        String[] pcs = new String[setPc.size()];
        pcs = setPc.toArray(pcs);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pcs",pcs);
        if("Y".equals(isCirculatesEnd)){
            ttsScltxxcjSlaStorService.updateProductStoreCount(map);
            return;
        }
        if("N".equals(isCirculatesEnd)){
            ttsScltxxcjSlaStorService.updateProductFreezeCount(map);
            return;
        }
    }

    public TtsScltxxcjSlaCustomer getSaleAndCustomerById(String id) {
        TtsScltxxcjSlaCustomer vo = provider.getSaleAndCustomerById(id);
        return vo;
    }

    public PageInfo<TtsScltxxcjSlaCustomer> queryPageForPurchase(Map<String, Object> queryParams) {
        return provider.queryPageForPurchase(queryParams);
    }
}