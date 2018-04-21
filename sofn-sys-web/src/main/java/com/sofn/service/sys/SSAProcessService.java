//package com.sofn.service.sys;
//
//import com.alibaba.fastjson.JSONArray;
//import com.sofn.core.base.BaseService;
//import com.sofn.core.constant.CurrentUser;
//import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
//import com.sofn.provider.ssa.SSAProcessProvider;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.ModelMap;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by 110 on 2016/11/17.
// */
//@Service
//public class SSAProcessService extends BaseService<SSAProcessProvider, CurrentUser> {
//
//    @DubboReference
//    public void setProvider(SSAProcessProvider provider) {
//        this.provider = provider;
//    }
//
//    /**
//     *
//     * @param modelMap
//     * @param request
//     * @return
//     */
//    public JSONArray processList(ModelMap modelMap, HttpServletRequest request){
//
//        return provider.processList(modelMap,request);
//    }
//
//    /**
//     *
//     *运行中流程列表
//     * @param modelMap
//     * @param request
//     * @return
//     */
//    public JSONArray runningList(ModelMap modelMap, HttpServletRequest request){
//        return provider.runningList(modelMap,request);
//    }
//
//
//    /**
//     * 模型列表
//     *
//     * @param modelMap
//     * @param request
//     * @return
//     */
//    public JSONArray modelList(ModelMap modelMap, HttpServletRequest request) {
//        return provider.runningList(modelMap,request);
//    }
//
//}
