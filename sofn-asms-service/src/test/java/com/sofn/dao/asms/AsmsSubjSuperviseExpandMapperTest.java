package com.sofn.dao.asms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年12月22日 下午 3:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:Spring-config.xml")
public class AsmsSubjSuperviseExpandMapperTest {
    @Autowired
    private AsmsSubjSuperviseExpandMapper subjSuperviseExpandMapper;
    @Test
    public void getSubjSuperviseList() throws Exception {
        Map queryMap = new HashMap<String,Object>();
        queryMap.put("pageOffset","0");
        queryMap.put("pageTail","10");
        queryMap.put("areaId","5101");
        queryMap.put("svId","");
        queryMap.put("svLevelId","");
        queryMap.put("svName","");
        queryMap.put("areaId","");
        queryMap.put("dateBegin", "");
        queryMap.put("dateEnd", "");
        List list = subjSuperviseExpandMapper.getSubjSuperviseList(queryMap);
        System.out.print(1);
    }

}