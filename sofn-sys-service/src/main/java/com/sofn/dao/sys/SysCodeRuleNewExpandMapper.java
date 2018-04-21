package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.core.persistence.Page;
import com.sofn.model.generator.SysCodeRule;
import com.sofn.model.generator.SysCodeRuleNew;
import org.apache.ibatis.annotations.Param;

import java.sql.Blob;
import java.util.List;

/**
 * @author gaoyi
 * @version 2017/4/25
 */
@MyBatisDao
public interface SysCodeRuleNewExpandMapper extends BaseExpandMapper {

    List<SysCodeRuleNew> getSysCodeListByPage(@Param("page") Page page);

    int getCount();

    SysCodeRuleNew getDemoImg(String ruleId);

    SysCodeRuleNew queryRuleAndField(String ruleId);
}
