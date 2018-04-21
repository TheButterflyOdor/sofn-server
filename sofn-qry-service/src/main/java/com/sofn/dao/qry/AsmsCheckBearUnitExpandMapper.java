package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsCheckBearUnit;

import java.util.List;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsCheckBearUnitExpandMapper extends BaseExpandMapper {

  public void delByTaskId(AsmsCheckBearUnit t);

  /**
   * 根据任务id获取检测单位列表
   * */
  List<AsmsCheckBearUnit> getUnitByTaskId(AsmsCheckBearUnit t);

}