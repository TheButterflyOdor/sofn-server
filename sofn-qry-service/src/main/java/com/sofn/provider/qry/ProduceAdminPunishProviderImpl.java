package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.ProduceAdminPunishExpandMapper;
import com.sofn.model.generator.AlesProduceAdminPunish;
import com.sofn.model.qry.AlesProduceAdminPunishDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdong
 * @version 2016年09月21日 上午 11:35
 */
@DubboService(interfaceClass = ProduceAdminPunishProvider.class)
public class ProduceAdminPunishProviderImpl extends BaseProviderImpl<AlesProduceAdminPunish> implements ProduceAdminPunishProvider{
    @Autowired(required = false)
    private ProduceAdminPunishExpandMapper produceAdminPunishExpandMapper;

    @Override
    public PageInfo getAisProduceAdminPunishList(Map<String,Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = produceAdminPunishExpandMapper.getProduceAdminPunishAllList(map);
        long count = produceAdminPunishExpandMapper.getProduceAdminPunishAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    @Override
    public AlesProduceAdminPunish findProduceAdminPunishById(String id) {
        return null;
    }

    @Override
    public AlesProduceAdminPunish findPunishById(String id) {
        return produceAdminPunishExpandMapper.getPunishById(id);
    }

    @Override
    public List<AlesProduceAdminPunishDto> getAllProduceAdminPunishList(Map<String, Object> map) {
        return produceAdminPunishExpandMapper.getAllProduceAdminPunishList(map);
    }
}
