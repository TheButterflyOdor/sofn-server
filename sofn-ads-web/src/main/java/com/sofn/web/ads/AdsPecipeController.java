package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;

import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AdsPecipe;
import com.sofn.service.ads.AdsPecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 回执单 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "回执单", description = "回执单")
@RequestMapping(value = "/adsPecipe", method = RequestMethod.POST)
public class AdsPecipeController extends BaseController {

    @Autowired
    private AdsPecipeService adsPecipeService;

    /**
     * 根据条件获取回执单列表
     *
     * @param adsPecipe
     * @return
     */
    @ApiOperation(value = "获取回执单信息列表")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsPecipe adsPecipe, int start2, int length2) {
        try {
            PageInfo<AdsPecipe> pageInfo = adsPecipeService.getPageInfo(adsPecipe, ((start2 + 1) / length2) + 1, length2);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取回执单列表
     *
     * @param adsPecipe
     * @return
     */
    @ApiOperation(value = "获取回执单信息列表")
    @RequestMapping(value = "/getAdsPecipePageInfo", method = RequestMethod.POST)
    public Map<String, Object> getAdsPecipePageInfo(AdsPecipe adsPecipe, int start, int length) {
        PageInfo<AdsPecipe> adsPecipepageInfo = adsPecipeService.getAdsPecipePageInfo(adsPecipe, ((start + 1) / length) + 1, length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("adsPecipepageInfo", adsPecipepageInfo);
        return map;
    }

    /**
     * 新增回执单记录
     *
     * @param adsPecipe
     * @return
     */
    @ApiOperation(value = "新增回执单数据")
    @RequestMapping(value = "/addadsPecipe", method = RequestMethod.POST)
    public Object addadsPecipe(AdsPecipe adsPecipe, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsPecipe.setCreateBy(u.getId());
                int result = adsPecipeService.insert(adsPecipe);
                if (result > 0) {
                    return setSuccessModelMap(new ModelMap());
                } else {
                    return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
                }
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID获取单条回执单数据信息
     *
     * @param adsPecipe
     * @return
     */
    @ApiOperation(value = "获取单条回执单数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public Map<String, Object> queryById(@RequestBody AdsPecipe adsPecipe) {
        adsPecipe = adsPecipeService.queryById(adsPecipe.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsPecipe", adsPecipe);
        return map;
    }

    /**
     * 修改回执单数据信息
     *
     * @param adsPecipe
     * @return
     */
    @ApiOperation(value = "修改回执单数据信息")
    @RequestMapping(value = "/updateadsPecipe", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> updateadsPecipe(@RequestBody AdsPecipe adsPecipe) {
        try {
            adsPecipeService.update(adsPecipe);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除回执单信息
     *
     * @param adsPecipe
     * @return
     */
    @ApiOperation(value = " 删除回执单信息")
    @RequestMapping(value = "/deleteadsPecipe", method = RequestMethod.POST)
    public ResponseEntity<ModelMap> deleteadsPecipe(AdsPecipe adsPecipe) {
        try {
            adsPecipeService.delete(adsPecipe.getId());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
}

