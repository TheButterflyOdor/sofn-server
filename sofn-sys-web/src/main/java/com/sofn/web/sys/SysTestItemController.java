package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.service.sys.SysTestItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19/019.
 */
@RestController
@Api(value = "检测项目信息接口", description = "检测项目信息接口")
@RequestMapping(value = "/sysTestItem")
public class SysTestItemController extends BaseController {
    @Autowired
    private SysTestItemService sysTestItemService;

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Object queryList(String itemName, String standardCode, Page page) {
        page.setStart(page.getStart() / page.getLength());
        PageInfo<SysTestItemModel> pageInfo = sysTestItemService.getTestItems(itemName, standardCode, page);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, pageInfo);
    }

    @Authorization
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public Object queryOne(String id) {
        ModelMap modelMap = new ModelMap();
        SysTestItemModel model = sysTestItemService.getTestItem(id);
        return setSuccessModelMap(modelMap, model);
    }

    @Authorization
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(SysTestItemModel model, String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }

        model.setUpdateBy(user.getAccount());
        SysTestItemModel model2 = sysTestItemService.updateTestItem(model);
        return setSuccessModelMap(modelMap, model2);
    }

    @RequestMapping(value = "/updateStandardCode", method = RequestMethod.POST)
    public Object updateStandardCode(String oldStandardCode, String newStandardCode) {
        ModelMap modelMap = new ModelMap();
        Map<String, Integer> data = new HashMap<>();
        Integer updateRows = sysTestItemService.updateTestItemBystandardCode(oldStandardCode, newStandardCode);
        data.put("updateRows", updateRows); // 更新行数
        modelMap.put("data", data);
        return setSuccessModelMap(modelMap);
    }

    @Authorization
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(SysTestItemModel model, String token) {
        ModelMap modelMap = new ModelMap();
        ResponseEntity<ModelMap> responseEntity = new ResponseEntity(HttpStatus.OK);

        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }

        if (sysTestItemService.isExistTestItem(model.getStandardCode(), model.getItemName())) {
            responseEntity = setModelMap(modelMap, HttpCode.REPEAT_DATA);
            modelMap.put("msg","检测项已存在，操作失败");
            return responseEntity;
        }

        model.setCreateBy(user.getAccount());
        SysTestItemModel model2 = sysTestItemService.addTestItem(model);
        return setSuccessModelMap(modelMap, model2);
    }

    @Authorization
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String ids, String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Integer deleteRows = sysTestItemService.deleteTestItems(ids, user.getAccount());
        Map<String, Integer> data = new HashMap<>();
        data.put("deleteRows", deleteRows); // 逻辑删除行数
        modelMap.put("data", data);
        return setSuccessModelMap(modelMap);
    }
}
