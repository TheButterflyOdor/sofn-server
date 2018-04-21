package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjCggl;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjCgglAndUserInfo;

import java.util.List;
import java.util.Map;

/**
 * 采购管理 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjCgglExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取采购管理列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取采购管理数据条数
	*/
    long getCount(Map<String, Object> map);


    /**
     * 根据追溯码查询采购管理信息
     * @param map
     */
    TtsScltxxcjCgglAndCustomer getCgglForZsm(Map<String, Object> map);

    /**
     * 当前登录用户在采购管理中是否能查询到对应的追溯数据
     * @param map
     * @return
     */
    long zsmIsCgOrXs(Map<String, Object> map);

    long isExists(String code);

    TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCg(String code);

    TtsScltxxcjCgglAndUserInfo getInfoByTraceCodeFromCgforUsual(String code);


    List<Map<String,Object>> getInfoByTraceCode(Map<String, Object> map);
}
