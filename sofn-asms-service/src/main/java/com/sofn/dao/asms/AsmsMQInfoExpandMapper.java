package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.asms.MQInfo;

import java.util.List;

/**
 * describe:
 *
 * @author xuquan
 * @date 2018/02/28
 */
@MyBatisDao
public interface AsmsMQInfoExpandMapper extends BaseMapper<MQInfo> {

    /**
     * 根据接收标记获取列表
     *
     * @param flag
     * @return
     */
    List<MQInfo> selectByRecevieFlag(String flag);


    /**
     * 根据消费者查询
     *
     * @param consumer
     * @return
     */
    List<MQInfo> queryByConsumer(String consumer);


    /**
     * 更新接收信息标记
     *
     * @param consumer
     */
    void updateReceiveFlag(String consumer);
}
