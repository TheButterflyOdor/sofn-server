package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.DateUtil;
import com.sofn.model.generator.TtsScltxxcjRegiter;
import com.sofn.model.generator.TtsScltxxcjXcdjjl;
import com.sofn.model.generator.TtsScltxxcjXsdj;
import com.sofn.provider.tts.TtsScltxxcjXcdjjlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 销售记录 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsScltxxcjXcdjjlService extends BaseService<TtsScltxxcjXcdjjlProvider, TtsScltxxcjXcdjjl> {

    @DubboReference
    public void setTtsScltxxcjXcdjjlProvider(TtsScltxxcjXcdjjlProvider provider) {
        this.provider = provider;
    }

    @Autowired
    public SSOLoginService sSOLoginService;

    public PageInfo

    getPageInfo(TtsScltxxcjXcdjjl bean, int pageNum, int pageSize, String productXspc) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("productXspc", productXspc);
        return provider.getPageInfo(queryParams);
    }


    @Override
    public TtsScltxxcjXcdjjl add(TtsScltxxcjXcdjjl record) {
        //主体身份码和用户码
        record.setEntityIdcode(DateUtil.getDateTime("yyyyMMddhhmmss"));
        record.setUserIdcode(DateUtil.getDateTime("yyyyMMddhhmmss"));
        record.setSaleUser(DateUtil.getDateTime("yyyyMMddhhmmss"));
        //销售时间
        record.setSaleTime(new Date());
        //
        return super.add(record);
    }

    /**
     * 通过销售临时表数据，将数据填入销售明细
     *
     * @param ttsScltxxcjXsdj
     * @return
     */
    public int insertByTemp(TtsScltxxcjXsdj ttsScltxxcjXsdj) {

        return provider.insertByTemp(ttsScltxxcjXsdj);
    }

    /**
     * 修改销售明细的销售确认状态为已确认
     *
     * @param setId
     */
    public void updateXsmxStatus(Set<String> setId) {
        String[] ids = new String[setId.size()];
        ids = setId.toArray(ids);
        Map<String, Object> map = new HashMap<String, Object>();
        //销售登记id
        map.put("ids", ids);
        provider.updateXsmxStatus(map);
    }

    /**
     * 修改销售明细的销售确认状态为已确认  传入销售登记id
     *
     * @param xsdjId
     */
    public void updateXsmxStatus(String xsdjId) {
        String[] ids = new String[]{xsdjId};
        Map<String, Object> map = new HashMap<String, Object>();
        //销售登记id
        map.put("ids", ids);
        provider.updateXsmxStatus(map);
    }

    /**
     * 通过销售登记ID，将对应多条或单条销售明细标记为删除
     *
     * @param id
     */
    public void deleteXsmxFlag(String id) {

        provider.deleteXsmxFlag(id);
    }


    /**
     * 通过条件查询全部的销售明细数据
     *
     * @param map
     * @return
     */
    public List<TtsScltxxcjXcdjjl> queryByXsdjId(Map<String, Object> map) {
        List<TtsScltxxcjXcdjjl> list = new ArrayList<TtsScltxxcjXcdjjl>();
        list = provider.queryByXsdjId(map);
        return list;
    }


    /**
     * 通过销售明细数据
     * @param fromzsm
     * @param tozsm
     * @return
     */
    public List<Map<String,Object>> pageForZsm(String fromzsm, String tozsm) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("fromzsm", fromzsm);
        queryParams.put("tozsm", tozsm);
        return provider.pageForZsm(queryParams);
    }


    /**
     * 追溯查询，销售是本级
     * @param fromzsm
     * @param tozsm
     * @return
     */
    public List<Map<String,Object>> getXsbaseZsm(String fromzsm, String tozsm,String entityId) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("fromzsm", fromzsm);
        queryParams.put("tozsm", tozsm);
        queryParams.put("entityId", entityId);
        return provider.getXsbaseZsm(queryParams);
    }


    /**
     * 通过产品批次码查询对应的销售明细信息
     * @param pageNum
     * @param pageSize
     * @param pc
     * @return
     */
    public PageInfo<Map<String,Object>> getInfoforpc(int pageNum, int pageSize,String pc){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("pc", pc);
        return provider.getInfobypc(queryParams);
    }

    /**
     * 通过产品批次码查询对应的销售明细信息
     * @param zsm
     * @return
     */
    public List<Map<String,Object>> getPcToUpInfo(String zsm){
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("zsm", zsm);
        return provider.getPcToUpInfo(queryParams);
    }


    public List<Map<String,Object>> getInfoforpc(Map<String, Object> queryParams){
        return provider.getInfoforpc(queryParams);
    }


    public List<Map<String,Object>> getHclx(Map<String,Object> pc){

        return provider.getHclx(pc);
    }

    public PageInfo<TtsScltxxcjXcdjjl> getPageInfoPrint(TtsScltxxcjXcdjjl ttsScltxxcjXcdjjl, int pageNum, int pageSize,
                                                        String entityId,String shrq_q, String shrq_h, String keyWord
                                                        , String productSort,String confirmState,String isCirculates) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        queryParams.put("shrq_q", shrq_q);
        queryParams.put("shrq_h", shrq_h);
        queryParams.put("keyWord", keyWord);
        queryParams.put("productSort", productSort);
        queryParams.put("confirmState", confirmState);
        queryParams.put("isCirculates", isCirculates);
        TtsScltxxcjRegiter reg = sSOLoginService.getEntityByRedis(entityId);
        queryParams.put("entityIdcode", reg.getEntityIdcode());
        return provider.getPageInfoPrint(queryParams);
    }

    public List<Map<String,Object>> getProofByRraceProof(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.getProofByRraceProof(queryParams);
    }

    public List<Map<String,Object>> getPrintInfoForPc(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return provider.getPrintInfoForPc(queryParams);
    }

    public List<Map<String,Object>> getPrintForProof(String id,String code) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        queryParams.put("code", code);
        return provider.getPrintForProof(queryParams);
    }

    public TtsScltxxcjXcdjjl getRecordByXspc(String productXspc) {
        return provider.getRecordByXspc(productXspc);
    }
}