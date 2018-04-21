package com.sofn.service.sso;


import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.provider.sso.SSOLoginProvider;
import org.springframework.stereotype.Service;

import java.util.Map;




@Service
public class SSOAppService extends BaseService<SSOLoginProvider,CurrentUser> {


    @DubboReference
    public void setSSOLoginProvider(SSOLoginProvider provider){
        this.provider = provider;
    }



    /**
     * Login map.
     *
     * @param account  the account
     * @param password the password
     * @return the map
     */
    public Map<String, Object> loginApp(String account, String password) {
        return provider.loginApp(account, password);
    }

    /**
     * Logout.
     *
     * @param token the token
     * @param type  the type
     */
    public void logout(String token, String type) {
        provider.logout(token, type);
    }


}
