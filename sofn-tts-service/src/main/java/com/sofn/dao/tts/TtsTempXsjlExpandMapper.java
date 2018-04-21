package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 销售记录临时数据表 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsTempXsjlExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取销售记录临时数据表列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取销售记录临时数据表数据条数
	*/
    long getCount(Map<String, Object> map);


    /**
     * 通过产品类型进行分组,并返回分组后，销售数量，库存总数量
     * @return
     */
    List<Map<String,Object>> getGroupProduct(Map<String,Object> map);

    /**
     * 清空数据表
     */
    @Select("TRUNCATE TABLE TTS_TEMP_XSJL")
    void deleteAll();

}
