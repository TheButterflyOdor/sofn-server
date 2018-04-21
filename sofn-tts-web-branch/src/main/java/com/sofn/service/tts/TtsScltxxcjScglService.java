package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.generator.*;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysTemplateProvider;
import com.sofn.provider.tts.TtsScltxxcjScglProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 生产管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjScglService extends BaseService<TtsScltxxcjScglProvider, TtsScltxxcjScgl> {

    @DubboReference
    public void setTtsScltxxcjScglProvider(TtsScltxxcjScglProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public TtsScltxxcjBaseService tscltxxcjBaseInfoService;

    @Autowired
    public TtsScltxxcjCppchcService ttsScltxxcjCppchcService;

    @Autowired
    public SSOLoginService sSOLoginService;

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

    @Autowired
    public SysDictionaryService sysDictionaryService;

    @DubboReference
    public SysCodeGeneratorProvider sysCodeGeneratorProvider;

    @DubboReference
    public SysDictProvider sysDictProvider;

    @DubboReference
    public SysTemplateProvider sysTemplateProvider;

    public IdGenerator idGenerator = new IdGenerator();


    private String entity_id;

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public PageInfo getPageInfo(TtsScltxxcjScgl bean, int pageNum, int pageSize,String entity_id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
    }

    /**
     * 批次合成信息列表获取
     * @param bean
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getPageInfoPchc(TtsScltxxcjScgl bean, int pageNum, int pageSize,
                                    String productName,String harvestUnit,String entity_id,String productSort,String shrq_q, String shrq_h) {
        Map<String, Object> queryParams = new HashMap<>();
        //合成后的数据不能再进行合成，0 表示为加入产品登记后，未进行任何合成操作的
        queryParams.put("billStratrus","0");
        //检测结果必须是合格
        queryParams.put("mediResult", "合格");
        //进行合成的时候，不允许有冻结情况，任意标记，表示验证冻结数量是否为0
        queryParams.put("freezeCount", 1);
        queryParams.put("zero", "1");
        queryParams.put("productName",productName);
        queryParams.put("harvestUnit",harvestUnit);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("productSort",productSort);
        queryParams.put("shrq_q",shrq_q);
        queryParams.put("shrq_h",shrq_h);
        //只允许种植业产品
        //queryParams.put("noJoinFlag","3");
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
    }

    /**
     * 产品登记信息管理
     * @param bean
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getCpdjgl(TtsScltxxcjScgl bean, int pageNum, int pageSize,
                              String productName,String status,
                              String productSort,String shrq_q,String shrq_h,String entity_id,String harvestUnit,String keyWord) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("productName",productName);
        queryParams.put("status",status);
        queryParams.put("productSort",productSort);
        queryParams.put("harvestUnit",harvestUnit);
        queryParams.put("keyWord",keyWord);
        //收获日期区间范围
        queryParams.put("shrq_q",shrq_q);
        queryParams.put("shrq_h",shrq_h);
        //合成后的数据不能再进行合成，就不显示了
        queryParams.put("noBillStratrus","2");
        //不允许屠宰产品在此合成
//        queryParams.put("noJoinFlag","3");
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
    }

    /**
     * 销售列表
     * @param bean
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getSaleList(TtsScltxxcjScgl bean, int pageNum, int pageSize,
                              String productName,String status,
                              String productSort,String shrq_q,String shrq_h,
                                String entity_id, String keyWord) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("productName",productName);
        queryParams.put("status",status);
        queryParams.put("productSort",productSort);
        queryParams.put("zero","1");
        //收获日期区间范围
        queryParams.put("shrq_q",shrq_q);
        queryParams.put("shrq_h",shrq_h);
        //合成后的数据不能再进行合成，就不显示了
        queryParams.put("noBillStratrus","2");
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("keyWord", keyWord);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
}
    /**
     * 添加产品批次登记(成都市对接)
     * @param vo
     * @return
     */
    public TtsScltxxcjScgl addProductForSc(TtsScltxxcjScgl vo,String entityIdCode,String userIdcode,String joinFlag){
        //当前数据状态默认为1表示产品未卖出
        vo.setStatus("1");
        //表示当前单据状态为初始状态
        vo.setBillStratrus("0");
        //加入标记是1 表示种植业
        vo.setJoinFlag(joinFlag);
        //获取单位名称
        String name = vo.getHarvestUnit();
        SysDictData unit = sysDictionaryService.getIdByName(name);
        String unitId = unit.getId();
        vo.setUnitId(unitId);
        //首次加入，如果库存为空的情况，库存数量为收获数量
        if(vo.getStoreCount() == null || BigDecimal.ZERO.equals(vo.getStoreCount())){
            vo.setStoreCount(vo.getProductAmount());
        }
        vo.setFreezeCount(BigDecimal.ZERO);

        String productInnerKey = DateUtil.getDateTime("yyyyMMddhhssmm");
        vo.setProductInnerKey(productInnerKey);
        //获取用户信息
        vo.setEntityIdcode(entityIdCode);
        vo.setUserIdcode(userIdcode);
        //获取产品批次码
        SysCodeConvert productPc = getProductPc(vo.getEntityIdcode(),vo.getProductId());
        //长码
        vo.setProductPc(productPc.getCodeLong());
        //短码
        vo.setShortCode(productPc.getCodeShort());
        //来源标识，此处存入批次码作为唯一标识
        if(null == vo.getSourceEntity() || "" == vo.getSourceEntity()){
            vo.setSourceEntity(productPc.getCodeShort());
        }
        //入库
        vo = super.add(vo);
        return vo;
    }
    /**
     * 添加产品批次登记
     * @param vo
     * @return
     */
    public TtsScltxxcjScgl addCppcdj(TtsScltxxcjScgl vo,String entity_id,String joinFlag){
        //当前数据状态默认为1表示产品未卖出
        vo.setStatus("1");
        //表示当前单据状态为初始状态
        vo.setBillStratrus("0");
        //加入标记是1 表示种植业
        vo.setJoinFlag(joinFlag);
        //获取单位名称
        String id = vo.getUnitId();
        SysDictData unit = sysDictionaryService.getUnitNameById(id);
        String harvestUnit = unit.getDictName();
        vo.setHarvestUnit(harvestUnit);
        //首次加入，如果库存为空的情况，库存数量为收获数量
        if(vo.getStoreCount() == null || vo.getStoreCount() == BigDecimal.ZERO){
            vo.setStoreCount(vo.getProductAmount());
        }
        vo.setFreezeCount(BigDecimal.ZERO);

        String productInnerKey = DateUtil.getDateTime("yyyyMMddhhssmm");
        vo.setProductInnerKey(productInnerKey);
        //获取用户信息
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        vo.setEntityIdcode(reg.getEntityIdcode());
        vo.setUserIdcode(reg.getUserIdcode());
        //获取产品批次码
        SysCodeConvert productPc = getProductPc(vo.getEntityIdcode(),vo.getProductId());
        //长码
        vo.setProductPc(productPc.getCodeLong());
        //短码
        vo.setShortCode(productPc.getCodeShort());
        //来源标识，此处存入批次码作为唯一标识
        if(null == vo.getSourceEntity() || "" == vo.getSourceEntity()){
            vo.setSourceEntity(productPc.getCodeShort());
        }
        //入库
        vo = super.add(vo);
        return vo;
    }

    /**
     * 产品批次合成
     * @param ids
     */
    public void cppchc(String[] ids,String entity_id){
        //拆分产品登记id
//        String[] ids = spids.split(";");
        //条件map
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ids",ids);
        //合成后数据
        TtsScltxxcjScgl vo = checkProduct(map,entity_id);
        //原数据
        List<TtsScltxxcjScgl> list = provider.getSphcByIds(map);
        //批次组合、产品来源、检测情况
        Set<String> productSourceSet = new HashSet<String>();
        Set<String> mediChecksSet =  new HashSet<String>();
        Set<String> fromzsmSet =  new HashSet<String>();
        //对原数据的数据状态进行修改
        for(TtsScltxxcjScgl yvo : list){
            //原数据状态修改为已合成，单据状态合并
            yvo.setStatus("4");
            yvo.setBillStratrus("2");
            //
            productSourceSet.add(yvo.getProductSource());
            mediChecksSet.add(yvo.getMediCheck());
            fromzsmSet.add(yvo.getFromzsm());
            //数据状态更新
            super.update(yvo);

        }
        String newzsm = DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*10;
        //合成数据对应的批次
        vo.setProductSource(SetToString(productSourceSet,","));
        vo.setMediCheck(SetToString(mediChecksSet,","));
        vo.setBillStratrus("1");
        //如果当前合成的数据有追溯码的情况，则合成后数据也要有追溯码
        /*if(fromzsmSet.size() > 1){
            vo.setFromzsm(newzsm);
            map.put("newzsm",newzsm);
        }*/
        //更新合成后数据
        super.update(vo);
        //批量插入合成记录
        map.put("hcid",vo.getId());
        map.put("product_pc_new",vo.getProductPc());
        map.put("shotCode_new",vo.getShortCode());
        //
        ttsScltxxcjCppchcService.insertBySelect(map);
    }


    /**
     * 校验数据对象，并返回一个新增合成后待使用数据对象
     * @param map
     * @return
     */
    public TtsScltxxcjScgl checkProduct(Map<String,Object> map,String entity_id){
        TtsScltxxcjScgl vo = new TtsScltxxcjScgl();
        List<Map<String,Object>> list = getCheckProduct(map);
        if(list.size() != 1){
            //提示当前待合成产品
            throw new DataParseException("当前待合成数据产品名称或计量单位不一致...");
        }
        String product_id = list.get(0).get("PRODUCT_ID")+"";
        String product_name = list.get(0).get("PRODUCT_NAME")+"";;
        String product_sort = list.get(0).get("PRODUCT_SORT")+"";
        String product_industry = list.get(0).get("PRODUCT_INDUSTRY")+"";
        String harvest_unit = list.get(0).get("HARVEST_UNIT")+"";
        BigDecimal product_amount = new BigDecimal(list.get(0).get("PRODUCT_AMOUNT")+"");
        BigDecimal store_count = new BigDecimal(list.get(0).get("STORE_COUNT")+"");
        String joinFlag = list.get(0).get("JOIN_FLAG")+"";
        String unit_id = list.get(0).get("UNIT_ID")+"";
        //
        vo.setProductId(product_id);
        vo.setProductName(product_name);
        vo.setProductSort(product_sort);
        vo.setProductIndustry(product_industry);
        vo.setHarvestUnit(harvest_unit);
        vo.setUnitId(unit_id);
        //说明，进行合成的时候，如果产品为进行销售行为，则产品收获数量和库存数量一致，
        //如果进行了销售的，则新批次收获的数量为销售后的库存数量
        vo.setProductAmount(store_count);
        vo.setStoreCount(store_count);
        vo.setDelFlag("N");
        vo.setHarvestTime(new Date());
        vo.setMediCheck("temp");//
        vo.setMediResult("合格");
        vo = addCppcdj(vo,entity_id,joinFlag);
        return vo;
    }


    /**
     * 校验产品是否是同一种产品，产品计量单位是否一致
     * @param map
     * @return
     */
    public List<Map<String,Object>> getCheckProduct(Map<String,Object> map){

        return provider.getCheckProduct(map);
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
    /**
     * 根据屠宰物名称获取屠宰产品列表
     * @param productId
     * @return
     */
    public PageInfo getslaughterProduct(int pageNum, int pageSize, String productId,String userId){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("productId",productId);
        queryParams.put("joinFlag","2");
        queryParams.put("noBillStratrus","2");
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getslaughterProduct(queryParams);
    }
    /**
     * 屠宰后修改库存
     * @param id
     * @param amount
     * @return
     */
    public void updateProductAmount(String id, String amount) {
        provider.updateProductAmount(id,amount);
    }


    /**
     * 通过产品的批次号 String[] pcs 来修改对应的产品库存情况
     * @param map
     */
    public void updateProductStoreCount(Map<String, Object> map) {

        provider.updateProductStoreCount(map);
    }


    /**
     * 通过产品的批次号 String[] pcs 来修改对应的产品库存情况,未进行销售确认数量，则进行冻结
     * @param map
     */
    public void updateProductFreezeCount(Map<String, Object> map){

        provider.updateProductStoreFreezeCount(map);
    }


    /**
     * 采购管理，通过销售登记id，重算生产管理库存，及销售状态
     * @param id
     */
    public boolean updateStoreCountForCgqr(String id){
        return provider.updateStoreCountForCgqr(id) > 0;
    }


    /**
     * 获取可屠宰的产品
     * @param userId
     * @return
     */
    public List<TtsScltxxcjScgl> getTypeList(String userId) {
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        String entityIdcode = reg.getEntityIdcode();
        return provider.getTypeList(entityIdcode);
    }

    /**
     * 获取屠宰产品入库记录
     * @param ttsScltxxcjScgl
     * @param pageNum
     * @param pageSize
     * @param productSort
     * @return
     */
    public PageInfo<TtsScltxxcjScgl> getSlaughterPageInfo(TtsScltxxcjScgl ttsScltxxcjScgl, int pageNum,
                                                          int pageSize, String productName,String status,
                                                          String productSort,String shrq_q,String shrq_h,
                                                          String sortFlag,String userId, String keyWord) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("productName",productName);
        queryParams.put("status",status);
        queryParams.put("productSort",productSort);
        queryParams.put("joinFlag","3");
        //收获日期区间范围
        queryParams.put("shrq_q",shrq_q);
        queryParams.put("shrq_h",shrq_h);
        //过滤被合成的数据
        queryParams.put("noBillStratrus","1");
        //产品来源
        queryParams.put("productSource","屠宰");
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("keyWord", keyWord);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
    }
    /**
     * 获取可用于批次合成的产品列表
     * @param productName
     * @return
     */
    public PageInfo<TtsScltxxcjScgl> getStorPageInfo(TtsScltxxcjScgl bean,String userId, int pageNum, int pageSize, String productName,String productType) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
//        queryParams.put("stor",bean);
        queryParams.put("productName",productName);
        queryParams.put("productSort",productType);
        queryParams.put("billStratrus","0");
        queryParams.put("joinFlag","3");
        //进行合成的时候，不允许有冻结情况，任意标记，表示验证冻结数量是否为0
        queryParams.put("freezeCount", 1);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
//        queryParams.put("noStatus","4");
        return provider.getPageInfo(queryParams);
    }
    /**
     * 获取屠宰后（包括批次合成）产品列表
     * @param productName
     * @return
     */
    public PageInfo<TtsScltxxcjScgl> getStor(TtsScltxxcjScgl bean, int pageNum, int pageSize,String productType, String productName,String status,String startTime,String endTime,String userId,String keyWord) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("shrq_q",startTime);
        queryParams.put("shrq_h",endTime);
        queryParams.put("keyWord",keyWord);
        queryParams.put("productName",productName);
        queryParams.put("productSort",productType);
        queryParams.put("joinFlag","3");
        queryParams.put("status",status);
        queryParams.put("noBillStratrus","2");
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(userId);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
    }


    /**
     * 批次码生成
     * @param entityIdcode
     * @param produceCode
     * @return
     */
    public SysCodeConvert getProductPc(String entityIdcode,String produceCode){
        //企业注册号
        String registerNum = getRegisterNum(entityIdcode);
        //码类型
        IdGenerator.TracingCodeType tracingCodeType = IdGenerator.TracingCodeType.production;
        //主体类型
        TtsScltxxcjRegiter reg = ttsScltxxcjRegiterService.getMainByUserIdCode(entityIdcode);
        String entityTypeName = reg.getEntityTypeName().toString().trim();
        IdGenerator.MainBodyCategories mainBodyCategories = null;
        if("生产主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainWorkingBody;
        }else if("经营主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainManagementBody;
        }else if("生产经营主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement;
        }/*else if("农资主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement;
        }*/
        SysCodeConvert SysCodeConvert = sysCodeGeneratorProvider.getProductBatchCode(tracingCodeType,mainBodyCategories,registerNum,produceCode);
        return SysCodeConvert;
    }

    /**
     * 保存搜索信息
     * @param pc
     * @return
     */
    public void saveSearchData(String pc,String userId)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pc",pc);
        map.put("userId",userId);
        provider.saveSearchData(map);
    }

    /**
     * 通过批次码查询信息
     * @param pc
     * @return
     */
    public List<Map<String,Object>> getBaseForPc(String pc,String entityId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pc",pc);
        map.put("entityId",entityId);
        return provider.getBaseForPc(map);
    }


    /**
     * 获取注册号
      * @param entityIdCode
     * @return
     */
    public String getRegisterNum(String entityIdCode){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entityIdCode",entityIdCode.trim());
        map.put("userIdCode",null);
        //
        Map<String,Object> result = ttsScltxxcjRegiterService.getRegisterNum(map);
        return result.get("REGISTERNUM")+"";
    }
    /**
     * 根据批次码查询产品和用户信息
     * @param userType
     * @param code
     * @return
     */
    public TtsScltxxcjScglAndUserInfo getInfoByPcCode(String userType, String code) {
        if(userType.trim().equals("4")){
            return provider.getInfoByPcCodeForUsual(code);
        }else{
            return provider.getInfoByPcCodeForGov(code);
        }
    }

    public TtsScltxxcjScgl getproductInfo(String id) {
        return provider.getproductInfo(id);
    }

    public void updateState(String id) {
        provider.updateState(id);
    }

    public void deleteByIds(String id) {
        provider.deleteByIds(id);
    }

    public List<TtsScltxxcjScgl> getSaleByProId(String[] ids) {
        return provider.getSaleByProId(ids);
    }

    public List<Map<String,Object>> getPrintForFrom(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.getPrintForFrom(queryParams);
    }

    public List<Map<String,Object>> getPrintForPc(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.getPrintForPc(queryParams);
    }

    public List<TtsScltxxcjScgl> getProType(String id, String industry, String joinFlag) {
        Map<String, Object> queryParams = new HashMap<>();
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(id);
        String entityId = reg.getEntityIdcode();
        queryParams.put("entityId",entityId);
        queryParams.put("industry",industry);
        queryParams.put("joinFlag",joinFlag);
        return  provider.getProType(queryParams);
    }

    public List<TtsScltxxcjScgl> getProName(String id, String typeName, String industry, String joinFlag) {
        Map<String, Object> queryParams = new HashMap<>();
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(id);
        String entityId = reg.getEntityIdcode();
        queryParams.put("entityId",entityId);
        queryParams.put("typeName",typeName);
        queryParams.put("industry",industry);
        queryParams.put("joinFlag",joinFlag);
        return  provider.getProName(queryParams);
    }

    public SysDictData getIndustryById(String id) {
        return sysDictProvider.getDictDataById(id);
    }

    public long isOneType(String id) {
        return provider.isOneType(id);
    }

    public String getProductId(String id) {
        return provider.getProductId(id);
    }

    public SysTemplate downloadById(String tid) { return sysTemplateProvider.foundById(tid); };

    public List<Map<String,Object>> queryBySourceEntity(String sourceEntity) {
        return provider.queryBySourceEntity(sourceEntity);
    }

}