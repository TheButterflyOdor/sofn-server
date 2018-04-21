package com.sofn.web.sys;

import com.sofn.core.base.BaseController;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysUser;
import com.sofn.service.sys.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by: dong4j.
 * Date: 2016-11-06.
 * Time: 16:44.
 * Description: 用于测试接前端传输的参数
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController{
    @Autowired
    private TestService testService;
    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public Object test1(ModelMap modelMap) {
        SysUser sysUser = new SysUser();
        sysUser.setAccount("test1");
        sysUser.setDelFlag("N");
        sysUser.setDeptId("xxxx");
        sysUser.setEmail("123@163.com");
        sysUser.setOrganizationId("xxxx");
        sysUser.setPassword("xxx");
        sysUser.setPhone("xxxxx");
        sysUser.setPostId("xxxx");
        sysUser.setUpdateTime(new Date());
        sysUser.setRemark("test");
        sysUser.setId(StringUtils.getUUIDString());
        sysUser.setCreateTime(new Date());
        sysUser.setCreateBy("dong4j");
        sysUser.setUpdateBy("dong4j");
        sysUser.setUserName("test");
        sysUser.setStatus("Y");
        sysUser.setRoleId("xxx");
        sysUser.setUserType("1");
        try{
            testService.transactionalTest1(sysUser);
        }catch (Exception e){
            e.printStackTrace();
        }
        return setSuccessModelMap(modelMap);
    }
}
