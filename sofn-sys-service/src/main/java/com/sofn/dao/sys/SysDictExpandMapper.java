package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysDictData;
import com.sofn.model.generator.SysDictType;
import com.sofn.model.sys.SysDictTypeDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by: dong4j.
 * Date: 2016-10-19.
 * Time: 18:11.
 * Description:
 */
@MyBatisDao
public interface SysDictExpandMapper extends BaseExpandMapper {
    List<SysDictTypeDto> getParentList(@Param("id")String id);
    List<SysDictTypeDto> getDictTypes();
    List<SysDictType> getDictTypeByPid(@Param("pid") String pid, @Param("keyWord") String keyWord);
    List<SysDictData> getDictDataByType(@Param("id") String id, @Param("keyWord") String keyWord, @Param("page") Page page);
    Integer getRecordsTotal(@Param("id") String id,@Param("keyWord") String keyWord);
    // 逻辑删除数据
    void deleteDictData(@Param("id") String id);
    Integer getDictTypesTotal();
    Integer updateEnable(@Param("enable")String enable,@Param("id")String id);
    List<SysDictData> getDictByType(@Param("code") String code);
    List<SysDictData> getUnits(@Param("code") String code,@Param("id") String id);
    SysDictData getIdByName(@Param("name") String name);
    List<SysDictData> getDictByName(@Param("dictTypeName") String dictTypeName);
}
