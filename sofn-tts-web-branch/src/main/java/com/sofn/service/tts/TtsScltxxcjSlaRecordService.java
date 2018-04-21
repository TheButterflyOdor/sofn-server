package com.sofn.service.tts;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.generator.*;
import com.sofn.provider.tts.TtsArgiProduct;
import com.sofn.provider.tts.TtsScltxxcjSlaRecordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 屠宰记录 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjSlaRecordService extends BaseService<TtsScltxxcjSlaRecordProvider, TtsScltxxcjSlaRecord> {

    @DubboReference
    public void setTtsScltxxcjSlaRecordProvider(TtsScltxxcjSlaRecordProvider provider) {
        this.provider = provider;
    }

    /**
     * 库存Service
     */
    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;
    /**
     * 主体登录信息Service
     */
    @Autowired
    public SSOLoginService sSOLoginService;

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;
    @Autowired
    public SysDictionaryService sysDictionaryService;

    public IdGenerator idGenerator = new IdGenerator();

    private String entity_id;

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }
    /**
     * 获取屠宰记录
     * @param bean
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getPageInfo(TtsScltxxcjSlaRecord bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }
    /**
     * 新增屠宰记录（出库）+入库
     */
    public void addSlaRecord(String jsonStr, String ajsonStr,String userId) {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        //生成内部追溯流水号,格式：主题用户码+当前时间
        String currentDate = DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10;
        //在redis缓存中获取当前用户的用户码
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        String userIdcode = reg.getUserIdcode();
        String entityIdCode = reg.getEntityIdcode();
        String userCode = reg.getUserIdcode();
        String insideTraceCode = userCode+currentDate;
        // 将前端传来的屠宰记录信息（json字符串）转换成Java对象数组
        List<TtsScltxxcjScgl> SlaRecordlist = JSON.parseArray(jsonStr, TtsScltxxcjScgl.class);
        //循环添加多条屠宰记录
        for (TtsScltxxcjScgl saleRecord : SlaRecordlist) {
                //屠宰记录对象
                TtsScltxxcjSlaRecord vo = new TtsScltxxcjSlaRecord();
                vo.setName(saleRecord.getProductName());
                vo.setProductPc(saleRecord.getProductPc());
                vo.setSlaughterAmount(saleRecord.getSlaughterAmount());
                vo.setSlaughterTime(nowDate);
                vo.setInsideTraceCode(insideTraceCode);
                String id = saleRecord.getId();
                //初始化为0，不为空则设入数量
                String amount = "0";
                if(null != saleRecord.getSlaughterAmount() && "" != saleRecord.getSlaughterAmount()){
                    amount = saleRecord.getSlaughterAmount();
                }
                super.add(vo);
                //修改生产管理表里面的屠宰前产品的库存
                ttsScltxxcjScglService.updateProductAmount(id,amount);
        }
        //将前端传入的屠宰后产品的json字符串转换为java字符串
        List<TtsArgiProduct> SlaStorlist = JSON.parseArray(ajsonStr, TtsArgiProduct.class);
        //循环加入库存
        for (TtsArgiProduct slaStor : SlaStorlist) {
            //库存对象
            TtsScltxxcjScgl tsScltxxcjScgl = new TtsScltxxcjScgl();
            //检疫合格证号
            tsScltxxcjScgl.setProofNumber(slaStor.getProofNumber());
            //产品名称
            tsScltxxcjScgl.setProductName(slaStor.getName());
            //产品ID
            tsScltxxcjScgl.setProductId(slaStor.getProductCode());
            //产品内部追溯码
            tsScltxxcjScgl.setInsideTraceCode(insideTraceCode);
            //屠宰时间
            tsScltxxcjScgl.setHarvestTime(nowDate);
            //产品单位
            String unitId = slaStor.getHarvestUnit();
            SysDictData unit = sysDictionaryService.getUnitNameById(unitId);
            String harvestUnit = unit.getDictName();

            tsScltxxcjScgl.setHarvestUnit(harvestUnit);
            tsScltxxcjScgl.setUnitId(unitId);
            //产品初始数量
            tsScltxxcjScgl.setProductAmount(slaStor.getSlaughterAmount());
            //产品刚入库时库存数量等于初始数量
            tsScltxxcjScgl.setProductSort(slaStor.getParentName());
            //库存为收获数量
            tsScltxxcjScgl.setStoreCount(slaStor.getSlaughterAmount());
            //产品标识
            tsScltxxcjScgl.setJoinFlag("3");
            //主体身份码
            tsScltxxcjScgl.setEntityIdcode(reg.getEntityIdcode());
            //主体用户码
            tsScltxxcjScgl.setUserIdcode(reg.getUserIdcode());
            //产品批次码
            SysCodeConvert productPc = ttsScltxxcjScglService.getProductPc(entityIdCode,tsScltxxcjScgl.getProductId());
            tsScltxxcjScgl.setProductPc(productPc.getCodeLong());
            tsScltxxcjScgl.setShortCode(productPc.getCodeShort());
            //来源追溯标识，存入批次短码
            tsScltxxcjScgl.setSourceEntity(productPc.getCodeShort());
            //初始状态为未卖出
            tsScltxxcjScgl.setStatus("1");
            //初始单据状态为可合成
            tsScltxxcjScgl.setBillStratrus("0");
            //初始冻结数量为0
            tsScltxxcjScgl.setFreezeCount(BigDecimal.valueOf(0));
            //行业类型为2：肉类
            tsScltxxcjScgl.setProductIndustry("02");
            tsScltxxcjScgl.setMediCheck("自检");
            tsScltxxcjScgl.setMediResult("合格");
            tsScltxxcjScgl.setProductSource("屠宰");
            //加入库存
            ttsScltxxcjScglService.add(tsScltxxcjScgl);

        }
    }

    public PageInfo<Map<String,Object>> findById(String id,int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("id",id);
        return provider.findById(queryParams);
    }

}