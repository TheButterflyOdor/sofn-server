package com.sofn.dao.qry;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.qry.CheckProject;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/11.
 */
@MyBatisDao
public interface CheckProjectExpandMapper extends BaseExpandMapper {
    /**
     *  获取检测项目列表
     */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);

    /**
     *根据受检单位获取检测项目
     */
    List<Map<String,Object>> getTestedDeaparment(Map<String, Object> map);

 /**
     *  获取检测项目名称列表
     */
    List<Map<String,Object>> getCheckInfo(Map<String, Object> map);

    /**
     *  获取检测项目数据条数
     */
    long getCount(Map<String, Object> map);

    /**
     *  获取产品名称数据条数
     */
    long getNameCount(Map<String, Object> map);

    /**
     *  获取检测项目名称数据条数
     */
    long getCheckCount(Map<String, Object> map);


   /**
     *  获取检测项目类型数据条数
     */
    long getTypeCount(Map<String, Object> map);

    /**
     *  获取行业列表
     */
    List<Map<String,Object>> getHangye(Map<String, Object> map);

    /**
     *  获取产品名称列表
     */
    List<Map<String,Object>> getSampleName(Map<String, Object> map);

    /**
     * 通过主键获取对象
     */

    List<Map<String,Object>>  getCkeckById (Map<String, Object> map);


    /**
     * 根据条件查询检测项目汇总数据，不分页，用作数据导出
     * @param map
     * @return
     */
    public List<CheckProject> getCheckList(Map<String, Object> map);

    /**
     *  获取检测项目数据条数
     */
    long getCountByTestedDeaparment(Map<String, Object> map);

}
