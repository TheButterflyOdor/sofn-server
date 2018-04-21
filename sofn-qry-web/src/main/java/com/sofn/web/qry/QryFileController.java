package com.sofn.web.qry;

import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.util.FastDFSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sofn
 * @version 2017年05月24日 下午 6:23
 */
@RestController
@Api(value = "文件管理",description = "文件管理")
@RequestMapping(value = "/qryFile",method = RequestMethod.POST)
public class QryFileController extends BaseController{

    @ApiOperation(value = "下载文件")
    @SystemControllerLog(description = "下载文件",operationType = "下载")
    @RequestMapping(value = "/downloadFile")
    public Object downloadFile(@RequestParam String fileUrl, @RequestParam String fileName) throws Exception {
        return FastDFSUtil.dowloadFile(fileUrl,fileName);
    }
}
