package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.AsmsSubjEntBadrecord;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
public interface AsmsSubjEntBadrecordProvider extends BaseProvider {

    public PageInfo<AsmsSubjEntBadrecord> getAsmsSubjEntBadrecordList(Map<String, Object> map);

    public Map<String,Object> findEnterpriseById(String enterpriseId);

    public AsmsSubjEntBadrecord findBadrecordById(String id);

    public PageInfo getAsmsSubjEntBadrecordByIdList(Map<String, Object> map);

}
