package com.sofn.dao.ales;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AlesEntrustSample;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AlesEntrustSampleExpandMapper extends BaseExpandMapper {
  //更具任务id删除
  boolean delByTaskId(AlesEntrustSample t);
  //根据id查询
  List<Map<String, Object>> getObjById(String id);

}