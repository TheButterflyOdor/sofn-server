package com.sofn.dao.ads;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.AdsInfoProject;

import java.util.List;
import java.util.Map;

/**
 * 检测信息_检测项目 ExpandMapper
 * @author moon.l
 */
@MyBatisDao
public interface AdsInfoProjectExpandMapper extends BaseExpandMapper {

   
    /**
    *  获取检测信息_检测项目列表
    */
    List<Map<String,Object>> getPageInfo(Map<String, Object> map);
    

	/**
	*  获取检测信息_检测项目数据条数
	*/
    long getCount(Map<String, Object> map);

    /**
     *  根据检测信息ID获取检测信息_检测项目列表
     */
    List<AdsInfoProject> getPageInfoWithCheckInfoId(Map<String, Object> map);

    /**
     *  根据检测信息ID获取检测信息_检测项目数据条数
     */
    long getCountWithCheckInfoId(Map<String, Object> map);

    /**
     *  获取检测信息_检测项目列表
     */
    void updateResult(Map<String, Object> map);

    /**
     *  获取检测信息_检测项目根据检测项目和信息ID
     */
    AdsInfoProject queryByProjectAndInfoId(Map<String, Object> map);

    /**
     *  获取检测信息_检测项目根据信息ID
     */
    List<AdsInfoProject> queryByInfoId(Map<String, Object> map);

    /**
     *  获取所有检测信息_检测项目根据信息ID
     */
    List<AdsInfoProject> queryListByInfoId(Map<String, Object> map);

    void updateMax(Map<String, Object> map);

}
