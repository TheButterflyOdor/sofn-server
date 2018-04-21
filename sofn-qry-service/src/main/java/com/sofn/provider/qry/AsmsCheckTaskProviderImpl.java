/**
 *
 */
package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.AsmsCheckBearUnitExpandMapper;
import com.sofn.dao.qry.AsmsCheckTaskExpandMapper;
import com.sofn.dao.qry.AsmsMonitorObjectExpandMapper;
import com.sofn.model.generator.AsmsCheckBearUnit;
import com.sofn.model.generator.AsmsCheckTask;
import com.sofn.model.generator.AsmsMonitorObject;
import com.sofn.model.generator.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

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
    private AsmsCheckBearUnitExpandMapper UnitExpandMapper;//扩展关联表mapp-牵头单位
  /*  @Autowired
    private AsmsMonitorObjectMapper objMapper;//自动生成关联表mapp-抽查对象*/
    @Autowired
    private AsmsMonitorObjectExpandMapper objExpandMapper;//扩展关联表mapp-抽查对象

    @Override
    public List<AsmsCheckTask> getTaskList(Map<String, Object> params) {
        return expandMapper.getTaskList(params);
    }


    @Override
    public List<Map<String, Object>> getCheckTaskByUser(Map<String, Object> params) {
        return expandMapper.getCheckTaskByUser(params);
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
        //TODO 用户权限判断
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = expandMapper.getTaskListByADS(params);
        long count = expandMapper.getTaskListCountByADS(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

   /* @Override
    public AsmsMonitorObject getMonitorObjectById(String id) {
        return objMapper.selectByPrimaryKey(id);
    }*/

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
        params.put("parentTaskId",parentTaskId);
        params.put("CreateOrgId",CreateOrgId);
        return expandMapper.getTaskByCreateOrgIdAndParentTaskId(params);
    }

    @Override
    public List<AsmsCheckTask> getCheckTaskList(Map<String, Object> params) {
        //调用mapper
        return expandMapper.getCheckTaskList(params);
    }
}
