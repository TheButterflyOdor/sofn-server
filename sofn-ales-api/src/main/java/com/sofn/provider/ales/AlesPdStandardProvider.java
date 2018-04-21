package com.sofn.provider.ales;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesPdStandard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AlesPdStandardProvider extends BaseProvider<AlesPdStandard> {

    /**
     * 自定义获得列表
     */
    List<AlesPdStandard> getListByParams(Map<String, Object> params);

    /**
     * 自定义物理删除
     */
    @Transactional
    boolean delByParm(Map<String, Object> params);
}
