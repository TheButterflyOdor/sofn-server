package com.sofn.provider.sys;

import com.sofn.core.constant.DictType;
import com.sofn.dao.sys.SysDictExpandMapper;
import com.sofn.model.generator.SysDictData;
import com.xiaoleilu.hutool.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by: dong4j.
 * Date: 2016-11-15.
 * Time: 14:19.
 * Description: 数据字典接口测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-dao.xml" })
public class SysDictProviderImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysDictExpandMapper sysDictExpandMapper;
    @Test
    public void testGetDictByType() throws Exception {
        List<SysDictData> list = sysDictExpandMapper.getDictByType(DictType.INDUSTRYTYPE.getCode());
        logger.debug("",list);
        JSONArray jsonArray = new JSONArray(list);
        System.out.println(jsonArray.toString());

    }

    @Test
    public void testGetDictByType1() throws Exception {
        List<SysDictData> list = sysDictExpandMapper.getUnits(DictType.UNITTYPE.getCode(),"cda3b1cf15b04034b9143ce1ce5458704082f3303982414395f57012fb3fccf8");
        logger.debug("",list);
        JSONArray jsonArray = new JSONArray(list);
        System.out.println(jsonArray.toString());
    }
}