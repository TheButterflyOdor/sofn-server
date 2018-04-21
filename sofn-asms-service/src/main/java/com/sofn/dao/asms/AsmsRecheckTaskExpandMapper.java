package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsRecheckTask;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsRecheckTaskExpandMapper extends BaseExpandMapper {

    List<AsmsRecheckTask> getPagesList(Map<String, Object> map);

    Long getPageCount(Map<String, Object> params);
}