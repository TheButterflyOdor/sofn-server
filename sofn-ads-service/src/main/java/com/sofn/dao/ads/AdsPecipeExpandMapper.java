package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsOrganTask;
import com.sofn.model.generator.AdsPecipe;

import java.util.List;
import java.util.Map;

/**
 * 回执单 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsPecipeExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取回执单列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    List<Map<String,Object>> getAdsPecipePageInfo(Map<String, Object> map);

	/**
	*  获取回执单数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  根据机构任务ID修改上报状态
     *  @author TC
     */
    void updateStatusByID(AdsOrganTask adsOrganTask);
}
