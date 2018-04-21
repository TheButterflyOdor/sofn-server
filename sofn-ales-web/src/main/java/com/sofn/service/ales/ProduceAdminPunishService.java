package com.sofn.service.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.*;
import com.sofn.provider.ales.ProduceAdminPunishProvider;
import com.sofn.provider.asms.AsmsSubjEntBadrecordProvider;
import com.sofn.provider.tts.TtsScltxxcjNotificationProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import com.sofn.util.Page;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/21.
 */
@Service
public class ProduceAdminPunishService extends BaseService<ProduceAdminPunishProvider,AlesProduceAdminPunish> {
    private  ProduceAdminPunishProvider produceAdminPunishProvider;
    @DubboReference
    public void setProduceAdminPunishProvider(ProduceAdminPunishProvider produceAdminPunishProvider){
        this.produceAdminPunishProvider = produceAdminPunishProvider;
    }
    private AsmsSubjEntBadrecordProvider asmsSubjEntBadrecordProvider;
    @DubboReference
    public void setAsmsSubjEntBadrecordProvider(AsmsSubjEntBadrecordProvider provider){
        asmsSubjEntBadrecordProvider = provider;
    }
    private TtsScltxxcjRegiterProvider ttsScltxxcjRegiterProvider;
    @DubboReference
    public void setTtsScltxxcjRegiterProvider(TtsScltxxcjRegiterProvider provider){ ttsScltxxcjRegiterProvider = provider;
    }
    private AsmsSubjEntBadrecordProvider subjEntBadrecordProvider;
    @DubboReference
    public void setEntBadRecordProvider(AsmsSubjEntBadrecordProvider provider){
        subjEntBadrecordProvider = provider;
    }

    private TtsScltxxcjNotificationProvider ttsScltxxcjNotificationProvider;
    @DubboReference
    public void setProvider(TtsScltxxcjNotificationProvider ttsScltxxcjNotificationProvider) {
        this.ttsScltxxcjNotificationProvider = ttsScltxxcjNotificationProvider;
    }

    /**
     * 获取生产管理行政惩罚清单
     *设置Map查询参数集
     * @param aisProduceAdminPunish
     * @param taskYear
     * @param dateBegin
     * @param area
     * @param dateEnd
     * @param pageNum
     * @param pageSize
     * @param queryCon
     * @param createOrgId
     * @return
     */
    public PageInfo getProduceAdminPunishList(AlesProduceAdminPunish aisProduceAdminPunish, String taskYear,
        String dateBegin,String area,String dateEnd, int pageNum, int pageSize, String queryCon,String createOrgId){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("taskYear",taskYear);
        queryParams.put("area",area);
        queryParams.put("dateBegin",dateBegin);
        queryParams.put("dateEnd",dateEnd);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        queryParams.put("queryCon",queryCon);
        queryParams.put("createOrgId",createOrgId);
        PageInfo pageInfo = produceAdminPunishProvider.getAisProduceAdminPunishList(queryParams);
        return pageInfo;

    }

    public int addProduceAdminPunish(String token,AlesProduceAdminPunish aisProduceAdminPunish){
        CurrentUser user = WebUtil.getCurrentUser(token);
        if(StringUtils.isNullEmpty(aisProduceAdminPunish.getId())){
            aisProduceAdminPunish.setId(StringUtils.getUUIDString().toString().replace("-", ""));
            aisProduceAdminPunish.setDelFlag("N");
            aisProduceAdminPunish.setCreateBy(user.getId());
            aisProduceAdminPunish.setCreateTime(new Date());
            aisProduceAdminPunish.setEnforceLawTime(new Date());
            this.addSubjEntBadrecord(aisProduceAdminPunish);

            TtsScltxxcjNotification ttsScltxxcjNotification = new TtsScltxxcjNotification();
            ttsScltxxcjNotification.setIsread("N");
            ttsScltxxcjNotification.setTitle("行政处罚通知");
            String content = "【"+aisProduceAdminPunish.getEnforceLawId()+"】（"+aisProduceAdminPunish.getEnforceLawPeople()+"）在【"+ DateUtil.formatDate(new Date(),"yyyy-MM-dd")+"】对主体进行处罚，处罚决定为【"+aisProduceAdminPunish.getPunishResult()+"】";
            ttsScltxxcjNotification.setContent(content);
            ttsScltxxcjNotification.setFromId(user.getId());//当前登录账户id
            ttsScltxxcjNotification.setFromName(user.getUserName());//当前登录用户名
            ttsScltxxcjNotification.setToId(aisProduceAdminPunish.getEnterpriseCode());
            ttsScltxxcjNotification.setToName(aisProduceAdminPunish.getEnterpriseName());//接收消息主体名称
            ttsScltxxcjNotification.setCreateBy(user.getUserName());//当前登录用户名
            ttsScltxxcjNotification.setCreateTime(new Date());
            ttsScltxxcjNotificationProvider.update(ttsScltxxcjNotification);

            return produceAdminPunishProvider.addProduceAdminPunish(aisProduceAdminPunish);
        }else{
//            aisProduceAdminPunish.setEnforceLawTime(new Date());
            aisProduceAdminPunish.setUpdateTime(new Date());
            aisProduceAdminPunish.setUpdateBy(WebUtil.getCurrentUser(token).getId());
            return provider.updateProduceAdminPunish(aisProduceAdminPunish);
        }

    }

    public AlesProduceAdminPunish findProduceAdminPunish(String id){
        return produceAdminPunishProvider.findProduceAdminPunishById(id);
    }
    //暂加
    public TtsScltxxcjRegiter findEnterpriseById(String entityIdCode){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("entityIdCode",entityIdCode);
        return ttsScltxxcjRegiterProvider.selectByEntityIdCode(params);
    }
    public PageInfo getAsmsSubjEntBadrecordByIdList(Page page, String enterpriseId){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("enterpriseId",enterpriseId);
        queryMap.put("pageOffset", page.getPageOffset());
        queryMap.put("pageTail",page.getPageTail());
        return asmsSubjEntBadrecordProvider.getAsmsSubjEntBadrecordByIdList(queryMap);
    }
    public int addSubjEntBadrecord(AlesProduceAdminPunish alesProduceAdminPunish){
        AsmsSubjEntBadrecord subjEntBadrecord = new AsmsSubjEntBadrecord();
        subjEntBadrecord.setId(StringUtils.getUUIDString());
        subjEntBadrecord.setDelFlag("N");
        subjEntBadrecord.setEnterpriseId(alesProduceAdminPunish.getEnterpriseId());
        subjEntBadrecord.setBadrecordContent(alesProduceAdminPunish.getPunishQualitative());
        subjEntBadrecord.setCreateBy(WebUtil.getCurrentUserId());
        subjEntBadrecord.setCreateTime(new Date());
        subjEntBadrecord.setSourceId(alesProduceAdminPunish.getId());
        subjEntBadrecord.setSourceType("2");
        subjEntBadrecord.setBadrecordFile(alesProduceAdminPunish.getPunishFiles());
        subjEntBadrecord.setBadrecordFileName(alesProduceAdminPunish.getPunishFilesName());
        return subjEntBadrecordProvider.addSubjEntBadrecord(subjEntBadrecord);
    }
    public AlesProduceAdminPunish getPunishById(String id){
        return  produceAdminPunishProvider.findPunishById(id);
    }

    /**
     * 根基Id删除行政处罚对象信息
     * @param id
     */
    public void deleteInfo(String id){
       produceAdminPunishProvider.detleteInfo(id);
    }

}
