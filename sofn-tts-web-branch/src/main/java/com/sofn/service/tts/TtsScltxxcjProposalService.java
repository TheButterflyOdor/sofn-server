package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjProposal;
import com.sofn.provider.tts.TtsScltxxcjProposalProvider;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 平台优化建议 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjProposalService extends BaseService<TtsScltxxcjProposalProvider, TtsScltxxcjProposal> {

    @DubboReference
    public void setTtsScltxxcjProposalProvider(TtsScltxxcjProposalProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjProposal bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    /**
     * 单纯上传文件--暂加
     * @param request
     * @throws Exception
     */
    public Map<String,Object> upload(HttpServletRequest request) throws Exception{
        //创建一个基于磁盘的文件项的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置工厂约束
        factory.setSizeThreshold(4096);//设置缓冲区大小，这里是4kb
        //创建一个新的文件上传机制
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置整体请求大小约束
        upload.setFileSizeMax(4194304);// 设置最大文件尺寸，这里是4MB
        //可能有多个文件
        List<FileItem> items;
        items = upload.parseRequest(request);
        Iterator<FileItem> iterator = items.iterator();
        String tomcatPath = request.getSession().getServletContext().getRealPath("")+"\\uploadFile\\baseInspection\\"
                +com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd");
        File fileD = new File(tomcatPath);
        if(!fileD.exists()){
            fileD.mkdir();
        }
        //文件预览路径
        StringBuilder sb = new StringBuilder();
        //文件真实路径
        String truePath = null;
        String name = null;
        while (iterator.hasNext()){
            FileItem fileItem = iterator.next();
            if(fileItem.getName()!=null) {
                name = fileItem.getName();
                String fileName = UUID.randomUUID().toString().replace("-", "") + fileItem.getName().substring(fileItem.getName().lastIndexOf("."),fileItem.getName().length());
//                File file = new File(tomcatPath +"\\"+ fileName);
                String tempPath = request.getServletContext().getRealPath("");
                String savePath = tempPath.substring(0,tempPath.indexOf("sofn-tts-web"));
                File file = new File(savePath+"soft-webfont/images/attachment/"+fileName);
//                sb.append("uploadFile/baseInspection/"+com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd") +"/" + fileName);
                sb.append("images/attachment/"+fileName);
                truePath = file.toString();
                fileItem.write(file);
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","200");
        map.put("name",name);
        map.put("path",sb.toString());
        map.put("truePath",truePath);
        return map;
    }


}