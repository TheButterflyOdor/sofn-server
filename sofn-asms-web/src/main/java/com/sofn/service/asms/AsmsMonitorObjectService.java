package com.sofn.service.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.AsmsMonitorObject;
import com.sofn.provider.asms.AsmsMonitorObjectProvider;
import com.sofn.provider.sys.SysArgiProductProvider;
import com.sofn.web.asms.AsmsEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsMonitorObjectService extends BaseService<AsmsMonitorObjectProvider, AsmsMonitorObject> {

    private SysArgiProductProvider productProvider;

    @DubboReference
    public void setProvider(AsmsMonitorObjectProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(SysArgiProductProvider productProvider) {
        this.productProvider = productProvider;
    }

    /**
     * 获取产品列表
     */
    public PageInfo<Map<String, Object>> getProList(String Industry, QueryParameter queryParameter, String keyWord) {
        return productProvider.queryByNameOrAlias(Industry, keyWord, ((queryParameter.getStart() + 1) / queryParameter.getLength()), queryParameter.getLength());
    }

    /**
     * 监督抽查任务-监测对象列表
     */
    public PageInfo<List<Map<String, Object>>> objList(AsmsMonitorObject asmsMonitorObject, QueryParameter queryParameter) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("taskId", StringUtil.isNotBlank(asmsMonitorObject.getSuperviseCheckTaskId()) ? asmsMonitorObject.getSuperviseCheckTaskId() : "-1");
        PageInfo<List<Map<String, Object>>> pageInfo = provider.objList(params);
        return pageInfo;
    }

    /**
     * 新增监测对象
     */
    public AsmsMonitorObject addObj(AsmsMonitorObject asmsMonitorObject) {
        asmsMonitorObject.setIsSample(AsmsEnum.NON_SEPARATE.getCode());
        return super.add(asmsMonitorObject);
    }

    /**
     * 修改监测对象
     */
    public boolean updateObj(AsmsMonitorObject aAsmsMonitorObject) {
        AsmsMonitorObject asmsMonitorObject = super.queryById(aAsmsMonitorObject.getId());
        aAsmsMonitorObject.setEnable(asmsMonitorObject.getEnable());
        aAsmsMonitorObject.setDelFlag(asmsMonitorObject.getDelFlag());
        aAsmsMonitorObject.setCreateTime(asmsMonitorObject.getCreateTime());
        aAsmsMonitorObject.setCreateBy(asmsMonitorObject.getCreateBy());
        aAsmsMonitorObject.setUpdateBy(asmsMonitorObject.getUpdateBy());
        aAsmsMonitorObject.setUpdateTime(asmsMonitorObject.getUpdateTime());
        aAsmsMonitorObject.setIsSample(asmsMonitorObject.getIsSample());
        try {
            super.update(aAsmsMonitorObject);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除监测对象
     */
    public boolean delObj(String id) {
        try {
            super.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
