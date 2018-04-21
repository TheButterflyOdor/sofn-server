package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.service.sys.SysTestStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * Created by Administrator on 2017/11/14/014.
 */
@RestController
@Api(value = "测试标准信息接口", description = "测试标准信息接口")
@RequestMapping(value = "/sysTestStandard")
public class SysTestStandardController extends BaseController {
    @Autowired
    private SysTestStandardService sysTestStandardService;

    @Authorization
    @ApiOperation(value = "根据标准号和标准名称查询标准信息")
    @SystemControllerLog(description = "根据标准号或者标准名称查询标准信息",operationType = "查询")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Object queryList(String standardCode, String standardName, Page page) {
        page.setStart(page.getStart() / page.getLength());
        PageInfo<SysTestStandardModel> pageInfo = sysTestStandardService.getTestStandards(standardCode, standardName, page);
        ModelMap modelMap = new ModelMap();

        return setSuccessModelMap(modelMap, pageInfo);
    }

    @Authorization
    @ApiOperation(value = "根据ID查询标准信息")
    @SystemControllerLog(description = "根据ID查询标准信息",operationType = "查询")
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public Object queryOne(String id) {
        SysTestStandardModel model = sysTestStandardService.getTestStandard(id);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, model);
    }

    @Authorization
    @ApiOperation(value = "根据ID更新标准信息")
    @SystemControllerLog(description = "根据ID更新标准信息",operationType = "更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(SysTestStandardModel model, String token) {
        boolean isUpdateStandardCode = true;
        ModelMap modelMap = new ModelMap();
        ResponseEntity<ModelMap> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }

        if (sysTestStandardService.isExistStandardCode(model.getStandardCode())) {
            isUpdateStandardCode = false;
            SysTestStandardModel model2 = sysTestStandardService.getTestStandard(model.getId());
            if (!model2.getStandardCode().equals(model.getStandardCode())) {
                responseEntity = setModelMap(modelMap, HttpCode.REPEAT_DATA);
                modelMap.put("msg","当前标准号已存在，保存失败");
                return responseEntity;
            }
        }
        model.setUpdateBy(user.getAccount());
        SysTestStandardModel model2 = sysTestStandardService.updateTestStandard(model, isUpdateStandardCode);

        return setSuccessModelMap(modelMap, model2);
    }

    @Authorization
    @ApiOperation(value = "新增标准信息")
    @SystemControllerLog(description = "新增标准信息",operationType = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(SysTestStandardModel model, String token) {
        ModelMap modelMap = new ModelMap();
        ResponseEntity<ModelMap> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }

        if (sysTestStandardService.isExistStandardCode(model.getStandardCode())) {
            responseEntity = setModelMap(modelMap, HttpCode.REPEAT_DATA);
            modelMap.put("msg","当前标准号已存在，保存失败");
            return responseEntity;
        }

        model.setCreateBy(user.getAccount());

        SysTestStandardModel model2 = sysTestStandardService.addTestStandard(model);
        return setSuccessModelMap(modelMap, model2);
    }

    @Authorization
    @ApiOperation(value = "删除标准信息")
    @SystemControllerLog(description = "删除标准信息",operationType = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(String ids, String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Integer deleteRows = sysTestStandardService.deleteTestStandards(ids, user.getAccount());
        Map<String, Integer> data = new HashMap<>();
        data.put("deleteRows", deleteRows); // 逻辑删除行数
        modelMap.put("data", data);
        return setSuccessModelMap(modelMap);
    }
}
