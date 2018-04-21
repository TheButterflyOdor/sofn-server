package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjBase;

import java.util.List;
import java.util.Map;

/**
 * 基地信息管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjBaseExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取基地信息管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取基地信息管理数据条数
	*/
    long getCount(Map<String, Object> map);

    int changeStatus(TtsScltxxcjBase tscltxxcjbase);

    void removeById(Map<String, Object> queryParams);

    List<Map<String,Object>> findBaseByEntityCode(Map<String, Object> queryParams);

    long findBaseCountByEntityCode(Map<String, Object> queryParams);

    void addBase(TtsScltxxcjBase ttsScltxxcjBase);

    String findBaseMainProcById(Map<String, Object> params);

    void updateMainProcs(Map<String, Object> params);
}
