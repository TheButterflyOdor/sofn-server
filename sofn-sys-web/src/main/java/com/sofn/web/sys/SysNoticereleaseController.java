package com.sofn.web.sys;

import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysDept;
import com.sofn.model.generator.SysNoticerelease;
import com.sofn.model.generator.SysOrganization;
import com.sofn.service.sys.SysDeptService;
import com.sofn.service.sys.SysNoticereleaseService;
import com.sofn.service.sys.SysOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	公告管理 controller 控制器实现
 * Created by moon.l 
 */
@RestController
@Api(value = "公告管理", description = "公告管理")
@RequestMapping(value = "/sysNoticerelease",method = RequestMethod.POST)
public class SysNoticereleaseController extends BaseController {

	@Autowired
    public SysNoticereleaseService sysNoticereleaseService;

    @Autowired
    public SysOrganizationService sysOrganizationService;

    @Autowired
    public SysDeptService sysDeptService;
    
     /**
     * 根据条件获取公告管理列表
     * @param
     * @return
     */
    @Authorization
    @ApiOperation(value = "获取公告管理信息列表")
    @RequestMapping(value = "/getPageInfo",method = RequestMethod.POST)
    public Object getPageInfo(ModelMap modelMap, @ApiParam(required = true, value = "类型") @RequestParam(value = "noticeType", required = false) String noticeType,
                              @ApiParam(required = true, value = "标题") @RequestParam(value = "title", required = false) String title,
                              @ApiParam(required = true, value = "发布时间") @RequestParam(value = "releaseTime", required = false) String releaseTime,
                              @ApiParam(required = true, value = "发布人") @RequestParam(value = "releasePerson", required = false) String releasePerson,
                              @ApiParam(required = true, value = "页数") @RequestParam(value = "draw", required = false) long draw,
                              @ApiParam(required = true, value = "开始数") @RequestParam(value = "start", required = false) long start,

                              @ApiParam(required = true, value = "数量") @RequestParam(value = "length", required = false) long length) throws ParseException {

        if(noticeType.equals("全部")){
            noticeType = "";
        }
        long recordsTotal = sysNoticereleaseService.getRecordsTotal(noticeType,title,releaseTime,releasePerson);
        Page pager = new Page();
        pager.setRecordsTotal(recordsTotal);
        pager.setDraw(draw);
        pager.setStart(start);
        pager.setLength(length);
        pager.doPage();
        List<SysNoticerelease> list = sysNoticereleaseService.getPageInfo(pager,noticeType,title,releaseTime,releasePerson);

        modelMap.addAttribute("page",pager);
        modelMap.addAttribute("list",list);
        return setSuccessModelMap(modelMap);
    }

	/**
     * 新增公告管理记录
     * @param sysNoticerelease
     * @return
     */
	@Authorization
    @ApiOperation(value = "新增公告管理数据")
    @RequestMapping(value = "/addSysNoticerelease",method = RequestMethod.POST)
    public Object addSysNoticerelease(@RequestBody SysNoticerelease  sysNoticerelease,@RequestParam("releaseTime") String  releaseTime){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
        sysNoticerelease.setReleaseTime(sf.parse(releaseTime));
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
        sysNoticereleaseService.addNotice(sysNoticerelease);
        return setSuccessModelMap(new ModelMap());
    }


	 /**
     * 根据ID获取单条公告管理数据信息
     * @param sysNoticerelease
     * @return
     */
	@Authorization
    @ApiOperation(value = "获取单条公告管理数据信息")
    @RequestMapping(value = "/queryById",method = RequestMethod.POST)
    public Map<String,Object> queryById(@RequestBody SysNoticerelease  sysNoticerelease){
        sysNoticerelease = sysNoticereleaseService.queryById(sysNoticerelease.getId());
        SysOrganization  sysOrganization = null ;
        if(sysNoticerelease != null && StringUtils.isNotEmpty(sysNoticerelease.getOrganId()))
            sysOrganization = sysOrganizationService.queryById(sysNoticerelease.getOrganId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("sysNoticerelease",sysNoticerelease);
        if(sysOrganization != null)
            map.put("orgName",sysOrganization.getOrgName());
        return map;
    }
    
     /**
     * 修改公告管理数据信息
     * @param sysNoticerelease
     * @return
     */
    @Authorization
    @ApiOperation(value = "修改公告管理数据信息")
    @RequestMapping(value = "/updateSysNoticerelease",method = RequestMethod.POST)
    public Object updateSysNoticerelease(@RequestBody SysNoticerelease sysNoticerelease,@RequestParam("releaseTime") String releaseTime){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            sysNoticerelease.setReleaseTime(sf.parse(releaseTime));
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
      	try{
    		sysNoticereleaseService.updateNotice(sysNoticerelease);
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            return null;
        }
    }
    
    
     /**
     * 删除公告管理信息
     * @param sysNoticerelease
     * @return
     */
    @ApiOperation(value = " 删除公告管理信息")
    @RequestMapping(value = "/deleteSysNoticerelease",method = RequestMethod.POST)
    public Object deleteSysNoticerelease(SysNoticerelease sysNoticerelease){
   	    try{
    		sysNoticereleaseService.deleteNotice(sysNoticerelease.getId());
    		return setSuccessModelMap(new ModelMap());
    	}catch (Exception e){
            //return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    /**
     * 批量删除
     * @return
     */
    @Authorization
    @ApiOperation(value="批量删除公告信息")
    @RequestMapping(value="/deleteAll",method = RequestMethod.POST)
    public Object deleteAll(String idStr){
        SysNoticerelease sysNoticerelease = new SysNoticerelease();
        ModelMap modelMap = new ModelMap();
        //过滤id字符串为空
        if(!StringUtils.isNullEmpty(idStr)){
            String[]ids=idStr.split(",");
            for (String id:ids){
                sysNoticerelease = sysNoticereleaseService.queryById(id);
                //伪删除(修改)角色信息
                if(sysNoticerelease==null) {
                    logger.error("com.sofn.service.sys.SysRoleService.saveSysRole(String idStr):No user role found for ID " + id);
                }else{

                    sysNoticerelease.setDelFlag("Y");
                    sysNoticereleaseService.update(sysNoticerelease);
                }
            }
        }
        return setSuccessModelMap(modelMap);
    }


    /**
     * 获取所有机构
     * @return
     */
    @Authorization
    @ApiOperation(value = "获取所有机构")
    @RequestMapping(value = "/addSysNoticereInit",method = RequestMethod.POST)
    public Object addSysNoticereInit(ModelMap modelMap,@RequestParam(value = "token", required = false) String token){
        List<SysOrganization> orList = sysOrganizationService.selectAll();
        CurrentUser u = WebUtil.getCurrentUser(token);
        modelMap.addAttribute("orList",orList);
        modelMap.addAttribute("user",u);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据机构ID获取对应部门
     * @param modelMap
     * @param orId
     * @return
     */
    @ApiOperation(value = "据机构ID获取对应部门")
    @RequestMapping(value = "/findDeptByOrganId",method = RequestMethod.POST)
    public Object findDeptByOrganId(ModelMap modelMap,String orId){
        List<SysDept> deptList = sysDeptService.getDeptListByRegion(orId);
        modelMap.addAttribute("deptList",deptList);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 获取所有机构
     * @return
     */
    @ApiOperation(value = "获取所有机构")
    @RequestMapping(value = "/findOrganization",method = RequestMethod.POST)
    public Object findOrganization(ModelMap modelMap){
        List<SysOrganization> orList = sysOrganizationService.selectAll();
        modelMap.addAttribute("orList",orList);
        return setSuccessModelMap(modelMap);
    }
    public static void main(String args[]){

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sf.format("");
        System.out.println();
    }
}

