package com.sofn.provider.ales;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AlesWtTaskEnterprise;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AlesWtTaskEnterpriseProvider extends BaseProvider<AlesWtTaskEnterprise> {

    /**
     * 自定义获得列表
     * checkTaskObjectId
     * 检测对象id
     * enterpriseName
     * 企业名称
     * entityType
     * 企业类型
     */
    List<AlesWtTaskEnterprise> getListByParams(Map<String, Object> params);

    /**
     * 自定义获得单个受检单位
     * @param params
     * @return
     */
    AlesWtTaskEnterprise geEntByParams(Map<String, Object> params);

    /**
     * 自定义物理删除
     * checkTaskObjectId
     * 检测对象id
     */
    @Transactional
    boolean delByParm(Map<String, Object> params);
}
