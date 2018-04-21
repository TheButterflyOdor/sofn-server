package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.DailyEnforceLawExpandMapper;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import com.sofn.model.qry.AlesDailyEnforceLawDto;
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
    private DailyEnforceLawExpandMapper AlesDailyEnforceLawExpandMapper;

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
    @Override
    public AlesDailyEnforceLaw findDailyEnforceLawById(String id) {
        return AlesDailyEnforceLawExpandMapper.selectAlesDailyEnforceLawByPrimaryKey(id);
    }

    @Override
    public int findByName(String taskName) {
        return AlesDailyEnforceLawExpandMapper.getTaskNameCount(taskName);
    }

    @Override
    public List<AlesDailyEnforceLawDto> getAllDailyEnforceLawList(Map<String, Object> map) {
        return AlesDailyEnforceLawExpandMapper.getAllDailyEnforceLawList(map);
    }


}
