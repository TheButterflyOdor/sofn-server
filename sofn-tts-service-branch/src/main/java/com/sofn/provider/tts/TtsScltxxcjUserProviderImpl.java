package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.TtsScltxxcjUserMapper;
import com.sofn.dao.tts.TtsScltxxcjUserExpandMapper;
import com.sofn.model.generator.TtsScltxxcjUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 企业用户及子账户信息表 providerImpl 实现
 * Created by moon.l
 */
@DubboService(interfaceClass = TtsScltxxcjUserProvider.class)
public class TtsScltxxcjUserProviderImpl extends BaseProviderImpl<TtsScltxxcjUser> implements TtsScltxxcjUserProvider {

    @Autowired
    private TtsScltxxcjUserExpandMapper TtsScltxxcjUserExpandMapper;
    @Autowired
    private TtsScltxxcjUserMapper TtsScltxxcjUserMapper;

    public PageInfo<TtsScltxxcjUser> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = TtsScltxxcjUserExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjUserExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int resetUserPassword(Map<String, Object> queryParams) {
        return TtsScltxxcjUserExpandMapper.resetUserPassword(queryParams);
    }

    @Override
    public TtsScltxxcjUser findUserByAccount(Map<String, Object> queryParams) {

        return TtsScltxxcjUserExpandMapper.findUserByAccount(queryParams);
    }

    @Override
    public void findById(Map<String, Object> queryParams) {
        TtsScltxxcjUserExpandMapper.queryById(queryParams);
    }

    @Override
    public TtsScltxxcjUser getUserByIdCard(String idCard) {
        return TtsScltxxcjUserExpandMapper.getUserByIdCard(idCard);
    }

    @Override
    public TtsScltxxcjUser getUserByTokenAccount(Map<String, Object> queryParams) {
        return TtsScltxxcjUserExpandMapper.getUserByTokenAccount(queryParams);
    }

    @Override
    public void updateByAcc(TtsScltxxcjUser ttsScltxxcjUser) {
        TtsScltxxcjUserExpandMapper.updateUserByAcc(ttsScltxxcjUser);
    }

    @Override
    public void batchEditDelFlag(String[] ids) {
        TtsScltxxcjUserExpandMapper.batchUpdateDelFlag(ids);
    }

    @Override
    public TtsScltxxcjUser selectUserById(String id) {
        return TtsScltxxcjUserExpandMapper.selectUserById(id);
    }

    @Override
    public void updateUserInfoById(TtsScltxxcjUser ttsScltxxcjUser) {
        TtsScltxxcjUserExpandMapper.updateUserInfoById(ttsScltxxcjUser);
    }

    @Override
    public TtsScltxxcjUser findUserByIdCard(Map<String, Object> params) {
        return TtsScltxxcjUserExpandMapper.findUserByIdCard(params);
    }

    @Override
    public void updateUserInfo(TtsScltxxcjUser ttsScltxxcjUser) {
        TtsScltxxcjUserExpandMapper.updateUserInfo(ttsScltxxcjUser);
    }

    @Override
    public int insertUser(TtsScltxxcjUser ttsScltxxcjUser) {
        return TtsScltxxcjUserMapper.insert(ttsScltxxcjUser);
    }

    public List<TtsScltxxcjUser> queryUsers( Map<String, Object> queryParams){
        return TtsScltxxcjUserExpandMapper.queryUsers(queryParams);
    }

}
