package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjScgl;
import com.sofn.model.generator.TtsScltxxcjSlaStor;
import com.sofn.provider.tts.TtsScltxxcjScglProvider;
import com.sofn.provider.tts.TtsScltxxcjSlaStorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 屠宰库存 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjSlaStorService extends BaseService<TtsScltxxcjScglProvider, TtsScltxxcjScgl> {

    @DubboReference
    public void setTtsScltxxcjScglProvider(TtsScltxxcjScglProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public TtsScltxxcjCppchcService ttsScltxxcjCppchcService;
    @Autowired
    public SSOLoginService sSOLoginService;
    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;
    @Autowired
    public TtsScltxxcjSlaRecordService ttsScltxxcjSlaRecordService;


    public PageInfo getPageInfo(TtsScltxxcjScgl bean, int pageNum, int pageSize, String productType, String productName, String startTime, String endTime, String keyWord) {

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("stor",bean);
        queryParams.put("startTime",startTime);
        queryParams.put("endTime",endTime);
        queryParams.put("keyWord",keyWord);
        return provider.getPageInfo(queryParams);
    }


    public void addSlaPchc(String spids,String userId) {
        //拆分产品登记id
        String[] ids = spids.split(";");
        //条件map
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ids",ids);
        //检测是否可以合成
        TtsScltxxcjScgl vo = checkProduct(map,userId);
        //原数据
        List<TtsScltxxcjScgl> list = provider.getSphcByIds(map);
        //对原数据的数据状态进行修改
        Set<String> fromzsmSet =  new HashSet<String>();
        for(TtsScltxxcjScgl yvo : list){
            //原数据状态修改为已合成，单据状态合并
            yvo.setStatus("4");
            yvo.setBillStratrus("2");
            fromzsmSet.add(yvo.getFromzsm());
            //数据状态更新
            super.update(yvo);
        }
        vo.setBillStratrus("1");
        vo.setJoinFlag("3");
        String newzsm = DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10;
        //如果当前合成的数据有追溯码的情况，则合成后数据也要有追溯码
        if(fromzsmSet.size() > 1){
            vo.setFromzsm(newzsm);
            map.put("newzsm",newzsm);
        }
        //更新合成后数据
        super.update(vo);
        //批量插入合成记录
        map.put("hcid",vo.getId());
        map.put("product_pc_new",vo.getProductPc());
        ttsScltxxcjCppchcService.insertBySelect(map);

    }
    /**
     * 校验数据对象，并返回一个新增合成后待使用数据对象
     * @param map
     * @return
     */
    public TtsScltxxcjScgl checkProduct(Map<String,Object> map,String userId){
        TtsScltxxcjScgl vo = new TtsScltxxcjScgl();
        List<Map<String,Object>> list = provider.getCheckProduct(map);
        if(list.size() != 1){
            //提示当前待合成产品
            throw new DataParseException("当前选择的数据库存为空或已被合成过...");
        }
        String product_name = list.get(0).get("PRODUCT_NAME")+"";
        String Product_id = list.get(0).get("PRODUCT_ID")+"";
        String unit = list.get(0).get("HARVEST_UNIT")+"";
        String productType = list.get(0).get("PRODUCT_SORT")+"";
        BigDecimal current_amount = new BigDecimal(list.get(0).get("STORE_COUNT")+"");
        //获取返回的数据，插入合成后的数据
        //获取主体身份码和主体用户码
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        if(StringUtils.isBlank(vo.getEntityIdcode())){
            vo.setEntityIdcode(reg.getEntityIdcode());
        }
        if(StringUtils.isBlank(vo.getUserIdcode())){
            vo.setUserIdcode(reg.getUserIdcode());
        }
        vo.setProductId(Product_id);
        vo.setProductName(product_name);
        vo.setProductAmount(current_amount);
        vo.setProductSort(productType);
        vo.setHarvestUnit(unit);
        vo.setFreezeCount(BigDecimal.valueOf(0));
        //单据状态为合成
        vo.setBillStratrus("1");
        //销售状态为未卖出
        vo.setStatus("1");
        //行业为屠宰
        vo.setProductIndustry("2");
        vo.setHarvestTime(new Date());
        //新增产品登记
        vo = addCppcdj(vo,userId);
        return vo;
    }
    /**
     * 添加产品库存记录
     * @param vo
     * @return
     */
    public TtsScltxxcjScgl addCppcdj(TtsScltxxcjScgl vo,String userId){
        //当前数据状态默认为1
        vo.setStatus("1");
        //首次加入，库存数量为收获数量
        vo.setStoreCount(vo.getProductAmount());
        //生成批次码
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        vo.setEntityIdcode(reg.getEntityIdcode());
        vo.setUserIdcode(reg.getUserIdcode());
        SysCodeConvert productPc = ttsScltxxcjScglService.getProductPc(vo.getEntityIdcode(),vo.getProductId());
        vo.setProductPc(productPc.getCodeLong());
        String productInnerKey = DateUtil.getDateTime("yyyyMMddhhssmm");
        vo.setInsideTraceCode(productInnerKey);
        vo = super.add(vo);
        return vo;
    }
    /**
     * 通过产品的批次号 String[] pcs 来修改对应的产品库存情况
     * @param map
     */
    public void updateProductStoreCount(Map<String, Object> map) {
        provider.updateProductStoreCount(map);
    }

    public void updateProductFreezeCount(Map<String, Object> map) {
        provider.updateProductStoreFreezeCount(map);
    }

    /**
     * 采购管理，通过销售登记id，重算生产管理库存，及销售状态
     * @param id
     */
    public void updateStoreCountForPurchase(String id){
        provider.updateStoreCountForCgqr(id);
    }
    /**
     * set转换为字符串形式
     * @param set
     * @param format
     * @return
     */
    private String SetToString(Set<String> set,String format){
        String result = "";
        for (String str:set) {
            result+=str+format;
        }
        result = result.substring(0,result.length()-1);
        return result;
    }
}