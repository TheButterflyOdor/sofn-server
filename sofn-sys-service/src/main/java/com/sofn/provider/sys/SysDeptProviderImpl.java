package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysDeptMapper;
import com.sofn.dao.sys.SysDeptExpandMapper;
import com.sofn.model.generator.SysDept;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/13.
 */
@DubboService(interfaceClass = SysDeptProvider.class)
public class SysDeptProviderImpl extends BaseProviderImpl<SysDept> implements SysDeptProvider {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysDeptExpandMapper sysDeptExpandMapper;


    @Override
    public long getRecordsTotal(String status,String deptName) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("status", status);
        queryParams.put("deptName", deptName);
        return sysDeptExpandMapper.getAllCount(queryParams);
    }

    @Override
    public List<SysDept> getSysDeptLists(Page pager, String status,String deptName) {
        return sysDeptExpandMapper.getSysDeptLists(pager, status,deptName);
    }

    @Override
    public List<SysDept> getDeptListByRegion(String regionId) {
        return sysDeptExpandMapper.getDeptListByRegion(regionId);
    }


    /**
     * 新增部门
     * @param sysDept
     * @return
     */
    @Override
    public int addSysDept(SysDept sysDept) {
        return sysDeptMapper.insert(sysDept);
    }

    /**
     * 查询部门列表
     * @param map
     * @return
     */
    @Override
    public PageInfo<SysDept> getSysDeptList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = sysDeptExpandMapper.selectAllByCondition(map);
        long count = sysDeptExpandMapper.getAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 查询部门详细
     * @param id
     * @return
     */
    @Override
    public SysDept findDailyEnforceLawById(String id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改部门
     * @param sysDept
     * @return
     */
    @Override
    public int updateSysDept(SysDept sysDept) {
        return sysDeptMapper.updateByPrimaryKey(sysDept);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public int deleteSysDept(String id) {
        return sysDeptMapper.deleteByPrimaryKey(id);
    }
}
