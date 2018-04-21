package com.sofn.dao.generator;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysDictType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由MyBatis Generator工具自动生成，请不要手动修改
 */
@MyBatisDao
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    List<SysDictType> selectByTypeId (@Param("id") String id);
}