package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjProductType;

import java.util.List;
import java.util.Map;

/**
 * 产品品种管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjProductTypeExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取产品品种管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取产品品种管理数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     * 修改产品品种状态
     * @param ttsScltxxcjProductType
     * @return
     */
    int changeStatus(TtsScltxxcjProductType ttsScltxxcjProductType);

    List<TtsScltxxcjProductType> getList();

    TtsScltxxcjProductType getTypeByCode(Map<String,Object> queryParams);

    void addProductType(TtsScltxxcjProductType proType);
}
