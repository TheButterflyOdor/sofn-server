package com.sofn.provider.qry;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.qry.AsmsSubjEntBadrecordExpandMapper;
import com.sofn.model.generator.AsmsSubjEntBadrecord;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
@DubboService(interfaceClass = AsmsSubjEntBadrecordProvider.class)
public class AsmsSubjEntBadrecordImpl extends BaseProviderImpl implements AsmsSubjEntBadrecordProvider {
    @Autowired(required = false)
    private AsmsSubjEntBadrecordExpandMapper asmsSubjEntBadrecordExpandMapper;

    @Override
    public PageInfo<AsmsSubjEntBadrecord> getAsmsSubjEntBadrecordList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = asmsSubjEntBadrecordExpandMapper.selectAllByCondition(map);
        long count = asmsSubjEntBadrecordExpandMapper.getAsmsSubjEntBadrecordCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }
    @Override
    //暂加
    public Map<String, Object> findEnterpriseById(String enterpriseId) {
        return asmsSubjEntBadrecordExpandMapper.findEnterpriseById(enterpriseId);
    }

    @Override
    public AsmsSubjEntBadrecord findBadrecordById(String id) {
        return null;
    }


    @Override
    public PageInfo<AsmsSubjEntBadrecord> getAsmsSubjEntBadrecordByIdList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(asmsSubjEntBadrecordExpandMapper.getSubjEntBadrecordByIdCount(map));
        pageInfo.setList(asmsSubjEntBadrecordExpandMapper.findSubjEntBadrecodeById(map));
        return pageInfo;
    }
}
