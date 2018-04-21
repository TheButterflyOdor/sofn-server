package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysDept;

import java.util.List;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysDeptMapper extends BaseMapper<SysDept> {

    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    SysDept selectByPrimaryKey(String id);

    List<SysDept> selectAll();

    int updateByPrimaryKey(SysDept record);
}