package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsRecheckObject;

import java.util.List;
import java.util.Map;

/**
 * Mapper扩展
 *
 * @author LiBing
 * @version 2016年8月29日 下午3:4:0
 */
@MyBatisDao
public interface AsmsRecheckObjectExpandMapper extends BaseExpandMapper {

    List<AsmsRecheckObject> getPagesList(Map<String, Object> map);

    Long getPageCount(Map<String, Object> params);

    void delByTaskId(AsmsRecheckObject t);

    List<AsmsRecheckObject> getObjsByTaskId(String id);

}