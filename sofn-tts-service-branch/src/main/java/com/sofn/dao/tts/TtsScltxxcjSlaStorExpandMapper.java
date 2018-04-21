package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaStor;

import java.util.List;
import java.util.Map;

/**
 * 屠宰库存 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjSlaStorExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取屠宰库存列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取屠宰库存数据条数
	*/
    long getCount(Map<String, Object> map);

    List<TtsScltxxcjSlaStor> getSphcByIds(Map<String, Object> map);

    List<Map<String,Object>> getCheckProduct(Map<String, Object> map);

    /**
     *  获取可用于批次合成的屠宰库存列表
     */
    List<Map<String,Object>> getStorPageInfo(Map<String, Object> map);


    /**
     *  获取可用于批次合成的屠宰库存数据条数
     */
    long getStorCount(Map<String, Object> map);

    List<Map<String,Object>> getStor(Map<String, Object> map);
    long getStorC(Map<String, Object> map);


    void updateProductStoreCount(Map<String, Object> map);

    void updateProductStoreFreezeCount(Map<String, Object> map);
    /**
     * 采购管理通过，销售登记ID，修改屠宰管理库存
     * @param id
     */
    void updateStoreCountForPurchase(String id);
}
