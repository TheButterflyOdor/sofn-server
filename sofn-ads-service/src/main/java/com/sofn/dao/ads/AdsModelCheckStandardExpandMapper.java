package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsModelCheckStandard;

import java.util.List;
import java.util.Map;

/**
 * 模型_检测标准 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelCheckStandardExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取模型_检测标准列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取模型_检测标准数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取全部模型_检测标准数据列表
     *  @author TC
     */
    List<Map<String, Object>> getPageInfoAll(Map<String, Object> map);

    /**
     *  获取全部模型_检测标准数据条数
     *  @author TC
     */
    long getCountAll(Map<String, Object> map);

    /**
     *  根据项目ID获取检测标准
     *  @author cq
     */
    AdsModelCheckStandard queryByItemId(Map<String, Object> map);

}
