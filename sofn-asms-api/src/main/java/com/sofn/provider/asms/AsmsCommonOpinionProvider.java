package com.sofn.provider.asms;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsCommonOpinion;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface AsmsCommonOpinionProvider extends BaseProvider<AsmsCommonOpinion> {
    List<AsmsCommonOpinion> getOpinionListByUserId(String userId);

    int addAsmsCommonOpinion(AsmsCommonOpinion asmsCommonOpinion);

    int updateAsmsCommonOpinion(AsmsCommonOpinion asmsCommonOpinion);
}
