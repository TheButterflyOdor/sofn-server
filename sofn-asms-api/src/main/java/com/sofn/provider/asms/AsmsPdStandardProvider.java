package com.sofn.provider.asms;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsPdStandard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AsmsPdStandardProvider extends BaseProvider<AsmsPdStandard> {

    /**
     * 自定义获得列表
     * */
    List<AsmsPdStandard> getListByParams(Map<String, Object> params);

    /**
     * 自定义物理删除
     * taskId
     * 任务id
     * */
    @Transactional
    boolean delByParm(Map<String, Object> params);
}
