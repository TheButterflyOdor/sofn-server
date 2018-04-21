package com.sofn.provider.ads;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.AdsFileMapper;
import com.sofn.dao.ads.AdsFileExpandMapper;
import com.sofn.model.generator.AdsFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
*	文件 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = AdsFileProvider.class)
public class AdsFileProviderImpl extends BaseProviderImpl<AdsFile> implements AdsFileProvider {

    @Autowired
    private AdsFileExpandMapper AdsFileExpandMapper;

    public PageInfo<AdsFile> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsFileExpandMapper.getPageInfo(map);
        long count = AdsFileExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public PageInfo<AdsFile> getDlReportPage(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsFileExpandMapper.getDlReportPage(map);
        long count = AdsFileExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<Map<String,Object>> getField() {
        List<Map<String,Object>> list = AdsFileExpandMapper.getField();
        return list;
    }
    public List<Map<String,Object>> getType() {
        List<Map<String,Object>> list = AdsFileExpandMapper.getType();
        return list;
    }


    public List<Map<String,Object>> getYear() {
        List<Map<String,Object>> list = AdsFileExpandMapper.getYear();
        return list;
    }

    public List<Map<String,Object>> getTask(Map<String, Object> map) {
        List<Map<String,Object>> list = AdsFileExpandMapper.getTask(map);
        return list;
    }
    public List<AdsFile> getIdByOrganTask(Map<String, Object> map) {
        List<AdsFile> list = AdsFileExpandMapper.getIdByOrganTask(map);
        return list;
    }

    public int updateForFile(Map<String, Object> map){
        return AdsFileExpandMapper.updateForFile(map);
    }

    public PageInfo<AdsFile> getPageInfoProblemFile(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsFileExpandMapper.getPageInfoProblemFile(map);
        long count = AdsFileExpandMapper.getCountProblemFile(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    public List<AdsFile> getAdsFileByCondition(Map<String, Object> map) {
        List<AdsFile> list = AdsFileExpandMapper.getAdsFileByCondition(map);
        return list;
    }

    public PageInfo<Map<String,Object>> getPageInfoAdsFileBySupId(Map<String, Object> map){
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = AdsFileExpandMapper.getAdsFileBySupId(map);
        long count = AdsFileExpandMapper.getCountBySupId(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
