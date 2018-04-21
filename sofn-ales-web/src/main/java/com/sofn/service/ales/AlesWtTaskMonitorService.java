package com.sofn.service.ales;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.asms.QueryParameter;
import com.sofn.model.generator.*;
import com.sofn.provider.ales.AlesWtObjectCriterionProvider;
import com.sofn.provider.ales.AlesWtTaskEnterpriseProvider;
import com.sofn.provider.ales.AlesWtTaskMonitorProvider;
import com.sofn.provider.asms.AsmsSubjEntProvider;
import com.sofn.provider.sys.SysArgiProductProvider;
import com.sofn.provider.tts.TtsScltxxcjRegiterProvider;
import com.sofn.util.Page;
import com.sofn.web.ales.AlesEnum;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBing
 */
@Service
public class AlesWtTaskMonitorService extends BaseService<AlesWtTaskMonitorProvider, AlesWtTaskMonitor> {

    private SysArgiProductProvider productProvider;

    private TtsScltxxcjRegiterProvider regiterProvider;

    private AsmsSubjEntProvider tempProvider;

    @DubboReference
    public void setRegiterProvider(TtsScltxxcjRegiterProvider regiterProvider) {
        this.regiterProvider = regiterProvider;
    }
    @DubboReference
    public void setProvider(AsmsSubjEntProvider tempProvider){
        this.tempProvider = tempProvider;
    }

    @DubboReference
    public void setProvider(AlesWtTaskMonitorProvider provider) {
        this.provider = provider;
    }

    @DubboReference
    public void setProvider(SysArgiProductProvider productProvider) {
        this.productProvider = productProvider;
    }

    /**
     * 产品列表
     */
    public PageInfo<Map<String, Object>> productList(String industry, QueryParameter queryParameter, String keyWord) {
        return productProvider.queryByNameOrAlias(industry, keyWord, ((queryParameter.getStart() + 1) / queryParameter.getLength()), queryParameter.getLength());
    }

    /**
     * page
     */
    public PageInfo<AlesWtTaskMonitor> pages(AlesWtTaskMonitor alesWtTaskMonitor, QueryParameter queryParameter) {
        Map<String, Object> params = new HashMap<>();
        params.putAll(AlesServiceBoth.buidPage(queryParameter.getStart(), queryParameter.getLength()));
        params.put("taskId", StringUtil.isNotBlank(alesWtTaskMonitor.getSuperviseCheckTaskId()) ? alesWtTaskMonitor.getSuperviseCheckTaskId() : "-1");
        return provider.pages(params);
    }

    /**
     * 列表
     */
    public List<AlesWtTaskMonitor> getListByTaskId(AlesWtTaskMonitor alesWtTaskMonitor) {
        return provider.getListByTaskId(alesWtTaskMonitor);
    }

    /**
     * 新增
     */
    public AlesWtTaskMonitor addAlesWtTaskMonitor(AlesWtTaskMonitor alesWtTaskMonitor) {
        alesWtTaskMonitor.setIsSample(AlesEnum.NON_SAMPLE.getCode());
        return super.add(alesWtTaskMonitor);
    }

    /**
     * 修改
     */
    public void updateAlesWtTaskMonitor(AlesWtTaskMonitor alesWtTaskMonitor) {
        AlesWtTaskMonitor data = super.queryById(alesWtTaskMonitor.getId());
        alesWtTaskMonitor.setEnable(data.getEnable());
        alesWtTaskMonitor.setDelFlag(data.getDelFlag());
        alesWtTaskMonitor.setCreateTime(data.getCreateTime());
        alesWtTaskMonitor.setCreateBy(data.getCreateBy());
        alesWtTaskMonitor.setUpdateBy(data.getUpdateBy());
        alesWtTaskMonitor.setUpdateTime(data.getUpdateTime());
        alesWtTaskMonitor.setIsSample(data.getIsSample());
        super.update(alesWtTaskMonitor);
    }

    /**
     * 删除
     */
    public void deleteAlesWtTaskMonitor(String id) {
        JSONArray jsonArray = JSONArray.parseArray(id);
        for (Object object : jsonArray) {
            super.delete(object.toString());
        }
    }

    /**
     * 生产经营主体列表
     */
    public PageInfo<TtsScltxxcjRegiter> getSubjEntList(TtsScltxxcjRegiter entity, Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageOffset", page.getPageOffset());
        map.put("pageTail", page.getPageTail());
        map.put("badRecord", entity.getBadRecord());
        map.put("entityIndustry", entity.getEntityIndustry());
        map.put("entityType", entity.getEntityType());
        map.put("entityScale", entity.getEntityScale());
        map.put("enterpriseName", entity.getEnterpriseName());
        map.put("entityIdcode", entity.getEntityIdcode());
        map.put("area", entity.getArea());
        if (entity.getApproveStatus() != null && !"".equals(entity.getApproveStatus().trim())) {
            map.put("approveStatus", entity.getApproveStatus().split(","));//可用的生产经营主体
        }
        List list = regiterProvider.getSubList(map);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(list);
        pageInfo.setTotal(regiterProvider.getSubjEntTotal(map));
        return pageInfo;
    }

    /**
     * 临时主体列表
     * @param subjEntTemp
     * @param page
     * @return
     */
    public PageInfo<AsmsSubjEntTemp> getSubjTempEntList(AsmsSubjEntTemp subjEntTemp,Page page){
        Map<String,Object> map = new HashMap<>();
        map.put("pageOffset",page.getPageOffset());
        map.put("pageTail",page.getPageTail());
        map.put("entityName",subjEntTemp.getEntityName());
        map.put("areaId",subjEntTemp.getAreaId());
        return tempProvider.getSubjEntTempList(map);
    }
}
