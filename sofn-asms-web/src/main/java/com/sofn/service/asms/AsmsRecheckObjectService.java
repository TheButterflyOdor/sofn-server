package com.sofn.service.asms;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.AsmsRecheckObject;
import com.sofn.provider.asms.AsmsRecheckObjectProvider;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AsmsRecheckObjectService extends BaseService<AsmsRecheckObjectProvider, AsmsRecheckObject> {
    @DubboReference
    public void setProvider(AsmsRecheckObjectProvider provider) {
        this.provider = provider;
    }

    public void addObj(AsmsRecheckObject asmsRecheckObject) {
        super.add(asmsRecheckObject);
    }

    public void delObj(String ids) {
        JSONArray jsonArray = JSONArray.parseArray(ids);
        for (Object id : jsonArray) {
            super.delete(id.toString());
        }

    }

    public PageInfo<AsmsRecheckObject> reCheckObjlist(AsmsRecheckObject asmsRecheckObject, int start, int length) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AsmsServiceBoth.buidPage(start, length));
        params.put("recheckTaskId", StringUtil.isNotBlank(asmsRecheckObject.getRecheckTaskId()) ? asmsRecheckObject.getRecheckTaskId() : "-1");
        params.put("recheckSampleName", StringUtil.isNotBlank(asmsRecheckObject.getRecheckSampleName()) ? "%" + asmsRecheckObject.getRecheckSampleName() + "%" : null);
        params.put("recheckSampleCode", StringUtil.isNotBlank(asmsRecheckObject.getRecheckSampleCode()) ? "%" + asmsRecheckObject.getRecheckSampleCode() + "%" : null);
        return provider.reCheckObjlist(params);
    }
}
