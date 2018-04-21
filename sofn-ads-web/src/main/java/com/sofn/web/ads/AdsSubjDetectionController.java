package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseController;

import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.AsmsSubjDetection;
import com.sofn.service.ads.AdsSubjDetectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *	检测机构 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "检测机构", description = "检测机构")
@RequestMapping(value = "/adsSubjDetection",method = RequestMethod.POST)
public class AdsSubjDetectionController extends BaseController {

    @Autowired
    private AdsSubjDetectionService adsSubjDetectionService;

    /**
     * 获取检测机构信息列表
     * @param detection
     * @return
     */
    @ApiOperation(value = "获取检测机构信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(AsmsSubjDetection detection, int start, int length) {
        try{
            PageInfo list = adsSubjDetectionService.getPageInfo(detection,((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(),list);
        }catch (Exception e){
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

}

