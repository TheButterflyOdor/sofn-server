package com.sofn.web.sys;

import com.alibaba.fastjson.JSONArray;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.sys.SysDictTypeDto;
import com.sofn.service.sys.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by: dong4j.
 * Date: 2016-10-19.
 * Time: 15:50.
 * Description: 字典管理接口
 */
@RestController
@Api(value = "字典管理接口", description = "字典管理接口")
@RequestMapping(value = "/sysDict")
public class SysDictController extends BaseController {
    /**
     * The Sys dict service.
     */
    @Autowired
    private SysDictService sysDictService;

    //**字典类型操作接口**//

    /**
     * Generator code object.
     * 获取所有字典类型包括子类
     * todo 需要缓存
     * 加载页面时和刷新页面时调用
     * @param modelMap the model map
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "查询所有字典类型")
    @SystemControllerLog(description = "查询所有字典类型",operationType = "查询")
    @RequestMapping(value = "/getDictTypes", method = RequestMethod.POST)
    public Object getDictTypes(ModelMap modelMap, Long draw, Long start, Long length,String id, String keyWord) {
        //Integer count = sysDictService.getDictTypesTotal();
        //Page    page  = new Page();
        //page.setDraw(draw);
        //page.setStart(start);
        //page.setLength(length);
        //page.setRecordsTotal(count);
        logger.debug(keyWord);
        List<SysDictTypeDto> list = sysDictService.getDictTypes("all",id,keyWord);
        return setSuccessModelMap(modelMap, list);
    }

    /**
     * Generator code object.
     * 根据字典父类类型获取所有子类
     * todo 需要缓存
     * 加载页面时和刷新页面时调用
     * @param modelMap the model map
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "根据字典父类类型获取所有子类")
    @SystemControllerLog(description = "根据字典父类类型获取所有子类",operationType = "根据字典父类类型获取所有子类")
    @RequestMapping(value = "/getDictTypes2", method = RequestMethod.POST)
    public Object getDictTypes2(ModelMap modelMap,String id, String keyWord) {
        logger.debug(keyWord);
        List<SysDictType> list = sysDictService.getDictTypes2("all",id,keyWord);
        return setSuccessModelMap(modelMap, list);
    }

    /**
     * Generator code object.
     * 获取所有字典类型,不包括子类
     * todo 需要缓存
     * 字典主类型下拉列表
     * @param modelMap the model map
     * @return the object
     */
    @ApiOperation(value = "查询所有字典类型")
    @SystemControllerLog(description = "查询所有字典类型使用",operationType = "查询")
    @RequestMapping(value = "/getParentDictTypes", method = RequestMethod.GET)
    public Object getParentDictTypes(ModelMap modelMap) {
        List<SysDictTypeDto> list = sysDictService.getDictTypes("parent","","");
        return setSuccessModelMap(modelMap, list);
    }

    /**
     * Add dict type object.
     * 新增字典类型
     * todo 更新缓存
     * todo 新增字段 status 字典状态 remark 备注
     * todo 没有 code
     * @param sysDictType the sys dict type
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "新增字典类型")
    @SystemControllerLog(description = "新增字典类型",operationType = "增加")
    @RequestMapping(value = "/addDictType", method = RequestMethod.POST)
    public Object addDictType(SysDictType sysDictType) {
        // todo 非空判断
        sysDictService.add(sysDictType);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * Gets dict type by id.
     * 根据Id查询字典类型信息
     * 点击编辑按钮时类型回显数据 todo 不再使用
     * @param modelMap the model map
     * @param id       the id
     * @return the dict type by id
     */
    @ApiOperation(value = "根据Id查询字典类型信息")
    @RequestMapping(value = "/getDictTypeById", method = RequestMethod.POST)
    public Object getDictTypeById(ModelMap modelMap,
                                  @ApiParam(required = false, value = "字典类型 id")
                                  @RequestParam(name = "id", required = false) String id) {
        logger.debug(id);
        // todo 非空判断
        return setSuccessModelMap(modelMap, sysDictService.queryById(id));
    }

    /**
     * Update dict type object.
     * 修改字典类型信息
     * 点击确认修改按钮调用
     * @param sysDictType the sys dict type
     * @return the object
     */
    //@ApiOperation(value = "修改字典类型信息")
    //@SystemControllerLog(description = "修改字典类型信息")
    //@RequestMapping(value = "/updateDictType", method = RequestMethod.POST)
    //public Object updateDictType(SysDictType sysDictType) {
    //    ModelMap    modelMap       = new ModelMap();
    //    SysDictType oldSysDictType = sysDictService.queryById(sysDictType.getId());
    //    if (oldSysDictType != null) {
    //        oldSysDictType.setPid(sysDictType.getPid());
    //        oldSysDictType.setName(sysDictType.getName());
    //        oldSysDictType.setRemark(sysDictType.getRemark());
    //        sysDictType = oldSysDictType;
    //    } else
    //        return setModelMap(modelMap, HttpCode.BAD_REQUEST);
    //    sysDictService.update(sysDictType);
    //    return setSuccessModelMap(modelMap);
    //}

    /**
     * Update dict type object.
     * 修改字典类型信息
     * 点击确认修改按钮调用
     * @return the object
     */
    @ApiOperation(value = "修改字典类型信息")
    @SystemControllerLog(description = "修改字典类型信息",operationType = "修改")
    @RequestMapping(value = "/updateDictType", method = RequestMethod.POST)
    public Object updateDictType(String enable, String pid, String subDictType) {
        ModelMap    modelMap       = new ModelMap();
        sysDictService.updateDictType(enable,pid,subDictType);
        return setSuccessModelMap(modelMap);
    }

    @Authorization
    @ApiOperation(value = "修改字典类型信息2")
    @SystemControllerLog(description = "修改字典类型信息2",operationType = "修改2")
    @RequestMapping(value = "/updateDictType2", method = RequestMethod.POST)
    public Object updateDictType2(String id){
        SysDictType sysDictType = sysDictService.queryById(id);
        String enable = "N";
        if(sysDictType.getEnable().equals("N")){
            enable = "Y";
        }else if(sysDictType.getEnable().equals("Y")){
            enable = "N";
        }
        sysDictService.updateDictType2(enable,id);
        return setSuccessModelMap(new ModelMap());
    }


    /**
     * Gets more dict type.
     * 获取更多字典类型
     *
     * @param modelMap the model map
     * @return the more dict type
     */
    @ApiOperation(value = "获取更多字典类型")
    @RequestMapping(value = "/getMoreDictType", method = RequestMethod.POST)
    public Object getMoreDictType(ModelMap modelMap) {
        return setSuccessModelMap(modelMap);
    }

    //************************************字典数据操作接口************************************//

    /**
     * Gets dict data by type.
     * 根据字典类型查询字典数据
     * todo 有ModelMap
     *
     * @param modelMap the model map
     * @param id       the id
     * @param draw     the draw
     * @param start    the start
     * @param length   the length
     * @return the dict data by type
     */
    @Authorization
    @ApiOperation(value = "根据字典类型查询字典数据")
    @RequestMapping(value = "/getDictDataByType", method = RequestMethod.POST)
    public Object getDictDataByType(ModelMap modelMap,
                                    @ApiParam(required = false, value = "字典类型 id")
                                    @RequestParam(name = "id", required = false) String id,
                                    @ApiParam(required = false, value = "安全验证参数")
                                    @RequestParam(name = "draw", required = false) long draw,
                                    @ApiParam(required = false, value = "开始索引")
                                    @RequestParam(name = "start", required = false) long start,
                                    @ApiParam(required = false, value = "分页长度")
                                    @RequestParam(name = "length", required = false) long length,
                                    @RequestParam(name = "keyWord", required = false) String keyWord) {
        // 第一次查询: 查询总记录数 如果有多个条件 最好封装成 map
        int  recordsTotal = sysDictService.getRecordsTotal(id, keyWord);
        Page page         = new Page();
        page.setRecordsTotal(recordsTotal);
        page.setDraw(draw);
        page.setStart(start);
        page.setLength(length);
        // 分页计算
        page.doPage();
        // 第二次查询: 查询分页数据
        List<SysDictData> list = sysDictService.getDictDataByType(id, keyWord, page);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("list", list);
        return setSuccessModelMap(modelMap);
    }


    /**
     * Add dict data object.
     * 新增字典数据  todo 无ModelMap
     * @param sysDictData the sys dict data
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "新增字典数据")
    @SystemControllerLog(description = "新增字典数据",operationType = "增加")
    @RequestMapping(value = "/addDictData", method = RequestMethod.POST)
    public Object addDictData(@RequestBody SysDictData sysDictData) {
        sysDictData.setCreateBy(WebUtil.getCurrentUserId());
        sysDictData.setUpdateBy(WebUtil.getCurrentUserId());
        sysDictService.addDictData(sysDictData);
        return setSuccessModelMap(new ModelMap());
    }


    /**
     * Query dict data by id object.
     * 根据查询Id字典数据
     *
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "根据查询Id字典数据")
    @RequestMapping(value = "/queryDictDataById", method = RequestMethod.POST)
    public Object queryDictDataById(@ApiParam(required = false, value = "字典数据 id")
                                    @RequestBody SysDictData sysDictData) {
        //Map<String, Object> map = new HashMap<>();
        //map.put("httpCode", HttpCode.OK);
        //map.put("msg", "请求成功");
        //map.put("timestamp", new Date());
        //map.put("data", sysDictService.getDictDataById(sysDictData.getId()));
        //return map;
        return setSuccessModelMap(new ModelMap(),sysDictService.getDictDataById(sysDictData.getId()));
    }

    /**
     * Query dict data by id object.
     * 根据查询Id字典数据
     *
     * @return the object
     */
    @ApiOperation(value = "根据查询Id字典数据")
    @RequestMapping(value = "/queryDictDataById2", method = RequestMethod.POST)
    public Object queryDictDataById(String id,String keyWord) {
        //Map<String, Object> map = new HashMap<>();
        //map.put("httpCode", HttpCode.OK);
        //map.put("msg", "请求成功");
        //map.put("timestamp", new Date());
        //map.put("data", sysDictService.getDictDataById(sysDictData.getId()));
        //return map;
        logger.debug(keyWord);
        return setSuccessModelMap(new ModelMap(),sysDictService.getDictDataById(id));
    }

    /**
     * Update dict data object.
     * 更新字典数据
     *
     * @param sysDictData the sys dict data
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "更新字典数据")
    @SystemControllerLog(description = "更新字典数据",operationType = "修改")
    @RequestMapping(value = "/updateDictData", method = RequestMethod.POST)
    public Object updateDictData(@RequestBody SysDictData sysDictData) {
        ModelMap    modelMap       = new ModelMap();
        SysDictData oldSysDictData = sysDictService.getDictDataById(sysDictData.getId());
        if (oldSysDictData != null) {
            oldSysDictData.setDictName(sysDictData.getDictName());
            oldSysDictData.setDictValue(sysDictData.getDictValue());
            oldSysDictData.setSpellName(sysDictData.getSpellName());
            oldSysDictData.setDictCode(sysDictData.getDictCode());
            oldSysDictData.setEnable(sysDictData.getEnable());
            oldSysDictData.setRemark(sysDictData.getRemark());
            oldSysDictData.setUpdateBy(WebUtil.getCurrentUserId());
            oldSysDictData.setUpdateTime(new Date());
            sysDictData = oldSysDictData;
        } else
            return setModelMap(modelMap, HttpCode.BAD_REQUEST);
        sysDictService.updateDictData(sysDictData);
        return setSuccessModelMap(modelMap);
    }

    /**
     * Delete dict data object.
     * 删除字典数据
     *
     * @param modelMap the model map
     * @param idStr    the idStr
     * @return the object
     */
    @Authorization
    @ApiOperation(value = "删除字典数据")
    @SystemControllerLog(description = "删除字典数据",operationType = "删除")
    @RequestMapping(value = "/deleteDictData", method = RequestMethod.POST)
    public Object deleteDictData(ModelMap modelMap,
                                 @ApiParam(required = false, value = "字典数据数组字符串 idStr")
                                 @RequestParam(name = "idStr", required = false) String idStr) {
        // todo 非空验证
        sysDictService.deleteDictData(idStr);
        return setSuccessModelMap(modelMap);
    }


    /**
     * Update valid.
     * 更改字典数据是否有效
     * // todo 修改状态失败
     * @param modelMap     the model map
     * @param id           the id
     * @param enable the whether valid
     * @return the valid
     */
    @Authorization
    @ApiOperation(value = "设置字典数据是否有效")
    @SystemControllerLog(description = "设置字典数据是否有效",operationType = "修改")
    @RequestMapping(value = "/updateDictDataEnable", method = RequestMethod.POST)
    public Object updateDictDataEnable(ModelMap modelMap,
                              @ApiParam(required = false, value = "字典数据 id")
                              @RequestParam(name = "id", required = false) String id,
                              @ApiParam(required = false, value = "是否有效")
                              @RequestParam(name = "enable", required = false) String enable) {
        sysDictService.updateValid(id, enable);
        return setSuccessModelMap(modelMap);
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test(ModelMap modelMap) {
        // todo 非空验证
        String[] ids = {"33a6dbab71c04079a8a41d5e725d2b3d100ec0d3dbc44f67963ffb54014fb607",
                "88bf73e5c7b546388346736691146a28cd0658dcaa674781b8e622a484dbf1d9",
                "5a3930420e5e4bc9ac4e75bea59f1067e1210d80e4514e7da392e1528cb942c3"};
        return setSuccessModelMap(modelMap,sysDictService.getDictByType(DictType.UNITTYPE ,ids));
    }



    @ApiOperation(value = "根据字典类型Code查询数据字典")
    @RequestMapping(value = "/getDictDataByCode", method = RequestMethod.POST)
    public Object getDictDataByCode(@ApiParam(required = false, value = "字典类型代码")
                                       @RequestParam(name = "codes", required = false) String codes){

        Map<String,Object> map = new HashMap<>();

        JSONArray jsonArray = new JSONArray();
        if (codes.length()>0){
            String[] str = codes.split(",");
            for (int i = 0; i < str.length; i++) {
                List<SysDictData> list = sysDictService.getDictByTypes(str[i]);
                if (list.size()>0){

                    Map<String,List<SysDictData>> maps = new HashMap<>();
                    maps.put(str[i],list);
                    jsonArray.add(maps);

                }
            }
        }
        map.put("data",jsonArray);
logger.info(String.valueOf(jsonArray));
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);

        return map;
    }

}
