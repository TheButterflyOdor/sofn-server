package com.sofn.web.sys;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import com.sofn.core.config.Global;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by huazi on 16-8-26.
 */
public class FileOpController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    @PostMapping("/uploadfdfs")
    public Map upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        File tempPathFile=new File(Global.getConfig("fdfs.tmp.path"));
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
        factory.setRepository(tempPathFile);// 设置缓冲区目录

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // Set overall request size constraint
        upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

        List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
        if(items.size()==0){
            return null;
        }
        Map map=upload(items);
        return map;
    }


    @RequestMapping("/downloadfdfs")
    public void download(HttpServletRequest request, HttpServletResponse response,String fileId) throws Exception{

        StorageClient1 sc1 = initFdfsClient();

        byte[] downByte=sc1.download_file1(fileId);
        NameValuePair[] meta_list=sc1.get_metadata1(fileId);
        String filename=meta_list[0].getValue();

        response.addHeader("Content-Disposition", "attachment;filename=" + filename);//设置文件名，attachment和filename之间是分号，注意！

        response.addHeader("Content-Length", (new Long(downByte.length)).toString());//设置大小
        OutputStream outputStream=response.getOutputStream();
        outputStream.write(downByte);
    }

    private StorageClient1 initFdfsClient() {
        try {
            ClientGlobal.init("/etc/fdfs/client.conf");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            TrackerGroup  tg = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress("172.16.7.157", 22122)});
            TrackerClient tc = new TrackerClient(tg);

            TrackerServer ts = tc.getConnection();
            if (ts == null) {
                logger.debug("getConnection return null");
                return null;
            }

            StorageServer ss = tc.getStoreStorage(ts);
            if (ss == null) {
                logger.debug("getStoreStorage return null");
                return null;
            }

            StorageClient1 sc1 = new StorageClient1(ts, ss);
            return sc1;
        }catch (IOException ioEx){
            ioEx.printStackTrace();
            return null;
        }catch (MyException mEx){
            mEx.printStackTrace();
            return null;
        }
    }
    private Map upload(List<FileItem> items ){
        Iterator<FileItem> i = items.iterator();
        Map map=new HashMap();
        int fileCount=0;
        while (i.hasNext()) {
            try {
                FileItem fi = (FileItem) i.next();
                if(fi.isFormField()){
                    //表单数据,不处理
                    continue;
                }
                StorageClient1 sc1 = initFdfsClient();

                String fileName = fi.getName();

                NameValuePair[] metaList = new NameValuePair[1];
                metaList[0]=new NameValuePair("filename",fileName);
                byte[] byteBuffer=new byte[(int)fi.getSize()];
                fi.getInputStream().read(byteBuffer);
                String fileid = sc1.upload_file1(byteBuffer, fileName.substring(fileName.lastIndexOf(".")+1), metaList);

                logger.info("Upload local file " + fileName + " ok, fileid=" + fileid);

                map.put("fileid"+String.valueOf(fileCount),fileid);
                fileCount++;

            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }

        }
        return map;
    }

}
