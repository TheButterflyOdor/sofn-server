package com.sofn.service.sys;


import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysResource;
import com.sofn.provider.sys.SysResourceProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
@Service
public class SysResourceService extends BaseService<SysResourceProvider,SysResource>{
    @DubboReference
    public void setProvider(SysResourceProvider provider) {
        this.provider = provider;
    }


    /**
     * 分页条件查询
     * @param resourcetype
     * @param professionalfiled
     * @param location
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getSysResourceList(String resourcetype,String professionalfiled,String location,String name,int pageNum, int pageSize){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("resourcetype",resourcetype);
        queryParams.put("professionalfiled",professionalfiled);
        queryParams.put("location",location);
        queryParams.put("name",name);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        return provider.getPageList(queryParams);
    }

    /**
     * 查询重复资源数量
     * @param name 姓名
     * @param unit 单位
     * @return 重复数量
     */
    public Integer getRepeatResourceCount(String name, String unit) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name", name);
        queryParams.put("unit", unit);

        return provider.getRepeatResourceCount(queryParams);
    }
}
