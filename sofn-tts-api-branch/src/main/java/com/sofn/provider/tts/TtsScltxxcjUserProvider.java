package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.TtsScltxxcjUser;

import java.util.List;
import java.util.Map;

/**
 * 企业用户及子账户信息表模型对象
 *
 * @author moon.l
 */
public interface TtsScltxxcjUserProvider extends BaseProvider<TtsScltxxcjUser> {

    public PageInfo<TtsScltxxcjUser> getPageInfo(Map<String, Object> map);


    int resetUserPassword(Map<String, Object> queryParams);

    TtsScltxxcjUser findUserByAccount(Map<String, Object> queryParams);

    void findById(Map<String, Object> queryParams);

    TtsScltxxcjUser getUserByIdCard(String idCard);

    TtsScltxxcjUser getUserByTokenAccount(Map<String, Object> queryParams);

    void updateByAcc(TtsScltxxcjUser ttsScltxxcjUser);

    void batchEditDelFlag(String[] ids);

    TtsScltxxcjUser selectUserById(String id);

    void updateUserInfoById(TtsScltxxcjUser ttsScltxxcjUser);

    TtsScltxxcjUser findUserByIdCard(Map<String, Object> params);

    void updateUserInfo(TtsScltxxcjUser ttsScltxxcjUser);

    int insertUser(TtsScltxxcjUser ttsScltxxcjUser);

    public List<TtsScltxxcjUser> queryUsers( Map<String, Object> queryParams);
}