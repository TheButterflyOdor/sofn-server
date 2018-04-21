package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.*;
import com.sofn.service.tts.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 *	产品管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "产品管理", description = "产品管理")
@RequestMapping(value = "/ttsScltxxcjProduct",method = RequestMethod.POST)
public class TtsScltxxcjProductController extends BaseController {

    @Autowired
    public TtsScltxxcjProductService ttsScltxxcjProductService;
    @Autowired
    public SSOLoginService ssoLoginService;
    @Autowired
    public TtsScltxxcjUserService ttsScltxxcjUserService;
    @Autowired
    public TtsScltxxcjProductTypeService ttsScltxxcjProductTypeService;
    @Autowired
    public SysDictionaryService sysDictionaryService;
    @Autowired
    public SysProductListService sysProductListService;

    /**
     * 根据条件获取产品管理列表
     * @param ttsScltxxcjProduct
     * @return
     */
    @ApiOperation(value = "获取产品管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取产品管理信息列表",operationType = "查询")
    public Object getPageInfo(TtsScltxxcjProduct ttsScltxxcjProduct, int start, int length,String id,String keyWord) {
        try{
            if(!StringUtils.isNullEmpty(keyWord)){
                ttsScltxxcjProduct.setName(keyWord);
            }
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            PageInfo<TtsScltxxcjProduct> pageInfo =ttsScltxxcjProductService.getPageInfo(ttsScltxxcjProduct,((start+1)/length)+1,length,user);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增产品管理记录
     *
     * @param ttsScltxxcjProduct
     * @return
     */
    @ApiOperation(value = "新增产品管理数据")
    @RequestMapping(value = "/addTtsScltxxcjProduct", method = RequestMethod.POST)
    @SystemControllerLog(description = "新增一条产品信息", operationType = "新增")
    public Object addTtsScltxxcjProduct(@RequestBody TtsScltxxcjProduct ttsScltxxcjProduct, String id) {
        try {
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            //封装产品信息
            ttsScltxxcjProduct.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjProduct.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjProduct.setCreateTime(new Date());
            //判断该产品是否已存在
            boolean isExistedPro = ttsScltxxcjProductService.isExistedPro(ttsScltxxcjProduct);
            if (!isExistedPro) {
                TtsScltxxcjProductType proType = ttsScltxxcjProductTypeService.getType(ttsScltxxcjProduct.getType());
                if (proType == null) {
                    proType = new TtsScltxxcjProductType();
                    proType.setEntityIdcode(user.getEntityIdcode());
                    proType.setUserIdcode(user.getUserIdcode());
                    proType.setTypeCode(ttsScltxxcjProduct.getType());
                    proType.setName(ttsScltxxcjProduct.getTypeName());
                    proType.setStatus("Y");
                    ttsScltxxcjProductTypeService.add(proType);
                }
                ttsScltxxcjProductService.add(ttsScltxxcjProduct);
                return setSuccessModelMap(new ModelMap());
            } else {
                ModelMap modelMap = new ModelMap();
                modelMap.put("msg", "已存在此数据");
                return setModelMap(modelMap, HttpCode.REPEAT_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID获取单条产品管理数据信息
     * @param ttsScltxxcjProduct
     * @return
     */
    @ApiOperation(value = "获取单条产品管理数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取一条产品信息",operationType = "查询")
    public Map<String,Object> queryById(@RequestBody TtsScltxxcjProduct  ttsScltxxcjProduct){
        ttsScltxxcjProduct = ttsScltxxcjProductService.queryById(ttsScltxxcjProduct.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjProduct",ttsScltxxcjProduct);
        return map;
    }

    /**
     * 修改产品管理数据信息
     * @param ttsScltxxcjProduct
     * @return
     */
    @ApiOperation(value = "修改产品管理数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjProduct",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改一条产品信息",operationType = "修改")
    public Object updateTtsScltxxcjProduct(@RequestBody TtsScltxxcjProduct ttsScltxxcjProduct, String id) {
        try {
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());
            ttsScltxxcjProduct.setUserIdcode(user.getUserIdcode());
            ttsScltxxcjProduct.setEntityIdcode(user.getEntityIdcode());
            ttsScltxxcjProduct.setUpdateTime(new Date());
            ttsScltxxcjProduct.setUpdateBy(user.getAccount());
            //判断该产品是否已存在
            boolean isExistedPro = ttsScltxxcjProductService.isExistedPro(ttsScltxxcjProduct);
            if (!isExistedPro) {
                ttsScltxxcjProductService.update(ttsScltxxcjProduct);
                return setSuccessModelMap(new ModelMap());
            } else {
                ModelMap modelMap = new ModelMap();
                modelMap.put("msg", "已存在此数据");
                return setModelMap(modelMap, HttpCode.REPEAT_DATA);
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除产品管理信息
     * @param ttsScltxxcjProduct
     * @return
     */
    @ApiOperation(value = " 删除产品管理信息")
    @RequestMapping(value = "/deleteTtsScltxxcjProduct",method = RequestMethod.POST)
    @SystemControllerLog(description = "删除一条产品信息",operationType = "删除")
    public Object deleteTtsScltxxcjProduct(TtsScltxxcjProduct ttsScltxxcjProduct){
        try{
            ttsScltxxcjProductService.delete(ttsScltxxcjProduct.getId());
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "修改产品状态")
    @RequestMapping(value="/updateProductStatus",method = RequestMethod.POST)
    @SystemControllerLog(description = "修改一条产品信息状态",operationType = "修改")
    public Object updateProductStatus(TtsScltxxcjProduct ttsScltxxcjProduct){
        ttsScltxxcjProduct.setUpdateTime(new Date());
        int result = ttsScltxxcjProductService.updateStatus(ttsScltxxcjProduct);
        if (result == 1){
            return setSuccessModelMap(new ModelMap());
        }else{
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据产品所属行业获取该行业下的产品信息")
    @RequestMapping(value = "/getProductPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据行业获取产品信息",operationType = "查询")
    public Map<String,Object> getProductPageInfo(TtsScltxxcjProduct ttsScltxxcjProduct,int start,int length,String industry,String keyWord,String id){
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>();
        int pageNum = (start - 1) < 0 ? 0 : (start - 1);
        pageInfo = sysProductListService.getProductByArgs(industry, keyWord, pageNum, length);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data",pageInfo);
        return map;
    }
    @ApiOperation("获取行业字典数据")
    @RequestMapping(value = "/getIndustryInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取行业字典数据",operationType = "查询")
    public Map<String,Object> getIndustry(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<SysDictData> list  = sysDictionaryService.getIndustry();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("list",list);
        return map;
    }

    /**
     * 2016-11-04 TL
     * 获取已有产品的产品种类
     *
     * @return list
     */
    @ApiOperation(value = "获取已有产品的产品种类")
    @RequestMapping(value = "/getProType", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取已有产品的产品种类",operationType = "查询")
    public List<TtsScltxxcjScgl> getProType(String id) {
        List<TtsScltxxcjScgl> list = new ArrayList<TtsScltxxcjScgl>();
        list = ttsScltxxcjProductService.getProType(id);
        return list;
    }
    /**
     * 2017-4-24 TL
     * 获取已有产品的产品名称
     *
     * @return list
     */
    @ApiOperation(value = "获取已有产品的产品种类")
    @RequestMapping(value = "/getProName", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取已有产品的产品种类",operationType = "查询")
    public List<TtsScltxxcjScgl> getProName(String id,String typeName,String industry) {
        List<TtsScltxxcjScgl> list = new ArrayList<TtsScltxxcjScgl>();
        list = ttsScltxxcjProductService.getProName(id,typeName,industry);
        return list;
    }

    @ApiOperation(value = "根据屠宰前产品产品code获取该产品下的屠宰后产品信息")
    @RequestMapping(value = "/getSlaProductPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "根据屠宰前产品产品code获取该产品下的屠宰后产品信息",operationType = "查询")
    public Map<String, Object> getSlaProductPageInfo(TtsScltxxcjProduct ttsScltxxcjProduct, int start, int length, String productId,String keyword) {
        PageInfo<Map<String,Object>> pageInfo = sysProductListService.getSlaProductPageInfo(((start + 1) / length), length, productId,keyword);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("data", pageInfo);
        return map;
    }

    /**
     * 根据产品code获取屠宰后产品列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据产品code获取屠宰后产品列表")
    @RequestMapping(value = "/getSlaProduct", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据产品code获取屠宰后产品列表",operationType="查询")
    public Map<String, Object> getSlaProduct(String productId,String keyWord) {
        List<Map<String, Object>> ttsScltxxcjProduct = sysProductListService.getSlaProduct(productId,keyWord);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjProduct", ttsScltxxcjProduct);
        return map;
    }
}