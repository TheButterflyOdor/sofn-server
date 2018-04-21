package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCustomer;

import java.util.List;
import java.util.Map;

/**
 * 客户信息管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjCustomerExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取客户信息管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取客户信息管理数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     * 修改客户删除标识
     * @param id
     */
    void updateDelFlag(String id);

    /**
     * 修改客户删除标识 - 批量
     * @param ids
     */
    void batchUpdateDelFlag(String[] ids);

    List<TtsScltxxcjCustomer> getCusromerList(Map<String, Object> map);

    List<TtsScltxxcjCustomer> getCusromerListNotCustomerEntityID(Map<String, Object> map);

    TtsScltxxcjCustomer checkCustomer(Map<String, Object> map);
}
