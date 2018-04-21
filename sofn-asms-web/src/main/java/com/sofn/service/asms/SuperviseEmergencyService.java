package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.AsmsEmergencyTask;
import com.sofn.provider.asms.SuperviseEmergencyProvider;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * Created by zhangdong on 2016/9/27 11:21.
 */
@Service
public class SuperviseEmergencyService extends BaseService<SuperviseEmergencyProvider,AsmsEmergencyTask> {
    @DubboReference
    public void setSuperviseEmergencyProvider(SuperviseEmergencyProvider provider){

        this.provider = provider;
    }

    public String addSuperviseEmergency(AsmsEmergencyTask asmsEmergencyTask){
        String reId="";

        if(StringUtils.isNullEmpty(asmsEmergencyTask.getId())){
            reId=StringUtils.getUUIDString().toString().replace("-", "");
            asmsEmergencyTask.setId(reId);
            asmsEmergencyTask.setDelFlag("N");
            asmsEmergencyTask.setEnable("0");
            asmsEmergencyTask.setCreateTime(new Date());
            asmsEmergencyTask.setUpdateTime(new Date());
             provider.addAsmsEmergencyTask(asmsEmergencyTask);
        }else{
            reId=asmsEmergencyTask.getId();
            asmsEmergencyTask.setUpdateTime(new Date());
            provider.updateAsmsEmergencyTask(asmsEmergencyTask);
        }
        return reId;
    }

    public PageInfo getAsmsEmergencyTaskList(AsmsEmergencyTask asmsEmergencyTask, String taskYear,String dateBegin, String dateEnd,
                                          int pageNum, int pageSize,String queryCon, String releaseUnit, String areaId, String bearUnit,String createOrgId){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("taskYear",taskYear);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("releaseUnit",releaseUnit);
        queryParams.put("areaId",areaId);
        if(bearUnit!=null&&!"".equals(bearUnit)) {
            queryParams.put("bearUnit", bearUnit.split(","));
        }
        queryParams.put("queryCon",queryCon);
        queryParams.put("createOrgId",createOrgId);
        return provider.getSuperviseEmergencyList(queryParams);
    }

    public AsmsEmergencyTask findAsmsEmergencyTaskById(String id){
        return provider.findAsmsEmergencyTaskById(id);
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
            fileD.mkdirs();
        }
        //文件路径
        StringBuilder sb = new StringBuilder();
        while(iterator.hasNext()){
            FileItem fileItem = iterator.next();
            if(fileItem.getName()!=null) {
                String fileName = UUID.randomUUID().toString().replace("-", "") + fileItem.getName().substring(fileItem.getName().lastIndexOf("."),fileItem.getName().length());
                File file = new File(tomcatPath +"\\"+ fileName);
                sb.append("uploadFile/baseInspection/"+com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd") +"/" + fileName);
                fileItem.write(file);
            }

        }
        Map<String,Object> map = new HashMap<>();
        map.put("path",sb.toString());
        return map;
    }
}
