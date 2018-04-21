package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.qry.CheckProject;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
public interface CheckProjectProvider extends BaseProvider {

    public PageInfo<Map<String, Object>> getPageInfoParams(Map<String, Object> params);
    public PageInfo<Map<String, Object>> getTestedDeaparment(Map<String, Object> params);

    public List<Map<String, Object>> getHangye(Map<String, Object> map);

    public PageInfo<Map<String, Object>> getSampleName(Map<String, Object> params);


    public List<Map<String, Object>> getCkeckById(Map<String, Object> map);

    public PageInfo<Map<String, Object>> getCheckInfo(Map<String, Object> params);


    public List<CheckProject> getCheckList(Map<String, Object> map);


    public long getTypeCount(Map<String, Object> map);

}
