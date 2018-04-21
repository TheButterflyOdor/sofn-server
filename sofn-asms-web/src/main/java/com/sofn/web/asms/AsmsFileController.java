package com.sofn.web.asms;

import com.alibaba.dubbo.common.json.JSON;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.WebUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sofn
 * @version 2017年05月18日 下午 3:55
 */

@RestController
@Api(value = "文件管理",description = "文件管理")
@RequestMapping(value = "/asmsFile",method = RequestMethod.POST)
public class AsmsFileController extends BaseController{

    @ApiOperation(value = "上传文件")
    @SystemControllerLog(description = "上传文件",operationType = "添加")
    @RequestMapping(value = "/upload")
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response){
        String address = "";
        Map<String,Object> map = new HashMap();
        Writer writer = null;
        try {
            address = super.uploadFile(request);
            writer = response.getWriter();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            map.put("httpCode",200);
            map.put("path",address);
            map.put("msg","请求成功");
            writer.write(JSON.json(map));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "下载文件")
    @SystemControllerLog(description = "下载文件",operationType = "下载")
    @RequestMapping(value = "/downloadFile")
    public Object downloadFile(@RequestParam String fileUrl, @RequestParam String fileName, HttpServletRequest request)  throws Exception{
        ResponseEntity<byte[]> responseEntity = FastDFSUtil.dowloadFile(fileUrl, fileName);
        // 解决微软家浏览器文件下载时，文件名中文乱码问题
        if(WebUtil.isMSBrowser(request)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileName, "UTF-8")));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            responseEntity = new ResponseEntity<>(responseEntity.getBody(), headers, HttpStatus.OK);
        }
        return responseEntity;

    }

}
