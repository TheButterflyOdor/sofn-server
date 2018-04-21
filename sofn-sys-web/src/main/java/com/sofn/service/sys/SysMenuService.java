package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.generator.SysMenu;
import com.sofn.provider.sys.SysMenuProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/14.
 */
@Service
public class SysMenuService extends BaseService<SysMenuProvider,SysMenu> {

    @DubboReference
    public void setSysMenuProvider(SysMenuProvider provider){
        this.provider = provider;
    }
    //获取菜单列表
    public PageInfo getSysMenuList(String menuName, int pageNum, int pageSize){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("menuName", menuName);
        queryParams.put("pageNum",pageNum);
        queryParams.put("pageSize",pageSize);
        return provider.getSysMenuList(queryParams);
    }
    //添加菜单
    public int addSysMenu(SysMenu sysMenu){
        return provider.addSysMenu(sysMenu);
    }
    //根据类型查找菜单
    public SysMenu findSysMenuByst(String id){
        return provider.findDailyEnforceLawById(id);
    }
    //修改菜单信息
    public  int updateSysMenu(SysMenu sysMenu){
        return provider.updateSysMenu(sysMenu);
    }
    //删除菜单
    public int deleteSysMenu(String id){
        return provider.deleteSysMenu(id);
    }

    public List<SysMenu> getSysMenuLists(Page pager, String menuName){
        return provider.getSysMenuLists(pager, menuName);
    }
    public List<SysMenu> queryByParam(Map<String,Object> param){
        return provider.queryByParam(param);
    }
    //查询所有菜单
    public List<SysMenu> getAllByDesc(){
        return provider.getAllByDesc();
    }

    public long getRecordsTotal(String menuName){
        return provider.getRecordsTotal(menuName);
    }


    /**
     * 根据父id查询下面子菜单
     * @param parentId
     * @return
     */
    public List<SysMenu> getAllByParentId(String parentId,String menuName){
        return provider.getAllByParentId(parentId,menuName);
    }

    /**
     * 根id查询下面子菜单
     * @param id
     * @return
     */
    public List<SysMenu> recursionQuery(String id){
        return provider.recursionQuery(id);
    }


    public List<SysMenu> getByNumber(String numbers){
        return provider.getByNumber(numbers);
    }

    public void updateAllNumber(String numbers){
        provider.updateAllNumber(numbers);
    }

    public long getMaxNumber(){
        return provider.getMaxNumber();
    }
}
