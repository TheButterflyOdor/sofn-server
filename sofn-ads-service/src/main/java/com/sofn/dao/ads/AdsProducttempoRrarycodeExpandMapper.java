package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.AdsProducttempoRrarycode;

import java.util.List;
import java.util.Map;

/**
 * 产品临时码Mapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsProducttempoRrarycodeExpandMapper extends BaseExpandMapper {
    /**
     * 获取详情
     * @param id
     * @return
     */
    AdsProducttempoRrarycode findById(String id);

    /**
     *  获取产品临时码列表
     */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);


    /**
     *  获取产品临时码数据条数
     */
    long getCount(Map<String, Object> map);
}