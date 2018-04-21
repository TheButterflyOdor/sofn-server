package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.DailyEnforceLawExpandMapper;
import com.sofn.dao.generator.AlesDailyEnforceLawMapper;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdong
 * @version 2016年09月20日 下午 4:35
 */
@DubboService(interfaceClass = DailyEnforceLawProvider.class)
public class DailyEnforceLawProviderImpl extends BaseProviderImpl<AlesDailyEnforceLaw> implements DailyEnforceLawProvider {
    @Autowired
    private AlesDailyEnforceLawMapper dailyEnforceLawMapper;
    @Autowired
    private DailyEnforceLawExpandMapper AlesDailyEnforceLawExpandMapper;

    /**
     *
     * @param dailyEnforceLaw
     * @return
     */
    @Override
    public int addDailyEnforceLaw(AlesDailyEnforceLaw dailyEnforceLaw) {
        return dailyEnforceLawMapper.insert(dailyEnforceLaw);
    }

    /**
     *
     * @param map
     * @return
     */
    @Override
    public PageInfo<AlesDailyEnforceLaw> getDailyEnforceLawList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = AlesDailyEnforceLawExpandMapper.selectAllByCondition(map);
        long count = AlesDailyEnforceLawExpandMapper.getDailyEnforceLawCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    //    @Override
//    public long getDailyEnforceLawCount(Map<String, Object> map);{
//
//        return null;
//    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public AlesDailyEnforceLaw findDailyEnforceLawById(String id) {
        return dailyEnforceLawMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @param dailyEnforceLaw
     * @return
     */
    @Override
    public int updateDailyEnforceLaw(AlesDailyEnforceLaw dailyEnforceLaw) {

        return dailyEnforceLawMapper.updateByPrimaryKey(dailyEnforceLaw);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int deleteDailyEnforceLaw(String id) {
        return 0;
    }

    /**
     *
     * @param taskName
     * @return
     */
    @Override
    public int findByName(String taskName) {
        return AlesDailyEnforceLawExpandMapper.getTaskNameCount(taskName);
    }


}
