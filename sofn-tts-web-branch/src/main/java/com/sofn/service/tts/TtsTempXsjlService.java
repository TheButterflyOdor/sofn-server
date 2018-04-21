package com.sofn.service.tts;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.TtsTempXsjl;
import com.sofn.provider.tts.TtsTempXsjlProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售记录临时数据表 service 业务逻辑
 * @author moon.l
 *
 */
@Service
public class TtsTempXsjlService extends BaseService<TtsTempXsjlProvider, TtsTempXsjl> {

    @DubboReference
    public void setTtsTempXsjlProvider(TtsTempXsjlProvider provider) {
        this.provider = provider;
    }

    public PageInfo getPageInfo(TtsTempXsjl bean, int pageNum, int pageSize) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("pageNum", pageNum);
        queryParams.put("pageSize", pageSize);
        return provider.getPageInfo(queryParams);
    }


    public List<Map<String,Object>> getGroupProduct(Map<String,Object> map){

        return provider.getGroupProduct(map);
    }

    public void deleteAll(){
        provider.deleteAll();
    }

    @Override
    public TtsTempXsjl add(TtsTempXsjl record) {
        String uid = WebUtil.getCurrentUserId();
        if(StringUtils.isBlank(record.getDelFlag())){
            record.setDelFlag("N");
        }
        return provider.insertData(record);
    }


    public void deleteById(String id){

        provider.deleteById(id);
    }


}