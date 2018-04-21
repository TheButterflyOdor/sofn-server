package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesDailyEnforceLaw;
import com.sofn.model.qry.AlesDailyEnforceLawDto;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/20.
 */
public interface DailyEnforceLawProvider extends BaseProvider<AlesDailyEnforceLaw> {

    PageInfo<AlesDailyEnforceLaw> getDailyEnforceLawList(Map<String, Object> map);

    AlesDailyEnforceLaw findDailyEnforceLawById(String id);

    int findByName(String taskName);

    //根据查询条件查询日常执法列表
    List<AlesDailyEnforceLawDto> getAllDailyEnforceLawList(Map<String,Object> map);

}
