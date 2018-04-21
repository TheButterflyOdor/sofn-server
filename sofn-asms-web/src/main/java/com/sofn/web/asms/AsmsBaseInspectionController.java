package com.sofn.web.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.DateUtil;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AsmsBaseInspection;
import com.sofn.model.generator.AsmsInspectionTask;
import com.sofn.model.sys.SysUserBean;
import com.sofn.service.asms.AsmsBaseInspectionService;
import com.sofn.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * @author sofn
 * @version 2016年08月25日 下午 5:05
 */
@RestController
@Api(value = "基地巡查",description = "基地巡查")
@RequestMapping(value = "/baseInspection")
public class AsmsBaseInspectionController extends BaseController{
    @Autowired
    private AsmsBaseInspectionService baseInspectionService;

    /**
     * 新增基地巡查
     * @param asmsBaseInspection
     * @return
     */
    @ApiOperation(value = "新增基地巡查")
    @SystemControllerLog(description = "新增基地巡查",operationType = "添加")
    @RequestMapping(value = "/addBaseInspection",method = RequestMethod.POST)
    @Authorization
    public Object addBaseInspection(@RequestHeader String token,@RequestBody AsmsBaseInspection asmsBaseInspection,@RequestParam boolean saveCommonOpinion){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
           return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        int result = baseInspectionService.addBaseInspection(user,asmsBaseInspection,saveCommonOpinion);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else if(result == -1){
            ModelMap modelMap = new ModelMap();
            modelMap.put("httpCode", ApiMsgConstants.FAILED_CODE);
            modelMap.put("msg", "保存成功,消息推送失败");
            return modelMap;
        }else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "新增基地巡查--APP调用")
    @SystemControllerLog(description = "新增基地巡查--APP调用",operationType = "添加")
    @RequestMapping(value = "/addBaseInspectionForApp",method = RequestMethod.POST)
    @Authorization
    public Object addBaseInspectionForApp(@RequestHeader String token, HttpServletRequest request, String enterpriseId, String inspectionType,
                                          String inspectionResult, String inspectionView, String inspectionTime, String inspectionUserName,
                                          String headSign, String inspectionTypeName,String  inspectionSvId,String inspectionSvName,boolean saveCommonOpinion,
                                          String userIdcode, String enterpriseName){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        String inspectionImages = "";
        String inspectionImagesName = "";
        String headSignFile = "";
        String headSignFileName = "";
        try {
            //将当前上下文初始化
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request)){
                //将request变成多部分request
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
                //获取multiRequest中所有的文件名
                Iterator iter = multipartRequest.getFileNames();
                String path = request.getSession().getServletContext().getRealPath("upload");
                while(iter.hasNext()){
                    //一次便利所有文件
                    List<MultipartFile> fileList = multipartRequest.getFiles(iter.next().toString());
                    for(int i=0;i<fileList.size();i++) {
                        if ("inspectionImage".equals(fileList.get(i).getName())) {
                            if(i==fileList.size()-1){
                                inspectionImagesName  += fileList.get(i).getOriginalFilename();
                                inspectionImages+= "//"+FastDFSUtil.getUpliadFileAddress2(fileList.get(i));
                            }else {
                                inspectionImagesName += fileList.get(i).getOriginalFilename() + "*##*";
                                inspectionImages += "//"+FastDFSUtil.getUpliadFileAddress2(fileList.get(i)) + "*##*";
                            }
                        }
                        if ("headSignFile".equals(fileList.get(i).getName())) {
                            if(i==fileList.size()-1) {
                                headSignFileName = fileList.get(i).getOriginalFilename();
                                headSignFile = "//"+FastDFSUtil.getUpliadFileAddress2(fileList.get(i));
                            }else{
                                headSignFileName = fileList.get(i).getOriginalFilename() + "*##*";
                                headSignFile = "//"+FastDFSUtil.getUpliadFileAddress2(fileList.get(i)) + "*##*";
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AsmsBaseInspection asmsBaseInspection = new AsmsBaseInspection();
        asmsBaseInspection.setEnterpriseId(enterpriseId);
        asmsBaseInspection.setInspectionType(inspectionType);
        asmsBaseInspection.setInspectionResult(inspectionResult);
        asmsBaseInspection.setInspectionView(inspectionView);
        asmsBaseInspection.setInspectionTime(DateUtil.stringToDate(inspectionTime));
        asmsBaseInspection.setInspectionUserName(inspectionUserName);
        asmsBaseInspection.setHeadSign(headSign);
        asmsBaseInspection.setInspectionTypeName(inspectionTypeName);
        asmsBaseInspection.setInspectionImages(inspectionImages);
        asmsBaseInspection.setInspectionImagesName(inspectionImagesName);
        asmsBaseInspection.setHeadSignFile(headSignFile);
        asmsBaseInspection.setHeadSignFileName(headSignFileName);
        asmsBaseInspection.setInspectionSvId(inspectionSvId);
        asmsBaseInspection.setInspectionSvName(inspectionSvName);
        asmsBaseInspection.setUserIdcode(userIdcode);
        asmsBaseInspection.setEnterpriseName(enterpriseName);
        int result = baseInspectionService.addBaseInspection(user,asmsBaseInspection,saveCommonOpinion);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改基地巡查
     * @param asmsBaseInspection
     * @return
     */
    @ApiOperation(value = "修改基地巡查")
    @SystemControllerLog(description = "修改基地巡查",operationType = "修改")
    @RequestMapping(value = "/updateBaseInspection",method = RequestMethod.POST)
    @Authorization
    public Object updateBaseInspection(@RequestHeader String token,@RequestBody AsmsBaseInspection asmsBaseInspection,@RequestParam boolean saveCommonOpinion){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        int result = baseInspectionService.addBaseInspection(user,asmsBaseInspection,saveCommonOpinion);
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据条件获取基地巡查列表
     * @param asmsBaseInspection
     * @return
     */
    @ApiOperation(value = "获取基地巡查列表")
    @SystemControllerLog(description = "获取基地巡查列表",operationType = "查询")
    @RequestMapping(value = "/getBaseInspectionAllList",method = RequestMethod.POST)
    @Authorization
    public Object getBaseInspectionAllList(@RequestHeader String token, AsmsBaseInspection asmsBaseInspection ,String dateBegin,String dateEnd,
                                           Page page,String enterpriseIndustryId,String entityScaleId,String enterpriseName,String area){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        //只能获取当前机构
        asmsBaseInspection.setInspectionSvId(user.getOrganization().getOrgId());
        PageInfo pageInfo = baseInspectionService.getBaseInspectionAllList(asmsBaseInspection,dateBegin,dateEnd,
                page,enterpriseIndustryId,entityScaleId,enterpriseName,area);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据人员获取对应基地巡查列表
     * @param asmsBaseInspection
     * @return
     */
    @ApiOperation(value = "获取巡查人员相关的基地巡查列表")
    @SystemControllerLog(description = "获取巡查人员相关的基地巡查列表",operationType = "查询")
    @RequestMapping(value = "/getBaseInspectionList",method = RequestMethod.POST)
    @Authorization
    public Object getBaseInspectionList(@RequestHeader String token, AsmsBaseInspection asmsBaseInspection , String dateBegin, String dateEnd, Page page,
                                        String inspectionPersonId, String inspectionType, String enterpriseName, String area, AsmsInspectionTask task){

        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo pageInfo = baseInspectionService.getBaseInspectionList(asmsBaseInspection,dateBegin,dateEnd,
                page,inspectionPersonId,inspectionType,enterpriseName,area,task);
        page.setRecordsTotal(pageInfo.getTotal());
        List list = pageInfo.getList();
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "根据机构ID获取机构内部用户")
    @SystemControllerLog(description = "根据机构ID获取机构内部用户",operationType = "查询")
    @RequestMapping(value = "/getSysUserListByOrgId",method = RequestMethod.POST)
    @Authorization
    public Object getSysUserListByOrgId(@RequestHeader String token,Page page,String orgId,String userName){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        PageInfo<SysUserBean> pageInfo = baseInspectionService.getSysUserListByOrgId(page,user.getOrganization().getOrgId(),userName);
        page.setRecordsTotal(pageInfo.getTotal());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("list",pageInfo.getList());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据ID获取单个基地巡查详情
     * @param asmsBaseInspection
     * @return
     */
    @ApiOperation(value = "获取单个基地巡查详情")
    @SystemControllerLog(description = "获取单个基地巡查详情",operationType = "查询")
    @RequestMapping(value = "/findBaseInspectionById",method = RequestMethod.POST)
    @Authorization
    public Object findBaseInspectionById(@RequestHeader String token,@RequestBody AsmsBaseInspection asmsBaseInspection){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        asmsBaseInspection = baseInspectionService.findBaseInspectionById(asmsBaseInspection.getId());
        return setSuccessModelMap(new ModelMap(),asmsBaseInspection);
    }

    /**
     * 删除基地巡查
     * @param asmsBaseInspection
     * @return
     */
    @ApiOperation(value = "删除基地巡查")
    @SystemControllerLog(description = "删除基地巡查",operationType = "删除")
    @RequestMapping(value = "/deleteBaseInspection",method = RequestMethod.POST)
    @Authorization
    public Object deleteBaseInspection(@RequestHeader String token,AsmsBaseInspection asmsBaseInspection){
        int result = baseInspectionService.deleteBaseInspection(asmsBaseInspection.getId());
        if(result==1){
            return setSuccessModelMap(new ModelMap());
        }else {
            return setModelMap(new ModelMap(),HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "获取用户常用意见列表")
    @SystemControllerLog(description = "获取用户常用意见列表",operationType = "查询")
    @RequestMapping(value = "/getOpinionListByUserId",method = RequestMethod.POST)
    @Authorization
    public Object getOpinionListByUserId(@RequestHeader String token){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(user==null||user.getId().isEmpty()){
            return setModelMap(new ModelMap(),HttpCode.UNAUTHORIZED);
        }
        List list = baseInspectionService.getOpinionListByUserId(user.getId());
        return  setSuccessModelMap(new ModelMap(),list);
    }

}
