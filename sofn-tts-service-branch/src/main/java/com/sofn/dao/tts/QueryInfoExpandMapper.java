package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@MyBatisDao
public interface QueryInfoExpandMapper extends BaseExpandMapper {

    /**
     * 根据追溯码获取当前批次码
     *
     * @param code
     * @return
     */
    String selectBatchCodeByTraceCode(@Param("traceCode") String code);

    /**
     * 查询当前批次合成记录数
     *
     * @param code
     * @return
     */
    int countHcNumByBatchCode(@Param("batchCode") String code);

    /**
     * 获取当前批次信息
     *
     * @param code
     * @return
     */
    Map<String, Object> selectInfoByBatchCode(@Param("batchCode") String code);

    /**
     * 查询当前批次的上游批次
     *
     * @param code
     * @return
     */
    List<String> selectParentBatchCodeByBatchCode(@Param("batchCode") String code);

}
