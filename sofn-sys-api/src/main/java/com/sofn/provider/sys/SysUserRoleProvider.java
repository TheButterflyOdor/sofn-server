package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/20.
 */
public interface SysUserRoleProvider extends BaseProvider<SysUserRole> {
    /**
     * 根据条件查询所有
     * @param param
     * @return
     */
    List<SysUserRole> queryByParam(@Param("param") Map<String, Object> param);

    //以下对业务平台提供接口-----------------------------------------------------------------------------
    /**
     * 根据用户ID 获取用户拥有的角色id集合
     * @param userId 用户id
     * @return
     */
    List<String> getRoleIdsByUser(String userId);

    public List<Map<String,Object>> getRoleByUserId(String userId);
}
