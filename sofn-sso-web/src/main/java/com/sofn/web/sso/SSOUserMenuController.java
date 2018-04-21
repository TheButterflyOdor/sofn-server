package com.sofn.web.sso;

/**
 * Created by qmy on 2016/10/14.
 */


import com.alibaba.dubbo.common.json.JSON;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.sso.SysMenu;
import com.sofn.model.sso.SysUserAuth;
import com.sofn.model.sso.UserInfoForAuth;
import com.sofn.service.sso.SSOMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qmy
 * @version 2016年10月14日 下午 3:05
 */
@RestController
@Api(value = "用户权限", description = "用户权限")
@RequestMapping(value = "/ssoMenu", method = RequestMethod.POST)
public class SSOUserMenuController extends BaseController {

    @Autowired
    private SSOMenuService ssoMenuService;

    /**
     * 获取用户权限菜单集合
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取用户权限菜单集合")
    @RequestMapping(value = "/getMenusByUserId", method = RequestMethod.POST)
    public Object getMenusByUserId(ModelMap modelMap, @RequestParam(name="type",required = false) String type, @RequestHeader(value = "token", defaultValue = "") String token) {
        String result = "" ;
        //从登录缓存中获取机构信息
        CurrentUser u = WebUtil.getCurrentUser(token);
        //未登录
        if (u == null)
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        /*if (type == null || type.trim().equals(""))
            return setModelMap(modelMap, HttpCode.BAD_REQUEST);*/
        String authorityKey = "MENU_AUTHORITY_" + u.getId();
        if(StringUtils.isNotEmpty(type))
            authorityKey = authorityKey  + "_" + type ;
        ArrayList<SysMenu> list = null;
//        Object authObj=RedisUtil.get(authorityKey);
//        if(authObj==null) {
        //判断超级管理员权限
        list = (ArrayList<SysMenu>) ssoMenuService.getMenusByUserId(u.getId(), type);
        //缓存菜单权限
        RedisUtil.set(authorityKey, list);
//        }else
//            list= (ArrayList<SysMenu>) authObj;
//        RedisUtil.set(account + user.getId() + "_" + ApiConstants.PCTOKEN, accessToken, ApiConstants.APP_TOKEN_TIMEOUT);
//        modelMap.addAttribute("data", list);
        return setSuccessModelMap(modelMap, list);
    }
    @ApiOperation(value = "分析决策获取用户权限信息")
    @RequestMapping(value = "/getAuthorityByToken", method = RequestMethod.GET)
    public void getAuthorityByToken(String type, String token, String callback , ModelMap modelMap, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        //从登录缓存中获取机构信息
        CurrentUser u = WebUtil.getCurrentUser(token);
        String result = "";
        //未登录
        if (u == null){
            result = "{\"httpCode\": 401,\"msg\": \"没有登录\"}";
            result=callback+"("+result+")";
            writer.write(result);
            writer.flush();
            writer.close();
            return ;
        }
        ArrayList<SysMenu> list = null;
        //判断超级管理员权限
        list = (ArrayList<SysMenu>) ssoMenuService.getMenusByUserId(u.getId(), null);
        List<SysMenu> leftitems = new ArrayList<SysMenu>();
        List<SysMenu> topitems = new ArrayList<SysMenu>();
        for(SysMenu menu : list){
            if(menu != null && StringUtils.isEmpty(menu.getId()))
                continue;
            if(type.equals(menu.getMenuValue())){
                leftitems.add(menu);
            }
            if(Integer.parseInt(menu.getNumbers())<=10 && Integer.parseInt(menu.getNumbers()) >1){
                topitems.add(menu);
            }
        }
        String userTye = u.getUserType();
        String orgLevel = u.getOrganization().getOrgLevel() ;
        String account = u.getAccount();
        String name = u.getUserName();
        String phone = u.getPhone();
        String department = u.getOrganization().getOrgName();
        String regionId= u.getOrganization().getRegionId();
        String orgType = u.getOrganization().getOrgType();
        List<String> regionIds = new ArrayList<>();
        String managearea = "000000";
        if("2".equals(orgLevel)){
            managearea = regionId.substring(0,2);
        }else if("3".equals(orgLevel)){
            managearea = regionId.substring(0,4);
        }else if("4".equals(orgLevel)){
            managearea = regionId.substring(0,6);
        }
        SysUserAuth sysUserAuth = new SysUserAuth();
        UserInfoForAuth userInfo = new UserInfoForAuth();
        sysUserAuth.setLeftitems(leftitems);
        sysUserAuth.setTopitems(topitems);
        sysUserAuth.setWhichsystem(orgType);
        sysUserAuth.setManagearea(managearea);
        userInfo.setUsername(account);
        userInfo.setName(name);
        userInfo.setPhone(phone);
        userInfo.setDepartment(department);
        userInfo.setUsertype(userTye);
        sysUserAuth.setUserinfo(userInfo);
        try {
            result = JSON.json(sysUserAuth);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result=callback+"("+result+")";
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
