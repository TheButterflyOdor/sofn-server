package com.sofn.provider.qry;

import com.sofn.core.base.BaseProvider;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;

/**
 * 追溯查询
 */
public interface TraceQueryProvider extends BaseProvider {
    /**
     * 批次码/追溯码查询
     * type非1或2默认为2
     *
     * @param code 追溯或批次码
     * @param type 1树图2力向图
     * @return
     */
    Object selectBytraceOrBatchCode(String code, int type) throws IllegalParameterException, DataParseException;
}
