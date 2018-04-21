package com.sofn.web.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.Assert;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.AdsFile;
import com.sofn.service.ads.AdsFileService;
import com.sofn.service.ads.AdsSetFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "文件", description = "文件")
@RequestMapping(value = "/adsFile", method = RequestMethod.POST)
public class AdsFileController extends BaseController {

    @Autowired
    private AdsFileService adsFileService;

    @Autowired
    private AdsSetFileInfoService adsSetFileInfoService;

    /**
     * 根据条件获取承担单位目录下的报告汇总列表
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = "获取文件信息列表")
    @SystemControllerLog(description = "获取文件信息列表", operationType = "查询")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfo(AdsFile adsFile, int start, int length, @RequestHeader(required = true) String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsFile.setCreateBy(u.getId());
                PageInfo<AdsFile> pageInfo = adsFileService.getPageInfo(adsFile, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 根据条件获取牵头单位目录下承担单位任务报告下载列表
     * @param adsDlReportFile
     * @return
     */

    @ApiOperation(value = "获取承担任务报告下载列表")
    @SystemControllerLog(description = "获取承担任务报告下载列表",operationType="查询")
    @RequestMapping(value = "/getDlReportPage", method = RequestMethod.POST)
    @Authorization
    public Object getDlReportPage(AdsFile adsDlReportFile, int start, int length, String token){
        try{
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if(u != null){
                String orgId = u.getOrganization().getOrgId();
                PageInfo<AdsFile> pageInfo = adsFileService.getDlReportPage(adsDlReportFile, orgId,((start + 1)/length)+1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            }else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 新增文件记录
     *
     * @param
     * @return
     */
    @ApiOperation(value = "新增文件数据")
    @RequestMapping(value = "/addAdsFile", method = RequestMethod.POST)
    public Object addAdsFile(AdsFile adsFile, String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                adsFile.setCreateBy(u.getId());
                adsFile.setUploadDepartment(u.getOrgName());
                adsFileService.add(adsFile);
                //传给另一个项目
                if(("承担单位".equals(adsFile.getSource()) && "专项监测".equals(adsFile.getMonitorType())) || ("承担单位".equals(adsFile.getSource()) && "例行监测".equals(adsFile.getMonitorType()))){
                    Boolean flag = adsSetFileInfoService.setFileInfoForBase(adsFile);
                    if (false == flag) {
                        logger.error("同步文件失败");
                        throw new IllegalParameterException("同步文件失败");
                    }
                }
                if (("牵头单位".equals(adsFile.getSource())) || ("承担单位".equals(adsFile.getSource()) && "监督抽查".equals(adsFile.getMonitorType())) || ("承担单位".equals(adsFile.getSource()) && "受托检测".equals(adsFile.getMonitorType())) || ("承担单位".equals(adsFile.getSource()) && "复检任务".equals(adsFile.getMonitorType()))) {
                    Boolean flag = adsSetFileInfoService.setFileInfo(adsFile);
                    if (false == flag) {
                        logger.error("同步文件失败");
                        throw new IllegalParameterException("同步文件失败");
                    }
                }
                return setSuccessModelMap(new ModelMap());
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            logger.error("AdsFileController.addAdsFile:新增文件数据异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR, e.toString());
        }

    }


    private String getFileName(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest中所有的文件名
            Iterator iter = multipartRequest.getFileNames();
            while (iter.hasNext()) {
                //一次便利所有文件
                MultipartFile file = multipartRequest.getFile(iter.next().toString());
                return file.getOriginalFilename();
            }
        }
        return "TempFileName";
    }


    /**
     * 根据ID获取单条文件数据信息
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = "获取单条文件数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public Map<String, Object> queryById(@RequestBody AdsFile adsFile) {
        adsFile = adsFileService.queryById(adsFile.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("adsFile", adsFile);
        return map;
    }

    /**
     * 修改文件数据信息
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = "修改文件数据信息")
    @RequestMapping(value = "/updateAdsFile", method = RequestMethod.POST)
    public Object updateAdsFile(@RequestBody AdsFile adsFile) {
        try {
            adsFileService.update(adsFile);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsFileController.updateAdsFile:修改文件数据信息异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除文件信息
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = " 删除文件信息")
    @RequestMapping(value = "/deleteAdsFile", method = RequestMethod.POST)
    public Object deleteAdsFile(AdsFile adsFile) {
        try {
            adsFileService.delete(adsFile.getId());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            logger.error("AdsFileController.deleteAdsFile:删除文件信息异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 上传文件
     *
     * @param
     * @return
     */
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public Object saveInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String address = this.uploadFile(request);
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("address", address);
            map.put("origFileName", getFileName(request));
            return map;
        } catch (Exception e) {
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            logger.error("AdsFileController.saveInfo:上传文件异常", e); //日志服务器抓取数据
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 下载文件
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = " 批量下载文件")
    @SystemControllerLog(description = "批量下载文件")
    @RequestMapping(value = "/batchDownload", method = RequestMethod.POST)
    public Object batchDownload(AdsFile adsFile) {
        try {
            int flag = adsFileService.batchDownload(adsFile);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 获取行业列表
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = "获取行业列表")
    @RequestMapping(value = "/getField", method = RequestMethod.POST)
    public Object getField(AdsFile adsFile) {
        try {
            List<Map<String, Object>> checkModel = adsFileService.getField();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取监测类型列表
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = "获取监测类型列表")
    @RequestMapping(value = "/getType", method = RequestMethod.POST)
    public Object getType(AdsFile adsFile) {
        try {
            List<Map<String, Object>> checkModel = adsFileService.getType();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取年度列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取年度列表")
    @RequestMapping(value = "/getYear", method = RequestMethod.POST)
    public Object getYear() {
        try {
            List<Map<String, Object>> checkModel = adsFileService.getYear();
            return setSuccessModelMap(new ModelMap(), checkModel);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 获取任务名称列表
     *
     * @param bean
     * @return
     */
    @ApiOperation(value = "获取任务名称列表")
    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    public Object getTask(AdsFile bean,String token) {
        try {
            Assert.isNotBlank(token, "token");
            CurrentUser u = WebUtil.getCurrentUser(token);
            if (u != null) {
                bean.setUploadDepartment(u.getOrgId());
                List<Map<String, Object>> checkModel = adsFileService.getTask(bean);
                return setSuccessModelMap(new ModelMap(), checkModel);
            } else {
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "下载")
    @RequestMapping(value = "fileDownload", method = RequestMethod.POST)
    public ResponseEntity<byte[]> fileDownload(@RequestParam(value = "fileUrl", required = false) String fileUrl,
                                               @RequestParam(value = "fileName", required = false) String fileName)
            throws Exception {
        return FastDFSUtil.dowloadFile(fileUrl, fileName);
    }

    @ApiOperation(value = "批量下载")
    @RequestMapping(value = "fileBatchDownload", method = RequestMethod.POST)
    public void fileBatchDownload(@RequestParam(value = "fileUrl", required = false) String fileUrl,
                                               @RequestParam(value = "fileName", required = false) String fileName,HttpServletResponse response)
            throws Exception {
        String[] fileUrlSplit = fileUrl.split(",");
        String[] fileNameSplit = fileName.split(",");
        String tmpFileName = UUID.randomUUID().toString() + ".zip";
        byte[] buffer = new byte[1024];
        String strZipPath = "C:\\" + tmpFileName;
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                strZipPath));
        for (int n = 0; n < fileUrlSplit.length; n ++){
            InputStream fis = FastDFSUtil.dowload2(fileUrlSplit[n]);
            out.putNextEntry(new ZipEntry(fileNameSplit[n]));
            //设置压缩文件内的字符编码，不然会变成乱码
            out.setEncoding("GBK");
            int len;
            // 读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();
        }
        out.close();
        File file = new File(strZipPath);
        if (file.exists()) {
            InputStream ins = new FileInputStream(strZipPath);
            BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
            OutputStream outs = response.getOutputStream();// 获取文件输出IO流
            BufferedOutputStream bouts = new BufferedOutputStream(outs);
            response.setContentType("application/x-download");// 设置response内容的类型
            response.setHeader(
                    "Content-disposition",
                    "attachment;filename="
                            + URLEncoder.encode(tmpFileName, "UTF-8"));// 设置头部信息
            int bytesRead = 0;
            byte[] bufferAll = new byte[8192];
            // 开始向网络传输文件流
            while ((bytesRead = bins.read(bufferAll, 0, 8192)) != -1) {
                bouts.write(bufferAll, 0, bytesRead);
            }
            bouts.flush();// 这里一定要调用flush()方法
            ins.close();
            bins.close();
            outs.close();
            bouts.close();
        }
    }


    /**
     * 下载文件V2
     *
     * @param fileAddress ,request, response
     * @return
     */
    @ApiOperation(value = " 下载文件V2")
    @SystemControllerLog(description = "单个下载文件V2")
    @RequestMapping(value = "/DownloadFileV2", method = RequestMethod.GET)
    public Object DownloadFileV2(String fileAddress, String fileName, HttpServletResponse response) {
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL urlfile = new URL(fileAddress);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();

            //String caseId = request.getParameter("caseId");
            //String fileName = ""; //URLDecoder.decode(request.getParameter("fileName"),"UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/octet-stream");
            response.setContentType("application/OCTET-STREAM;charset=UTF-8");
            // byte buffer[] = new byte[1024*1024*1];//1M
            //int read = 0;
            bis = new BufferedInputStream(httpUrl.getInputStream());
            //sos = response.getOutputStream();
            bos = new BufferedOutputStream(response.getOutputStream());

            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();

            //while((read=fos.read(buffer))!=-1){                sos.write(buffer,0,read);//每次写1M            }
            //OutputUtil.jsonOutPut(response, null);
        } catch (Exception e) {
            throw new RuntimeException("");
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                throw new RuntimeException("");
            }
        }
        return true;
    }


    /**
     * 下载文件
     *
     * @param adsFile
     * @return
     */
    @ApiOperation(value = " 下载文件")
    @SystemControllerLog(description = "单个下载文件")
    @RequestMapping(value = "/DownloadFile", method = RequestMethod.POST)
    public Object DownloadFile(AdsFile adsFile) {

        String localFilePath = "";
        String remoteFilePath = "http://" + adsFile.getFileAddress();

        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        System.out.println(f.exists());
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f.getPath();

    }

    /**
     * 根据多个条件获取问题单据数据列表
     *
     * @param
     * @return
     */
    @ApiOperation(value = "根据多个条件获取问题单据数据列表")
    @SystemControllerLog(description = "根据多个条件获取问题单据数据列表", operationType = "查询")
    @RequestMapping(value = "/getPageInfoProblemFile", method = RequestMethod.POST)
    @Authorization
    public Object getPageInfoProblemFile(AdsFile adsFile, int start, int length, @RequestHeader(required = true) String token) {
        try {
            Assert.isNotBlank(token,"token");
            CurrentUser u= WebUtil.getCurrentUser(token);
            if(u !=null){
                adsFile.setUploadDepartment(u.getOrgName());
                String now_year=adsFile.getYears();
                if(StringUtils.isEmpty(now_year)){
                    SimpleDateFormat sdf = new SimpleDateFormat( " yyyy" );
                    adsFile.setYears(sdf.format(new Date()).trim());
                }
                PageInfo<AdsFile> pageInfo = adsFileService.getPageInfoProblemFile(adsFile, ((start + 1) / length) + 1, length);
                return setSuccessModelMap(new ModelMap(), pageInfo);
            }else{
                return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);   //找不到用户信息
            }

        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

}

