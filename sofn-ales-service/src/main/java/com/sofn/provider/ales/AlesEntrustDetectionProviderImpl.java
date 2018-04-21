/**
 *
 */
package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.ales.AlesEntrustDetectionExpandMapper;
import com.sofn.dao.ales.AlesEntrustSampleExpandMapper;
import com.sofn.dao.generator.AlesEntrustDetectionMapper;
import com.sofn.dao.generator.AlesEntrustSampleMapper;
import com.sofn.model.generator.AlesEntrustDetection;
import com.sofn.model.generator.AlesEntrustSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 * @version 2016年5月26日 上午9:1:0
 */
@DubboService(interfaceClass = AlesEntrustDetectionProvider.class)
public class AlesEntrustDetectionProviderImpl extends BaseProviderImpl<AlesEntrustDetection> implements AlesEntrustDetectionProvider {
    @Autowired
    private AlesEntrustDetectionMapper alesEntrustDetectionMapper;
    @Autowired
    private AlesEntrustDetectionExpandMapper expandMapper;
    @Autowired
    private AlesEntrustSampleMapper sampleMapper;//自动生成关联表mapp
    @Autowired
    private AlesEntrustSampleExpandMapper sampleExpandMapper;//扩展关联表mapp

    /**
     * 获取带分页集合
     * @param params
     * @return
     */
    @Override
    public PageInfo<List<Map<String, Object>>> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getPagesList(params);
        long count = expandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 添加委托检测任务
     *
     * @param alesEntrustDetection
     * @return
     */
    @Override
    @Transactional
    public int addAlesEntrustDetection(AlesEntrustDetection alesEntrustDetection) {
        return alesEntrustDetectionMapper.insert(alesEntrustDetection);
    }

    /**
     * 插入抽样单
     * @param o
     * @return
     */
    @Override
    @Transactional
    public int addGlInfo(AlesEntrustSample o) {
        o.setId(StringUtils.getUUIDString());//赋值64位id
        return sampleMapper.insert(o);
    }

    /**
     * 删除任务
     * @param taskId
     * @return
     */
    @Override
    @Transactional
    public boolean delGlInfoByTaskId(String taskId) {
        AlesEntrustSample t = new AlesEntrustSample();
        return sampleExpandMapper.delByTaskId(t);
    }

    /**
     * 根据id查询集合
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> getObjById(String id) {
        return sampleExpandMapper.getObjById(id);
    }

    /**
     * 更新
     * @param entrustDetectionTaskId
     * @param status
     * @return
     */
    @Override
    public boolean updateMonitorObjectStatus(String entrustDetectionTaskId, String status) {
        return expandMapper.updateMonitorObjectStatus(entrustDetectionTaskId,status);
    }

    /**
     * 更新
     * @param monitorObjectId
     * @param status
     * @return
     */
    @Override
    public boolean updateSampleStatus(String monitorObjectId, String status) {
        return expandMapper.updateSampleStatus(monitorObjectId,status);
    }
}
