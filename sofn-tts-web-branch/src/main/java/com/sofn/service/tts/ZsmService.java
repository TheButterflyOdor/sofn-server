package com.sofn.service.tts;


import com.github.pagehelper.PageInfo;
import com.sofn.core.exception.DataParseException;
import com.sofn.model.generator.TtsScltxxcjCggl;
import com.sofn.model.generator.TtsScltxxcjCgglAndCustomer;
import com.sofn.model.generator.TtsScltxxcjXsdjAndCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 追溯码查询入口
 * Created by Administrator on 2016/11/7.
 */
@Service
public class ZsmService  {

    /**
     * 采购记录
     */
    @Autowired
    public TtsScltxxcjCgglService ttsScltxxcjCgglService;

    /**
     * 销售登记
     */
    @Autowired
    public TtsScltxxcjXsdjService ttsScltxxcjXsdjService;

    /**
     * 销售明细
     */
    @Autowired
    public TtsScltxxcjXcdjjlService ttsScltxxcjXcdjjlService;

    /**
     * 追溯码查询入口
     * @param zsm
     * @return
     */
    public List<TtsScltxxcjCgglAndCustomer> getBaseZsm(String zsm){
        //通过追溯码查询本级信息
        List<TtsScltxxcjCgglAndCustomer> vo = ttsScltxxcjCgglService.getCgglForZsm(zsm);
        if(null == vo){
            throw new DataParseException("当前追溯码，无对应的追溯信息");
        }
        return vo;
    }

    /**
     * 获取上游追溯信息
     * @param zsm
     * @return
     */
    public List<TtsScltxxcjXsdjAndCustomer> getUpZsm(String zsm){
        TtsScltxxcjXsdjAndCustomer vo = new TtsScltxxcjXsdjAndCustomer();
        //追溯上级销售信息
        List<TtsScltxxcjXsdjAndCustomer> list = ttsScltxxcjXsdjService.getXsdjForZsm(zsm,"");
        return list;
    }


    /**
     * 查询销售下游追溯信息
     * @param fromzsm
     * @param tozsm
     * @return
     */
    public List<Map<String,Object>> getDownZsm(String fromzsm,String tozsm){

        return ttsScltxxcjXcdjjlService.pageForZsm(fromzsm,tozsm);
    }


    /**
     * 本级为销售的时候，查询上游信息
     * @param zsm
     * @return
     */
    public List<Map<String,Object>> getXsUpZsm(String zsm){
        return ttsScltxxcjXsdjService.getXsUpZsm(zsm);
    }

    /**
     * 追溯查询，本级是销售
     * @param fromzsm
     * @param tozsm
     * @return
     */
    public List<Map<String,Object>> getXsbaseZsm(String fromzsm,String tozsm,String entityId){

        return ttsScltxxcjXcdjjlService.getXsbaseZsm(fromzsm,tozsm,entityId);
    }


}
