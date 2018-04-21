package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaProduct;

import java.util.List;
import java.util.Map;

/**
 * 屠宰后产品 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjSlaProductExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取屠宰后产品列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取屠宰后产品数据条数
	*/
    long getCount(Map<String, Object> map);

    List<TtsScltxxcjSlaProduct> getProductList();
}
