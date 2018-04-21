package com.sofn.provider.asms;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.asms.AsmsSubjEntExpandMapper;
import com.sofn.dao.generator.AsmsSubjEntTempMapper;
import com.sofn.model.generator.AsmsSubjEntTemp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年10月17日 下午 2:20
 */
@DubboService(interfaceClass = AsmsSubjEntProvider.class)
public class AsmsSubjEntProviderImpl extends BaseProviderImpl<AsmsSubjEntTemp> implements AsmsSubjEntProvider{

    @Autowired
    private AsmsSubjEntExpandMapper subjEntExpandMapper;
    @Autowired
    private AsmsSubjEntTempMapper subjEntTempMapper;

    @Override
    public PageInfo getSubjEntTempList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(subjEntExpandMapper.getSubjEntTempList(map));
        pageInfo.setTotal(subjEntExpandMapper.getSubjEntTempCount(map));
        return pageInfo;
    }

    @Override
    public List<AsmsSubjEntTemp> getSubjEntTempListByCode(String code) {
        return subjEntExpandMapper.getSubjEntTempListByCode(code);
    }

    @Override
    public AsmsSubjEntTemp findSubjEntTempById(String id) {
        return subjEntTempMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addSubjEntTemp(AsmsSubjEntTemp subjEntTemp) {
        return subjEntTempMapper.insert(subjEntTemp);
    }
}
