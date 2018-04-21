package com.sofn.service.asms;


import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysResource;
import com.sofn.provider.sys.SysResourceProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
/**
 * @author dongwenfeng
 * @version 2016年09月20日 下午 4:50
 */

@Service
public class SysResourceService extends BaseService<SysResourceProvider,SysResource> {
    @DubboReference
    public void setSysResourceProvider(SysResourceProvider provider){
        this.provider = provider;
    }

    public PageInfo getSysResourceList(SysResource sysResource, String professionalfiled, String location,
                                       int pageNum, int pageSize, String queryCon){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("professionalfiled",professionalfiled);
        queryParams.put("location",location);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("queryCon",queryCon);
        return provider.getQueryList(queryParams);
    }

    /**
     * 根据ID查看当前专家信息
     * @param id
     * @return
     */
    public SysResource findSysResource(String id){

        return provider.queryById(id);
    }

}
