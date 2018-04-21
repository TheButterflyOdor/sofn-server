package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysDept;
import com.sofn.provider.sys.SysDeptProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/14.
 */
@Service
public class SysDeptService extends BaseService<SysDeptProvider,SysDept> {

    @DubboReference
    public void setSysDeptProvider(SysDeptProvider provider){
        this.provider = provider;
    }
    //获取部门列表
    public PageInfo getSysDeptList(String status, int pageNum, int pageSize){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("status", status);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        return provider.getSysDeptList(queryParams);
    }

    public List<SysDept> getSysDeptLists(Page pager, String status,String deptName){
        return provider.getSysDeptLists(pager,status,deptName);
    }

    public long getRecordsTotal(String status,String deptName){
        return provider.getRecordsTotal(status,deptName);
    }
    //添加部门
    public int addSysDept(SysDept sysDept){
        return provider.addSysDept(sysDept);
    }
    //根据类型查找部门
    public SysDept findSysDeptByst(String id){
        return provider.findDailyEnforceLawById(id);
    }
    //修改部门信息
    public  int updateSysDept(SysDept sysDept){
        return provider.updateSysDept(sysDept);
    }
    //删除部门
    public int deleteSysDept(String id){
        return provider.deleteSysDept(id);
    }
    //根据区域ID获取所有部门
    public List<SysDept> getDeptListByRegion(String regionId){
        return provider.getDeptListByRegion(regionId);
    }

}
