package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.OrgFormEnum;
import com.sofn.core.util.SecurityUtil;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.UserEnum;
import com.sofn.dao.generator.TtsScltxxcjRegiterMapper;
import com.sofn.dao.generator.TtsScltxxcjUserMapper;
import com.sofn.dao.tts.TtsScltxxcjRegiterExpandMapper;
import com.sofn.dao.tts.TtsScltxxcjUserExpandMapper;
import com.sofn.model.generator.*;
import com.sofn.model.tts.TtsScltxxcjRegiterDto;
import com.sofn.provider.sso.SSOLoginProvider;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysUserProvider;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 注册主体信息表 providerImpl 实现
 * Created by moon.l
 */
@DubboService(interfaceClass = TtsScltxxcjRegiterProvider.class)
public class TtsScltxxcjRegiterProviderImpl extends BaseProviderImpl<TtsScltxxcjRegiter> implements TtsScltxxcjRegiterProvider {

    @Autowired
    private TtsScltxxcjRegiterMapper ttsScltxxcjRegiterMapper;
    @Autowired
    private TtsScltxxcjUserMapper tsScltxxcjUserMapper;
    @Autowired
    private TtsScltxxcjUserExpandMapper ttsScltxxcjUserExpandMapper;
    @Autowired
    private TtsScltxxcjRegiterExpandMapper TtsScltxxcjRegiterExpandMapper;
    @Autowired
    private TtsScltxxcjUsernumberProvider ttsScltxxcjUsernumberProvider;

    @DubboReference
    private SSOLoginProvider ssoLoginProvider;
    @DubboReference
    private SysUserProvider sysUserProvider;
    @DubboReference
    private SysDictProvider sysDictProvider;
    @DubboReference
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;


    public PageInfo<TtsScltxxcjRegiter> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = TtsScltxxcjRegiterExpandMapper.getPageInfo(map);
        long count = TtsScltxxcjRegiterExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int applyRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter) throws Exception {
        CurrentUser userModel = new CurrentUser();
        userModel.setStatus("0");
        int result = 0;
        userModel.setAccount(ttsScltxxcjRegiter.getAccount());
        userModel.setUserName(ttsScltxxcjRegiter.getAccount());
        userModel.setId(StringUtils.getUUIDString());
        userModel.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(ttsScltxxcjRegiter.getPassword())));
        userModel.setUpdateTime(new Date());
        userModel.setCreateTime(new Date());
        userModel.setStatus("1");
        userModel.setDelFlag("N");
        userModel.setEmail(ttsScltxxcjRegiter.getContactEmail());
        userModel.setPhone(ttsScltxxcjRegiter.getLegalPhone());
        SysDictData typeData = sysDictProvider.getDictDataById(ttsScltxxcjRegiter.getEntityScale());
        String type = typeData.getDictValue();
        ttsScltxxcjRegiter.setId(StringUtils.getUUIDString());
        ttsScltxxcjRegiter.setRegisterTime(new Date());
        ttsScltxxcjRegiter.setApproveStatus("0");//首次备案未审核
        ttsScltxxcjRegiter.setDelFlag("N");//删除标志
        ttsScltxxcjRegiter.setUpdateTime(new Date());
        ttsScltxxcjRegiter.setCreateTime(new Date());
        ttsScltxxcjRegiter.setEntityIdcode(ttsScltxxcjRegiter.getAccount());

        //新建一个list集合，存入行业类型的字典值(用于角色授权)
        List<String> industryTypes = new ArrayList<>();
        String indus = ttsScltxxcjRegiter.getEntityIndustry();
        String[] ids = indus.split(",");
        for (int i = 0; i < ids.length; i++) {
            SysDictData industry = sysDictProvider.getDictDataById(ids[i]);
            String industryValue = industry.getDictValue().toString().trim();
            industryTypes.add(industryValue);
        }
        //此处用于判断是否勾选了屠宰场，如前端名称发生变化，请相应改变包含字符串的判断
        boolean choiceAbattoir = false;
        String Propertys = ttsScltxxcjRegiter.getEntityPropertyName();
        if (Propertys.indexOf("屠宰场") != -1) {
            choiceAbattoir = true;
        }
        //主体注册，token为空
        String token = "";

        SysUser sysUser = new SysUser();
        sysUser.setAccount(ttsScltxxcjRegiter.getAccount());
        sysUser.setPassword(ttsScltxxcjRegiter.getPassword());

        if (!"".equals(type) && null != type) {
            if ("0".equals(type)) {//企业类型
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
                sysUserProvider.addSubjUser(token, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.ENTERPRISE);
            } else if ("1".equals(type)) {//合作社
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
                sysUserProvider.addSubjUser(token, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.COOPERATIVE);
            } else if ("2".equals(type)) {//农场
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
                sysUserProvider.addSubjUser(token, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.FARM);
            } else if ("3".equals(type)) {//个人
                ttsScltxxcjRegiter.setLegalPhone(ttsScltxxcjRegiter.getLegalPhone());
                ttsScltxxcjRegiter.setLegalName(ttsScltxxcjRegiter.getRealName());
                ttsScltxxcjRegiter.setLegalIdnumber(ttsScltxxcjRegiter.getIdcode());
                sysUserProvider.addSubjUser(token, UserEnum.SUBJADMIN, sysUser, industryTypes, choiceAbattoir, OrgFormEnum.PERSONAL);
            }
        }
        if (ttsScltxxcjRegiter.getEntityScaleName().equals("企业/个体工商户")
                || ttsScltxxcjRegiter.getEntityScaleName().equals("合作社")) {
            ttsScltxxcjRegiter.setInfoUnique(ttsScltxxcjRegiter.getCreditCode());
        }
        if (ttsScltxxcjRegiter.getEntityScaleName().equals("个人")
                || ttsScltxxcjRegiter.getEntityScaleName().equals("家庭农场")) {
            ttsScltxxcjRegiter.setInfoUnique(ttsScltxxcjRegiter.getLegalIdnumber());
        }
        result += ttsScltxxcjRegiterMapper.insert(ttsScltxxcjRegiter);
        if (result > 0) {
            TtsScltxxcjUser ttsScltxxcjUser = new TtsScltxxcjUser();
            if (!"".equals(type) && null != type) {
                if ("3".equals(type)) {//个人
                    ttsScltxxcjUser.setName(ttsScltxxcjRegiter.getRealName());
                    ttsScltxxcjUser.setPhone(ttsScltxxcjRegiter.getContactPhone());
                    ttsScltxxcjUser.setEmail(ttsScltxxcjRegiter.getContactEmail());
                } else {//非个人
                    ttsScltxxcjUser.setName(ttsScltxxcjRegiter.getContactName());
                    ttsScltxxcjUser.setPhone(ttsScltxxcjRegiter.getContactPhone());
                    ttsScltxxcjUser.setEmail(ttsScltxxcjRegiter.getContactEmail());
                }
            }
            ttsScltxxcjUser.setId(UUID.randomUUID().toString().replace("-", ""));
            ttsScltxxcjUser.setAccount(ttsScltxxcjRegiter.getAccount());
            ttsScltxxcjUser.setCreateTime(new Date());
            ttsScltxxcjUser.setUpdateTime(new Date());
            ttsScltxxcjUser.setIsMain("1");
            ttsScltxxcjUser.setDelFlag("N");//删除标志
            ttsScltxxcjUser.setEntityIdcode(ttsScltxxcjRegiter.getAccount());
            result += tsScltxxcjUserMapper.insert(ttsScltxxcjUser);
        }
        return result;
    }

    @Override
    @Transactional
    public int updateRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter) throws Exception {
        int result = 0;

        String type = ttsScltxxcjRegiter.getEntityScale();
        ttsScltxxcjRegiter.setRegisterTime(new Date());
        ttsScltxxcjRegiter.setApproveStatus("0");//首次备案未审核
        ttsScltxxcjRegiter.setDelFlag("N");//删除标志
        ttsScltxxcjRegiter.setUpdateTime(new Date());
        ttsScltxxcjRegiter.setEntityIdcode(ttsScltxxcjRegiter.getAccount());
        if (!"".equals(type) && null != type) {
            if ("1".equals(type)) {//企业类型
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
            } else if ("2".equals(type)) {//合作社
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
            } else if ("3".equals(type)) {//农场
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
            } else if ("4".equals(type)) {//个人
                ttsScltxxcjRegiter.setLegalPhone(ttsScltxxcjRegiter.getContactPhone());
                ttsScltxxcjRegiter.setLegalName(ttsScltxxcjRegiter.getRealName());
                ttsScltxxcjRegiter.setLegalIdnumber(ttsScltxxcjRegiter.getIdcode());
            }
        }
        if (ttsScltxxcjRegiter.getEntityScaleName().equals("企业/个体工商户")
                || ttsScltxxcjRegiter.getEntityScaleName().equals("合作社")) {
            ttsScltxxcjRegiter.setInfoUnique(ttsScltxxcjRegiter.getCreditCode());
        }
        if (ttsScltxxcjRegiter.getEntityScaleName().equals("个人")
                || ttsScltxxcjRegiter.getEntityScaleName().equals("家庭农场")) {
            ttsScltxxcjRegiter.setInfoUnique(ttsScltxxcjRegiter.getLegalIdnumber());
        }
        result += TtsScltxxcjRegiterExpandMapper.updateByAccount(ttsScltxxcjRegiter);
        if (result > 0) {
            TtsScltxxcjUser ttsScltxxcjUser = new TtsScltxxcjUser();
            ttsScltxxcjUser.setName(ttsScltxxcjRegiter.getContactName());
            ttsScltxxcjUser.setPhone(ttsScltxxcjRegiter.getContactPhone());
            ttsScltxxcjUser.setEmail(ttsScltxxcjRegiter.getContactEmail());
            ttsScltxxcjUser.setAccount(ttsScltxxcjRegiter.getAccount());
            ttsScltxxcjUser.setUpdateTime(new Date());
            ttsScltxxcjUser.setDelFlag("N");//删除标志
            ttsScltxxcjUser.setEntityIdcode(ttsScltxxcjRegiter.getAccount());
            result += ttsScltxxcjUserExpandMapper.updateByacc(ttsScltxxcjUser);
        }

        return result;
    }

    @Override
    public TtsScltxxcjRegiter selectByAccountAndUserInfo(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.selectByAccountAndUserInfo(map);
    }

    @Override
    public TtsScltxxcjRegiter selectByUserIdCode(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.selectByUserIdCode(map);
    }

    @Override
    public TtsScltxxcjRegiter selectByEntityIdCode(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.selectByEntityIdCode(map);
    }

    @Override
    public List<String> queryByInfounique(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.queryByInfounique(map);
    }

    @Override
    public Map<String, Object> queryByIdCode(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.queryByIdCode(map);
    }

    @Override
    public Map<String, Object> queryDictValueById(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.queryDictValueById(map);
    }

    @Override
    public List<TtsScltxxcjRegiter> getEntityPageInfoByCondition(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getEntityPageInfoByCondition(map);
    }

    @Override
    public long getTotal(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getCount(map);
    }

    @Override
    public List<TtsScltxxcjRegiterDto> getSubjEntList(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getSubjEntListNew(map);
    }

    @Override
    public long getSubjEntTotal(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getSubjEntTotal(map);
    }

    @Override
    public TtsScltxxcjRegiter selectByEntityId(String id) {
        return ttsScltxxcjRegiterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TtsScltxxcjRegiter> getSubList(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getSubjEntList(map);
    }

    @Override
    public Map<String, Object> getRegisterNum(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getRegisterNum(map);
    }


    /**
     * 审批（注册）帐号
     * @param map 编号 id
     *            审批类型 approveStatus= 1：注销,2：变更，3：撤销
     *            审批状态 approveStatus=0:未审核，1:已审核通过,2:审核未通过
     *            审批意见 approveOpinion
     *            审批人 approveName
     *            审批时间 approveTime
     *            审批人主体用户码 approveUserIdcode
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public Integer updateEntityByEntity(Map<String, Object> map, String token) throws Exception {
        TtsScltxxcjRegiter tts = new TtsScltxxcjRegiter();
        if (!"".equals(map.get("approveStatus")) && null != map.get("approveStatus")) {
            //如果是审批通过的话
            if (map.get("approveStatus").equals("1")) {
                tts = ttsScltxxcjRegiterMapper.selectByPrimaryKey(map.get("id").toString());
                IdGenerator idGenerator = new IdGenerator();
                //主体组织形式
                Integer entityScale = 0;
                if ("".equals(tts.getCreditCode()) || null == tts.getCreditCode()) {
                    //个人
                    entityScale = 3;
                } else {
                    //企业
                    entityScale = 0;
                }
                //主体类型
                Map<String, Object> params = new HashedMap();
                params.put("id", tts.getEntityType());
                Map<String, Object> m = TtsScltxxcjRegiterExpandMapper.queryDictValueById(params);
                Integer entityType = Integer.parseInt(m.get("DICTVALUE").toString());
                switch (entityScale) {
                    //个人
                    case 3:
                        switch (entityType) {
                            //经营主体
                            case 1:
                                map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode()));
                                map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainManagementBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode(), token == null || token == "" ? null : token));
                                break;
                            //生产经营主体主体
                            case 2:
                                map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode()));
                                map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode(), token == null || token == "" ? null : token));
                                break;
                            //生产主体+其他
                            default:
                                map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainWorkingBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode()));
                                map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainWorkingBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode(), token == null || token == "" ? null : token));
                                break;
                        }
                        break;
                    default:
                        switch (entityType) {
                            //经营主体
                            case 1:
                                map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode()));
                                map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainManagementBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode(), token == null || token == "" ? null : token));
                                break;
                            //生产经营主体主体
                            case 2:
                                map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode()));
                                map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode(), token == null || token == "" ? null : token));
                                break;
                            //生产主体+其他
                            default:
                                map.put("entityIdcode", sysCodeGeneratorProvider.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainWorkingBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode()));
                                map.put("userIdcode", sysCodeGeneratorProvider.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainWorkingBody, tts.getCreditCode() == null || tts.getCreditCode() == "" ? tts.getLegalIdnumber() : tts.getCreditCode(), token == null || token == "" ? null : token));
                                break;
                        }
                        break;
                }
                Map<String, Object> umap = new HashedMap();
                umap.put("entityCode", map.get("entityIdcode"));
                ttsScltxxcjUsernumberProvider.updateUsernumByEntityCode(umap);
            }
        }
        //在user表中同步主体身份码和主体用户码
        TtsScltxxcjUser ttsScltxxcjUser = new TtsScltxxcjUser();
        ttsScltxxcjUser.setAccount(map.get("account").toString());
        if (map.get("entityIdcode") != null && map.get("entityIdcode") != "") {
            ttsScltxxcjUser.setEntityIdcode(map.get("entityIdcode").toString());
        }
        if (map.get("userIdcode") != null && map.get("userIdcode") != "") {
            ttsScltxxcjUser.setUserIdcode(map.get("userIdcode").toString());
        }
        ttsScltxxcjUser.setDelFlag("N");
        ttsScltxxcjUserExpandMapper.updateByAccount(ttsScltxxcjUser);
        Integer result = TtsScltxxcjRegiterExpandMapper.updateEntityByEntity(map);
        return result;
    }


    /**
     * *返回主体号
     * 通过企业证件号，法人身份证号
     *
     * @param creditCode
     * @param legalIdnumber
     * @return
     */
    private String getRegistNum(String creditCode, String legalIdnumber) {


        //如果企业证件号为空
        if ("".equals(creditCode) || null == creditCode) {
            StringBuffer sbLegalIdnumber = new StringBuffer(legalIdnumber);
            if (sbLegalIdnumber.length() < 18) {
                for (int i = 0; i < 18 - sbLegalIdnumber.length(); i++)
                    sbLegalIdnumber.append("0");
            }
            return sbLegalIdnumber.toString();
        }
        //去掉注册号中的-号
        creditCode = creditCode.replaceAll("-", "");
        StringBuffer sbCreditCode = new StringBuffer(creditCode);
        if (sbCreditCode.length() < 18) {
            for (int i = 0; i < 18 - sbCreditCode.length(); i++)
                sbCreditCode.append("0");
            return sbCreditCode.toString();
        }
        return creditCode;
    }

    @Override
    public TtsScltxxcjRegiter getInfoForBody(String code) {
        return TtsScltxxcjRegiterExpandMapper.getInfoForBody(code);
    }

    @Override
    public TtsScltxxcjRegiter getInfoForUser(String code) {
        return TtsScltxxcjRegiterExpandMapper.getInfoForUser(code);
    }

    @Override
    public TtsScltxxcjRegiter getInfoByUserInfo(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getInfoByUserInfo(map);
    }

    @Override
    public TtsScltxxcjRegiter queryByUserId(String id) {
        return TtsScltxxcjRegiterExpandMapper.queryByUserId(id);
    }

    @Override
    public TtsScltxxcjRegiterAndUser queryByUserIdCode(String id) {
        return TtsScltxxcjRegiterExpandMapper.queryByUserIdCode(id);
    }

    @Override
    public TtsScltxxcjRegiter getMainByUserIdCode(String entityIdCode) {
        return TtsScltxxcjRegiterExpandMapper.getMainByUserIdCode(entityIdCode);
    }

    @Override
    public TtsScltxxcjRegiter getUserInfoByRegiter(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getUserInfoByRegiter(map);
    }

    @Override
    public void updateByFirm(TtsScltxxcjRegiter scltxxcjRegiter) {
        TtsScltxxcjRegiterExpandMapper.updateByFirm(scltxxcjRegiter);
    }

    @Override
    public Map<String, Object> getSubjSuperviseListByArea(String areaId) {
        return TtsScltxxcjRegiterExpandMapper.getSubjSuperviseListByArea(areaId);
    }


    @Override
    public TtsScltxxcjRegiter getEntityByCode(String code) {
        return TtsScltxxcjRegiterExpandMapper.getEntityByCode(code);
    }

    @Override
    public TtsScltxxcjRegiter findInfoByCreditCode(Map<String, Object> params) {
        return TtsScltxxcjRegiterExpandMapper.queryEntityCodeAndUserCode(params);
    }

    @Override
    public TtsScltxxcjRegiter findByAccount(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.findByAccount(map);
    }

    @Override
    public TtsScltxxcjRegiter queryEntityInfoByEntity(String sourceEntity) {
        return TtsScltxxcjRegiterExpandMapper.queryEntityInfoByEntity(sourceEntity);
    }

    @Override
    public TtsScltxxcjRegiter findInfoByEntityCode(String entityCode) {
        return TtsScltxxcjRegiterExpandMapper.findInfoByEntityCode(entityCode);
    }

    @Override
    public List<TtsScltxxcjRegiter> getSubjEntByCondition(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getSubjEntByCondition(map);
    }

    @Override
    public int insertSubj(TtsScltxxcjRegiter subj) {
        return ttsScltxxcjRegiterMapper.insert(subj);
    }

    @Override
    public List<TtsScltxxcjRegiter> getSubjEntByUnionCredit(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getSubjEntByUnionCredit(map);
    }
}
