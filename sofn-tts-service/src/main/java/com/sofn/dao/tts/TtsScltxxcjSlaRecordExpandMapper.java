package com.sofn.dao.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaRecord;

import java.util.List;
import java.util.Map;

/**
 * 屠宰记录 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjSlaRecordExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取屠宰记录列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取屠宰记录数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     * 获取屠宰记录
     * @param params
     * @return
     */
    List<Map<String,Object>> findById(Map<String,Object> params);

    long findCount(Map<String, Object> params);
}
