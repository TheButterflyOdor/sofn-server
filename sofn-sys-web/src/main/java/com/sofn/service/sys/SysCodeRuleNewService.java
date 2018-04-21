package com.sofn.service.sys;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.base.BaseService;
import com.sofn.core.oid.IdGenerator.*;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.*;
import com.sofn.provider.sys.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@Service
public class SysCodeRuleNewService extends BaseService<SysCodeRuleNewProvider,SysCodeRuleNew> {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SysCodeRuleNewService.class);
    private SysCodeRuleFieldNewProvider sysCodeRuleFieldNewProvider;
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;

    @DubboReference
    public void setSysCodeRuleNewProvider(SysCodeRuleNewProvider sysCodeRuleNewProvider) {
        this.provider = sysCodeRuleNewProvider;
    }
    @DubboReference
    public void setSysCodeRuleFieldProvider(SysCodeRuleFieldNewProvider sysCodeRuleFieldNewProvider){
        this.sysCodeRuleFieldNewProvider = sysCodeRuleFieldNewProvider;
    }
    @DubboReference
    public void setSysCodeGeneratorProvider(SysCodeGeneratorProvider sysCodeGeneratorProvider) {
        this.sysCodeGeneratorProvider = sysCodeGeneratorProvider;
    }

    public int getRecordsTotal(){
        return provider.getRecordsTotal();
    }
    public List<SysCodeRuleNew> getPageList(Page page){
        return provider.getSysCodeList(page);
    }

    public SysCodeRuleNew getDemoImg(String ruleId){
        return provider.getDemoImg(ruleId);
    }

    public SysCodeRuleNew queryRuleAndField(String ruleId){
        return provider.queryRuleAndField(ruleId);
    }

    public SysCodeConvert test(int num,String token){
        SysCodeConvert sysCodeConvert=null;
        String code="";
        switch (num) {
            case 0: {
                code=sysCodeGeneratorProvider.getMainBodyIdentityCode(MainBodyCategories.MainBodyOfWokingAndManagement, "915101007130545948");
            }
            break;
            case 1: {
                code=sysCodeGeneratorProvider.getMainBodyUserCode(MainBodyCategories.MainBodyOfWokingAndManagement, "915101007130545948",token);
            }
            break;
            case 2: {
                sysCodeConvert=sysCodeGeneratorProvider.getProductBatchCode(TracingCodeType.production,MainBodyCategories.MainWorkingBody, "915101007130545948","00000001");
            }
            break;
            case 3: {
                sysCodeConvert=sysCodeGeneratorProvider.getProductBatchCode(TracingCodeType.circulate,MainBodyCategories.MainWorkingBody, "915101007130545948","00000001");
            }
            break;
            case 4: {
                sysCodeConvert=sysCodeGeneratorProvider.getProductBatchCode(TracingCodeType.enterMarket,MainBodyCategories.MainWorkingBody, "915101007130545948","00000001");
            }
            break;
            case 5: {
                sysCodeConvert=sysCodeGeneratorProvider.queryCode("1.2.156.326.8.2.2.852741963369852147.020103.20170506161716000","21265763383145901767");
            }
            break;

        }
        if(sysCodeConvert==null) {
            System.out.println("____________码:"+code);
            sysCodeConvert=new SysCodeConvert();
            sysCodeConvert.setCodeLong(code);
        } else {
            System.out.println("____________长码:"+sysCodeConvert.getCodeLong());
            System.out.println("____________短码:"+sysCodeConvert.getCodeShort());
        }
        return sysCodeConvert;

    }

    public SysCodeConvert test1(){
//        RedisUtil.set("test:cc:test",1,60);
        RedisUtil.del("test:cc:test");
        long i= RedisUtil.incr("test:cc:test");
        System.out.println(i);
        return null;
    }
}
