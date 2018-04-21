package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TtsScltxxcjUser;

import java.util.List;
import java.util.Map;

/**
 * 企业用户及子账户信息表 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface TtsScltxxcjUserExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取企业用户及子账户信息表列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取企业用户及子账户信息表数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     * 重置用户密码
     * @param queryParams
     * @return
     */
    int resetUserPassword(Map<String, Object> queryParams);

    /**
     * 根据注册账号查询登录账号信息
     * @param queryParams
     * @return
     */
    TtsScltxxcjUser getUserByAccount(Map<String, Object> queryParams);

    void queryById(Map<String, Object> queryParams);

    TtsScltxxcjUser getUserByIdCard(String idCard);

    TtsScltxxcjUser getUserByTokenAccount(Map<String, Object> queryParams);

    int updateByAccount(TtsScltxxcjUser ttsScltxxcjUser);

    int updateByacc(TtsScltxxcjUser ttsScltxxcjUser);

    void updateUserByAccount(TtsScltxxcjUser ttsScltxxcjUser);
}
