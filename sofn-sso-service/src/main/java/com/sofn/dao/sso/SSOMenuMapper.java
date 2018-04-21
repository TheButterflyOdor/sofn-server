package com.sofn.dao.sso;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.sso.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by qjh on 2016/10/13.
 */
@MyBatisDao
public interface SSOMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getSysMenuByUserId( @Param("userId") String userId,@Param("type") String type);
    List<SysMenu> getAllMenus();
}
