package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesProduceAdminPunish;

import java.util.Map;

/**
 * @author zhangdong
 * @version 2016年09月21日 上午 11:32
 */
public interface ProduceAdminPunishProvider extends BaseProvider<AlesProduceAdminPunish> {
    /**
     * 添加行政处罚
     * @param produceAdminPunish
     * @return
     */
    int addProduceAdminPunish(AlesProduceAdminPunish produceAdminPunish);

    /**
     * 修改行政处罚
     * @param produceAdminPunish
     * @return
     */
    int updateProduceAdminPunish(AlesProduceAdminPunish produceAdminPunish);

    /**
     * 查询行政处罚列表
     * @param map
     * @return
     */
    PageInfo getAisProduceAdminPunishList(Map<String, Object> map);

    /**
     * 根据id查询行政处罚
     * @param id
     * @return
     */
    AlesProduceAdminPunish findProduceAdminPunishById(String id);

    /**
     * 此接口已经废除
     * @param id
     * @return
     */
    AlesProduceAdminPunish findPunishById(String id);

    /**
     * 根据id删除对应的行政处罚信息
     * @param id
     */
    void detleteInfo(String id);

}
