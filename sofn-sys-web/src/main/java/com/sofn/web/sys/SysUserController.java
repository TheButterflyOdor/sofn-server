package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.constant.Organization;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.*;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysOrganization;
import com.sofn.model.generator.SysRole;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysUserBean;
import com.sofn.service.sys.SysDictService;
import com.sofn.service.sys.SysOrganizationService;
import com.sofn.service.sys.SysRoleService;
import com.sofn.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: dong4j.
 * Date: 2016-10-17.
 * Time: 22:51.
 * Description: 分页 demo
 */
@RestController
@Api(value = "用户信息", description = "用户信息")
@RequestMapping(value = "/user",method = RequestMethod.POST)
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysOrganizationService sysOrgService;
    @Autowired
    private SysRoleService sysRoleService ;
    @Autowired
    private SysDictService sysDictService;


    /**
     * 新增用户和角色权限
     * @param sysUser 用户对象
     * @return
     */
    @Authorization
    @ApiOperation(value = "新增用户")
    @PostMapping("/addSysUser")
    @SystemControllerLog(description = "新增用户",operationType="增加")
    public Object addSysUser( @RequestBody SysUserBean sysUser,
                              @RequestHeader(value = "token", defaultValue = "") String token) {
        //默认设置为当前登录用户的机构id，防止篡权添加
        CurrentUser u= WebUtil.getCurrentUser(token);
        ModelMap modelMap = new ModelMap();
        //未登录
        if(u==null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        if( UserEnum.SUPERADMIN.name().equals(u.getUserType()) || StringUtils.isEmpty(u.getUserType()) || UserEnum.NORMAL.name().equals(u.getUserType()))
            return setModelMap(modelMap, HttpCode.FORBIDDEN);
        sysUser.setOrganizationId(u.getOrganizationId());
        sysUser.setReservedField1("0");
        String roleStr=sysUser.getRoleStr();
        if("ASMS".equals(u.getOrganization().getOrgType())){
            boolean hasIndustryRole = false ;
            List<String> roleCodes =new ArrayList<>();
            roleCodes.add("01-JG-INDUSTRY-NORMAL-ROLE");
            roleCodes.add("02-JG-INDUSTRY-NORMAL-ROLE");
            roleCodes.add("03-JG-INDUSTRY-NORMAL-ROLE");
            List<SysRole> normalHyRoles =  sysRoleService.getSysRoleByRoleCodes(roleCodes);
            String[] rolesArray = roleStr.split(",");
            for(SysRole role : normalHyRoles){
                for(String s : rolesArray){
                    if(s.equals(role.getId())){
                        hasIndustryRole = true ;
                        break;
                    }
                }
            }
            if (!hasIndustryRole){
                return setFailModelMap(modelMap,"保存失败，至少得为监管机构内部用户选择一个行业角色");
            }
        }

        //配置修改时间、修改人
        sysUser.setCreateBy(u.getId());
        sysUser.setUpdateBy(u.getId());
        //生成密码SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(password))

        SysUser user = sysUserService.saveSysUser(sysUser, roleStr);
        modelMap.addAttribute("id", user.getId());
        return setSuccessModelMap(modelMap);
    }
    /**
     * 新增机构和管理员用户以及全部菜单权限
     * @param oranization 机构信息
     * @return
     */
   /* @ApiOperation(value = "新增机构和生成管理员用户")
    @PostMapping("/addSysOranization")
    public Object addSysOranization(ModelMap modelMap, @RequestBody SysOrganization oranization,
                                    @RequestHeader(value = "token", defaultValue = "") String token) {
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if(u==null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        //配置修改时间、修改人
        oranization.setCreateBy(u.getId());
        SysUser user = sysUserService.saveSysOranizationUser(oranization);
        modelMap.addAttribute("id", user.getId());
        return setSuccessModelMap(modelMap);
    }*/
    /**
     * 修改用户和角色权限
     * @param sysUser 用户对象
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "修改用户",operationType="修改")
    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "/updateSysUser", method = RequestMethod.POST)
    public Object updateSysUser(@RequestBody SysUserBean sysUser,
                                @RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        if (StringUtils.isNullEmpty(sysUser.getId())) {
            return setModelMap(modelMap, HttpCode.BAD_REQUEST);
        }
        String roleStr = sysUser.getRoleStr();
        SysUser uOld = sysUserService.queryById(sysUser.getId());
        if (uOld == null)
            return setModelMap(modelMap, HttpCode.NOT_FOUND);
        //替换创建时间、创建人、删除标识、排序ID、用户名、密码、用户类型
        sysUser.setAccount(uOld.getAccount());
        sysUser.setPassword(uOld.getPassword());
        sysUser.setCreateBy(uOld.getCreateBy());
        sysUser.setCreateTime(uOld.getCreateTime());
        sysUser.setUserType(uOld.getUserType());
        //替换等级
        sysUser.setReservedField1(uOld.getReservedField1());

        //配置修改时间、修改人
        sysUser.setUpdateBy(u.getId());
        if (sysUser.getDelFlag() == null)
            sysUser.setDelFlag(uOld.getDelFlag());
        SysUser user = sysUserService.saveSysUser(sysUser, roleStr);
        modelMap.addAttribute("id", user.getId());
        return setSuccessModelMap(modelMap);

    }

    /**
     * 修改密码
     * @return
     */
    @SystemControllerLog(description = "修改密码",operationType="修改")
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Object updatePassword( String account,String oldPassword,String newPassword,
                                  @RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
        if(oldPassword==null||oldPassword.trim().equals("")||newPassword==null||newPassword.trim().equals(""))
            return setModelMap(modelMap, HttpCode.BAD_REQUEST);

        CurrentUser u = WebUtil.getCurrentUser(token);//未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        SysUser uOld=null;
        if (account == null || account.trim().equals("")) {
            uOld = sysUserService.queryById(u.getId());
        } else {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("account", account);
            List<SysUser> list = sysUserService.queryByParam(param);
            if (list == null || list.size() <= 0)
                return setModelMap(modelMap, HttpCode.PASSWORD_ERROR);
            for(SysUser user : list){
                if(user.getAccount().equals(account.trim())){
                    uOld=user;
                    break;
                }
            }
        }
        if (uOld == null)
            return setModelMap(modelMap, HttpCode.NOT_FOUND);
        if(!uOld.getPassword().equals(oldPassword)){
            return setModelMap(modelMap, HttpCode.PASSWORD_ERROR);
        }

        //替换创建时间、创建人、删除标识、排序ID
        uOld.setPassword(newPassword);
        uOld.setUpdateBy(u.getUpdateBy());
        SysUser user = sysUserService.saveSysUser(uOld, null);
        modelMap.addAttribute("id", user.getId());
        return setSuccessModelMap(modelMap);

    }

    /**
     * 重置密码
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "重置密码",operationType="修改")
    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Object resetPassword( @ApiParam(required = true, value = "账号")
                                     @RequestParam(value = "account", required = false) String account,
                                  @RequestHeader(value = "token", defaultValue = "") String token) {
        ModelMap modelMap = new ModelMap();
        CurrentUser u = WebUtil.getCurrentUser(token);//未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        boolean result=sysUserService.resetPassword(account,token);
        if (!result){
            return setModelMap(modelMap, HttpCode.NOT_FOUND_DATA);
        }
        return setSuccessModelMap(modelMap);

    }
    /**
     * 单个或批量删除用户信息
     * @param idStr 要删除的用户ID串，以英文逗号分隔
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "删除用户信息",operationType="删除")
    @ApiOperation(value = "删除用户信息")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Object deleteUser(ModelMap modelMap,String idStr,
                             @RequestHeader(value = "token", defaultValue = "") String token) {

        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        //配置修改时间、修改人
        int count=sysUserService.deleteSysUser(idStr,u.getId());
        if(count==0)
            return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 获取角色分页信息列表
     *
     * @param
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "获取用户分页信息列表",operationType="查询")
    @ApiOperation(value = "获取用户分页信息列表")
    @RequestMapping(value = "/getSysUserList", method = RequestMethod.POST)
    public Object getSysUserList(ModelMap modelMap,long draw ,long start,long length, String userName,String status,@RequestHeader(value = "token", defaultValue = "") String token) {
        CurrentUser u= WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);

        String userType=null;
        String regionStr=null;
        String noRegionStr=null;
        //判断是否为超级管理员
        userType = u.getUserType();
        /*if(u.getUserType()!=null&&u.getOrganization()!=null&&u.getUserType().equals(UserEnum.SYSADMIN.name())&&u.getOrganization().getOrgType().equals(RSAUtils.Module.ASMS.name())){
            //判断行政等级，查询下一级所有行政编号(用户为监管机构管理员)
            switch (u.getOrganization().getOrgLevel()){
                //部级
                case "1":
                    regionStr="__0000";
                    break;
                //省级
                case "2":
                    regionStr=(u.getOrganization().getRegionId().substring(0,2)+"__00");
                    noRegionStr="__00__";
                    break;
                //市级
                case "3":
                    regionStr=(u.getOrganization().getRegionId().substring(0,4)+"__");
                    noRegionStr="____00";
                    break;
            }
        }*/
        String userId = u.getId();
        //判断用户机构是否为监管机构
        int count=sysUserService.getRecordsTotal(userName,status,userId,userType);
        Page page =new Page();
        page.setDraw(draw);
        page.setStart(start);
        page.setLength(length);
        page.setRecordsTotal(count);
        // 分页计算
        page.doPage();
        List<SysUserBean> beans=null;
        //如果总数为0就不再查下数据，减小数据库压力
        if(count>0) {
            beans = sysUserService.getPageList(page, userName,status,userId,userType);
        }else{
            beans=new ArrayList<SysUserBean>();
        }
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("data", beans);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据机构ID查询用户列表
     *
     * @param
     * @return
     */
    @SystemControllerLog(description = "根据机构ID查询用户列表",operationType="查询")
    @ApiOperation(value = "根据机构ID查询用户列表")
    @RequestMapping(value = "/queryUserListByOranizationId", method = RequestMethod.POST)
    public Object queryUserListByOranizationId(ModelMap modelMap,String oranizationId,@RequestHeader(value = "token", defaultValue = "") String token) {
        if(oranizationId==null||oranizationId.trim().equals("")) {
            CurrentUser u = WebUtil.getCurrentUser(token);
            oranizationId=u.getOrganizationId();
        }
        Map<String,Object> param=new HashMap<>();
        param.put("organizationId",oranizationId);
        List<SysUser> list=sysUserService.queryByParam(param);
        modelMap.addAttribute("data", list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据机构ID分页查询用户列表，仅供前端datatable使用
     * @param organizationId 机构ID
     * @param start   datatable记录起始行号，行号从0开始计算
     * @param length  每页记录数
     * @return 带分页信息的用户列表
     */
    @RequestMapping(value = "/pageingQueryByOrganizationId", method = RequestMethod.POST)
    public Object queryUserListByOranizationId(String organizationId, int start, int length) {
        Map<String,Object> param = new HashMap<>();
        param.put("organizationId", organizationId);
        PageInfo<SysUser> list = sysUserService.queryByParamWithPaging(param, (start / length + 1), length);
        return setSuccessModelMap(new ModelMap(), list);
    }
    /**
     * 根据机构ID查询用户列表
     *
     * @param deptId 为空则默认为登陆人同部门
     * @return
     */
    @SystemControllerLog(description = "根据部门ID查询用户列表",operationType="查询")
    @ApiOperation(value = "根据部门ID查询用户列表")
    @RequestMapping(value = "/getDeptUser", method = RequestMethod.POST)
    public Object getDeptUser(ModelMap modelMap,String deptId,@RequestHeader(value = "token", defaultValue = "") String token) {
        if(deptId==null||deptId.trim().equals("")) {
            CurrentUser u = WebUtil.getCurrentUser(token);
            deptId=u.getDeptId();
            //如果没传部门ID并且登录人没有所属部门，返回空
            if(deptId==null||deptId.trim().equals("")) {
                List<SysUser> list=new ArrayList<SysUser>();
                modelMap.addAttribute("data", list);
                return setSuccessModelMap(modelMap);
            }
        }
        Map<String,Object> param=new HashMap();
        param.put("deptId",deptId);
        List<SysUser> list=sysUserService.queryByParam(param);
        modelMap.addAttribute("data", list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据角色ID查询用户列表
     *
     * @param roleId 为空则默认为登陆人同角色
     * @return
     */
    @SystemControllerLog(description = "根据角色ID查询用户列表",operationType="查询")
    @ApiOperation(value = "根据角色ID查询用户列表")
    @RequestMapping(value = "/getRoleUser", method = RequestMethod.POST)
    public Object getRoleUser(ModelMap modelMap,String roleId,@RequestHeader(value = "token", defaultValue = "") String token) {
        if(roleId==null||roleId.trim().equals("")) {
            CurrentUser u = WebUtil.getCurrentUser(token);
            roleId=u.getRoleId();
            //如果没传部门ID并且登录人没有所属部门，返回空
            if(roleId==null||roleId.trim().equals("")) {
                List<SysUser> list=new ArrayList<SysUser>();
                modelMap.addAttribute("data", list);
                return setSuccessModelMap(modelMap);
            }
        }
        Map<String,Object> param=new HashMap();
        param.put("roleId",roleId);
        List<SysUser> list=sysUserService.queryByParam(param);
        modelMap.addAttribute("data", list);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 根据ID获取单个用户信息
     * @param
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "获取单个用户信息",operationType="查询")
    @ApiOperation(value = "获取单个用户信息")
    @RequestMapping(value = "/getSysUserById", method = RequestMethod.POST)
    public Object getSysUserById(ModelMap modelMap,@RequestParam(value = "id", required = true) String id) {
        SysUser sysUser = sysUserService.queryById(id);
        List<Map<String,Object>> roles = sysUserService.getRoleByUserId(sysUser.getId());
        //查询用户的机构信息
        SysOrganization org=null;
        //如果机构为空则不查询机构信息
        if(sysUser.getOrganizationId()!=null)
            org=sysOrgService.queryById(sysUser.getOrganizationId());
        modelMap.addAttribute("data",sysUser);
        modelMap.addAttribute("roles",roles);
        modelMap.addAttribute("org",org);
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据ID获取单个用户信息         需要补充用到缓存登录用户数据
     * @param
     * @return
     */
    @SystemControllerLog(description = "获取当前登录用户的机构信息",operationType="查询")
    @ApiOperation(value = "获取当前登录用户的机构信息")
    @RequestMapping(value = "/findSysUserOrganization", method = RequestMethod.POST)
    public Object findSysUserOrganization(ModelMap modelMap,
                                          @RequestHeader(value = "token", defaultValue = "") String token) {
        //从登录缓存中获取机构信息
        CurrentUser u= WebUtil.getCurrentUser(token);
        //判断机构id不为空则获取机构缓存，如果缓存中不存在则报错，如果机构id为空表示为系统管理用户
        if((u != null ? u.getOrganizationId() : null) !=null) {
            try {
                Organization org =  u.getOrganization();
                modelMap.addAttribute("org", org);
                modelMap.addAttribute("user",u);
            }catch (Exception ex){
                return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
            }
        }
        return setSuccessModelMap(modelMap);
    }
    /**
     * 根据ID获取单个用户信息         需要补充用到缓存登录用户数据
     * @param
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "导入用户Excel",operationType="查询")
    @ApiOperation(value = "导入用户Excel")
    @RequestMapping(value = "/importUserListByExcel", method = RequestMethod.POST)
    public Object importUserListByExcel(ModelMap modelMap,HttpServletRequest request,
                                        @RequestHeader(value = "token", defaultValue = "") String token, String roleStr) {
        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null) {
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        try {
            //将当前上下文初始化
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                // 根据 name 获取上传的文件...
                MultipartFile file = multipartRequest.getFile("file");
                String fileName = file.getOriginalFilename();
                String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                InputStream input = file.getInputStream();
                parseExcel(input, extensionName, u, roleStr);
            }
        }catch (Exception e){
            return setFailModelMap(new ModelMap(),e.getMessage());
        }
        return setSuccessModelMap(modelMap);
    }


    /**
     * 解析excel
     * @param in
     * @param extensionName
     * @param u
     * @throws Exception
     */
    private void parseExcel(InputStream in, String extensionName, CurrentUser u, String roleStr)throws  Exception{
        Workbook workbook = null;
        if (extensionName.toLowerCase().equals("xls")) {
            workbook = new HSSFWorkbook(in);
        } else if (extensionName.toLowerCase().equals("xlsx")) {
            workbook = new XSSFWorkbook(in);
        }
        readCell(workbook, u, roleStr);
    }

    /**
     * 获取单元格数据
     * @param workbook
     * @param u
     * @throws Exception
     */
    private void readCell(Workbook workbook, CurrentUser u, String roleStr)throws Exception{
        //查询所有职务
        List<SysDictData> dicList = sysDictService.getDictByTypes("PostType");
        List<String> list = null;
        String firstCell="";
        //默认获取第一个sheet表的数据
        Sheet sheet = workbook.getSheetAt(0);
        //默认字段为5列
        int cellSize = 5;
        //总列数
        int cellCount =0;
        if(sheet.getRow(0)!=null) {
            cellCount =sheet.getRow(0).getLastCellNum();
            firstCell = ExcelReadUtil.getCellValue(sheet.getRow(0).getCell(0));
        }
        if(cellCount!=cellSize){
            throw new Exception("表格列数不正确！默认为5列,");
        }
        //校验表格数据
        if (firstCell.equals("序号")) {
            int n=0;
            //总行数
            int rsRows = sheet.getRow(0) == null ? -1 : sheet.getLastRowNum();
            // 循环行,跳过第一行
            for (int i = 1; i <= rsRows; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    list = new ArrayList<String>();
                    //循环列，跳过第一列
                    try {
                        for (int j = 1; j < cellCount; j++) {// 读取单元格
                            //默认第2,3,4列数据不能为空
                            if (j == 1 || j == 2 || j == 3) {
                                notNullValidate(row.getCell(j));
                            }
                            list.add(ExcelReadUtil.getCellValue(row.getCell(j)));
                        }
                        saveUser(list, u, dicList, roleStr);
                        n++;
                    }catch (Exception e){
                        if(i==rsRows && n==0) {
                            throw e;
                        } else {
                            continue;
                        }
                    }
                }
            }
        }else {
                throw new Exception("表格第一行第一列数据不正确！默认为“序号”,");
        }
    }

    /**
     * 字段非空校验
     * @throws Exception
     */
    private void notNullValidate(Cell cell) throws Exception {
        if( Cell.CELL_TYPE_BLANK == cell.getCellType())
        {
            throw new Exception("列不能为空");
        }
    }

    /**
     * 导入并保存用户
     * @param list
     * @param u
     * @param dicList
     */
   private void saveUser(List<String> list,CurrentUser u,List<SysDictData> dicList, String roleStr)
   {
       SysUserBean sysUser = new SysUserBean();
       sysUser.setUserName(list.get(0));
       for(SysDictData dic:dicList)
       {
           if(list.get(1).equals(dic.getDictName()))
           {
               sysUser.setPostId(dic.getId());
           }
       }

       sysUser.setPhone(list.get(2));
       sysUser.setEmail(list.get(3));
       sysUser.setCreateBy(u.getId());
       sysUser.setUpdateBy(u.getId());
       sysUser.setOrganizationId(u.getOrganizationId());
       sysUser.setStatus("1");

       if (org.springframework.util.StringUtils.isEmpty(roleStr)) {
           //默认角色
           roleStr = "ad70568ce27b47db84d219b09baa43c847ef8806a5c14571801fbfe56f027512";
       }
       sysUserService.saveSysUser(sysUser, roleStr);
   }

    @ApiOperation(value = "test")
    @RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST)
    public Object getCurrentUser(@RequestHeader(value = "token", defaultValue = "") String token){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("currentUser",WebUtil.getCurrentUser(token));
        return setSuccessModelMap(modelMap);
    }

    /**
     * 获取当前用户所属系统对应的普通用户基础角色
     * @param token
     * @return
     */
    @Authorization
    @SystemControllerLog(description = "获取内部用户基础角色信息",operationType="查询")
    @ApiOperation(value = "getNormalRoleInfo")
    @RequestMapping(value = "/getNormalRoleInfo", method = RequestMethod.POST)
    public Object getNormalRoleInfo(@RequestHeader(value = "token", defaultValue = "") String token){
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null)
            return null;
        List<SysRole> normalRoles = new ArrayList<SysRole>();
        SysRole sysRole = sysRoleService.getNormalRoleByAccount(u.getAccount());
        normalRoles.add(sysRole);
        if("ASMS".equals(u.getOrganization().getOrgType())){
            List<SysRole> roles = sysRoleService.getIndustryormalRolesByUserId(u.getId(),"ASMS");
            if(roles != null && roles.size() > 0)
                normalRoles.addAll(roles);
            //临时方案，后续需要给部级监管机构_admin做角色初始化处理
            if("部级监管机构_admin".equals(u.getAccount())){
                List<String> roleCodes =new ArrayList<>();
                roleCodes.add("01-JG-INDUSTRY-NORMAL-ROLE");
                roleCodes.add("02-JG-INDUSTRY-NORMAL-ROLE");
                roleCodes.add("03-JG-INDUSTRY-NORMAL-ROLE");
                List<SysRole> normalHyRoles =  sysRoleService.getSysRoleByRoleCodes(roleCodes);
                normalRoles.addAll(normalHyRoles);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.put("normalRole",normalRoles);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "saveSysOranizationUser")
    @RequestMapping(value = "/saveSysOranizationUser", method = RequestMethod.POST)
    public Object saveSysOranizationUser(@RequestHeader(value = "token", defaultValue = "") String token,@RequestParam(value = "orgName", defaultValue = "") String orgName,@RequestParam(value = "regionId", defaultValue = "") String regionId){
        SysOrganization oranization = new SysOrganization();
        oranization.setOrgType("ASMS");
        oranization.setOrgLevel("2");
        oranization.setRegionId(regionId);
        oranization.setOrgName(orgName);
        sysUserService.saveSysOranizationUser(oranization,token);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "addSubj")
    @RequestMapping(value = "/addSubj", method = RequestMethod.POST)
    public Object addSubj(@RequestHeader(value = "token", defaultValue = "") String token,@RequestParam(value = "lx", defaultValue = "") String lx,@RequestParam(value = "username", defaultValue = "") String username,@RequestParam(value = "password", defaultValue = "") String password,@RequestParam(value = "account", defaultValue = "") String account,boolean choiceAbattoir,String sub){
        List<String> lxlist = new ArrayList<>();
        lxlist.add("01");
        lxlist.add("02");
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPassword(password);
        sysUser.setAccount(account);
        if("1".equals(sub))
            sysUserService.addSubjUser(token,UserEnum.SUBJADMIN,sysUser, lxlist,choiceAbattoir, OrgFormEnum.COOPERATIVE);
        else
            sysUserService.addSubjUser(token,UserEnum.SUBJNORMAL,sysUser, lxlist,choiceAbattoir, OrgFormEnum.COOPERATIVE);
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap);
    }

}
