package com.sofn.web.tts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by sofn on 2017/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:Spring-config.xml", "classpath:Spring-servlet.xml"})
public class QueryControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
//        手动将WebApplication上下文添加到ContextLoderListner
        MockServletContext sc = new MockServletContext("");
        ServletContextListener listener = new ContextLoaderListener(wac);
        ServletContextEvent event = new ServletContextEvent(sc);
        //解决ContextLoader.getCurrentWebApplicationContext();为空
        listener.contextInitialized(event);

        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getSampleSingleInfo() throws Exception {
        mockMvc.perform((post("/query/getSampleSingleInfo/1.2.156.326.8.2.0.000123456789987654.01010101.20170515162828.000")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getProAndSubInfo() throws Exception {
        mockMvc.perform((get("/query/getProAndSubInfo/21356483466561294594")))
                .andExpect(status().isOk())
                .andDo(print());
    }

}