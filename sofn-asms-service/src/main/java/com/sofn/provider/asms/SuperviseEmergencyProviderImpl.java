package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsEmergencyTaskExpandMapper;
import com.sofn.dao.generator.AsmsEmergencyTaskMapper;
import com.sofn.model.generator.AsmsEmergencyTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/27 11:21.
 */
@DubboService(interfaceClass = SuperviseEmergencyProvider.class)
public class SuperviseEmergencyProviderImpl extends BaseProviderImpl<AsmsEmergencyTask> implements SuperviseEmergencyProvider {
    @Autowired
    private AsmsEmergencyTaskExpandMapper asmsEmergencyTaskExpandMapper;
    @Autowired
    private AsmsEmergencyTaskMapper asmsEmergencyTaskMapper;

    public AsmsEmergencyTask findAsmsEmergencyTaskById(String id){
        return asmsEmergencyTaskMapper.selectByPrimaryKey(id);
    }

    public int updateAsmsEmergencyTask(AsmsEmergencyTask asmsEmergencyTask){
        return asmsEmergencyTaskMapper.updateByPrimaryKey(asmsEmergencyTask);
    }

    public int addAsmsEmergencyTask(AsmsEmergencyTask asmsEmergencyTask){
       return asmsEmergencyTaskMapper.insert(asmsEmergencyTask);
    }

    public PageInfo getSuperviseEmergencyList(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = asmsEmergencyTaskExpandMapper.getAsmsEmergencyTaskAllList(map);
        long count = asmsEmergencyTaskExpandMapper.getAsmsEmergencyTaskAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
}





