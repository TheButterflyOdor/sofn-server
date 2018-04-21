package com.sofn.dao.tts;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.TtsScltxxcjProduct;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface TtsScltxxcjProductManageExpandMapper extends BaseExpandMapper {
    /**
     * 分页获取产品信息列表
     * @param params 分页参数
     * @return
     */
    public List<Map<String,Object>> findList(Map<String,Object> params);

    /**
     * 获取分页页总数
     * @param params 分页参数
     * @return
     */
    public Long getCount(Map<String,Object> params);
}