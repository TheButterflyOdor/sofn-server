package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProvider;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysMenu;

import java.util.List;
import java.util.Map;


/**
 * Created by qjh  on 2016/10/13.
 */
public interface SysMenuProvider extends BaseProvider<SysMenu> {


    int addSysMenu(SysMenu sysMenu);

    PageInfo<SysMenu> getSysMenuList(Map<String, Object> map);

    SysMenu findDailyEnforceLawById(String id);

    int updateSysMenu(SysMenu sysMenu);

    int deleteSysMenu(String id);

    long getRecordsTotal(String menuName);

    List<SysMenu> getSysMenuLists(Page pager, String menuName);
    List<SysMenu> queryByParam(Map<String,Object> param);

    List<SysMenu> getAllByDesc();

    List<SysMenu> getAllByParentId(String parentId,String menuName);

    List<SysMenu> recursionQuery(String id);

    List<SysMenu> getByNumber(String numbers);

    public void updateAllNumber(String numbers);

    long getMaxNumber();

}
