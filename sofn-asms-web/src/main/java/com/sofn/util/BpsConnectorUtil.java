package com.sofn.util;

import com.eos.workflow.api.BPSServiceClientFactory;
import com.eos.workflow.api.IBPSServiceClient;
import com.primeton.workflow.api.WFServiceException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author sofn
 * @version 2018年02月10日 下午 4:42
 */
public class BpsConnectorUtil {
    private static String username;
    private static String password;

    static{
        init();
    }

    //建立客户端连接
    public static IBPSServiceClient getIBPSServiceClient(){
        IBPSServiceClient client = null;
        try {
            BPSServiceClientFactory.getLoginManager().setCurrentUser(username, username);
            client = BPSServiceClientFactory.getDefaultClient();
        } catch (WFServiceException e) {
            e.printStackTrace();
        }
        return client;
    }

    //初始化读取配置文件
    private static void init () {
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties("system.properties");
            username = props.getProperty("bps.username");
            password = props.getProperty("bps.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
