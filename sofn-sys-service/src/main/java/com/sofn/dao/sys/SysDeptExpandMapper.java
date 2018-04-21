package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/13.
 */
@MyBatisDao
public interface SysDeptExpandMapper extends BaseExpandMapper {
    //获取条件数据
    List<Map<String,Object>> selectAllByCondition(Map<String, Object> map);
    //获取满足条件的记录数
    long getAllCount(Map<String, Object> map);

    List<SysDept> getSysDeptLists(@Param("pager") Page pager, @Param("status")String status,@Param("deptName")String deptName);

    List<SysDept> getDeptListByRegion(@Param("regionId")String regionId);
}
