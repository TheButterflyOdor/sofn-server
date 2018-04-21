package com.sofn.service.ads;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.UserAndSubj;
import com.sofn.model.generator.AsmsSubjSupervise;
import com.sofn.provider.asms.AsmsSubjSuperviseProvider;
import com.sofn.provider.sys.SysUserProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 忘记密码
 * Created by yangran on 2017/1/16 0016.
 */
@Service
public class AdsSubjSuperviseService extends BaseService<AsmsSubjSuperviseProvider,AsmsSubjSupervise> {

    @DubboReference
    public void setProvider(AsmsSubjSuperviseProvider provider){
        this.provider = provider;
    }

    private SysUserProvider sysUserProvider;
    @DubboReference
    public void setSysUserProvider(SysUserProvider sysUserProvider){
        this.sysUserProvider = sysUserProvider;
    }

    /** 根据条件获取用户信息 **/
    public List<Map<String,Object>> getUserByCondition(UserAndSubj userAndSubj){
        Map<String,Object> map = new HashMap();
        map.put("account", userAndSubj.getAccount());
        map.put("name", userAndSubj.getName());
        map.put("code", userAndSubj.getCode());
        map.put("level", userAndSubj.getLevel());
        map.put("areaId", userAndSubj.getAreaId());
        map.put("leader", userAndSubj.getLeader());
        List list = null;
        if(userAndSubj.getType().equals("0")) {
            list = provider.getSvUserList(map);
        }else if(userAndSubj.getType().equals("1")) {
            list = provider.getElUserList(map);
        }else if(userAndSubj.getType().equals("2")) {
            list = provider.getDtUserList(map);
        }
        return list;
    }

    /** 根据条件获取上级机构联系方式 **/
    public List<Map<String,Object>> getSuperiorList(UserAndSubj userAndSubj){
        Map<String,Object> map = new HashMap();
        map.put("name", userAndSubj.getName());
        map.put("code", userAndSubj.getCode());
        map.put("level", userAndSubj.getLevel());
        map.put("areaId", userAndSubj.getAreaId());
        map.put("superiorLevel", userAndSubj.getSuperiorLevel());
        map.put("superiorAreaId", userAndSubj.getSuperiorAreaId());
        List list = null;
        if(userAndSubj.getType().equals("0")) {
            list = provider.getSuperiorSvList(map);
        }else if(userAndSubj.getType().equals("1")) {
            list = provider.getSuperiorElList(map);
        }else if(userAndSubj.getType().equals("2")) {
            list = provider.getSuperiorDtList(map);
        }
        return list;
    }

    /** 根据条件重置密码 **/
    public boolean resetPwd(String account, String newPwd){
        return sysUserProvider.changePwdByAccount(account,newPwd);
    }

}
