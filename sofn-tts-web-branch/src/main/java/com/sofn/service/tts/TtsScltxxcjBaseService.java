package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjBase;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.provider.tts.TtsScltxxcjBaseProvider;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 基地信息管理 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjBaseService extends BaseService<TtsScltxxcjBaseProvider, TtsScltxxcjBase> {

    @DubboReference
    public void setTtsScltxxcjBaseProvider(TtsScltxxcjBaseProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjBase bean, int pageNum, int pageSize,TtsScltxxcjRegiter user,String status) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("base",bean);
        queryParams.put("user",user);
        queryParams.put("status",status);
        return provider.getPageInfo(queryParams);
    }

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
        while (iterator.hasNext()){
            FileItem fileItem = iterator.next();
            if(fileItem.getName()!=null) {
                String fileName = UUID.randomUUID().toString().replace("-", "") + fileItem.getName().substring(fileItem.getName().lastIndexOf("."),fileItem.getName().length());
//                File file = new File(tomcatPath +"\\"+ fileName);
                String tempPath = request.getServletContext().getRealPath("");
                String savePath = tempPath.substring(0,tempPath.indexOf("sofn-tts-web"));
                File file = new File(savePath+"soft-webfont/images/baseImage/"+fileName);
//                sb.append("uploadFile/baseInspection/"+com.sofn.core.util.DateUtil.getDateTime("yyyyMMdd") +"/" + fileName);
                sb.append("images/baseImage/"+fileName);
                 truePath = file.toString();
                fileItem.write(file);
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","200");
        map.put("path",sb.toString());
        map.put("truePath",truePath);
        return map;
    }


    public int changeBaseStatus(TtsScltxxcjBase tscltxxcjbase) {

        return provider.updateStatus(tscltxxcjbase);
    }

    public void deleteById(String id) {
        Map<String, Object> queryParams = new HashMap<String,Object>();
        queryParams.put("id", id);
        provider.deleteById(queryParams);
    }

    public void batchDeleteByIds(String[] ids) {
        provider.batchDeleteByIds(ids);
    }

    public boolean isExistedBase(TtsScltxxcjBase tscltxxcjbase){ return provider.isExistedBase(tscltxxcjbase); }

    /**
     * 根据主体身份码查询基地信息列表
     * @param pageNum
     * @param length
     * @param entityCode
     * @return
     */
    public PageInfo<TtsScltxxcjBase> queryBaseByEntityCode(int pageNum, int length, String entityCode) {
        Map<String, Object> queryParams = new HashMap<String,Object>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", length);
        queryParams.put("entityCode",entityCode);
        return provider.queryBaseByEntity(queryParams);
    }

    public void addBase(TtsScltxxcjBase ttsScltxxcjBase) {
        provider.addBase(ttsScltxxcjBase);
    }

    /**
     * 添加基地主要产品
     * @param id 需要添加的基地id
     * @param procName 主要产品的名称
     * @return 返回true表示操作成功
     */
    public boolean updateMainProc(String id,String procName){
        boolean isTrue = true;
        try {
            if (id == null || id == ""){
                isTrue = false;
                return isTrue;
            }
            if (procName == null || procName == ""){
                isTrue = false;
                return isTrue;
            }
            String proc = findBaseMainProc(id);
            String procNames = "";
            if (proc == null || proc == ""){
                procNames = procName;
            }else{
                String[] procs = proc.split(",");
                for (int i = 0; i < procs.length; i++){
                    if (procs[i].equals(procName)){
                        return isTrue;
                    }
                }
                procNames = StringUtils.join(procs,",")+","+procName;
            }
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("id",id);
            params.put("procNames",procNames);
            provider.updateMainProcs(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isTrue;
    }

    private String findBaseMainProc(String id){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",id);
        String mainProc = provider.findBaseMainProcById(param);
        return mainProc;
    }


}