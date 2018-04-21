package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@MyBatisDao
public interface TempDemoExpandMapper extends BaseExpandMapper {

    //获取测试信息列表
    List<Map<String,Object>> getTempDemoList(Map<String, Object> map);
    //获取测试信息列表数量
    long getTempDemoCount(Map<String, Object> map);

}
