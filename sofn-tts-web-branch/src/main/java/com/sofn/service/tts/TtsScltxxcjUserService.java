package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.oid.IdGenerator;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.OrgFormEnum;
import com.sofn.core.util.Result;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.UserEnum;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysUser;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjUser;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.sys.SysCodeGeneratorProvider;
import com.sofn.provider.sys.SysDictProvider;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.tts.TtsScltxxcjUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业用户及子账户信息表 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjUserService extends BaseService<TtsScltxxcjUserProvider, TtsScltxxcjUser> {
    @Autowired
    public SysCreateUserService sysCreateUserService;
    @Autowired
    public SSOLoginService ssoLoginService;
    @Autowired
    public TtsScltxxcjUserService ttsScltxxcjUserService;
    @Autowired
    public TtsScltxxcjUsernumberService ttsScltxxcjUsernumberService;
    @DubboReference
    private SysUserProvider sysUserProvider;
    @DubboReference
    private SysDictProvider sysDictProvider;
    @DubboReference
    private SysCodeGeneratorProvider sysCodeGeneratorProvider;


    @DubboReference
    public void setTtsScltxxcjUserProvider(TtsScltxxcjUserProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsScltxxcjUser bean, int pageNum, int pageSize,String createTimeStart,String createTimeEnd,TtsScltxxcjRegiter user) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("user",bean);
        queryParams.put("createTimeStart",createTimeStart);
        queryParams.put("createTimeEnd",createTimeEnd);
        queryParams.put("admin",user);
        return provider.getPageInfo(queryParams);
    }


    public int reset(TtsScltxxcjUser ttsScltxxcjUser) {
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("user",ttsScltxxcjUser);
        return provider.resetUserPassword(queryParams);
    }

    /**
     * 根据注册账号名查询登录用户信息
     * @param account
     * @return
     */
    public TtsScltxxcjUser getUserByAccount(String account) {
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("account",account);
        return provider.queryUserByAccount(queryParams);
    }

    public void deleteById(String id) {
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("id",id);
        provider.findById(queryParams);
    }

    public void batchDeleteByIds(String[] ids) {
        provider.batchEditDelFlag(ids);
    }

    public boolean getUserByIdCard(String idCard) {
        TtsScltxxcjUser user = new TtsScltxxcjUser();
        user = provider.getUserByIdCard(idCard);
        return user==null?false:true;
    }

    public TtsScltxxcjUser getUserByTokenAccount(String account) {
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("account",account);
        return provider.getUserByTokenAccount(queryParams);
    }

    @Transactional
    public boolean buildUser(TtsScltxxcjUser ttsScltxxcjUser, String id, String token) {
        try {
            SysUserBean sysUser = new SysUserBean();
            SysUser systemUser = new SysUser();
            sysUser.setUserName(ttsScltxxcjUser.getName());
            //获取当前登录用户信息
            TtsScltxxcjRegiter user = ssoLoginService.getEntityByRedis(id.trim());

            List<String> industryTypes = new ArrayList<>();
            String indus = user.getEntityIndustry();
            String[] ids = indus.split(",");
            for(int i = 0;i < ids.length;i++){
                SysDictData industry = sysDictProvider.getDictDataById(ids[i]);
                String industryValue = industry.getDictValue().toString().trim();
                industryTypes.add(industryValue);
            }
            //此处用于判断是否勾选了屠宰场，如前端名称发生变化，请相应改变包含字符串的判断
            boolean choiceAbattoir = false;
            String Propertys = user.getEntityPropertyName();
            if(Propertys.indexOf("屠宰场") != -1){
                choiceAbattoir = true;
            }
            SysDictData typeData = sysDictProvider.getDictDataById(user.getEntityScale());
            String type = typeData.getDictValue();

            Result result = new Result();
            if (!"".equals(type) && null != type) {
                if ("0".equals(type)) {//企业类型
                   result = sysUserProvider.addSubjUser(token,UserEnum.SUBJNORMAL,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.ENTERPRISE);
                } else if ("1".equals(type)) {//合作社
                    result = sysUserProvider.addSubjUser(token,UserEnum.SUBJNORMAL,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.COOPERATIVE);
                } else if ("2".equals(type)) {//农场
                    result = sysUserProvider.addSubjUser(token,UserEnum.SUBJNORMAL,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.FARM);
                } else if ("3".equals(type)) {//个人
                    result = sysUserProvider.addSubjUser(token,UserEnum.SUBJNORMAL,sysUser,industryTypes,choiceAbattoir, OrgFormEnum.PERSONAL);
                }
            }
            systemUser = (SysUser) result.getData();

            //[170512PB]获取并设置初始密码
            ttsScltxxcjUser.setPassword(systemUser.getReservedField2());

            if(!StringUtils.isNullEmpty(systemUser.getId())){
                ttsScltxxcjUser.setAccount(systemUser.getAccount());
                ttsScltxxcjUser.setIsMain("0");
            }else{
                return false;
            }
            if (user != null){
                //获取主体用户码
                String userEntityCode = sysCodeGeneratorProvider.getMainBodyUserCode
                        (IdGenerator.MainBodyCategories.MainBodyOfWokingAndManagement,
                        user.getCreditCode()==null||user.getCreditCode()==""?user.getLegalIdnumber():user.getCreditCode()
                        ,token);
                if(userEntityCode != null && !"".equals(userEntityCode)){
                    ttsScltxxcjUser.setUserIdcode(userEntityCode);
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("entityCode",user.getEntityIdcode());
                    ttsScltxxcjUsernumberService.updateUsernumByEntityCode(map);
                }else{
                    return false;
                }
            }else{
                return false;
            }
            ttsScltxxcjUser.setEntityIdcode(user.getEntityIdcode());
            //添加用户
            ttsScltxxcjUserService.add(ttsScltxxcjUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateByAcc(TtsScltxxcjUser ttsScltxxcjUser) {
        provider.updateByAcc(ttsScltxxcjUser);
    }

    public TtsScltxxcjUser selectUserById(String id){
        return provider.selectUserById(id);
    }

    public void updateUserInfoById(TtsScltxxcjUser ttsScltxxcjUser){provider.updateUserInfoById(ttsScltxxcjUser);}

    public boolean findUserByIdCard(String idCard, String account) {
        TtsScltxxcjUser user = new TtsScltxxcjUser();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("idCard",idCard);
        params.put("account",account);
        user = provider.findUserByIdCard(params);
        return user==null?false:true;

    }

    public void updateUserInfo(TtsScltxxcjUser ttsScltxxcjUser) {
        provider.updateUserInfo(ttsScltxxcjUser);
    }
}