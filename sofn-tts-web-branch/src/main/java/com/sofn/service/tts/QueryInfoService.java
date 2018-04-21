package com.sofn.service.tts;

import com.sofn.core.exception.DataParseException;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.tts.SamplingInfo;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.tts.QueryInfoProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
@Service
public class QueryInfoService {
    @DubboReference
    public SysCodeGeneratorProvider sysCodeGeneratorProvider;
    @DubboReference
    private QueryInfoProvider queryInfoProvider;

    /**
     * 根据传入的code和用户类型查询相关信息
     *
     * @param userType
     * @param code
     * @return
     */
    public List<Map<String, Object>> getInfoTest(String userType, String code) {
        return queryInfoProvider.queryInfoByCode(userType, code);
    }

    /**
     * 根据追溯码获取抽样信息
     *
     * @param code
     * @return
     */
    public SamplingInfo getSamplingInfoByTraceCode(String code) {
        return queryInfoProvider.getSamplingInfoByTraceCode(code);
    }

    /**
     * 根据批次码获取抽样信息
     *
     * @param code
     * @return
     */
    public SamplingInfo getSamplingInfoByBatchCode(String code) {
        return queryInfoProvider.getSamplingInfoByBatchCode(code);
    }

    /**
     * 根据入市追溯凭证获取抽样信息
     *
     * @param code
     * @return
     */
    public SamplingInfo getSamplingInfoByTraceProof(String code) {
        return queryInfoProvider.getSamplingInfoByTraceProof(code);
    }

    /**
     * 根据追溯码获取信息
     *
     * @param code 追溯、批次短码
     * @return
     */
    public Map<String, Object> getInfoByTraceCode(String code) {
        try {
            SysCodeConvert sysCodeConvert = sysCodeGeneratorProvider.queryCode(null, code);
            if (sysCodeConvert == null) {
                return null;
            }
            return queryInfoProvider.getInfoByTraceCode(sysCodeConvert.getCodeLong());
        } catch (IllegalParameterException e) {
            throw new IllegalParameterException(e);
        } catch (DataParseException e) {
            throw new DataParseException(e);
        }
    }
}
