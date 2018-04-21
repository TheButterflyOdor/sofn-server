package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.TtsScltxxcjUserChangeRecordMapper;
import com.sofn.dao.tts.TtsScltxxcjRegiterExpandMapper;
import com.sofn.dao.tts.TtsScltxxcjUserChangeRecordExpandMapper;
import com.sofn.model.generator.TtsScltxxcjUserChangeRecord;
import com.sofn.provider.sso.SSOLoginProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*	注册主体信息表变更记录表 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsScltxxcjUserChangeRecordProvider.class)
public class TtsScltxxcjUserChangeRecordProviderImpl extends BaseProviderImpl<TtsScltxxcjUserChangeRecord> implements TtsScltxxcjUserChangeRecordProvider {

    @Autowired
    private TtsScltxxcjUserChangeRecordMapper ttsScltxxcjUserChangeRecordMapper;
    @Autowired
    private TtsScltxxcjUserChangeRecordExpandMapper TtsScltxxcjUserChangeRecordExpandMapper;
    @Autowired
    private TtsScltxxcjRegiterExpandMapper ttsScltxxcjRegiterExpandMapper;
    @DubboReference
    private SSOLoginProvider ssoLoginProvider;

    public PageInfo<TtsScltxxcjUserChangeRecord> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        long count = 0;
        if(null != map.get("entityId") && StringUtils.isNotBlank(map.get("entityId").toString())){
            list = TtsScltxxcjUserChangeRecordExpandMapper.getPageInfo(map);
            count = TtsScltxxcjUserChangeRecordExpandMapper.getCount(map);
        }
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int insert(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("entityIdCode",ttsScltxxcjUserChangeRecord.getEntityIdcode());
        map1.put("delFlag","N");
        map1.put("approveStatus","5");
        ttsScltxxcjRegiterExpandMapper.updateEntityByIdcode(map1);
        return ttsScltxxcjUserChangeRecordMapper.insert(ttsScltxxcjUserChangeRecord);
    }

    @Override
    public int insertRe(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        return ttsScltxxcjUserChangeRecordMapper.insert(ttsScltxxcjUserChangeRecord);
    }

    @Override
    public int updateEntityByZX(TtsScltxxcjUserChangeRecord ttsScltxxcjUserChangeRecord) {
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(ttsScltxxcjUserChangeRecord.getApproveType())&&ttsScltxxcjUserChangeRecord.getApproveType().equals("1")){
            map.put("approveStatus","4");
        }else{
            map.put("approveStatus","3");
        }
        map.put("entityId",ttsScltxxcjUserChangeRecord.getEntityId());
        map.put("delFlag","N");
        return ttsScltxxcjRegiterExpandMapper.updateEntityByCX(map);
    }
    
    @Override
    public List<Map<String, Object>> getEntityUserChangeRecordPageInfoByCondition(Map<String, Object> map) {
        return TtsScltxxcjUserChangeRecordExpandMapper.getEntityUserChangeRecordPageInfoByCondition(map);
    }

    @Override
    public long getTotal(Map<String, Object> map) {
        return TtsScltxxcjUserChangeRecordExpandMapper.getCountList(map);
    }

    @Override
    public TtsScltxxcjUserChangeRecord selectById(String id) {
        return ttsScltxxcjUserChangeRecordMapper.selectByPrimaryKey(id);
    }
    @Override
    @Transactional
    public Integer updateEntityUserChangeRecordByMap(Map<String, Object> map) {
        try {
            if (!"".equals(map.get("approveType")) && null != map.get("approveType")) {
                Integer approveType = Integer.parseInt(map.get("approveType").toString());
                Map<String,Object> map1 = new HashMap<>();
                map1.put("entityId",map.get("entityId"));
                switch (approveType){
                    //注销
                    case 1:
                        if (!"".equals(map.get("approveStatus")) && null != map.get("approveStatus")) {
                            //如果是审批通过的话
                            if (map.get("approveStatus").equals("1"))  {
                                map.put("delFlag","Y");
                                ttsScltxxcjRegiterExpandMapper.updateEntityByCX(map);
//                                ssoLoginProvider.delUser(map.get("account").toString());
                            }else{
                                map1.put("delFlag","N");
                                map1.put("approveStatus","1");
                                ttsScltxxcjRegiterExpandMapper.updateEntityByCX(map1);
                            }
                        }
                        break;
                    //变更
                    case 2:
                        if (!"".equals(map.get("approveStatus")) && null != map.get("approveStatus")) {
                        if(map.get("approveStatus").equals("1")){
                            ttsScltxxcjRegiterExpandMapper.updateEntityByUpdate(map);
                        }
                            map1.put("delFlag","N");
                            map1.put("approveStatus","1");
                            ttsScltxxcjRegiterExpandMapper.updateEntityByCX(map1);
                        }
                        break;
                    //撤销
                    case 3:
                        //如果是审批通过的话
                        if (map.get("approveStatus").equals("1"))  {
                            map.put("delFlag","Y");
                            map.put("approveStatus","6");
                            ttsScltxxcjRegiterExpandMapper.updateEntityByCX(map);
//                            ssoLoginProvider.delUser(map.get("account").toString());
                        }else{
                            map1.put("delFlag","N");
                            map1.put("approveStatus","1");
                            ttsScltxxcjRegiterExpandMapper.updateEntityByCX(map1);
                        }
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TtsScltxxcjUserChangeRecordExpandMapper.updateEntityUserChangeRecordByMap(map);
    }

}
