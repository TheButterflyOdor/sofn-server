package com.sofn.service.tts;

import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysUser;
import com.sofn.provider.sys.SysUserProvider;
import org.springframework.stereotype.Service;

/**
 * Created by czx on 2016/12/15.z
 */
@Service
public class TtsForgetPasswordService extends BaseService<SysUserProvider,SysUser> {
    @DubboReference
    public void setSysDictProvider(SysUserProvider provider) {
        this.provider = provider;
    }


    public boolean updatepassword(String acc, String pass) {
        return provider.editPasswordByAccount(acc,pass);
    }
}
