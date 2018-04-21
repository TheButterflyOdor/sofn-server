package com.sofn.core.listener;

/**
 * Created by dong4j on 16/9/3.
 * Description:
 */

import com.sofn.core.config.Global;
import com.sofn.core.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerListerner implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void contextDestroyed(ServletContextEvent contextEvent) {
    }

    public void contextInitialized(ServletContextEvent contextEvent) {
        logger.info("=================================");
        logger.info("系统[{}]启动完成!!!", contextEvent.getServletContext().getServletContextName());
        logger.info("=================================");
        logger.debug("redis.host={}", PropertiesUtil.getString("redis1.host"));
        logger.debug("dubbo.registry.address={}", Global.getConfig("dubbo.registry.address"));
        logger.debug("mq.brokerURL={}", Global.getConfig("mq.brokerURL"));
        logger.debug("sftp.host={}", Global.getConfig("sftp.host"));
        logger.debug("db.driver={}", Global.getConfig("db.driver"));
        logger.debug("加载的配置文件 {}",Global.getConfig("sofn.environment"));
    }
}
