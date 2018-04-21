package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysMenu;

import java.util.List;


@MyBatisDao
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);
}