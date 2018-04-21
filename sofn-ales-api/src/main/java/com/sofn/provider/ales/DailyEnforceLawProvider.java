package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesDailyEnforceLaw;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/20.
 */
public interface DailyEnforceLawProvider extends BaseProvider<AlesDailyEnforceLaw> {
    /**
     * 添加现场巡查列表
     * @param dailyEnforceLaw
     * @return
     */
    public int addDailyEnforceLaw(AlesDailyEnforceLaw dailyEnforceLaw);

    /**
     * 查询现场巡查列表
     * @param map
     * @return
     */
    public PageInfo<AlesDailyEnforceLaw> getDailyEnforceLawList(Map<String, Object> map);

    /**
     * 根据id查询对应的现场巡查数据
     * @param id
     * @return
     */
    public AlesDailyEnforceLaw findDailyEnforceLawById(String id);

    /**
     * 修改现场巡查
     * @param dailyEnforceLaw
     * @return
     */
    public int updateDailyEnforceLaw(AlesDailyEnforceLaw dailyEnforceLaw);

    /**
     * 删除现场巡查------暂时不开放此功能
     * @param id
     * @return
     */
    public int deleteDailyEnforceLaw(String id);

    /**
     * 根据任务名称查询是否含有此任务
     * @param taskName
     * @return
     */
    public int findByName(String taskName);

}
