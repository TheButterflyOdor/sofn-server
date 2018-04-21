package com.sofn.web.mobile;

import com.sofn.core.base.BaseController;
import com.sofn.core.config.Resources;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.exception.LoginException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.service.ads.SSOLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: yangran
 * Date: 2016-11-28
 * 移动端登录接口
 */
@RestController
@Api(value = "移动端登录接口", description = "移动端登录接口")
@RequestMapping(value = "/adsMobileLogin",method = RequestMethod.POST)
public class AdsMobileLoginController extends BaseController {

    @Autowired
    private SSOLoginService ssoLoginService;

    /**
     * Login sso object.
     *
     * @param modelMap the model map
     * @param username  the account
     * @param password the password
     * @return the object
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Object login(ModelMap modelMap,
                        @ApiParam(required = true, value = "登录帐号")
                        @RequestParam(value = "username", required = false) String username,
                        @ApiParam(required = true, value = "登录密码")
                        @RequestParam(value = "password", required = false) String password) {
        // 参数检查
        Assert.isNotBlank(username, "USERNAME");
        Assert.isNotBlank(password, "PASSWORD");
        //登录逻辑
        Map<String, Object> map = ssoLoginService.login(username, password);
        if (map == null) {
            throw new LoginException(Resources.getMessage("LOGIN_FAIL"));
        }
        CurrentUser userModel = (CurrentUser)map.get("user");
        //查询机构信息并缓存
        CurrentUser user= (CurrentUser) map.get("user");
        WebUtil.saveCurrentUser(user);
        //返回用户信息
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("id",user.getId());
        userMap.put("account",user.getAccount());
        userMap.put("userName",user.getUserName());
        userMap.put("deptId",user.getDeptId());
        userMap.put("roleId",user.getRoleId());
        userMap.put("postId",user.getPostId());
        userMap.put("phone",user.getPhone());
        userMap.put("email",user.getEmail());
        userMap.put("status",user.getStatus());
        userMap.put("organizationId",user.getOrganizationId());
        userMap.put("orgName",user.getOrgName());
        userMap.put("orgType",user.getOrgType());
        userMap.put("orgLevel",user.getOrgLevel());
        userMap.put("orgId",user.getOrgId());
        userMap.put("userType",user.getUserType());
        modelMap.addAttribute("data",userMap);
        modelMap.addAttribute("token",map.get("token"));
        modelMap.addAttribute("code",true);
        //存入redis
        RedisUtil.set(map.get("token").toString(),user);
        return setSuccessModelMap(modelMap,userMap);
    }

}
