package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.tts.TtsScltxxcjUserExpandMapper;
import com.sofn.model.generator.TtsScltxxcjUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	企业用户及子账户信息表 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjUserProvider.class)
public class TtsScltxxcjUserProviderImpl extends BaseProviderImpl<TtsScltxxcjUser> implements TtsScltxxcjUserProvider {

    @Autowired
    private TtsScltxxcjUserExpandMapper TtsScltxxcjUserExpandMapper;

    public PageInfo<TtsScltxxcjUser> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsScltxxcjUserExpandMapper.getPageInfo(map);
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
    public TtsScltxxcjUser queryUserByAccount(Map<String, Object> queryParams) {

        return TtsScltxxcjUserExpandMapper.getUserByAccount(queryParams);
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
        TtsScltxxcjUserExpandMapper.updateUserByAccount(ttsScltxxcjUser);
    }

}
