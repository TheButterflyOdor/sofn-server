package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.*;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.tts.TtsScltxxcjCgglProvider;
import com.sun.star.uno.Exception;
import com.sun.star.uno.RuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/11/2.
 */
@Service
public class TtsScltxxcjCgglService extends BaseService<TtsScltxxcjCgglProvider, TtsScltxxcjCggl> {

    @DubboReference
    public void setTtsScltxxcjCgglProvider(TtsScltxxcjCgglProvider provider) {
        this.provider = provider;
    }
    /**
     * 销售登记
     */
    @Autowired
    public TtsScltxxcjXsdjService ttsScltxxcjXsdjService;

    /**
     * 销售明细
     */
    @Autowired
    public TtsScltxxcjXcdjjlService ttsScltxxcjXcdjjlService;


    /**
     * 生产管理记录
     */
    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;

    /**
     * 销售退回
     */
    @Autowired
    public  TtsScltxxcjXsthService ttsScltxxcjXsthService;


    /**
     * 登录信息获取
     */
    @Autowired
    public SSOLoginService ssoLoginService;


    /**
     * 客户信息
     */
    @Autowired
    public TtsScltxxcjCustomerService ttsScltxxcjCustomerService;


    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;


    /**
     * 追溯码生成主件
     */
    public IdGenerator idGenerator = new IdGenerator();

    @DubboReference
    public SysCodeGeneratorProvider sysCodeGeneratorProvider;



    /**
     * 采购未确认展示数据信息表
     * @param pageNum
     * @param pageSize
     * @param confirmState
     * @param isCirculatesEnd
     * @return
     */
    public PageInfo<TtsScltxxcjXsdjAndCustomer> queryPageForCg(int pageNum, int pageSize, String confirmState,String isCirculatesEnd,
                                                               String entity_id,String productName,String shrq_q,String shrq_h,
                                                               String keyWord,String productSort,String purchaseRecord){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", ((pageNum+1)/pageSize)+1);
        queryParams.put("pageSize", pageSize);
        queryParams.put("confirmState",confirmState);
        queryParams.put("isCirculatesEnd",isCirculatesEnd);
        queryParams.put("productName",productName);
        queryParams.put("productSort",productSort);
        queryParams.put("shrq_q",shrq_q);
        queryParams.put("shrq_h",shrq_h);
        queryParams.put("keyWord",keyWord);
        queryParams.put("purchaseRecord",purchaseRecord);
        TtsScltxxcjRegiter reg = ssoLoginService.getEntityByRedis(entity_id);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return ttsScltxxcjXsdjService.queryPageForCg(queryParams);
    }


    /**
     * 采购确认
     * @param id
     */
    @Transactional
    public void updateTtsScltxxcjCgqr(String id,String entity_id,String customerUserId){

        //获取销售登记信息
        TtsScltxxcjXsdj vo = ttsScltxxcjXsdjService.queryById(id);
        //确认时，先产生一条追溯码
        SysCodeConvert sysCodeConvert = getTraceCode(vo.getEntityIdcode(),vo.getProductId());
        vo.setTozsm(sysCodeConvert.getCodeLong());
        vo.setToShortCode(sysCodeConvert.getCodeShort());

        String type = "L";
        ttsScltxxcjXsdjService.updateXsdjConfirm(vo,type);
        //将销售明细确认状态和时间加上
        ttsScltxxcjXcdjjlService.updateXsmxStatus(id);
        //通过销售登记id，修改生产管理库存
        ttsScltxxcjScglService.updateStoreCountForCgqr(id);
        //添加采购记录
        TtsScltxxcjCggl cggl = insertCggl(vo);
        //新增客户信息
        ttsScltxxcjXsdjService.insertTtsScltxxcjCustomer(entity_id,customerUserId);
        //产品入库
        insertScgl(cggl,entity_id,vo.getJoinFlag(),vo.getHarvestUnit(),vo.getUnitId());
    }


    /**
     * 产品入库
     * @param vo
     * @param entity_id
     */
    public void insertScgl(TtsScltxxcjCggl vo,String entity_id,String joinFlag,String unit,String unitID){
        TtsScltxxcjScgl scgl = new TtsScltxxcjScgl();
        scgl.setProductId(vo.getProductId());
        scgl.setProductName(vo.getProductName());
        scgl.setProductSort(vo.getProductSort());
        scgl.setProductIndustry(vo.getProductIndustry());
        //
        scgl.setProductAmount(vo.getCgAmount());
        scgl.setHarvestTime(vo.getSaleTime());
        scgl.setHarvestUnit(unit);
        scgl.setUnitId(unitID);
        scgl.setProductSource("采购");
        //采购产品暂定为送检合格
        scgl.setMediCheck("自检");
        scgl.setMediResult("合格");
        scgl.setFromzsm(vo.getFromzsm());
        scgl.setFromShortCode(vo.getFromShortCode());
        scgl.setSourceEntity(vo.getSourceEntity());
        ttsScltxxcjScglService.addCppcdj(scgl,entity_id,joinFlag);
    }


    /**
     * 根据销售流通记录，在采购确认后，产生采购记录
     * @param vo
     * @return
     */
    public TtsScltxxcjCggl insertCggl(TtsScltxxcjXsdj vo) {
        TtsScltxxcjCggl cggl = new TtsScltxxcjCggl();
        //
        cggl.setXsdjid(vo.getId());
        cggl.setProductId(vo.getProductId());
        cggl.setProductName(vo.getProductName());
        cggl.setProductSort(vo.getProductSort());
        cggl.setProductIndustry(vo.getProductIndustry());
        //采购时间，采购数量，销售人
        cggl.setSaleTime(vo.getSaleTime());
        cggl.setCgAmount(vo.getSaleAmount());
        cggl.setCgDw(vo.getHarvestUnit());
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
        //质检情况和结论
        cggl.setZjcheck("自检");
        cggl.setZjresult("合格");
        //采购记录中的来源追溯码即为销售登记表中的去向追溯码
        cggl.setFromzsm(vo.getTozsm());
        cggl.setFromShortCode(vo.getToShortCode());
        //来源标识
        cggl.setSourceEntity(vo.getSourceEntity());
        if(vo.getProofNumber() != null){
            cggl.setProofNumber(vo.getProofNumber());
        }
        return add(cggl);
    }

    /**
     * 采购退回
     * @param id
     */
//    @Transactional(rollbackFor={Exception.class, RuntimeException.class})
    public void updateTtsScltxxcjCgth(String id,String xsthyy,String type){
        //将销售登记数据，删除标记，标记为Y
        ttsScltxxcjXsdjService.delete(id);
        ttsScltxxcjXsdjService.updateComfigState(id,type);
        //根据销售登记id，将销售明细数据，删除标记，标记为Y
        ttsScltxxcjXcdjjlService.deleteXsmxFlag(id);
        //通过销售登记id，修改生产管理库存
        if(ttsScltxxcjScglService.updateStoreCountForCgqr(id)){
            ttsScltxxcjScglService.updateState(id);
        }
        //销售登记记录主体
        TtsScltxxcjXsdj vo = ttsScltxxcjXsdjService.queryById(id);
        ttsScltxxcjXsthService.insertXsth(vo,xsthyy);
    }

    /**
     * 根据追溯码，查询采购管理记录
     * @param zsm
     * @return
     */
    public List<TtsScltxxcjCgglAndCustomer> getCgglForZsm(String zsm){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("fromzsm",zsm);
        return provider.getCgglForZsm(map);
    }


    /**
     * 通过追溯码和主体身份码，查询是否有对应的采购记录
     * @param zsm
     * @param entity_id
     * @return
     */
    public long zsmIsCgOrXs(String zsm,String entity_id) {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("zsm",zsm);
        map.put("entity_id",entity_id);
        return provider.zsmIsCgOrXs(map);
    }


    /**
     * 产品追溯码生成
     * @param entityIdCode
     * @param produceCode
     * @return
     */
    private SysCodeConvert getTraceCode(String entityIdCode,String produceCode){
        //企业注册号
        String registerNum = getRegisterNum(entityIdCode);
        //码类型
        IdGenerator.TracingCodeType tracingCodeType = IdGenerator.TracingCodeType.circulate;
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
     * 获取注册号
     * @param entityIdCode
     * @return
     */
    public String getRegisterNum(String entityIdCode){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entityIdCode",entityIdCode);
        map.put("userIdCode",null);
        Map<String,Object> result = ttsScltxxcjRegiterService.getRegisterNum(map);
        return result.get("REGISTERNUM")+"";
    }

    public long isExists(String code) {
        return provider.isExists(code);
    }

    public TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCg(String userType,String code) {
        if(!userType.trim().equals("4")){
            return provider.getInfoByTraceCodeFromCg(code);
        }else{
            return provider.getInfoByTraceCodeFromCgforUsual(code);
        }
    }
}
