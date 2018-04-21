package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.SysOperateLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wuye on 2016/12/8.
 */
@MyBatisDao
public interface SysOperateLogExpandMapper extends BaseExpandMapper{

    /**
     *  获取模板管理列表
     */
    List<SysOperateLog> getPageInfo(Map<String,Object> map);


    /**
     *  获取模板管理数据条数
     */
    long getCount(Map<String, Object> map);


    List<SysOperateLog> findByIp(@Param(value = "operateIp") String operateIp);
}
