package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.List;
import java.util.Map;

/**
 * 产品批次合成 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjCppchcExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取产品批次合成列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取产品批次合成数据条数
	*/
    long getCount(Map<String, Object> map);


    /**
     * 批量生成数据
     * @param map
     * @return
     */
    int insertBySelect(Map<String, Object> map);


    /**
     * 通过批次码查询合成前信息
     * @param map
     * @return
     */
    List<Map<String,Object>> getInfoForNewPc(Map<String, Object> map);
    long getCountForNewPc(Map<String, Object> map);

}
