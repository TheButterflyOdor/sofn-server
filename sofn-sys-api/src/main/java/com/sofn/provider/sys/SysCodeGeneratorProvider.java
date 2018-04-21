/**
 *
 */
package com.sofn.provider.sys;

import com.sofn.core.oid.IdGenerator.MainBodyCategories;
import com.sofn.core.oid.IdGenerator.TracingCodeType;
import com.sofn.model.generator.SysCodeConvert;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
public interface SysCodeGeneratorProvider {
    /**
     * 获取主体身份码
     *
     * @param mainBodyCategories 追加生产经营类别代码（枚举）
     * @param creditCode         社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * @return 主体身份码
     */
    String getMainBodyIdentityCode(MainBodyCategories mainBodyCategories, String creditCode);

    /**
     * 获取主体用户码
     *
     * @param mainBodyCategories 追加生产经营类别代码（枚举）
     * @param creditCode         社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * @return 主体用户码
     */
    String getMainBodyUserCode(MainBodyCategories mainBodyCategories, String creditCode,String token);

    /**
     * 获取产品追溯码
     * @param tracingCodeType     产品追溯类型代码（枚举）
     * @param mainBodyCategories  生产经营类别代码（枚举）
     * @param creditCode          社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * @param agriculturalProductCode 农产品分类码
     * @return 产品追溯码
     */
    SysCodeConvert getProductBatchCode(TracingCodeType tracingCodeType, MainBodyCategories mainBodyCategories, String creditCode,
                               String agriculturalProductCode);

    /**
     * 获取产品追溯码
     * parms key:
     * tracingCodeType     产品追溯类型代码（枚举）
     * mainBodyCategories  生产经营类别代码（枚举）
     * creditCode         社会信用代码(18位) or　营业执照号码(15位) or 自然人身份证号(18位)
     * projectCategoryCode      产品分类代码
     *
     * @return 产品追溯码
     */
    List<SysCodeConvert> getProductBatchCode(List<Map<String, Object>> parms);

    /**
     * 根据长码获取短码 根据短码获取长码
     * @param codeLong
     * @param codeShort
     * @return
     */
    SysCodeConvert queryCode(String codeLong,String codeShort) ;
}
