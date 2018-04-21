package com.sofn.service.sys;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysUser;
import com.sofn.provider.sys.TestExpandProvider;
import org.springframework.stereotype.Service;

/**
 * Created by codeai on 2016/11/21.
 * 事务测试
 */
@Service
public class TestService {
    @DubboReference
    private TestExpandProvider testExpandProvider;

    public void transactionalTest1(SysUser sysUser){
        testExpandProvider.transactionalTest1(sysUser);
    }
}
