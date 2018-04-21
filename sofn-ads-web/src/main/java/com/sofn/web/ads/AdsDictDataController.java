package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.SysDictData;
import com.sofn.service.ads.AdsDictDataService;
import com.sofn.service.ads.AdsSysArgiProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *	监测字典数据获取 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "监测字典数据", description = "监测字典数据")
@RequestMapping(value = "/adsDictData",method = RequestMethod.POST)
public class AdsDictDataController extends BaseController {

    @Autowired
    private AdsDictDataService adsDictDataService;
    @Autowired
    private AdsSysArgiProductService adsSysArgiProductService;

    /**
     * 获取机构级别字典数据
     * @return
     */
    @ApiOperation(value = "获取机构级别字典数据")
    @RequestMapping(value = "/getLevelDictData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取机构级别字典数据",operationType="查询")
    public Object getLevelDictData() {
        try{
            DictType dictType = DictType.ORGLEVEL;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取机构性质字典数据
     * @return
     */
    @ApiOperation(value = "获取机构性质字典数据")
    @RequestMapping(value = "/getNatureDictData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取机构性质字典数据",operationType="查询")
    public Object getNatureDictData() {
        try{
            DictType dictType = DictType.ORGNATURE;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取机构类别字典数据
     * @return
     */
    @ApiOperation(value = "获取机构类别字典数据")
    @RequestMapping(value = "/getOrgTypeDictData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取机构类别字典数据",operationType="查询")
    public Object getOrgTypeDictData() {
        try{
            DictType dictType = DictType.ORGTYPE;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取资格证书字典数据
     * @return
     */
    @ApiOperation(value = "获取资格证书字典数据")
    @RequestMapping(value = "/getQualificationsDictData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取资格证书字典数据",operationType="查询")
    public Object getQualificationsDictData() {
        try{
            DictType dictType = DictType.QUALIFICATIONCERTIFICATE;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取监测种类字典数据
     * @return
     */
    @ApiOperation(value = "获取监测种类字典数据")
    @RequestMapping(value = "/getMonitorKindData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取监测种类字典数据",operationType="查询")
    public Object getMonitorKindData() {
        try{
            DictType dictType = DictType.MONITORKIND;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 获取行业分类字典数据
     * @return
     */
    @ApiOperation(value = "获取行业分类字典数据")
    @RequestMapping(value = "/getIndustryTypeData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取行业分类字典数据",operationType="查询")
    public Object getIndustryTypeData() {
        try{
            DictType dictType = DictType.INDUSTRYTYPE;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取职务字典数据
     * @return
     */
    @ApiOperation(value = "获取职务字典数据")
    @RequestMapping(value = "/getPostTypeData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取职务字典数据",operationType="查询")
    public Object getPostTypeData() {
        try{
            DictType dictType = DictType.POSTTYPE;
            List<SysDictData> list = adsDictDataService.getSysDictListByType(dictType);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询产品信息分页数据
     * @return
     */
    @ApiOperation(value = "查询产品信息分页数据")
    @RequestMapping(value = "/getProductPageInfo",method = RequestMethod.POST)
    @SystemControllerLog(description = "查询产品信息分页数据",operationType="查询")
    public Object getProductPageInfo( String industry,String keyWord, int start, int length) {
        try{
            PageInfo<Map<String,Object>> pageInfo = adsSysArgiProductService.getArgiProductByArgs(industry,keyWord,((start+1)/length),length);
            return setSuccessModelMap(new ModelMap(),pageInfo);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "农产品分类查询(keyWord)")
    @PostMapping("/queryProduct")
    @SystemControllerLog(description = "农产品分类查询(keyWord)",operationType="查询")
    public Object query(SysArgiProduct sysArgiProduct, String keyWord, long start, long length) {
        Page page=new Page();
        page.setLength(length);
        page.setStart(start/length);
        PageInfo<Map<String, Object>> pageInfo = adsSysArgiProductService.queryByCondition(sysArgiProduct,keyWord,page);
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    @ApiOperation(value = "获取数量单位字典数据")
    @RequestMapping(value = "/getUnitDictData",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取数量单位字典数据",operationType="查询")
    public Object getUnitDictData() {
        DictType dictType = DictType.UNITTYPE;
        List<SysDictData> dictList = adsDictDataService.getSysDictListByType(dictType);
        return setSuccessModelMap(new ModelMap(), dictList);
    }
}
