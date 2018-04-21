package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesProduceAdminPunish;
import com.sofn.model.qry.AlesProduceAdminPunishDto;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdong
 * @version 2016年09月21日 上午 11:32
 */
public interface ProduceAdminPunishProvider extends BaseProvider<AlesProduceAdminPunish> {

    public PageInfo getAisProduceAdminPunishList(Map<String, Object> map);

    public AlesProduceAdminPunish findProduceAdminPunishById(String id);

    public AlesProduceAdminPunish findPunishById(String id);

    //所有行政处罚数据列表（不分页）
    List<AlesProduceAdminPunishDto> getAllProduceAdminPunishList(Map<String,Object> map);

}
