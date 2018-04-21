package com.sofn.service.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.exception.IllegalParameterException;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.FileManager;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.model.tts.SamplingInfo;
import com.sofn.provider.ads.AdsMonitorSampleProvider;
import com.sofn.provider.asms.AsmsSubjEntBadrecordProvider;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.tts.QueryInfoProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 监测抽样单 service 业务逻辑
 *
 * @author moon.l
 */
@Service
public class AdsMonitorSampleService extends BaseService<AdsMonitorSampleProvider, AdsMonitorSample> {

    @DubboReference
    public void setAdsMonitorSampleProvider(AdsMonitorSampleProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;

    @DubboReference
    private QueryInfoProvider queryInfoProvider;

    @DubboReference
    private AsmsSubjEntBadrecordProvider subjEntBadrecordProvider;

    @DubboReference
    private TtsScltxxcjRegiterProvider regiterProvider;

    private static final String CLIENT_CONFIG_FILE1 = "例行监测抽样单.doc";
    private static final String CLIENT_CONFIG_FILE2 = "专项监测抽样单.doc";

    public PageInfo getPageInfo(AdsMonitorSample bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("sampleReport", bean.getSampleReport());
        queryParams.put("checkReport", bean.getCheckReport());
        queryParams.put("sampleOrgan", bean.getSampleOrgan());
        queryParams.put("organTaskId", bean.getOrganTaskId());
        queryParams.put("productTraceabilityCode", bean.getProductTraceabilityCode());
        queryParams.put("producingArea", bean.getProducingArea());
        queryParams.put("cityCode", bean.getCityCode());
        queryParams.put("sampleDeparment", bean.getSampleDeparment());
        queryParams.put("testedDeparment", bean.getTestedDeparment());
        queryParams.put("sampleCode", bean.getSampleCode());
        queryParams.put("sampleName", bean.getSampleName());
        queryParams.put("sampleDate", bean.getSampleDate());
        queryParams.put("checkLink", bean.getCheckLink());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }

    public PageInfo getAuditSamplePageInfo(AdsMonitorSample bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId", bean.getOrganTaskId());
        queryParams.put("producingArea", bean.getProducingArea());
        queryParams.put("producingAreaName", bean.getProducingAreaName());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("sampleOrgan", bean.getSampleOrgan());
        queryParams.put("sampleCode", bean.getSampleCode());
        queryParams.put("sampleName", bean.getSampleName());
        queryParams.put("sampleDate", bean.getSampleDate());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getAuditSamplePageInfo(queryParams);
    }

    public PageInfo getRecheckSamplePageInfo(AdsMonitorSample bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId", bean.getOrganTaskId());
        queryParams.put("producingArea", bean.getProducingArea());
        queryParams.put("producingAreaName", bean.getProducingAreaName());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("sampleOrgan", bean.getSampleOrgan());
        queryParams.put("sampleCode", bean.getSampleCode());
        queryParams.put("sampleName", bean.getSampleName());
        queryParams.put("sampleDate", bean.getSampleDate());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getRecheckSamplePageInfo(queryParams);
    }

    public PageInfo pageInfocodeTypeByTaskId(String monitorTaskId, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", monitorTaskId);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.pageInfocodeTypeByTaskId(queryParams);
    }
    public PageInfo getEntrustSamplePageInfo(AdsMonitorSample bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId", bean.getOrganTaskId());
        queryParams.put("producingArea", bean.getProducingArea());
        queryParams.put("producingAreaName", bean.getProducingAreaName());
        queryParams.put("taskName", bean.getTaskName());
        queryParams.put("sampleOrgan", bean.getSampleOrgan());
        queryParams.put("sampleCode", bean.getSampleCode());
        queryParams.put("sampleName", bean.getSampleName());
        queryParams.put("sampleDate", bean.getSampleDate());
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getEntrustSamplePageInfo(queryParams);
    }

    public PageInfo getSamplePageInfo(AdsMonitorSample bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getSamplePageInfo(queryParams);
    }

    public int updateAdsMonitorSampleById(AdsMonitorSample adsMonitorSample) {
        return provider.updateAdsMonitorSampleById(adsMonitorSample);
    }

    public void updateAdsMonitorSample(AdsMonitorSample adsMonitorSample) {
         provider.updateAdsMonitorSample(adsMonitorSample);
    }
    public int selectCount(AdsMonitorSample adsMonitorSample) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId",adsMonitorSample.getOrganTaskId());
        return provider.selectCount(queryParams);
    }

    public Map<String, Object> findAdsMonitorSampleById(String sampleCode,String organTaskId) {
        AdsMonitorSample adsMonitorSample = new AdsMonitorSample();
        adsMonitorSample.setSampleCode(sampleCode);
        adsMonitorSample.setOrganTaskId(organTaskId);
        return provider.findAdsMonitorSampleById(adsMonitorSample);
    }

    public AdsMonitorSample findAdsMonitorSample(String sampleCode) {
        AdsMonitorSample adsMonitorSample = new AdsMonitorSample();
        adsMonitorSample.setSampleCode(sampleCode);
        return provider.findAdsMonitorSample(adsMonitorSample);
    }

    public void addAdsMonitorSample(AdsMonitorSample adsMonitorSample) {
        provider.addAdsMonitorSample(adsMonitorSample);

    }

    public List<AdsMonitorSample> findDepartment(String organTaskId) {
        return provider.findDepartment(organTaskId);
    }

    public void downloadSampleList(HttpServletResponse response, String monitorClass) {
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
            String routineMonitorClientConfigFilePath = classPath + File.separator + "doc" + File.separator + CLIENT_CONFIG_FILE1;
            String specialMonitorClientConfigFilePath = classPath + File.separator + "doc" + File.separator + CLIENT_CONFIG_FILE2;
            if (monitorClass.equals("例行监测")) {
                inputStream = new FileInputStream(new File(routineMonitorClientConfigFilePath));
                response.setHeader("Content-Disposition", "attachment;fileName="
                        + "例行监测抽样单.doc");
            } else if (monitorClass.equals("专项监测")) {
                inputStream = new FileInputStream(new File(specialMonitorClientConfigFilePath));
                response.setHeader("Content-Disposition", "attachment;fileName="
                        + "专项监测抽样单.doc");
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Map<Object,Object> getMsgByProductTraceabilityCode(String productTraceabilityCode) {
        return null;
    }

    public List<AdsMonitorSample> queryByOrgTaskId(String orgTaskId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId",orgTaskId);
        return provider.queryByOrgTaskId(queryParams);
    }
    public PageInfo getMappingModel(String monitorTaskId, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", monitorTaskId);
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getMappingModel(queryParams);
    }
    public List<AdsMonitorSample> queryListByOrgTaskId(String orgTaskId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("organTaskId",orgTaskId);
        return provider.queryListByOrgTaskId(queryParams);
    }

    public Map<String,Object> getTestingOrganizationInfo(String organizationName) {
        return provider.getTestingOrganizationInfo(organizationName);
    }



    public void updateInfo(AdsMonitorSample adsMonitorSample,AdsCheckInfo adsCheckInfo) {
        try {
            if("0".equals(adsCheckInfo.getResult())){
                String traceCode = adsMonitorSample.getProductTraceabilityCode();
                String productTraceabilityCode = adsMonitorSample.getProductTraceabilityCode();
                if(productTraceabilityCode.indexOf(".") == -1) {//短码
                    SysCodeConvert sysCodeConvert = sysCodeGeneratorProvider.queryCode(null, productTraceabilityCode);
                    if (sysCodeConvert == null) {
                        traceCode = null;
                    }else{
                        traceCode = sysCodeConvert.getCodeLong();
                    }
                }
                if(StringUtils.isEmpty(traceCode)){
                    return;
                }
                IdGenerator.TracingCodeType codeType = null;
                codeType = IdGenerator.getTracingCodeType(traceCode);
                if (codeType == null) {
                    return;
                }
                SamplingInfo samplingInfo = null;//抽样信息
                if (IdGenerator.TracingCodeType.production.equals(codeType)) {
                    samplingInfo = queryInfoProvider.getSamplingInfoByBatchCode(traceCode);
                } else if (IdGenerator.TracingCodeType.circulate.equals(codeType)) {
                    samplingInfo = queryInfoProvider.getSamplingInfoByTraceCode(traceCode);
                } else if (IdGenerator.TracingCodeType.enterMarket.equals(codeType)) {
                    samplingInfo = queryInfoProvider.getSamplingInfoByTraceProof(traceCode);
                }
                if(null != samplingInfo){
                    String entityIdCode = samplingInfo.getEntityIdCode();
                    Map<String,Object> params = new HashMap<String,Object>();
                    params.put("entityIdCode",entityIdCode);
                    TtsScltxxcjRegiter ttsScltxxcjRegiter = regiterProvider.selectByEntityIdCode(params);
                    //同步检测结果为不合格的给asms
                    String uuid = UUID.randomUUID().toString();
                    uuid = uuid.replace("-", "");
                    AsmsSubjEntBadrecord subjEntBadrecord = new AsmsSubjEntBadrecord();
                    subjEntBadrecord.setId(uuid);
                    subjEntBadrecord.setDelFlag("N");
                    subjEntBadrecord.setEnterpriseId(ttsScltxxcjRegiter.getId());//主体id
                    subjEntBadrecord.setBadrecordContent("");//不良记录描述
                    subjEntBadrecord.setCreateBy(WebUtil.getCurrentUserId());//创建者id
                    subjEntBadrecord.setCreateTime(new Date());//创建时间
                    subjEntBadrecord.setSourceId(adsMonitorSample.getId());//不良记录来源id
                    subjEntBadrecord.setSourceType("3");//检测不合格为3
                    subjEntBadrecord.setBadrecordFile("");//附件,多个以*##*分割
                    subjEntBadrecord.setBadrecordFileName("");//附件名称,多个以*##*分割
                    subjEntBadrecordProvider.addSubjEntBadrecord(subjEntBadrecord);//调用接口添加
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}