package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysCodeRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 */
@MyBatisDao
public interface SysCodeRuleExpandMapper extends BaseExpandMapper {
    List<SysCodeRule> getSysCodeListByPage(@Param("page") Page page);
    int getCount();

}
