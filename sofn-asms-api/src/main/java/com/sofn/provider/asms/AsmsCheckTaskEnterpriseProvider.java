package com.sofn.provider.asms;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsCheckTaskEnterprise;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AsmsCheckTaskEnterpriseProvider extends BaseProvider<AsmsCheckTaskEnterprise> {

    /**
     * 自定义获得列表
     * checkTaskObjectId
     * 检测对象id
     * enterpriseName
     * 企业名称
     * entityType
     * 企业类型
     */
    List<AsmsCheckTaskEnterprise> getListByParams(Map<String, Object> params);

    /**
     * 自定义物理删除
     * checkTaskObjectId
     * 检测对象id
     */
    @Transactional
    boolean delByParm(Map<String, Object> params);
}
