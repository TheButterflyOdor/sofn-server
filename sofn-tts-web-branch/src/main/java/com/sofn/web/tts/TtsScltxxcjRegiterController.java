package com.sofn.web.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.service.tts.SSOLoginService;
import com.sofn.service.tts.SysDictionaryService;
import com.sofn.service.tts.TtsForgetPasswordService;
import com.sofn.service.tts.TtsScltxxcjRegiterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册主体信息表 controller 控制器实现
 * Created by moon.l
 */
@RestController
@Api(value = "注册主体信息表", description = "注册主体信息表")
@RequestMapping(value = "/ttsScltxxcjRegiter", method = RequestMethod.POST)
public class TtsScltxxcjRegiterController extends BaseController {

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;

    @Autowired
    private SSOLoginService ssoLoginService;

    @Autowired
    private SysDictionaryService sysDictionaryService;

    @Autowired
    private TtsForgetPasswordService ttsForgetPassword;

    /**
     * 根据条件获取注册主体信息表列表
     *
     * @param ttsScltxxcjRegiter
     * @return
     */
    @ApiOperation(value = "获取注册主体信息表信息列表")
    @RequestMapping(value = "/getPageInfo", method = RequestMethod.POST)
    @SystemControllerLog(description = "主体信息",operationType="查询")
    public Object getPageInfo(TtsScltxxcjRegiter ttsScltxxcjRegiter, int start, int length) {
        try {
            PageInfo<TtsScltxxcjRegiter> pageInfo = ttsScltxxcjRegiterService.getPageInfo(ttsScltxxcjRegiter, ((start + 1) / length) + 1, length);
            return setSuccessModelMap(new ModelMap(), pageInfo);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }
    @ApiOperation(value = "根据主体身份码查询二维码主体信息")
    @RequestMapping(value = "/getEntityInfos", method = RequestMethod.POST)
    @SystemControllerLog(description = "二维码信息",operationType="查询")
    public Object getEntityInfos(String sourceEntity) {
        try {
            TtsScltxxcjRegiter ttsScltxxcjRegiter = ttsScltxxcjRegiterService.queryEntityInfoByEntity(sourceEntity);
            return ttsScltxxcjRegiter;
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增注册主体信息表记录
     *
     * @param ttsScltxxcjRegiter
     * @return
     */
    @ApiOperation(value = "新增注册主体信息表数据")
    @SystemControllerLog(description = "注册主体",operationType="新增主体")
    @RequestMapping(value = "/addTtsScltxxcjRegiter", method = RequestMethod.POST)
    public Object addTtsScltxxcjRegiter(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        try {
            ttsScltxxcjRegiterService.add(ttsScltxxcjRegiter);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 根据ID获取单条注册主体信息表数据信息
     *
     * @param ttsScltxxcjRegiter
     * @return
     *
     */
    @ApiOperation(value = "获取单条注册主体信息表数据信息")
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体Id获取主体信息",operationType="查询主体信息")
    public Map<String, Object> queryById(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        ttsScltxxcjRegiter = ttsScltxxcjRegiterService.queryById(ttsScltxxcjRegiter.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        map.put("ttsScltxxcjRegiter", ttsScltxxcjRegiter);
        return map;
    }

    /**
     * 验证企业信息的唯一性
     *
     * @param uniqueKey
     * @return
     */
    @ApiOperation(value = "验证企业信息的唯一性")
    @RequestMapping(value = "/queryByCreditCode", method = RequestMethod.POST)
    @SystemControllerLog(description = "校验企业注册号是否注册过",operationType="校验查询")
    public Map<String, Object> queryByCreditCode(String uniqueKey,String account) {
        Map<String, Object> params = new HashMap<String, Object>();
        List<String> resutls = new ArrayList<String>();
        params.put("uniqueKey",uniqueKey);
        params.put("account",account);
        resutls = ttsScltxxcjRegiterService.queryByInfounique(params);
        Map<String, Object> map = new HashMap<String, Object>();
        if (resutls.size() > 0) {
            map.put("isExists", "Y");
        } else {
            map.put("isExists", "N");
        }
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    /**
     * 法人身份证号是否注册过
     *
     * @param idCode
     * @return
     */
    @ApiOperation(value = "法人身份证号是否注册过")
    @RequestMapping(value = "/queryByIdCode", method = RequestMethod.POST)
    @SystemControllerLog(description = "校验法人身份证号是否注册过",operationType="注册校验查询")
    public Map<String, Object> queryByIdCode(String idCode,String entityScale) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idCode", idCode);
        params.put("entityScale",entityScale);
        Map<String, Object> resutls = null;
        try {
            resutls = ttsScltxxcjRegiterService.queryByIdCode(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (Integer.parseInt(resutls.get("NUM").toString()) > 0) {
            map.put("isExists", "Y");
        } else {
            map.put("isExists", "N");
        }
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "验证用户是否存在")
    @RequestMapping(value = "/repeatCheck", method = RequestMethod.POST)
    @SystemControllerLog(description = "校验验证用户是否存在",operationType="用户号校验查询")
    public Object repeatCheck(ModelMap modelMap, @RequestBody String account) {
        Map<String, Object> resultMap = new HashMap<>();
        Boolean b = false;
        try {
            b = ssoLoginService.repeatCheck(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("isExists", b == true ? "Y" : "N");
        resultMap.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        resultMap.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return resultMap;
    }

    /**
     * 修改注册主体信息表数据信息
     *
     * @param ttsScltxxcjRegiter
     * @return
     */
    @ApiOperation(value = "修改注册主体信息表数据信息")
    @RequestMapping(value = "/updateTtsScltxxcjRegiter", method = RequestMethod.POST)
    @SystemControllerLog(description = "修改注册主体信息表数据信息",operationType="修改注册主体")
    public Object updateTtsScltxxcjRegiter(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            if (ttsScltxxcjRegiter.getEntityScaleName().equals("企业/个体工商户")
                    || ttsScltxxcjRegiter.getEntityScaleName().equals("合作社")){
                params.put("uniqueKey",ttsScltxxcjRegiter.getCreditCode());
            }
            if(ttsScltxxcjRegiter.getEntityScaleName().equals("个人")
                    || ttsScltxxcjRegiter.getEntityScaleName().equals("家庭农场")){
                params.put("uniqueKey",ttsScltxxcjRegiter.getLegalIdnumber());
            }
            List<String> resutls = new ArrayList<String>();
            params.put("account",ttsScltxxcjRegiter.getAccount());
            resutls = ttsScltxxcjRegiterService.queryByInfounique(params);
            Map<String, Object> map = new HashMap<String, Object>();
            if (resutls.size() > 0) {
                return setModelMap(new ModelMap(), HttpCode.REPEAT_DATA);
            }
            ttsScltxxcjRegiterService.update(ttsScltxxcjRegiter);
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除注册主体信息表信息
     *
     * @param ttsScltxxcjRegiter
     * @return
     */
    @ApiOperation(value = " 删除注册主体信息表信息")
    @RequestMapping(value = "/deleteTtsScltxxcjRegiter", method = RequestMethod.POST)
    @SystemControllerLog(description = "删除注册主体信息表信息",operationType="删除主体")
    public Object deleteTtsScltxxcjRegiter(TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        try {
            ttsScltxxcjRegiterService.delete(ttsScltxxcjRegiter.getId());
            return setSuccessModelMap(new ModelMap());
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "申请备案")
    @RequestMapping(value = "/applyRecord", method = RequestMethod.POST)
    @SystemControllerLog(description = "申请备案",operationType="申请修改主体信息")
    public Object applyRecord(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Integer result = 0;//注册主体信息result
        try {Map<String, Object> params = new HashMap<String, Object>();
            if (ttsScltxxcjRegiter.getEntityScaleName().equals("企业/个体工商户")
                    || ttsScltxxcjRegiter.getEntityScaleName().equals("合作社")){
                params.put("uniqueKey",ttsScltxxcjRegiter.getCreditCode());
                if(ttsScltxxcjRegiter.getEntityScale() == null || ttsScltxxcjRegiter.getEntityScale().equals("")
                        || ttsScltxxcjRegiter.getEntityScaleName() == null || ttsScltxxcjRegiter.getEntityScaleName().equals("")
                        || ttsScltxxcjRegiter.getAccount() == null || ttsScltxxcjRegiter.getAccount().equals("")
                        || ttsScltxxcjRegiter.getPassword() == null || ttsScltxxcjRegiter.getPassword().equals("")
                        || ttsScltxxcjRegiter.getEnterpriseName() == null || ttsScltxxcjRegiter.getEnterpriseName().equals("")
                        || ttsScltxxcjRegiter.getCardType() == null || ttsScltxxcjRegiter.getCardType().equals("")
                        || ttsScltxxcjRegiter.getCreditCode() == null || ttsScltxxcjRegiter.getCreditCode().equals("")
                        || ttsScltxxcjRegiter.getEntityType() == null || ttsScltxxcjRegiter.getEntityType().equals("")
                        || ttsScltxxcjRegiter.getEntityTypeName() == null || ttsScltxxcjRegiter.getEntityTypeName().equals("")
                        || ttsScltxxcjRegiter.getEntityProperty() == null || ttsScltxxcjRegiter.getEntityProperty().equals("")
                        || ttsScltxxcjRegiter.getEntityPropertyName() == null || ttsScltxxcjRegiter.getEntityPropertyName().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustry() == null || ttsScltxxcjRegiter.getEntityIndustry().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustryName() == null || ttsScltxxcjRegiter.getEntityIndustryName().equals("")
                        || ttsScltxxcjRegiter.getAddress() == null || ttsScltxxcjRegiter.getAddress().equals("")
                        || ttsScltxxcjRegiter.getArea() == null || ttsScltxxcjRegiter.getArea().equals("")
                        || ttsScltxxcjRegiter.getLongitude() == null || ttsScltxxcjRegiter.getLongitude().equals("")
                        || ttsScltxxcjRegiter.getLatitude() == null || ttsScltxxcjRegiter.getLatitude().equals("")
                        || ttsScltxxcjRegiter.getLegalName() == null || ttsScltxxcjRegiter.getLegalName().equals("")
                        || ttsScltxxcjRegiter.getLegalIdnumber() == null || ttsScltxxcjRegiter.getLegalIdnumber().equals("")
                        || ttsScltxxcjRegiter.getLegalPhone() == null || ttsScltxxcjRegiter.getLegalPhone().equals("")
                        || ttsScltxxcjRegiter.getLegalImages() == null || ttsScltxxcjRegiter.getLegalImages().equals("")
                        || ttsScltxxcjRegiter.getContactName() == null || ttsScltxxcjRegiter.getContactName().equals("")
                        || ttsScltxxcjRegiter.getContactPhone() == null || ttsScltxxcjRegiter.getContactPhone().equals("")
                        || ttsScltxxcjRegiter.getContactEmail() == null || ttsScltxxcjRegiter.getContactEmail().equals("")
                        || ttsScltxxcjRegiter.getIdcode() == null || ttsScltxxcjRegiter.getIdcode().equals("")
                        || ttsScltxxcjRegiter.getBusinessLicenceimg() == null || ttsScltxxcjRegiter.getBusinessLicenceimg().equals("")
                        || ttsScltxxcjRegiter.getPositiveIdcardeimg() == null || ttsScltxxcjRegiter.getPositiveIdcardeimg().equals("")
                        || ttsScltxxcjRegiter.getNegativeIdcardimg() == null || ttsScltxxcjRegiter.getNegativeIdcardimg().equals("")){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
                if(ttsScltxxcjRegiter.getCardType().equals("普通营业执照（有独立组织机构代码证）")){
                    if(ttsScltxxcjRegiter.getOrganizationCode() == null || ttsScltxxcjRegiter.getOrganizationCode().equals("")
                            || ttsScltxxcjRegiter.getOrganizationCertificateimg() == null
                            || ttsScltxxcjRegiter.getOrganizationCertificateimg().equals("")){
                        return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                    }
                }
                if((ttsScltxxcjRegiter.getBusinessOperationStart() == null
                        || ttsScltxxcjRegiter.getBusinessOperationStart().equals(""))
                        && (ttsScltxxcjRegiter.getIsLong().equals("false")
                        || ttsScltxxcjRegiter.getIsLong() == null
                        || ttsScltxxcjRegiter.getIsLong().equals(""))){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
            }
            if(ttsScltxxcjRegiter.getEntityScaleName().equals("个人")
                    || ttsScltxxcjRegiter.getEntityScaleName().equals("家庭农场")){
                params.put("uniqueKey",ttsScltxxcjRegiter.getLegalIdnumber());
                if(ttsScltxxcjRegiter.getEntityScale() == null || ttsScltxxcjRegiter.getEntityScale().equals("")
                        || ttsScltxxcjRegiter.getEntityScaleName() == null || ttsScltxxcjRegiter.getEntityScaleName().equals("")
                        || ttsScltxxcjRegiter.getAccount() == null || ttsScltxxcjRegiter.getAccount().equals("")
                        || ttsScltxxcjRegiter.getPassword() == null || ttsScltxxcjRegiter.getPassword().equals("")
                        || ttsScltxxcjRegiter.getEnterpriseName() == null || ttsScltxxcjRegiter.getEnterpriseName().equals("")
                        || ttsScltxxcjRegiter.getEntityType() == null || ttsScltxxcjRegiter.getEntityType().equals("")
                        || ttsScltxxcjRegiter.getEntityTypeName() == null || ttsScltxxcjRegiter.getEntityTypeName().equals("")
                        || ttsScltxxcjRegiter.getEntityProperty() == null || ttsScltxxcjRegiter.getEntityProperty().equals("")
                        || ttsScltxxcjRegiter.getEntityPropertyName() == null || ttsScltxxcjRegiter.getEntityPropertyName().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustry() == null || ttsScltxxcjRegiter.getEntityIndustry().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustryName() == null || ttsScltxxcjRegiter.getEntityIndustryName().equals("")
                        || ttsScltxxcjRegiter.getAddress() == null || ttsScltxxcjRegiter.getAddress().equals("")
                        || ttsScltxxcjRegiter.getArea() == null || ttsScltxxcjRegiter.getArea().equals("")
                        || ttsScltxxcjRegiter.getLongitude() == null || ttsScltxxcjRegiter.getLongitude().equals("")
                        || ttsScltxxcjRegiter.getLatitude() == null || ttsScltxxcjRegiter.getLatitude().equals("")
                        || ttsScltxxcjRegiter.getLegalName() == null || ttsScltxxcjRegiter.getLegalName().equals("")
                        || ttsScltxxcjRegiter.getLegalIdnumber() == null || ttsScltxxcjRegiter.getLegalIdnumber().equals("")
                        || ttsScltxxcjRegiter.getLegalPhone() == null || ttsScltxxcjRegiter.getLegalPhone().equals("")
                        || ttsScltxxcjRegiter.getLegalImages() == null || ttsScltxxcjRegiter.getLegalImages().equals("")
                        || ttsScltxxcjRegiter.getContactEmail() == null || ttsScltxxcjRegiter.getContactEmail().equals("")
                        || ttsScltxxcjRegiter.getPositiveIdcardeimg() == null || ttsScltxxcjRegiter.getPositiveIdcardeimg().equals("")
                        || ttsScltxxcjRegiter.getNegativeIdcardimg() == null || ttsScltxxcjRegiter.getNegativeIdcardimg().equals("")){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
            }
            List<String> resutls = new ArrayList<String>();
//            params.put("account",ttsScltxxcjRegiter.getAccount());
            resutls = ttsScltxxcjRegiterService.queryByInfounique(params);
            Map<String, Object> map = new HashMap<String, Object>();
            if (resutls.size() > 0) {
                return setModelMap(new ModelMap(), HttpCode.REPEAT_DATA);
            }
            result = ttsScltxxcjRegiterService.applyRecord(ttsScltxxcjRegiter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 2) {
            return setModelMap(new ModelMap(), HttpCode.OK);
        } else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "注册审核未通过的主体重新申请备案")
    @RequestMapping(value = "/updateRecord", method = RequestMethod.POST)
    @SystemControllerLog(description = "申请备案",operationType="申请修改主体信息")
    public Object updateRecord(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Integer result = 0;//注册主体信息result
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            if (ttsScltxxcjRegiter.getEntityScaleName().equals("企业/个体工商户")
                    || ttsScltxxcjRegiter.getEntityScaleName().equals("合作社")){
                params.put("uniqueKey",ttsScltxxcjRegiter.getCreditCode());
                params.put("account",ttsScltxxcjRegiter.getAccount());
                if(ttsScltxxcjRegiter.getEntityScale() == null || ttsScltxxcjRegiter.getEntityScale().equals("")
                        || ttsScltxxcjRegiter.getEntityScaleName() == null || ttsScltxxcjRegiter.getEntityScaleName().equals("")
                        || ttsScltxxcjRegiter.getAccount() == null || ttsScltxxcjRegiter.getAccount().equals("")
                        || ttsScltxxcjRegiter.getPassword() == null || ttsScltxxcjRegiter.getPassword().equals("")
                        || ttsScltxxcjRegiter.getEnterpriseName() == null || ttsScltxxcjRegiter.getEnterpriseName().equals("")
                        || ttsScltxxcjRegiter.getCardType() == null || ttsScltxxcjRegiter.getCardType().equals("")
                        || ttsScltxxcjRegiter.getCreditCode() == null || ttsScltxxcjRegiter.getCreditCode().equals("")
                        || ttsScltxxcjRegiter.getEntityType() == null || ttsScltxxcjRegiter.getEntityType().equals("")
                        || ttsScltxxcjRegiter.getEntityTypeName() == null || ttsScltxxcjRegiter.getEntityTypeName().equals("")
                        || ttsScltxxcjRegiter.getEntityProperty() == null || ttsScltxxcjRegiter.getEntityProperty().equals("")
                        || ttsScltxxcjRegiter.getEntityPropertyName() == null || ttsScltxxcjRegiter.getEntityPropertyName().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustry() == null || ttsScltxxcjRegiter.getEntityIndustry().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustryName() == null || ttsScltxxcjRegiter.getEntityIndustryName().equals("")
                        || ttsScltxxcjRegiter.getAddress() == null || ttsScltxxcjRegiter.getAddress().equals("")
                        || ttsScltxxcjRegiter.getArea() == null || ttsScltxxcjRegiter.getArea().equals("")
                        || ttsScltxxcjRegiter.getLongitude() == null || ttsScltxxcjRegiter.getLongitude().equals("")
                        || ttsScltxxcjRegiter.getLatitude() == null || ttsScltxxcjRegiter.getLatitude().equals("")
                        || ttsScltxxcjRegiter.getLegalName() == null || ttsScltxxcjRegiter.getLegalName().equals("")
                        || ttsScltxxcjRegiter.getLegalIdnumber() == null || ttsScltxxcjRegiter.getLegalIdnumber().equals("")
                        || ttsScltxxcjRegiter.getLegalPhone() == null || ttsScltxxcjRegiter.getLegalPhone().equals("")
                        || ttsScltxxcjRegiter.getLegalImages() == null || ttsScltxxcjRegiter.getLegalImages().equals("")
                        || ttsScltxxcjRegiter.getContactName() == null || ttsScltxxcjRegiter.getContactName().equals("")
                        || ttsScltxxcjRegiter.getContactPhone() == null || ttsScltxxcjRegiter.getContactPhone().equals("")
                        || ttsScltxxcjRegiter.getContactEmail() == null || ttsScltxxcjRegiter.getContactEmail().equals("")
                        || ttsScltxxcjRegiter.getIdcode() == null || ttsScltxxcjRegiter.getIdcode().equals("")
                        || ttsScltxxcjRegiter.getBusinessLicenceimg() == null || ttsScltxxcjRegiter.getBusinessLicenceimg().equals("")
                        || ttsScltxxcjRegiter.getPositiveIdcardeimg() == null || ttsScltxxcjRegiter.getPositiveIdcardeimg().equals("")
                        || ttsScltxxcjRegiter.getNegativeIdcardimg() == null || ttsScltxxcjRegiter.getNegativeIdcardimg().equals("")){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
                if(ttsScltxxcjRegiter.getCardType().equals("普通营业执照（有独立组织机构代码证）")){
                    if(ttsScltxxcjRegiter.getOrganizationCode() == null || ttsScltxxcjRegiter.getOrganizationCode().equals("")
                            || ttsScltxxcjRegiter.getOrganizationCertificateimg() == null
                            || ttsScltxxcjRegiter.getOrganizationCertificateimg().equals("")){
                        return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                    }
                }
                if((ttsScltxxcjRegiter.getBusinessOperationStart() == null
                        || ttsScltxxcjRegiter.getBusinessOperationStart().equals(""))
                        && (ttsScltxxcjRegiter.getIsLong().equals("false")
                        || ttsScltxxcjRegiter.getIsLong() == null
                        || ttsScltxxcjRegiter.getIsLong().equals(""))){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
            }
            if(ttsScltxxcjRegiter.getEntityScaleName().equals("个人")
                    || ttsScltxxcjRegiter.getEntityScaleName().equals("家庭农场")){
                params.put("uniqueKey",ttsScltxxcjRegiter.getLegalIdnumber());
                params.put("account",ttsScltxxcjRegiter.getAccount());
                if(ttsScltxxcjRegiter.getEntityScale() == null || ttsScltxxcjRegiter.getEntityScale().equals("")
                        || ttsScltxxcjRegiter.getEntityScaleName() == null || ttsScltxxcjRegiter.getEntityScaleName().equals("")
                        || ttsScltxxcjRegiter.getAccount() == null || ttsScltxxcjRegiter.getAccount().equals("")
                        || ttsScltxxcjRegiter.getPassword() == null || ttsScltxxcjRegiter.getPassword().equals("")
                        || ttsScltxxcjRegiter.getEnterpriseName() == null || ttsScltxxcjRegiter.getEnterpriseName().equals("")
                        || ttsScltxxcjRegiter.getEntityType() == null || ttsScltxxcjRegiter.getEntityType().equals("")
                        || ttsScltxxcjRegiter.getEntityTypeName() == null || ttsScltxxcjRegiter.getEntityTypeName().equals("")
                        || ttsScltxxcjRegiter.getEntityProperty() == null || ttsScltxxcjRegiter.getEntityProperty().equals("")
                        || ttsScltxxcjRegiter.getEntityPropertyName() == null || ttsScltxxcjRegiter.getEntityPropertyName().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustry() == null || ttsScltxxcjRegiter.getEntityIndustry().equals("")
                        || ttsScltxxcjRegiter.getEntityIndustryName() == null || ttsScltxxcjRegiter.getEntityIndustryName().equals("")
                        || ttsScltxxcjRegiter.getAddress() == null || ttsScltxxcjRegiter.getAddress().equals("")
                        || ttsScltxxcjRegiter.getArea() == null || ttsScltxxcjRegiter.getArea().equals("")
                        || ttsScltxxcjRegiter.getLongitude() == null || ttsScltxxcjRegiter.getLongitude().equals("")
                        || ttsScltxxcjRegiter.getLatitude() == null || ttsScltxxcjRegiter.getLatitude().equals("")
                        || ttsScltxxcjRegiter.getLegalName() == null || ttsScltxxcjRegiter.getLegalName().equals("")
                        || ttsScltxxcjRegiter.getLegalIdnumber() == null || ttsScltxxcjRegiter.getLegalIdnumber().equals("")
                        || ttsScltxxcjRegiter.getLegalPhone() == null || ttsScltxxcjRegiter.getLegalPhone().equals("")
                        || ttsScltxxcjRegiter.getLegalImages() == null || ttsScltxxcjRegiter.getLegalImages().equals("")
                        || ttsScltxxcjRegiter.getContactEmail() == null || ttsScltxxcjRegiter.getContactEmail().equals("")
                        || ttsScltxxcjRegiter.getPositiveIdcardeimg() == null || ttsScltxxcjRegiter.getPositiveIdcardeimg().equals("")
                        || ttsScltxxcjRegiter.getNegativeIdcardimg() == null || ttsScltxxcjRegiter.getNegativeIdcardimg().equals("")){
                    return setModelMap(new ModelMap(), HttpCode.NOT_FOUND_DATA);
                }
            }
            List<String> resutls = new ArrayList<String>();
            params.put("account",ttsScltxxcjRegiter.getAccount());
            resutls = ttsScltxxcjRegiterService.queryByInfounique(params);
            Map<String, Object> map = new HashMap<String, Object>();
            if (resutls.size() > 0) {
                return setModelMap(new ModelMap(), HttpCode.REPEAT_DATA);
            }
            result = ttsScltxxcjRegiterService.updateRecord(ttsScltxxcjRegiter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 2) {
            return setModelMap(new ModelMap(), HttpCode.OK);
        } else {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "主体类型字典")
    @RequestMapping(value = "/getEntityTypes", method = RequestMethod.POST)
    @SystemControllerLog(description = "主体类型字典",operationType="查询主体数据字典")
    public Object getEntityTypes() {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            List<SysDictData> industrys = sysDictionaryService.getIndustry();
            List<SysDictData> subjPropertys = sysDictionaryService.getSubjPropertys();
            List<SysDictData> subTypes = sysDictionaryService.getSubjTypes();
            List<SysDictData> orgs = sysDictionaryService.getOrgs();
            resultMap.put("industrys", industrys);
            resultMap.put("subjPropertys", subjPropertys);
            resultMap.put("subTypes", subTypes);
            resultMap.put("orgs", orgs);
            return setSuccessModelMap(new ModelMap(), resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "根据不同行业单位字典")
    @RequestMapping(value = "/getUnits", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据不同行业单位字典",operationType="查询不同行业的数据字典")
    public Object getUnits(String id) {
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            String[] idZ = {"33a6dbab71c04079a8a41d5e725d2b3d100ec0d3dbc44f67963ffb54014fb607"};
            String[] idX = {"88bf73e5c7b546388346736691146a28cd0658dcaa674781b8e622a484dbf1d9"};
            String[] idS = {"5a3930420e5e4bc9ac4e75bea59f1067e1210d80e4514e7da392e1528cb942c3"};
            List<SysDictData> unitZ = sysDictionaryService.getUnit(idZ);
            List<SysDictData> unitX = sysDictionaryService.getUnit(idX);
            List<SysDictData> unitS = sysDictionaryService.getUnit(idS);
            resultMap.put("unitZ", unitZ);
            resultMap.put("unitX", unitX);
            resultMap.put("unitS", unitS);
            return setSuccessModelMap(new ModelMap(), resultMap);
        } catch (Exception e) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 单纯上传文件--暂加
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload")
    @SystemControllerLog(description = "上传文件",operationType="添加")
    public Map<String, Object> upload(HttpServletRequest request) {
        //返回对象
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("path", this.uploadFile(request));
        } catch (Exception e) {
            map.put("httpCode", ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return map;
        }
        map.put("httpCode", ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }
    /**
     *企业、合作社忘记密码
     */

    @ApiOperation(value = "企业忘记密码")
    @RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
    @SystemControllerLog(description = "忘记密码",operationType="查询密码")
    public Map<String, Object> forgetpassword(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Map<String, Object> map = new HashMap<String, Object>();
        TtsScltxxcjRegiter vo = ttsScltxxcjRegiterService.getUserInfoByRegiter(ttsScltxxcjRegiter);
        if(vo.getAccount()==null||vo.getAccount()=="") {
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return map;
        }else{
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("account", vo.getAccount());
            return map;
        }
    }
    /**
     * 忘记账号验证
     */
    @ApiOperation(value = "忘记账号验证")
    @RequestMapping(value = "/forgetAccount", method = RequestMethod.POST)
    @SystemControllerLog(description = "忘记账号", operationType = "查询")
    public Map<String, Object> forgetAccount(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Map<String, Object> map = new HashMap<String, Object>();
        TtsScltxxcjRegiter vo = ttsScltxxcjRegiterService.getUserInfoByRegiter(ttsScltxxcjRegiter);
        if (vo.getAccount() == null || vo.getAccount() == "") {
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return map;
        } else {
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            map.put("account", vo.getAccount());
            String msg = "请电话联系所属监管机构，申请账号或密码重置！";
            map.put("msg", msg);
            return map;
        }
    }


    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    @SystemControllerLog(description = "修改密码",operationType="修改")
    public Object updatepassword(@RequestBody TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Map<String, Object> map = new HashMap<String, Object>();
        String pass = ttsScltxxcjRegiter.getRePassword().trim();
        String acc = ttsScltxxcjRegiter.getAccount().trim();
        if (ttsForgetPassword.updatepassword(acc, pass)) {
            map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
            return map;
        }else {
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return map;
        }
    }

    @ApiOperation(value = "查询主体信息")
    @RequestMapping(value = "/getRegiterByAccount", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据account查询主体信息",operationType="修改")
    public Map<String, Object> selectByAccount(String account ) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(account)) {
                map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
                map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
                return map;
            } else {
                map.put("account", account);
                TtsScltxxcjRegiter vo = ttsScltxxcjRegiterService.findByAccount(map);
                map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
                map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
                map.put("vo", vo);
                return map;
            }
        } catch (Exception e) {
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return map;
        }
    }

    @ApiOperation(value = "查询账号原密码是否匹配")
    @RequestMapping(value = "/checkFormerPwd", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据account查询主体信息",operationType="查询")
    public Object checkFormerPwd(String account,String password){
        Map<String, Object> map = ssoLoginService.login(account, password);
        if (map == null || map.size() < 1) {
            map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
            map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
            return map;
        }
        map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
        map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
        return map;
    }

    @ApiOperation(value = "根据主体身份码查询主体信息")
    @RequestMapping(value = "/queryInfoByEntityCode", method = RequestMethod.POST)
    @SystemControllerLog(description = "根据主体身份码查询主体信息",operationType="查询")
    public Object  queryInfoByEntityCode(String entityCode){
        TtsScltxxcjRegiter ttsScltxxcjRegiter = new TtsScltxxcjRegiter();
        ttsScltxxcjRegiter = ttsScltxxcjRegiterService.findInfoByEntityCode(entityCode);
        return ttsScltxxcjRegiter;
    }
}

