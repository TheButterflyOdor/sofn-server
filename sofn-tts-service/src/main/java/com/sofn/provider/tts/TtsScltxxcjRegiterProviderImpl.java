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
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysUserProvider;
import jxl.write.Boolean;
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
    private SSOLoginProvider  ssoLoginProvider;
    @DubboReference
    private SysUserProvider sysUserProvider;
    @DubboReference
    private SysDictProvider sysDictProvider;


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
    public int applyRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter)throws Exception {
        CurrentUser userModel = new CurrentUser();
        userModel.setStatus("0");
        int result = 0;
        userModel.setAccount(ttsScltxxcjRegiter.getAccount());
        userModel.setUserName(ttsScltxxcjRegiter.getAccount());
        userModel.setId(StringUtils.getUUIDString());
        userModel.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(ttsScltxxcjRegiter.getPassword())));
        userModel.setUpdateTime(new Date());
        userModel.setCreateTime(new Date());
//        userModel.setDeptId("enterprise");
//        userModel.setPostId("enterprise");
//        userModel.setRoleId("enterprise");
        userModel.setStatus("1");
        userModel.setDelFlag("N");
        userModel.setEmail(ttsScltxxcjRegiter.getContactEmail());
        userModel.setPhone(ttsScltxxcjRegiter.getLegalPhone());
//        result += ssoLoginProvider.register(userModel);

//        String type = ttsScltxxcjRegiter.getEntityScale();
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
        for(int i = 0;i < ids.length;i++){
            SysDictData industry = sysDictProvider.getDictDataById(ids[i]);
            String industryValue = industry.getDictValue().toString().trim();
            industryTypes.add(industryValue);
        }
        //此处用于判断是否勾选了屠宰场，如前端名称发生变化，请相应改变包含字符串的判断
        boolean choiceAbattoir = false;
        String Propertys = ttsScltxxcjRegiter.getEntityPropertyName();
        if(Propertys.indexOf("屠宰场") != -1){
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
                sysUserProvider.addSubjUser(token,UserEnum.SUBJADMIN,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.ENTERPRISE);
            } else if ("1".equals(type)) {//合作社
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
                sysUserProvider.addSubjUser(token,UserEnum.SUBJADMIN,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.COOPERATIVE);
            } else if ("2".equals(type)) {//农场
                ttsScltxxcjRegiter.setRealName(ttsScltxxcjRegiter.getLegalName());
                sysUserProvider.addSubjUser(token,UserEnum.SUBJADMIN,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.FARM);
            } else if ("3".equals(type)) {//个人
                ttsScltxxcjRegiter.setLegalPhone(ttsScltxxcjRegiter.getContactPhone());
                ttsScltxxcjRegiter.setLegalName(ttsScltxxcjRegiter.getRealName());
                ttsScltxxcjRegiter.setLegalIdnumber(ttsScltxxcjRegiter.getIdcode());
                sysUserProvider.addSubjUser(token,UserEnum.SUBJADMIN,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.PERSONAL);
            }
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
            ttsScltxxcjUser.setDelFlag("Y");//删除标志
            ttsScltxxcjUser.setEntityIdcode(ttsScltxxcjRegiter.getAccount());
            result += tsScltxxcjUserMapper.insert(ttsScltxxcjUser);
        }
        return result;
    }

    @Override
    @Transactional
    public int updateRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter)throws Exception {
        int result = 0;
        /*CurrentUser userModel = new CurrentUser();
        userModel.setStatus("0");
        userModel.setAccount(ttsScltxxcjRegiter.getAccount());
        userModel.setUserName(ttsScltxxcjRegiter.getAccount());
        userModel.setId(StringUtils.getUUIDString());
        userModel.setPassword(SecurityUtil.encryptMd5(SecurityUtil.encryptSHA(ttsScltxxcjRegiter.getPassword())));
        userModel.setUpdateTime(new Date());
        userModel.setCreateTime(new Date());
//        userModel.setDeptId("enterprise");
//        userModel.setPostId("enterprise");
//        userModel.setRoleId("enterprise");
        userModel.setStatus("1");
        userModel.setDelFlag("N");
        userModel.setEmail(ttsScltxxcjRegiter.getContactEmail());
        userModel.setPhone(ttsScltxxcjRegiter.getLegalPhone());
        result += ssoLoginProvider.register(userModel);*/

        String type = ttsScltxxcjRegiter.getEntityScale();
        ttsScltxxcjRegiter.setRegisterTime(new Date());
        ttsScltxxcjRegiter.setApproveStatus("0");//首次备案未审核
        ttsScltxxcjRegiter.setDelFlag("N");//删除标志
        ttsScltxxcjRegiter.setUpdateTime(new Date());
        //ttsScltxxcjRegiter.setCreateTime(new Date());
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

        result += TtsScltxxcjRegiterExpandMapper.updateByAccount(ttsScltxxcjRegiter);
        if (result > 0) {
            TtsScltxxcjUser ttsScltxxcjUser = new TtsScltxxcjUser();
            ttsScltxxcjUser.setName(ttsScltxxcjRegiter.getContactName());
            ttsScltxxcjUser.setPhone(ttsScltxxcjRegiter.getContactPhone());
            ttsScltxxcjUser.setEmail(ttsScltxxcjRegiter.getContactEmail());
            // ttsScltxxcjUser.setId(UUID.randomUUID().toString().replace("-", ""));
            ttsScltxxcjUser.setAccount(ttsScltxxcjRegiter.getAccount());
            //ttsScltxxcjUser.setCreateTime(new Date());
            ttsScltxxcjUser.setUpdateTime(new Date());
//            ttsScltxxcjUser.setIsMain("1");
            ttsScltxxcjUser.setDelFlag("Y");//删除标志
            ttsScltxxcjUser.setEntityIdcode(ttsScltxxcjRegiter.getAccount());
            result += ttsScltxxcjUserExpandMapper.updateByacc(ttsScltxxcjUser);
        }

        return result;
    }

    @Override
    public TtsScltxxcjRegiter selectByAccount(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.selectByAccount(map);
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
    public long queryByCreditCode(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.queryByCreditCode(map);
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
    public Map<String, Object> getRegisterNum(Map<String, Object> map) {
        return TtsScltxxcjRegiterExpandMapper.getRegisterNum(map);
    }



    @Override
    public Integer updateEntityByEntity(Map<String, Object> map) throws Exception {
        if (!"".equals(map.get("approveStatus")) && null != map.get("approveStatus")) {
            //如果是审批通过的话
            if (map.get("approveStatus").equals("1")) {
                TtsScltxxcjRegiter tts = ttsScltxxcjRegiterMapper.selectByPrimaryKey(map.get("id").toString());
                IdGenerator idGenerator = new IdGenerator();
                //主体组织形式
                Integer entityType = 0;
                if ("".equals(tts.getCreditCode()) || null == tts.getCreditCode()) {
                    //个人
                    entityType = 3;
                } else {
                    //企业
                    entityType = 0;
                }
                //主体类型
                Map<String, Object> params = new HashedMap();
                params.put("id", tts.getEntityScale());
                Map<String, Object> m = TtsScltxxcjRegiterExpandMapper.queryDictValueById(params);
                Integer entityScale = Integer.parseInt(m.get("DICTVALUE").toString());
                switch (entityType) {
                    //个人
                    case 3:
                        switch (entityScale) {
                            //经营主体
                            case 1:
                                map.put("entityIdcode", idGenerator.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainManagementBody,
                                        IdGenerator.OrganizationForms.Individual, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber())
                                ));
                                map.put("userIdcode", idGenerator.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainManagementBody,
                                        IdGenerator.OrganizationForms.Individual, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber()), "1")
                                );
                                break;
                            //生产经营主体主体
                            case 2:
                                map.put("entityIdcode", idGenerator.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                                        IdGenerator.OrganizationForms.Individual, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber())
                                ));
                                map.put("userIdcode", idGenerator.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                                        IdGenerator.OrganizationForms.Individual, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber()), "1")
                                );
                                break;
                            //生产主体+其他
                            default:
                                map.put("entityIdcode", idGenerator.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainWorkingBody,
                                        IdGenerator.OrganizationForms.Individual, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber())
                                ));
                                map.put("userIdcode", idGenerator.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainWorkingBody,
                                        IdGenerator.OrganizationForms.Individual, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber()), "1")
                                );
                                break;
                        }
                        break;
                    default:
                        switch (entityScale) {
                            //经营主体
                            case 1:
                                map.put("entityIdcode", idGenerator.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainManagementBody,
                                        IdGenerator.OrganizationForms.Enterprise, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber())
                                ));
                                map.put("userIdcode", idGenerator.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainManagementBody,
                                        IdGenerator.OrganizationForms.Enterprise, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber()), "1")
                                );
                                break;
                            //生产经营主体主体
                            case 2:
                                map.put("entityIdcode", idGenerator.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                                        IdGenerator.OrganizationForms.Enterprise, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber())
                                ));
                                map.put("userIdcode", idGenerator.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                                        IdGenerator.OrganizationForms.Enterprise, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber()), "1")
                                );
                                break;
                            //生产主体+其他
                            default:
                                map.put("entityIdcode", idGenerator.getMainBodyIdentityCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainWorkingBody,
                                        IdGenerator.OrganizationForms.Enterprise, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber())
                                ));
                                map.put("userIdcode", idGenerator.getMainBodyUserCode(IdGenerator.ServiceType.Retrospect, IdGenerator.MainBodyCategories.MainWorkingBody,
                                        IdGenerator.OrganizationForms.Enterprise, this.getRegistNum(tts.getCreditCode(), tts.getLegalIdnumber()), "1")
                                );
                                break;
                        }
                        break;
                }
                //在user表中同步主体身份码和主体用户码
                TtsScltxxcjUser ttsScltxxcjUser = new TtsScltxxcjUser();
                ttsScltxxcjUser.setAccount(tts.getAccount());
                ttsScltxxcjUser.setEntityIdcode(map.get("entityIdcode").toString());
                ttsScltxxcjUser.setUserIdcode(map.get("userIdcode").toString());
                ttsScltxxcjUser.setDelFlag("N");
                ttsScltxxcjUserExpandMapper.updateByAccount(ttsScltxxcjUser);
                Map<String, Object> umap = new HashedMap();
                umap.put("entityCode",map.get("entityIdcode"));
                ttsScltxxcjUsernumberProvider.updateUsernumByEntityCode(umap);
            }
        }

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
    public TtsScltxxcjRegiter getMainByUserIdCode(String userIdCode) {
        return TtsScltxxcjRegiterExpandMapper.getMainByUserIdCode(userIdCode);
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
    public Map<String, Object> getSubjSuperviseListByArea(String areaId) { return TtsScltxxcjRegiterExpandMapper.getSubjSuperviseListByArea(areaId); }

}
