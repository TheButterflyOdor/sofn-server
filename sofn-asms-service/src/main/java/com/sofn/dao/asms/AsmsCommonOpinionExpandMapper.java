package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AsmsCommonOpinion;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@MyBatisDao
public interface AsmsCommonOpinionExpandMapper extends BaseExpandMapper {
    List<AsmsCommonOpinion> getOpinionListByUserId(String userId);
}
