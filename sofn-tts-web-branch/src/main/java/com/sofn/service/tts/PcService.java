package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjScgl;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批次码查询追溯入口
 * Created by Administrator on 2016/11/17.
 */

@Service
public class PcService {


    @Autowired
    public TtsScltxxcjScglService ttsScltxxcjScglService;

    @Autowired
    public TtsScltxxcjCppchcService ttsScltxxcjCppchcService;

    @Autowired
    public TtsScltxxcjXsdjService ttsScltxxcjXsdjService;

    @Autowired
    public TtsScltxxcjXcdjjlService ttsScltxxcjXcdjjlService;

    @Autowired
    public SSOLoginService sSOLoginService;


    public void saveSearchData(String pc,String userId)
    {
        ttsScltxxcjScglService.saveSearchData(pc, userId);
    }

    /**
     * 通过产品登记批次码查询本级信息
     * @param pc
     * @return
     */
    public List<Map<String,Object>> getBaseForPc(String pc,String entityId){
        List<Map<String,Object>> result = ttsScltxxcjScglService.getBaseForPc(pc,entityId);
        return result;
    }


    /**
     * 通过批次码查询来源信息 --查询上级信息
     * @param pc
     * @param status 当前状态(1未卖出2未卖完3已卖完4已合成)
     * @param billStatus 单据状态(0初始1合成2合并)
     * @param toZsm
     * @return
     */
    public List<Map<String,Object>> getTopForPc(String pc,String status,String billStatus,String toZsm){
        List<Map<String,Object>> pageInfo = null;
        //表示产品来源是收获 产品来源是收获，则无对应的上级来源，所以这里是无信息
        if(StringUtils.isBlank(toZsm)){
            pageInfo = new ArrayList<Map<String,Object>>();
        } else {
        //表示产品来源是采购
            pageInfo = ttsScltxxcjXcdjjlService.getPcToUpInfo(toZsm);
        }
        return pageInfo;
    }


    /**
     * 产品是是否是合成，如果是合成返回合成前产品信息  --查询上级信息（合成的情况）
     * @param pc
     * @return
     */
    public List<Map<String,Object>> getIfhc(String pc){
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("pc",pc);
        return ttsScltxxcjCppchcService.getInfoForNewPc(queryParams);
    }


    /**
     * 下游批次追溯信息
     * @param pc
     * @return
     */
    public List<Map<String,Object>> getInfoforpc(String pc,String entityId){
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("pc",pc);
        queryParams.put("entityId",entityId);
        return ttsScltxxcjXcdjjlService.getInfoforpc(queryParams);
    }


    /**
     * 合成流向
     * @param pc
     * @return
     */
    public List<Map<String,Object>> getHclx(String pc){
        Map<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("pc",pc);
        return ttsScltxxcjXcdjjlService.getHclx(queryParams);
    }

}
