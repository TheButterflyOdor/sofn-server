package com.sofn.provider.sys;

import com.sofn.model.generator.SysUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by codeai on 2016/11/21.
 */
public interface TestExpandProvider {
    @Transactional
    void transactionalTest1(SysUser sysUser);
}
