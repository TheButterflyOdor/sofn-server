/**
 *
 */
package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.asms.AsmsCheckBearUnitExpandMapper;
import com.sofn.dao.asms.AsmsCheckTaskExpandMapper;
import com.sofn.dao.asms.AsmsMonitorObjectExpandMapper;
import com.sofn.dao.generator.*;
import com.sofn.model.generator.*;
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
@DubboService(interfaceClass = AsmsCheckTaskProvider.class)
@CacheConfig(cacheNames = "AsmsCheckTask")
public class AsmsCheckTaskProviderImpl extends BaseProviderImpl<AsmsCheckTask> implements AsmsCheckTaskProvider {

    @Autowired
    private AsmsCheckTaskExpandMapper expandMapper;
    @Autowired
    private AsmsCheckTaskMapper asmsCheckTaskMapper;
    @Autowired
    private AsmsCheckBearUnitMapper UnitMapper;//自动生成关联表mapp-牵头单位
    @Autowired
    private AsmsCheckBearUnitExpandMapper UnitExpandMapper;//扩展关联表mapp-牵头单位
    @Autowired
    private AsmsMonitorObjectMapper objMapper;//自动生成关联表mapp-抽查对象
    @Autowired
    private AsmsMonitorObjectExpandMapper objExpandMapper;//扩展关联表mapp-抽查对象
    @Autowired
    private AsmsRoutineMonitorMapper asmsRoutineMonitorMapper;//例行监测
    @Autowired
    private AsmsSpecialMonitorMapper asmsSpecialMonitorMapper;//专项监测
    @Autowired
    private AsmsRecheckTaskMapper asmsRecheckTaskMapper;//复检任务


    @Override
    public List<AsmsCheckTask> getTaskList(Map<String, Object> params) {
        return expandMapper.getTaskList(params);
    }

    @Override
    public List<Map<String, Object>> getCheckTaskByUser(Map<String, Object> params) {
        return expandMapper.getCheckTaskByUser(params);
    }

    @Override
    public List<Map<String, Object>> getNewCheckTaskByUser(Map<String, Object> params) {
        return expandMapper.getNewCheckTaskByUser(params);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> list(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getPagesList(params);
        long count = expandMapper.getPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<AsmsCheckTask>> listByOrg(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<AsmsCheckTask> list = expandMapper.getPagesListByOrg(params);
        long count = expandMapper.getPageCountByOrg(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getParentTaskList(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getParentTaskPagesList(params);
        long count = expandMapper.getParentTaskPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getMyTaskObjById(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = objExpandMapper.getMyTaskObjPagesList(params);
        long count = objExpandMapper.getMyTaskObjPageCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    @Transactional
    public int addGlInfo(AsmsMonitorObject o) {
        o.setId(StringUtils.getUUIDString());//赋值64位id
        return objMapper.insert(o);
    }

    @Override
    @Transactional
    public boolean delGlInfoByTaskId(String taskId) {
        AsmsMonitorObject t = new AsmsMonitorObject();
        t.setSuperviseCheckTaskId(taskId);
        return objExpandMapper.delByTaskId(t);
    }

    @Override
    @Transactional
    public int addBjGlInfo(AsmsCheckBearUnit o) {
        o.setId(StringUtils.getUUIDString());//赋值64位id
        o.setDelFlag("N");
        return UnitMapper.insert(o);
    }

    @Override
    @Transactional
    public boolean delBjGlInfoByTaskId(String taskId) {
        AsmsCheckBearUnit t = new AsmsCheckBearUnit();
        t.setSuperviseCheckTaskId(taskId);
        return UnitExpandMapper.delByTaskId(t);
    }

    @Override
    public List<Map<String, Object>> getObjById(String id) {
        return objExpandMapper.getObjById(id);
    }

    @Override
    public List<AsmsCheckBearUnit> getUnitByTaskId(String taskId) {
        AsmsCheckBearUnit u = new AsmsCheckBearUnit();
        u.setSuperviseCheckTaskId(taskId);
        return UnitExpandMapper.getUnitByTaskId(u);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getTaskListByADS(Map<String, Object> params, SysUser user) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getTaskListByADS(params);
        long count = expandMapper.getTaskListCountByADS(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int updateTaskIsSample(String keyId, String state) {
        AsmsCheckTask k = new AsmsCheckTask();
        k.setId(keyId);
        k.setIsSample(state);
        return expandMapper.updateIsSample(k);
    }

    @Override
    public boolean updateObjIsSample(String keyId, String state) {
        AsmsMonitorObject o = new AsmsMonitorObject();
        o.setId(keyId);
        o.setIsSample(state);
        return objExpandMapper.updateIsSample(o);
    }

    @Override
    public AsmsMonitorObject getMonitorObjectById(String id) {
        return objMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AsmsMonitorObject> getMonObjByTaskId(String taskId) {
        AsmsMonitorObject o = new AsmsMonitorObject();
        o.setId(taskId);
        return objExpandMapper.getMonObjByTaskId(o);
    }

    @Override
    public PageInfo<List<Map<String, Object>>> getHistoryCheckTask(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getHistoryCheckTaskList(params);
        long count = expandMapper.getHistoryCheckTaskCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<AsmsCheckTask> getTaskByCreateOrgIdAndParentTaskId(String parentTaskId, String CreateOrgId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentTaskId", parentTaskId);
        params.put("CreateOrgId", CreateOrgId);
        return expandMapper.getTaskByCreateOrgIdAndParentTaskId(params);
    }

    @Override
    public List<AsmsCheckTask> getTaskByParentTaskId(String parentTaskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("parentTaskId", parentTaskId);
        return expandMapper.getTaskByParentTaskId(params);
    }
    public Long findSampleCountByProductTraceabilityCode(Map<String, Object> params){
        return expandMapper.findSampleCountByProductTraceabilityCode(params);
    }

    @Override
    public AsmsCheckTask addAsmsCheckTask(AsmsCheckTask asmsCheckTask) {
        asmsCheckTaskMapper.insert(asmsCheckTask);
        return asmsCheckTask;
    }

    @Override
    public AsmsCheckTask updateAsmsCheckTask(AsmsCheckTask asmsCheckTask) {
        asmsCheckTaskMapper.updateByPrimaryKey(asmsCheckTask);
        return asmsCheckTask;
    }

    @Override
    public void updateAsmsTaskStateByIdAndType(String id, String type, String state) {
        type = type==null?"":type.trim();
        if("例行监测".equals(type)){
            AsmsRoutineMonitor asmsRoutineMonitor = asmsRoutineMonitorMapper.selectByPrimaryKey(id);
            asmsRoutineMonitor.setRmState(state);
            asmsRoutineMonitorMapper.updateByPrimaryKey(asmsRoutineMonitor);
        }else if("专项监测".equals(type)){
            AsmsSpecialMonitor asmsSpecialMonitor = asmsSpecialMonitorMapper.selectByPrimaryKey(id);
            asmsSpecialMonitor.setSmState(state);
            asmsSpecialMonitorMapper.updateByPrimaryKey(asmsSpecialMonitor);
        }else if("监督抽查".equals(type)){
            AsmsCheckTask asmsCheckTask = asmsCheckTaskMapper.selectByPrimaryKey(id);
            asmsCheckTask.setState(state);
            asmsCheckTaskMapper.updateByPrimaryKey(asmsCheckTask);
        }else if("复检任务".equals(type)){
            AsmsRecheckTask asmsRecheckTask = asmsRecheckTaskMapper.selectByPrimaryKey(id);
            asmsRecheckTask.setState(state);
            asmsRecheckTaskMapper.updateByPrimaryKey(asmsRecheckTask);
        }
    }
}
