package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.exception.DataParseException;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjRegiterAndUser;
import com.sofn.provider.sys.SysUserProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 注册主体信息表 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjRegiterService extends BaseService<TtsScltxxcjRegiterProvider, TtsScltxxcjRegiter> {

    @DubboReference
    public void setTtsScltxxcjRegiterProvider(TtsScltxxcjRegiterProvider provider) {
        this.provider = provider;
    }

    private SysUserProvider userProvider;

    @DubboReference
    public void setUserProvider(SysUserProvider userProvider){
        this.userProvider = userProvider;
    }





    public PageInfo getPageInfo(TtsScltxxcjRegiter bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }
    /**
     * 申请注册备案
     * @param ttsScltxxcjRegiter
     * @return
     */
    public int applyRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter) throws Exception {
        return provider.applyRecord(ttsScltxxcjRegiter);
    }
    /**
     * 注册审核未通过主体申请注册备案
     * @param ttsScltxxcjRegiter
     * @return
     */
    public int updateRecord(TtsScltxxcjRegiter ttsScltxxcjRegiter) throws Exception {
        return provider.updateRecord(ttsScltxxcjRegiter);
    }

    /**
     * 通过entityIdCode查询实体对象
     * @param map
     * @return
     */
    public TtsScltxxcjRegiter selectByEntityIdCode(Map<String,Object> map) {
        return provider.selectByEntityIdCode(map);
    }
    /**
     * 通过userIdCode查询实体对象
     * @param userIdcode
     * @return
     */
    public TtsScltxxcjRegiter selectByUserIdCode(String userIdcode) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userIdCode",userIdcode);
        return provider.selectByUserIdCode(map);
    }
    /**
     * 检查注册信息的唯一性
     * @param map
     * @return
     */
    public List<String> queryByInfounique(Map<String,Object> map) {
        return provider.queryByInfounique(map);
    }
    /**
     * 检查法人省份证是否存在
     * @param map
     * @return
     */
    public Map<String,Object> queryByIdCode(Map<String,Object> map) {
        return provider.queryByIdCode(map);
    }
    /**
     * 通过ACCOUNT查询对象
     * @param map
     * @return
     */
    public TtsScltxxcjRegiter selectByAccount(Map<String,Object> map) {
        return provider.selectByAccount(map);
    }
    /**
     * 获取注册号
     * map参数为
     * id 或者
     * userIdCode,entityIdCode
     * @param map
     * @return
     */
    public Map<String,Object> getRegisterNum(Map<String,Object> map){
        return  provider.getRegisterNum(map);
    };

    /**
     * 根据主体身份码获取主体信息
     * @param code
     * @param userType
     * @return
     */
    public TtsScltxxcjRegiter getSubjectInfo(String userType,String code) {
        TtsScltxxcjRegiter vo = new TtsScltxxcjRegiter();
        if(!userType.equals("4")){
            vo = provider.getInfoForBody(code);
            return vo;
        }else if(userType.equals("4")){
            throw new DataParseException("无");
        }
        return vo;
    }
    /**
     * 根据主体用户码获取主体信息
     * @param code
     * @param userType
     * @return
     */
    public TtsScltxxcjRegiter getInfoByUserId(String userType, String code) {
        TtsScltxxcjRegiter vo = new TtsScltxxcjRegiter();
        if(!userType.equals("4")){
            vo = provider.getInfoForUser(code);
            return vo;
        }else if(userType.equals("4")){
            throw new DataParseException("无");
        }
        return vo;
    }

    public TtsScltxxcjRegiter queryByUserName(String account) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("account",account);
        return provider.selectByAccount(map);
    }

    public TtsScltxxcjRegiter getInfoByUserInfo(String entityIdcode, String userIdcode) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("entityIdcode",entityIdcode);
        map.put("userIdcode",userIdcode);
        return provider.getInfoByUserInfo(map);
    }

    /**
     * 根据userid查询主体信息
     */
    public TtsScltxxcjRegiter queryByUserId(String id){
        return provider.queryByUserId(id);
    }

    /**
     * 根据userIdcode查询主体信息
     */
    public TtsScltxxcjRegiterAndUser queryByUserIdCode(String id){
        return provider.queryByUserIdCode(id);
    }


    public TtsScltxxcjRegiter getMainByUserIdCode(String entityIdCode) {
        return provider.getMainByUserIdCode(entityIdCode);
    }

    public TtsScltxxcjRegiter getUserInfoByRegiter(TtsScltxxcjRegiter ttsScltxxcjRegiter) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ttsScltxxcjRegiter",ttsScltxxcjRegiter);
        return provider.getUserInfoByRegiter(map);
    }

    public void updateByFirm(TtsScltxxcjRegiter scltxxcjRegiter) {
        provider.updateByFirm(scltxxcjRegiter);
    }


    public TtsScltxxcjRegiter getEntityByCode(String code) {
        return provider.getEntityByCode(code);
    }

    public TtsScltxxcjRegiter findInfoByCreditCode(Map<String, Object> params) {
        return provider.findInfoByCreditCode(params);
    }

    public TtsScltxxcjRegiter findByAccount(Map<String, Object> map) {
        return provider.findByAccount(map);
    }

    public TtsScltxxcjRegiter queryEntityInfoByEntity(String sourceEntity) {
        return provider.queryEntityInfoByEntity(sourceEntity);
    }

    public TtsScltxxcjRegiter findInfoByEntityCode(String entityCode) {
        return provider.findInfoByEntityCode(entityCode);
    }
}