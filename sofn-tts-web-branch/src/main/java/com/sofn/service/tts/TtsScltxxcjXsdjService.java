package com.sofn.service.tts;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.tts.TtsScltxxcjXsdjProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 销售登记 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjXsdjService extends BaseService<TtsScltxxcjXsdjProvider, TtsScltxxcjXsdj> {

    @DubboReference
    public void setTtsScltxxcjXsdjProvider(TtsScltxxcjXsdjProvider provider) {
        this.provider = provider;
    }


    @Autowired
    public SSOLoginService sSOLoginService;

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
     * 销售记录
     */
    @Autowired
    public TtsScltxxcjXcdjjlService ttsScltxxcjXcdjjlService;


    /**
     * 临时数据表
     */
    @Autowired
    public  TtsTempXsjlService ttsTempXsjlService;

    @Autowired
    public  TtsScltxxcjCgglService ttsScltxxcjCgglService;
    /**
     * 生产管理记录
     */
    @Autowired
    public  TtsScltxxcjScglService ttsScltxxcjScglService;

    @DubboReference
    public SysCodeGeneratorProvider sysCodeGeneratorProvider;


    public IdGenerator idGenerator = new IdGenerator();


    private TtsScltxxcjRegiter reg;

    public TtsScltxxcjRegiter getReg() {
        return reg;
    }

    public void setReg(TtsScltxxcjRegiter reg) {
        this.reg = reg;
    }

    public PageInfo getPageInfo(TtsScltxxcjXsdj bean, int pageNum, int pageSize, String confirmState,
                                String entity_id,String productName,String shrq_q,String shrq_h,
                                String keyWord, String productSort,String isCirculates) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("confirmState",confirmState);
        queryParams.put("productName",productName);
        queryParams.put("productSort",productSort);
        queryParams.put("shrq_q",shrq_q);
        queryParams.put("shrq_h",shrq_h);
        queryParams.put("keyWord",keyWord);
        queryParams.put("isCirculates",isCirculates);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfo(queryParams);
    }


    /**
     * 销售登记流通
     * @param jsonXsjl 产品销售信息
     * @param customer_id 客户id
     */
//    @Transactional(rollbackForClassName={"RuntimeException","Exception"})
    public void xsdjlt(String jsonXsjl,String customer_id,String customerUserIdCode,String entity_id,String type){
        setReg(sSOLoginService.getEntityByRedis(entity_id));
        //销售流通，添加新客户信息
        TtsScltxxcjCustomer customer = insertTtsScltxxcjCustomer(entity_id,customerUserIdCode);
        customer_id = customer.getId();
        //销售登记临时表
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
            TtsScltxxcjXsdj vo = addTtsScltxxcjXsdj(temp,customer_id);
            setId.add(vo.getId());
            //将临时表数据添加销售明细中
            vo.setTempId(tempId);
            int count = insertXsdjjl(vo);
            addSource(count,vo);
        }

        //修改生产管理表库存 将此次销售数量变成冻结，并同步减少库存
        updateScglStore("N",setPc);
        //清理临时表数据
        ttsTempXsjlService.deleteById(tempId);
    }
    /**
     * 入市销售
     * @param
     */
//    @Transactional
    public void xsdjrs(String jsonXsjl, String jsonCustomer,String entity_id,String type){
        //校验客户是否已存在，不存在，则进行生成
        TtsScltxxcjCustomer customer = JSON.parseObject(jsonCustomer,TtsScltxxcjCustomer.class);
        String customer_id = checkInsertTtsScltxxcjCustomer(customer,entity_id);
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
            TtsScltxxcjXsdj vo = addTtsScltxxcjXsdj(temp,customer_id);
            //销售登记id放入set
            setId.add(vo.getId());
            //添加销售明细记录
            vo.setTempId(tempId);
            int count = insertXsdjjl(vo);
            addSource(count,vo);
            //销售登记-确认销售
            updateXsdjConfirm(vo,type);
        }
        //销售明细--进行销售确认状态变更
        updateXsmxSatus(setId);
        //修改直接入库生产管理情况..生产管理数据库存情况
        updateScglStore("Y",setPc);
        ttsTempXsjlService.deleteById(tempId);
    }

    /**
     * 加入来源标识
     * @param count
     * @param vo
     */
    public void addSource(int count,TtsScltxxcjXsdj vo){
        //加入来源标识（合成的存入销售登记信息的id，未合成的继续存入原来的来源标识，0.0这是一个坑）
        if(count > 1){
            vo.setSourceEntity(vo.getId());
            update(vo);
        }else if(count == 1){
            TtsScltxxcjXcdjjl record = ttsScltxxcjXcdjjlService.getRecordByXspc(vo.getProductXspc());
            vo.setSourceEntity(record.getSourceEntity());
            update(vo);
        }
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
     * 销售登记
     * @param temp
     * @param customer_id
     * @return
     */
    public TtsScltxxcjXsdj addTtsScltxxcjXsdj(Map<String, Object> temp,String customer_id){
        TtsScltxxcjXsdj vo = new TtsScltxxcjXsdj();
        //
        String product_id = temp.get("PRODUCT_ID")+"";
        String product_name = temp.get("PRODUCT_NAME")+"";
        String product_industry = temp.get("PRODUCT_INDUSTRY")+"";
        String product_sort = temp.get("PRODUCT_SORT")+"";
        String unitId = temp.get("UNIT_ID")+"";
        String joinFlag = temp.get("JOIN_FLAG")+"";
        String harvestUnit = temp.get("HARVEST_UNIT")+"";
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
        vo.setProductId(product_id);
        vo.setProductName(product_name);
        vo.setProductIndustry(product_industry);
        vo.setProductSort(product_sort);
        vo.setSaleAmount(sale_amount);
        vo.setProductXspcSl(xssl);
        vo.setUnitId(unitId);
        vo.setJoinFlag(joinFlag);
        vo.setHarvestUnit(harvestUnit);
        //
        vo.setSaleTime(new Date());
        vo.setSaleUser(getReg().getId());
        //是否直接入市销售标记
        vo.setIsCirculatesEnd(temp.get("iscirculatesend")+"");
        //销售确认标记
        vo.setConfirmState("N");

        //产生销售批次码（主体用户码+当前时间+随机三位数）
        vo.setProductXspc(getReg().getUserIdcode()+DateUtil.getDateTime("yyyyMMddhhmmss")+Math.random()*3);
        //主体身份码和用户码
        vo.setEntityIdcode(getReg().getEntityIdcode());
        vo.setUserIdcode(getReg().getUserIdcode());
        return add(vo);
    }


    /**
     * 进行销售确认
     * @param vo
     */
    public void updateXsdjConfirm(TtsScltxxcjXsdj vo,String type){
        //销售确认确认状态
        vo.setConfirmState("Y");
        vo.setConfirmTime(new Date());
        //进行销售确认后，生成产品追溯码和入市凭证
        String isCirculatesend = vo.getIsCirculatesEnd();
        //直接入市的情况生成 入市凭证，不是直接入市生成产品追溯码
        if("Y".equals(isCirculatesend) && "R".equals(type)){
            //确认时，先产生一条入市追溯码
            SysCodeConvert sysCodeConvert = getTraceCode(vo.getEntityIdcode(),vo.getProductId());
            vo.setTraceProof(sysCodeConvert.getCodeLong());
            vo.setTraceProofShort(sysCodeConvert.getCodeShort());
        } else if("N".equals(isCirculatesend) && "L".equals(type)) {
            //产品追溯码为去向追溯码
            vo.setTraceCode(vo.getTozsm());
        }
        //更新销售记录，销售确认状态
        update(vo);
    }

    /**
     * 入市追溯码生成
     * @param entityIdCode
     * @param produceCode
     * @return
     */
    private SysCodeConvert getTraceCode(String entityIdCode,String produceCode){
        //企业注册号
        String registerNum = getRegisterNum(entityIdCode);
        //码类型
        IdGenerator.TracingCodeType tracingCodeType = IdGenerator.TracingCodeType.enterMarket;
        //主体类型
        TtsScltxxcjRegiter reg = ttsScltxxcjRegiterService.getMainByUserIdCode(entityIdCode);
        String entityTypeName = reg.getEntityTypeName().toString().trim();
        IdGenerator.MainBodyCategories mainBodyCategories = null;
        if("生产主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainWorkingBody;
        }else if("经营主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainManagementBody;
        }else if("生产经营主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement;
        }
        SysCodeConvert SysCodeConvert = sysCodeGeneratorProvider.getProductBatchCode(tracingCodeType,mainBodyCategories,registerNum,produceCode);
        return SysCodeConvert;
    }

    /**
     * 通过ids 对销售明细的确认状态，进行批量更新
     * @param setIds
     */
    public void updateXsmxSatus(Set<String> setIds){
        ttsScltxxcjXcdjjlService.updateXsmxStatus(setIds);
    }

    /**
     * 将临时表数据插入销售记录表
     * @param vo
     */
    public int insertXsdjjl(TtsScltxxcjXsdj vo){
        return ttsScltxxcjXcdjjlService.insertByTemp(vo);
    }


    /**
     * 校验客户是否存在，并返回客户的id
     * @param vo
     * @return
     */
    public String checkInsertTtsScltxxcjCustomer(TtsScltxxcjCustomer vo,String entity_id){
        //用户登录信息
        setReg(sSOLoginService.getEntityByRedis(entity_id));
        //
        String user_name = vo.getUserName();
        String name = vo.getName();
        String phone = vo.getPhone();
        String address = vo.getAddress();
        //
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName",user_name);
        map.put("name",name);
        map.put("phone",phone);
        map.put("address",address);
        map.put("entityIdCode",getReg().getEntityIdcode());
        map.put("userIdCode",getReg().getUserIdcode());
        //
        TtsScltxxcjCustomer tempvo = ttsScltxxcjCustomerService.checkCustomer(map);
        String customer_id = "";
        if(null != tempvo){
            customer_id = tempvo.getId();
        }
        //如果常用客户表中没有记录则进行新增，并加入更新时间
        if(StringUtils.isBlank(customer_id)){
            vo.setUpdatetime(new Date());
            //获取当前账户主体身份信息，并在数据对象中加入主体身份码和用户码
            vo.setEntityIdCode(getReg().getEntityIdcode());
            vo.setUserIdCode(getReg().getUserIdcode());
            vo.setStatus("Y");
            vo.setCreatetime(new Date());
            //客户信息这边代码有问题，不晓得放入哪个标记，就都放进来了
            vo.setDelFlag("N");
            vo.setDelflag("N");
            vo.setCreateby(getReg().getId());
            vo = ttsScltxxcjCustomerService.add(vo);
            return vo.getId();
        }
        //如果是客户存在的情况，则更新客户的变更时间
        vo = ttsScltxxcjCustomerService.queryById(customer_id);
        vo.setStatus("Y");
        vo.setDelFlag("N");
        vo.setDelflag("N");
        vo.setUpdatetime(new Date());
        ttsScltxxcjCustomerService.update(vo);
        return  customer_id;

    }


    /**
     * 修改直接入市销售库存或未直接销售入市库存
     * @param isCirculatesEnd Y 是 N 否
     *
     * @param setPc 产品批次
     */
    public void updateScglStore(String isCirculatesEnd,Set<String> setPc){
        String[] pcs = new String[setPc.size()];
        pcs = setPc.toArray(pcs);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pcs",pcs);
        if("Y".equals(isCirculatesEnd)){
            ttsScltxxcjScglService.updateProductStoreCount(map);
            return;
        }
        if("N".equals(isCirculatesEnd)){
            ttsScltxxcjScglService.updateProductFreezeCount(map);
            return;
        }
    }


    /**
     * 单独封装的带客户信息的查询单条数据返回
     * @param id
     * @return
     */
    public TtsScltxxcjXsdjAndCustomer getXsdjAndCustomerById(String id){
        TtsScltxxcjXsdjAndCustomer vo = provider.getXsdjAndCustomerById(id);
        return vo;
    }



    /**
     * 采购确认前单独-使用的分页
     *
     * @return
     */
    public PageInfo<TtsScltxxcjXsdjAndCustomer> queryPageForCg(Map<String, Object> map){

        return provider.queryPageForCg(map);
    }


    /**
     * 通过追溯码查询 销售登记，产品从哪里来，到哪里去
     * @param tozsm
     * @param fromzsm
     * @return
     */
    public List<TtsScltxxcjXsdjAndCustomer> getXsdjForZsm(String tozsm,String fromzsm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tozsm",tozsm);
        map.put("fromzsm",fromzsm);
        return provider.getXsdjForZsm(map);
    }


    /**
     * 销售是本级，查询上级销售信息
     * @param zsm
     * @return
     */
    public List<Map<String,Object>> getXsUpZsm(String zsm){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("zsm",zsm);
        return provider.getXsUpZsm(queryParams);
    }


    /**
     * 销售流通，销售确认后，通过用户主体身份码来确定客户信息，并添加到客户信息管理表中
     * @param customerUserIdCode
     * @return
     */
    public TtsScltxxcjCustomer insertTtsScltxxcjCustomer(String entity_id,String customerUserIdCode){
        //用户登录信息
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entity_id);
        //查询客户是否是存在的
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entityIdCode",reg.getEntityIdcode());
        map.put("userIdCode",reg.getUserIdcode());
        map.put("customerUserIdCode",customerUserIdCode);
        TtsScltxxcjCustomer vo = ttsScltxxcjCustomerService.checkCustomer(map);
        //根据主体身份码查询主体相关信息
        TtsScltxxcjRegiterAndUser cusreg = ttsScltxxcjRegiterService.queryByUserIdCode(customerUserIdCode);
        //客户不存在的情况，就进行添加客户
        if(null == vo){
            vo = new TtsScltxxcjCustomer();
            //注册表中客户信息，放入常客户信息表中
            vo.setName(cusreg.getEnterpriseName());
            vo.setUserName(cusreg.getName());
            vo.setPhone(cusreg.getPhone());
            vo.setAddress(cusreg.getAddress());
            vo.setCustomerEntityIdCode(cusreg.getEntityIdcode());
            vo.setCustomerUserIdCode(cusreg.getUserIdcode());
            vo.setUpdatetime(new Date());
            //获取当前账户主体身份信息，并在数据对象中加入主体身份码和用户码
            vo.setEntityIdCode(reg.getEntityIdcode());
            vo.setUserIdCode(reg.getUserIdcode());
            vo.setStatus("Y");
            vo.setCreatetime(new Date());
            //客户信息这边代码有问题，不晓得放入哪个标记，就都放进来了
            vo.setDelFlag("N");
            vo.setDelflag("N");
            vo.setCreateby(reg.getId());

            vo = ttsScltxxcjCustomerService.add(vo);
        }else{
            //注册表中客户信息，放入常客户信息表中
            vo.setName(cusreg.getEnterpriseName());
            vo.setUserName(cusreg.getName());
            vo.setPhone(cusreg.getPhone());
            vo.setAddress(cusreg.getAddress());
            vo.setCustomerEntityIdCode(cusreg.getEntityIdcode());
            vo.setCustomerUserIdCode(cusreg.getUserIdcode());
            vo.setUpdatetime(new Date());
            //获取当前账户主体身份信息，并在数据对象中加入主体身份码和用户码
            vo.setEntityIdCode(reg.getEntityIdcode());
            vo.setUserIdCode(reg.getUserIdcode());
            vo.setStatus("Y");
            //客户信息这边代码有问题，不晓得放入哪个标记，就都放进来了
            vo.setDelFlag("N");
            vo.setDelflag("N");
            vo.setCreateby(reg.getId());
            ttsScltxxcjCustomerService.update(vo);
        }
        return vo;
    }

    /**
     * 产品入市凭证生成
     * @param entityIdCode 主体身份码
     * @param userIdCode    用户码
     * @param produceCode
     * @return
     */
    private String getTraceProof(String entityIdCode,String userIdCode,String produceCode){
        IdGenerator.ServiceType retrospect = IdGenerator.ServiceType.Retrospect;
        TtsScltxxcjRegiter reg = ttsScltxxcjRegiterService.getMainByUserIdCode(userIdCode);
        String entityTypeName = reg.getEntityTypeName().toString().trim();
        String entityScaleName = reg.getEntityScaleName().toString().trim();
        IdGenerator.MainBodyCategories mainBodyCategories = null;
        if("生产主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainWorkingBody;
        }else if("经营主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainManagementBody;
        }else if("生产经营主体".equals(entityTypeName)){
            mainBodyCategories = IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement;
        }
        IdGenerator.OrganizationForms organizationForms = null;
        if("个人".equals(entityScaleName)){
            organizationForms = IdGenerator.OrganizationForms.Individual;
        }else if("企业/个体工商户".equals(entityScaleName)){
            organizationForms = IdGenerator.OrganizationForms.Enterprise;
        }else{
            organizationForms = IdGenerator.OrganizationForms.Enterprise;
        }
        String registerNum = getRegisterNum(entityIdCode);
        String productTraceabilityCode = idGenerator.getIntoCityTraceability(retrospect,mainBodyCategories,organizationForms,registerNum,produceCode);
        return productTraceabilityCode;
    }


    /**
     * 获取注册号
     * @param entityIdCode
     * @return
     */
    public String getRegisterNum(String entityIdCode){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entityIdCode",entityIdCode);
        map.put("userIdCode",null);
        //
        Map<String,Object> result = ttsScltxxcjRegiterService.getRegisterNum(map);
        return result.get("REGISTERNUM")+"";
    }

    /**
     * 根据入市追溯凭证查询产品和用户信息
     * @param userType
     * @param code
     * @return
     */
    public TtsScltxxcjXsdjAndUserInfo getInfoByRsCode(String userType, String code) {
        if(userType.trim().equals("4")){
            return provider.getInfoByRsCodeForUsual(code);
        }else{
            return provider.getInfoByRsCodeForGov(code);
        }
    }

    public void updateComfigState(String id,String type) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("type",type);
        provider.updateComfigState(map);
    }

    /**
     * 获取采购确认的客户和产品的详细信息
     * @param id
     * @param userIdCode
     * @return
     */
    public TtsScltxxcjXsdjAndCustomer getCgqrAndCustomerById(String id, String userIdCode) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("userIdCode",userIdCode);
        return provider.getCgqrAndCustomerById(map);
    }

    public long existsTrace(String zsm, String entityId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("zsm",zsm);
        map.put("entityId",entityId);
        return provider.existsTrace(map);
    }

    public SysCodeConvert getLongByShort(String longCode,String shortCode) {
        return sysCodeGeneratorProvider.queryCode(longCode,shortCode);
    }

    public List<Map<String,Object>> querySource(String id) {
        return provider.querySource(id);
    }
}