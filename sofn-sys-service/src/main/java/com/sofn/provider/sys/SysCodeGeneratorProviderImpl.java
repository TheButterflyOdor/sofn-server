package com.sofn.provider.sys;

import com.sofn.core.oid.IdGenerator.*;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.WebUtil;
import com.sofn.dao.generator.SysCodeConvertMapper;
import com.sofn.dao.generator.SysCodeRecordMapper;
import com.sofn.dao.sys.SysCodeConvertExpandMapper;
import com.sofn.dao.sys.SysCodeRecordExpandMapper;
import com.sofn.model.generator.SysCodeConvert;
import com.sofn.model.generator.SysCodeRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@DubboService(interfaceClass = SysCodeGeneratorProvider.class)
public class SysCodeGeneratorProviderImpl implements SysCodeGeneratorProvider {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SysCodeGeneratorProviderImpl.class);

    @Autowired
    private SysCodeRecordExpandMapper sysCodeRecordExpandMapper;
    @Autowired
    private SysCodeRecordMapper sysCodeRecordMapper;
    @Autowired
    private SysCodeConvertMapper sysCodeConvertMapper;
    @Autowired
    private SysCodeConvertExpandMapper sysCodeConvertExpandMapper;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private final String key = "TracingCodeLong:"; // redis key值前缀

    /**
     * 左补位函数
     *
     * @param old    需要补位的字符串
     * @param length 补位后字符串长度
     * @param c      补位填充的字符
     * @return 补位后的字符串
     */
    private String leftFillSeats(String old, int length, char c) {
        return leftFillSeats(old, length, String.valueOf(c));
    }

    /**
     * 左补位函数
     *
     * @param old    需要补位的字符串
     * @param length 补位后字符串长度
     * @param c      补位填充的字符
     * @return 补位后的字符串
     */
    private String leftFillSeats(String old, int length, String c) {
        if (old.length() == length) {
            return old;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length - old.length(); i++)
                sb.append(c);
            sb.append(old);
            return sb.toString();
        }
    }

    /**
     * 获取主体身份码
     *
     * @param mainBodyCategories 追加生产经营类别代码
     * @param creditCode         社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * @return 主体身份码
     */
    public String getMainBodyIdentityCode(MainBodyCategories mainBodyCategories, String creditCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(mainBodyCategories.value);
        //追加社会信用代码
        creditCode = leftFillSeats(creditCode, 18, '0');
        sb.append('.');
        sb.append(creditCode);
        //追加主体号
        sb.append('.');
        sb.append("00000");
        SysCodeConvert codeConvert = new SysCodeConvert();
        codeConvert.setCodeLong(sb.toString());
        return codeConvert.getCodeLong();
    }

    /**
     * 获取主体用户码
     *
     * @param mainBodyCategories 生产经营类别代码
     * @param creditCode         社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * @return 主体用户码
     */
    public String getMainBodyUserCode(MainBodyCategories mainBodyCategories, String creditCode, String token) {
        SysCodeRecord sysCodeRecord = new SysCodeRecord();
        sysCodeRecord.setMangeType(mainBodyCategories.value + "");
        sysCodeRecord.setCreditCode(creditCode);
        sysCodeRecord.setCodeType(EncodingProperties.MainBodyIdentityCode.value + "");
        int maxCode = sysCodeRecordExpandMapper.getMaxOrderCode(sysCodeRecord);
        sysCodeRecord.setOrderCode(maxCode + 1);
        String orderCode = leftFillSeats(maxCode + "", 5, '0');

        StringBuilder sb = new StringBuilder();
        sb.append(mainBodyCategories.value);
        //追加社会信用代码
        creditCode = leftFillSeats(creditCode, 18, '0');
        sb.append('.');
        sb.append(creditCode);
        //追加用户顺序码 从00001开始
        sb.append('.');
        sb.append(orderCode);
        // 记录顺序码
        if (maxCode == 0) { // 如果之前没有记录
            insertRecord(sysCodeRecord, token);
        } else { // 如果已经有记录则更新顺序码
            sysCodeRecordExpandMapper.updateMaxOrderCode(sysCodeRecord);
        }
        SysCodeConvert codeConvert = new SysCodeConvert();
        codeConvert.setCodeLong(sb.toString());
        return codeConvert.getCodeLong();

    }

    /**
     * 获取产品追溯码
     *
     * @param tracingCodeType         产品追溯类型代码（1位 —— 1：产品生产批次码；2：产品流通批次码；3：产品入市批次码）
     * @param mainBodyCategories      生产经营类别代码（1位 —— 0：生产型主体；1：经营型主体；2：生产经营型主体）
     * @param creditCode              社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * @param agriculturalProductCode 农产品分类码
     * @return 获取产品追溯码
     */
    public SysCodeConvert getProductBatchCode(TracingCodeType tracingCodeType, MainBodyCategories mainBodyCategories, String creditCode,
                                              String agriculturalProductCode) {
        StringBuilder sb = new StringBuilder();
        //追加产品追溯类型代码
        sb.append(tracingCodeType.value);
        //追加生产经营类别代码
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加社会信用代码
        creditCode = leftFillSeats(creditCode, 18, '0');
        sb.append('.');
        sb.append(creditCode);
        //追加农产品分类码
        sb.append('.');
        sb.append(agriculturalProductCode);
        //追加时间戳
        sb.append('.');
        sb.append(sdf.format(new Date()));
        //追加顺序码
        SysCodeConvert codeConvert = new SysCodeConvert();
        String codeLong = codeLongGenerator(sb);
        codeConvert.setCodeLong(codeLong);
        // 生成短码
        codeConvert.setCodeShort(codeShortGenerator(tracingCodeType));
        insertCodeConvert(codeConvert);
        return codeConvert;
    }

    /**
     * 获取产品追溯码
     * map key:
     * encodingProperties  生成追溯码类型
     * tracingCodeType     产品追溯类型代码
     * mainBodyCategories  生产经营类别代码
     * creditCode         社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * agriculturalProductCode      产品分类代码
     *
     * @return 获取产品追溯码
     */
    public List<SysCodeConvert> getProductBatchCode(List<Map<String, Object>> params) {
        List<SysCodeConvert> codeList = new ArrayList<>();
        for (Map<String, Object> param : params) {
            TracingCodeType tracingCodeType = (TracingCodeType) param.get("tracingCodeType");
            MainBodyCategories mainBodyCategories = (MainBodyCategories) param.get("mainBodyCategories");
            String creditCode = param.get("creditCode").toString();
            String agriculturalProductCode = param.get("agriculturalProductCode").toString();

            codeList.add(getProductBatchCode(tracingCodeType, mainBodyCategories, creditCode, agriculturalProductCode));
        }
        return codeList;
    }

    @Override
    public SysCodeConvert queryCode(String codeLong, String codeShort) {
        if (StringUtils.isNotBlank(codeLong) || StringUtils.isNotBlank(codeShort)) {
            SysCodeConvert query = new SysCodeConvert();
            query.setCodeLong(codeLong);
            query.setCodeShort(codeShort);
            if (StringUtils.isNotBlank(codeLong) && StringUtils.isNotBlank(codeShort)) {
                // 如果都不为空
                return query;
            }
            return sysCodeConvertExpandMapper.queryCode(query);
        } else {
            return null;
        }
    }

    public SysCodeRecord insertRecord(SysCodeRecord sysCodeRecord, String token) {
//        String uid = WebUtil.getCurrentUserId();
        String uid = WebUtil.getCurrentUserId(token);
        sysCodeRecord.setDelFlag("N");
        sysCodeRecord.setCreateBy(uid == null ? "" : uid);
        sysCodeRecord.setUpdateBy(uid == null ? "" : uid);
        sysCodeRecord.setId(UUID.randomUUID().toString().replace("-", ""));
        sysCodeRecordMapper.insert(sysCodeRecord);
        return sysCodeRecord;
    }

    public SysCodeConvert insertCodeConvert(SysCodeConvert sysCodeConvert) {
        sysCodeConvert.setId(UUID.randomUUID().toString().replace("-", ""));
        sysCodeConvert.setCreateTime(new Date());
        try {
            sysCodeConvertMapper.insert(sysCodeConvert);
        } catch (DuplicateKeyException e) {
            String codeShort = sysCodeConvert.getCodeShort().substring(0, 9);
            String ran = sysCodeConvert.getCodeShort().substring(9);
            long ranInt = Long.valueOf(ran);
            // 如果短码生成重复 循环 随机数++ 再生成
            while (true) {
                ranInt++;
                ran = leftFillSeats(ranInt + "", 11, '0');
                sysCodeConvert.setCodeShort(codeShort + ran);
                try {
                    sysCodeConvertMapper.insert(sysCodeConvert);
                    break;
                } catch (DuplicateKeyException e1) {
                    LOG.error(sysCodeConvert.getCodeLong() + "生成短码重复", e1);
                }
            }

        }
        return sysCodeConvert;
    }

    public String codeShortGenerator(TracingCodeType tracingCodeType) {
        StringBuilder sb = new StringBuilder();
        sb.append(tracingCodeType.value);
        Calendar calendar = Calendar.getInstance();
        String timeStr = calendar.get(Calendar.DAY_OF_YEAR) + getTime(calendar);
        sb.append(timeStr);
        Random random1 = new Random();
        Random random2 = new Random();
        Random random3 = new Random();

        int randomNumber1 = random1.nextInt(999) % (1000);
        int randomNumber2 = random2.nextInt(9999) % (10000);
        int randomNumber3 = random3.nextInt(9999) % (10000);

        String ran1 = leftFillSeats(randomNumber1 + "", 3, randomNumber1 % 10 + "");
        String ran2 = leftFillSeats(randomNumber2 + "", 4, randomNumber2 % 10 + "");
        String ran3 = leftFillSeats(randomNumber3 + "", 4, randomNumber3 % 10 + "");

        sb.append(ran1);
        sb.append(ran2);
        sb.append(ran3);
        return sb.toString();
    }

    /***
     * 获取追溯码的顺序号
     * @param code
     * @return
     */
    public String codeLongGenerator(StringBuilder code) {
        // 将追溯码作为 redis的key值 获取自增的顺序号
        long num = RedisUtil.incr(key + code);
        RedisUtil.expire(key + code, 300);
        code.append(".");
        // 将追溯码+顺序号 返回
        code.append(leftFillSeats(num + "", 3, '0'));
        return code.toString();
    }

    public static String getTime(Calendar calendar) {
        String time = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) + calendar.get(Calendar.SECOND) + "";
        return time;
    }
}
