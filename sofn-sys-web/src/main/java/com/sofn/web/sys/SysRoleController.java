package com.sofn.web.sys;

import com.alibaba.fastjson.JSONArray;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysOrganization;
import com.sofn.model.generator.SysRole;
import com.sofn.model.generator.SysRoleMenu;
import com.sofn.model.sys.SysRoleBean;
import com.sofn.service.sys.SysOrganizationService;
import com.sofn.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author qmy
 * @version 2016年10月14日 下午 3:05
 */
@RestController
@Api(value = "角色信息", description = "角色信息")
@RequestMapping(value = "/sysRole",method = RequestMethod.POST)
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysOrganizationService sysOrgService;

    /**
     * 新增角色和权限菜单
     * @param sysRole 角色对象
     * @param token 登录token
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "新增角色",operationType="增加")
    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/addSysRole", method = RequestMethod.POST)
    public Object addSysRole(@RequestBody SysRole sysRole,
                             @RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
        //配置修改时间、修改人
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("organizationId",u.getOrganizationId()==null?"":u.getOrganizationId());
        param.put("roleName",sysRole.getRoleName());
        //查询是否已存在此机构的角色
        List<SysRole> list=sysRoleService.queryByParam(param);
        if(list!=null&&list.size()>0){
            return setModelMap(modelMap, HttpCode.REPEAT_DATA);
        }
        sysRole.setCreateBy(u.getId());
        sysRole.setUpdateBy(u.getId());
        SysRole role = sysRoleService.saveSysRole(sysRole);
        modelMap.addAttribute("id", role.getId());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 修改角色和权限菜单
     * @param sysRole 角色对象
     * @param menuStr 菜单ID串
     * @param token 登录token
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "修改角色",operationType="修改")
    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/updateSysRole", method = RequestMethod.POST)
    public Object updateSysRole( @RequestBody SysRole sysRole, String menuStr,
                                @RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap=new ModelMap();
        //配置修改时间、修改人
        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        if (StringUtils.isNullEmpty(sysRole.getId())) {
            return setModelMap(modelMap, HttpCode.BAD_REQUEST);
        }
        SysRole rOld = sysRoleService.queryById(sysRole.getId());
        if (rOld == null)
            return setModelMap(modelMap, HttpCode.NOT_FOUND_DATA);

        Map<String,Object> param=new HashMap<String,Object>();
        param.put("roleName",sysRole.getRoleName());
        param.put("organizationId",u.getOrganizationId()==null?"":u.getOrganizationId());
        List<SysRole> list=sysRoleService.queryByParam(param);
        if(list.size() > 0 && !rOld.getRoleName().equals(sysRole.getRoleName())){
            return setModelMap(modelMap,HttpCode.REPEAT_DATA,null);
        }
        //替换创建时间、创建人、删除标识、排序ID
        sysRole.setCreateBy(rOld.getCreateBy());
        sysRole.setCreateTime(rOld.getCreateTime());
        if (sysRole.getDelFlag() == null)
            sysRole.setDelFlag(rOld.getDelFlag());
        if (sysRole.getSortid() == null)
            sysRole.setSortid(rOld.getSortid());
        //配置修改时间、修改人
        sysRole.setUpdateBy(u.getId());
        sysRole.setUpdateTime(new Date());
        SysRole role = sysRoleService.saveSysRole(sysRole);
        modelMap.addAttribute("id", role.getId());
        return setSuccessModelMap(modelMap);
    }

    /**
     * 修改角色权限菜单
     * @param id 角色ID
     * @param menusJson 权限集合的json串
     * @param token 登录token
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "修改角色权限",operationType="修改")
    @ApiOperation(value = "修改角色权限")
    @RequestMapping(value = "/updateSysRoleMenus", method = RequestMethod.POST)
    public Object updateSysRoleMenus( String id , String menusJson,
                                     @RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap=new ModelMap();
        //配置修改时间、修改人
        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        List<SysRoleMenu> menus= JSONArray.parseArray(menusJson,SysRoleMenu.class);
        SysRole rOld = sysRoleService.queryById(id);
        if (rOld == null)
            return setModelMap(modelMap, HttpCode.NOT_FOUND);
        //配置修改时间、修改人
        rOld.setUpdateBy(u.getId());
        SysRole role = sysRoleService.saveSysRoleMenus(rOld, menus);
        modelMap.addAttribute("id", role.getId());
        return setSuccessModelMap(modelMap);

    }
    /**
     * 单个或批量删除角色信息
     * @param idStr 要删除的角色ID串，以英文逗号分隔
     * @param token 登录token
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "删除角色信息",operationType="删除")
    @ApiOperation(value = "删除角色信息")
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public Object deleteRole(ModelMap modelMap,String idStr,
                             @RequestHeader(value = "token", defaultValue = "") String token) {
        //配置修改时间、修改人
        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);

        String result = sysRoleService.deleteSysRole(idStr,u.getId());
        return setFailModelMap(modelMap,result);
    }
    /**
     * 获取角色分页信息列表
     * @param
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "获取角色数据列表",operationType="查询")
    @ApiOperation(value = "获取角色分页信息列表")
    @RequestMapping(value = "/getSysRoleList", method = RequestMethod.POST)
    public Object getSysRoleList(ModelMap modelMap,long draw ,long start,long length, String roleName,String status,@RequestHeader(value = "token", defaultValue = "") String token) {
        //从登录缓存中获取机构信息
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        //判断超级管理员权限

        String rId=u.getUserType().equals("SUPERADMIN")?null:u.getRoleId();
        String userId = u.getUserType().equals("SUPERADMIN")?null:u.getId();
        int count=sysRoleService.getRecordsTotal(roleName, u != null ? u.getOrganizationId(): null,userId,status,rId);
        System.out.println("oId:"+ (u != null ? u.getOrganizationId() : null));
        Page page =new Page();
        page.setDraw(draw);
        page.setStart(start);
        page.setLength(length);
        page.setRecordsTotal(count);
        // 分页计算
        page.doPage();
        List<SysRoleBean> beans=null;
        //如果总数为0就不再查下数据，减小数据库压力
        if(count>0) {
            //判断超级管理员权限
            beans = sysRoleService.getPageList(page, roleName, u != null ? u.getOrganizationId() : null,userId,status,rId);
        }else{
            beans=new ArrayList<SysRoleBean>();
        }
        modelMap.addAttribute("data", beans);
        modelMap.addAttribute("page", page);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据ID获取单个角色信息
     *
     * @param
     * @return
     */
    @SystemControllerLog(description = "获取单个角色和权限信息",operationType="查询")
    @ApiOperation(value = "获取单个角色和权限信息")
    @RequestMapping(value = "/getSysRoleAndMenusById", method = RequestMethod.POST)
    public Object getSysRoleAndMenusById(ModelMap modelMap,@RequestParam(value = "id", required = true) String id) {
        SysRole sysRole = sysRoleService.queryById(id);
        List<SysRoleMenu> menus=sysRoleService.findSysRoleMenu(sysRole.getId(),"N");
        modelMap.addAttribute("data",sysRole);
        modelMap.addAttribute("menus",menus);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据ID获取单个角色信息
     *
     * @param
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "获取单个角色信息",operationType="查询")
    @ApiOperation(value = "获取单个角色信息")
    @RequestMapping(value = "/getSysRoleById", method = RequestMethod.POST)
    public Object getSysRoleById(ModelMap modelMap,@RequestParam(value = "id", required = false) String id) {
        SysRole sysRole = sysRoleService.queryById(id);
        //查询用户的机构信息
        SysOrganization org=null;
        //如果机构为空则不查询机构信息
        if(sysRole.getOrganizationId()!=null)
            org=sysOrgService.queryById(sysRole.getOrganizationId());
        modelMap.addAttribute("org",org);
        modelMap.addAttribute("data",sysRole);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据ID获取单个角色的权限信息
     *
     * @param
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "获取单个角色的权限信息",operationType="查询")
    @ApiOperation(value = "获取单个角色的权限信息")
    @RequestMapping(value = "/getSysRoleMenusById", method = RequestMethod.POST)
    public Object getSysRoleMenusById(ModelMap modelMap,@RequestParam(value = "id", required = false) String id) {
        List<SysRoleMenu> menus=sysRoleService.findSysRoleMenu(id,"N");
        modelMap.addAttribute("menus",menus);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 获取角色分页信息列表
     * @param
     * @return
     */
    @SystemControllerLog(description = "为内部用户获取角色信息",operationType="查询")
    @ApiOperation(value = "为内部用户获取角色信息")
    @RequestMapping(value = "/getSysRoleListRorUser", method = RequestMethod.POST)
    public Object getSysRoleListRorUser(ModelMap modelMap,long draw ,long start,long length, String roleName,String status,@RequestHeader(value = "token", defaultValue = "") String token) {
        //从登录缓存中获取机构信息
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        //判断超级管理员权限

        String rId=u.getUserType().equals("SUPERADMIN")?null:u.getRoleId();
        String userId = u.getUserType().equals("SUPERADMIN")?null:u.getId();
        List<SysRoleBean> beans=null;
        if("SUPERADMIN".equals(u.getUserType())){
            Map map = new HashMap();
            List<SysRole> bs = sysRoleService.queryByParam(map);
            modelMap.addAttribute("data", bs);
            return setSuccessModelMap(modelMap);
        }
        int count=sysRoleService.getRecordsTotal(roleName, u != null ? u.getOrganizationId(): null,userId,status,rId);
        System.out.println("oId:"+ (u != null ? u.getOrganizationId() : null));
        Page page =new Page();
        page.setDraw(draw);
        page.setStart(start);
        page.setLength(length);
        page.setRecordsTotal(count);
        // 分页计算
        page.doPage();

        //如果总数为0就不再查下数据，减小数据库压力
        if(count>0) {
            //判断超级管理员权限
            beans = sysRoleService.getPageList(page, roleName, u != null ? u.getOrganizationId() : null,userId,status,rId);
        }else{
            beans=new ArrayList<SysRoleBean>();
        }
        //监管系统获取检测和执法的角色
        //List<SysRole> asmsRoles = null ;
        List<SysRole> initSysRoles = null ;
        /*if(u.getOrganization() != null && u.getOrganization().getOrgType() != null && "ASMS".equals(u.getOrganization().getOrgType()))
            asmsRoles =  sysRoleService.getASMSRoles();*/

        if(u.getOrganization() != null && u.getOrganization().getOrgType() != null){
            List<String> roleCodes = new ArrayList<>();
            String orgGjz="";
            switch (u.getOrganization().getOrgType()) {
                case "ASMS":
                    orgGjz = "JG";
                    break;
                case "ALES":
                    orgGjz = "ZF";
                    break;
                case "ADS":
                    orgGjz = "JC";
                    break;
            }

            for(int i=1;i<=4;i++){
                roleCodes.add(orgGjz+"-0"+i+"-NORMAL");
            }
            initSysRoles = sysRoleService.getSysRoleByRoleCodes(roleCodes);
        }


        //追加内部用户基本角色信息
        /*SysRole sysRole = sysRoleService.getNormalRoleByAccount(u.getAccount());
        if(sysRole != null){
            SysRoleBean s = new SysRoleBean();
            s.setId(sysRole.getId());
            s.setRoleName(sysRole.getRoleName());
            s.setRoleCode(sysRole.getRoleCode());
            beans.add(s);
        }
        if(asmsRoles != null){
            for(SysRole sr : asmsRoles){
                SysRoleBean s = new SysRoleBean();
                s.setId(sr.getId());
                s.setRoleName(sr.getRoleName());
                s.setRoleCode(sr.getRoleCode());
                beans.add(s);
            }
        }*/
        if(initSysRoles != null){
            for(SysRole sr : initSysRoles){
                SysRoleBean s = new SysRoleBean();
                s.setId(sr.getId());
                s.setRoleName(sr.getRoleName());
                s.setRoleCode(sr.getRoleCode());
                beans.add(s);
            }
        }
        if("ASMS".equals(u.getOrganization().getOrgType())){
            List<SysRole> roles = sysRoleService.getIndustryormalRolesByUserId(u.getId(),"ASMS");
            //临时方案，后续需要给部级监管机构_admin做角色初始化处理
            if("部级监管机构_admin".equals(u.getAccount()) && roles.size() == 0){
                List<String> roleCodes =new ArrayList<>();
                roleCodes.add("01-JG-INDUSTRY-NORMAL-ROLE");
                roleCodes.add("02-JG-INDUSTRY-NORMAL-ROLE");
                roleCodes.add("03-JG-INDUSTRY-NORMAL-ROLE");
                List<SysRole> normalHyRoles =  sysRoleService.getSysRoleByRoleCodes(roleCodes);
                roles.addAll(normalHyRoles);
            }
            for(SysRole role : roles){
                SysRoleBean srb = new SysRoleBean();
                srb.setId(role.getId());
                srb.setRoleName(role.getRoleName());
                beans.add(srb);
            }
        }
        modelMap.addAttribute("data", beans);
        return setSuccessModelMap(modelMap);
    }
}
