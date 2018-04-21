package com.sofn.web.ales;

/**
 * Created by Administrator on 2017/5/18.
 */

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.support.Assert;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.WebUtil;
import com.sun.javafx.fxml.expression.Expression;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sofn
 * @version 2017年05月18日 下午 3:55
 */

@RestController
@Api(value = "文件管理",description = "文件管理")
@RequestMapping(value = "/alesFile",method = RequestMethod.POST)
public class AlesFileController extends BaseController {

    @ApiOperation(value = "上传文件")
    @SystemControllerLog(description = "上传文件",operationType = "添加")
    @RequestMapping(value = "/upload")
    public Object upload(HttpServletRequest request){
        String address = "";
        try {
            address = super.uploadFile(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("path",address);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "下载文件")
    @SystemControllerLog(description = "下载文件",operationType = "下载")
    @RequestMapping(value = "/downloadFile")
    public Object downloadFile(@RequestParam String fileUrl, @RequestParam String fileName ) throws Exception {
        /*Assert.isNotBlank(token,"token");
        CurrentUser u = WebUtil.getCurrentUser(token);
        if(u == null){
            return setSuccessModelMap(new ModelMap(), HttpCode.UNAUTHORIZED);
        }*/
        return FastDFSUtil.dowloadFile(fileUrl,fileName);
    }
}
