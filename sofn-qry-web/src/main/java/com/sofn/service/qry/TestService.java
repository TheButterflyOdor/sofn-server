package com.sofn.service.qry;

import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.qry.SysTemplate;
import com.sofn.provider.qry.TestExpandProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 测试 service 业务逻辑
 * @author Liw
 *
 */
@Service
public class TestService {
    @DubboReference
    TestExpandProvider provider ;

    public List<SysTemplate> getPageInfo(Page pager, String templateName, String templateType) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pager",pager);
        map.put("templateName",templateName);
        map.put("templateType",templateType);
        return provider.getPageInfo(map) ;
    }
    public long getRecordsTotal(String templateName,String templateType){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("templateName",templateName);
        queryParams.put("templateType",templateType);
        return provider.getCount(queryParams);
    }
}