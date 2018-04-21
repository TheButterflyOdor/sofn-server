package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.SysPesticideResidueModel;
import com.sofn.model.generator.SysTestItemModel;
import com.sofn.model.generator.SysTestStandardModel;
import com.sofn.service.sys.SysPesticideResidueService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21/021.
 */
@RestController
@Api(value = "检测农药残留限量信息接口", description = "检测农药残留限量信息接口")
@RequestMapping(value = "/sysPesticideResidue")
public class SysPesticideResidueController extends BaseController {
    @Autowired
    private SysPesticideResidueService sysPesticideResidueService;

    @Authorization
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Object queryList(String standardCode, String testItemName, String testObjectName, Page page) {
        //page.setStart(page.getStart() / page.getLength());
        page.setPageNumber((page.getStart() / page.getLength()) + 1);
        PageInfo<SysPesticideResidueModel> pageInfo = sysPesticideResidueService.getPesticideResidues(standardCode, testItemName, testObjectName, page);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, pageInfo);
    }

    @Authorization
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public Object queryOne(String id) {
        ModelMap modelMap = new ModelMap();
        SysPesticideResidueModel model = sysPesticideResidueService.getPesticideResidue(id);
        return setSuccessModelMap(modelMap, model);
    }

    @Authorization
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(SysPesticideResidueModel model, String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }

        model.setUpdateBy(user.getAccount());
        SysPesticideResidueModel model2 = sysPesticideResidueService.updatePesticideResidue(model);
        return setSuccessModelMap(modelMap, model2);
    }

    @Authorization
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(SysPesticideResidueModel model, String token) {
        ModelMap modelMap = new ModelMap();
        ResponseEntity<ModelMap> responseEntity = new ResponseEntity(HttpStatus.OK);

        CurrentUser user = WebUtil.getCurrentUser(token);
        //未登录
        if (user == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }

/*        if (sysPesticideResidueService.isExistPesticideResidue(model.getTestItemId(), model.getTestObjectId())) {
            responseEntity = setModelMap(modelMap, HttpCode.REPEAT_DATA);
            modelMap.put("msg","检测标准限量值已存在，操作失败");
            return responseEntity;
        }*/

        model.setCreateBy(user.getAccount());
        SysPesticideResidueModel model2 = sysPesticideResidueService.addPesticideResidue(model);
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

        Integer deleteRows = sysPesticideResidueService.deletePesticideResidue(ids, user.getAccount());
        Map<String, Integer> data = new HashMap<>();
        data.put("deleteRows", deleteRows); // 逻辑删除行数
        modelMap.put("data", data);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/getTestObjects", method = RequestMethod.POST)
    public Object getTestObjects(String typeId, String productName, int pageNum) {
        PageInfo<SysArgiProduct> pageInfo = sysPesticideResidueService.getArgiProductsByName(typeId, productName, pageNum);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, pageInfo);
    }

    @RequestMapping(value = "/getTestItems", method = RequestMethod.POST)
    public Object getTestItems(String itemName, String standardCode, int pageNum) {
        PageInfo<SysTestItemModel> pageInfo = sysPesticideResidueService.getTestItems(itemName, standardCode, pageNum);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, pageInfo);
    }

    @RequestMapping(value = "/getAllStandardCodes", method = RequestMethod.POST)
    public Object getAllStandardCodes() {
        ModelMap modelMap = new ModelMap();
        List<SysTestStandardModel> list = sysPesticideResidueService.getAllStandardCodes();
        return setSuccessModelMap(modelMap, list);
    }
}
