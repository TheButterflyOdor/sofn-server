package com.sofn.provider.ales;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.ales.AlesWtTaskMonitorExpandMapper;
import com.sofn.model.generator.AlesWtTaskMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Administrator
 * @Time 2018-2-28 14:49:01
 */
@DubboService(interfaceClass = AlesWtTaskMonitorProvider.class)
@CacheConfig(cacheNames = "AlesWtTaskMonitor")
public class AlesWtTaskMonitorProviderImpl extends BaseProviderImpl<AlesWtTaskMonitor> implements AlesWtTaskMonitorProvider {
    @Autowired
    private AlesWtTaskMonitorExpandMapper ExpandMapper;

    /**
     *
     * @param params
     * @return
     */
    @Override
    public PageInfo<AlesWtTaskMonitor> pages(Map<String, Object> params) {
        PageInfo page = new PageInfo();
        List<AlesWtTaskMonitor> list = ExpandMapper.getPagesList(params);
        long count = ExpandMapper.getPageCount(params);
        /*String id = params.get("organId").toString();
        logger.info("====id"+id);
        Map map = new HashMap();
        map.put("taskId",id);
        List<Map<String, Object>> list1 = ExpandMapper.getReportStatus(map);
        *//* 增加监测系统状态判断 start (2018年2月10日10:14:42)*//*
        int k = 0;
        boolean flag = false;
        if(list1!=null&&list1.size()>0){
            for(Map<String, Object> m : list1){
                String status = m.get("CHECK_REPORT").toString();
                if("1".equals(status)){
                    k++;
                }
            }
            if(k==list1.size()){
                  flag = true;
            }
        }
        for(AlesWtTaskMonitor monitor : list){
            if(flag){
                monitor.setStatus("1");
            }
        }
        *//*增加监测系统状态判断 end*/
        page.setList(list);
        page.setTotal(count);
        return page;
    }

    /**
     *
     * @param alesWtTaskMonitor
     * @return
     */
    @Override
    public List<AlesWtTaskMonitor> getListByTaskId(AlesWtTaskMonitor alesWtTaskMonitor) {
        return ExpandMapper.getListByTaskId(alesWtTaskMonitor);
    }

}
