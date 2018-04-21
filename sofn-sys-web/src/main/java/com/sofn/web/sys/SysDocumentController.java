package com.sofn.web.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.DictType;
import com.sofn.core.constant.SysCategoryEnum;
import com.sofn.core.util.FastDFSUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.SysDocument;
import com.sofn.model.sys.SysDocumentQueryParam;
import com.sofn.service.sys.SysDictService;
import com.sofn.service.sys.SysDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Administrator on 2018/2/9/009.
 */
@RestController
@RequestMapping(value = "/document")
public class SysDocumentController extends BaseController {
    private static final String ERROR_MESSAGE_KEY = "errorMessage";
    private static final String FILE_NAME_KEY = "fileName";
    private static final String FILE_ADDRESS_KEY = "fileAddress";
    private static final String FILE_DOWNLOAD_URI_FORMAT = "/sofn-sys-web/sysTemplate/fileDownload?fileUrl=%s&fileName=%s";

    @Autowired
    private SysDocumentService sysDocumentService;
    @Autowired
    private SysDictService sysDictService;

    /**
     * 根据条件分页查询文档列表
     *
     * @param param  查询参数
     * @param start  前端datatable插件数据起始位置
     * @param length 前端datatable插件的pageSize
     * @return 文档列表
     */
    @Authorization
    @SystemControllerLog(description = "根据查询条件分页查询文档信息", operationType = "查询")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Object getDocumentList(SysDocumentQueryParam param, int start, int length) {
        PageInfo<SysDocument> pageInfo = sysDocumentService.getDocumentList(param, start, length);

        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    /**
     * 根据ID获取文档信息
     *
     * @param id 文档ID
     * @return 文档信息
     */
    @Authorization
    @SystemControllerLog(description = "根据ID查询文档信息", operationType = "查询")
    @RequestMapping(value = "/queryOne", method = RequestMethod.POST)
    public Object getDocument(String id) {
        SysDocument doc = sysDocumentService.getDocument(id);

        return setSuccessModelMap(new ModelMap(), doc);
    }

    /**
     * 新增文档信息
     *
     * @param doc 文档信息
     * @return 新增记录数
     */
    @Authorization
    @SystemControllerLog(description = "新增文档信息", operationType = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addDocument(SysDocument doc, @RequestHeader(value = "token") String token) {
        Map<String, Integer> resultMap = new HashMap<>();
        CurrentUser user = WebUtil.getCurrentUser(token);
        doc.setCreateBy(user.getAccount());

        resultMap.put("affectedRows", sysDocumentService.addDocument(doc));

        return setSuccessModelMap(new ModelMap(), resultMap);
    }

    /**
     * 更新文档信息
     *
     * @param doc 文档信息
     * @return 更新记录数
     */
    @Authorization
    @SystemControllerLog(description = "修改文档信息", operationType = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateDocument(SysDocument doc, @RequestHeader(value = "token") String token) {
        Map<String, Integer> resultMap = new HashMap<>();
        CurrentUser user = WebUtil.getCurrentUser(token);
        doc.setUpdateBy(user.getAccount());

        resultMap.put("affectedRows", sysDocumentService.updateDocument(doc));

        return setSuccessModelMap(new ModelMap(), resultMap);
    }

    /**
     * CKEditor富文本框拖拽或粘贴上传图片
     * <p>
     * 参考：https://docs.ckeditor.com/ckeditor4/latest/guide/dev_file_upload.html
     *
     * @param request http请求
     * @return 图片上传结果
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        List<Map<String, String>> fileUploadResultList = uploadFiles(request);

        Map<String, String> fileInfoMap = fileUploadResultList.get(0);
        if (!fileInfoMap.containsKey(ERROR_MESSAGE_KEY)) {
            String url = getFileDownloadURL(fileInfoMap.get(FILE_NAME_KEY), fileInfoMap.get(FILE_ADDRESS_KEY));
            resultMap.put("uploaded", 1); // 上传成功标识 1
            resultMap.put("fileName", fileInfoMap.get(FILE_NAME_KEY));
            resultMap.put("url", url);
        } else {
            resultMap.put("uploaded", 0); // 上传失败标识 0
            Map<String, String> errorInfo = new LinkedHashMap<>();
            errorInfo.put("message", "The form is not multipart content.");
            resultMap.put("error", errorInfo);
        }

        return resultMap;
    }

    /**
     * CKEditor富文本框文件管理器上传图片
     * <p>
     * 参考：http://blog.csdn.net/saytime/article/details/51416411
     *      https://docs.ckeditor.com/ckeditor4/latest/guide/dev_file_browser_api.html
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ckeditorupload", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})
    public String FileManagerUpload(HttpServletRequest request) throws Exception {
        List<Map<String, String>> fileUploadResultList = uploadFiles(request);
        String callback = request.getParameter("CKEditorFuncNum");
        StringBuilder sb = new StringBuilder();
        Map<String, String> fileInfoMap = fileUploadResultList.get(0);

        if (!fileInfoMap.containsKey(ERROR_MESSAGE_KEY)) {
            String url = getFileDownloadURL(fileInfoMap.get(FILE_NAME_KEY), fileInfoMap.get(FILE_ADDRESS_KEY));
            sb.append("<script type=\"text/javascript\">\n");
            sb.append(String.format("window.parent.CKEDITOR.tools.callFunction(%s, '%s', '')\n", callback, url));
            sb.append("</script>\n");
        } else {
            sb.append("<script type=\"text/javascript\">\n");
            sb.append("alert('文件上传的数据格式必须是multipart格式！')");
            sb.append("</script>\n");
        }

        return sb.toString();
    }

    /**
     * 上传文件
     *
     * @param request http请求
     * @return 上传结果List
     * @throws Exception
     */
    private List<Map<String, String>> uploadFiles(HttpServletRequest request) throws Exception {
        List<Map<String, String>> fileUploadResultList = new ArrayList<>();
        String fileAddress;

        //将当前上下文初始化
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest中所有的文件名
            Iterator fileNames = multipartRequest.getFileNames();
            while (fileNames.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multipartRequest.getFile(fileNames.next().toString());
                fileAddress = FastDFSUtil.getUpliadFileAddress2(file); // 保存文件到dfs

                Map<String, String> fileInfoMap = new LinkedHashMap<>();
                fileInfoMap.put(FILE_NAME_KEY, file.getOriginalFilename());
                fileInfoMap.put(FILE_ADDRESS_KEY, String.format("http://%s", fileAddress));
                fileUploadResultList.add(fileInfoMap);
            }
        } else {
            Map<String, String> fileInfoMap = new LinkedHashMap<>();
            fileInfoMap.put(ERROR_MESSAGE_KEY, "The form is not multipart content.");
            fileUploadResultList.add(fileInfoMap);
        }

        return fileUploadResultList;
    }

    /**
     * 获得文件下载URL
     *
     * @param fileName    原始文件名
     * @param fileAddress 文件存储地址
     * @return 文件下载URL
     */
    private String getFileDownloadURL(String fileName, String fileAddress) throws UnsupportedEncodingException {
        return String.format(FILE_DOWNLOAD_URI_FORMAT, URLEncoder.encode(fileAddress, "UTF-8"), URLEncoder.encode(fileName, "UTF-8"));
    }

    /**
     * 获取适用系统下拉框的选项
     *
     * @return 拉框的选项
     */
    @RequestMapping(value = "/getSystemOptions", method = RequestMethod.GET)
    public Object getApplySystems() {
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, SysCategoryEnum.toMap());
    }

    @RequestMapping(value = "/getDocTypeOptions", method = RequestMethod.GET)
    public Object getDocTypes() {
        ModelMap modelMap = new ModelMap();
        return setSuccessModelMap(modelMap, sysDictService.getDictByTypeName(DictType.DOCTYPE));
    }
}
