package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by qjh on 2016/10/13.
 */
@MyBatisDao
public interface SysMenuExpandMapper extends BaseExpandMapper {
    //获取条件数据
    List<Map<String,Object>> selectAllByCondition(Map<String, Object> map);
    //获取满足条件的记录数
    long getAllCount(Map<String, Object> map);

    List<SysMenu> getSysMenuLists(@Param("pager") Page pager, @Param("menuName")String menuName);
    List<SysMenu> queryByParam(Map<String,Object> map);

    List<SysMenu> getAllByDesc();

    List<SysMenu> getAllByParentId(@Param("parentId")String parentId,@Param("menuName")String menuName);

    List<SysMenu> recursionQuery(@Param("id")String id);

    List<SysMenu> getByNumber(@Param("numbers")String numbers);

    public void updateAllNumber(@Param("numbers")String numbers);

    long getMaxNumber();
}
