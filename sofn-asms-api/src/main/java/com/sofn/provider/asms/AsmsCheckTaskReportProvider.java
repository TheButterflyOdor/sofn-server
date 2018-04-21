/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsCheckTaskReport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
public interface AsmsCheckTaskReportProvider extends BaseProvider<AsmsCheckTaskReport> {

    PageInfo<List<Map<String, Object>>> list(Map<String, Object> params);

    /**
     * 【监测系统 接口】
     * 任务报告录入[包含：监督抽查、例行监测、专项检测]
     * */
    @Transactional
    boolean addReport(AsmsCheckTaskReport report);
}
