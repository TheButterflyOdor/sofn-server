package com.sofn.web.scheduler;

import com.sofn.core.util.FastDFSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wuye on 16/11/2.
 */
@RestController
@Api(value = "文件上传接口", description = "文件上传接口")
@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
public class UploadFileController {

    // 上传文件
    @RequestMapping("/uplaod")
    @ApiOperation(value = "上传文件")
    public Object uploadImage(@RequestParam MultipartFile file, ModelMap modelMap) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setContentType("text/html;charset=utf-8");



        //File file = new File("");
        String fileAddress = null;
        try {
            fileAddress = FastDFSUtil.getUpliadFileAddress2(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fileAddress !=null) {
            modelMap.put("fileAddress", fileAddress);
            return modelMap;
        } else {
            modelMap.put("msg", "请选择要上传的文件！");
            return modelMap;
        }
    }
}
