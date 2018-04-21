package com.sofn.web.sys;

import com.alibaba.dubbo.common.json.JSON;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.*;
import com.sofn.core.persistence.Page;
import com.sofn.core.rabbitmq.RabbitMqProducer;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.RabbitMQSend;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysTemplate;
import com.sofn.service.sys.SysTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

/**
*	模板管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "模板管理", description = "模板管理")
@RequestMapping(value = "/sysTemplate",method = RequestMethod.POST)
public class SysTemplateController extends BaseController {

	@Autowired
    public SysTemplateService sysTemplateService;

    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    @Autowired
    private RabbitMQSend rabbitMQSend;
    
     /**
     * 根据条件获取模板管理列表
     * @param
     * @return
     */
    @Authorization
    @ApiOperation(value = "获取模板管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(ModelMap modelMap,@RequestParam(value = "token", required = false) String token, @ApiParam(required = true, value = "模板名称") @RequestParam(value = "templateName", required = false) String templateName,
                              @ApiParam(required = true, value = "模板类型") @RequestParam(value = "templateType", required = false) String templateType,
                              @ApiParam(required = true, value = "类型标志") @RequestParam(value = "flags", required = false) String flags,
                              @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                              @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,
                              @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length, HttpServletResponse response) {
        if(templateType.equals("全部")){
            templateType = "";
        }
        CurrentUser currentUser = WebUtil.getCurrentUser(token);
        Organization organization = currentUser.getOrganization();
        String orgType = "";
        String regionId = "";
        String orgLevel= "";
        if(organization != null){
            orgType = currentUser.getOrganization().getOrgType();
            if(!"1".equals(currentUser.getOrganization().getOrgLevel())){
                orgLevel= currentUser.getOrganization().getOrgLevel();
                regionId = currentUser.getOrganization().getRegionId();
                if(StringUtils.isNotEmpty(regionId)){
                    regionId = regionId.substring(0,"2".equals(orgLevel)?2:("3".equals(orgLevel)?4:6));
                }
            }
        }
        long recordsTotal = sysTemplateService.getRecordsTotal(templateName,templateType,flags,"", orgType,regionId,orgLevel);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setDraw(draw);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        List<SysTemplate> list = sysTemplateService.getPageInfo(pager,templateName,templateType,flags, "", orgType,regionId,orgLevel);
        modelMap.addAttribute("page",pager);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }



    /**
     * 根据条件获取模板管理列表
     * @param
     * @return
     */
    @ApiOperation(value = "获取模板管理信息列表")
    @RequestMapping(value = "/getHomePageInfo",method = RequestMethod.GET)
    public void getHomePageInfo(String callback,
                              @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                              @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,
                              @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length,
                                  HttpServletResponse response) throws IOException {

        String templateType = "",templateName="",flags="homeFile", status = "Y";
        //CurrentUser currentUser = WebUtil.getCurrentUser(token);
        String orgType = "";
        String regionId = "";
        String orgLevel= "";
        long recordsTotal = sysTemplateService.getRecordsTotal(templateName,templateType,flags,status, orgType,regionId,orgLevel);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setDraw(draw);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        String result = "";
        Map map = new HashMap<String,String>();
        List<SysTemplate> list = sysTemplateService.getPageInfo(pager,templateName,templateType,flags,status, orgType,regionId,orgLevel);
        if(list.isEmpty()){
            result = "{\"httpCode\":"+ 200 +",\"msg\": \"暂无记录\"}";
        }else{
            map.put("list",list);
            map.put("page",pager);
            map.put("httpCode",200);
            result = JSON.json(map);
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        result=callback+"("+result+")";
        writer.write(result);
        writer.flush();
        writer.close();
    }

    /**
     * 模板文件上传
     * @param request
     * @return
     */
    @ApiOperation(value = "模板文件上传")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Map<String,Object> uploadTemplate(HttpServletRequest request) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        String path = this.uploadFile(request);
        //File pdfFile = this.word2PDF(request);
        //String pdfPath = this.uploadFile1(pdfFile);
        //获取文件别名
        String templeName = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiReuqest = (MultipartHttpServletRequest) request;
            Iterator iter = multiReuqest.getFileNames();
            while(iter.hasNext()){
                MultipartFile file = multiReuqest.getFile(iter.next().toString());
                templeName = file.getOriginalFilename();
            }
        }
        //pdfFile.delete();
        map.put("templeName",templeName);
        map.put("path","http://"+path);
        //map.put("pdfPath","http://"+pdfPath);
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG,ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

	/**
     * 新增模板管理记录
     * @param sysTemplate
     * @return
     */
	@Authorization
    @ApiOperation(value = "新增模板管理数据")
    @RequestMapping(value = "/addSysTemplate",method = RequestMethod.POST)
    public Object addSysTemplate(@RequestBody SysTemplate  sysTemplate){
        SysTemplate s = sysTemplateService.getTemplateByName(sysTemplate.getTemplateName());
        if(s != null){
            return setModelMap(new ModelMap(),HttpCode.CONFLICT,null);
        }
    	try{
    		sysTemplateService.addTemplate(sysTemplate);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return setFailModelMap(new ModelMap(),e.getMessage());
        }
    }


	 /**
     * 根据ID获取单条模板管理数据信息
     * @param sysTemplate
     * @return
     */
	@Authorization
    @ApiOperation(value = "获取单条模板管理数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody SysTemplate  sysTemplate){
        sysTemplate = sysTemplateService.queryById(sysTemplate.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("sysTemplate",sysTemplate);
        return map;
    }

    /**
     * 根据ID获取单条模板管理数据信息2
     * @param
     * @return
     */
    @ApiOperation(value = "获取单条模板管理数据信息2")
    @RequestMapping(value = "/queryById2",method = RequestMethod.POST)
    public Map<String,Object> queryById2(String tempId){
        SysTemplate sysTemplate = new SysTemplate();
        sysTemplate = sysTemplateService.queryById(tempId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("sysTemplate",sysTemplate);
        return map;

    }
    
     /**
     * 修改模板管理数据信息
     * @param sysTemplate
     * @return
     */
    @Authorization
    @ApiOperation(value = "修改模板管理数据信息")
    @RequestMapping(value = "/updateSysTemplate",method = RequestMethod.POST)
    public Object updateSysTemplate(@RequestBody SysTemplate sysTemplate){
        SysTemplate sysTemplate1 = sysTemplateService.queryById(sysTemplate.getId());
      	try{
            if(sysTemplate.getTemplateFilename()==""||sysTemplate.getTemplateFilename()==null){
                sysTemplate.setTemplateFilename(sysTemplate1.getTemplateFilename());
            }
            sysTemplate.setStatus(sysTemplate1.getStatus());
            sysTemplate.setCreateBy(sysTemplate1.getCreateBy());
            sysTemplate.setCreateTime(sysTemplate1.getCreateTime());
            if(sysTemplate.getFileAddress()==null||sysTemplate.getFileAddress()==""){
                sysTemplate.setPdfAddress(sysTemplate1.getPdfAddress());
                sysTemplate.setFileAddress(sysTemplate1.getFileAddress());
            }
            sysTemplate.setDelFlag("N");
            sysTemplate.setUpdateTime(new Date());
            sysTemplate.setUpdateBy(WebUtil.getCurrentUserId());
    		sysTemplateService.update(sysTemplate);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            //return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            return null;
        }
    }
    
    
     /**
     * 删除模板管理信息
     * @param sysTemplate
     * @return
     */
    @ApiOperation(value = " 删除模板管理信息")
    @RequestMapping(value = "/deleteSysTemplate",method = RequestMethod.POST)
    public Object deleteSysTemplate(SysTemplate sysTemplate){
   	    try{
    		sysTemplateService.deleteTemplate(sysTemplate.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
           // return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    /**
     * 批量删除
     * @return
     */
    @ApiOperation(value="批量删除模板信息")
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
    public Object deleteAll(String idStr){
        SysTemplate sysTemplate = new SysTemplate();
        ModelMap modelMap = new ModelMap();
        //过滤id字符串为空
        if(!StringUtils.isNullEmpty(idStr)){
            String[]ids=idStr.split(",");
            for (String id:ids){
                sysTemplate = sysTemplateService.queryById(id);
                //伪删除(修改)角色信息
                if(sysTemplate==null) {
                    logger.error("com.sofn.service.sys.SysRoleService.saveSysRole(String idStr):No user role found for ID :{}" + id);
                }else{
                    sysTemplate.setDelFlag("Y");
                    sysTemplateService.update(sysTemplate);
                }
            }
        }
        return setSuccessModelMap(modelMap);
    }


    @ApiOperation(value="发送")
    @RequestMapping(value = "send",method = RequestMethod.POST)
    public void sendMessage(String msg){
        rabbitMQSend.TopicSend("sofn.sys","safsafdsafsaf");
    }

    @ApiOperation(value = "下载")
    @RequestMapping(value = "/dowload",method = RequestMethod.POST)
    public ResponseEntity<byte[]> fileDowload(@RequestParam(value = "fileUrl", required = false) String fileUrl,@RequestParam(value = "fileName", required = false) String fileName) throws Exception {
        return FastDFSUtil.dowloadFile(fileUrl,fileName);
    }

    @ApiOperation(value = "下载图片返现")
    @RequestMapping(value = "/downPic",method = RequestMethod.GET)
    public void downPic(@RequestParam(value = "fileUrl", required = false) String fileUrl,@RequestParam(value = "fileName", required = false) String fileName,HttpServletRequest request, HttpServletResponse response) throws Exception {

        FastDFSUtil.dowloadtu(fileUrl,fileName,request,response);

    }

    /**
     * 删除模板管理信息
     * @param id
     * * @param status
     * @return
     */
    @Authorization
    @ApiOperation(value = " 删除模板管理信息")
    @RequestMapping(value = "/updateTempStatus",method = RequestMethod.POST)
        public Object updateTempStatus(@RequestParam("enable") String status , @RequestParam("id") String id){
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",id);
            map.put("status",status);
//            map.put("flags", status.equals("Y") ? "homeFile" : null );
            sysTemplateService.updateTempStatus(map);
            return setSuccessModelMap(new ModelMap());
        }catch (Exception e){
            // return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    /**
     * 文件下载
     *
     * 前端请求该方法时，如果不使用form提交，建议使用encodeURIComponent(uri) JS全局函数对参数进行编码
     *
     * @param fileUrl 文件存储地址（举个例子：http://10.0.51.100/dfs/group2/M00/00/1A/CgAzG1pcPUCAbnq1AAAFliuqkDQ32..jpg）
     * @param fileName 文件名（不传文件名，下载的时候从文件元数据中获取文件名）
     * @throws Exception
     */
    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileUrl, String fileName) throws Exception {
        FastDFSUtil.download(fileUrl, fileName, request, response);
    }
}

