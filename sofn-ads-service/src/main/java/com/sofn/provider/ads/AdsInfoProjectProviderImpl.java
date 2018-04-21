package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsInfoProjectMapper;
import com.sofn.dao.ads.AdsInfoProjectExpandMapper;
import com.sofn.model.generator.AdsInfoProject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	检测信息_检测项目 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsInfoProjectProvider.class)
public class AdsInfoProjectProviderImpl extends BaseProviderImpl<AdsInfoProject> implements AdsInfoProjectProvider {

    @Autowired
    private AdsInfoProjectExpandMapper AdsInfoProjectExpandMapper;

    public PageInfo<AdsInfoProject> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsInfoProjectExpandMapper.getPageInfo(map);
        long count = AdsInfoProjectExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<AdsInfoProject> getPageInfoWithCheckInfoId(Map<String, Object> map) {
        List<AdsInfoProject> list = AdsInfoProjectExpandMapper.getPageInfoWithCheckInfoId(map);
        return list;
    }

    public void updateResult(Map<String, Object> map) {
        AdsInfoProjectExpandMapper.updateResult(map);
    }

    public AdsInfoProject queryByProjectAndInfoId(Map<String, Object> map) {
        AdsInfoProject adsInfoProject = AdsInfoProjectExpandMapper.queryByProjectAndInfoId(map);
        return adsInfoProject;
    }

    public List<AdsInfoProject> queryByInfoId(Map<String, Object> map) {
        List<AdsInfoProject> adsInfoProjects = AdsInfoProjectExpandMapper.queryByInfoId(map);
        return adsInfoProjects;
    }

    public List<AdsInfoProject> queryListByInfoId(Map<String, Object> map) {
        List<AdsInfoProject> adsInfoProjects = AdsInfoProjectExpandMapper.queryListByInfoId(map);
        return adsInfoProjects;
    }

    public List<AdsInfoProject> getInfoForId(Map<String, Object> map) {
        List<AdsInfoProject> list = AdsInfoProjectExpandMapper.getPageInfoWithCheckInfoId(map);
        return list;
    }

    public void updateMax(Map<String, Object> map) {
        AdsInfoProjectExpandMapper.updateMax(map);
    }

}
