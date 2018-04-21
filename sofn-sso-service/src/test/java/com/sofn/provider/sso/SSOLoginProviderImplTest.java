package com.sofn.provider.sso;

import com.sofn.dao.sso.SSOLoginMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by: dong4j.
 * Date: 2016-11-17.
 * Time: 18:15.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:Spring-dao.xml" })
public class SSOLoginProviderImplTest {
    @Autowired
    private SSOLoginMapper ssoLoginMapper;
    @Test
    public void testLogin() throws Exception {
        System.out.println(ssoLoginMapper.login("dong4j","dong4j"));
    }
}