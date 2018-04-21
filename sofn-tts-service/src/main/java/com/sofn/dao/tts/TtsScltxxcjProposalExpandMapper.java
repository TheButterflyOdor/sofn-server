package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 平台优化建议 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjProposalExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取平台优化建议列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取平台优化建议数据条数
	*/
    long getCount(Map<String, Object> map);

}
