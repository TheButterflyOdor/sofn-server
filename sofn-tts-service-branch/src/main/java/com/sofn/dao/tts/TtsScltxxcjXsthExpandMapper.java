package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjXsth;
import com.sofn.model.generator.TtsScltxxcjXsthAndCustomer;

import java.util.List;
import java.util.Map;

/**
 * 销售退回 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjXsthExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取销售退回列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取销售退回数据条数
	*/
    long getCount(Map<String, Object> map);
    /**
     *  根据销售登记id获取退货详细信息
     */
    TtsScltxxcjXsthAndCustomer queryByXsjlId(String id);

    TtsScltxxcjXsthAndCustomer querySaleInfoById(String id);
}
