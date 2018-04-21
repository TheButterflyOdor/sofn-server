package com.sofn.provider.sys;

import com.sofn.dao.generator.SysResourceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/10/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-dao.xml" })
public class SysResourceProviderImplTest {
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Test
    public void queryTest() {
        System.out.println(sysResourceMapper.selectByPrimaryKey("1"));
    }
}