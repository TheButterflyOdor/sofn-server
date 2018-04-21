package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 模型_判定标准 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelJudgeStandardExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取模型_判定标准分页列表list
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取模型_判定标准count
	*/
    long getCount(Map<String, Object> map);

    /**
     * 根据
     * @param model_id
     * @return
     */
    int getJudgeCountByModel(String model_id);
}
