package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 屠宰后产品批次合成 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjSlaProPchcExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取屠宰后产品批次合成列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取屠宰后产品批次合成数据条数
	*/
    long getCount(Map<String, Object> map);

    int insertBySelect(Map<String, Object> map);

}
