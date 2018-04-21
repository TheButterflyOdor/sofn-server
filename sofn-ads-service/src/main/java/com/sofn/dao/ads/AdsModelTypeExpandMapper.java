package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 模型_种类 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelTypeExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取模型_种类列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取模型_种类数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取全部模型_种类数据列表
     *  @author TC
     */
    List<Map<String, Object>> getPageInfoAll(Map<String, Object> map);

    /**
     *  获取全部模型_种类数据条数
     *  @author TC
     */
    long getCountAll(Map<String, Object> map);

}
