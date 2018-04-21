package com.sofn.dao.asms;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.sys.SysUserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@MyBatisDao
public interface AsmsBaseInspectionExpandMapper extends BaseExpandMapper{

    List<Map<String,Object>> getBaseInspectionList(Map<String, Object> map);

    long getBaseInspectionCount(Map<String, Object> map);

    List<Map<String,Object>> getBaseInspectionAllList(Map<String, Object> map);

    long getBaseInspectionAllCount(Map<String, Object> map);

    Map<String,Object> findEntTempById(String enterpriseId);

    long deleteBaseUserByInspectionId(String inspectionId);

    int updateElState(@Param("elState") String elState,@Param("baseInspectionId") String baseInspectionId);

    /**
     * 巡查人员添加列表
     * @param map
     * @return
     */
    int getSysUserCountByOrgId(Map map);

    List<SysUserBean> getSysUserListByOrgId(Map map);
}