package com.sofn.provider.sys;

import com.sofn.core.base.BaseProvider;
import com.sofn.model.generator.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdong on 2016/9/20.
 */
public interface SysRoleMenuProvider extends BaseProvider<SysRoleMenu> {
    /**
     * 根据条件查询所有
     * @param param
     * @return
     */
    List<SysRoleMenu> queryByParam(@Param("param") Map<String, Object> param);
}
