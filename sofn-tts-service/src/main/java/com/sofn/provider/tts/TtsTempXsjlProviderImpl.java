package com.sofn.provider.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.core.util.StringUtils;
import com.sofn.dao.generator.TtsTempXsjlMapper;
import com.sofn.dao.tts.TtsTempXsjlExpandMapper;
import com.sofn.model.generator.TtsTempXsjl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
*	销售记录临时数据表 providerImpl 实现
 * Created by moon.l 
 */
@DubboService(interfaceClass = TtsTempXsjlProvider.class)
public class TtsTempXsjlProviderImpl extends BaseProviderImpl<TtsTempXsjl> implements TtsTempXsjlProvider {

    @Autowired
    private TtsTempXsjlExpandMapper TtsTempXsjlExpandMapper;
    @Autowired
    private TtsTempXsjlMapper ttsTempXsjlMapper;

    public PageInfo<TtsTempXsjl> getPageInfo(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String,Object>> list = TtsTempXsjlExpandMapper.getPageInfo(map);
        long count = TtsTempXsjlExpandMapper.getCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> getGroupProduct(Map<String,Object> map) {

        return TtsTempXsjlExpandMapper.getGroupProduct(map);
    }

    @Override
    public void deleteAll() {
        TtsTempXsjlExpandMapper.deleteAll();
    }


    public TtsTempXsjl insertData(TtsTempXsjl record){
        int result = 0;
        if (StringUtils.isBlank(record.getId())) {
            String key = record.getClass().getSimpleName();
            record.setId(createId(key));
            record.setCreateTime(new Date());

        }
        result = ttsTempXsjlMapper.insert(record);
        return record;
    }

    @Override
    public void deleteById(String id) {
        ttsTempXsjlMapper.deleteByPrimaryKey(id);
    }


}
