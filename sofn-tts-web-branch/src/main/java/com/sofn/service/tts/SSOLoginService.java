package com.sofn.service.tts;

import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.provider.sso.SSOLoginProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by dong4j on 16/9/13.
 * Description:
 */
@Service
public class SSOLoginService extends BaseService<SSOLoginProvider, CurrentUser> {

    @Autowired
    public TtsScltxxcjRegiterService ttsScltxxcjRegiterService;
    /**
     * Sets provider.
     *
     * @param provider the provider
     */
    @DubboReference
    public void setProvider(SSOLoginProvider provider) {
        this.provider = provider;
    }

    /**
     * Login map.
     *
     * @param account  the account
     * @param password the password
     * @return the map
     */
    public Map<String, Object> login(String account, String password) {
        return provider.login(account, password);
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


    /**
     * Gets sys user.
     * Cacheable 缓存测试 如果缓存中有数据 则不会走 db
     * 然后将数据放入缓存中
     * @param id the id
     * @return the sys user
     */
    @Cacheable(value = "default", key = "'queryById_id_'+#id")
    public CurrentUser getSysUser(String id) {
        logger.info("real query SysUser. {}" + id);
        return provider.queryById(id);
    }

    /**
     * Insert selective sys user.
     * CachePut 缓存测试 如果参数与缓存不一致,则更新数据库,并且更新缓存
     * @param user the user
     * @return the sys user
     */
    @CachePut(value="default",key="#user.getUserName()")
    public CurrentUser insertSelective(CurrentUser user) {
        logger.info("real insertSelective SysUser. {}" + user);
        return provider.update(user);
    }

    /**
     * Delete by primary key sys user.
     * 缓存测试
     * @param id   the id
     * @param type the type
     * @return the sys user
     */
    @CacheEvict(value="default",key="'delete_id_'+#id")
    public String deleteByPrimaryKey(String id, String type) {
        provider.delete(id,"admin");
        return "return cache";
    }

    /**
     * 事务测试
     */

    public Integer register(CurrentUser userModel){
        return provider.register(userModel);
    }

    /**
     * 通过ID从缓存redis中得到实体
     * @param id
     * @return
     */
    @Cacheable(value = "default", key = "'queryById_id_'+#id")
    public TtsScltxxcjRegiter getEntityByRedis(String id) {
////        String ids = "63bb76d31d9344d1b86435f2a7866d17";
//            TtsScltxxcjRegiter entity =  new TtsScltxxcjRegiter();
//            entity = (TtsScltxxcjRegiter) RedisUtil.get(id.trim());
//        if(null == entity){
//            entity = ttsScltxxcjRegiterService.queryById(id);
//            //缓存用户主体信息
//            RedisUtil.set(entity.getId().trim(), entity, 3600);
//        }
        logger.info("real query SysUser. {}" + id);
        TtsScltxxcjRegiter ttsScltxxcjRegiter = ttsScltxxcjRegiterService.queryById(id);
        if(null==ttsScltxxcjRegiter){
            ttsScltxxcjRegiter = ttsScltxxcjRegiterService.queryByUserId(id);
        }
        return ttsScltxxcjRegiter;
    }

    /**
     * 验证账号是否重复
     * @param account
     * @return
     */
    public Boolean repeatCheck(String account) {
        CurrentUser user = new CurrentUser();
        user.setAccount(account);
        return provider.repeatCheck(user);
    }



}
