package com.sofn.service.qry;


import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.provider.qry.TraceQueryProvider;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import org.springframework.stereotype.Service;

/**
 * 追溯码查询入口
 * Created by Administrator on 2016/11/7.
 */
@Service
public class TraceQueryService extends BaseService {
    @DubboReference
    private TraceQueryProvider traceQueryProvider;
    @DubboReference
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;

    /**
     * 批次码/追溯码查询
     * type非1或2默认为2
     *
     * @param code 追溯或批次码
     * @param type 1树图2力向图
     * @return
     */
    public Object getBytraceOrBatchCode(String code, int type) {
        if (code.indexOf(".") == -1) {//短码
            SysCodeConvert sysCodeConvert = sysCodeGeneratorProvider.queryCode(null, code);
            if (sysCodeConvert == null) {
                return null;
            }
            code = sysCodeConvert.getCodeLong();
        }
        return traceQueryProvider.selectBytraceOrBatchCode(code, type);
    }
}
