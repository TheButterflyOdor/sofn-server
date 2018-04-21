package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsModelCheckObject;
import com.sofn.model.generator.AdsModelObjectItemMapping;

import java.util.List;
import java.util.Map;

/**
 * 模型_检测对象 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelCheckObjectExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取模型_检测对象列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取模型_检测对象数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  获取全部模型_检测对象数据列表
     *  @author TC
     */
    List<Map<String, Object>> getPageInfoAll(Map<String, Object> map);

    /**
     *  获取全部模型_检测对象数据条数
     *  @author TC
     */
    long getCountAll(Map<String, Object> map);

    /**
     *  查询该模型_检测对象名称是否已有
     *  @author TC
     */
    long selectByName(Map<String, Object> map);

    /**
     * 根据监测模型id查询检测对象list
     * @param map
     * @return
     */
    List<AdsModelCheckObject> getPageInfoByModelId(Map<String, Object> map);

    /**
     * 根据监测模型id查询检测对象count
     * @param map
     * @return
     */
    int getCountByModelId(Map<String, Object> map);

    /**
     *  获取模型_检测对象
     */
    AdsModelCheckObject queryByModelIdWithName(Map<String, Object> map);

    /**
     *  获取模型下的检测对象LIST
     */

    List<String> getCheckObjectNameListByModelId(String id);

    /**
     *  获取模型_检测对象含有的模型_检测项目列表
     */
    List<Map<String,Object>> getPageInfoChecked(Map<String, Object> map);


    /**
     *  获取模型_检测对象含有的模型_检测项目数据条数
     */
    long getCountChecked(Map<String, Object> map);

    /**
     *  获取模型_检测对象未含有的模型_检测项目列表
     */
    List<Map<String,Object>> getPageInfoUnChecked(Map<String, Object> map);


    /**
     *  获取模型_检测对象未含有的模型_检测项目数据条数
     */
    long getCountUnChecked(Map<String, Object> map);

    /**
     * 批量插入对象数据
     * @param adsModelObjectItemMapping
     * @return
     */
    int insertIntoMapping(AdsModelObjectItemMapping adsModelObjectItemMapping);

    /**
     * 根据ID删除单条模型_检测项目中的检测对象
     * @param adsModelObjectItemMapping
     * @return
     */
    int deleteMappingByID(AdsModelObjectItemMapping adsModelObjectItemMapping);

    /**
     *  获取模型_检测对象
     */
    AdsModelCheckObject queryByName(Map<String, Object> map);
}
