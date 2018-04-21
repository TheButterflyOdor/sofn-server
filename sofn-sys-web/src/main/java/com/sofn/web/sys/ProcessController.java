//package com.sofn.web.sys;
//
//import com.alibaba.fastjson.JSONArray;
//import com.sofn.core.base.BaseController;
//import com.sofn.service.sys.SSAProcessService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by qjh on 2016/11/17.
// */
//
//@RestController
//@Api(value = "流程引擎接口", description = "流程引擎接口")
//@RequestMapping(value = "/process")
//public class ProcessController extends BaseController {
//
//    private final Logger logger = LogManager.getLogger(ProcessController.class);
//
//
//    @Autowired
//    private SSAProcessService ssaProcessService;
//
//
//
//    @ApiOperation(value = "流程定义及部署管理列表")
//    @RequestMapping(value = "/getProcessList",method = RequestMethod.POST)
//    public JSONArray getProcessList(){
//
//        ModelMap modelMap = new ModelMap();
//
//        return  ssaProcessService.processList(modelMap,null);
//    }
//
//
//
//
//
//
//}
