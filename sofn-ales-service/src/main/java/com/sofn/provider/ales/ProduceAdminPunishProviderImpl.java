package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.ProduceAdminPunishExpandMapper;
import com.sofn.dao.generator.AlesProduceAdminPunishMapper;
import com.sofn.model.generator.AlesProduceAdminPunish;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdong
 * @version 2016年09月21日 上午 11:35
 */
@DubboService(interfaceClass = ProduceAdminPunishProvider.class)
public class ProduceAdminPunishProviderImpl extends BaseProviderImpl<AlesProduceAdminPunish> implements ProduceAdminPunishProvider {
    @Autowired
    private ProduceAdminPunishExpandMapper produceAdminPunishExpandMapper;
   /* @Autowired
    private  boolean s = false;*/
    @Autowired
    private AlesProduceAdminPunishMapper produceAdminPunishMapper;

    /**
     *
     * @param produceAdminPunish
     * @return
     */
    @Override
    public int addProduceAdminPunish(AlesProduceAdminPunish produceAdminPunish) {
        return produceAdminPunishMapper.insert(produceAdminPunish);
    }

    /**
     *
     * @param produceAdminPunish
     * @return
     */
    @Override
    public int updateProduceAdminPunish(AlesProduceAdminPunish produceAdminPunish) {
        return produceAdminPunishMapper.updateByPrimaryKey(produceAdminPunish);
    }

    /**
     *
     * @param map
     * @return
     */
    @Override
    public PageInfo getAisProduceAdminPunishList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        //返回数据
        List<Map<String, Object>> list = produceAdminPunishExpandMapper.getProduceAdminPunishAllList(map);
        //统计条数
        long count = produceAdminPunishExpandMapper.getProduceAdminPunishAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public AlesProduceAdminPunish findProduceAdminPunishById(String id) {
        return produceAdminPunishMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public AlesProduceAdminPunish findPunishById(String id) {
        return produceAdminPunishExpandMapper.getPunishById(id);
    }

    @Override
    public void detleteInfo(String id) {
        produceAdminPunishExpandMapper.deleteInfo(id);
    }

}
