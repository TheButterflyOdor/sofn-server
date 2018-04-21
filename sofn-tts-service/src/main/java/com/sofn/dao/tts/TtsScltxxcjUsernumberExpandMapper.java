package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/18.
 */
@MyBatisDao
public interface TtsScltxxcjUsernumberExpandMapper extends BaseExpandMapper{

    /**
     * 根据主体身份码查询用户号
     * @param entityCode
     * @return
     */
    Integer getUsernumByEntityCode(String entityCode);

    /**
     * 编辑主体身份码对应的身份号
     * @param map
     */
    void updateUsernumByEntityCode(Map<String, Object> map);
}
