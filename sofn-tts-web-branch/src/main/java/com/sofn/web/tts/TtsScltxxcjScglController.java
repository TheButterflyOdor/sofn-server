package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.*;
import com.sofn.service.tts.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
*	生产管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "生产管理", description = "生产管理")
@RequestMapping(value = "/ttsScltxxcjScgl",method = RequestMethod.POST)
    public class TtsScltxxcjScglController extends BaseController {

    Logger logger = LoggerFactory.getLogger(TtsScltxxcjScglController.class);

    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;

    @Autowired
    private SysDictionaryService sysDictionaryService;

    @Autowired
    public TtsScltxxcjProductService ttsScltxxcjProductService;

    @Autowired
    public TtsScltxxcjBaseService ttsScltxxcjBaseService;

    @Autowired
    public SysProductListService sysProductListService;

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;


    /**
     * 根据条件获取生产管理列表
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "获取生产管理信息列表")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取生产管理信息列表",operationType="查询")
    public Map<String, Object> getPageInfo(TtsScltxxcjScgl ttsScltxxcjScgl, int start, int length, String entity_id) {
        PageInfo<TtsScltxxcjScgl> pageInfo = ttsScltxxcjScglService.getPageInfo(ttsScltxxcjScgl, ((start + 1) / length) + 1, length, entity_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    /**
     * 新增生产管理记录
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "新增生产管理数据")
    @RequestMapping(value = "/addTtsScltxxcjScgl", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增生产管理数据",operationType="新增")
    public Map<String, Object> addTtsScltxxcjScgl(@RequestBody TtsScltxxcjScgl ttsScltxxcjScgl) {
        ttsScltxxcjScglService.add(ttsScltxxcjScgl);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }


    /**
     * 根据ID获取单条生产管理数据信息
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "获取单条生产管理数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取单条生产管理数据信息",operationType="查询")
    public Map<String, Object> queryById(@RequestBody TtsScltxxcjScgl ttsScltxxcjScgl) {
        ttsScltxxcjScgl = ttsScltxxcjScglService.queryById(ttsScltxxcjScgl.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjScgl", ttsScltxxcjScgl);
        return map;
    }

    /**
     * 根据企业ID，用户ID查看详情
     *
     *
     */
    @ApiOperation(value = "获取产品详细信息")
    @RequestMapping(value = "/getproductInfo", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品详细信息",operationType="查询")
    public Map<String,Object> getproductInfo(String id){
        TtsScltxxcjScgl ttsScltxxcjScgl = ttsScltxxcjScglService.getproductInfo(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", ttsScltxxcjScgl);
        return  map;
    }

    /**
     * 修改生产管理数据信息
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "修改生产管理数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjScgl", method = RequestMethod.POST)
    @SystemControllerLog(description = "修改生产管理数据信息",operationType="修改")
    public Object updateTtsScltxxcjScgl(@RequestBody TtsScltxxcjScgl ttsScltxxcjScgl) {
        try {
            ttsScltxxcjScglService.update(ttsScltxxcjScgl);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除生产管理信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = " 删除生产管理信息")
    @RequestMapping(value = "/deleteTtsScltxxcjScgl", method = RequestMethod.POST)
    @SystemControllerLog(description = "删除生产管理信息",operationType="删除")
    public Object deleteTtsScltxxcjScgl(String id) {
        try {
            ttsScltxxcjScglService.deleteByIds(id);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 新增生产管理记录
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "新增产品批次登记")
    @RequestMapping(value = "/addCppcdj", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增产品批次登记",operationType="新增")
    public Object addCppcdj(@RequestBody TtsScltxxcjScgl ttsScltxxcjScgl) {
        try {
            ttsScltxxcjScglService.addCppcdj(ttsScltxxcjScgl, ttsScltxxcjScgl.getTempEnId(), ttsScltxxcjScgl.getJoinFlag());
            //传入参数id为基地id，procName为新增产品名称
            String id = ttsScltxxcjScgl.getHarvestBaseid();
            String procName = ttsScltxxcjScgl.getProductName();
            if(id != null && !ttsScltxxcjBaseService.updateMainProc(id,procName)){
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     *成都市平台新增产品接口
	 *
     * @param
     * @return
     */
    @ApiOperation(value = "四川省平台新增产品接口")
    @RequestMapping(value = "/addProductForSc", method = RequestMethod.POST)
    @SystemControllerLog(description = "四川省平台新增产品接口",operationType="新增")
    public Object addProductForSc(HttpServletRequest request) {
        try {
            TtsScltxxcjScgl ttsScltxxcjScgl = new TtsScltxxcjScgl();
            String productName = request.getParameter("productName");
            ttsScltxxcjScgl.setProductName(productName);
            //需要根据产品名称来获取产品编号以及产品种类
            SysArgiProduct sysArgiProduct = sysProductListService.getProductInfoByName(productName);
            ttsScltxxcjScgl.setProductId(sysArgiProduct.getProductCode());
            ttsScltxxcjScgl.setProductSort(sysArgiProduct.getParentName());
            String joinFlag = null;
            String industry = sysArgiProduct.getTypeId();
            ttsScltxxcjScgl.setProductIndustry(industry);
            if("01".equals(industry)){
                joinFlag = "1";
            }else if("02".equals(industry)){
                joinFlag = "2";
            }else if("03".equals(industry)){
                joinFlag = "4";
            }
            ttsScltxxcjScgl.setJoinFlag(joinFlag);
            //依据证号，仅限畜牧产品是存入
            ttsScltxxcjScgl.setProofNumber(request.getParameter("proofNumber"));
            //需要根据单位名称获取单位id
            ttsScltxxcjScgl.setHarvestUnit(request.getParameter("unitName"));
            ttsScltxxcjScgl.setHarvestBasename(request.getParameter("baseName"));
            ttsScltxxcjScgl.setHarvestTime(new Date());
            ttsScltxxcjScgl.setMediCheck("自检");
            ttsScltxxcjScgl.setMediResult("合格");
            ttsScltxxcjScgl.setProductAmount(new BigDecimal(request.getParameter("amount")));
            //内部追溯码，用于查询农事活动详情
            ttsScltxxcjScgl.setTraceId(request.getParameter("insideCode"));
            ttsScltxxcjScgl.setProductSource("自产");
            //成都市平台传入的统一社会信用代码/身份证号
            String code = request.getParameter("code");
            TtsScltxxcjRegiter reg = ttsScltxxcjRegiterService.getEntityByCode(code);
            ttsScltxxcjScglService.addProductForSc(ttsScltxxcjScgl, reg.getEntityIdcode(),reg.getUserIdcode(), ttsScltxxcjScgl.getJoinFlag());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "新增地方产品批次登记")
    @RequestMapping(value = "/addAreaCppcdj", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增产品批次登记",operationType="新增")
    public Object addAreaCppcdj(@RequestBody TtsScltxxcjScgl ttsScltxxcjScgl) {
        try {
            if (ttsScltxxcjScgl == null){
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR," 传递了空的信息");
            }
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("creditCode","");
            TtsScltxxcjRegiter regiter = new TtsScltxxcjRegiter();
            regiter = ttsScltxxcjRegiterService.findInfoByCreditCode(params);
            ttsScltxxcjScglService.addCppcdj(ttsScltxxcjScgl, ttsScltxxcjScgl.getTempEnId(), ttsScltxxcjScgl.getJoinFlag());
            //传入参数id为基地id，procName为新增产品名称
            String id = ttsScltxxcjScgl.getHarvestBaseid();
            String procName = ttsScltxxcjScgl.getProductName();
            if(id != null && !ttsScltxxcjBaseService.updateMainProc(id,procName)){
                return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            }
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取生产管理列表
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "获取生产管理产品合成信息列表")
    @RequestMapping(value = "/getPageInfoPchc", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取生产管理产品合成信息列表",operationType="查询")
    public Object getPageInfoPchc(TtsScltxxcjScgl ttsScltxxcjScgl, int start, int length,
                                  String productName, String harvestUnit, String entity_id,String productSort,String shrq_q, String shrq_h ) {
        try {
            PageInfo<TtsScltxxcjScgl> pageInfo = ttsScltxxcjScglService.getPageInfoPchc(
                    ttsScltxxcjScgl, ((start + 1) / length) + 1, length, productName, harvestUnit, entity_id,productSort,shrq_q,shrq_h);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据条件获取生产管理列表
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "根据条件获取生产管理列表")
    @RequestMapping(value = "/getCpdjgl", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取生产管理列表",operationType="查询")
    public Object getCpdjgl(TtsScltxxcjScgl ttsScltxxcjScgl, int start, int length,
                            String productName, String status, String productSort, String shrq_q, String shrq_h, String entity_id ,String harvestUnit,String keyWord) {
        try {
            PageInfo<TtsScltxxcjScgl> pageInfo = ttsScltxxcjScglService.getCpdjgl(
                    ttsScltxxcjScgl, ((start+1)/length)+1, length, productName, status, productSort, shrq_q, shrq_h, entity_id,harvestUnit,keyWord);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取销售列表
     *
     * @param ttsScltxxcjScgl
     * @return
     */
    @ApiOperation(value = "根据条件获取销售列表")
    @RequestMapping(value = "/getSaleList", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取销售列表",operationType="查询")
    public Object getSaleList(TtsScltxxcjScgl ttsScltxxcjScgl, int start, int length,
                              String productName, String status, String productSort, String shrq_q,
                              String shrq_h, String entity_id, String keyWord) {
        try {
            PageInfo<TtsScltxxcjScgl> pageInfo = ttsScltxxcjScglService.getSaleList(
                    ttsScltxxcjScgl, ((start + 1) / length) + 1,
                    length, productName, status, productSort, shrq_q, shrq_h, entity_id, keyWord);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 产品批次合成
     *
     * @param spids 传入产品登记id组合字符串，逗号隔开
     * @return
     */
    @ApiOperation(value = "产品批次合成")
    @RequestMapping(value = "/addCppchc", method = RequestMethod.POST)
    @SystemControllerLog(description = "产品批次合成",operationType="新增")
    public Object addCppchc(String spids, String entity_id) {
        try {
            String[] ids = spids.split(",");
            if(ids.length <= 1){
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
            }
            ttsScltxxcjScglService.cppchc(ids, entity_id.trim());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 2016-10-25 TL
     * 根据屠宰产品名称获取产品列表
     * @return map
     */
    @ApiOperation(value = "根据屠宰产品名称获取产品列表")
    @RequestMapping(value = "/getslaughterProduct", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据屠宰产品名称获取产品列表",operationType="查询")
    public Object getslaughterProduct(int start, int length, String productId, String userId) {
        try {
            PageInfo<TtsScltxxcjScgl> pageInfo = ttsScltxxcjScglService.getslaughterProduct(((start + 1) / length) + 1, length, productId, userId);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 2016-11-04 TL
     * 获取屠宰前产品列表（此处是为屠宰前产品类型查询做下拉菜单初始化）
     *
     * @return list
     */
    @ApiOperation(value = "获取屠宰前产品列表")
    @RequestMapping(value = "/getslaughterProType", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取屠宰前产品列表",operationType="查询")
    public List<TtsScltxxcjScgl> getProTypeList(String userId) {
        List<TtsScltxxcjScgl> list = new ArrayList<TtsScltxxcjScgl>();
        list = ttsScltxxcjScglService.getTypeList(userId);
        return list;
    }
    /***
     * 根据不同行业单位字典
     * @param id
     * @return
     */
    @ApiOperation(value = "根据不同行业单位字典")
    @RequestMapping(value = "/getUnits", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据不同行业单位字典",operationType="查询")
    public Object getUnits(String id) {
        try {
            String[] ids = id.split(",");
            List<SysDictData> units = sysDictionaryService.getDictByType(ids);
            return units;
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 获取行业value
     * @param
     * @return list
     *
     */
    @ApiOperation(value = "获取行业value")
    @RequestMapping(value = "/getIndustry",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取行业value",operationType="查询")
    public Object getIndustry(String id){
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            String[] ids = id.split(",");
            //循环查询企业类型的数据字典
            for(int i = 0;i < ids.length;i++){
                SysDictData industry = ttsScltxxcjScglService.getIndustryById(ids[i]);
                String industryValue = industry.getDictValue().toString().trim();
                if(industryValue.equals("01")){
                    resultMap.put("0", industry);
                }else if(industryValue.equals("02")){
                    resultMap.put("1", industry);
                }else if(industryValue.equals("03")){
                    resultMap.put("2", industry);
                }
            }
            return resultMap;
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取产品列表
     * @param start
     * @param length
     * @param entity_id
     * @param industry
     * @param typeName
     * @return
     */
    @ApiOperation(value = "根据条件获取产品列表")
    @RequestMapping(value = "/getProductByTerm", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取产品列表",operationType="查询")
    public Map<String, Object> getProductByTerm(int start, int length, String entity_id,String industry,String typeName) {
        PageInfo<TtsScltxxcjProduct> pageInfo = ttsScltxxcjProductService.getProductByTerm(((start + 1) / length) + 1, length, entity_id,industry,typeName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }
    /**
     * 根据id获取产品列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据id获取产品列表")
    @RequestMapping(value = "/getSaleByProId", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据条件获取产品列表",operationType="查询")
    public Object getSaleByProId(String proId) {
        try {
            String[] ids = proId.split(",");
            List<TtsScltxxcjScgl> ttsScltxxcjScgl = ttsScltxxcjScglService.getSaleByProId(ids);
            return ttsScltxxcjScgl;
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取追溯凭证打印详细信息(来源追溯码)
     * @param id
     * @return
     */
    @ApiOperation(value = "获取追溯凭证打印详细信息(来源追溯码)")
    @RequestMapping(value = "/getPrintForFrom",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取追溯凭证打印详细信息(来源追溯码)",operationType="查询")
    public Map<String,Object> getPrintForFrom(String id){
        List<Map<String,Object>> vo  = ttsScltxxcjScglService.getPrintForFrom(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }

    /**
     * 获取追溯凭证打印详细信息(批次码)
     * @param id
     * @return
     */
    @ApiOperation(value = "获取追溯凭证打印详细信息(批次码)")
    @RequestMapping(value = "/getPrintForPc",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取追溯凭证打印详细信息(批次码)",operationType="查询")
    public Map<String,Object> getPrintForPc(String id){
        List<Map<String,Object>> vo  = ttsScltxxcjScglService.getPrintForPc(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",vo);
        return map;
    }

    /**
     * 2017-5-11 TL
     * 获取已有产品的产品种类
     * @return list
     */
    @ApiOperation(value = "获取已有产品的产品种类")
    @RequestMapping(value = "/getProType", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取已有产品的产品种类",operationType = "查询")
    public List<TtsScltxxcjScgl> getProType(String id,String industry,String joinFlag) {
        List<TtsScltxxcjScgl> list = new ArrayList<TtsScltxxcjScgl>();
        list = ttsScltxxcjScglService.getProType(id,industry,joinFlag);
        return list;
    }
    /**
     * 2017-5-11 TL
     * 获取已有产品的产品名称
     * @return list
     */
    @ApiOperation(value = "获取已有产品的产品名称集合")
    @RequestMapping(value = "/getProName", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取已有产品的产品名称集合",operationType = "查询")
    public List<TtsScltxxcjScgl> getProName(String id,String typeName,String industry,String joinFlag) {
        List<TtsScltxxcjScgl> list = new ArrayList<TtsScltxxcjScgl>();
        list = ttsScltxxcjScglService.getProName(id,typeName,industry,joinFlag);
        return list;
    }
    /**
     * 判断是否为同一类型的屠宰前产品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = " 判断是否为同一类型的屠宰前产品")
    @RequestMapping(value = "/isOneType", method = RequestMethod.POST)
    @SystemControllerLog(description = "判断是否为同一类型的屠宰前产品",operationType="查询")
    public Object isOneType(String id) {
        long count = ttsScltxxcjScglService.isOneType(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("count",count);
        return map;
    }
    /**
     * 获取多个产品的相同产品编号
     * @param id
     * @return
     */
    @ApiOperation(value = " 获取多个产品的相同产品编号")
    @RequestMapping(value = "/getProductId", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取多个产品的相同产品编号",operationType="查询")
    public Object getProductId(String id) {
        String  productId= ttsScltxxcjScglService.getProductId(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("productId",productId);
        return map;
    }
    
    /**
     * 2017-5-22
     * 农产品信息采集模板下载
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "下载农产品信息采集模板")
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @SystemControllerLog(description = "下载农产品信息采集模板", operationType = "模板下载")
    public Map<String, Object> download(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SysTemplate tmplVo = ttsScltxxcjScglService.downloadById("sofn-ncpxxcjb");
            map.put("data", tmplVo);
            map.put("httpCode", ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("httpCode", ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
        }
        return map;
    }
    /**
     * 根据来源标识获取来源追溯信息
     *
     * @param sourceEntity
     * @return
     */
    @ApiOperation(value = "根据来源标识获取来源追溯信息")
    @RequestMapping(value = "/queryBySourceEntity", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据来源标识获取来源追溯信息",operationType="查询")
    public Map<String, Object> queryBySourceEntity(String sourceEntity) {
        List<Map<String,Object>> vo;
        Map<String, Object> map = new HashMap<String, Object>();

        if(null != sourceEntity && "" != sourceEntity){
            vo = ttsScltxxcjScglService.queryBySourceEntity(sourceEntity);
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("data",vo);
        }else{
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
        }
        return map;
    }
}