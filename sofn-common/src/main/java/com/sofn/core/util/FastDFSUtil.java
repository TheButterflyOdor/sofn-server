package com.sofn.core.util;

import com.sofn.core.util.fastDFS.ConnectionPool;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * 内网
 * Created by wuye on 16/11/1.
 */
public final class FastDFSUtil {
    private static final String METADATA_NAME_FILENAME = "fileName";
    private static final String METADATA_NAME_FILEEXTNAME = "fileExtName";
    private static final String METADATA_NAME_FILELENGTH = "fileLength";
    /**
     * 连接池
     */
    private static ConnectionPool connectionPool = null;
    /**
     * 连接池最小连接数
     */
    private static long minPoolSize = 8;
    /**
     * 连接池最大连接数
     */
    private static long maxPoolSize = 50;
    /**
     * 默认等待时间（单位：秒）
     */
    private static long waitTimes = 200;
    // fastdfs文件下载路径
    private static String downloadPath;

    static {
        init();
    }

    /**
     * 初始化线程池
     *
     * @Description:
     */
    private static void init() {
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties(ConnectionPool.FASTDFS_CONIFG);
            downloadPath = props.getProperty("fastdfs.download_path");
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionPool = new ConnectionPool(minPoolSize, maxPoolSize, waitTimes);
    }

    /**
     * 文件上传（File）
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String getUpliadFileAddress(File file) throws Exception {
        String address = downloadPath;

        try {
            String[] files = uploadFile(file, file.getName(), file.length());
            List<String> files2 = Arrays.asList(files);
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < files2.size(); i++) {
                str.append(files2.get(i));
                str.append("/");
            }
            str.deleteCharAt(str.length() - 1);
            address += str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return address;
    }

    /**
     * 文件上传（MultipartFile）
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String getUpliadFileAddress2(MultipartFile file) throws Exception {
        String address = downloadPath;
        try {
            String[] files = uploadFile2(file);
            List<String> files2 = Arrays.asList(files);
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < files2.size(); i++) {
                str.append(files2.get(i));
                str.append("/");
            }
            str.deleteCharAt(str.length() - 1);
            address += str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     * @return
     * @deprecated 准备删除
     */
    public static ResponseEntity<byte[]> dowloadFile(String fileUrl, String fileName) throws Exception {
        ResponseEntity<byte[]> result = null;
        try {
            result = dowload(fileUrl, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public InputStream dowloadFile2(String fileUrl, String fileName) throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = dowload2(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     * @return
     */
    public static InputStream dowloadFileToInputStream(String fileUrl) throws Exception {
        InputStream inputStream = null;
        try {
            inputStream = dowload2(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 删除文件
     */
    public static String deleteFastDFS(String fileUrl) throws Exception {
        String str = "";

        try {
            int i = deleteFile(fileUrl);
            if (i == 0) {
                str = "删除成功";
            } else {
                str = "删除失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 上传文件(File)
     */
    public static String[] uploadFile(File file, String uploadFileName, long fileLength) throws Exception {
        byte[] fileBuff = getFileBuffer(new FileInputStream(file), fileLength);
        String[] files = null;
        String fileExtName = "";
        if (uploadFileName.contains(".")) {
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        } else {
            System.out.print("Fail to upload file,because the format of filename is illegal");
            return null;
        }

        //建立链接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);

        //设置元信息
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair(METADATA_NAME_FILENAME, uploadFileName);
        metaList[1] = new NameValuePair(METADATA_NAME_FILEEXTNAME, fileExtName);
        metaList[2] = new NameValuePair(METADATA_NAME_FILELENGTH, String.valueOf(fileLength));

        //上传文件
        try {
            files = client.upload_file(fileBuff, fileExtName, metaList);
        } catch (MyException e) {
            e.printStackTrace();
        }
        connectionPool.checkin(trackerServer, logId);

        return files;
    }


    /**
     * 文件上传（MultipartFile）
     *
     * @param file
     * @return
     */
    public static String[] uploadFile2(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String[] fileId;
        String fileExtName = fileName.substring(fileName.lastIndexOf("."));

        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        //设置元信息
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair(METADATA_NAME_FILENAME, fileName);
        metaList[1] = new NameValuePair(METADATA_NAME_FILEEXTNAME, fileExtName);
        metaList[2] = new NameValuePair(METADATA_NAME_FILELENGTH, String.valueOf(file.getSize()));

        //上传文件 (2018-3-29  解决大文件上传可能会内存溢出的问题)
        fileId = client.upload_file(null, file.getSize(), new UploadStream(file.getInputStream(), file.getSize()), fileExtName, metaList);
        connectionPool.checkin(trackerServer, logId);

        return fileId;
    }

    /**
     * 文件下载
     *
     * @param fileUrl
     * @return
     * @deprecated 准备删除，该方法下载的实现存在问题，下载大文件可能会有内存溢出的问题，而且响应客户端慢
     * @see #download(String, String, HttpServletRequest, HttpServletResponse)
     */
    public static ResponseEntity<byte[]> dowload(String fileUrl, String fileName) throws Exception {
        HttpHeaders headers = new HttpHeaders();

        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);

        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/") + 1);
        String specFileName = fileName + substr.substring(substr.indexOf("."));
        //文件名拆析
        if (specFileName.contains("..")) {
            specFileName = specFileName.replace("..", ".");
            int tempint = filterRepeatString(specFileName);
            if (tempint > 0) {
                specFileName = specFileName.substring(0, tempint);
            }
        }

        byte[] b = client.download_file(group, remoteFileName);
        connectionPool.checkin(trackerServer, logId);

        headers.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", new String(specFileName.getBytes("UTF-8"), "iso-8859-1")));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(b, headers, HttpStatus.OK);
    }

    /**
     * 文件下载
     *
     * <p>
     *     该方法下载文件响应客户端快，无JVM内存溢出问题，推荐使用
     * </p>
     * @param fileUrl 文件存储地址
     * @param fileName 文件名
     * @param request http请求
     * @param response http响应
     * @throws Exception 下载出错抛出的异常
     */
    public static void download(String fileUrl, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        if (StringUtils.isEmpty(fileUrl)) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
//            response.sendError(HttpStatus.NOT_FOUND.value(), "文件未找到！");
            return;
        }
        response.setStatus(HttpStatus.OK.value());

        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String groupName = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/") + 1);

        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);

        try {
            NameValuePair[] fileMetaData = client.get_metadata(groupName, remoteFileName);
            boolean fileNameIsEmpty = StringUtils.isEmpty(fileName);
            for (NameValuePair nameValuePair : fileMetaData) {
                // 如果文件名参数为空，从文件元数据中获取文件名
                if (fileNameIsEmpty) {
                    if (METADATA_NAME_FILENAME.equals(nameValuePair.getName())) {
                        fileName = nameValuePair.getValue();
                        continue;
                    }
                }
                // 从文件元数据中获取文件字节数
                if (METADATA_NAME_FILELENGTH.equals(nameValuePair.getName())) {
                    response.setContentLengthLong(Long.parseLong(nameValuePair.getValue()));
                }
            }

            // 解决微软家浏览器文件下载时，文件名中文乱码问题
            if (WebUtil.isMSBrowser(request)) {
                String encodeFilename = URLEncoder.encode(fileName, "UTF-8");
                encodeFilename = encodeFilename.replace("+", "%20"); // 解决文件名称中有空格转码后有“+”号的问题
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", encodeFilename));
            } else {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", new String(fileName.getBytes("UTF-8"), "iso-8859-1")));
            }
            // 下载文件，循环读入文件内容并写入到客户端
            client.download_file(groupName, remoteFileName, new DownloadCallback() {
                /**
                 * recv file content callback function, may be called more than once when the file downloaded
                 *
                 * @param file_size file size
                 * @param data      data buff
                 * @param bytes     data bytes
                 * @return 0 success, return none zero(errno) if fail
                 */
                @Override
                public int recv(long file_size, byte[] data, int bytes) throws IOException {
                    OutputStream os = response.getOutputStream();
                    os.write(data, 0, bytes);

                    return 0;
                }
            });
        } catch (IOException | MyException | NumberFormatException e) {
            throw e;
        } finally {
            connectionPool.checkin(trackerServer, logId);
        }
    }

    public static void dowloadtu(String fileUrl, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);

        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/") + 1);
        String specFileName = fileName + substr.substring(substr.indexOf("."));
        //文件名拆析
        if (specFileName.contains("..")) {
            specFileName = specFileName.replace("..", ".");
            int tempint = filterRepeatString(specFileName);
            if (tempint > 0) {
                specFileName = specFileName.substring(0, tempint);
            }
        }

        byte[] b = client.download_file(group, remoteFileName);
        connectionPool.checkin(trackerServer, logId);

        String ptype = specFileName.substring(specFileName.indexOf(".") + 1);
        String pictype = "image/" + ptype;

        response.setContentType(pictype); // 设置返回的文件类型
        OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        toClient.write(b); // 输出数据
        toClient.close();

    }

    /**
     * 过滤重复
     *
     * @param
     * @return
     * @throws Exception
     */
    public static int filterRepeatString(String specFileName) throws Exception {

        int num = 0;
        for (int i = 0; i < specFileName.length(); i++) {
            if (specFileName.substring(i, (i + 1)).indexOf(".") != -1) {
                num = num + 1;
                if (num > 1) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 文件下载2
     *
     * @param fileUrl
     * @return
     */
    public static InputStream dowload2(String fileUrl) throws Exception {
        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);
        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/") + 1);
        byte[] b = client.download_file(group, remoteFileName);
        InputStream inputStream = new ByteArrayInputStream(b);
        connectionPool.checkin(trackerServer, logId);
        return inputStream;
    }

    public InputStream dowload21(String fileUrl) throws Exception {
        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);
        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/") + 1);
        byte[] b = client.download_file(group, remoteFileName);
        InputStream inputStream = new ByteArrayInputStream(b);
        connectionPool.checkin(trackerServer, logId);
        return inputStream;
    }

    /**
     * 文件删除
     *
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static int deleteFile(String fileUrl) throws Exception {
        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);
        //获取文件名组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/") + 1);
        int result = client.delete_file(group, remoteFileName);
        connectionPool.checkin(trackerServer, logId);
        return result;
    }

    private static byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {
        byte[] buffer = new byte[256 * 1024];
        byte[] fileBuffer = new byte[(int) fileLength];

        int count = 0;
        int lenght = 0;

        while ((lenght = inStream.read(buffer)) != -1) {
            for (int i = 0; i < lenght; ++i) {
                fileBuffer[count + i] = buffer[i];
            }
            count += lenght;
        }
        return fileBuffer;
    }

    public static String uploadFileByStream(InputStream inStream, String uploadFileName, long fileLength) throws IOException, MyException, Exception {
        String[] results = null;
        String fileExtName = "";
        if (uploadFileName.contains(".")) {
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        } else {
            return null;
        }
        //建立连接
        String logId = UUID.randomUUID().toString();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", uploadFileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));

        try {
            // results[0]: groupName, results[1]: remoteFilename.
            results = client.upload_file(null, fileLength, new UploadFileSender(inStream), fileExtName, metaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionPool.checkin(trackerServer, logId);
        trackerServer.close();
        List<String> files2 = Arrays.asList(results);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < files2.size(); i++) {
            str.append(files2.get(i));
            str.append("/");
        }
        str.deleteCharAt(str.length() - 1);
        String address = downloadPath;
        address += str.toString();
        connectionPool.checkin(trackerServer, logId);
        return address;
    }

    private static class UploadFileSender implements UploadCallback {

        private InputStream inStream;

        public UploadFileSender(InputStream inStream) {
            this.inStream = inStream;
        }

        public int send(OutputStream out) throws IOException {
            int readBytes;
            while ((readBytes = inStream.read()) > 0) {
                out.write(readBytes);
            }
            return 0;
        }
    }
}

