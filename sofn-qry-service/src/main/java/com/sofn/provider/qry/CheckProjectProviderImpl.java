package com.sofn.provider.qry;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseModel;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.CheckProjectExpandMapper;
import com.sofn.model.qry.CheckProject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
@DubboService(interfaceClass = CheckProjectProvider.class)
public class CheckProjectProviderImpl extends BaseProviderImpl implements CheckProjectProvider {

    @Autowired
    private CheckProjectExpandMapper checkProjectExpandMapper;


    @Override
    public PageInfo<Map<String, Object>> getPageInfoParams(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = checkProjectExpandMapper.getPageInfo(params);
        long count = checkProjectExpandMapper.getCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    @Override
    public PageInfo<Map<String, Object>> getTestedDeaparment(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = checkProjectExpandMapper.getTestedDeaparment(params);
        //long count = checkProjectExpandMapper.getCount(params);
        long count = checkProjectExpandMapper.getCountByTestedDeaparment(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }


    @Override
    public PageInfo<Map<String, Object>> getCheckInfo(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = checkProjectExpandMapper.getCheckInfo(params);
        long count = checkProjectExpandMapper.getCheckCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<CheckProject> getCheckList(Map<String, Object> map) {
        return checkProjectExpandMapper.getCheckList(map);
    }

    @Override
    public List<Map<String, Object>> getHangye(Map<String, Object> map) {
        List<Map<String, Object>> list = checkProjectExpandMapper.getHangye( map);
        return list;
    }

    @Override
    public PageInfo<Map<String, Object>> getSampleName(Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = checkProjectExpandMapper.getSampleName(params);
        long count = checkProjectExpandMapper.getNameCount(params);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;

    }

    @Override
    public List<Map<String,Object>> getCkeckById(Map<String, Object> map) {
        List<Map<String,Object>> checkProject=checkProjectExpandMapper.getCkeckById(map);
        return checkProject;
    }

    @Override
    public long getTypeCount(Map<String, Object> map){
        return checkProjectExpandMapper.getTypeCount(map);
    };


}
