package com.sofn.web.sys;


import com.sofn.service.sys.SysResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/10/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-config.xml","classpath:Spring-servlet.xml" })
public class SysResourceTest {

    @Autowired
    private SysResourceService service;
    @Test
    public void queryTest() {
        System.out.print(service.queryById("1"));

    }

}
