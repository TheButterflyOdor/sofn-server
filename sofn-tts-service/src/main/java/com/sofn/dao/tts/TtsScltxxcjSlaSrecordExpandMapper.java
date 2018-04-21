package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjSlaSale;
import com.sofn.model.generator.TtsScltxxcjSlaSrecord;

import java.util.List;
import java.util.Map;

/**
 * 屠宰产品销售记录 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjSlaSrecordExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取屠宰产品销售记录列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取屠宰产品销售记录数据条数
	*/
    long getCount(Map<String, Object> map);

    int insertByTemp(TtsScltxxcjSlaSale ttsScltxxcjXsdj);

    void updateXsmxStatus(Map<String, Object> map);

    List<TtsScltxxcjSlaSrecord> queryByXsdjId(Map<String, Object> map);

    void deleteRecordFlag(String id);
}
