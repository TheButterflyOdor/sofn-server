package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;

import java.util.List;
import java.util.Map;

/**
 * 屠宰产品销售 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjSlaSaleExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取屠宰产品销售列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取屠宰产品销售数据条数
	*/
    long getCount(Map<String, Object> map);


    TtsScltxxcjSlaCustomer getSaleAndCustomerById(String id);


    /**
     *  获取屠宰产品采购数据条数
     */
    List<Map<String,Object>> getPurchasePageInfo(Map<String, Object> map);
    long getPurchaseCount(Map<String, Object> map);
}
