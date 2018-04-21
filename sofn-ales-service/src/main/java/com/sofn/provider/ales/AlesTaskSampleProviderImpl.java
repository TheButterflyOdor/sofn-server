/**
 *
 */
package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesTaskSampleExpandMapper;
import com.sofn.dao.ales.AlesWtObjectCriterionExpandMapper;
import com.sofn.dao.generator.AlesWtTaskMonitorMapper;
import com.sofn.model.generator.AlesTaskSample;
import com.sofn.model.generator.AlesWtObjectCriterion;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AlesTaskSampleProvider.class)
@CacheConfig(cacheNames = "AlesTaskSample")
public class AlesTaskSampleProviderImpl extends BaseProviderImpl<AlesTaskSample> implements AlesTaskSampleProvider {

    @Autowired
    private AlesTaskSampleExpandMapper expandMapper;
    @Autowired
    private AlesWtTaskMonitorMapper monitorMapper;
    @Autowired
    private AlesWtObjectCriterionExpandMapper criterionExpandMapper;

    /**
     * 查询带分页集合
     * @param params
     * @return
     */
    @Override
    public PageInfo<AlesTaskSample> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getPagesList(params);
        long count = expandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 根据id查询抽验单
     * @param jcxxId
     * @return
     */
    @Override
    public List<AlesTaskSample> getSampleListByjcxxId(String jcxxId) {
        AlesTaskSample a = new AlesTaskSample();
        a.setOrganTaskId(jcxxId);
        List<AlesTaskSample> list = expandMapper.getSampleListByjcxxId(a);
        return list;
    }

    /**
     * 根据id查询抽验单
     * @param jcxxId
     * @param code
     * @return
     */
    @Override
    public List<AlesTaskSample> getSampleListByjcxxIds(String jcxxId,String code) {
        AlesTaskSample a = new AlesTaskSample();
        a.setOrganTaskId(jcxxId);
        a.setSampleCode(code);
        List<AlesTaskSample> list = expandMapper.getSampleListByjcxxIds(a);
        return list;
    }

    /**
     *
     * @param jcxxId
     * @param state
     * @return
     */
    @Override
    @Transactional
    public boolean syncIsTrue(String jcxxId, String state) {
        AlesTaskSample a = new AlesTaskSample();
        a.setOrganTaskId(jcxxId);
        a.setIsSync(state);
        boolean x = expandMapper.syncIsTrue(a);
        return x;
    }

    /**
     *
     * @param jcxxId
     * @param state
     * @param code
     * @return
     */
    @Override
    @Transactional
    public boolean sampleUpdate(String jcxxId, String state, String code,String id) {
        AlesTaskSample alesTaskSample = new AlesTaskSample();
        alesTaskSample.setOrganTaskId(jcxxId);
        alesTaskSample.setIsSync(state);
        alesTaskSample.setSampleCode(code);
        alesTaskSample.setId(id);
        boolean b = expandMapper.sampleUpdate(alesTaskSample);
        return b;
    }

    /**
     * 根据样品编码查询
     * @param sampleCode
     * @return
     */
    @Override
    public AlesTaskSample getSampleBySampleCode(String sampleCode) {
        AlesTaskSample a = new AlesTaskSample();
        a.setSampleCode(sampleCode);
        return expandMapper.getSampleBySampleCode(a);
    }
    @Override
    public  AlesTaskSample getLocaloleSampleBySampleCode(String sampleCode,String id){
        AlesTaskSample a = new AlesTaskSample();
        a.setSampleCode(sampleCode);
        a.setId(id);
        return expandMapper.getLocalSoleSampleBySampleCode(a);
    }
    /**
     * 根据样品编码查询
     * @param sampleCode
     * @return
     */
    @Override
    public AlesTaskSample getLocalSampleBySampleCode(String sampleCode) {
        AlesTaskSample a = new AlesTaskSample();
        a.setSampleCode(sampleCode);
        return expandMapper.getLocalSampleBySampleCode(a);
    }

    /**
     * 根据参数查询带分页集合
     * @param params
     * @return
     */
    @Override
    public PageInfo<List<Map<String, Object>>> getSampleListByObjIds(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getSampleListByTaskIdPagesList(params);
        long count = expandMapper.getSampleListByTaskIdPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 根据参数获取样品信息
     * @param id
     * @param result
     * @return
     */
    @Override
    @Transactional
    public boolean sampleResult(String id, String result) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("result", result);
        return expandMapper.sampleResult(params);
    }

    /**
     * 根据样品编码获取检测对象
     * @param sampleCode
     * @return
     */
    @Override
    public List<AlesWtObjectCriterion> getCriBySampleCode(String sampleCode) {
//        AlesTaskSample a = new AlesTaskSample();
//        a.setSampleCode(sampleCode);
//        AlesTaskSample sample = expandMapper.getSampleBySampleCode(a);
//        AlesWtTaskMonitor alesWtTaskMonitor=monitorMapper.selectByPrimaryKey(sample.getOrganTaskId());
        Map<String, Object> params= new<String, Object> HashedMap();
        params.put("sampleCode",sampleCode);
        List<AlesWtObjectCriterion> alesWtObjectCriterions = criterionExpandMapper.queryByTaskId(params);
        return alesWtObjectCriterions;
    }

    @Override
    public long judgeisSync(String organTaskId){
        AlesTaskSample a = new AlesTaskSample();
        a.setOrganTaskId(organTaskId);
        long  ale = expandMapper.judgeisSync(a); //未上报
        long  alm = expandMapper.judgeisSyncs(a);// 已上报
        if(ale > 0 && alm > 0){
            return 2;
        }else if(ale > 0){
            return 0;
        }else if(alm >0){
            return 1;
        }
        return 0;
    }
  /*  public String judgeisSyncs(String organTaskId){
        AlesTaskSample a = new AlesTaskSample();
        a.setOrganTaskId(organTaskId);
       // List<AlesTaskSample> ale = expandMapper.judgeisSync(a);
        String b = null;
        String c = null;
        String d = null;
        for(AlesTaskSample list: ale){
           if("0".equals(list.getIsSync())) {
            return b ="0";
           }else if("1".equals(list.getIsSync())){
               return b ="1";
           }
        }
        return null ;
    }
*/

    public Long countSubjectinfoTraceabilityCode(Map<String, Object> params) {
        long count = expandMapper.countSubjectinfoTraceabilityCode(params);
        return count;
    }
}
