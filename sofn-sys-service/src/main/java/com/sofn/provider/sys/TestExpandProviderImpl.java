package com.sofn.provider.sys;

import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sys.TestExpandMapper;
import com.sofn.model.generator.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by codeai on 2016/11/21.
 */
@DubboService(interfaceClass = TestExpandProvider.class)
@Transactional(readOnly = true)
public class TestExpandProviderImpl implements TestExpandProvider{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TestExpandMapper testExpandMapper;


    @Override
    @Transactional(readOnly = false)
    public void transactionalTest1(SysUser sysUser) {
        // 插入测试
        try{
            logger.debug("insertTest");
            testExpandMapper.insertTest(sysUser);
            throw  new RuntimeException("insertTest 异常");
        }catch (Exception e){
            logger.debug("insertTest");
        }

        sysUser.setAccount("dong4j1");
        try{
            testExpandMapper.updateTest(sysUser);
            throw  new RuntimeException("updateTest 异常");
        }catch (Exception e){
            logger.debug("updateTest");
        }
    }
}
