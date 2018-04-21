package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjProduct;
import com.sofn.model.generator.TtsScltxxcjScgl;

import java.util.List;
import java.util.Map;

/**
 * 产品管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjProductExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取产品管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取产品管理数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     * 修改产品状态
     * @param ttsScltxxcjProduct 产品对象
     * @return
     */
    int changeStatus(TtsScltxxcjProduct ttsScltxxcjProduct);

    /**
     * 根据所属行业获取产品信息
     * @param map 行业字段标识
     * @return
     */
    List<Map<String,Object>> findProList(Map<String, Object> map);

    /**
     * 根据所属行业获取产品信息的总数
     * @param map 行业字段标识
     * @return
     */
    long getProCount(Map<String, Object> map);

    List<Map<String,Object>> findProByEntityCode(Map<String, Object> queryParams);

    long queryProCountByEntityCode(Map<String, Object> queryParams);

    List<TtsScltxxcjScgl> getProType(String entityId);

    //产品登记获取产品列表
    long getProductByTermCount(Map<String, Object> queryParams);

    List<Map<String,Object>> getProductByTerm(Map<String, Object> queryParams);

    List<TtsScltxxcjScgl> getProName(Map<String, Object> queryParams);

    /**
     * 产品存在性判断
     * @param ttsScltxxcjProduct
     * @return
     */
    long isExistedPro(TtsScltxxcjProduct ttsScltxxcjProduct);


    List<Map<String,Object>> getProductforEntity(Map<String, Object> map);

    long getProductforEntityCount(Map<String, Object> map);

}
