package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsModelCheckItem;

import java.util.List;
import java.util.Map;

/**
 * 模型_检测项目 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsModelCheckItemExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取模型_检测项目列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取模型_检测项目数据条数
	*/
    long getCount(Map<String, Object> map);

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
     * 根据对象id查询其所有检测项目
     * @param object_id
     * @return
     */
    List<AdsModelCheckItem> getPageInfoByCheckObjectId(String object_id);

    /**
     * 查询模型下的所有检测项目list
     * @param model_id
     * @return
     */
    List<AdsModelCheckItem> getCheckItemListByModelId(String model_id);

    /**
     * 查询模型下的所有检测项目count
     * @param model_id
     * @return
     */
    int getCheckItemCountByModelId(String model_id);

    /**
     *  根据检测对象ID获取模型_检测集合
     */
    List<AdsModelCheckItem> queryByObjId(Map<String, Object> map);

}
