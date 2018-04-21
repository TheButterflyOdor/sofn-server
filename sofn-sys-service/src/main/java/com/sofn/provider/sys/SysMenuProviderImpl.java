package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.persistence.Page;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.generator.SysMenuMapper;
import com.sofn.dao.sys.SysMenuExpandMapper;
import com.sofn.model.generator.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/13.
 */
@DubboService(interfaceClass = SysMenuProvider.class)
public class SysMenuProviderImpl extends BaseProviderImpl<SysMenu> implements SysMenuProvider {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuExpandMapper sysMenuExpandMapper;

    /**
     * 新增菜单
     * @param sysMenu
     * @return
     */
    @Override
    public int addSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    /**
     * 查询菜单列表
     * @param map
     * @return
     */
    @Override
    public PageInfo<SysMenu> getSysMenuList(Map<String, Object> map) {
        PageInfo pageInfo = new PageInfo();
        List<Map<String, Object>> list = sysMenuExpandMapper.selectAllByCondition(map);
        long count = sysMenuExpandMapper.getAllCount(map);
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

    /**
     * 查询菜单详细
     * @param id
     * @return
     */
    @Override
    public SysMenu findDailyEnforceLawById(String id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    @Override
    public int updateSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.updateByPrimaryKey(sysMenu);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    public int deleteSysMenu(String id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public long getRecordsTotal(String menuName) {
            Map<String,Object> queryParams = new HashMap<>();
            queryParams.put("menuName", menuName);
            long count = sysMenuExpandMapper.getAllCount(queryParams);
            return count;
    }


    @Override
    public List<SysMenu> getSysMenuLists(Page pager, String menuName) {
        return sysMenuExpandMapper.getSysMenuLists(pager,menuName);
    }
    @Override
    public List<SysMenu> queryByParam(Map<String,Object> param) {
        return sysMenuExpandMapper.queryByParam(param);
    }

    @Override
    public List<SysMenu> getAllByDesc() {
        return sysMenuExpandMapper.getAllByDesc();
    }

    /**
     * 根据父id查询下面子菜单
     * @param
     * @return
     */
    @Override
    public List<SysMenu> getAllByParentId(String parentId,String menuName) {
        return sysMenuExpandMapper.getAllByParentId(parentId,menuName);
    }


    /**
     * 根据id查询齐下菜单
     * @param id
     * @return
     */
    @Override
    public List<SysMenu> recursionQuery(String id) {
        return sysMenuExpandMapper.recursionQuery(id);
    }

    /**
     * 根据序号查询
     * @param numbers
     * @return
     */
    @Override
    public List<SysMenu> getByNumber(String numbers) {
        return sysMenuExpandMapper.getByNumber(numbers);
    }

    /**
     * 批量修改序号
     * @param numbers
     */
    @Override
    public void updateAllNumber(String numbers) {
        sysMenuExpandMapper.updateAllNumber(numbers);
    }

    /**
     * 得到最大序号
     * @return
     */
    @Override
    public long getMaxNumber() {
        return sysMenuExpandMapper.getMaxNumber();
    }
}
