package com.sofn.service.tts;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysUser;
import com.sofn.model.sys.SysUserBean;
import com.sofn.provider.sys.SysUserProvider;
import org.springframework.stereotype.Service;
import com.sofn.core.util.UserEnum;

/**
 * 操作子账号service
 */
@Service
public class SysCreateUserService extends BaseService<SysUserProvider,SysUser>{
    @DubboReference
    public void setSysDictProvider(SysUserProvider provider) {
        this.provider = provider;
    }

    /**
     * 重置用户密码
     * @param account
     * @param token
     * @return
     */
    public boolean resetPassword(String account,String token) {
        return this.provider.resetPwd(account, token);
    }
        /**
         * 生成用户子账号
         */
    public SysUser createUser(SysUserBean sysUser, String token ,UserEnum userEnum){
        // todo 报错
        return this.provider.addUser(sysUser,token,userEnum);
    }

    public void updatepassword(String pass, String acc) {
        provider.changePwdByAccount(pass,acc);
    }
}
