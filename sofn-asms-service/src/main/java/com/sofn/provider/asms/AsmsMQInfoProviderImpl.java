package com.sofn.provider.asms;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsMQInfoExpandMapper;
import com.sofn.model.asms.MQInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;


/**
 * describe:
 *
 * @author xuquan
 * @date 2018/02/28
 */
@DubboService(interfaceClass = AsmsMQInfoProvider.class)
@CacheConfig(cacheNames = "AsmsMQInfo")
public class AsmsMQInfoProviderImpl implements AsmsMQInfoProvider {

    @Autowired
    private AsmsMQInfoExpandMapper mapper;


    @Override
    public void insertMessage(MQInfo info) {
        mapper.insert(info);
    }

    /**
     * 获取未接收的消息队列记录
     *
     * @return
     */
    @Override
    public List<MQInfo> getListForNotReceived() {
        return mapper.selectByRecevieFlag("N");
    }

    /**
     * 根据消费者模糊查询
     *
     * @param consumer
     * @return
     */
    @Override
    public List<MQInfo> getForConsumer(String consumer) {
        List<MQInfo> infos = mapper.queryByConsumer(consumer);
        if (infos != null && infos.size() > 0) {
            for (int i = 0; i < infos.size(); i++) {
                mapper.updateReceiveFlag(consumer);
            }
        }
        return infos;
    }
}
